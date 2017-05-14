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
      
      if(invalid=="deleted"){
        $("#deleted").show();
      }
      else if(invalid=="modified"){
        $("#modified").show();
      }
        });
     </script>
 
  <%@include file="include/navigation.jsp" %>

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Manage Interview Time</h3>
              </div>
            </div>

            <div class="clearfix"></div>
            <div id="modified" class="alert alert-warning alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
              <strong>Advertisement modified!</strong>
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
                          <th>Interview Time</th>
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
                            
                            <c:if test="${list.recruitment_status == 'TestQuestionDone'}">
                                <form action="manage_interview_time.htm" method="post"> 
                                    <td><input type="datetime-local" name="datetime" required>&nbsp;&nbsp;&nbsp;<input type="submit" name="action" value="Submit Time" class='btn btn-success'></td>
                                    <input type="hidden" name="applicant_id" value="${list.applicant_id}">
                                </form>
                        
                                <td><a href="${pageContext.request.contextPath}/resources/cv/${list.cv}" target="_blank"><button class='btn btn-primary'>Download</button></a></td>
                                <td>Awaiting interview time submission.</td>
                            </c:if>
                                
                            <c:if test="${list.recruitment_status == 'OnInterview'}">
                                <c:forEach items="${interviewlist}" var="test2" varStatus="status2">
                                    <c:if test="${list.applicant_id == test2.applicant_id}">
                                        <td><c:out value="${test2.datetime}"/></td>
                                    </c:if>
                                </c:forEach> 
                                <td><a href="${pageContext.request.contextPath}/resources/cv/${list.cv}" target="_blank"><button class='btn btn-primary'>Download</button></a></td>
                                <form action="manage_interview_time.htm" method="post"> 
                                    <td><input type="submit" name="action" value="Interview Completed" class='btn btn-primary'></td>
                                    <input type="hidden" name="applicant_id" value="${list.applicant_id}">
                                </form>
                            </c:if>
                        </tr>

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
<!--                                    <h4>Question ID 2</h4>
                                    <p>This is a sample question. This is a sample question. This is a sample question.</p>
                                    <p><b>Answer: </b>This is a sample answer. This is a sample answer. This is a sample answer.</p><br>
                                    
                                    <h4>Question ID 3</h4>
                                    <p>This is a sample question. This is a sample question. This is a sample question.</p>
                                    <p><b>Answer: </b>This is a sample answer. This is a sample answer. This is a sample answer.</p><br>-->
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
                      
                      <!-- Edit Modal -->
                      <div id="editModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">

<!--                           Modal content-->
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title">Edit Employee</h4>
                            </div>

                            <div class="modal-body">
                              <div class="form-horizontal">
                              <form action="controller/editAdvertController.php" method="POST" class="contact_form" enctype="multipart/form-data">
                                  <h4>Position Info</h4>
                                  
                                  <div class='form-group'>
                                      <p>Division:
                                      <select style="margin-top:7px; width:150px">
                                            <option>Production</option>
                                            <option>Sales</option>
                                            <option>Marketing</option>
                                        </select>
                                      </p>
                                  </div><input type="submit" value="Update" class='btn btn-success'><br><br>
                                  
                                  <div class='form-group'>
                                      <p>Position:
                                      <select style="margin-top:7px; width:150px">
                                            <option>Packaging</option>
                                            <option>Sample</option>
                                            <option>Sample</option>
                                        </select>
                                      </p>
                                  </div><input type="submit" value="Update" class='btn btn-success'>
                              </div>
                            </div>
                            <div class="modal-footer">
<!--                              <input type="submit" value="Dismiss" style="align: center;" class="btn btn-danger">-->
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