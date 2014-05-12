package com.yee.webstudy.jersey.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Path("/xml")
public class XmlSupportResource
{
	@GET
	@Path("/jaxb")
	@Produces(MediaType.APPLICATION_XML)
	public XmlSupportBean getJaxb()
	{
		return new XmlSupportBean("Roger", 33);
	}
}

@XmlRootElement
class XmlSupportBean
{
	@XmlElement(name = "fullName")
	public String name;

	@XmlTransient
	public int age;

	// JAXB needs this default constructor
	public XmlSupportBean()
	{
	}

	public XmlSupportBean(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
}
