/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.personalizationtrail.setup;

import static de.hybris.personalizationtrail.constants.PersonalizationtrailConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.personalizationtrail.constants.PersonalizationtrailConstants;
import de.hybris.personalizationtrail.service.PersonalizationtrailService;


@SystemSetup(extension = PersonalizationtrailConstants.EXTENSIONNAME)
public class PersonalizationtrailSystemSetup
{
	private final PersonalizationtrailService personalizationtrailService;

	public PersonalizationtrailSystemSetup(final PersonalizationtrailService personalizationtrailService)
	{
		this.personalizationtrailService = personalizationtrailService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		personalizationtrailService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return PersonalizationtrailSystemSetup.class.getResourceAsStream("/personalizationtrail/sap-hybris-platform.png");
	}
}
