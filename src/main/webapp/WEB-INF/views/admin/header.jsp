<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <sec:authorize access="isAuthenticated()">
        <h1>Panel administratora</h1>
        <nav class="container container--70">
            <ul class="nav--actions">
                <li class="logged-user">
                    Witaj ${name}
                    <ul class="dropdown">
                        <li><a href="#">Profil</a></li>
                        <li><a href="#">Moje zbiórki</a></li>
                        <li><a href="/logout">Wyloguj</a></li>
                    </ul>
                </li>
            </ul>

            <ul>
                <li><a href="index.html" class="btn btn--without-border active">Start</a></li>
                <li><a href="/admin/users" class="btn btn--without-border">Użytkownicy</a></li>
                <li><a href="/admin/admins" class="btn btn--without-border">Administratorzy</a></li>
                <li><a href="/admin/institution" class="btn btn--without-border">Fundacje i organizacje</a></li>
                <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
            </ul>
        </nav>
    </sec:authorize>
</sec:authorize>
