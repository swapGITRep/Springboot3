package springboot.brewery.services;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import springboot.brewery.model.BeerCSVRecord;
import springboot.brewery.services.BeerCsvService;
import springboot.brewery.services.BeerCsvServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BeerCsvServiceImplTest {

    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void convertCSV() throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

        List<BeerCSVRecord> recs = beerCsvService.convertCSV(file);

        System.out.println(recs.size());

        assertThat(recs.size()).isGreaterThan(0);
    }
}