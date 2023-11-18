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
            <a href="/admin/institution/add" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj instytucje</a>
        </div>
        <table class="table border-bottom">
            <thead>
            <tr class="d-flex">
                <th>Lp</th>
                <th class="col-1">Nazwa</th>
                <th class="col-2">Cel</th>
                <th class="col-2 center">AKCJE</th>
            </tr>
            </thead>
            <tbody class="text-color-lighter">
            <c:forEach var="institution" items="${institutions}" varStatus="stat">
                <tr class="d-flex">
                    <td><c:out value="${stat.index  + 1}"/></td>
                    <td class="col-1"><c:out value="${institution.name}"/></td>
                    <td class="col-2"><c:out value="${institution.description}"/></td>
                    <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                        <a href="/admin/institution/delete?institutionId=${institution.getId()}"
                           class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                        <a href="/admin/institution/edit?institutionId=${institution.getId()}"
                           class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>
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