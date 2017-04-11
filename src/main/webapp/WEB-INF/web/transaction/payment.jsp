<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>转账</title>
    <%@ include file="/WEB-INF/web/common/include.jsp"%>
    <style>
        .btn_green{
            display: block;
            width: 100%;
            font-size: 1.4em;
            color: #fff;
            text-align: center;
            background: #0ac599;
            -webkit-box-shadow: 0 2px 0 #008765;
            -moz-box-shadow: 0 2px 0 #008765;
            box-shadow: 0 2px 0 #008765;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            border-radius: 5px;
            margin: 20px 0;
            padding: 2%;
        }
    </style>
    <link rel="stylesheet" href="/css/boxy.css">
    <link rel="stylesheet" href="/css/payment.css">
    <script src="/js/jquery.boxy.js"></script>
</head>
<body>
    <div class="container-fluid" id="container1" style="position: fixed; z-index: 1;">
        <h1 class="text-center">转账</h1>
        <p class="text-center">向 ${requestScope.toUser.userName} 转账</p>
        <div class="text-center">
            <div class="input-group">
                <span class="input-group-addon">￥</span>
                <input type="number" class="form-control" id="money">
            </div>
            <input type="button" class="btn_green" value="转账" id="moneyBtn">
        </div>
    </div>

    <div class="container-fluid hidden" id="container2" style="position: relative; z-index: 1;">
        <p class="text-center"><img src="/images/success.png"></p>
        <p class="text-center" id="paymentResult"></p>
        <p class="text-center">收款人:${requestScope.toUser.userName}</p>
        <input type="button" class="btn_green" value="完成" id="doneBtn">
    </div>

    <div id="diag1" class="full hidden">
        <div class="diag">
            <a href="javascript:closeDiag1();"><img src="/images/payment-close.png" WIDTH="20"></a>
            <p class="payTitle">支付</p>
            <em></em>
            <p>向用户“${requestScope.toUser.userName}”转账</p>
            <h5 id="diagMoney"></h5>
            <em></em>
            <a class="pay" id="payBtn">确认支付</a>
        </div>
    </div>

    <div id="diag2" class="full hidden">
        <div class="diag">
            <a href="javascript:closeDiag2();"><img src="/images/payment-close.png" WIDTH="20"></a>
            <p class="payTitle"><img src="/images/touchid.png" width="35%"/></p>
            <em></em>
            <p>请验证已有的指纹用于支付</p>
            <h5 id="diagMoney"></h5>
            <em></em>
            <a class="pay" id="sendBtn">确认</a>
        </div>
    </div>

    <div id="diag3" class="full hidden">
        <div class="diag" style="text-align: center;">
            <a href="javascript:closeDiag3();"><img src="/images/payment-close.png" WIDTH="20"></a>
            <p class="payTitle">安全提示</p>
            <em></em>
            <p>系统检测到支付活动异常，请确认您的支付环境是否安全。</p>
            <h5 id="diagMoney"></h5>
            <em></em>
            <div style="margin: 0 auto;width: 140px;">
                <a class="pay" style="width: 60px;display: inline-block;" id="diag3SendBtn">确认支付</a>
                <a class="pay" style="width: 60px;display: inline-block;" id="diag3CancelBtn">取消支付</a>
            </div>
        </div>
    </div>

    <div id="diag4" class="full hidden">
        <div class="diag">
            <a href="javascript:closeDiag4();"><img src="/images/payment-close.png" WIDTH="20"></a>
            <p class="payTitle">安全提示</p>
            <em></em>
            <p>系统检测到支付活动异常，我们的工作人员将通过电话联系您。</p>
            <h5 id="diagMoney"></h5>
            <em></em>
            <div class="text-center">
                <a class="pay" id="diag4Btn">关闭</a>
            </div>
        </div>
    </div>


    <script>
        $(function() {
            var fromId = '${requestScope.fromId}';
            var toId = '${requestScope.toUser.id}';
            var totalmoney = null;
            var flag = '${requestScope.flag}';
            $('#moneyBtn').click(function() {
                var money = $('#money').val();
                if (money == '' || isNaN(money)) {
                    alert('金额不能为空且必须是数字！');
                    return;
                }
                totalmoney = money;
                $('#diagMoney').html('￥' + money);
                if (flag == '1') {
                    $('#diag3').removeClass('hidden');
                    return;
                }
                if (flag == 2) {
                    $('#diag4').removeClass('hidden');
                    return;
                }
                $('#diag1').removeClass('hidden');
            });

            $('#payBtn').click(function() {
                $('#diag2').removeClass('hidden');
                $('#diag1').addClass('hidden');
            })

            $('#sendBtn').click(function() {
               sendPayment();
            });

            $('#cancelBtn').click(function() {
                $('#diag2').addClass('hidden');
            });

            $('#doneBtn').click(function() {
                location.href = '/user/' + fromId;
            });

            $('#diag3SendBtn').click(function() {
                $('#diag3').addClass('hidden');
                $('#diag1').removeClass('hidden');
            });

            $('#diag3CancelBtn').click(function() {
                $('#diag3').addClass('hidden');
            });

            $('#diag4Btn').click(function() {
                $('#diag4').addClass('hidden');
            });

            var sendPayment = function() {
                $.ajax({
                    url: '/transaction/create',
                    type: 'POST',
                    data: {fromId: fromId, toId: toId, money: totalmoney},
                    dataType: 'json',
                    success: function(data) {
                        if (data.status == 1) {
                            $('#container1').addClass('hidden');
                            $('#container2').removeClass('hidden');
                            $('#diag2').addClass('hidden');
                            $('#paymentResult').html("￥" + totalmoney);
                        } else {
                            alert(data.message);
                        }
                    }
                });
            }
        });
        function closeDiag1() {
            $('#diag1').addClass('hidden');
        }
        function closeDiag2() {
            $('#diag2').addClass('hidden');
        }
        function closeDiag3() {
            $('#diag3').addClass('hidden');
        }
        function closeDiag4() {
            $('#diag4').addClass('hidden');
        }
    </script>
</body>
</html>