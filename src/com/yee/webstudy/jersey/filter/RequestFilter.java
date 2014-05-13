package com.yee.webstudy.jersey.filter;

import java.io.IOException;
import java.util.Calendar;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class RequestFilter implements ContainerResponseFilter,
		ContainerRequestFilter
{
	private static ThreadLocal<Long> EXECUTION_TIME = new ThreadLocal<Long>();

	public void filter(ContainerRequestContext requestContext)
			throws IOException
	{
		EXECUTION_TIME.set(Calendar.getInstance().getTimeInMillis());
		System.out.println("Request which is started at "
				+ EXECUTION_TIME.get().longValue());
	}

	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("User:")
				.append((requestContext.getSecurityContext().getUserPrincipal() == null ? "unknown"
						: requestContext.getSecurityContext()
								.getUserPrincipal().getName()));
		sb.append(" - Path:").append(requestContext.getUriInfo().getPath());
		sb.append(" - ").append(
				Calendar.getInstance().getTimeInMillis()
						- EXECUTION_TIME.get().longValue());
		System.out.println(sb.toString());
	}
}
