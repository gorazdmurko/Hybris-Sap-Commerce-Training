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
package de.hybris.platform.testingcore.setup;

import static de.hybris.platform.testingcore.constants.TestingcoreConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.testingcore.constants.TestingcoreConstants;
import de.hybris.platform.testingcore.service.TestingcoreService;


@SystemSetup(extension = TestingcoreConstants.EXTENSIONNAME)
public class TestingcoreSystemSetup
{
	private final TestingcoreService testingcoreService;

	public TestingcoreSystemSetup(final TestingcoreService testingcoreService)
	{
		this.testingcoreService = testingcoreService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		testingcoreService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return TestingcoreSystemSetup.class.getResourceAsStream("/testingcore/sap-hybris-platform.png");
	}
}
