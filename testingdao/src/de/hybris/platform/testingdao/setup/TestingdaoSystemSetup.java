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
package de.hybris.platform.testingdao.setup;

import static de.hybris.platform.testingdao.constants.TestingdaoConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.testingdao.constants.TestingdaoConstants;
import de.hybris.platform.testingdao.service.TestingdaoService;


@SystemSetup(extension = TestingdaoConstants.EXTENSIONNAME)
public class TestingdaoSystemSetup
{
	private final TestingdaoService testingdaoService;

	public TestingdaoSystemSetup(final TestingdaoService testingdaoService)
	{
		this.testingdaoService = testingdaoService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		testingdaoService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return TestingdaoSystemSetup.class.getResourceAsStream("/testingdao/sap-hybris-platform.png");
	}
}
