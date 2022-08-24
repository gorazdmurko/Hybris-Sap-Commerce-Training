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
package de.hybris.platform.testingfacade.setup;

import static de.hybris.platform.testingfacade.constants.TestingfacadeConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.testingfacade.constants.TestingfacadeConstants;
import de.hybris.platform.testingfacade.service.TestingfacadeService;


@SystemSetup(extension = TestingfacadeConstants.EXTENSIONNAME)
public class TestingfacadeSystemSetup
{
	private final TestingfacadeService testingfacadeService;

	public TestingfacadeSystemSetup(final TestingfacadeService testingfacadeService)
	{
		this.testingfacadeService = testingfacadeService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		testingfacadeService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return TestingfacadeSystemSetup.class.getResourceAsStream("/testingfacade/sap-hybris-platform.png");
	}
}
