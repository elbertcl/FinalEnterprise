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
      
      if(invalid=="acceptsuccess"){
        $("#acceptsuccess").show();
      }
      else if(invalid=="rejectsuccess"){
        $("#rejectsuccess").show();
      }
        });
        
        function acceptConfirm() {
            return confirm('Are you sure you want to accept the applicant as an employee?');
        }
        
        function rejectConfirm() {
            return confirm('Are you sure you want to reject the applicant?');
        }
     </script>
 
  <%@include file="include/navigation.jsp" %>

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Set Recruitment Result</h3>
              </div>
            </div>

            <div class="clearfix"></div>
            <div id="acceptsuccess" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The applicant is successfully accepted and is now the employee of Slav.</strong>
            </div>
            <div id="rejectsuccess" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">X</span></button>
              <strong>The applicant is successfully rejected from Slav recruitment.</strong>
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
                          <th style="width:8%">Applicant ID</th>
                          <th>Name</th>
                          <th>Gender</th>
                          <th>Marital Status</th>
                          <th>Date Of Birth</th>
                          <th>Test Result</th>
                          <th>Download CV</th>
                          <th>Action</th>
                        </tr>
                      </thead>

                      <tbody>
                          <c:forEach items="${applicantslist}" var="list" varStatus="status">
                          <tr>
                          <td><c:out value="${list.applicant_id}"/></td>
                          <td><c:out value="${list.applicant_name}"/></td>
                          <td><c:out value="${list.gender}"/></td>
                          <td><c:out value="${list.marital_status}"/></td>
                          <td><c:out value="${list.date_of_birth}"/></td>
                          <td><button class='btn btn-primary' data-toggle='modal' data-target='#viewModal${list.applicant_id}'>Show</button></td>
                          <td><a href="${pageContext.request.contextPath}/resources/cv/${list.cv}" target="_blank"><button class='btn btn-primary'>Download</button></a></td>
                          <td>
                              <form action="set_recruitment_result.htm" method="post"> 
                              <input type="submit" onclick="return acceptConfirm()" name="action" value="Accept" class='btn btn-success'><input type="submit" onclick="return rejectConfirm()" name="action" value="Reject" class='btn btn-danger'>
                              <input type="hidden" name="applicant_id" value="${list.applicant_id}">
                              </form>
                              
<!--                              <form action="set_recruitment_result.htm" method="post"> 
                              <input type="submit" name="action" value="Reject" class='btn btn-danger'>
                              <input type="hidden" name="applicant_id" value="${list.applicant_id}">
                              </form>-->
                          </td>
                        </tr>
                        
<!--                        <tr>
                          <td>2</td>
                          <td>Matthew</td>
                          <td>Male</td>
                          <td>Single</td>
                          <td>20</td>
                          <td><button class='btn btn-primary' data-toggle='modal' data-target='#viewModal'>Show</button></td>
                          <td><button class='btn btn-primary'>Download</button></td>
                          <td><button class='btn btn-success'>Accept</button><button class='btn btn-danger'>Reject</button></td>
                        </tr>
                        
                        <tr>
                          <td>3</td>
                          <td>Williams</td>
                          <td>Male</td>
                          <td>Single</td>
                          <td>20</td>
                          <td><button class='btn btn-primary' data-toggle='modal' data-target='#viewModal'>Show</button></td>
                          <td><button class='btn btn-primary'>Download</button></td>
                          <td><button class='btn btn-success'>Accept</button><button class='btn btn-danger'>Reject</button></td>
                        </tr>-->
                      
                      <!-- View Modal -->
                      <div id="viewModal${list.applicant_id}" class="modal fade" role="dialog">
                        <div class="modal-dialog">

<!--                           Modal content-->
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title">${list.applicant_name}'s Test Result</h4>
                            </div>
                            <div class="modal-body">
                              <div class="form-horizontal">
                                <div class="form-group">
                                    <c:forEach items="${answerlist}" var="test" varStatus="status2">
                                         <c:if test="${list.applicant_id == test.applicant_id}">
                                            <h4>Question ID <c:out value="${test.question_id}"/></h4>
                                            <p><c:out value="${questionlist[status2.index].question}"/></p>
                                            <p><b>Answer: </b> <c:out value="${test.answer}"/></p><br>
                                        </c:if>
                                    </c:forEach>
                                </div>
                              </div>
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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