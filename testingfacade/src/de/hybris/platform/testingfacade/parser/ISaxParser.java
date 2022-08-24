package de.hybris.platform.testingfacade.parser;

import de.hybris.platform.core.model.product.ProductModel;

import javax.xml.parsers.SAXParser;
import java.nio.file.Path;
import java.util.List;

public interface ISaxParser {

    SAXParser createSaxParser();
    List<ProductModel> parseProducts(Path filePath);

}
