package de.hybris.platform.testingcore.job;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.testingcore.dao.IProductListResultDao;
import de.hybris.platform.testingcore.dao.ITestingOccApiDao;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class CustomerListJobPerformable extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(CustomerListJobPerformable.class);

    // @Resource(name = "testingWebServicesDao")
    ITestingOccApiDao customerDao;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {

        this.getCustomers();

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    protected void getCustomers() {
        final List<CustomerModel> customerModels = customerDao.getAllCustomers();

        LOG.info("*** Printing customers ***");
        customerModels.stream().forEach(this::printCustomers);
    }

    protected void printCustomers(final CustomerModel model) {
        LOG.info(model.getCustomerID() + ": " + model.getName());
    }

    // GETTERS & SETTERS
    public ITestingOccApiDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(ITestingOccApiDao customerDao) {
        this.customerDao = customerDao;
    }
}
