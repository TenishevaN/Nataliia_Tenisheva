<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="controller?command=openCardUser&id="${user.id}"><fmt:message
                        key="profile"></fmt:message></a></li>
                <li><a href="${pageContext.request.contextPath}/request"><fmt:message key="repair_requests"></fmt:message></a></li>
                <c:set var="role" value="${fn:toLowerCase(role)}"></c:set>
                <c:if test="${role != 'user' && role != 'master'}">
                    <li><a href="${pageContext.request.contextPath}/user"><fmt:message key="users"></fmt:message></a></li>
                    <li><a href="${pageContext.request.contextPath}/report"><fmt:message key="reports"></fmt:message></a></li>
                </c:if>
                <c:if test="${role == 'admin'}">
                    <li><a href="${pageContext.request.contextPath}/service"><fmt:message key="service"></fmt:message></a></li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/"><span class="glyphicon glyphicon-log-out"></span><fmt:message
                        key="log_out"></fmt:message></a></li>
            </ul>
        </div>
    </div>
</nav>


