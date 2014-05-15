package com.yee.webstudy.shiro.loginAndLogout;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import org.apache.shiro.mgt.SecurityManager;

/**
 * 本例展示了用Shiro作为框架构建的登录/登出的验证。
 * 
 * @author Roger.Yee
 */
public class LoginAndLogoutSample
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LoginAndLogoutSample sample = new LoginAndLogoutSample();

		// 用户配置信息保存在loginAndLogout.ini文件中
		sample.testLoginFromIniFile("Roger", "123");
	}

	public void testLoginFromIniFile(String username, String password)
	{
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:loginAndLogout.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityUtils.setSecurityManager(factory.getInstance());

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);

		try
		{
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e)
		{
			// 5、身份验证失败
			System.out.println("Login failed！");
		}

		System.out.println("subject.isAuthenticated() = "
				+ subject.isAuthenticated());

		// 6、退出
		subject.logout();
	}

}
