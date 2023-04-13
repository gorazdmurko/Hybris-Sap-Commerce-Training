package de.hybris.platform.testingcore.job;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.testingcore.dao.IProductListResultDao;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

public class ProductListJobPerformable extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(CustomerListJobPerformable.class);

    // @Resource(name = "productListDao")
    IProductListResultDao productDao;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {

        this.getProducts();

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    protected void getProducts() {
        final SearchResult<ProductModel> searchResult = productDao.findProductsByFirstLetter();

        LOG.info("*** Printing products starting with i ***");
        searchResult.getResult().stream().forEach(this::printProducts);
    }

    protected void printProducts(final ProductModel model) {
        LOG.info(model.getCode() + ": " + model.getName());
    }


    // GETTERS & SETTERS
    public IProductListResultDao getProductDao() {
        return productDao;
    }

    public void setProductDao(IProductListResultDao productDao) {
        this.productDao = productDao;
    }
}
