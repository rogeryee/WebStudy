package com.yee.webstudy.shiro.authentication;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm
{
	public String getName()
	{
		return "MyRealm2";
	}

	public boolean supports(AuthenticationToken token)
	{
		return token instanceof UsernamePasswordToken; // 仅支持UsernamePasswordToken类型的Token
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码
		
		if (!"Phoebe".equals(username))
		{
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!"234".equals(password))
		{
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		
		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(username, password, getName());
	}
}
