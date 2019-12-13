<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/pages/aux/common/taglibs.jsp" %>

<c:set var="genericErrorOccurredVar" scope="session"
       value="${fn:escapeXml(exception.getClass().name)}"/>

<t:genericpage title="${fn:escapeXml(genericErrorOccurredVar)}">
    <jsp:attribute name="htmlHead">
        <jsp:include page="/WEB-INF/pages/aux/common/htmlHeader.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="header">
        <h2>${fn:escapeXml(genericErrorOccurredVar)} (refer to support, please)</h2>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <jsp:include page="/WEB-INF/pages/aux/common/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <h3>Debug Information:</h3>
        Date and time = ${fn:escapeXml(datetime)}<br/>
        Requested URL = ${fn:escapeXml(url)}<br/>
        <c:if test="${fn:escapeXml(exception.message != null)}">
            Exception message = ${fn:escapeXml(exception.message)}<br/>
        </c:if><br/>
        <strong>Exception Stack Trace</strong><br/>
        <c:forEach items="${fn:escapeXml(exception.stackTrace)}" var="stackLine">
            ${fn:escapeXml(stackLine)}
        </c:forEach>
    </jsp:body>
</t:genericpage>
