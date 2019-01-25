<!DOCTYPE html>
<html lang="zxx" class="no-js">
<head>
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/fav.png">
    <!-- Author Meta -->
    <meta name="author" content="codepixer">
    <!-- Meta Description -->
    <meta name="description" content="">
    <!-- Meta Keyword -->
    <meta name="keywords" content="">
    <!-- meta character set -->
    <meta charset="UTF-8">
    <!-- Site Title -->
    <title>Robotics</title>

    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700" rel="stylesheet">
    <!--
    CSS
    ============================================= -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/hexagons.min.css">
    <link rel="stylesheet" href="css/animate.min.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body background="img/background.png" style="background-attachment: fixed">
<header id="header">
    <div class="container main-menu">
        <div class="row align-items-center justify-content-between d-flex">
            <div id="logo">
                <a href="index.php"><img src="img/icon.png" height="120" width="180" alt="" title="" /></a>
            </div>
            <nav id="nav-menu-container">
                <ul class="nav-menu">
                    <li><a href="index.php">Home</a>
                    <li class="menu-has-children"><a href="">Componentes</a>
                        <ul>
                            <li><a href="ver-componentes.php">Ver Componentes</a></li>
                            <li><a href="adicionar-componentes.php">Adicionar Componentes</a></li>
                        </ul>
                    </li>
                    <li><a class="menu-has-children" href="">Builds</a>
                        <ul>
                            <li><a href="minhas-builds.php">Minhas Builds</a></li>
                            <li><a href="criar-build.php">Criar Build</a></li>
                            <li> <a href="ver-builds.php">Ver Builds</a></li>
                        </ul>
                    </li>
                    <li class="menu-has-children"><a href="">Rankings</a>
                        <ul>
                            <li><a href="top-componentes.php">Top Components</a></li>
                            <li><a href="top-builds.php">Top Builds</a></li>
                            <li><a href="top-utilizadores.php">Top Utilizadores</a></li>
                        </ul>

                </ul>
            </nav><!-- #nav-menu-container -->
            <div id="profile">
                <?php
                include_once "Users.php";
                $session = new Users("","","","","","","","","","","");
                session_start();
                if ($session->check_session()){

                    $username = $_SESSION['email'];
                    echo'<h5 style="color: #eeeeee"> ' . $username . '</h5>
                                 <h6 style="color: #eeeeee"><a href="?logout" onclick="javascript:window.location.reload();">Logout</a>';
                    if (isset($_GET['logout'])) $session->close_session();
                }else{
                    echo'<h6 style="color: #eeeeee"><a href="regist.php">Registo</a>/<a href="login.php">Login</a></h6>';
                }

                ?>
            </div>

        </div>

    </div>
</header><!-- #header -->


<!-- start banner Area -->
<section class="banner-area relative" id="home">
    <div class="overlay overlay-bg"></div>
    <div class="container">
        <div class="row d-flex align-items-center justify-content-center">
            <div class="about-content col-lg-12">
                <h1 class="text-white">
                    Top Componentes
                </h1>
                <p class="text-white link-nav"><a href="index.php">Home </a>  <span class="lnr lnr-arrow-right"></span>  <a href="blog-single.html"> Top Componentes</a></p>
            </div>
        </div>
    </div>
</section>
<!-- End banner Area -->



<!--<div class="container" style="background-color: #eeeeee;margin-top: 20px; margin-bottom: 20px">-->
<!---->
<!---->
<!--    <div  class="align-content-center">-->
<!---->
<!--        --><?php
//
//        include_once "Components.php";
//
//
//        $nc = new Components("", "", "", "", "","", "", "", "");
//        $result = $nc->query_top_components();
//
//        echo "<br>";
//
//        if ($result != null) {
//            // output data of each row
//            echo "<table class='table table-striped' style='border:2px solid; border-collapse: collapse'>";
//            echo "<thead>";
//            echo "<tr>
//								<th name='name' scope='col' style='border:4px solid; padding:10px;'>Nome</th>
//								<th name='price' scope='col' style='border:4px solid; padding:10px;'>Preço</th>
//								<th name='icon_url' scope='col' style='border:4px solid; padding:10px;'>Imagem</th>
//							</tr>
//							</thead>
//							<tbody style=''>";
//
//            while ($row = $result->fetch_assoc()) {
//                $id = $row['component_id'];
//
//                $component = $nc->query_component_id($id);
////                $newComponent = new Components( $component['component_type_id'], $component['user_id'], $component['brand']
////                    , $component['name'], $component['description'], $component['price'], $component['flg_available'], $component['icon_url'], $component['regist_date']);
////
//
//                echo "<tr>",
//                "<td style='border:4px solid; padding:10px'>",$component['name'],"</td>",
//                "<td style='border:4px solid; padding:10px;'>",$component['price'],"</td>",
//                "<td style='border:4px solid; padding:10px;'><img src=",$component['icon_url']," alt=",$component['icon_url']," </img></td>",
//
//                "</tr>";
//
//            }
//            echo  "</tbody></table>";
//            echo "<br>";
//
//
//        }
//
//        ?>
<!--    </div>-->
<!--</div>-->
<!---->
<!--</section>-->


<!-- start footer Area -->
<footer class="footer-area section-gap ">
    <div class="container">


        <div class="row footer-bottom d-flex justify-content-between">
            <p class="col-lg-8 col-sm-12 footer-text m-0 text-white"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
            <div class="col-lg-4 col-sm-12 footer-social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-dribbble"></i></a>
                <a href="#"><i class="fa fa-behance"></i></a>
            </div>
        </div>
    </div>
</footer>
<!-- End footer Area -->

<script src="js/vendor/jquery-2.2.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="js/vendor/bootstrap.min.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
<script src="js/easing.min.js"></script>
<script src="js/hoverIntent.js"></script>
<script src="js/superfish.min.js"></script>
<script src="js/jquery.ajaxchimp.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/hexagons.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery.counterup.min.js"></script>
<script src="js/waypoints.min.js"></script>
<script src="js/mail-script.js"></script>
<script src="js/main.js"></script>
<script src="scripts.js"></script>
</body>
</html>



