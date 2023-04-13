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
package de.hybris.ruleenginetrail.setup;

import static de.hybris.ruleenginetrail.constants.RuleenginetrailConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.ruleenginetrail.constants.RuleenginetrailConstants;
import de.hybris.ruleenginetrail.service.RuleenginetrailService;


@SystemSetup(extension = RuleenginetrailConstants.EXTENSIONNAME)
public class RuleenginetrailSystemSetup
{
	private final RuleenginetrailService ruleenginetrailService;

	public RuleenginetrailSystemSetup(final RuleenginetrailService ruleenginetrailService)
	{
		this.ruleenginetrailService = ruleenginetrailService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		ruleenginetrailService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return RuleenginetrailSystemSetup.class.getResourceAsStream("/ruleenginetrail/sap-hybris-platform.png");
	}
}
