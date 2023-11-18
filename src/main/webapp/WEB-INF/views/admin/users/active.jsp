<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="../../head.jsp"/>
<body>
<header class="header--form-page">
    <jsp:include page="../header.jsp"/>
    <div class="table-content">
        <div class="dashboard-content border-dashed p-3 m-4 view-height">
            <style>
                .container2 {
                    max-width: 400px;
                    background-color: #fff;
                    padding: 20px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                    border-radius: 5px;
                }
            </style>
            <div class="container2">
                <h2>Czy na pewno chcesz zmienić status użytkownika?</h2>
                <div class="buttons">
                    <form action="/admin/users/active" method="post">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button class="btn btn-danger rounded-0 text-light m-1" type="submit" name="action" value="delete">OK</button>
                    </form>
                    <form action="/admin/users" method="get">
                        <button class="btn btn-danger rounded-0 text-light m-1" type="submit">Anuluj</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</header>


<%@include file="../../footer.jsp" %>
<script src="<c:url value="../../../../resources/js/app.js"/>"></script>
</body>
</html>
