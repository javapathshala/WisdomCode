<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Login Page</title>
</head>

<link type="text/css" rel="stylesheet" href="stylesheets/jp.css" />
<link type="text/css" rel="stylesheet" href="stylesheets/newjp.css" />

<script type="text/javascript" src="./javascript/ajaxHelper.js"></script>
<body class="bodyNew" onload='document.f.j_username.focus();'>
	<table cellspacing="0" cellpadding="0" border="0" width="100%">
		<tr>
			<td colspan="3"><jsp:include page="/jsp/HeaderSpring.jsp"
					flush="true" /></td>
		</tr>
	</table>

	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><%@ include file="/jsp/navBar.jsp"%>
			</td>
			<td class="vt" width="100%"><c:url value="/LoginDisplay.do"
					var="loginUrl" /> <!-- form:form
					method="post" commandName="viewCustomerForm"
					action="CustomerListViewDisplay.do?showCustButton=true"
					name="viewCustomerForm"--> <form:form method="post"
					action="${loginUrl}" name="loginForm" commandName="loginForm">

					<div class="header">
						Spring Framework Demo by Smylee Consultants!!
						<%=request.getSession().getId()%></div>
					<br />
					<form:errors path="*" element="div" cssClass="errorbgc" />
					<c:if test="${not empty error}">
						<div class="errorbgc">
							Your login attempt was not successful, try again.<br /> Caused :
							${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
						</div>
					</c:if>



					<table class="filter" cellpadding="2" cellspacing="5" border="0"
						width="100%">
						<tr>
							<td class="normalar" width="20%"><label for="username">Username</label></td>
							<td width="20%"><form:input path="j_username" /></td>
						</tr>
						<tr>
							<td class="normalar" width="20%"><label for="password">Password</label></td>
							<td width="20%"><form:password path="j_password" /></td>
						</tr>
						<tr>
							<td class="normalar" width="20%"><input id="submit"
								class="btn" name="submit" type="submit" value="Login" /></td>
							<td width="20%"></td>
						</tr>

					</table>
				</form:form></td>
		</tr>
	</table>

</body>


</html>




























