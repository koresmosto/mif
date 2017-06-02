<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="htmlHead" fragment="true" %>
<%@attribute name="title" required="false"%>

<html>
    <head>
        <jsp:invoke fragment="htmlHead"/>
        <title>${title}</title>
    </head>
    <body>
        <br />
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="jumbotron">
                        <div id="pageheader">
                            <jsp:invoke fragment="header"/>
                        </div>
                        <div id="body">
                            <jsp:doBody/>
                        </div>
                        <div id="pagefooter">
                            <jsp:invoke fragment="footer"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
