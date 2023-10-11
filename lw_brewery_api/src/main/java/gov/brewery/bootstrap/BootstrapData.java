package gov.brewery.bootstrap;

import gov.brewery.entities.BeerOrder;
import gov.brewery.entities.BeerOrderShipment;
import gov.brewery.repositories.BeerOrderRepository;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import gov.brewery.entities.Beer;
import gov.brewery.entities.Customer;
import gov.brewery.generated.repository.BeerRepository;
import gov.brewery.model.BeerCSVRecord;
import gov.brewery.model.BeerStyle;
import gov.brewery.repositories.BeerRepo;
import gov.brewery.repositories.CustomerRepository;
import gov.brewery.services.BeerCsvService;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    private final BeerOrderRepository beerOrderRepository;
    private final BeerCsvService beerCsvService;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCsvData();
        loadCustomerData();
        loadBeerOrderData();
    }

    private void loadCsvData() throws FileNotFoundException {
        if (beerRepository.count() < 10){
            File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

            List<BeerCSVRecord> recs = beerCsvService.convertCSV(file);

            recs.forEach(beerCSVRecord -> {
                BeerStyle beerStyle = switch (beerCSVRecord.getStyle()) {
                    case "American Pale Lager" -> BeerStyle.LAGER;
                    case "American Pale Ale (APA)", "American Black Ale", "Belgian Dark Ale", "American Blonde Ale" ->
                            BeerStyle.ALE;
                    case "American IPA", "American Double / Imperial IPA", "Belgian IPA" -> BeerStyle.IPA;
                    case "American Porter" -> BeerStyle.PORTER;
                    case "Oatmeal Stout", "American Stout" -> BeerStyle.STOUT;
                    case "Saison / Farmhouse Ale" -> BeerStyle.SAISON;
                    case "Fruit / Vegetable Beer", "Winter Warmer", "Berliner Weissbier" -> BeerStyle.WHEAT;
                    case "English Pale Ale" -> BeerStyle.PALE_ALE;
                    default -> BeerStyle.PILSNER;
                };

                
//                beerRepository.save(Beer.builder()
//                                .beerName(StringUtils.abbreviate(beerCSVRecord.getBeer(), 50))
//                                .beerStyle(beerStyle)
//                                .price(BigDecimal.TEN)
//                                .upc(beerCSVRecord.getRow().toString())
//                                .quantityOnHand(beerCSVRecord.getCount())
//                        .build());
                
                Beer beer = new Beer();
                beer.setBeerName(StringUtils.abbreviate(beerCSVRecord.getBeer(), 50));
        		beer.setBeerStyle(beerStyle);
                beer.setPrice(BigDecimal.TEN);
                beer.setUpc(beerCSVRecord.getRow().toString());
                beer.setQuantityOnHand(beerCSVRecord.getCount());
                beerRepository.save(beer);
            });
        }
    }

    private void loadBeerData() {
        if (beerRepository.count() == 0){
//            Beer beer1 = Beer.builder()
//                    .beerName("Galaxy Cat")
//                    .beerStyle(BeerStyle.PALE_ALE)
//                    .upc("12356")
//                    .price(new BigDecimal("12.99"))
//                    .quantityOnHand(122)
////                    .createdDate(LocalDateTime.now())
////                    .updateDate(LocalDateTime.now())
//                    .build();
        	
            Beer beer1 = new Beer();
            beer1.setBeerName("Galaxy Cat");
            beer1.setBeerStyle(BeerStyle.PALE_ALE);
            beer1.setUpc("12356");
            beer1.setPrice(new BigDecimal("12.99"));
            beer1.setQuantityOnHand(122);
//                    .createdDate(LocalDateTime.now())
//                    .updateDate(LocalDateTime.now())


//            Beer beer2 = Beer.builder()
//                    .beerName("Crank")
//                    .beerStyle(BeerStyle.PALE_ALE)
//                    .upc("12356222")
//                    .price(new BigDecimal("11.99"))
//                    .quantityOnHand(392)
////                    .createdDate(LocalDateTime.now())
////                    .updateDate(LocalDateTime.now())
//                    .build();
            
            Beer beer2 = new Beer();
            beer2.setBeerName("Crank");
            beer2.setBeerStyle(BeerStyle.PALE_ALE);
            beer2.setUpc("12356222");
            beer2.setPrice(new BigDecimal("11.99"));
            beer2.setQuantityOnHand(392);
           

//            Beer beer3 = Beer.builder()
//                    .beerName("Sunshine City")
//                    .beerStyle(BeerStyle.IPA)
//                    .upc("12356")
//                    .price(new BigDecimal("13.99"))
//                    .quantityOnHand(144)
////                    .createdDate(LocalDateTime.now())
////                    .updateDate(LocalDateTime.now())
//                    .build();
            Beer beer3 = new Beer();
            beer3.setBeerName("Sunshine City");
            beer3.setBeerStyle(BeerStyle.IPA);
            beer3.setUpc("12356");
            beer3.setPrice(new BigDecimal("13.99"));
            beer3.setQuantityOnHand(144);

            beerRepository.save(beer1);
            beerRepository.save(beer2);
            beerRepository.save(beer3);
        }

    }

    private void loadCustomerData() {

        if (customerRepository.count() == 0) {
            Customer customer1 = Customer.builder()
                    .id(UUID.randomUUID())
                    .name("Customer 1")
                    .email("customer1@gmail.com")
//                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Customer customer2 = Customer.builder()
                    .id(UUID.randomUUID())
                    .name("Customer 2")
                    .email("customer2@yahoo.com")
//                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Customer customer3 = Customer.builder()
                    .id(UUID.randomUUID())
                    .name("Customer 3")
                    .email("customer3@microsoft.com")
//                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
        }

    }

    private void loadBeerOrderData() {
        if(beerOrderRepository.count() ==0 ){
            Beer beer1 = new Beer();
            beer1.setBeerName("Super 1000");
            beer1.setBeerStyle(BeerStyle.IPA);
            beer1.setUpc("12356");
            beer1.setPrice(new BigDecimal("11.99"));
            beer1.setQuantityOnHand(190);
            Customer customer1 = Customer.builder()
                    .id(UUID.randomUUID())
                    .name("Customer 4")
                    .email("customer4@deloitte.com")
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            BeerOrderShipment beerOrderShipment = new BeerOrderShipment();
            beerOrderShipment.setTrackingNumber(UUID.randomUUID().toString());
            BeerOrder beerOrder1 = new BeerOrder();
            beerOrder1.setCustomer(customer1);
            beerOrder1.setId(UUID.randomUUID());
            beerOrder1.setBeerOrderShipment(beerOrderShipment);
            beerOrderRepository.save(beerOrder1);
        }
    }


}
