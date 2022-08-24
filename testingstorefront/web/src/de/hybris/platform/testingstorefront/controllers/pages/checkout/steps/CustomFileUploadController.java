package de.hybris.platform.testingstorefront.controllers.pages.checkout.steps;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.testingfacade.parser.IStaxParser;
import de.hybris.platform.testingfacade.parser.impl.StaxParser;
import de.hybris.platform.testingstorefront.helpers.FileUploadForm;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@Controller
@RequestMapping("/my-account")
@javax.servlet.annotation.MultipartConfig
public class CustomFileUploadController {

    @Resource(name = "defaultModelService")
    private ModelService modelService;

    private InputStream inputStream;

    // @Resource(name = "externalTokenDataPopulator")
    // private ExternalTokenDataPopulator externalTokenDataPopulator;

    // 1. file upload (1 or many files)
    @RequestMapping(value = "/upload-file/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void uploadFile(@RequestParam(value = "files[]", required = true) MultipartFile[] files,
                              final Model model,
                              HttpSession session,
                              HttpServletRequest request,
                              HttpServletResponse response) {

        boolean success;
        String fileName = "";

        System.out.println("Invoice invoked. Successfully in controller.");
        System.out.println("Context path: " + request.getContextPath());
        System.out.println("Session: " + session.getAttributeNames());

        if (files.length == 0) {
            success = false;
            fileName = "not exist";
        } else {
            success = true;
            fileName = files[0].getOriginalFilename();
        }

        String json = "{\n" + "  success: " + success + ",\n" + "  fileName: " + fileName + "\n}";
        System.out.println(json);

        // *
        for (MultipartFile file : files) {
            try {
                if (!file.isEmpty()) {
                    fileName = file.getOriginalFilename();
                    inputStream = file.getInputStream();

                    System.out.println("STREAM: " + inputStream);
                    System.out.println("FILENAME: " + fileName);

                    IStaxParser parser = new StaxParser();
                    ProductModel productModel = parser.xmlEventReader(inputStream);
                    modelService.save(productModel);

                } else {
                    System.out.println("FILE EMPTY!");
                }
            } catch (IOException | XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }

    // 2. file upload (1 file)
    @RequestMapping(value = "/upload-file/test", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void uploadFile2(@RequestParam(value = "file") MultipartFile file,
                               final Model model,
                               HttpSession session,
                               HttpServletRequest request,
                               HttpServletResponse response) throws MissingServletRequestPartException {

        boolean success;
        String fileName = "";

        System.out.println("Invoice invoked. Successfully in controller.");
        System.out.println("Context path: " + request.getContextPath());
        System.out.println("Session: " + session.getAttributeNames());

        if (!file.isEmpty()) {
            success = true;
            fileName = file.getOriginalFilename();
            System.out.println("Multipart name: " + fileName);
        } else {
            success = false;
            fileName = "not exist";
            System.out.println("Multipart name: " + fileName);
        }

        String json = "{ success: " + success + ",\n" + "  fileName: " + fileName + "\n}";
        System.out.println(json);
    }


    // 3. ~~ WHOLE FORM with helper class
    @RequestMapping(value="/parseXmlFromFile", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void parseFromXml(@ModelAttribute("fileUploadFormContainer") FileUploadForm fileUploadForm,
                             BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) throws IOException, XMLStreamException {

        System.out.println("Successfully in controller");

        if (bindingResult.hasErrors()) {
            System.out.println("Binding result has errors. See debug result for details.");
        } else {
            System.out.println("Binding successful.");
        }

        if (fileUploadForm.getMultipartFile() != null) {
            try {
                // MediaType mediaType = fileUploadForm.getMediaType();
                MultipartFile multipartFile = fileUploadForm.getMultipartFile();
                inputStream = multipartFile.getInputStream();
                String fileName = multipartFile.getOriginalFilename();
                String contentType = multipartFile.getContentType();
                String stream = IOUtils.toString(inputStream, String.valueOf(StandardCharsets.UTF_8));
                StaxParser parser = new StaxParser();

                ProductModel productModel;
                productModel = parser.xmlEventReader(inputStream);

                System.out.println("STREAM: " + stream);
                System.out.println("MULTIPART: " + inputStream);
                System.out.println("FILE NAME: " + fileName);
                System.out.println("CONTENT TYPE: " + contentType);

                System.out.println("CODE: " + productModel.getCode());

                // save to DB
                modelService.save(productModel);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("MULTIPART = null");
        }

    }


    // 4. ~~ w/form or w/jQuery
    @RequestMapping(value = "/parseXmlFile", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void parseXml(@RequestParam(value = "files[]", required = false) MultipartFile[] files) throws IOException {

        System.out.println("Successfully in 2nd controller");
        System.out.println("Array length: " + files.length);

        for (MultipartFile file : files) {
            inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();

            System.out.println("STREAM: " + inputStream);
            System.out.println("NAME: " + fileName);
            System.out.println("TYPE: " + contentType);
        }
    }
}
