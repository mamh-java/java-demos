<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
<body>

<s:debug/><br/><br/>

productName : ${name}
<br/>

productDesc: ${desc}
<br/>

productPrice: ${price}
<br/><br/>

<p>打印值栈中的属性值的，对于对象栈，打印对应的属性值。</p>
productPrice: <s:property value="[0].price"/>
<p>or:</p>
productPrice: <s:property value="price"/>
<br/><br/>


<p>打印值栈中的属性值的，对于map栈，打印request，session，application的某个属性某个请求参数。</p>
#session.date : <s:property value="#session.date"/>
<p>or:</p>
#parameters.name[0] : <s:property value="#parameters.name[0]"/>

<br/><br/>


s:url 创建url字符串的 ,对应value中的会自动进行OGNL解析，要想用字符串值需要加上单引号。数字不会自动OGNL解析。
<s:url value="/testUrl" var="url">
    <s:param value="price" name="id"/>

</s:url>
<p>${url}</p><br/>

<s:url action="product-save" method="save" var="url4" namespace="/helloworld" includeParams="all">
    <s:param value="'price'" name="id"/>

</s:url>

url4 == ${url4}

</body>
</html>
