<%@include file="include/header.jsp" %>
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
                <h3>Manage Training</h3>
              </div>
              <div class="title_right">
                  <button class='btn btn-primary' data-toggle='modal' data-target='#addTrainingModal' style='float:right'>New Training</button>
                <!-- Add Training Modal -->
                      <div id="addTrainingModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">

<!--                           Modal content-->
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title">Manage Training</h4>
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
                                          <input type='text' name='pic' class='form-control'>
                                      </div>
                                  </div>
                                  <div class='form-group'>
                                      <label class="control-label col-sm-3">Speaker:</label>
                                      <div class="col-sm-9">
                                          <input type='text' name='speaker' class='form-control'>
                                      </div>
                                  </div>
                                  
<!--                                 <?php
                                  echo "<input type='hidden' name='advertID' value='{$row['advertID']}'>";
                                  echo "<div class='form-group'>";
                                    echo "<label class='control-label col-sm-2'>File:</label>";
                                    echo "<div class='col-sm-10'>";
                                      echo "<input name='photo' required='required' type='file' style='margin-top:5px'>";
                                    echo "</div>";
                                  echo "</div>";

                                  echo "<div class='form-group'>";
                                    echo "<label class='control-label col-sm-2'>URL:</label>";
                                    echo "<div class='col-sm-10'>";
                                      echo "<input class='form-control' type='text' name='url' placeholder='http://example.com' value='{$row['url']}' required>";
                                    echo "</div>";
                                  echo "</div>";

                                  echo "<div class='form-group'>";
                                    echo "<label class='control-label col-sm-2'>Type:</label>";
                                    echo "<div class='col-sm-10'>";
                                      echo "<select class='form-control' name='type'>";?>
                                        <option value="square" <?php if($row['type']=='square') echo "selected"?>>Square</option>
                                        <option value="banner" <?php if($row['type']=='banner') echo "selected"?>>Banner</option>
                                      <?php
                                      echo "</select>";
                                    echo "</div>";
                                  echo "</div>";
                                ?> -->
                              </div>
                            </div>
                            <div class="modal-footer">
<!--                              <input type="submit" value="Dismiss" style="align: center;" class="btn btn-danger">-->
                                    <input type="submit" value="Submit" class='btn btn-success'>
                              </form>
                            </div>
                          </div>

                        </div>
                      </div>
                
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
                   <div class="x_title">
                    <h2>List of Training</h2>
                    <div class="clearfix"></div>
                  </div> 
                  <div class="x_content">
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th style="width:8%">Training ID</th>
                          <th>Training Name</th>
                          <th>Assigned Position</th>
                          <th>Start Time</th>
                          <th>End Time</th>
                          <th>PIC</th>
                          <th>Speaker</th>
                          <th>Action</th>
                        </tr>
                      </thead>

                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>How To Be A Great Production</td>
                          <td>Production</td>
                          <td>10/12/2017 13:30</td>
                          <td>10/12/2017 15:30</td>
                          <td>Sample PIC</td>
                          <td>Sample Speaker</td>
                          <td>
                              <!--<button class='btn btn-warning' data-toggle='modal' data-target='#editTrainingModal'>Edit</button>-->
                          <button class='btn btn-danger'>Delete</button>
                          </td>
                        </tr>
                        
                        <tr>
                          <td>1</td>
                          <td>How To Be A Great Sales</td>
                          <td>Sales</td>
                          <td>10/12/2017 13:30</td>
                          <td>10/12/2017 15:30</td>
                          <td>Sample PIC</td>
                          <td>Sample Speaker</td>
                          <td>
                              <!--<button class='btn btn-warning' data-toggle='modal' data-target='#editTrainingModal'>Edit</button>-->
                          <button class='btn btn-danger'>Delete</button>
                          </td>
                        </tr>
                        
                        <tr>
                          <td>1</td>
                          <td>How To Be A Great Marketing</td>
                          <td>Marketing</td>
                          <td>10/12/2017 13:30</td>
                          <td>10/12/2017 15:30</td>
                          <td>Sample PIC</td>
                          <td>Sample Speaker</td>
                          <td>
                              <!--<button class='btn btn-warning' data-toggle='modal' data-target='#editTrainingModal'>Edit</button>-->
                          <button class='btn btn-danger'>Delete</button>
                          </td>
                        </tr>
                      <!-- <?php
                          $query=mysql_query("SELECT * FROM advert");
                          $row=mysql_fetch_array($query, MYSQL_ASSOC);

                          if($row==false) 
                          {
                            echo '<h4>There is no advertisement registered.</h4>';
                          }
                          else
                          {
                            do
                            {
                              echo "<tr>";
                                echo "<td>{$row['advertID']}</td>";
                                echo "<td>{$row['url']}</td>";
                                echo "<td>{$row['type']}</td>";
                                echo "<td>";
                                  echo "<button class='btn btn-primary' data-toggle='modal' data-target='#advertModal{$row['advertID']}'>View</button>";
                                echo "</td>";
                                echo "<td>";
                                  echo "<button class='btn btn-warning' data-toggle='modal' data-target='#editModal{$row['advertID']}'>Edit</button>";
                                echo "</td>";
                                echo "<td>";
                                  echo "<form action='controller/removeAdsController.php' method='POST'>";
                                  echo "<input type='hidden' name='advertID' value='{$row['advertID']}'>";
                                  echo "<input type='submit' class='btn btn-danger' value='Delete'>";
                                  echo "</form>";
                                echo "</td>";
                              echo "</tr>";
                      ?> -->
                      
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
                                  
<!--                                 <?php
                                  echo "<input type='hidden' name='advertID' value='{$row['advertID']}'>";
                                  echo "<div class='form-group'>";
                                    echo "<label class='control-label col-sm-2'>File:</label>";
                                    echo "<div class='col-sm-10'>";
                                      echo "<input name='photo' required='required' type='file' style='margin-top:5px'>";
                                    echo "</div>";
                                  echo "</div>";

                                  echo "<div class='form-group'>";
                                    echo "<label class='control-label col-sm-2'>URL:</label>";
                                    echo "<div class='col-sm-10'>";
                                      echo "<input class='form-control' type='text' name='url' placeholder='http://example.com' value='{$row['url']}' required>";
                                    echo "</div>";
                                  echo "</div>";

                                  echo "<div class='form-group'>";
                                    echo "<label class='control-label col-sm-2'>Type:</label>";
                                    echo "<div class='col-sm-10'>";
                                      echo "<select class='form-control' name='type'>";?>
                                        <option value="square" <?php if($row['type']=='square') echo "selected"?>>Square</option>
                                        <option value="banner" <?php if($row['type']=='banner') echo "selected"?>>Banner</option>
                                      <?php
                                      echo "</select>";
                                    echo "</div>";
                                  echo "</div>";
                                ?> -->
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
                      <!-- <?php
                      $row=mysql_fetch_array($query, MYSQL_ASSOC);
                            }
                            while($row!=false);
                          }
                      ?> -->
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