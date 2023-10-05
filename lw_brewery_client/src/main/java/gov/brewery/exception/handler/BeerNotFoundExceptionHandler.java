package gov.brewery.exception.handler;


import com.deloitte.nextgen.framework.exceptions.handlers.AbstractNotFoundExceptionHandler;

import gov.brewery.exception.BeerNotFoundException;

import org.springframework.stereotype.Component;

/**
 * @author nishmehta on 18/06/2021 1:34 PM
 * @project ng-demo
 */

@Component
public class BeerNotFoundExceptionHandler extends AbstractNotFoundExceptionHandler<BeerNotFoundException> {
	
	@Override
	protected int getMessageCode(BeerNotFoundException ex) {
	    return 404;
	}

    public BeerNotFoundExceptionHandler() {
        super(BeerNotFoundException.class);
    }
}
