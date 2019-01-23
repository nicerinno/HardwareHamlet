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
		<title>HardwareHamlet</title>

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
            <script src="scripts.js"></script>
		</head>
		<body>	
			    <header id="header">
			    <div class="container main-menu">
			    	<div class="row align-items-center justify-content-between d-flex">
				      <div id="logo">
				        <a href="index.html"><img src="img/logo.png" alt="" title="" /></a>
				      </div>
				      <nav id="nav-menu-container">
				        <ul class="nav-menu">
				          <li><a href="index.html">Home</a>
				          <li class="menu-has-children"><a href="">Componentes</a>
				            <ul>
				              <li><a href="ver-componentes.html">Ver Componentes</a></li>
				              <li><a href="adicionar-componentes.php">Adicionar Componentes</a></li>
				            </ul>
				          </li>						          
				          <li><a class="menu-has-children" href="">Builds</a>
							<ul>
				              <li><a href="minhas-builds.html">Minhas Builds</a></li>	
				              <li><a href="criar-build.html">Criar Build</a></li>
				              <li> <a href="ver-builds.html">Ver Builds</a></li>
							</ul>
						  </li>
				          <li class="menu-has-children"><a href="">Rankings</a>
				            <ul>
								<li><a href="top-componentes.html">Top Components</a></li>
								<li><a href="top-builds.html">Top Builds</a></li>
				              	<li><a href="top-utilizadores.html">Top Utilizadores</a></li>
				            </ul>	
							
				        </ul>
				      </nav><!-- #nav-menu-container -->	
					    <div id="profile">
				        <a href="index.html"><img src="img/transferir.png" alt="" title="" /></a>
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
								Adicionar Componentes
							</h1>	
							<p class="text-white link-nav"><a href="index.html">Home </a>  <span class="lnr lnr-arrow-right"></span>  <a href="elements.html"> Adicionar Componentes</a></p>
						</div>	
					</div>
				</div>
			</section>
			<!-- End banner Area -->	

			<!-- Start Sample Area -->
                <?php
                include_once "Components.php";
                // define variables and set to empty values
                $nameErr = $descriptionErr = $typeErr = $dateErr = $iconErr = $brandErr = $priceErr = "";
                $name = $description = $type = $date = $icon = $brand = $price="";

                if ($_SERVER["REQUEST_METHOD"] == "POST") {
                    if (empty($_POST["name"])) {
                        $nameErr = "O nome é obrigatório!";
                    } else {
                        $name = test_input($_POST["name"]);

                    }

                    if (empty($_POST["description"])) {
                        $descriptionErr = "A descrição obrigatória!";
                    } else {
                        $description = test_input($_POST["description"]);

                    }

                    if (empty($_POST["type"])) {
                        $typeErr = "O tipo é obrigatório!";
                    } else {
                        $type = test_input($_POST["type"]);

                    }

                    if (empty($_POST["brand"])) {
                        $brandErr = "A marca é obrigatória";
                    } else {
                        $brand = test_input($_POST["brand"]);
                    }

                    if (empty($_POST["icon"])) {
                        $iconErr = "O icon é obrigatório";
                    } else {
                        $icon = $_POST["icon"];
                    }

                    if (empty($_POST["price"])) {
                        $priceErr = "O preço é obrigatório";
                    } else {
                        $price = test_input($_POST["price"]);
                    }

                    if (empty($_POST["description"])) {
                        $descriptionErr = "A descrição é obrigatória";
                    } else {
                        $description = test_input($_POST["description"]);
                    }


                }

                function test_input($data)
                {
                    $data = trim($data);
                    $data = stripslashes($data);
                    $data = htmlspecialchars($data);
                    return $data;
                }

                ?>
				<section class="">

                    <div class="container" style="background-color: #eeeeee;margin-top: 20px">


					    <form method="post" accept-charset="ISO-8859-1" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
                            <div style="margin-bottom: 10px">
                                <div class="form-group">
                                    <label>Nome do componente:</label>
                                    <span class="error">*<?php echo $nameErr; ?></span>
                                    <input class="form-control" type="text" name="name" placeholder="Insira o nome do componente" value="<?php echo $name; ?>">
                                </div>
                                <div class="form-group">
                                    <label>Marca do componente:</label>
                                    <span class="error">*<?php echo $brandErr; ?></span>
                                    <input class="form-control" type="text" name="brand" placeholder="Insira a marca do componente" value="<?php echo $brand; ?>">
                                </div>
                                <div class="form-group">
                                    <label>Tipo de componente:</label>
                                    <select class="form-control" name="components_type">
                                        <option value="1" name="type"<?php if (isset($type) && $type == "1") echo "checked"; ?> >Processador</option>
                                        <option value="2"  name="type" <?php if (isset($type) && $type == "2") echo "checked"; ?>>Placa Gráfica</option>
                                        <option value="3"  name="type" <?php if (isset($type) && $type == "3") echo "checked"; ?>>Motherboard</option>
                                        <option value="4"  name="type" <?php if (isset($type) && $type == "4") echo "checked"; ?>>Ventoinha</option>
                                        <option value="5"  name="type" <?php if (isset($type) && $type == "5") echo "checked"; ?>>Cooler CPU</option>
                                        <option value="6"  name="type" <?php if (isset($type) && $type == "6") echo "checked"; ?>>Fonte de Alimentação</option>
                                        <option value="7"  name="type" <?php if (isset($type) && $type == "7") echo "checked"; ?>>Disco SSD</option>
                                        <option value="8"  name="type" <?php if (isset($type) && $type == "8") echo "checked"; ?>>Disco HDD</option>
                                        <option value="9"  name="type" <?php if (isset($type) && $type == "9") echo "checked"; ?>>Disco SSHD</option>
                                        <option value="10"  name="type"<?php if (isset($type) && $type == "10") echo "checked"; ?>>Memória RAM</option>
                                        <option value="11"  name="type"<?php if (isset($type) && $type == "11") echo "checked"; ?>>Caixa</option>
                                    </select>
                                </div>
                                <div class=" form-group">
                                    <label>Preço do componente em € :</label>
                                    <span class="error">*<?php echo $priceErr; ?></span>
                                    <input class="form-control" type="number" name="price" placeholder="000" value="<?php echo $price; ?>">
                                </div>
                                <div class=" form-group" >
                                    <label>Descrição:</label>
                                    <span class="error">* <?php echo $descriptionErr; ?></span>
                                    <textarea  cols="35" rows="8" class="form-control text"  name="description"><?php echo $description; ?></textarea>
                                </div>
                                <div class=" form-group" >
                                    <label>URL imagem:</label>
                                    <span class="error">* <?php echo $iconErr; ?></span>
                                    <input class="form-control" type="text" name="icon" placeholder="https://" value="<?php echo $icon; ?>">

                                </div>
                                 <p><span class="error">* Preenchimento obrigatório</span></p>

                                 <input style="margin:auto; display: block" class="btn btn-primary" type="submit" name="submit" value="Submeter">
                                 <br>
                         </div>

                        </form>

                        <?php

                        if(isset($_POST['submit'])){
                            $type = $_POST['components_type'];

                        }


                        $milliseconds = round(microtime(true) * 1000);


                        if( isset($_POST['submit']) && $nameErr = $descriptionErr = $brandErr = $priceErr = $iconErr = "") {
                            $nuser = new Components($type, 1, $brand, $name, $description, $price,1, $icon, $milliseconds);
                            $result = $nuser->addComponent();
                            echo "<br>";
                            if($result)
                                echo "Registrado com sucesso!";
                            else
                                echo "Erro no registo: ", $nuser->conn->error,".";


                        }
                        ?>
                    </div>



                </div>

            </div>


				</section>
			<!-- End Sample Area -->




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
		</body>
	</html>