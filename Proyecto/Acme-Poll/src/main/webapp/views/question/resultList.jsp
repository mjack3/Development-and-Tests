<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table pagesize="5" keepStatus="true" name="question"
	requestURI="${requestURI}" id="row" class="table table-over">

	<!-- Attributes -->

	<spring:message code="question.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="false" />

	<spring:message code="poll.ticket" var="ticketHeader" />
	<display:column property="ticket" title="${ticketHeader}"
		sortable="false" />

	<spring:message code="poll.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"
		sortable="false" />

	<spring:message code="poll.banner" var="bannerHeader" />
	<display:column title="${bannerHeader}">
		<img src="<jstl:out value="${row.banner}" />">
	</display:column>

	<spring:message code="poll.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}"
		sortable="false" />
	
	<spring:message code="poll.endDate" var="endDateHeader" />
	<display:column property="endDate" title="${endDateHeader}"
		sortable="false" />

	<jstl:if test="${today.before(row.startDate)}">
		<display:column>
			<a href="question/resultList.do?pollId=${row.id}"></a>
			<spring:message code="poll.result" />
		</display:column>
	</jstl:if>


	<display:column>
		<a href="question/list.do?pollId=${row.id}"> <spring:message
				code="poll.questions" />
		</a>
	</display:column>

	<display:column>
		<a href="instance/list.do?pollId=${row.id}"> <spring:message
				code="poll.instances" />
		</a>
	</display:column>

	<display:column>
		<a href="poller/view.do?pollId=${row.id}"> <spring:message
				code="poll.poller" />
		</a>
	</display:column>


</display:table>









