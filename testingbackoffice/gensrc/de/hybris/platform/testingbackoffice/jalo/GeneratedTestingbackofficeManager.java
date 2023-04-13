/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13.4.2023 10:49:30                          ---
 * ----------------------------------------------------------------
 *  
 * [y] hybris Platform
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.testingbackoffice.jalo;

import de.hybris.platform.backoffice.jalo.Courses;
import de.hybris.platform.backoffice.jalo.CustomProduct;
import de.hybris.platform.backoffice.jalo.CustomTrainingTree;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.testingbackoffice.constants.TestingbackofficeConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>TestingbackofficeManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTestingbackofficeManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("productCustomerFeedback", AttributeMode.INITIAL);
		tmp.put("productPackagingQuality", AttributeMode.INITIAL);
		tmp.put("productContents", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.product.Product", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public Courses createCourses(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingbackofficeConstants.TC.COURSES );
			return (Courses)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Courses : "+e.getMessage(), 0 );
		}
	}
	
	public Courses createCourses(final Map attributeValues)
	{
		return createCourses( getSession().getSessionContext(), attributeValues );
	}
	
	public CustomProduct createCustomProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingbackofficeConstants.TC.CUSTOMPRODUCT );
			return (CustomProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CustomProduct : "+e.getMessage(), 0 );
		}
	}
	
	public CustomProduct createCustomProduct(final Map attributeValues)
	{
		return createCustomProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public CustomTrainingTree createCustomTrainingTree(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingbackofficeConstants.TC.CUSTOMTRAININGTREE );
			return (CustomTrainingTree)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CustomTrainingTree : "+e.getMessage(), 0 );
		}
	}
	
	public CustomTrainingTree createCustomTrainingTree(final Map attributeValues)
	{
		return createCustomTrainingTree( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return TestingbackofficeConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productContents</code> attribute.
	 * @return the productContents - Content
	 */
	public String getProductContents(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, TestingbackofficeConstants.Attributes.Product.PRODUCTCONTENTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productContents</code> attribute.
	 * @return the productContents - Content
	 */
	public String getProductContents(final Product item)
	{
		return getProductContents( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productContents</code> attribute. 
	 * @param value the productContents - Content
	 */
	public void setProductContents(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, TestingbackofficeConstants.Attributes.Product.PRODUCTCONTENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productContents</code> attribute. 
	 * @param value the productContents - Content
	 */
	public void setProductContents(final Product item, final String value)
	{
		setProductContents( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productCustomerFeedback</code> attribute.
	 * @return the productCustomerFeedback - Feedback
	 */
	public String getProductCustomerFeedback(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, TestingbackofficeConstants.Attributes.Product.PRODUCTCUSTOMERFEEDBACK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productCustomerFeedback</code> attribute.
	 * @return the productCustomerFeedback - Feedback
	 */
	public String getProductCustomerFeedback(final Product item)
	{
		return getProductCustomerFeedback( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productCustomerFeedback</code> attribute. 
	 * @param value the productCustomerFeedback - Feedback
	 */
	public void setProductCustomerFeedback(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, TestingbackofficeConstants.Attributes.Product.PRODUCTCUSTOMERFEEDBACK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productCustomerFeedback</code> attribute. 
	 * @param value the productCustomerFeedback - Feedback
	 */
	public void setProductCustomerFeedback(final Product item, final String value)
	{
		setProductCustomerFeedback( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productPackagingQuality</code> attribute.
	 * @return the productPackagingQuality - Quality
	 */
	public String getProductPackagingQuality(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, TestingbackofficeConstants.Attributes.Product.PRODUCTPACKAGINGQUALITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productPackagingQuality</code> attribute.
	 * @return the productPackagingQuality - Quality
	 */
	public String getProductPackagingQuality(final Product item)
	{
		return getProductPackagingQuality( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productPackagingQuality</code> attribute. 
	 * @param value the productPackagingQuality - Quality
	 */
	public void setProductPackagingQuality(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, TestingbackofficeConstants.Attributes.Product.PRODUCTPACKAGINGQUALITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productPackagingQuality</code> attribute. 
	 * @param value the productPackagingQuality - Quality
	 */
	public void setProductPackagingQuality(final Product item, final String value)
	{
		setProductPackagingQuality( getSession().getSessionContext(), item, value );
	}
	
}
