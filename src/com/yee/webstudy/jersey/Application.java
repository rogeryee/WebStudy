package com.yee.webstudy.jersey;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class Application extends ResourceConfig
{
	public Application()
	{
		// 加载Resource
		packages("com.yee.webstudy.jersey.resources");
		// register(HelloWorldResource.class);
		// register(JsonSupportResource.class);

		// JSON Support
		register(JacksonJsonProvider.class);

		// Logging
		register(LoggingFilter.class);
	}
}
