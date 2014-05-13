package com.yee.webstudy.jersey;

import javax.json.stream.JsonGenerator;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.yee.webstudy.jersey.filter.RequestFilter;

public class Application extends ResourceConfig
{
	public Application()
	{
		// 加载Resource
		packages("com.yee.webstudy.jersey.resources");
		// register(HelloWorldResource.class);
		// register(JsonSupportResource.class);

		// JSON Support
		register(JacksonJsonProvider.class);// For Jackson
		property(JsonGenerator.PRETTY_PRINTING, true);// For JSON-Processing

		// Logging
		register(RequestFilter.class);
	}
}
