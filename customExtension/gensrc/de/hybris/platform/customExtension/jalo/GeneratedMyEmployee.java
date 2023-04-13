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
import de.hybris.platform.customExtension.jalo.Person;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.customExtension.jalo.MyEmployee MyEmployee}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMyEmployee extends Person
{
	/** Qualifier of the <code>MyEmployee.department</code> attribute **/
	public static final String DEPARTMENT = "department";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Person.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(DEPARTMENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyEmployee.department</code> attribute.
	 * @return the department
	 */
	public String getDepartment(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEPARTMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MyEmployee.department</code> attribute.
	 * @return the department
	 */
	public String getDepartment()
	{
		return getDepartment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyEmployee.department</code> attribute. 
	 * @param value the department
	 */
	public void setDepartment(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEPARTMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MyEmployee.department</code> attribute. 
	 * @param value the department
	 */
	public void setDepartment(final String value)
	{
		setDepartment( getSession().getSessionContext(), value );
	}
	
}
