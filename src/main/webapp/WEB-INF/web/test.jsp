<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <input type="file" id="myFile" multiple />
    <input type="button" value="submit" onclick="upload()" />
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous">
    </script>
    <script>
        function upload() {
            var xmlhttp;
            if (window.XMLHttpRequest) {
                xmlhttp = new XMLHttpRequest();
            } else {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            var myFile = document.getElementById('myFile');
            var formData = new FormData();
            for (var i = 0; i < myFile.files.length; i++) {
                formData.append('file', myFile.files[i]);
            }
            //formData.append('file', myFile.files[0]);
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var result = xmlhttp.responseText;
                    console.log('result: ' + result);
                }
            };
            xmlhttp.open('POST', '/upload', true);
            xmlhttp.send(formData);
        }
    </script>
</body>
</html>
