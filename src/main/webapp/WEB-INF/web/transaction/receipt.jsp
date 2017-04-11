<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>收款</title>
    <%@ include file="/WEB-INF/web/common/include.jsp"%>
</head>
<body>
    <div class="container-fluid">
        <h1 class="text-center">收款</h1>
        <p class="text-center">扫二维码向我付钱</p>
        <br/><br/>
        <div id="qrcodeDiv" class="text-center"></div>
        <br/><br/>
        <div>
            <ul class="list-group" id="listGroup">
            </ul>
        </div>
    </div>
    <script>
        $(function(){
            var date = new Date();
            $("#qrcodeDiv").qrcode({
                render   : "canvas",
                width: 116,
                height: 116,
                text: 'http://d1.oxchains.com/transaction/payment?toId=${sessionScope.user.id}&flag=${sessionScope.flag}'
            });
            $('#top_wx_div').show();

            var queryTransaction = function() {
                var url = '/transaction/after?to_id=${sessionScope.user.id}&date=' + date.getTime();
                $.ajax({
                    url: url,
                    type: 'GET',
                    timeout: 32000,
                    dataType: 'json',
                    success: function(data) {
                        if (data.status == 1) {
                            var list = data.data;
                            if (list) {
                                var html = '';
                                for (var i = 0; i < list.length; i++) {
                                    var item = list[i];
                                    var userName = item.fromUser.userName;
                                    var amount = item.money;
                                    html = html + '<li class="list-group-item">' + userName + '<span style="float:right;">￥' + amount + '</span></li>';
                                }
                                $('#listGroup').html(html);
                            }
                        }
                        queryTransaction();
                    }
                });
            }

            queryTransaction();
        });
    </script>
</body>
</html>