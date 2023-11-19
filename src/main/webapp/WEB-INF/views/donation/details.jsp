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
            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">Dla fundacji:</label>
                <div class="col-sm-10">
                    <c:out value="${donation.institution.name}"/><br>
                    <c:out value="${donation.institution.description}"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">Rodzaj darów:</label>
                <div class="col-sm-10">
                    <c:forEach var="cat" items="${donation.categories}">
                        <c:out value="${cat.name}"/><br>
                    </c:forEach>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">Ilość worków</label>
                <div class="col-sm-10">
                    <c:out value="${donation.quantity}"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">Adres odbioru</label>
                <div class="col-sm-10">
                    <c:out value="${donation.street}"/><br/>
                    <c:out value="${donation.zipCode}"/>&nbsp;<c:out value="${donation.city}"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">Telefon</label>
                <div class="col-sm-10">
                    <c:out value="${donation.phone}"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">Data odbioru</label>
                <div class="col-sm-10">
                    <c:out value="${donation.pickUpDate}"/><br><c:out value="${donation.pickUpTime}"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">Status</label>
                <div class="col-sm-10">
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
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">Data utworzenia</label>
                <div class="col-sm-10">
                    <c:out value="${donation.created}"/>
                </div>
            </div>
            <c:if test="${donation.received == false}">
                <a href="/donation/confirm?donationId=${donation.id}"
                   class="btn btn-warning rounded-0 text-light m-1">Potwierdź odbiór</a>
            </c:if>
            <a href="/donation/list"
               class="btn btn-warning rounded-0 text-light m-1">Powrót</a>
        </div>
    </div>
</header>


<jsp:include page="../footer.jsp"/>
<div id="mojaDiv" data-nazwa-zmiennej="${donation.quantity}"></div>

<script src="../../../resources/js/app.js"></script>
</body>
</html>

