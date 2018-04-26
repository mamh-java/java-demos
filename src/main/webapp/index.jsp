<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
<body>

<br/>



<s:debug/>

<s:form action="testCoversion">
    <s:textfield name="age" label="age"/>
    <s:textfield name="birth" label="birth"/>
    <s:submit/>
</s:form>

</body>
</html>
