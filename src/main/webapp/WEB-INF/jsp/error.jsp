<%@ include file="../include/head.jspf" %>

<html>
<body>

<h2><fmt:message key="error"></fmt:message></h2>

${errorText}
<br>
<div>
      <a href="${pageContext.request.contextPath}/"><fmt:message key="home"></fmt:message></a>
</div>

</body>
</html>