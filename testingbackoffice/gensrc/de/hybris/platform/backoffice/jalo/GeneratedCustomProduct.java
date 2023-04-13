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
package de.hybris.platform.backoffice.jalo;

import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.testingbackoffice.constants.TestingbackofficeConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.backoffice.jalo.CustomProduct CustomProduct}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomProduct extends Product
{
	/** Qualifier of the <code>CustomProduct.deadline</code> attribute **/
	public static final String DEADLINE = "deadline";
	/** Qualifier of the <code>CustomProduct.inStock</code> attribute **/
	public static final String INSTOCK = "inStock";
	/** Qualifier of the <code>CustomProduct.media</code> attribute **/
	public static final String MEDIA = "media";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Product.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(DEADLINE, AttributeMode.INITIAL);
		tmp.put(INSTOCK, AttributeMode.INITIAL);
		tmp.put(MEDIA, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomProduct.deadline</code> attribute.
	 * @return the deadline - My Example Initial String Value
	 */
	public String getDeadline(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEADLINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomProduct.deadline</code> attribute.
	 * @return the deadline - My Example Initial String Value
	 */
	public String getDeadline()
	{
		return getDeadline( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomProduct.deadline</code> attribute. 
	 * @param value the deadline - My Example Initial String Value
	 */
	public void setDeadline(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEADLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomProduct.deadline</code> attribute. 
	 * @param value the deadline - My Example Initial String Value
	 */
	public void setDeadline(final String value)
	{
		setDeadline( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomProduct.inStock</code> attribute.
	 * @return the inStock - Example Initial Boolean Field
	 */
	public Boolean isInStock(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, INSTOCK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomProduct.inStock</code> attribute.
	 * @return the inStock - Example Initial Boolean Field
	 */
	public Boolean isInStock()
	{
		return isInStock( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomProduct.inStock</code> attribute. 
	 * @return the inStock - Example Initial Boolean Field
	 */
	public boolean isInStockAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isInStock( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomProduct.inStock</code> attribute. 
	 * @return the inStock - Example Initial Boolean Field
	 */
	public boolean isInStockAsPrimitive()
	{
		return isInStockAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomProduct.inStock</code> attribute. 
	 * @param value the inStock - Example Initial Boolean Field
	 */
	public void setInStock(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, INSTOCK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomProduct.inStock</code> attribute. 
	 * @param value the inStock - Example Initial Boolean Field
	 */
	public void setInStock(final Boolean value)
	{
		setInStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomProduct.inStock</code> attribute. 
	 * @param value the inStock - Example Initial Boolean Field
	 */
	public void setInStock(final SessionContext ctx, final boolean value)
	{
		setInStock( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomProduct.inStock</code> attribute. 
	 * @param value the inStock - Example Initial Boolean Field
	 */
	public void setInStock(final boolean value)
	{
		setInStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomProduct.media</code> attribute.
	 * @return the media
	 */
	public Media getMedia(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, MEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomProduct.media</code> attribute.
	 * @return the media
	 */
	public Media getMedia()
	{
		return getMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomProduct.media</code> attribute. 
	 * @param value the media
	 */
	public void setMedia(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomProduct.media</code> attribute. 
	 * @param value the media
	 */
	public void setMedia(final Media value)
	{
		setMedia( getSession().getSessionContext(), value );
	}
	
}
