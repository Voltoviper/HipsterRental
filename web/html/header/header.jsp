<%@ page import="wak.system.server.Seitenaufbau" %>
<%@page import="wak.system.server.Login" %>
<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
    <title>Hipster Rental</title>
    <!-- Bootstrap -->
    <link href="./html/CSS/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <link href="./html/CSS/bootstrap.css" rel='stylesheet' type='text/css' />
    <link rel="shortcut icon" href="./img/icon.ico" type="image/x-icon" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.web/js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--  webfonts  -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
    <!-- // webfonts  -->
    <link href="./html/CSS/style.css" rel="stylesheet" type="text/css" media="all" />
    <!-- start plugins -->
    <script type="text/javascript" src="./html/js/jquery.min.js"></script>
    <script type="text/javascript" src="./html/js/bootstrap.js"></script>
    <script type="text/javascript" src="./html/js/datetimepicker_css.js"></script>
    <script>
        $(document).ready(function(){
            $(".dropdown").hover(
                    function() {
                        $('.dropdown-menu', this).stop( true, true ).slideDown("fast");
                        $(this).toggleClass('open');
                    },
                    function() {
                        $('.dropdown-menu', this).stop( true, true ).slideUp("fast");
                        $(this).toggleClass('open');
                    }
            );
        });
    </script>
</head>
<body>
<div class="header_bg"><!-- start header -->
    <div class="container">
        <div class="row header">
            <nav class="navbar" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="index.jsp"><img src="./img/logo.png" alt="" class="img-responsive"/> </a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="menu nav navbar-nav ">
                            <li class="active"><a href="index.jsp">Home</a></li>
                           <!--
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Features<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="feature.html">Features 1</a></li>
                                    <li><a href="feature.html">Features 2</a></li>
                                    <li><a href="feature.html">Features 3</a></li>
                                </ul>
                            </li>
                            -->
                            <% Seitenaufbau.getMenu(out, request.getCookies());%>
                        </ul>
                        <div class="navbar-form navbar-right">
                        <%Login.getLogin(out,request.getCookies());%>
                        </div>
                        <!--
                        <form class="navbar-form navbar-right" role="search">
                            <div class="form-group my_search">
                                <input type="text" class="form-control" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-default">Search</button>
                        </form>
                        -->
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </div>