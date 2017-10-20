
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:acme_view entity="${poller}" skip_fields="name,surname,email,phone,address,polls"  >

 <tr>
	<td> <b><spring:message code="poller.name"/></b> <jstl:out value="${poller.name}"/> </td>
<td>

 <tr>
	<td> <b><spring:message code="poller.surname"/></b> <jstl:out value="${poller.surname}"/> </td>
<td>

 <tr>
	<td> <b><spring:message code="poller.email"/></b> <jstl:out value="${poller.email}"/> </td>
<td>

 <tr>
	<td> <b><spring:message code="poller.phone"/></b> <jstl:out value="${poller.phone}"/> </td>
<td>

 <tr>
	<td> <b><spring:message code="poller.address"/></b> <jstl:out value="${poller.address}"/> </td>
<td>

</acme:acme_view>

<br/>