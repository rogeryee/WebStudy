package com.yee.webstudy.jersey.resources;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.glassfish.jersey.server.JSONP;

import com.fasterxml.jackson.annotation.JsonProperty;

@Path("/json")
public class JsonSupportResource
{
	@GET
	@Path("/jaxb")
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * This method return a JSON object based on Jaxb
	 * @return
	 */
	public JsonSupportBean byJaxb()
	{
		return new JsonSupportBean("Roger", 33);
	}

	@GET
	@Path("/jackson")
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * This method return a JSON object based on Jackson libraries
	 * @return
	 */
	public JacksonBean byJackson()
	{
		return new JacksonBean("Roger", "Shanghai");
	}

	@GET
	@Path("/jsonp")
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * This method return a JSON object based on JSON Processing
	 * @return
	 */
	public JsonObject byJsonProcessing()
	{
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add("firstName", "Duke")
				.add("lastName", "Java")
				.add("age", 18)
				.add("streetAddress", "100 Internet Dr")
				.add("city", "JavaTown")
				.add("state", "JA")
				.add("postalCode", "12345")
				.add("phoneNumbers",
						Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("type", "mobile")
										.add("number", "111-111-1111"))
								.add(Json.createObjectBuilder()
										.add("type", "home")
										.add("number", "222-222-2222")))
				.build();

		return jsonObject;
	}

	@GET
	@Path("/jsonpadding")
	@JSONP(callback = "eval", queryParam = "jsonpCallback")
	@Produces({ "application/json", "application/javascript" })
	public JsonpBean getSimpleJSONP()
	{
		return new JsonpBean("jsonp");
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

class JacksonBean
{
	@JsonProperty("fullname")
	public String name;

	public String address;

	public JacksonBean(String name, String address)
	{
		this.name = name;
		this.address = address;
	}
}

@XmlRootElement
class JsonpBean
{
	private String value;

	public JsonpBean()
	{
	}

	public JsonpBean(final String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(final String value)
	{
		this.value = value;
	}
}
