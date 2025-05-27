<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 5/27/2025
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2><c:if test="${product.id == 0}">Thêm</c:if><c:if test="${product.id != 0}">Sửa</c:if> sản phẩm</h2>

<form:form method="post" modelAttribute="product" enctype="multipart/form-data">
  <table>
    <tr>
      <td>Tên sản phẩm:</td>
      <td>
        <form:input path="productName"/>
        <form:errors path="productName" cssClass="error"/>
      </td>
    </tr>
    <tr>
      <td>Giá:</td>
      <td>
        <form:input path="price"/>
        <form:errors path="price" cssClass="error"/>
      </td>
    </tr>
    <tr>
      <td>Mô tả:</td>
      <td><form:textarea path="description"/></td>
    </tr>
    <tr>
      <td>Trạng thái:</td>
      <td>
        <form:select path="status">
          <form:option value="" label="-- Chọn trạng thái --"/>
          <form:option value="AVAILABLE" label="Còn hàng"/>
          <form:option value="NOT_AVAILABLE" label="Hết hàng"/>
        </form:select>
        <form:errors path="status" cssClass="error"/>
      </td>
    </tr>
    <tr>
      <td>Ảnh:</td>
      <td><form:input path="image"/></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="Lưu"/></td>
    </tr>
  </table>
</form:form>

<a href="${pageContext.request.contextPath}/product">Quay lại</a>
</body>
</html>
