<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/pages/aux/common/taglibs.jsp" %>

<spring:message code="accessDeniedPage" var="accessDeniedPageVar"/>

<t:genericpage title="${fn:escapeXml(accessDeniedPageVar)}">
    <jsp:attribute name="htmlHead">
        <jsp:include page="/WEB-INF/pages/aux/common/htmlHeader.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>${fn:escapeXml(accessDeniedPageVar)}</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <jsp:include page="/WEB-INF/pages/aux/common/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <spring:message code="dear"/> <strong>${fn:escapeXml(user)}</strong>,
            <spring:message code="youAreNotAuthorized"/>.
        </h2>
    </jsp:body>
</t:genericpage>
