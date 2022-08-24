/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.testingstorefront.filters;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;


public class FileUploadFilter extends OncePerRequestFilter
{
	private Map<String, MultipartFilter> urlFilterMapping;
	private PathMatcher pathMatcher;

	public FileUploadFilter() {
	}

	@Override
	public void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws IOException, ServletException
	{
		if (HttpMethod.POST.name().equalsIgnoreCase(request.getMethod()))
		{
			final MultipartFilter multipartFilter = getMultipartFilter(request.getServletPath());
			if (multipartFilter != null)
			{
				multipartFilter.doFilter(request, response, filterChain);
			}
			else
			{
				filterChain.doFilter(request, response);
			}
		}
		else
		{
			filterChain.doFilter(request, response);
		}
	}

	// ?????
//	@Bean(name = "multipartResolver")
//	protected CommonsMultipartResolver getMultipartResolver() {
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		multipartResolver.setMaxUploadSize(-1);
//		return multipartResolver;
//	}

	protected MultipartFilter getMultipartFilter(final String servletPath)
	{
		Iterator var3 = this.getUrlFilterMapping().entrySet().iterator();

		while (var3.hasNext()) {
			Map.Entry<String, MultipartFilter> urlFilterMapping = (Map.Entry) var3.next();
			if (this.getPathMatcher().match((String) urlFilterMapping.getKey(), servletPath)) {
				return (MultipartFilter) urlFilterMapping.getValue();
			}
		}

//		for (Map.Entry<String, MultipartFilter> urlFilterMapping : getUrlFilterMapping().entrySet())
//		{
//			if (getPathMatcher().match(urlFilterMapping.getKey(), servletPath))
//			{
//				return urlFilterMapping.getValue();
//			}
//		}

		return null;
	}

	protected Map<String, MultipartFilter> getUrlFilterMapping()
	{
		return urlFilterMapping;
	}

	@Required
	public void setUrlFilterMapping(final Map<String, MultipartFilter> urlFilterMapping)
	{
		this.urlFilterMapping = urlFilterMapping;
	}

	protected PathMatcher getPathMatcher()
	{
		return pathMatcher;
	}

	@Required
	public void setPathMatcher(final PathMatcher pathMatcher)
	{
		this.pathMatcher = pathMatcher;
	}
}
