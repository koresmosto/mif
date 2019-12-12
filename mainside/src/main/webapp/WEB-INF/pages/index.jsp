<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/pages/aux/common/taglibs.jsp" %>
<spring:message code="homePage" var="homePageVar"/>

<t:concretpage title="${fn:escapeXml(homePageVar)}">
    <p>
    <h2><spring:message code="mif.project"/><br/></h2>
    </p>
</t:concretpage>
