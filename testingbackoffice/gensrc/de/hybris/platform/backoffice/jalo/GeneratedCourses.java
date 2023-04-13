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

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.testingbackoffice.constants.TestingbackofficeConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.backoffice.jalo.Courses Courses}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCourses extends GenericItem
{
	/** Qualifier of the <code>Courses.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>Courses.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>Courses.duration</code> attribute **/
	public static final String DURATION = "duration";
	/** Qualifier of the <code>Courses.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>Courses.amount</code> attribute **/
	public static final String AMOUNT = "amount";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(DURATION, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(AMOUNT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.amount</code> attribute.
	 * @return the amount - Amount of Courses
	 */
	public Integer getAmount(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, AMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.amount</code> attribute.
	 * @return the amount - Amount of Courses
	 */
	public Integer getAmount()
	{
		return getAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.amount</code> attribute. 
	 * @return the amount - Amount of Courses
	 */
	public int getAmountAsPrimitive(final SessionContext ctx)
	{
		Integer value = getAmount( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.amount</code> attribute. 
	 * @return the amount - Amount of Courses
	 */
	public int getAmountAsPrimitive()
	{
		return getAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.amount</code> attribute. 
	 * @param value the amount - Amount of Courses
	 */
	public void setAmount(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, AMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.amount</code> attribute. 
	 * @param value the amount - Amount of Courses
	 */
	public void setAmount(final Integer value)
	{
		setAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.amount</code> attribute. 
	 * @param value the amount - Amount of Courses
	 */
	public void setAmount(final SessionContext ctx, final int value)
	{
		setAmount( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.amount</code> attribute. 
	 * @param value the amount - Amount of Courses
	 */
	public void setAmount(final int value)
	{
		setAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.code</code> attribute.
	 * @return the code - Course Code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.code</code> attribute.
	 * @return the code - Course Code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.code</code> attribute. 
	 * @param value the code - Course Code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.code</code> attribute. 
	 * @param value the code - Course Code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.description</code> attribute.
	 * @return the description - Description of available course
	 */
	public String getDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.description</code> attribute.
	 * @return the description - Description of available course
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.description</code> attribute. 
	 * @param value the description - Description of available course
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.description</code> attribute. 
	 * @param value the description - Description of available course
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.duration</code> attribute.
	 * @return the duration - Course Duration
	 */
	public String getDuration(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DURATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.duration</code> attribute.
	 * @return the duration - Course Duration
	 */
	public String getDuration()
	{
		return getDuration( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.duration</code> attribute. 
	 * @param value the duration - Course Duration
	 */
	public void setDuration(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DURATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.duration</code> attribute. 
	 * @param value the duration - Course Duration
	 */
	public void setDuration(final String value)
	{
		setDuration( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.name</code> attribute.
	 * @return the name - Course Name
	 */
	public String getName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Courses.name</code> attribute.
	 * @return the name - Course Name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.name</code> attribute. 
	 * @param value the name - Course Name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Courses.name</code> attribute. 
	 * @param value the name - Course Name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
}
