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

import java.util.logging.Logger;

import static de.hybris.platform.testingwebservices.v2.controller.BaseController.DEFAULT_FIELD_SET;


// url >> /{baseSiteId}/name/{name}}
@Controller
@RequestMapping(value = "/{baseSiteId}")
@CacheControl(directive = CacheControlDirective.PUBLIC, maxAge = 1800)
@Api(tags = "Training Courses")
public class TestingWebServicesControllerName extends BaseCommerceController {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestingWebServicesController.class));

    private CustomerData customerData;
    private TestingOccApiFacade testingOccApiFacade;
    private final CustomerDataWsDTO customerDataWsDTO = new CustomerDataWsDTO();

    public TestingWebServicesControllerName(CustomerData customerData, TestingOccApiFacade testingOccApiFacade) {
        this.customerData = customerData;
        this.testingOccApiFacade = testingOccApiFacade;
    }

    // @Secured("ROLE_TRUSTED_CLIENT")
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ApiOperation(nickname = "getCustomerDetails", value = "Get a single customer with specified name.", notes = "Returns details of customer data.")  // ,authorizations = {@Authorization(value = "oauth2_client_credentials")}
    @ApiBaseSiteIdParam
    public CustomerDataWsDTO getCustomerData(
            @ApiParam(value = "customer name", required = true) @PathVariable final String name,
            @ApiParam(value = "Response configuration. Returned list of fields in R Body.", allowableValues = "BASIC, DEFAULT, FULL")
            @RequestParam(defaultValue = DEFAULT_FIELD_SET)
            final String fields)
    {

        LOGGER.info("Customer name == " + name);

        customerData = testingOccApiFacade.populateCustomerDataByName(name);

        // customerDataWsDTO.setCustomerId(customerData.getCustomerId());
        // customerDataWsDTO.setCustomerTitle(customerData.getTitle());
        // customerDataWsDTO.setFirstName(customerData.getFirstName());
        // customerDataWsDTO.setLastName(customerData.getLastName());
        // customerDataWsDTO.setExternalTokens(customerData.getExternalTokens());
        // return customerDataWsDTO;

        // ORIKA RETURN
        return getDataMapper().map(customerData, CustomerDataWsDTO.class, fields);
    }
}
