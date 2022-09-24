package de.hybris.platform.testingfacade.processes.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.testingcore.model.custom.HybrisTubeEmailProcessModel;
import org.apache.poi.ss.usermodel.Picture;
import org.springframework.beans.factory.annotation.Required;

public class HybrisTubeEmailContext extends AbstractEmailContext<HybrisTubeEmailProcessModel>
{
	// gets title, displayName, email, fromEmail, fromDisplayName, email_language & dateTool properties from parent

	private CustomerData customerData;
	private CustomerModel customerModel;
	private Converter<UserModel, CustomerData> customerConverter;

	@Override
	public void init(final HybrisTubeEmailProcessModel hybrisTubeEmailProcessModel, final EmailPageModel emailPageModel) {
		super.init(hybrisTubeEmailProcessModel, emailPageModel);
		put(EMAIL, getCustomerEmailResolutionService().getEmailForCustomer(getCustomer(hybrisTubeEmailProcessModel)));
		put(DISPLAY_NAME, getCustomer(hybrisTubeEmailProcessModel).getDisplayName());

		customerModel = getCustomer(hybrisTubeEmailProcessModel);
		customerData = getCustomerConverter().convert(customerModel);
		put("customerModel", customerModel);
		put("customerData", customerData);

		ProductModel product = (ProductModel) hybrisTubeEmailProcessModel.getCart().getEntries().get(0).getProduct();
		put("product", product);
		String imageUrl = product.getPicture().getURL();
		put("imageUrl", imageUrl);

		Double price = hybrisTubeEmailProcessModel.getCart().getEntries().get(0).getBasePrice();
		put("price", price);
	}

	@Override
	protected BaseSiteModel getSite(HybrisTubeEmailProcessModel businessProcessModel) {
		return businessProcessModel.getCart().getSite();
	}

	@Override
	protected CustomerModel getCustomer(HybrisTubeEmailProcessModel businessProcessModel) {
		return (CustomerModel) businessProcessModel.getCart().getUser();
	}

	@Override
	protected LanguageModel getEmailLanguage(HybrisTubeEmailProcessModel businessProcessModel) {
		return businessProcessModel.getCart().getUser().getSessionLanguage();
	}

	// GETTERS & SETTERS
	public Converter<UserModel, CustomerData> getCustomerConverter() {
		return customerConverter;
	}

	public void setCustomerConverter(Converter<UserModel, CustomerData> customerConverter) {
		this.customerConverter = customerConverter;
	}

	public CustomerModel getCustomerModel() {
		return customerModel;
	}

	public void setCustomerModel(CustomerModel customerModel) {
		this.customerModel = customerModel;
	}

	public CustomerData getCustomerData() {
		return customerData;
	}

	public void setCustomerData(CustomerData customerData) {
		this.customerData = customerData;
	}
}
