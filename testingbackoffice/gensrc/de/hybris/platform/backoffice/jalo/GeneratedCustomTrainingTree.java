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
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.testingbackoffice.constants.TestingbackofficeConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.backoffice.jalo.CustomTrainingTree CustomTrainingTree}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomTrainingTree extends GenericItem
{
	/** Qualifier of the <code>CustomTrainingTree.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>CustomTrainingTree.subject</code> attribute **/
	public static final String SUBJECT = "subject";
	/** Qualifier of the <code>CustomTrainingTree.attachment</code> attribute **/
	public static final String ATTACHMENT = "attachment";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(SUBJECT, AttributeMode.INITIAL);
		tmp.put(ATTACHMENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomTrainingTree.attachment</code> attribute.
	 * @return the attachment
	 */
	public Media getAttachment(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, ATTACHMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomTrainingTree.attachment</code> attribute.
	 * @return the attachment
	 */
	public Media getAttachment()
	{
		return getAttachment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomTrainingTree.attachment</code> attribute. 
	 * @param value the attachment
	 */
	public void setAttachment(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, ATTACHMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomTrainingTree.attachment</code> attribute. 
	 * @param value the attachment
	 */
	public void setAttachment(final Media value)
	{
		setAttachment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomTrainingTree.code</code> attribute.
	 * @return the code - Custom training tree code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomTrainingTree.code</code> attribute.
	 * @return the code - Custom training tree code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomTrainingTree.code</code> attribute. 
	 * @param value the code - Custom training tree code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomTrainingTree.code</code> attribute. 
	 * @param value the code - Custom training tree code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomTrainingTree.subject</code> attribute.
	 * @return the subject - Custom training tree subject
	 */
	public String getSubject(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SUBJECT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomTrainingTree.subject</code> attribute.
	 * @return the subject - Custom training tree subject
	 */
	public String getSubject()
	{
		return getSubject( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomTrainingTree.subject</code> attribute. 
	 * @param value the subject - Custom training tree subject
	 */
	public void setSubject(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SUBJECT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomTrainingTree.subject</code> attribute. 
	 * @param value the subject - Custom training tree subject
	 */
	public void setSubject(final String value)
	{
		setSubject( getSession().getSessionContext(), value );
	}
	
}
