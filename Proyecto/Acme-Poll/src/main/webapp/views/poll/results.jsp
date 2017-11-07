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


<jstl:forEach begin="0" end="${question.size()-1}" var="a">
	
	<b>${question.get(a).number}. ${question.get(a).statment}</b>

	<br />
	
	<div class="btn-group" data-toggle="buttons">
	<jstl:forEach begin="0" end="${question.get(a).choices.size()-1}" var="i">

		${question.get(a).choices.get(i)} ( ${ results.get(a+i) } )
		<br/>

	</jstl:forEach>
	</div>
	<br/>

</jstl:forEach>


<script>

</script>