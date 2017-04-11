<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="zh-CN">
<head>
    <title>首页</title>
    <%@ include file="/WEB-INF/web/common/include.jsp"%>
    <style>
        .btn_green{
            display: block;
            width: 100px;
            font-size: 1.4em;
            color: black;
            text-align: center;
            background: #ffffff;
            -webkit-box-shadow: 0 2px 0 #ffffff;
            -moz-box-shadow: 0 2px 0 #ffffff;
            box-shadow: 0 2px 0 #ffffff;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            border-radius: 5px;
            margin: 20px 0;
            padding: 2%;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <h2>Welcome, ${sessionScope.user.userName}.</h2>

        <div class="row">
            <div class="col-xs-12 col-md-12">
                <a href="javascript:;" class="thumbnail btn_green" style="width: 100%;">
                    零钱:
                    <fmt:formatNumber type="number" value="${sessionScope.user.amount}" pattern="0.00" maxFractionDigits="2"/>
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6 col-md-6">
                <a href="/transaction/receipt" class="thumbnail btn_green" style="background-color:#0ac599;">
                    收款
                </a>
            </div>
            <%--<div class="col-xs-3 col-md-3">
                <a href="javascript:;" class="thumbnail">
                    转账
                </a>
            </div>--%>
            <div class="col-xs-6 col-md-6">
                <a href="/transaction/list/${sessionScope.user.id}" class="thumbnail btn_green">
                    交易记录
                </a>
            </div>
        </div>
    </div>
</body>
</html>