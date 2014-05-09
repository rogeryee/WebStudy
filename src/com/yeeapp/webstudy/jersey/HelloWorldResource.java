package com.yeeapp.webstudy.jersey;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
public class HelloWorldResource
{
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello()
	{
		return "Hello World";
	}
	
	@GET
	@Path("/user/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloToYou( @PathParam("name") String name)
	{
		return "Hi " + name + ", Welcome!";
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHiToYouByGetParam(@QueryParam("name") String name)
	{
		return "Hi " + name + ", Welcome! This is using @QueryParam";
	}
}
