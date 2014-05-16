package com.yee.webstudy.shiro.authorization;

import java.util.Arrays;

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

public class AuthorizationSample
{
	private Logger logger = LoggerFactory.getLogger(AuthorizationSample.class);

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		AuthorizationSample sample = new AuthorizationSample();

		// sample.testRoles();

		// sample.testPermissions();

		sample.testCustomPermission();
	}

	public void testRoles()
	{
		logger.debug("### testRoles ###");

		this.login("Roger", "123", "role.ini");

		Subject user = SecurityUtils.getSubject();

		/**
		 * 同样Shiro也提供了checkRole(),checkRoles()的方法，只不过check的这两个方法在用户没有该角色时会抛出UnauthorizedException的异常
		 */
		// Return true
		logger.debug("Roger has role1  = " + user.hasRole("role1"));
		// Return true
		logger.debug("Roger has All(role1 and role2)  = " + user.hasAllRoles(Arrays.asList("role1", "role2")));
		// Return false
		logger.debug("Roger has All(role1 and role3)  = " + user.hasAllRoles(Arrays.asList("role1", "role3")));

		user.logout();
	}

	public void testPermissions()
	{
		logger.debug("### testPermission ###");

		this.login("Roger", "123", "permission.ini");

		Subject user = SecurityUtils.getSubject();

		/**
		 * 同样Shiro也提供了checkPermission(),checkPermissions()的方法，只不过check的这两个方法在用户没有该权限时会抛出UnauthorizedException的异常
		 */
		// Return true
		logger.debug("Roger has permission - 'user:create'  = " + user.isPermitted("user:create"));
		// Return true (equals = user.isPermittedAll("user:create,update"))
		logger.debug("Roger has All permission ('user:create' and 'user:udpate')  = "
				+ user.isPermittedAll("user:create", "user:update"));
		// Return false
		logger.debug("Roger has All permission ('user:create' and 'system:user:create')  = "
				+ user.isPermittedAll("user:create", "system:user:create"));

		user.logout();
	}

	public void testCustomPermission()
	{
		logger.debug("### testCustomPermission ###");

		this.login("Roger", "123", "authorizer.ini");

		Subject user = SecurityUtils.getSubject();

		logger.debug("Roger has permission - 'user1:create'  = " + user.isPermitted("user1:create"));
		logger.debug("Roger has permission - 'user1:update'  = " + user.isPermitted("user1:update"));
		logger.debug("Roger has permission - '+user1+10'  = " + user.isPermitted("+user1+10"));
		logger.debug("Roger has permission - 'menu:view'  = " + user.isPermitted("menu:view"));// 通过MyRolePermissionResolver解析得到的权限

		user.logout();
	}

	public void login(String username, String password, String config)
	{
		logger.debug("### login start ###");
		logger.debug("### login -> user = [" + username + "], password = [" + password + "]");

		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		// 如果要使用例如"classpath:loginAndLogout.ini"的方式获取文件，则要将该文件放在classes的根目录上。
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(Constants.INI_PATH
				+ "/com/yee/webstudy/shiro/authorization/" + config);

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
		// subject.logout();
	}
}
