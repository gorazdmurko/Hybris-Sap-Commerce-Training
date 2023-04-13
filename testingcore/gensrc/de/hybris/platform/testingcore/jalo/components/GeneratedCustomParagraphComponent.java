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
package de.hybris.platform.testingcore.jalo.components;

import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.testingcore.constants.TestingcoreConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.testingcore.jalo.components.CustomParagraphComponent CustomParagraphComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomParagraphComponent extends CMSParagraphComponent
{
	/** Qualifier of the <code>CustomParagraphComponent.headerSection</code> attribute **/
	public static final String HEADERSECTION = "headerSection";
	/** Qualifier of the <code>CustomParagraphComponent.footerSection</code> attribute **/
	public static final String FOOTERSECTION = "footerSection";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADERSECTION, AttributeMode.INITIAL);
		tmp.put(FOOTERSECTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomParagraphComponent.footerSection</code> attribute.
	 * @return the footerSection
	 */
	public String getFooterSection(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomParagraphComponent.getFooterSection requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, FOOTERSECTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomParagraphComponent.footerSection</code> attribute.
	 * @return the footerSection
	 */
	public String getFooterSection()
	{
		return getFooterSection( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomParagraphComponent.footerSection</code> attribute. 
	 * @return the localized footerSection
	 */
	public Map<Language,String> getAllFooterSection(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,FOOTERSECTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomParagraphComponent.footerSection</code> attribute. 
	 * @return the localized footerSection
	 */
	public Map<Language,String> getAllFooterSection()
	{
		return getAllFooterSection( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomParagraphComponent.footerSection</code> attribute. 
	 * @param value the footerSection
	 */
	public void setFooterSection(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomParagraphComponent.setFooterSection requires a session language", 0 );
		}
		setLocalizedProperty(ctx, FOOTERSECTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomParagraphComponent.footerSection</code> attribute. 
	 * @param value the footerSection
	 */
	public void setFooterSection(final String value)
	{
		setFooterSection( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomParagraphComponent.footerSection</code> attribute. 
	 * @param value the footerSection
	 */
	public void setAllFooterSection(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,FOOTERSECTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomParagraphComponent.footerSection</code> attribute. 
	 * @param value the footerSection
	 */
	public void setAllFooterSection(final Map<Language,String> value)
	{
		setAllFooterSection( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomParagraphComponent.headerSection</code> attribute.
	 * @return the headerSection
	 */
	public String getHeaderSection(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomParagraphComponent.getHeaderSection requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADERSECTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomParagraphComponent.headerSection</code> attribute.
	 * @return the headerSection
	 */
	public String getHeaderSection()
	{
		return getHeaderSection( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomParagraphComponent.headerSection</code> attribute. 
	 * @return the localized headerSection
	 */
	public Map<Language,String> getAllHeaderSection(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADERSECTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomParagraphComponent.headerSection</code> attribute. 
	 * @return the localized headerSection
	 */
	public Map<Language,String> getAllHeaderSection()
	{
		return getAllHeaderSection( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomParagraphComponent.headerSection</code> attribute. 
	 * @param value the headerSection
	 */
	public void setHeaderSection(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomParagraphComponent.setHeaderSection requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADERSECTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomParagraphComponent.headerSection</code> attribute. 
	 * @param value the headerSection
	 */
	public void setHeaderSection(final String value)
	{
		setHeaderSection( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomParagraphComponent.headerSection</code> attribute. 
	 * @param value the headerSection
	 */
	public void setAllHeaderSection(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADERSECTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomParagraphComponent.headerSection</code> attribute. 
	 * @param value the headerSection
	 */
	public void setAllHeaderSection(final Map<Language,String> value)
	{
		setAllHeaderSection( getSession().getSessionContext(), value );
	}
	
}
