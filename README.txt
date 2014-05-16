####### 项目介绍 #######
本工程包含了学习WEB Application的多个例子，并集成了Typescript作为开发的环境。
在使用Eclipse时，采用Dynamic Web Application的模板，output目录设置为webapp/WEB-INF/classes目录，否则可能会发生cannot found or load main class


#################
### AngularJS ### 
#################

	#### HelloWorld.html ####
	介绍：本例为入门的Hello Wolrd例子，展现了Controller和model的绑定
	
	#### SimpleSample.html ####
	介绍：本例包含了若干个简单的AngularJS的应用，包括：
	   .按钮响应
	   .数据遍历
	   .函数使用
	   .表格使用
	   .CSS应用
	   .Filter应用
	
	#### ServiceSample.html ####
	介绍：本例介绍了Service和Factory的使用
	
	#### RouteSample\RouteSample.html ####
	介绍：本例介绍了采用AngularJS原生的route的应用，其中分别使用了 TemplateUrl和Template两种加载页面的方式。(也可以使用TemplateProvider来实现，可以参考uiRoute\RouteSample.html)
	
	#### uiRoute\RouteSample.html ####
	介绍：本例介绍了采用AngularJS的ui-Router插件的应用。
	
	#### SimpleXHR.html ####
	介绍：本例介绍了AngularJS的$http的使用。

	
##################
####  Jersey   ### 
##################

	#### HelloWorldResource.java ####
	介绍：本例为入门的Hello Wolrd例子，展现了如何采用Jersey2.8+Tomcat8集成搭建RESTFul的架构，可以通过以下的URL来访问相应的服务：
	http://localhost:8080/WebStudy/services/helloworld
	http://localhost:8080/WebStudy/services/helloworld/user/Roger
	http://localhost:8080/WebStudy/services/helloworld/user?name=Roger
	
	#### JsonSupportResource.java ####
	介绍：本例展示了在Jersey服务中使用JSON对象，可以通过以下的URL来访问相应的服务：
	基于Jaxb的JSON支持 - http://localhost:8080/WebStudy/services/json/jaxb
	基于Jackson的JSON支持 - http://localhost:8080/WebStudy/services/json/jackson
	基于JSON Processing的JSON支持 - http://localhost:8080/WebStudy/services/json/jsonp
	
	#### RequestFilter.java ####
	介绍：本例展示了在Jersey服务中使用Filter，RequestFilter类可以捕获在request发送到server之前以及response返回给client之前。通过Filter可以进行用户验证、日志等操作。
	
	#### Logged.java ####
	介绍：本例展示了在Jersey服务中如何自定义的将Filter或Interceptor与具体的类或方法绑定。本例中创建了一个自定义的注释Logged，并对其添加了Namebinding的注释，这样可以给对应的Resource类和Filter类添加@Logged注释，
	这样只有添加了@Logged注释的方法才会触发Filter
	
	
##################
####  SLF4J    ### 
##################

	#### HelloWorld.java ####
	介绍：本例采用了SLF4J的框架+SLF4J-Simple实现了简单的日志输出.
	注意：确保classpath下只有 SLF4J-Simple的jar，而无logback的jar包，否则SLF4J会自动加载其中任一个。
	
	#### LogbackHelloWorld.java ####
	介绍：本例采用了SLF4J的框架+logback实现了简单的日志输出	
	注意：确保classpath下只有 logback的jar，而无SLF4J-Simple的jar包，否则SLF4J会自动加载其中任一个。


##################
####  Shiro    ### 
##################

	#### authentication\AuthenticationSample ####
	介绍：本例展示了用Shiro作为框架构建的登录/登出的验证。
	注意：使用ini配置文件时，如果用"classpath:xxxx.ini"的方式，则需要将该xxxx.ini文件放在根目录下，否则要使用绝对路径获取文件
	
	1. testLoginFromIniFile通过读取ini文件中的用户凭证，来验证用户信息
	2. testLoginFromCustomMutilpleRealm方法读取了ini的配置信息，但是该ini文件并不存放用户信息，而是指向了MyRealm1和MyRealm2这两个自定义的Realm的实现。
	3. testLoginFromJDBCRealm方法展示了如何采用JDBC的方式获取用户验证：
	   1) 需要在ini文件中配置jdbc数据源，并配置org.apache.shiro.realm.jdbc.JdbcRealm为Realm的实现类
	   2) JdbcRealm中默认查询语句为"select password from users where username = ?"，当然用户也可以继承JdbcRealm并override相应的方法，达到定制化查询语句的目的。

	4. testLoginFromCustomMutilpleRealmWithStrategy继承了验证的策略，在allSuccessful.ini文件中配置验证策略，本例用的是AllSuccessfulStrategy，即所有Realm验证成功才算成功，且返回所有Realm身份验证成功的
认证信息，Shiro还提供如下策略：
		FirstSuccessfulStrategy：只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
		AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；

	#### authorization\AuthorizationSample ####
	介绍：本例展示了用Shiro作为框架构建的用户角色权限的验证。
	
	1. testRoles通过读取ini文件中的用户角色信息，来验证用户是否拥有角色
	2. testPermissions通过读取ini文件中的用户角色权限信息，来验证用户是否拥有权限
	
	
	
	
	
	
	
	
	
	
	
	
	

