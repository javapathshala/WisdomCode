<%@ page language="java" contentType="text/html" session="true" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body class="bodyNew">
 
<DIV class="utilNavBar">

</DIV>
<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
	id="BreadCrumbArea">
	<TBODY>
		<TR>
			<TD height="6" width="8%" colspan="4">
			<TABLE width="76%" border="0" cellspacing="0" cellpadding="0"
				id="BreadCrumbSpacing">
				<TBODY>
					<TR>
						<TD height="6" width="76%"><IMG src="images/lines/spacer.gif"
							width="748" height="6" alt=""></TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
		<TR>
			<TD>
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
				id="BreadCrumb">
				<TBODY>
					<TR>
						<TD width="2%" height="10"><IMG src="images/lines/spacer.gif" 
							width="20" height="10" alt=""></TD>
						<TD height="10" width="19%"><SPAN class="breadcrumb">

						
					<A href="" class="breadcrumbNav" alt="Home" >	 <img src="images/buttons/loginNew.gif" border="0"/>  </A></SPAN></TD>

						<TD height="10">&nbsp;&nbsp;&nbsp;  <SPAN class="breadcrumb">SymlSee Consultants</SPAN>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;fsdfd
		<c:if test="${not empty username}">
		Username : ${username}</h3>
		</c:if>						
		
	
						</TD>
						<TD height="10" class="ar" width="27%"><SPAN class="breadcrumb">
						<%
								java.util.Calendar calendar = java.util.Calendar.getInstance();
								java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
								String dateString = formatter.format(calendar.getTime());
						%>
						 <SPAN class="breadcrumb"> <%= dateString  %>
						</SPAN>&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						<SPAN class="breadcrumb"> 
						
							 
						<a href="<c:url value="/j_spring_security_logout" />" >
						
						<img src="images/buttons/logoutNew.gif" border="0"/> 
						
						
						</A>
						
						 </SPAN>
						</SPAN></TD>
						<TD height="10" width="2%"><IMG src="images/lines/spacer.gif"
							width="20" height="10" alt=""></TD>
					</TR>
					<TR>
						<TD colspan="6" height="5"><IMG src="images/lines/spacer.gif"
							width="20" height="10" alt=""></TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>

</body>

</html>
