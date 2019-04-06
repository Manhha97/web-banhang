<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="msapplication-tap-highlight" content="no">
        <meta name="description" content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
        <meta name="keywords" content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
        <title>Đăng nhập tài khoản</title>
        <!-- Favicons-->
        <link rel="icon" href="images/favicon-32x32.png" sizes="32x32">
        <!-- Favicons-->
        <link rel="apple-touch-icon-precomposed" href="images/apple-touch-icon-152x152.png">
        <!-- For iPhone -->
        <meta name="msapplication-TileColor" content="#00bcd4">
        <meta name="msapplication-TileImage" content="images/mstile-144x144.png">
    <!-- /meta tags -->
    <!-- custom style sheet -->
    <link href="css//materialize.css" type="text/css" rel="stylesheet">
    <link href="css//login.css" rel="stylesheet" type="text/css" />
    <!-- /custom style sheet -->
    <!-- fontawesome css -->
    <link rel="stylesheet" type="text/css" href="../vendors/font-awesome-4.6.3/css/font-awesome.css">
    <!-- /fontawesome css -->
    

</head>


<body style="background:url(images/cover4.jpg) no-repeat 0px 0px">
    <h1>Admin Login Form</h1>
    <div class=" w3l-login-form">
        <h2>Login Here</h2>
        <form >

            <div class=" w3l-form-group">
                <label>Username:</label>
                <div class="group">
                    <i class="fa fa-user"></i>
                    <input type="text" class="form-control" id="name" placeholder="Username" required="required"  name="email"/>
                </div>
            </div>
            <div class=" w3l-form-group">
                <label>Password:</label>
                <div class="group">
                    <i class="fa fa-unlock"></i>
                    <input type="password" class="form-control" id="password"  placeholder="Password" required="required" name="password" />
                </div>
            </div>
            <div class="forgot">
                <a href="#">Forgot Password?</a>
                <p><input type="checkbox">Remember Me</p>
            </div>
            <button type="submit" id="login" value="submit">Login</button>
        </form>
        
    </div>
    <footer>
        <p class="copyright-agileinfo"> &copy; 2018 Admin Login Form. All Rights Reserved  </p>
    </footer>

</body>

</html>
<script type="text/javascript" src="../vendors/jquery/js/jquery.js"></script>
<script type="text/javascript" src="../vendors/jquery/js/jquery-validate.js"></script>
<script type="text/javascript" src="vendors/materialize.js"></script>
<!--scrollbar-->
    <script type="text/javascript" src="vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="vendors/plugins.js"></script>
<script type="text/javascript" src="js/login.js"></script>