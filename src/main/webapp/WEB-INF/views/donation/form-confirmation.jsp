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
      <div class="slogan container container--90">
          <h2>
            Dziękujemy za przesłanie formularza Na maila prześlemy wszelkie
            informacje o odbiorze.
          </h2>
      </div>
    </header>

    <jsp:include page="../footer.jsp"/>

    <script src="js/app.js"></script>
  </body>
</html>
