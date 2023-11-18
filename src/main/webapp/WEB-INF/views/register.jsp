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
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input path="email" type="email" placeholder="Email"/><form:errors path="email"/>
        </div>
        <div class="form-group">
            <form:input path="name" type="text" placeholder="Imie"/><form:errors path="name"/>
        </div>
        <div class="form-group">
            <form:input path="surname" type="text" placeholder="Nazwisko"/><form:errors path="surname"/>
        </div>
        <div class="form-group">
            <form:input type="password" name="password" placeholder="Hasło" path="password"/><form:errors
                path="password"/>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="login.jsp" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>
