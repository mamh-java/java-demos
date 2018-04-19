<%@ page import="com.mamh.struts2.demo.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
<br/><br/>

<p>设置键值对到page，request，session，application中</p>
<s:set value="'p name for request'" scope="request" var="produtName"/>
${requestScope.produtName}
<br/><br/>


<p>标签开始把值压入值栈，结束时弹出</p>
<%
    Product product = new Product();
    product.setDesc("xxxxxxxxxxxxxxxxxxxxx0001");
    request.setAttribute("product", product);
%>

<s:push value="#request.product">


    ${desc}

</s:push> <p>标签结束的时候这个desc就没有了。 </p>

=== ${requestScope.product.desc}


<br/><br/>
<p>s:if 标签</p>
<s:if test="price > 1000">
    I7 CPU
</s:if>
<s:elseif test="price >800">
    I5 CPU
</s:elseif>
<s:else>
    I3 CPU
</s:else>
<br/><br/>

<%
    List<Product> list = new ArrayList<>();
    list.add(new Product(001, "aa", "bbb", "ccc"));
    list.add(new Product(002, "bb", "222", "####"));
    list.add(new Product(003, "cc", "333", "@@@@"));
    list.add(new Product(004, "dd", "xxxx", "!!!!!"));
    request.setAttribute("list", list);
%>
<s:iterator value="#request.list">
    ${id} --- ${name} -- ${desc} --- ${price} <br/>
</s:iterator>
<br/><br/>

<s:iterator value="#request.list" status="status">
    index: ${status.index}, count: ${status.count} == ${id} --- ${name} -- ${desc} --- ${price} <br/>
</s:iterator>
<br/><br/>














</body>
</html>
