<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="zh-CN">
<head>
    <title>首页</title>
    <%@ include file="/WEB-INF/web/common/include.jsp"%>
</head>
<body>
    <div class="container-fluid">
        <h1 class="text-center">交易记录</h1>
        <table class="table">
            <c:forEach items="${requestScope.list}" var="transaction">
                <tr>
                    <td>
                        <c:if test="${sessionScope.user.id == transaction.fromId}">付款</c:if>
                        <c:if test="${sessionScope.user.id == transaction.toId}">收款</c:if>
                    </td>
                    <td>
                        <c:if test="${sessionScope.user.id == transaction.fromId}">${transaction.toUser.userName}</c:if>
                        <c:if test="${sessionScope.user.id == transaction.toId}">${transaction.fromUser.userName}</c:if>
                    </td>
                    <td>
                        <c:if test="${sessionScope.user.id == transaction.fromId}"><span style="color: red;">-</c:if>
                        <c:if test="${sessionScope.user.id == transaction.toId}"><span style="color: green;">+</c:if>${transaction.money}
                    </td>
                    <td><fmt:formatDate value="${transaction.createTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>