package de.hybris.platform.testingwebservices.v2.controller;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.testingfacade.parser.impl.StaxParser;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/{baseSiteId}/multipart")
@CacheControl(directive = CacheControlDirective.PUBLIC, maxAge = 1800)
@Api(tags = "Training Courses")
public class TestingWebServicesMultipartController extends BaseCommerceController {

    private String json;
    private String code;
    private boolean success;
    private String fileName;
    private StaxParser parser;
    private InputStream inputStream;    private ProductModel productModel;

    private static final Logger LOG = Logger.getLogger(TestingWebServicesMultipartController.class);

    public TestingWebServicesMultipartController() {}

    @RequestMapping(value = "/upload-file", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(nickname = "uploadMultipartFile", value = "Upload multipart file.", notes = "Uploads the file and processes it.")
    @ApiBaseSiteIdParam
    public String sendMultipartFile(@ApiParam(value = "multipart file", required = true) @RequestParam final MultipartFile multipartFile) throws IOException, XMLStreamException {

        if (!multipartFile.isEmpty()) {
            parser = new StaxParser();
            inputStream = multipartFile.getInputStream();
            productModel = parser.xmlEventReader(inputStream);

            success = true;
            code = productModel.getCode();
            fileName = multipartFile.getOriginalFilename();
        } else {
            success = false;
            code = "null";
            fileName = "null";
        }

        json = "{" +
            " success: " + success + "," +
            " fileName: " + fileName + "," +
            " productCode: " + code + " " +
        "}";

        return json;
    }


    @RequestMapping(value = "/upload-multiple-files", method = RequestMethod.POST, consumes = "multipart/form-data", produces = "application/json")
    @ResponseBody
    @ApiOperation(nickname = "uploadMultipleMultipartFiles", value = "Upload many multipart files.", notes = "Uploads selected files and processes them.")
    @ApiBaseSiteIdParam
    public String sendMultipleMultipartFiles(@ApiParam(value = "multipart files", required = true) @RequestParam final MultipartFile[] multipartFiles) throws IOException, XMLStreamException {

        json = "";

        final int length = multipartFiles.length;
        int counter = length;

        for (MultipartFile file : multipartFiles) {
            if (!file.isEmpty()) {
                try {
                    counter--;
                    parser = new StaxParser();
                    inputStream = file.getInputStream();
                    productModel = parser.xmlEventReader(inputStream);

                    success = true;
                    code = productModel.getCode();
                    fileName = file.getOriginalFilename();

                    json = counter > 0 ? json + "{ success: " + success + ", fileName: " + fileName + ", productCode: " + code + " }, " :
                                         json + "{ success: " + success + ", fileName: " + fileName + ", productCode: " + code + " }";

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                success = false;
                fileName = "null";
                code = "null";

                json = "{" +
                    " success: " + success + "," +
                    " fileName: " + fileName + "," +
                    " productCode: " + code + " " +
                "}";
            }
        }

        return json;
    }

//    @RequestMapping(value = "/upload-file", method = RequestMethod.GET, params = "auid")
//    public String uploadFile(@RequestParam String auid, final Model model, HttpSession session) throws CMSItemNotFoundException {
//
//        ApplianceData appliance = ggCustomerFacade.getApplianceByAuid(auid);
//
//        if (appliance.getProductCode() != null) {
//            session.setAttribute("updateApplianceData", appliance);
//            session.setAttribute("registrationType", "updateInvoice");
//
//            ApplianceRegistrationFindForm findForm = new ApplianceRegistrationFindForm();
//            findForm.setAuidNumber(auid);
//            findForm.setArticleNumber(appliance.getArticleNum());
//            findForm.setSerialIndex(appliance.getSerialIndex());
//            findForm.setSerialNumber(appliance.getSerialNum());
//            findForm.setCustomName(appliance.getCustomName());
//            setProductData(findForm, session, model);
//            final ApplianceRegistrationForm appRegisterFrom = (ApplianceRegistrationForm) session.getAttribute("applianceRegistrationModel");
//            appRegisterFrom.setWarrantyMonths(appliance.getWarranty().getWarrantyMonths());
//            appRegisterFrom.setPurchasedAtStore(appliance.getWarranty().getPurchasedAtStore());
//            appRegisterFrom.setPurchasedAtRetailer(appliance.getWarranty().getPurchasedAtRetailer());
//
//            session.setAttribute("applianceRegistrationModel", appRegisterFrom);
//
//            model.addAttribute("appRegistrationOverview", appRegisterFrom);
//
//            ProductData productData = ggCustomerFacade.getProductForAuidAndOptions(findForm.getAuidNumber(),
//                    Arrays.asList(ProductOption.BASIC,
//                            ProductOption.CATEGORIES,
//                            ProductOption.DESCRIPTION,
//                            ProductOption.SUMMARY,
//                            ProductOption.GALLERY));
//            session.setAttribute("applianceProductDataModel", productData);
//
//            getProductData(model, session);
//
//            final ProductConfirmForm confirmForm = (ProductConfirmForm) session.getAttribute("productData");
//            model.addAttribute("productData", confirmForm);
//            setModelAndRedirect(model, "upload-invoice");
//            return getViewForPage(model);
//        } else {
//            return REDIRECT_PREFIX + FIND_YOUR_PRODUCT;
//        }
//    }
//
//    @RequestMapping(value = "/upload-file", method = RequestMethod.GET)
//    public String uploadFile(final Model model, HttpSession session) throws CMSItemNotFoundException {
//        if (session.getAttribute("applianceRegistrationModel") != null) {
//            final ApplianceRegistrationForm appRegisterFrom = (ApplianceRegistrationForm) session.getAttribute("applianceRegistrationModel");
//            model.addAttribute("appRegistrationOverview", appRegisterFrom);
//
//            final ProductConfirmForm confirmForm = (ProductConfirmForm) session.getAttribute("productData");
//            model.addAttribute("productData", confirmForm);
//
//            setModelAndRedirect(model, "upload-invoice");
//
//            return getViewForPage(model);
//        } else {
//            return REDIRECT_PREFIX + FIND_YOUR_PRODUCT;
//        }
//    }
}
