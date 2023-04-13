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
package de.hybris.platform.testingservice.setup;

import static de.hybris.platform.testingservice.constants.TestingserviceConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.testingservice.constants.TestingserviceConstants;
import de.hybris.platform.testingservice.service.TestingserviceService;


@SystemSetup(extension = TestingserviceConstants.EXTENSIONNAME)
public class TestingserviceSystemSetup
{
	private final TestingserviceService testingserviceService;

	public TestingserviceSystemSetup(final TestingserviceService testingserviceService)
	{
		this.testingserviceService = testingserviceService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		testingserviceService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return TestingserviceSystemSetup.class.getResourceAsStream("/testingservice/sap-hybris-platform.png");
	}
}
