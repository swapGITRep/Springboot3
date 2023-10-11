package gov.brewery.entities;

import com.deloitte.nextgen.framework.validator.annotations.*;

public class EmployeeDto {
    @Alpha
    private String firstName;

    @AlphaNumeric
    private String userName;

    @ApplicationNumber
    private String  applicationNumber;

    @CaseNumber
    private String caseNumber;


    @IsDate(format = "mm-dd-yyyy")
    private String date;

    @Numeric
    private String phoneNumber;

    private String mobbString;

}
