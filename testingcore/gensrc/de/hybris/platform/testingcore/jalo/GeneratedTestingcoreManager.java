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

import de.hybris.platform.acceleratorcms.jalo.actions.MyNewAction;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.testingcore.constants.TestingcoreConstants;
import de.hybris.platform.testingcore.jalo.ExternalToken;
import de.hybris.platform.testingcore.jalo.SocialSite;
import de.hybris.platform.testingcore.jalo.TrainingOldCartRemovalJob;
import de.hybris.platform.testingcore.jalo.TrainingProduct;
import de.hybris.platform.testingcore.jalo.components.CustomConsentManagementComponent;
import de.hybris.platform.testingcore.jalo.components.CustomParagraphComponent;
import de.hybris.platform.testingcore.jalo.components.TrainingVideoComponent;
import de.hybris.platform.testingcore.jalo.custom.ConsentEmailProcess;
import de.hybris.platform.testingcore.jalo.custom.HybrisTubeEmailProcess;
import de.hybris.platform.testingcore.jalo.custom.TrainingEmailProcess;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>TestingcoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTestingcoreManager extends Extension
{
	/**
	* {@link OneToManyHandler} for handling 1:n EXTERNALTOKEN's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<ExternalToken> CUSTOMER2EXTERNALTOKENEXTERNALTOKENHANDLER = new OneToManyHandler<ExternalToken>(
	TestingcoreConstants.TC.EXTERNALTOKEN,
	true,
	"customer",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n SOCIALSITE's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<SocialSite> CUSTOMER2SOCIALSITESOCIALSITEHANDLER = new OneToManyHandler<SocialSite>(
	TestingcoreConstants.TC.SOCIALSITE,
	true,
	"customer",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
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
	
	public CustomConsentManagementComponent createAnonymousConsentManagementComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.ANONYMOUSCONSENTMANAGEMENTCOMPONENT );
			return (CustomConsentManagementComponent)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating AnonymousConsentManagementComponent : "+e.getMessage(), 0 );
		}
	}
	
	public CustomConsentManagementComponent createAnonymousConsentManagementComponent(final Map attributeValues)
	{
		return createAnonymousConsentManagementComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public ConsentEmailProcess createConsentEmailProcess(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.CONSENTEMAILPROCESS );
			return (ConsentEmailProcess)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating ConsentEmailProcess : "+e.getMessage(), 0 );
		}
	}
	
	public ConsentEmailProcess createConsentEmailProcess(final Map attributeValues)
	{
		return createConsentEmailProcess( getSession().getSessionContext(), attributeValues );
	}
	
	public CustomConsentManagementComponent createCustomConsentManagementComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.CUSTOMCONSENTMANAGEMENTCOMPONENT );
			return (CustomConsentManagementComponent)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating CustomConsentManagementComponent : "+e.getMessage(), 0 );
		}
	}
	
	public CustomConsentManagementComponent createCustomConsentManagementComponent(final Map attributeValues)
	{
		return createCustomConsentManagementComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public CustomParagraphComponent createCustomParagraphComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.CUSTOMPARAGRAPHCOMPONENT );
			return (CustomParagraphComponent)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating CustomParagraphComponent : "+e.getMessage(), 0 );
		}
	}
	
	public CustomParagraphComponent createCustomParagraphComponent(final Map attributeValues)
	{
		return createCustomParagraphComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public ExternalToken createExternalToken(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.EXTERNALTOKEN );
			return (ExternalToken)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating ExternalToken : "+e.getMessage(), 0 );
		}
	}
	
	public ExternalToken createExternalToken(final Map attributeValues)
	{
		return createExternalToken( getSession().getSessionContext(), attributeValues );
	}
	
	public HybrisTubeEmailProcess createHybrisTubeEmailProcess(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.HYBRISTUBEEMAILPROCESS );
			return (HybrisTubeEmailProcess)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating HybrisTubeEmailProcess : "+e.getMessage(), 0 );
		}
	}
	
	public HybrisTubeEmailProcess createHybrisTubeEmailProcess(final Map attributeValues)
	{
		return createHybrisTubeEmailProcess( getSession().getSessionContext(), attributeValues );
	}
	
	public MyNewAction createNewCustomTrainingAction(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.NEWCUSTOMTRAININGACTION );
			return (MyNewAction)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating NewCustomTrainingAction : "+e.getMessage(), 0 );
		}
	}
	
	public MyNewAction createNewCustomTrainingAction(final Map attributeValues)
	{
		return createNewCustomTrainingAction( getSession().getSessionContext(), attributeValues );
	}
	
	public SocialSite createSocialSite(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.SOCIALSITE );
			return (SocialSite)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating SocialSite : "+e.getMessage(), 0 );
		}
	}
	
	public SocialSite createSocialSite(final Map attributeValues)
	{
		return createSocialSite( getSession().getSessionContext(), attributeValues );
	}
	
	public TrainingEmailProcess createTrainingEmailProcess(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.TRAININGEMAILPROCESS );
			return (TrainingEmailProcess)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating TrainingEmailProcess : "+e.getMessage(), 0 );
		}
	}
	
	public TrainingEmailProcess createTrainingEmailProcess(final Map attributeValues)
	{
		return createTrainingEmailProcess( getSession().getSessionContext(), attributeValues );
	}
	
	public TrainingOldCartRemovalJob createTrainingOldCartRemovalJob(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.TRAININGOLDCARTREMOVALJOB );
			return (TrainingOldCartRemovalJob)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating TrainingOldCartRemovalJob : "+e.getMessage(), 0 );
		}
	}
	
	public TrainingOldCartRemovalJob createTrainingOldCartRemovalJob(final Map attributeValues)
	{
		return createTrainingOldCartRemovalJob( getSession().getSessionContext(), attributeValues );
	}
	
	public TrainingProduct createTrainingProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.TRAININGPRODUCT );
			return (TrainingProduct)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating TrainingProduct : "+e.getMessage(), 0 );
		}
	}
	
	public TrainingProduct createTrainingProduct(final Map attributeValues)
	{
		return createTrainingProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public TrainingVideoComponent createTrainingVideoComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TestingcoreConstants.TC.TRAININGVIDEOCOMPONENT );
			return (TrainingVideoComponent)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating TrainingVideoComponent : "+e.getMessage(), 0 );
		}
	}
	
	public TrainingVideoComponent createTrainingVideoComponent(final Map attributeValues)
	{
		return createTrainingVideoComponent( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.externalToken</code> attribute.
	 * @return the externalToken
	 */
	public Collection<ExternalToken> getExternalToken(final SessionContext ctx, final Customer item)
	{
		return CUSTOMER2EXTERNALTOKENEXTERNALTOKENHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.externalToken</code> attribute.
	 * @return the externalToken
	 */
	public Collection<ExternalToken> getExternalToken(final Customer item)
	{
		return getExternalToken( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.externalToken</code> attribute. 
	 * @param value the externalToken
	 */
	public void setExternalToken(final SessionContext ctx, final Customer item, final Collection<ExternalToken> value)
	{
		CUSTOMER2EXTERNALTOKENEXTERNALTOKENHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.externalToken</code> attribute. 
	 * @param value the externalToken
	 */
	public void setExternalToken(final Customer item, final Collection<ExternalToken> value)
	{
		setExternalToken( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to externalToken. 
	 * @param value the item to add to externalToken
	 */
	public void addToExternalToken(final SessionContext ctx, final Customer item, final ExternalToken value)
	{
		CUSTOMER2EXTERNALTOKENEXTERNALTOKENHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to externalToken. 
	 * @param value the item to add to externalToken
	 */
	public void addToExternalToken(final Customer item, final ExternalToken value)
	{
		addToExternalToken( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from externalToken. 
	 * @param value the item to remove from externalToken
	 */
	public void removeFromExternalToken(final SessionContext ctx, final Customer item, final ExternalToken value)
	{
		CUSTOMER2EXTERNALTOKENEXTERNALTOKENHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from externalToken. 
	 * @param value the item to remove from externalToken
	 */
	public void removeFromExternalToken(final Customer item, final ExternalToken value)
	{
		removeFromExternalToken( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return TestingcoreConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.socialSite</code> attribute.
	 * @return the socialSite
	 */
	public Collection<SocialSite> getSocialSite(final SessionContext ctx, final Customer item)
	{
		return CUSTOMER2SOCIALSITESOCIALSITEHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.socialSite</code> attribute.
	 * @return the socialSite
	 */
	public Collection<SocialSite> getSocialSite(final Customer item)
	{
		return getSocialSite( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.socialSite</code> attribute. 
	 * @param value the socialSite
	 */
	public void setSocialSite(final SessionContext ctx, final Customer item, final Collection<SocialSite> value)
	{
		CUSTOMER2SOCIALSITESOCIALSITEHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.socialSite</code> attribute. 
	 * @param value the socialSite
	 */
	public void setSocialSite(final Customer item, final Collection<SocialSite> value)
	{
		setSocialSite( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to socialSite. 
	 * @param value the item to add to socialSite
	 */
	public void addToSocialSite(final SessionContext ctx, final Customer item, final SocialSite value)
	{
		CUSTOMER2SOCIALSITESOCIALSITEHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to socialSite. 
	 * @param value the item to add to socialSite
	 */
	public void addToSocialSite(final Customer item, final SocialSite value)
	{
		addToSocialSite( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from socialSite. 
	 * @param value the item to remove from socialSite
	 */
	public void removeFromSocialSite(final SessionContext ctx, final Customer item, final SocialSite value)
	{
		CUSTOMER2SOCIALSITESOCIALSITEHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from socialSite. 
	 * @param value the item to remove from socialSite
	 */
	public void removeFromSocialSite(final Customer item, final SocialSite value)
	{
		removeFromSocialSite( getSession().getSessionContext(), item, value );
	}
	
}
