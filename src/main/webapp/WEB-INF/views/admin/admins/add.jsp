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
    <%@include file="../header.jsp" %>
    <div class="table-content">
        <div class="button-add">
            <a href="/admin/admins/add" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj administratora</a>
        </div>
        <table class="table border-bottom">
            <thead>
            <tr class="d-flex">
                <th>Lp</th>
                <th class="col-2">Email</th>
                <th class="col-2">Imie</th>
                <th class="col-2">Nazwisko</th>
                <th class="col-2">Aktywny</th>
                <th class="col-2 center">AKCJE</th>

            </tr>
            </thead>
            <tbody class="text-color-lighter">
            <c:forEach var="user" items="${users}" varStatus="stat">
                <tr class="d-flex">
                    <td><c:out value="${stat.index  + 1}"/></td>
                    <td class="col-2"><c:out value="${user.email}"/></td>
                    <td class="col-2"><c:out value="${user.name}"/></td>
                    <td class="col-2"><c:out value="${user.surname}"/></td>
                    <td class="col-2"><c:out value="${user.active}"/></td>
                    <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                        <form method="post" action="/admin/admins/add">
                            <input type="hidden" name="userId" value="${user.id}">
                            <button type="submit" class="btn btn-warning rounded-0 text-light m-1">Dodaj</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

</header>


<%@include file="../../footer.jsp" %>
<script src="<c:url value="../../../../resources/js/app.js"/>"></script>
</body>
</html>