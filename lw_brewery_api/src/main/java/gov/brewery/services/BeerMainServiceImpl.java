package gov.brewery.services;

import com.deloitte.nextgen.framework.commons.payload.response.AuthResponse;
import com.deloitte.nextgen.framework.commons.spi.KairosClock;
import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.security.spi.TokenService;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import gov.brewery.model.BeerDTO;
import gov.brewery.model.BeerStyle;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by jt, Spring Framework Guru.
 */
@Slf4j
@Service
public class BeerMainServiceImpl implements BeerMainService {

    private Map<UUID, BeerDTO> beerMap;

    @Autowired
    private TokenService jwtTokenService;

    @Autowired
    private ReferenceTable reftableManager;

    @Autowired
    private CacheManager cacheManager;

    private KairosClock kairosClock;

    public BeerMainServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
//                .createdDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
//                .createdDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
//                .createdDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer) {
        BeerDTO existing = beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())){
            existing.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existing.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getPrice() != null) {
            existing.setPrice(beer.getPrice());
        }

        if (beer.getQuantityOnHand() != null){
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existing.setUpc(beer.getUpc());
        }

        return Optional.of(existing);
    }

    @Override
    @Deprecated
    public List<BeerDTO> findAllBeersByQuantityOnHand(Integer quantityOnHand) {
        return null;
    }

    @Override
    public Boolean deleteById(UUID beerId) {
        beerMap.remove(beerId);

        return true;
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        BeerDTO existing = beerMap.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setPrice(beer.getPrice());
        existing.setUpc(beer.getUpc());
        existing.setQuantityOnHand(beer.getQuantityOnHand());
        return Optional.of(existing);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize){
        return new PageImpl<>(new ArrayList<>(beerMap.values()));
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get Beer by Id - in service. Id: " + id.toString());

        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {

        BeerDTO savedBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
//                .createdDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .quantityOnHand(beer.getQuantityOnHand())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }

    @Override
    public AuthResponse getToken(String token) throws JOSEException, ParseException, IOException {


        Map<String, Object> claims =  jwtTokenService.validate(token);

        Map<String,Object> newClaims = new HashMap<>(claims);

        newClaims.put("fname", "aayush");
        newClaims.put("laame", "sdjhgcz");



        return   jwtTokenService.expiring((String) claims.get("sub"), newClaims);
    }

    @Cacheable(value = "employeeCache", key="#empId")
    public String getEmployee(String empId) {
        System.out.println(" the record with id : " + empId);
        Cache cache = cacheManager.getCache("employeeCache");
        Cache.ValueWrapper valueMapper = cache.get(empId);
        Object obje = (String)valueMapper.get();
        return empId;
    }

    @CachePut(value = "employeeCache", key = "#updatedValue")
    public String updateEmployee(String updatedValue) {
//    	Cache cache = cacheManager.getCache("employeeCache");
//    	Cache.ValueWrapper valueMapper = cache.get(updatedValue);
//    	Object obje = (String)valueMapper.get();
        System.out.println("Update the record with id : " + updatedValue);
        return updatedValue;
    }

    @CacheEvict(value = "employeeCache", key = "#empId")
    public void deleteEmployee(String empId) {
        Cache cache = cacheManager.getCache("employeeCache");
        Cache.ValueWrapper valueMapper = cache.get(empId);
        Object obje = (String)valueMapper.get();


        System.out.println("Delete the record with id : " + obje);
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

//    	log.info(LogMarker.SECRET, "{aayushGandhi}", aayushGandhi);
//    	log.info(LogMarker.SECRET_LEVEL_ONE,"{aayushGandhi}",aayushGandhi);
//    	log.info(LogMarker.SECRET_LEVEL_TWO, "{aayushGandhi}",aayushGandhi);
//
        log.info(LogMarker.SECRET, "this will mask all {}", aayushGandhi);
        log.info(LogMarker.SECRET_LEVEL_ONE, "this will ending  partial {}", aayushGandhi);
        log.info(LogMarker.SECRET_LEVEL_TWO, "this will starting partial {}", aayushGandhi);
        //	log.info(aayushGandhi);

        return aayushGandhi;
    }

    @Override
    public String getTime(String value) {

        LocalDate localDate = kairosClock.localDate();

        return value;
    }

}

















