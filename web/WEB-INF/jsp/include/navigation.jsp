	<!-- Custom Theme Style -->
	<link href="${pageContext.request.contextPath}/resources/build/css/custom.min.css" rel="stylesheet">
	
</head>

<body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.htm" class="site_title"><img src="${pageContext.request.contextPath}/resources/images/slavHRwhite.png" alt="logo"> <span>HRM App</span></a>
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
                    <img src="${pageContext.request.contextPath}/resources/images/<%= session.getAttribute("currentHRM_picture") %>" alt="..." class="img-circle profile_img">                                                     
              </div>
              <div class="profile_info">
<!--                <span>Welcome,</span>
                <h2><?php 
									if(isset($_SESSION["usernameAdmin"])){
										echo $_SESSION['usernameAdmin'];
										
									} 
									?></h2>-->
                     <span>Welcome,</span>
                     <h2> <%= session.getAttribute("currentHRM_name") %></h2>                                                    
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
                <h3>HR Management</h3>
                <!-- <br> -->
                <ul class="nav side-menu">
                  <li><a href="index.htm"><i class="fa fa-area-chart"></i> Dashboard </a>
<!--                    <ul class="nav child_menu">
                      <li><a href="index.htm">Dashboard</a></li>
                    </ul>-->
                  </li>
                  <li><a><i class="fa fa-suitcase"></i> Recruitment <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="list_of_applicants.htm">List of Applicants</a></li>
                      <li><a href="manage_test_questions.htm">Manage Test Questions</a></li>
                      <li><a href="manage_interview_time.htm">Manage Interview Time</a></li>
                      <li><a href="set_recruitment_result.htm">Set Recruitment Result</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-group"></i> Rostering <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="manage_employee.htm">Manage Employee</a></li>
                      <li><a href="manage_position.htm">Manage Company's Positions</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-graduation-cap"></i> Training <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="manage_training.htm">Manage Training</a></li>
                      <li><a href="training_assignment.htm">Employees Training Assignment</a></li>
                      <li><a href="employee_on_training.htm">Employees on Training</a></li>
                      <li><a href="training_history.htm">Employees Training History</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-gift"></i> Benefits <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="employee_benefit.htm">View Employee's Benefit</a></li>
                      <li><a href="manage_position_benefit.htm">Manage Position's Benefits</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-user-times"></i> Dismissal <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="employee_dismissal.htm">Employee Dismissal</a></li>
                      <li><a href="dismissal_list.htm">Dismissal List</a></li>
                      <li><a href="manage_dismissal_fee.htm">Manage Position's Dismissal Fee</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
              <div class="menu_section">
                <h3>My Account</h3>
                <ul class="nav side-menu">
                  <li><a href="edit_account.htm"><i class="fa fa-user"></i> Edit Account </a>
<!--                    <ul class="nav child_menu">
                      <li><a href="edit_account.php">Edit Account</a></li>
                    </ul>-->
                  </li>
<!--                  <li><a><i class="fa fa-envelope"></i> Messages <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="manage_message.php">Manage Messages</a></li>
                    </ul>
                  </li>                 -->
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
                  <a href="logout_hrm.htm">Log Out</a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->