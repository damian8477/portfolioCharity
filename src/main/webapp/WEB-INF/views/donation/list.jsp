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
    <%@include file="../header.jsp" %>
    <div class="table-content">
        <div class="button-add">
            <a href="/donation/add" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Przekaź dary</a>
        </div>
        <table class="table border-bottom">
            <thead>
            <tr class="d-flex">
                <th>Lp</th>
                <th class="col-2">Ilość worków</th>
                <th class="col-2">Adres</th>
                <th class="col-2">Telefon</th>
                <th class="col-2">Data odbioru</th>
                <th class="col-2">Godzina odbioru</th>
                <th class="col-2">Komentarz</th>
                <th class="col-2">Status</th>
                <th class="col-2 center">AKCJE</th>

            </tr>
            </thead>
            <tbody class="text-color-lighter">
            <c:forEach var="donation" items="${donations}" varStatus="stat">
                <tr class="d-flex">
                    <td><c:out value="${stat.index  + 1}"/></td>
                    <td class="col-2"><c:out value="${donation.quantity}"/></td>
                    <td class="col-2">
                        <c:out value="${donation.street}"/><br/>
                        <c:out value="${donation.zipCode}"/>&nbsp;<c:out value="${donation.city}"/>
                    </td>
                    <td class="col-2"><c:out value="${donation.phone}"/></td>
                    <td class="col-2"><c:out value="${donation.pickUpDate}"/></td>
                    <td class="col-2"><c:out value="${donation.pickUpTime}"/></td>
                    <td class="col-2"><c:out value="${donation.pickUpComment}"/></td>
                    <c:if test="${donation.received == true}">
                        <td class="col-2">
                            Odebrano<br>
                            <c:out value="${donation.receivedTime}"/>
                        </td>
                    </c:if>
                    <c:if test="${donation.received == false}">
                        <td class="col-2">
                            Nieodebrano
                        </td>
                    </c:if>
                    <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                        <a href="/donation/details?donationId=${donation.id}"
                           class="btn btn-warning rounded-0 text-light m-1">Szczegóły</a>
                        <c:if test="${donation.received == false}">
                            <a href="/donation/confirm?donationId=${donation.id}"
                               class="btn btn-warning rounded-0 text-light m-1">Potwierdź odbiór</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

</header>


<%@include file="../footer.jsp" %>
<script src="<c:url value="../../../resources/js/app.js"/>"></script>
</body>
</html>