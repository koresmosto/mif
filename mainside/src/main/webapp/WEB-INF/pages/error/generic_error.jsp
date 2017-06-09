<%--
  ~ Created under not commercial project
  --%>
<%@include file="/WEB-INF/pages/aux/common/taglibs.jsp" %>

<c:set var = "genericErrorOccurredVar" scope = "session" value = "${exception.getClass().name}"/>

<t:genericpage title="${genericErrorOccurredVar}">
    <jsp:attribute name="htmlHead">
        <jsp:include page="/WEB-INF/pages/aux/common/htmlHeader.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>${genericErrorOccurredVar}</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <jsp:include page="/WEB-INF/pages/aux/common/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <h3>Debug Information:</h3>
        Date and time = ${datetime}<br />
        Requested URL = ${url}<br />
        <c:if test = "${exception.message != null}">
            Exception message = ${exception.message}<br />
        </c:if><br />
        <strong>Exception Stack Trace</strong><br />
        <c:forEach items="${exception.stackTrace}" var="stackLine">
            ${stackLine}
        </c:forEach>
    </jsp:body>
</t:genericpage>
