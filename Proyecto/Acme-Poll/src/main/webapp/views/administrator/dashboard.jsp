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


				
		<spring:message code="dashboard.findMinAvgStdMaxPollsByPoller" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${findMinAvgStdMaxPollsByPoller}" />		
		
		<br/><br/>
		
		<spring:message code="dashboard.findMinAvgStdMaxInstancesByPoll" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${findMinAvgStdMaxInstancesByPoll}" />		
		
		<br/><br/>
		
		<spring:message code="dashboard.findMinAvgStdMaxQuestionByPoll" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${findMinAvgStdMaxQuestionByPoll}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.findMinAvgMaxHintsByPoll" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${findMinAvgMaxHintsByPoll}" />	
		
		<br/><br/>
		
		<spring:message code="dashboard.findPollWithMoreHints" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${findPollWithMoreHints}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.findPollWithFewerHints" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${findPollWithFewerHints}" />		
		
		
		<br/><br/>
		<spring:message code="dashboard.avgChirpActor" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:forEach var="c" items="${avgChirpActor}" varStatus="loop">
		<jstl:out value="${c[0].name}" />:<jstl:out value="${c[1]}," />	
		</jstl:forEach>
		
		<br/><br/>
		
		<spring:message code="dashboard.actorsAboveAvg" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:if test="${actorsAboveAvgChirp.size()==0}">
		<jstl:out value="0"/>
		</jstl:if>
		<jstl:if test="${actorsAboveAvgChirp.size()>0}">
		<jstl:forEach var="c" items="${actorsAboveAvgChirp}" varStatus="loop">
			<jstl:out value="${c}"/>
			<jstl:if test="${!loop.last}">, </jstl:if> 
		</jstl:forEach>
		</jstl:if>
		<br />
		

		