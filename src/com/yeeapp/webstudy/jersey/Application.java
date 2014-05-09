package com.yeeapp.webstudy.jersey;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class Application extends ResourceConfig
{
	public Application()
	{
		// 加载Resource
		register(HelloWorldResource.class);
		
		// JSON Support
		register(JacksonJsonProvider.class);

		// Logging
		register(LoggingFilter.class);
	}
}
