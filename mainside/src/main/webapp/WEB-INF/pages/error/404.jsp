<%@include file="/WEB-INF/pages/aux/common/taglibs.jsp" %>

<spring:message code="404ClientError" var="Var404ClientError"/>

<t:genericpage title="${fn:escapeXml(Var404ClientError)}">
    <jsp:attribute name="htmlHead">
        <jsp:include page="/WEB-INF/pages/aux/common/htmlHeader.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>${fn:escapeXml(Var404ClientError)}</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <jsp:include page="/WEB-INF/pages/aux/common/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <h2><spring:message code="resourceNotFoundErrorOccured"/></h2>
    </jsp:body>
</t:genericpage>
