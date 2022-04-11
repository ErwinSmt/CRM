<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Customers</title>
</head>
<body>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/style.css"/>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <!--put new button: add Customer -->

        <input type="button" value="Add Customer"
                onclick="window.location.href='showFormForAdd'; return false"
               class="add-button"
        />

        <%--add out html table--%>
        <table>

            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempCustomer" items="${customer}">

                <!-- construct an "update" link with customer id-->
                <c:url var="updateLink" value="showFormForUpdate">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>
                <c:url var="deleteLink" value="showFormForDelete">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>
                <tr>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>
                    <td><a href="${updateLink}">Update|</a>
                        <a href="${deleteLink}"
                            onclick="if (!(confirm('Are you sure you want to delete this customer?')))return false">Delete</a></td>

                </tr>
            </c:forEach>

        </table>

    </div>

</div>


</body>
</html>
