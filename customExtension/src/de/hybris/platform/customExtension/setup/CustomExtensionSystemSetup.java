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
package de.hybris.platform.customExtension.setup;

import static de.hybris.platform.customExtension.constants.CustomExtensionConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.customExtension.constants.CustomExtensionConstants;
import de.hybris.platform.customExtension.service.CustomExtensionService;


@SystemSetup(extension = CustomExtensionConstants.EXTENSIONNAME)
public class CustomExtensionSystemSetup
{
	private final CustomExtensionService customExtensionService;

	public CustomExtensionSystemSetup(final CustomExtensionService customExtensionService)
	{
		this.customExtensionService = customExtensionService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		customExtensionService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CustomExtensionSystemSetup.class.getResourceAsStream("/customExtension/sap-hybris-platform.png");
	}
}
