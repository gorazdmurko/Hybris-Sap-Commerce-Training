package de.hybris.platform.testingfacade.parser;

import de.hybris.platform.core.model.product.ProductModel;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public interface IStaxParser {

    ProductModel xmlEventReader(InputStream stream) throws IOException, XMLStreamException;

}
