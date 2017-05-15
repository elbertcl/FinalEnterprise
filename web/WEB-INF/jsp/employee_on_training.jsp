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
      
      if(invalid=="invalidtime"){
        $("#invalidtime").show();
      }
      else if(invalid=="passtraining"){
        $("#passtraining").show();
      }
      else if(invalid=="failtraining"){
        $("#failtraining").show();
      }
        });
     </script>
 
  <%@include file="include/navigation.jsp" %>

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Employees On Training</h3>
              </div>
            </div>

            <div class="clearfix"></div>
            <div id="invalidtime" class="alert alert-danger alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The training associated with the employee is not done yet, please check for the training's end time.</strong>
            </div>
            <div id="passtraining" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The result of employee passed the training has been recorded.</strong>
            </div>
            <div id="failtraining" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The result of employee failed the training has been recorded.</strong>
            </div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_content">
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th style="width:8%">Employee ID</th>
                          <th>Employee Name</th>
                          <th>Training Name</th>
                          <th>Assigned Position</th>
                          <th>Start Time</th>
                          <th>End Time</th>
                          <th>PIC</th>
                          <th>Result</th>
                        </tr>
                      </thead>

                      <tbody>
                          <c:forEach items="${employeelist}" var="list" varStatus="status">
                        <tr>
                          <td><c:out value="${list.employee_id}"/></td>
                          <td><c:out value="${list.employee_name}"/></td>
                          <td><c:out value="${traininglist[status.index].training_name}"/></td>
                          <td><c:out value="${positionlist[status.index].position_name}"/></td>
                          <td><c:out value="${traininglist[status.index].start_time}"/></td>
                          <td><c:out value="${traininglist[status.index].end_time}"/></td>
                          <td><c:out value="${traininglist[status.index].pic}"/></td>
                          <td>
                              <form action="employee_on_training.htm" method="post">
                                <input type="submit" name="action" value="Pass" class='btn btn-success'>
                                <input type="submit" name="action" value="Fail" class='btn btn-danger'>
                                <input type="hidden" name="employee_id" value="${list.employee_id}">
                                <input type="hidden" name="training_id" value="${traininglist[status.index].training_id}">
                              </form>
                          </td>
                        </tr>
                        </c:forEach>
                        
<!--                        <tr>
                          <td>2</td>
                          <td>Matthew</td>
                          <td>How To Be A Great Sales</td>
                          <td>Sales</td>
                          <td>10/12/2017 13:30</td>
                          <td>10/12/2017 15:30</td>
                          <td>Sample Speaker</td>
                          <td>
                              <button class='btn btn-warning' data-toggle='modal' data-target='#editTrainingModal'>Edit</button>
                              <button class='btn btn-success'>Pass</button>
                              <button class='btn btn-danger'>Fail</button>
                          </td>
                        </tr>
                        
                        <tr>
                          <td>1</td>
                          <td>Williams</td>
                          <td>How To Be A Great Marketing</td>
                          <td>Marketing</td>
                          <td>10/12/2017 13:30</td>
                          <td>10/12/2017 15:30</td>
                          <td>Sample Speaker</td>
                          <td>
                              <button class='btn btn-warning' data-toggle='modal' data-target='#editTrainingModal'>Edit</button>
                              <button class='btn btn-success'>Pass</button>
                              <button class='btn btn-danger'>Fail</button>
                          </td>
                        </tr>-->

                      <!-- Edit Division Modal -->
                      <div id="editTrainingModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">

<!--                           Modal content-->
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title">Edit Division</h4>
                            </div>

                            <div class="modal-body">
                              <div class="form-horizontal">
                              <form action="controller/editAdvertController.php" method="POST" class="contact_form" enctype="multipart/form-data">
                                  
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">Training Name:</label>
                                      <div class="col-sm-9">
                                          <input type='text' name='divisionname' class='form-control'>
                                      </div>
                                  </div>
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">Assigned Position:</label>
                                      <div class="col-sm-9">
                                        <select name='assignedposition'>
                                            <option>Production</option>
                                            <option>Sales</option>
                                            <option>Marketing</option>
                                        </select>
                                      </div>
                                  </div>
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">Start Time:</label>
                                      <div class="col-sm-9">
                                          <input type='datetime-local' name='starttime' class='form-control'>
                                      </div>
                                  </div>
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">End Time:</label>
                                      <div class="col-sm-9">
                                          <input type='datetime-local' name='endtime' class='form-control'>
                                      </div>
                                  </div>
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">PIC:</label>
                                      <div class="col-sm-9">
                                          <select name='pic'>
                                            <option>Sample PIC 1</option>
                                            <option>Sample PIC 2</option>
                                            <option>Sample PIC 3</option>
                                        </select>
                                      </div>
                                  </div>
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">Speaker:</label>
                                      <div class="col-sm-9">
                                          <input type='text' name='speaker' class='form-control'>
                                      </div>
                                  </div>
                              </div>
                            </div>
                            <div class="modal-footer">
<!--                              <input type="submit" value="Dismiss" style="align: center;" class="btn btn-danger">-->
                                <input type="submit" value="Update" class='btn btn-success'>
                              </form>
                            </div>
                          </div>

                        </div>
                      </div>
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