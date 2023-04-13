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
package de.hybris.platform.testingcore.jalo.custom;

import de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.testingcore.constants.TestingcoreConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.testingcore.jalo.custom.ConsentEmailProcess ConsentEmailProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedConsentEmailProcess extends StoreFrontCustomerProcess
{
	/** Qualifier of the <code>ConsentEmailProcess.token</code> attribute **/
	public static final String TOKEN = "token";
	/** Qualifier of the <code>ConsentEmailProcess.consentConfirmationURL</code> attribute **/
	public static final String CONSENTCONFIRMATIONURL = "consentConfirmationURL";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontCustomerProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TOKEN, AttributeMode.INITIAL);
		tmp.put(CONSENTCONFIRMATIONURL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsentEmailProcess.consentConfirmationURL</code> attribute.
	 * @return the consentConfirmationURL - Holds URL address for consent confirmation
	 */
	public String getConsentConfirmationURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CONSENTCONFIRMATIONURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsentEmailProcess.consentConfirmationURL</code> attribute.
	 * @return the consentConfirmationURL - Holds URL address for consent confirmation
	 */
	public String getConsentConfirmationURL()
	{
		return getConsentConfirmationURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsentEmailProcess.consentConfirmationURL</code> attribute. 
	 * @param value the consentConfirmationURL - Holds URL address for consent confirmation
	 */
	public void setConsentConfirmationURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CONSENTCONFIRMATIONURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsentEmailProcess.consentConfirmationURL</code> attribute. 
	 * @param value the consentConfirmationURL - Holds URL address for consent confirmation
	 */
	public void setConsentConfirmationURL(final String value)
	{
		setConsentConfirmationURL( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsentEmailProcess.token</code> attribute.
	 * @return the token - The confirmation token.
	 */
	public String getToken(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TOKEN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConsentEmailProcess.token</code> attribute.
	 * @return the token - The confirmation token.
	 */
	public String getToken()
	{
		return getToken( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsentEmailProcess.token</code> attribute. 
	 * @param value the token - The confirmation token.
	 */
	public void setToken(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TOKEN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConsentEmailProcess.token</code> attribute. 
	 * @param value the token - The confirmation token.
	 */
	public void setToken(final String value)
	{
		setToken( getSession().getSessionContext(), value );
	}
	
}
