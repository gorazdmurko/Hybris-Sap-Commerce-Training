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
package de.hybris.platform.testingcore.jalo;

import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.testingcore.constants.TestingcoreConstants;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.product.Product TrainingProduct}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTrainingProduct extends Product
{
	/** Qualifier of the <code>TrainingProduct.trainingName</code> attribute **/
	public static final String TRAININGNAME = "trainingName";
	/** Qualifier of the <code>TrainingProduct.trainingDescription</code> attribute **/
	public static final String TRAININGDESCRIPTION = "trainingDescription";
	/** Qualifier of the <code>TrainingProduct.trainingDate</code> attribute **/
	public static final String TRAININGDATE = "trainingDate";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Product.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TRAININGNAME, AttributeMode.INITIAL);
		tmp.put(TRAININGDESCRIPTION, AttributeMode.INITIAL);
		tmp.put(TRAININGDATE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TrainingProduct.trainingDate</code> attribute.
	 * @return the trainingDate
	 */
	public Date getTrainingDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, TRAININGDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TrainingProduct.trainingDate</code> attribute.
	 * @return the trainingDate
	 */
	public Date getTrainingDate()
	{
		return getTrainingDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TrainingProduct.trainingDate</code> attribute. 
	 * @param value the trainingDate
	 */
	public void setTrainingDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, TRAININGDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TrainingProduct.trainingDate</code> attribute. 
	 * @param value the trainingDate
	 */
	public void setTrainingDate(final Date value)
	{
		setTrainingDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TrainingProduct.trainingDescription</code> attribute.
	 * @return the trainingDescription
	 */
	public String getTrainingDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TRAININGDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TrainingProduct.trainingDescription</code> attribute.
	 * @return the trainingDescription
	 */
	public String getTrainingDescription()
	{
		return getTrainingDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TrainingProduct.trainingDescription</code> attribute. 
	 * @param value the trainingDescription
	 */
	public void setTrainingDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TRAININGDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TrainingProduct.trainingDescription</code> attribute. 
	 * @param value the trainingDescription
	 */
	public void setTrainingDescription(final String value)
	{
		setTrainingDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TrainingProduct.trainingName</code> attribute.
	 * @return the trainingName
	 */
	public String getTrainingName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TRAININGNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TrainingProduct.trainingName</code> attribute.
	 * @return the trainingName
	 */
	public String getTrainingName()
	{
		return getTrainingName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TrainingProduct.trainingName</code> attribute. 
	 * @param value the trainingName
	 */
	public void setTrainingName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TRAININGNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TrainingProduct.trainingName</code> attribute. 
	 * @param value the trainingName
	 */
	public void setTrainingName(final String value)
	{
		setTrainingName( getSession().getSessionContext(), value );
	}
	
}
