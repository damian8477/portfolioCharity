<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form:form method="post" action="/email" class="form--contact" modelAttribute="email">
            <div class="form-group form-group--50"><form:input path="name" type="text" name="name"
                                                               placeholder="Imię"/></div>
            <div class="form-group form-group--50"><form:input path="surname" type="text" name="surname"
                                                               placeholder="Nazwisko"/></div>
            <div class="form-group"><form:input path="email" type="email" name="email" placeholder="Email"/></div>

            <div class="form-group"><form:textarea path="message" name="message" placeholder="Wiadomość"
                                                   rows="1"></form:textarea></div>

            <form:button class="btn" type="submit">Wyślij</form:button>
        </form:form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img
                    src="<c:url value="../../resources/images/icon-facebook.svg"/>"/></a>
            <a href="#" class="btn btn--small"><img
                    src="<c:url value="../../resources/images/icon-instagram.svg"/>"/></a>
        </div>
    </div>
</footer>