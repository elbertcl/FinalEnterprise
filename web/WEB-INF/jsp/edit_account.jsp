<%@include file="include/header.jsp" %>
<%@include file="include/navigation.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			
			if(invalid=="usernameexist"){
				$("#usernameexist").show();
			}
			else if(invalid=="oldpassword"){
				$("#oldpassword").show();
			}
			else if(invalid=="repeatpassword"){
				$("#repeatpassword").show();
			}
			else if(invalid=="success"){
				$("#success").show();
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

                    
                      <span class="section">Edit Admin Account ${test}</span>
					  <!--<p id="invalid-false" style="display: none; color: green;">The changes have been saved.</p>-->
                    <div id="success" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
                      <strong>The changes have been saved.</strong>
                    </div>
                    <div id="usernameexist" class="alert alert-danger alert-dismissible fade in" style="display:none;"> 
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
                      <strong>Invalid username: the username inputted already exists, or you have not made any changes to your username.</strong>
                    </div>
                    <div id="oldpassword" class="alert alert-danger alert-dismissible fade in" style="display:none;"> 
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
                      <strong>Invalid old password inputted, please enter the correct old password.</strong>
                    </div>
                    <div id="repeatpassword" class="alert alert-danger alert-dismissible fade in" style="display:none;"> 
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
                      <strong>The new password and repeated password is not the same, please try again.</strong>
                    </div>
                
                      
                      <div class="item form-group">
                          <c:forEach items="${hrmlist}" var="list" varStatus="status">
                            <img src="${pageContext.request.contextPath}/resources/images/${list.hrm_picture}" class="img-circle img-responsive" alt="avatar" style="margin: 0 auto;">                                           
                            </c:forEach>
                          </div><br>

					  
                        <form action="edit_account.htm" method="post" class="form-horizontal form-label-left">
                      
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Username 
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <c:forEach items="${hrmlist}" var="list" varStatus="status">
                                <input class="form-control col-md-7 col-xs-12"  name="hrm_username" value="${list.hrm_username}"  type="text" required >   
                                </c:forEach>
                        </div>
						
						<br><br>
						<div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
						<!--<p id="invalid-username" style="display: none; color: red;"><br>The username has already been taken.</p><br>-->
                                                <c:forEach items="${hrmlist}" var="list" varStatus="status">
                                                <input type="hidden" name="hrm_id" value="${list.hrm_id}">
                                                <input type="hidden" name="action" value="Submit New Username">
                                                </c:forEach>
                                                <br>
                                                <input type="submit" value="Submit New Username" class="btn btn-success">
                        </div>
                      </div><div class="ln_solid"></div>
                      </form>
                      
					  
                        <form action="edit_account.htm" method="post" class="form-horizontal form-label-left" >
                     
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Profile Picture 
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="file"  name="hrm_picture" style="margin-top:5px" required>
                        </div>
						
						<br><br>
						<div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
                            <c:forEach items="${hrmlist}" var="list" varStatus="status">
                            <input type="hidden" name="hrm_id" value="${list.hrm_id}">
                            </c:forEach>
                            <input type="hidden" name="action" value="Submit New Photo">
                          <br><input id="send" type="submit" value="Submit New Photo" class="btn btn-success">
                        </div>
                      </div>
					  <div class="ln_solid"></div>
                      </form>
                      
					  
					  
					  <form action="edit_account.htm" method="post" class="form-horizontal form-label-left" name="editAdminPassword" id="editAdminPassword-form">
<br>
					  <div>
                        <label for="password" class="control-label col-md-3">Old Password</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="password" name="oldpassword"  class="form-control col-md-7 col-xs-12" required>
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
						<!--<p id="invalid-oldpassword" style="display: none; color: red;"><br>The old password inputted is incorrect.</p>-->
						<!--<p id="invalid-repeatpassword" style="display: none; color: red;"><br>The password confirmation does not match.</p>-->
                                                <c:forEach items="${hrmlist}" var="list" varStatus="status">
                                                <input type="hidden" name="hrm_id" value="${list.hrm_id}">
                                                </c:forEach>
                                                <input type="hidden" name="action" value="Submit New Password">
                                                <br><input id="send" type="submit" value="Submit New Password" class="btn btn-success">
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