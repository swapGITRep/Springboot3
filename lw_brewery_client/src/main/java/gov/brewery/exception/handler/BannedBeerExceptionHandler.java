package gov.brewery.exception.handler;


import com.deloitte.nextgen.framework.exceptions.handlers.AbstractExceptionHandler;
import com.deloitte.nextgen.framework.exceptions.handlers.AbstractNotFoundExceptionHandler;

import gov.brewery.exception.BeerNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author nishmehta on 18/06/2021 1:34 PM
 * @project ng-demo
 */

@Component
public class BannedBeerExceptionHandler extends AbstractExceptionHandler<BeerNotFoundException> {
	
	@Override
	protected int getMessageCode(BeerNotFoundException ex) {
	    return ex.getCode();
	}

    public BannedBeerExceptionHandler() {
        super(BeerNotFoundException.class);
    }

	@Override
	protected HttpStatus getStatus(BeerNotFoundException ex) {
		return HttpStatus.NOT_ACCEPTABLE;
	}
}
