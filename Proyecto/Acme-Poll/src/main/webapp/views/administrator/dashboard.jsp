<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div>
		<spring:message code="dashboard.findMinAvgStdMaxPollsByPoller" var="dash1" />
			
		<display:table name="findMinAvgStdMaxPollsByPoller" id="findMinAvgStdMaxPollsByPoller" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.min" var="min" />
			<display:column title="${min}">
				<h3> <jstl:out value="${findMinAvgStdMaxPollsByPoller.get(0)}" /> </h3> 
			</display:column>
			<spring:message code="dashboard.avg" var="avg" />
			<display:column title="${avg}">
				<h3> <jstl:out value="${findMinAvgStdMaxPollsByPoller.get(1)}" /> </h3> 
			</display:column>
			<spring:message code="dashboard.std" var="std" />
			<display:column title="${std}">
				<h3> <jstl:out value="${findMinAvgStdMaxPollsByPoller.get(2)}" /> </h3> 
			</display:column>
			<spring:message code="dashboard.max" var="max" />
			<display:column title="${max}">
				<h3> <jstl:out value="${findMinAvgStdMaxPollsByPoller.get(3)}" /> </h3> 
			</display:column>
		</display:table>
		
		<spring:message code="dashboard.findMinAvgStdMaxInstancesByPoll" var="dash1" />
			
		<display:table name="findMinAvgStdMaxPollsByPoller" id="findMinAvgStdMaxPollsByPoller" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.min" var="min" />
			<display:column title="${min}">
				<h3> <jstl:out value="${findMinAvgStdMaxInstancesByPoll.get(0)}" /> </h3> 
			</display:column>
			<spring:message code="dashboard.avg" var="avg" />
			<display:column title="${avg}">
				<h3> <jstl:out value="${findMinAvgStdMaxInstancesByPoll.get(1)}" /> </h3> 
			</display:column>
			<spring:message code="dashboard.std" var="std" />
			<display:column title="${std}">
				<h3> <jstl:out value="${findMinAvgStdMaxInstancesByPoll.get(2)}" /> </h3> 
			</display:column>
			<spring:message code="dashboard.max" var="max" />
			<display:column title="${max}">
				<h3> <jstl:out value="${findMinAvgStdMaxInstancesByPoll.get(3)}" /> </h3> 
			</display:column>
		</display:table>
	</div>