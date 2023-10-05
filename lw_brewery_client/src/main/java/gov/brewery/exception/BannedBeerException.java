package gov.brewery.exception;


import com.deloitte.nextgen.framework.commons.exceptions.ApiException;
import com.deloitte.nextgen.framework.commons.exceptions.ResourceNotFoundException;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author nishmehta on 21/06/2021 8:14 PM
 * @project ng-demo
 */

@EqualsAndHashCode(callSuper = true)
@ToString
public class BannedBeerException extends ApiException {

    private static final String MESSAGE = "Banned Beer Exception";

    private final String resourceName;

    private final String resourceId;

    public BannedBeerException(int code, String resourceName, String resourceId) {
        super(code, MESSAGE);
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }

    public BannedBeerException(int code, String resourceName, String resourceId, Throwable t) {
        super(code, MESSAGE, t);
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }

}
