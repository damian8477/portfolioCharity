<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="../head.jsp"/>
<body>
<header class="header--form-page">
    <jsp:include page="../header.jsp"/>
    <div class="table-content">
        <div class="dashboard-content border-dashed p-3 m-4 view-height">

            <form:form method="post" modelAttribute="user">

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">Email</label>
                    <div class="col-sm-10">
                        <form:input path="email" class="form-control"/><form:errors path="email"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">Imie</label>
                    <div class="col-sm-10">
                        <form:input path="name" class="form-control"/><form:errors path="name"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">Nazwisko</label>
                    <div class="col-sm-10">
                        <form:input path="surname" class="form-control"/><form:errors path="surname"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">Hasło</label>
                    <div class="col-sm-10">
                        <form:input type="password" path="password" value="${user.password}"
                                    class="form-control"/><form:errors path="password"/>
                    </div>
                </div>
                <form:hidden path="active"/>
                <form:hidden path="role"/>
                <form:hidden path="id"/>
                <form:button class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</form:button>
            </form:form>
        </div>
    </div>
</header>


<%@include file="../footer.jsp" %>
<script src="<c:url value="../../../resources/js/app.js"/>"></script>
</body>
</html>

