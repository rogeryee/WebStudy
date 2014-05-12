package com.yee.webstudy.jersey.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Path("/json")
public class JsonSupportResource
{
	@GET
	@Path("/jaxb")
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonSupportBean getJaxb()
	{
		return new JsonSupportBean("Roger", 33);
	}
}

@XmlRootElement
class JsonSupportBean
{
	@XmlElement
	public String name;
	
	@XmlTransient
	public int age;

	// JAXB needs this default constructor
	public JsonSupportBean()
	{
	}

	public JsonSupportBean(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
}
