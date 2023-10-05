package gov.brewery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.deloitte.nextgen.framework.automate.annotations.ImportOperations;

import gov.brewery.constants.BreweryApiOperations;

@SpringBootApplication
//@ComponentScan("springboot.brewery.*")
//@EntityScan("springboot.brewery.*")

@ImportOperations(BreweryApiOperations.class)
public class BreweryApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(BreweryApplication.class, args);
    }

}
