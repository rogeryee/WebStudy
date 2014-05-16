package com.yee.webstudy.shiro.authentication;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealm3 implements Realm
{
	private Logger logger = LoggerFactory.getLogger(MyRealm3.class);
	
	public String getName()
	{
		return "MyRealm3";
	}

	public boolean supports(AuthenticationToken token)
	{
		return token instanceof UsernamePasswordToken; // 仅支持UsernamePasswordToken类型的Token
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		logger.debug("### MyRealm3.getAuthenticationInfo ###");
		
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码

		if (!"Roger".equals(username))
		{
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!"123".equals(password))
		{
			throw new IncorrectCredentialsException(); // 如果密码错误
		}

		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(username + "@hotmil.com", password, getName());
	}
}
