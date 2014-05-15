package com.yee.webstudy.shiro.loginAndLogout;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yee.webstudy.shiro.Constants;

/**
 * 本例展示了用Shiro作为框架构建的登录/登出的验证。
 * 
 * @author Roger.Yee
 */
public class LoginAndLogoutSample
{
	private Logger logger = LoggerFactory.getLogger(LoginAndLogoutSample.class);

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LoginAndLogoutSample sample = new LoginAndLogoutSample();

		sample.testLoginFromIniFile("Phoebe", "234");

		sample.testLoginFromCustomMutilpleRealm("Phoebe", "234");

		sample.testLoginFromJDBCRealm("Roger", "123");
	}

	/**
	 * 本方法读取了loginAndLogout.ini文件，其中保存了用户的凭证信息。
	 * 
	 * @param username
	 * @param password
	 */
	public void testLoginFromIniFile(String username, String password)
	{
		logger.debug("### testLoginFromIniFile ###");

		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		// 如果要使用"classpath:loginAndLogout.ini"的方式获取文件，则要将该文件放在classes的根目录上。
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(Constants.INI_PATH
				+ "/com/yee/webstudy/shiro/loginAndLogout/userRealm.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityUtils.setSecurityManager(factory.getInstance());

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		try
		{
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e)
		{
			// 5、身份验证失败
			logger.debug("Login failed！");
		}

		logger.debug("subject.isAuthenticated() = " + subject.isAuthenticated());

		// 6、退出
		subject.logout();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public void testLoginFromCustomMutilpleRealm(String username, String password)
	{
		logger.debug("### testLoginFromIniFile ###");

		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		// 如果要使用"classpath:loginAndLogout.ini"的方式获取文件，则要将该文件放在classes的根目录上。
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(Constants.INI_PATH
				+ "/com/yee/webstudy/shiro/loginAndLogout/customRealm.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityUtils.setSecurityManager(factory.getInstance());

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		try
		{
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e)
		{
			// 5、身份验证失败
			logger.debug("Login failed！");
			e.printStackTrace();
		}

		logger.debug("subject.isAuthenticated() = " + subject.isAuthenticated());

		// 6、退出
		subject.logout();
	}

	public void testLoginFromJDBCRealm(String username, String password)
	{

		logger.debug("### testLoginFromJDBCRealm ###");

		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(Constants.INI_PATH
				+ "/com/yee/webstudy/shiro/loginAndLogout/jdbcRealm.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("username", "password");

		try
		{
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e)
		{
			// 5、身份验证失败
			logger.debug("Login failed！");
			e.printStackTrace();
		}

		logger.debug("subject.isAuthenticated() = " + subject.isAuthenticated());

		// 6、退出
		subject.logout();
	}

}
