package gov.brewery.services;

import com.deloitte.nextgen.framework.commons.payload.response.AuthResponse;
import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.security.spi.TokenService;
import com.nimbusds.jose.JOSEException;
import gov.brewery.repositories.IBeerCustomRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import gov.brewery.entities.Beer;
import gov.brewery.generated.repository.BeerRepository;
import gov.brewery.mappers.BeerMapper;
import gov.brewery.model.BeerDTO;
import gov.brewery.model.BeerStyle;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by jt, Spring Framework Guru.
 */
@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class BeerServiceJPA implements BeerMainService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    private final IBeerCustomRepository beerCustomRepository;
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Autowired
    TokenService jwtTokenService;

    @Autowired
    private ReferenceTable reftableManager;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory,
                                   Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<Beer> beerPage;

        if(StringUtils.hasText(beerName) && beerStyle == null) {
            beerPage = listBeersByName(beerName, pageRequest);
        } else if (!StringUtils.hasText(beerName) && beerStyle != null){
            beerPage = listBeersByStyle(beerStyle, pageRequest);
        } else if (StringUtils.hasText(beerName) && beerStyle != null){
            beerPage = listBeersByNameAndStyle(beerName, beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if (showInventory != null && !showInventory) {
            beerPage.forEach(beer -> beer.setQuantityOnHand(null));
        }

//        return beerPage.map(beerMapper::beerToBeerDto);
        return beerPage.map(beerMapper::toResponse);

    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 1000) {
                queryPageSize = 1000;
            } else {
                queryPageSize = pageSize;
            }
        }

        Sort sort = Sort.by(Sort.Order.asc("beerName"));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

    private Page<Beer> listBeersByNameAndStyle(String beerName, BeerStyle beerStyle, Pageable pageable) {
        return beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle("%" + beerName + "%",
                beerStyle, pageable);
    }

    public Page<Beer> listBeersByStyle(BeerStyle beerStyle, Pageable pageable) {
        return beerRepository.findAllByBeerStyle(beerStyle, pageable);
    }

    public Page<Beer> listBeersByName(String beerName, Pageable pageable){
        return beerRepository.findAllByBeerNameIsLikeIgnoreCase("%" + beerName + "%", pageable);
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(beerMapper.toResponse(beerRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
//        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
    	return beerMapper.toResponse(beerRepository.save(beerMapper.toEntity(beer)));
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setPrice(beer.getPrice());
            foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            atomicReference.set(Optional.of(beerMapper
                    .toResponse(beerRepository.save(foundBeer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID beerId) {
        if (beerRepository.existsById(beerId)) {
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            if (StringUtils.hasText(beer.getBeerName())){
                foundBeer.setBeerName(beer.getBeerName());
            }
            if (beer.getBeerStyle() != null){
                foundBeer.setBeerStyle(beer.getBeerStyle());
            }
            if (StringUtils.hasText(beer.getUpc())){
                foundBeer.setUpc(beer.getUpc());
            }
            if (beer.getPrice() != null){
                foundBeer.setPrice(beer.getPrice());
            }
            if (beer.getQuantityOnHand() != null){
                foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            }
//            atomicReference.set(Optional.of(beerMapper
//                    .beerToBeerDto(beerRepository.save(foundBeer))));
            atomicReference.set(Optional.of(beerMapper
                    .toResponse(beerRepository.save(foundBeer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    public List<BeerDTO> findAllBeersByQuantityOnHand(Integer quantityOnHand){
        List<BeerDTO> beerDTOS= new ArrayList<>();
        beerCustomRepository.findAllBeersByQuantityOnHand(quantityOnHand).forEach(beer -> {
            BeerDTO beerDTO = beerMapper.toResponse(beer);
            beerDTOS.add(beerDTO);
        });
        return beerDTOS;
    }

    @Override
    public AuthResponse getToken(String token) throws JOSEException, ParseException, IOException {

        Map<String, Object> claims =  jwtTokenService.validate(token);

        Map<String,Object> newClaims = new HashMap<>(claims);

        newClaims.put("fname", "aayush");
        newClaims.put("laame", "sdjhgcz");



        return   jwtTokenService.expiring((String) claims.get("sub"), newClaims);
    }

    @Override
    @Cacheable(value = "employeeCache", key="#empId" )
    public String getEmployee(String empId) {
        System.out.println(" the record with id : " + empId);
        Cache cache = cacheManager.getCache(empId);
        Cache.ValueWrapper valueMapper = cache.get("empId");
        return "empId";

    }

    @CachePut(value = "employeeCache", key = "#updatedValue")
    public String updateEmployee(String updatedValue) {
//    	Cache cache = cacheManager.getCache("employeeCache");
//    	Cache.ValueWrapper valueMapper = cache.get(updatedValue);
//    	Object obje = (String)valueMapper.get();
        System.out.println("Update the record with id : " + updatedValue);
        return updatedValue;
    }

    @Override
    @CacheEvict(value = "employeeCache", key = "#empId")
    public void deleteEmployee(String empId) {
        System.out.println("Delete the record with id : " + empId);
    }

    @Override
    public String getEmployeefromBean(String key) {
        System.out.println(" the record with id : " + key);
        Cache cache = cacheManager.getCache("employeeCache");
//		String cacheName = cache.getName();

        Cache.ValueWrapper valueMapper = cache.get(key);
        System.out.println(" cacheName : " + valueMapper);
        Object obje = (String)valueMapper.get();
        System.out.println(" the obj : " + obje);
        return (String) obje;
    }

    @Override
    public String checkForLog(String aayushGandhi) {

//	    	log.info(LogMarker.SECRET, "{aayushGandhi}", aayushGandhi);
//	    	log.info(LogMarker.SECRET_LEVEL_ONE,"{aayushGandhi}",aayushGandhi);
//	    	log.info(LogMarker.SECRET_LEVEL_TWO, "{aayushGandhi}",aayushGandhi);
//
        log.info(LogMarker.SECRET, "this will mask all {}", aayushGandhi);
        log.info(LogMarker.SECRET_LEVEL_ONE, "this will ending  partial {}", aayushGandhi);
        log.info(LogMarker.SECRET_LEVEL_TWO, "this will starting partial {}", aayushGandhi);

        //	log.info(aayushGandhi);

        return aayushGandhi;
    }

}
