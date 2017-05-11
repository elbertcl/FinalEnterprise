<!-- <?php ?> 
start session -->

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>HRM Login</title>

	<link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" type="image/x-icon">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/resources/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/resources/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="${pageContext.request.contextPath}/resources/vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/resources/build/css/custom.min.css" rel="stylesheet">
    <script type="text/javascript">
        $(document).ready(function() {
			<!-- Block of codes for getUrlParameter function -->
			var getUrlParameter = function getUrlParameter(sParam) {
				var sPageURL = decodeURIComponent(window.location.search.substring(1)),
					sURLVariables = sPageURL.split('&'),
					sParameterName,
					i;

				for (i = 0; i < sURLVariables.length; i++) {
					sParameterName = sURLVariables[i].split('=');

					if (sParameterName[0] === sParam) {
						return sParameterName[1] === undefined ? true : sParameterName[1];
					}
				}
			};
			
            $(".dropdown img.flag").addClass("flagvisibility");

            $(".dropdown dt a").click(function() {
                $(".dropdown dd ul").toggle();
            });
                        
            $(".dropdown dd ul li a").click(function() {
                var text = $(this).html();
                $(".dropdown dt a span").html(text);
                $(".dropdown dd ul").hide();
                $("#result").html("Selected value is: " + getSelectedValue("sample"));
            });
                        
            function getSelectedValue(id) {
                return $("#" + id).find("dt a span.value").html();
            }

            $(document).bind('click', function(e) {
                var $clicked = $(e.target);
                if (! $clicked.parents().hasClass("dropdown"))
                    $(".dropdown dd ul").hide();
            });


            $("#flagSwitcher").click(function() {
                $(".dropdown img.flag").toggleClass("flagvisibility");
            });
			
			<!-- Input validation -->
			$("#loginAdmin-form").submit(function(e){
				e.preventDefault();
				var valid=true;
				$("input",this).each(function(){
					if($(this).attr("type")!="submit"){
						if($(this).val()==""){
							valid=false;
						}
					}
				});
				if(valid){
					$(this).off("submit");
					$(this).submit();
				} else {
					$("#invalid-input").show();
				}
			});
			<!-- End of Input validation  -->
			
			<!-- Using the getUrlParameter function to help show the invalid input message -->
			var invalid = getUrlParameter('invalid');
			
			if(invalid=="true"){
				$("#invalid-input").show();
			}
        });
     </script>
	
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form action="login_hrm.htm" method="post" id="loginAdmin-form"><br><br>
				<center><img src="${pageContext.request.contextPath}/resources/images/LogoLogin.png" style="width:100%"></center><br>
				
				  <h1>HRM Login ${message}</h1>
                                  <p>Please login using your HR Manager credentials.</p><br>
				  <div>
					<input type="text" class="form-control" placeholder="Username"  name="username" required />
				  </div>
				  <div>
					<input type="password" class="form-control" placeholder="Password" name="password" required/>
				  </div>
				  <div>
				  <p id="invalid-input" style="display: none; color: red;">Your username/password is invalid!</p><br>
					<input type="submit" value="Log in" style="margin-left: 148px;"><br>
			
              </div>

              <br><div class="clearfix"></div>

              <div class="separator">

                <div class="clearfix"></div>
                <br />

                <div>
                  <p>©2017 by Elbert, Jeremy, Matthew, & Williams</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form>
              <h1>Create Account</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username" required />
              </div>
              <div>
                <input type="email" class="form-control" placeholder="Email" required/>
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required/>
              </div>
              <div>
                <input type="submit" class="btn btn-default submit" href="index.html">Submit</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Already a member ?
                  <a href="#signin" class="to_register"> Log in </a>
                </p>

                <div class="clearfix"></div>
                <br />
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
