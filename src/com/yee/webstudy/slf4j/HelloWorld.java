package com.yee.webstudy.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Logger logger = LoggerFactory.getLogger("com.yee.webstudy.slf4j.HelloWorld");
	    logger.debug("Hello World");
	}

}
