package com.yee.webstudy.shiro.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
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
public class AuthenticationSample
{
	private Logger logger = LoggerFactory.getLogger(AuthenticationSample.class);

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		AuthenticationSample sample = new AuthenticationSample();

		// sample.testLoginFromIniFile("Phoebe", "234");
		//
		// sample.testLoginFromCustomMutilpleRealm("Phoebe", "234");
		//
		// sample.testLoginFromJDBCRealm("Roger", "123");

		sample.testLoginFromCustomMutilpleRealmWithStrategy("Roger", "123");
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
		
		this.login(username, password, "userRealm.ini");
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public void testLoginFromCustomMutilpleRealm(String username, String password)
	{
		logger.debug("### testLoginFromIniFile ###");
		
		this.login(username, password, "customRealm.ini");
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public void testLoginFromJDBCRealm(String username, String password)
	{
		logger.debug("### testLoginFromJDBCRealm ###");
		
		this.login(username, password, "jdbcRealm.ini");
	}

	/**
	 * Shiro处理用户验证的流程如下：
	 * 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager。ini文件中可以配置用户[user]
	 * @param username
	 * @param password
	 */
	public void testLoginFromCustomMutilpleRealmWithStrategy(String username, String password)
	{
		logger.debug("### testLoginFromCustomMutilpleRealmWithStrategy ###");
		
		this.login(username, password, "allSuccessful.ini");
	}

	public void login(String username, String password, String config)
	{
		logger.debug("### login start ###");
		logger.debug("### login -> user = [" + username + "], password = [" + password + "]");

		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		// 如果要使用例如"classpath:loginAndLogout.ini"的方式获取文件，则要将该文件放在classes的根目录上。
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(Constants.INI_PATH
				+ "/com/yee/webstudy/shiro/authentication/" + config);

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

		// 得到一个身份集合，其包含了Realm验证成功的身份信息
		PrincipalCollection principalCollection = subject.getPrincipals();
		logger.debug("principalCollection size = "
				+ (principalCollection == null ? "Null" : principalCollection.asList().size()));

		// 6、退出
		subject.logout();
	}
}
