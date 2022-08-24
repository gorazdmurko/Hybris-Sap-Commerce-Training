package de.hybris.platform.testingwebservices.v2.controller;

import de.hybris.platform.commercefacades.user.data.CustomerListData;
import de.hybris.platform.testingfacade.facades.ITestingOccApiFacade;
import de.hybris.platform.testingwebservices.AllCustomersDataWsDTO;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/{baseSiteId}")
@Api(tags = "Training Courses")
public class TestingWebServicesControllerAllByName extends BaseCommerceController {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestingWebServicesControllerAllByName.class));

    @Resource(name = "testingOccApiFacade")
    private ITestingOccApiFacade testingOccApiFacade;


    @Secured("ROLE_TRUSTED_CLIENT")
    @RequestMapping(value = "/list/{customerName}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseBody
    @ApiOperation(nickname = "getCustomerList", value = "Get list of all customers by same names",
                    notes = "Return a list of customers with same names",
                    authorizations = { @Authorization(value = "oauth2_client_credentials" )})
    @ApiBaseSiteIdParam
    public AllCustomersDataWsDTO getCustomersList(
            @ApiParam(value = "customer name", required = true) @PathVariable final String customerName,
            @ApiParam(value = "Response configuration. This is the list of customers", allowableValues = "BASIC, DEFAULT, FULL")
            @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) {

        LOGGER.info("Customer name == " + customerName);

        final CustomerListData customerListData = new CustomerListData();
        customerListData.setCustomerList(testingOccApiFacade.populateCustomerListByName(customerName));

        return getDataMapper().map(customerListData, AllCustomersDataWsDTO.class, fields);
    }
}
