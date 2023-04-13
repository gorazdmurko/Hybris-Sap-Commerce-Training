package de.hybris.platform.testingwebservices.v2.controller;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.testingfacade.facades.impl.TestingOccApiFacade;
import de.hybris.platform.testingwebservices.CustomerDataWsDTO;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.logging.Logger;


// url >> /{baseSiteId}{customerId}}
@Controller
@RequestMapping(value = "/{baseSiteId}")
@CacheControl(directive = CacheControlDirective.PUBLIC, maxAge = 1800)
@Api(tags = "Training Courses")
public class TestingWebServicesController extends BaseCommerceController {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestingWebServicesController.class));

    @Resource(name = "customerData")
    private CustomerData customerData;

    @Resource(name = "testingOccApiFacade")
    private TestingOccApiFacade testingOccApiFacade;

    // private final CustomerDataWsDTO customerDataWsDTO = new CustomerDataWsDTO(); ??

    // CONSTRUCTOR
    public TestingWebServicesController(CustomerData customerData, TestingOccApiFacade testingOccApiFacade) {
        this.customerData = customerData;
        this.testingOccApiFacade = testingOccApiFacade;
    }

    // @Secured("ROLE_TRUSTED_CLIENT")
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ApiOperation(nickname = "getCustomerDetails", value = "Get Customer Details by ID.", notes = "Returns details of customer data.")  // ,authorizations = {@Authorization(value = "oauth2_client_credentials")}
    @ApiBaseSiteIdParam
    public CustomerDataWsDTO getCustomerData(
            @ApiParam(value = "customer id", required = true) @PathVariable final String customerId,
            @ApiParam(value = "Response configuration. Returned list of fields in R Body.", allowableValues = "BASIC, DEFAULT, FULL")
            @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) {

        LOGGER.info("Customer id == " + customerId);

        customerData = testingOccApiFacade.populateCustomerData(customerId);

        // ORIKA RETURN
        return getDataMapper().map(customerData, CustomerDataWsDTO.class, fields);
    }
}


/*

    url: https://localhost:9002/testingwebservices/v2/swagger-ui.html#/
    url: https://localhost:9002/testingwebservices/v2/electronics/123?fields=DEFAULT

    SWAGGER:
    username: admin
    password: nimda
    clientId: oauth_client
    secret: secret

* */