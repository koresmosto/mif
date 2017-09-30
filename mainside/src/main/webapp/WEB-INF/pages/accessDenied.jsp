<%@include file="/WEB-INF/pages/aux/common/taglibs.jsp" %>
<%--
  ~ Created in scope of "Make it fine" project
  --%>

<spring:message code="accessDeniedPage" var="accessDeniedPageVar"/>

<t:genericpage title="${accessDeniedPageVar}">
    <jsp:attribute name="htmlHead">
        <jsp:include page="/WEB-INF/pages/aux/common/htmlHeader.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>${accessDeniedPageVar}</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <jsp:include page="/WEB-INF/pages/aux/common/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <spring:message code="dear"/> <strong>${user}</strong>, <spring:message code="youAreNotAuthorized"/>.
        </h2>
    </jsp:body>
</t:genericpage>
