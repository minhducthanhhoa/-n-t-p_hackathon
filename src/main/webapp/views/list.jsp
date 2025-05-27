<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 5/27/2025
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Danh sách sản phẩm</h2>
<a href="${pageContext.request.contextPath}/product/add">Thêm sản phẩm</a>

<form action="${pageContext.request.contextPath}/product/search" method="get">
    <input type="text" name="keyword" placeholder="Tìm theo tên sản phẩm"/>
    <input type="submit" value="Tìm kiếm"/>
</form>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Trạng thái</th>
        <th>Ảnh</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.productName}</td>
            <td>${product.price}</td>
            <td>${product.status}</td>
            <td>
                <c:if test="${not empty product.image}">
                    <img src="${product.image}" width="50"/>
                </c:if>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/product/edit/${product.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/product/delete/${product.id}" onclick="return confirm('Xác nhận xoá?')">Xoá</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
