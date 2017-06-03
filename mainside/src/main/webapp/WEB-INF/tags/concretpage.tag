<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="title" required="true"%>

<t:genericpage title="${title}">
    <jsp:attribute name="htmlHead">
        <jsp:include page="/WEB-INF/pages/aux/common/htmlHeader.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="header">
      <h1>${title}</h1>
      <spring:message code="language" /> : <a href="?language=en">English</a> | <a href="?language=ru">Русский</a>
      (<spring:message code="currentLocale" /> : ${pageContext.response.locale}) |
        <a href="<c:url value='/logout' />"><spring:message code="logout" /></a> <br /><br />
    </jsp:attribute>
    <jsp:attribute name="footer">
      <jsp:include page="/WEB-INF/pages/aux/common/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:genericpage>
