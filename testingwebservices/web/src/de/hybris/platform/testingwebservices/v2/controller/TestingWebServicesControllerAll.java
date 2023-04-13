package de.hybris.platform.testingwebservices.v2.controller;

import de.hybris.platform.commercefacades.user.data.CustomerListData;
import de.hybris.platform.testingfacade.facades.impl.TestingOccApiFacade;
import de.hybris.platform.testingwebservices.AllCustomersDataWsDTO;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.logging.Logger;

// url >> /{baseSiteId}
@Controller
@RequestMapping(value = "/{baseSiteId}")
@CacheControl(directive = CacheControlDirective.PUBLIC, maxAge = 1800)
@Api(tags = "Training Courses")
public class TestingWebServicesControllerAll extends BaseCommerceController {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestingWebServicesController.class));

    @Resource(name = "customerDataList")
    private CustomerListData customerDataList;

    @Resource(name = "testingOccApiFacade")
    private TestingOccApiFacade testingOccApiFacade;

    public TestingWebServicesControllerAll() {
    }

    public TestingWebServicesControllerAll(CustomerListData customerDataList, TestingOccApiFacade testingOccApiFacade) {
        this.customerDataList = customerDataList;
        this.testingOccApiFacade = testingOccApiFacade;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ApiOperation(nickname = "getListOfCustomerDetails", value = "List of Customer details.", notes = "Returns details of customer data.")  // ,authorizations = {@Authorization(value = "oauth2_client_credentials")}
    @ApiBaseSiteIdParam
    // must return AllCustomersDataWsDto
    public AllCustomersDataWsDTO getAllCustomers(
            @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) {

        customerDataList = testingOccApiFacade.populateCustomerListData();

        // ORIKA RETURN
        return getDataMapper().map(customerDataList, AllCustomersDataWsDTO.class, fields);
    }

    public CustomerListData getCustomerDataList() {
        return customerDataList;
    }

    public void setCustomerDataList(CustomerListData customerDataList) {
        this.customerDataList = customerDataList;
    }

    public TestingOccApiFacade getTestingOccApiFacade() {
        return testingOccApiFacade;
    }

    public void setTestingOccApiFacade(TestingOccApiFacade testingOccApiFacade) {
        this.testingOccApiFacade = testingOccApiFacade;
    }
}
