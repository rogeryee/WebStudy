####### 项目介绍 #######
本工程包含了学习WEB Application的多个例子，并集成了Typescript作为开发的环境。


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








