package springboot.brewery.services;

import java.io.File;
import java.util.List;

import springboot.brewery.model.BeerCSVRecord;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File csvFile);
}
