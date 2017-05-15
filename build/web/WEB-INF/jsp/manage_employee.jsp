<%@include file="include/header.jsp" %>
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
      
      if(invalid=="invalidbirthdate"){
        $("#invalidbirthdate").show();
      }
      else if(invalid=="updatesuccess"){
        $("#updatesuccess").show();
      }
        });
        
        function updateConfirm() {
            return confirm('Are you sure you want to update the employee data?');
        }
     </script>
 
  <%@include file="include/navigation.jsp" %>

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Manage Employee</h3>
              </div>
            </div>

            <div class="clearfix"></div>
            <div id="invalidbirthdate" class="alert alert-danger alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>Invalid birth date inputted, please input a birth date that does not exceed the current date.</strong>
            </div>
            <div id="updatesuccess" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The employee's data has been updated.</strong>
            </div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <!-- <div class="x_title">
                    <h2>List of Users</h2>
                    <div class="clearfix"></div>
                  </div> -->
                  <div class="x_content">
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th style="width:8%">Employee ID</th>
                          <th>Name</th>
                          <th>Gender</th>
                          <th>Phone Number</th>
                          <th>Email</th>
                          <th>Marital Status</th>
                          <th>Date Of Birth</th>
                          <th>Employee Since</th>
                          <th>Division</th>
                          <th>Position</th>
                          <th>Action</th>
                        </tr>
                      </thead>

                      <tbody>
                          <c:forEach items="${employeelist}" var="list" varStatus="status">
                          <tr>
                          <td><c:out value="${list.employee_id}"/></td>
                          <td><c:out value="${list.employee_name}"/></td>
                          <td><c:out value="${list.gender}"/></td>
                          <td><c:out value="${list.phone_number}"/></td>
                          <td><c:out value="${list.email}"/></td>
                          <td><c:out value="${list.marital_status}"/></td>
                          <td><c:out value="${list.date_of_birth}"/></td>
                          <td><c:out value="${list.employee_start_date}"/></td>
                          <td>
                              <c:out value="${divisionlist[status.index].division_name}"/>
                          </td>
                          <td>
                              <c:out value="${positionlist[status.index].position_name}"/>
                          </td>
                          <td><button class='btn btn-warning' data-toggle='modal' data-target='#editModal${list.employee_id}'>Edit</button></td>
                        </tr>

                      <!-- Edit Modal -->
                      <div id="editModal${list.employee_id}" class="modal fade" role="dialog">
                        <div class="modal-dialog">

<!--                           Modal content-->
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title">Edit Employee</h4>
                            </div>

                            <div class="modal-body">
                              <div class="form-horizontal">
                              <form action="manage_employee.htm" method="post" class="contact_form" >
                                    <h4>${list.employee_name}'s Position Info</h4>
                                    
                                    <div class='form-group'>
                                        <label class="control-label col-sm-3">Employee Name:</label>
                                        <div class="col-sm-9">
                                            <input type='text' name='employee_name' class='form-control' value="${list.employee_name}" required>
                                        </div>
                                    </div>
                                        
                                    <div class='form-group'>
                                        <label class="control-label col-sm-3">Phone Number:</label>
                                        <div class="col-sm-9">
                                            <input type='text' name='phone_number' class='form-control' value="${list.phone_number}" required>
                                        </div>
                                    </div>
                                        
                                    <div class='form-group'>
                                        <label class="control-label col-sm-3">Email:</label>
                                        <div class="col-sm-9">
                                            <input type='email' name='email' class='form-control' value="${list.email}" required>
                                        </div>
                                    </div>
                                        
                                    <div class='form-group'>
                                        <label class="control-label col-sm-3">Date of Birth:</label>
                                        <div class="col-sm-9">
                                            <input type='date' name='date_of_birth' class='form-control' value="${list.date_of_birth}" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Gender</label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                      <div id="gender" class="btn-group" data-toggle="buttons">
                                          <c:if test="${list.gender == 'Male'}">
                                            <label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                              <input type="radio" name="gender" checked="checked" value="Male"> &nbsp; Male &nbsp;
                                            </label>
                                            <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                              <input type="radio" name="gender" value="Female"> Female
                                            </label>
                                         </c:if> 
                                          
                                          <c:if test="${list.gender == 'Female'}">
                                            <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                              <input type="radio" name="gender" value="Male"> &nbsp; Male &nbsp;
                                            </label>
                                            <label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                              <input type="radio" name="gender" checked="checked" value="Female"> Female
                                            </label>
                                         </c:if> 
                                          
                                      </div>
                                    </div>
                                  </div>
                                        
                                    <div class='form-group'>
                                        <label class="control-label col-sm-3">Marital Status:</label>
                                        <div class="col-sm-9">
                                            <input type='text' name='marital_status' class='form-control' value="${list.marital_status}" required>
                                        </div>
                                    </div>

                                    <div class='form-group'>
                                        <label class="control-label col-sm-3">Position:</label>
                                        <div class="col-sm-9">
                                            <select name='position_id' required class="form-control">
                                                <c:forEach items="${positionforeditlist}" var="positionlist" varStatus="status">
                                                    <c:if test="${list.position_id == positionlist.position_id}">
                                                        <option value="${positionlist.position_id}" selected><c:out value="${positionlist.position_name}"/></option>
                                                    </c:if>
                                                    <c:if test="${list.position_id != positionlist.position_id}">
                                                        <option value="${positionlist.position_id}"><c:out value="${positionlist.position_name}"/></option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                              </div>
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" name="employee_id" value="${list.employee_id}">
                                <input type="submit" onclick="return updateConfirm()" name="action" value="Update" class='btn btn-success'>
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