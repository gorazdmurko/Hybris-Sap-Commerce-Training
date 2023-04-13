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
package de.hybris.platform.customExtension.jalo;

import de.hybris.platform.customExtension.constants.CustomExtensionConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.customExtension.jalo.MyProduct MyProduct}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMyProduct extends Product
{
	/** Qualifier of the <code>MyProduct.id</code> attribute **/
	public static final String ID = "id";
	/** Qualifier of the <code>MyProduct.productName</code> attribute **/
	public static final String PRODUCTNAME = "productName";
	/** Qualifier of the <code>MyProduct.inStock</code> attribute **/
	public static final String INSTOCK = "inStock";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Product.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ID, AttributeMode.INITIAL);
		tmp.put(PRODUCTNAME, AttributeMode.INITIAL);
		tmp.put(INSTOCK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.id</code> attribute.
	 * @return the id
	 */
	public Integer getId(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, ID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.id</code> attribute.
	 * @return the id
	 */
	public Integer getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.id</code> attribute. 
	 * @return the id
	 */
	public int getIdAsPrimitive(final SessionContext ctx)
	{
		Integer value = getId( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.id</code> attribute. 
	 * @return the id
	 */
	public int getIdAsPrimitive()
	{
		return getIdAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, ID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final Integer value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final SessionContext ctx, final int value)
	{
		setId( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final int value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.inStock</code> attribute.
	 * @return the inStock - Example Initial Boolean Field
	 */
	public Boolean isInStock(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, INSTOCK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.inStock</code> attribute.
	 * @return the inStock - Example Initial Boolean Field
	 */
	public Boolean isInStock()
	{
		return isInStock( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.inStock</code> attribute. 
	 * @return the inStock - Example Initial Boolean Field
	 */
	public boolean isInStockAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isInStock( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.inStock</code> attribute. 
	 * @return the inStock - Example Initial Boolean Field
	 */
	public boolean isInStockAsPrimitive()
	{
		return isInStockAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.inStock</code> attribute. 
	 * @param value the inStock - Example Initial Boolean Field
	 */
	public void setInStock(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, INSTOCK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.inStock</code> attribute. 
	 * @param value the inStock - Example Initial Boolean Field
	 */
	public void setInStock(final Boolean value)
	{
		setInStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.inStock</code> attribute. 
	 * @param value the inStock - Example Initial Boolean Field
	 */
	public void setInStock(final SessionContext ctx, final boolean value)
	{
		setInStock( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.inStock</code> attribute. 
	 * @param value the inStock - Example Initial Boolean Field
	 */
	public void setInStock(final boolean value)
	{
		setInStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.productName</code> attribute.
	 * @return the productName - My Example Initial String Value
	 */
	public String getProductName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PRODUCTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyProduct.productName</code> attribute.
	 * @return the productName - My Example Initial String Value
	 */
	public String getProductName()
	{
		return getProductName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.productName</code> attribute. 
	 * @param value the productName - My Example Initial String Value
	 */
	public void setProductName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PRODUCTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyProduct.productName</code> attribute. 
	 * @param value the productName - My Example Initial String Value
	 */
	public void setProductName(final String value)
	{
		setProductName( getSession().getSessionContext(), value );
	}
	
}
