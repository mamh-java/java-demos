<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.mamh.spring.demo.beans.Person" %>
<%@ page import="com.mamh.spring.demo.beans.HelloWorld" %>
<html>
<body>
<h2>Hello World!</h2>


<a href="testServlet">test</a>

<%
    //从application域中得到 spring的applicationContext实例

    ApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
    Person person = (Person) webApplicationContext.getBean("person");

    HelloWorld helloWorld = (HelloWorld) webApplicationContext.getBean("helloworld");
    helloWorld.hello();


%>


</body>
</html>
