	<!-- Custom Theme Style -->
	<link href="${pageContext.request.contextPath}/resources/build/css/custom.min.css" rel="stylesheet">
	
</head>

<body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="manage_hrm_account.htm" class="site_title"><img src="${pageContext.request.contextPath}/resources/images/slavHRwhite.png" alt="logo"> <span>HRM App</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile">
              <div class="profile_pic">
<!--                <img src="<?php 
									if(isset($_SESSION["photoAdmin"])){
										if($_SESSION["photoAdmin"] != ""){
											echo "images/".$_SESSION['photoAdmin'];
										} 
										else
										{
											echo "images/defaultadmin.png";
										}
									}
									?>" alt="..." class="img-circle profile_img">-->
                    <img src="${pageContext.request.contextPath}/resources/images/defaultadmin.png" alt="..." class="img-circle profile_img">                                                     
              </div>
              <div class="profile_info">
<!--                <span>Welcome,</span>
                <h2><?php 
									if(isset($_SESSION["usernameAdmin"])){
										echo $_SESSION['usernameAdmin'];
										
									} 
									?></h2>-->
                     <span>Welcome,</span>
                     <h2> <%= session.getAttribute("currentAdmin_username") %></h2>                                                    
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
              <br>
              <br>
              <br>
              <br>
                <h3>Admin</h3>
                <!-- <br> -->
                <ul class="nav side-menu">
                  <li><a href="manage_hrm_account.htm"><i class="fa fa-key"></i> Manage HRM Accounts </a></li>
                  <li><a href="login_log.htm"><i class="fa fa-sign-in"></i> Login Log </a></li>
                  <li><a href="activity_log.htm"><i class="fa fa-hourglass-half"></i> Activity Log </a></li>
                </ul>
              </div>
            </div>
            <!-- /sidebar menu -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="logout_admin.htm">Log Out</a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->