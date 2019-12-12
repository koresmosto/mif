<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/pages/aux/common/taglibs.jsp" %>

<spring:message code="login" var="loginVar"/>

<html>
<head>
    <jsp:include page="/WEB-INF/pages/aux/common/htmlHeader.jsp"/>
    <style type="text/css">
        .elem-bottom {
            margin-bottom: 7px
        }
    </style>
    <title><spring:message code="loginPage"/></title>
</head>
<body>
<div id="mainWrapper">
    <div class="login-container">
        <div class="login-card">
            <spring:message code="language"/> : <a href="?language=en">English</a> |
            <a href="?language=ru">Русский</a>
            (<spring:message code="currentLocale"/> : ${fn:escapeXml(pageContext.response.locale)})
            <div class="login-form">
                <c:url var="loginUrl" value="/login"/>
                <form action="${fn:escapeXml(loginUrl)}" method="post" class="form-horizontal">
                    <c:if test="${fn:escapeXml(param.error != null)}">
                        <div class="alert alert-danger">
                            <p><spring:message code="invalidUsernameAndPassword"/>.</p>
                        </div>
                    </c:if>
                    <c:if test="${fn:escapeXml(param.logout != null)}">
                        <div class="alert alert-success">
                            <p><spring:message code="youHaveBeenLoggedOutSuccessfully"/>.</p>
                        </div>
                    </c:if>
                    <div class="input-group input-sm elem-bottom">
                        <label class="input-group-addon" for="username"><i
                                class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="username" name="ssoId"
                               placeholder="Enter Username"
                               required>
                    </div>
                    <div class="input-group input-sm elem-bottom">
                        <label class="input-group-addon" for="password"><i
                                class="fa fa-lock"></i></label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Enter Password" required>
                    </div>
                    <input type="hidden" name="${fn:escapeXml(_csrf.parameterName)}"
                           value="${fn:escapeXml(_csrf.token)}"/>
                    <div class="form-actions">
                        <input type="submit"
                               class="btn btn-block btn-primary btn-default"
                               value="${fn:escapeXml(loginVar)}">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
