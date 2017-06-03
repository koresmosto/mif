<%@include file="/WEB-INF/pages/aux/common/taglibs.jsp" %>
<%--
  ~ Created under not commercial project
  --%>

<spring:message code="accessDeniedPage" var="accessDeniedPageVar"/>

<t:concretpage title="${accessDeniedPageVar}">
    <spring:message code="dear"/> <strong>${user}</strong>, <spring:message code="youAreNotAuthorized"/>.
</t:concretpage>
