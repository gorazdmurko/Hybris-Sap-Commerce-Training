package de.hybris.platform.testingcore.service.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.testingcore.dao.ITestingOccApiDao;
import de.hybris.platform.testingcore.dao.impl.TestingOccApiDaoImpl;
import de.hybris.platform.testingcore.service.ITestingOccApiService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.logging.Logger;

public class TestingOccApiService implements ITestingOccApiService {

    private ITestingOccApiDao testingOccApiDao;

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestingOccApiService.class));

    @Override
    public CustomerModel findCustomerById(String customerId) {

        final CustomerModel model = testingOccApiDao.getCustomerById(customerId);

        return model;
    }

    @Override
    public CustomerModel findCustomerByName(String name) {

        final CustomerModel model = testingOccApiDao.getCustomerByName(name);

        return model;
    }

    @Override
    public List<CustomerModel> findAllCustomers() {

        final List<CustomerModel> modelList = testingOccApiDao.getAllCustomers();

        return modelList;
    }

    @Override
    public List<CustomerModel> findAllCustomersByName(String name) {

        final List<CustomerModel> modelList = testingOccApiDao.getAllCustomersWithName(name);

        modelList.forEach( model -> LOGGER.info((String) getRecordToPrint(model)));

        return modelList;
    }

    // setters & getters
    private Object getRecordToPrint(final CustomerModel customerModel) {

        return customerModel.getCustomerID() + "--" + customerModel.getName() + "--" + customerModel.getOriginalUid();
    }

    // just fo TEST, just for FUN ;-)
    private void checkContextTEST() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testingcore-spring.xml");
        TestingOccApiDaoImpl dao = (TestingOccApiDaoImpl)applicationContext.getBean("testingWebServicesDao");
        System.out.println(dao);
    }


    // NEED TO IMPLEMENT GETTERS & SETTERS (if @Resource annotation is not used)
    public ITestingOccApiDao getTestingOccApiDao() {
        return testingOccApiDao;
    }

    @Required
    public void setTestingOccApiDao(ITestingOccApiDao testingOccApiDao) {
        this.testingOccApiDao = testingOccApiDao;
    }
}
