<%--
  Created by IntelliJ IDEA.
  User: HuyChu
  Date: 11/29/2018
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>fie</title>
</head>
<body>
<div class="container-fluid" >
    <div class="row" style="margin-top: 50px;">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">Import file</h2>
            <form id="code" method="post" enctype="multipart/form-data;charset=UTF-8" class="form-horizontal" acceptcharset="UTF-8">
                <div class="input-group">
                    <input id="file-upload" type="file" name="file-upload">
                </div>
                <div class="input-group">
                    <input id="file-upload1" type="text" name="name">
                </div>
            </form><br>
            <button type="button" id="btn-import" class="btn btn-default">Upload</button>
            </tbody>
            </table>
        </div>
    </div>
</div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#btn-import").click(function() {
            var file = $('#file-upload').get()[0].files[0];
            var fileUpload = $('#file-upload').val();
            if(!file){
                alert("Vui lòng chọn file");
                return;
            }

            $.ajax({
                url: '/upload',
                type: 'POST',
                data: new FormData($('#code')[0]),
                processData: false,
                contentType: false
            }).done(function (res) {
                alert(res);
            });
        });
    });
</script>
</html>