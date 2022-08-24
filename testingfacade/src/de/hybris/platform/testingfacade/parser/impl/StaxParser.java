package de.hybris.platform.testingfacade.parser.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.testingfacade.parser.IStaxParser;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class StaxParser implements IStaxParser {

    ProductModel productModel = new ProductModel();

    public ProductModel xmlEventReader(InputStream stream) throws IOException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(stream);

        while (reader.hasNext()) {

            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                StartElement element = event.asStartElement();
                switch (element.getName().getLocalPart()) {
                    case "product":
                        Attribute id = element.getAttributeByName(new QName("id"));
                        System.out.printf("Product id : %s%n", id.getValue());
                        break;
                    case "code":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            String code = event.asCharacters().getData();
                            code = String.valueOf(code);
                            System.out.printf("Code : %s%n", event.asCharacters().getData());
                            productModel.setCode(code);
                            System.out.println("PRODUCT MODEL CODE: " + productModel.getCode());
                        }
                        break;
                }
            }
        }
        return productModel;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }
}
