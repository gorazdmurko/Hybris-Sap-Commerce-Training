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
package de.hybris.personalizationtrail.jalo;

import de.hybris.personalizationtrail.constants.PersonalizationtrailConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.user.UserGroup;
import de.hybris.platform.personalizationservices.jalo.CxAbstractTrigger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.personalizationtrail.jalo.UserGroupTrigger UserGroupTrigger}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedUserGroupTrigger extends CxAbstractTrigger
{
	/** Qualifier of the <code>UserGroupTrigger.userGroup</code> attribute **/
	public static final String USERGROUP = "userGroup";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CxAbstractTrigger.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(USERGROUP, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserGroupTrigger.userGroup</code> attribute.
	 * @return the userGroup - User group
	 */
	public UserGroup getUserGroup(final SessionContext ctx)
	{
		return (UserGroup)getProperty( ctx, USERGROUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserGroupTrigger.userGroup</code> attribute.
	 * @return the userGroup - User group
	 */
	public UserGroup getUserGroup()
	{
		return getUserGroup( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserGroupTrigger.userGroup</code> attribute. 
	 * @param value the userGroup - User group
	 */
	public void setUserGroup(final SessionContext ctx, final UserGroup value)
	{
		setProperty(ctx, USERGROUP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserGroupTrigger.userGroup</code> attribute. 
	 * @param value the userGroup - User group
	 */
	public void setUserGroup(final UserGroup value)
	{
		setUserGroup( getSession().getSessionContext(), value );
	}
	
}
