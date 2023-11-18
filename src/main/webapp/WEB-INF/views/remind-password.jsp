<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="head.jsp"/>
<body>
<header class="header--main-page">
    <jsp:include page="header.jsp"/>
</header>
<c:if test="${param['error'] != null}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Błędne dane logowania!
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<section class="login-page">
    <h2>Zmień hasło</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input path="email" type="email" placeholder="Email"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/register" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Wyślij email przypominający</button>
            <sec:csrfInput/>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>
