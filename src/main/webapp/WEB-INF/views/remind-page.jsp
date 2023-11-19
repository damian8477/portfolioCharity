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
<section class="login-page">
    <h2>Wprowadź nowe hasło</h2>
    <form:form method="post" modelAttribute="user">
        <form:hidden path="email"/>
        <form:hidden path="name"/>
        <form:hidden path="surname"/>
        <form:hidden path="id"/>
        <form:hidden path="active"/>
        <form:hidden path="role"/>
        <div class="form-group">
            <form:input type="password" name="password" placeholder="Hasło" path="password"/>
            <br><form:errors path="password"/>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Zmień hasło</button>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>
