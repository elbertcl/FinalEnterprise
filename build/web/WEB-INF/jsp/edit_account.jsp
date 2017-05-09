<%@include file="include/header.jsp" %>
<%@include file="include/navigation.jsp" %>

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
			$("#editAdminUsername-for").submit(function(e){
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
			
			if(invalid=="invalid-username"){
				$("#invalid-username").show();
			}
			else if(invalid=="invalid-oldpassword"){
				$("#invalid-oldpassword").show();
			}
			else if(invalid=="invalid-repeatpassword"){
				$("#invalid-repeatpassword").show();
			}
			else if(invalid=="false"){
				$("#invalid-false").show();
			}
        });
     </script>

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="clearfix"></div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_content">

                    
                      <span class="section">Edit Admin Account</span>
					  <p id="invalid-false" style="display: none; color: green;">The changes have been saved.</p>

                      <div class="item form-group">
                        <!-- <div class="col-md-4 col-sm-4 col-xs-12"></div> -->
<!--                        <img src="<?php 
									if(isset($_SESSION["photoAdmin"])){
										if($_SESSION["photoAdmin"] != ""){
											echo "images/".$_SESSION['photoAdmin'];
										} 
										else
										{
											echo "images/defaultadmin.png";
										}
									}
									?>" class="img-circle img-responsive" alt="avatar" style="margin: 0 auto;">-->
                            <img src="${pageContext.request.contextPath}/resources/images/defaultadmin.png" class="img-circle img-responsive" alt="avatar" style="margin: 0 auto;">                                           
                      </div><br>

					  
					<form action="controller/editAdminUsername.php" method="post" class="form-horizontal form-label-left" name="editAdminUsername" id="editAdminUsername-form">
                      
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Username 
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
<!--                          <input id="name" class="form-control col-md-7 col-xs-12"  name="newusername" value="<?php 
									
											echo $_SESSION['usernameAdmin'];
										
									?>"  type="text" required >-->
                                <input id="name" class="form-control col-md-7 col-xs-12"  name="newusername" value="elbertcl"  type="text" required >                                        
                        </div>
						
						<br><br>
						<div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
						<p id="invalid-username" style="display: none; color: red;"><br>The username has already been taken.</p><br>
                          <button id="send" type="submit" class="btn btn-success">Submit New Username</button>
                        </div>
                      </div><div class="ln_solid"></div>
                      </form>
                      
					  
					  <form action="controller/editAdminPhoto.php" method="post" class="form-horizontal form-label-left" name="editAdminPhoto" id="editAdminPhoto-form" enctype="multipart/form-data">
                     
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Profile Picture 
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="file"  name="newphoto" style="margin-top:5px" required>
                        </div>
						
						<br><br>
						<div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
                          <br><button id="send" type="submit" class="btn btn-success">Submit New Photo</button>
                        </div>
                      </div>
					  <div class="ln_solid"></div>
                      </form>
                      
					  
					  
					  <form action="controller/editAdminPassword.php" method="post" class="form-horizontal form-label-left" name="editAdminPassword" id="editAdminPassword-form">
                      <!-- <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="email" id="email" name="email" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div> --><br>
					  <div>
                        <label for="password" class="control-label col-md-3">Old Password</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="password" name="oldpassword" class="form-control col-md-7 col-xs-12" required>
                        </div>
                      </div><br><br>
                      <div>
                        <label for="password" class="control-label col-md-3">Password</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="password" name="newpassword" class="form-control col-md-7 col-xs-12" required>
                        </div>
                      </div><br><br>
                      <div>
                        <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Repeat Password</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input  type="password" name="repeatpassword" class="form-control col-md-7 col-xs-12"  required>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
						<p id="invalid-oldpassword" style="display: none; color: red;"><br>The old password inputted is incorrect.</p>
						<p id="invalid-repeatpassword" style="display: none; color: red;"><br>The password confirmation does not match.</p>
                         <br><button id="send" type="submit" class="btn btn-success">Submit New Password</button>
                        </div>
                      </div><div class="ln_solid"></div>
                    </form>
					
					
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->

        <%@include file="include/footer.jsp" %>

    <!-- validator -->
    <script src="${pageContext.request.contextPath}/resources/vendors/validator/validator.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/build/js/custom.min.js"></script>

    <!-- validator -->
    <script>
      // initialize the validator function
      validator.message.date = 'not a real date';

      // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
      $('form')
        .on('blur', 'input[required], input.optional, select.required', validator.checkField)
        .on('change', 'select.required', validator.checkField)
        .on('keypress', 'input[required][pattern]', validator.keypress);

      $('.multi.required').on('keyup blur', 'input', function() {
        validator.checkField.apply($(this).siblings().last()[0]);
      });

      $('form').submit(function(e) {
        e.preventDefault();
        var submit = true;

        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
          submit = false;
        }

        if (submit)
          this.submit();

        return false;
      });
    </script>
    <!-- /validator -->
  </body>
</html>