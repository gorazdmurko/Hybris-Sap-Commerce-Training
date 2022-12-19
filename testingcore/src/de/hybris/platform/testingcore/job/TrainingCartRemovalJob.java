package de.hybris.platform.testingcore.job;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.testingcore.dao.IFindOldCartsDao;
import de.hybris.platform.testingcore.model.TrainingOldCartRemovalJobModel;
import org.apache.log4j.Logger;

import de.hybris.platform.servicelayer.search.SearchResult;


// passing custom itemtype model
public class TrainingCartRemovalJob extends AbstractJobPerformable<TrainingOldCartRemovalJobModel> {

    // @Resource(name = "findAllCartsDao")
    IFindOldCartsDao findOldCartsDao;

    private static final Logger LOG = Logger.getLogger(TrainingCartRemovalJob.class);

    @Override
    public PerformResult perform(TrainingOldCartRemovalJobModel trainingOldCartRemovalJobModel) {

        final SearchResult<CartModel> searchResult = findOldCartsDao.findOldCarts(trainingOldCartRemovalJobModel.getHours());

        LOG.info("Found " + searchResult.getCount() + " carts in the last " + trainingOldCartRemovalJobModel.getHours() + " hours");
        searchResult.getResult().stream().forEach(this::removeOldCarts);
        LOG.info("Removed " + searchResult.getCount() + " carts");
        LOG.info(searchResult.getCount() > 1 ? "Removed " + searchResult.getCount() + " carts" : "Removed " + searchResult.getCount() + " cart");

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private void removeOldCarts(CartModel cartModel) {
        LOG.info("Removing carts");
        modelService.remove(cartModel);
    }


    // GETTERS & SETTERS
    public IFindOldCartsDao getFindOldCartsDao() {
        return findOldCartsDao;
    }

    public void setFindOldCartsDao(IFindOldCartsDao findOldCartsDao) {
        this.findOldCartsDao = findOldCartsDao;
    }
}
