<%@include file="include/header_admin.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- iCheck -->
    <link href="${pageContext.request.contextPath}/resources/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
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
      
      if(invalid=="addsuccess"){
        $("#addsuccess").show();
      }
      else if(invalid=="updatesuccess"){
        $("#updatesuccess").show();
      }
      else if(invalid=="deletesuccess"){
        $("#deletesuccess").show();
      }
      else if(invalid=="usernameexist"){
        $("#usernameexist").show();
      }
        });
        
        function deleteConfirm() {
            return confirm('Are you sure you want to delete the HRM account?');
        }
     </script>
 
  <%@include file="include/navigation_admin.jsp" %>

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Manage HRM Accounts</h3>
              </div>
              <div class="title_right">
                  <button class='btn btn-primary' data-toggle='modal' data-target='#addHRMModal' style='float:right'>New HRM Account</button>
                
                <!-- Add Position Modal -->
                      <div id="addHRMModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">

<!--                           Modal content-->
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title">Add New HRM Account</h4>
                            </div>

                            <div class="modal-body">
                              <div class="form-horizontal">
                              <form action="manage_hrm_account.htm" method="post" class="contact_form"> 
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">Name:</label>
                                      <div class="col-sm-9">
                                          <input type='text' name='hrm_name' class='form-control' required>
                                      </div>
                                  </div>
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">Username:</label>
                                      <div class="col-sm-9">
                                          <input type='text' name='hrm_username' class='form-control' required>
                                      </div>
                                  </div>
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">Password:</label>
                                      <div class="col-sm-9">
                                          <input type='password' name='hrm_password' class='form-control' required>
                                      </div>
                                  </div>
                              </div>
                            </div>
                            <div class="modal-footer">
<!--                              <input type="submit" value="Dismiss" style="align: center;" class="btn btn-danger">-->
                                    <input type="submit" name="action" value="Add"  class='btn btn-success'>
                              </form>
                            </div>
                          </div>

                        </div>
                      </div>
                
              </div>
            </div>

            <div class="clearfix"></div>
            <div id="addsuccess" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The HRM account has been successfully added.</strong>
            </div>
            <div id="updatesuccess" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The HRM account has been successfully updated.</strong>
            </div>
            <div id="deletesuccess" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The HRM account has been successfully deleted.</strong>
            </div>
            <div id="usernameexist" class="alert alert-danger alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The inputted HRM username already exists, or you have not made any changes to the username.</strong>
            </div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
<!--                   <div class="x_title">
                    <h2>List of HRM Accounts</h2>
                    <div class="clearfix"></div>
                  </div> -->
                  <div class="x_content">
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th style="width:8%">HRM ID</th>
                          <th>Username</th>
                          <th>Edit</th>
                          <th>Delete</th>
                        </tr>
                      </thead>

                      <tbody>
                          <c:forEach items="${hrmlist}" var="list" varStatus="status">
                        <tr>
                          <td><c:out value="${list.hrm_id}" /></td>
                          <td><c:out value="${list.hrm_username}" /></td>
                          <td><button class='btn btn-warning' data-toggle='modal' data-target='#editAccountModal<c:out value="${list.hrm_id}" />'>Edit</button></td>
                          
                          <td>
                              <form method="post" action="manage_hrm_account.htm">
                                  <input type="hidden" name="hrm_id" value="${list.hrm_id}">
                                    <input type="submit" onclick="return deleteConfirm()" name="action" value="Delete" class='btn btn-danger'>
                                </form>
                          </td>
                        </tr>
                        
                      <!-- Edit Division Modal -->
                      <div id="editAccountModal<c:out value="${list.hrm_id}" />" class="modal fade" role="dialog">
                        <div class="modal-dialog">

<!--                           Modal content-->
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title">Edit HRM Account's Username (${list.hrm_username})</h4>
                            </div>

                            <div class="modal-body">
                              <div class="form-horizontal">
                              <form action="manage_hrm_account.htm" method="post" class="contact_form">
                                  
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">Username:</label>
                                      <div class="col-sm-9">
                                          <input type='text' name='hrm_username' value="${list.hrm_username}" class='form-control' required>
                                      </div>
                                  </div>
                              </div>
                            </div>
                            <div class="modal-footer">
<!--                              <input type="submit" value="Dismiss" style="align: center;" class="btn btn-danger">-->
                                <input type="hidden" name="hrm_id" value="${list.hrm_id}">
                                <input type="submit" name="action" value="Update" class='btn btn-success'>
                              </form>
                            </div>
                          </div>

                        </div>
                      </div>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            
          </div>
        </div>
        <!-- /page content -->

        <%@include file="include/footer.jsp" %>
    
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/resources/vendors/iCheck/icheck.min.js"></script>
    <!-- Datatables -->
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net-scroller/js/datatables.scroller.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/jszip/dist/jszip.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/pdfmake/build/vfs_fonts.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/build/js/custom.min.js"></script>

    <!-- Datatables -->
    <script>
      $(document).ready(function() {
        var handleDataTableButtons = function() {
          if ($("#datatable-buttons").length) {
            $("#datatable-buttons").DataTable({
              dom: "Bfrtip",
              buttons: [
                {
                  extend: "copy",
                  className: "btn-sm"
                },
                {
                  extend: "csv",
                  className: "btn-sm"
                },
                {
                  extend: "excel",
                  className: "btn-sm"
                },
                {
                  extend: "pdfHtml5",
                  className: "btn-sm"
                },
                {
                  extend: "print",
                  className: "btn-sm"
                },
              ],
              responsive: true
            });
          }
        };

        TableManageButtons = function() {
          "use strict";
          return {
            init: function() {
              handleDataTableButtons();
            }
          };
        }();

        $('#datatable').dataTable();
        $('#datatablePosition').dataTable();

        // $('#datatable-keytable').DataTable({
        //   keys: true
        // });

        $('#datatable-scroller').DataTable({
          ajax: "js/datatables/json/scroller-demo.json",
          deferRender: true,
          scrollY: 380,
          scrollCollapse: true,
          scroller: true
        });

        // var $datatable = $('#datatable-checkbox');

        $datatable.dataTable({
          'order': [[ 1, 'asc' ]],
          'columnDefs': [
            { orderable: false, targets: [0] }
          ]
        });
        // $datatable.on('draw.dt', function() {
        //   $('input').iCheck({
        //     checkboxClass: 'icheckbox_flat-green'
        //   });
        // });

        TableManageButtons.init();
      });
    </script>
    <!-- /Datatables -->
  </body>
</html>