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
                <h3>List of Applicants</h3>
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
                          <th>Age</th>
                          <th>Action</th>
                        </tr>
                      </thead>

                      <tbody>
                          <tr>
                          <td>1</td>
                          <td>Jeremy</td>
                          <td>Male</td>
                          <td>Single</td>
                          <td>20</td>
                          <td><button class='btn btn-danger'>Delete</button></td>
                        </tr>
                        
                        <tr>
                          <tr>
                          <td>2</td>
                          <td>Matthew</td>
                          <td>Male</td>
                          <td>Single</td>
                          <td>20</td>
                          <td><button class='btn btn-danger'>Delete</button></td>
                        </tr>
                        
                        <tr>
                          <tr>
                          <td>3</td>
                          <td>Williams</td>
                          <td>Male</td>
                          <td>Single</td>
                          <td>20</td>
                          <td><button class='btn btn-danger'>Delete</button></td>
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
                      
                      <!-- View Modal -->
<!--                      <div id="viewModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">

                           Modal content
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title">Employee's Benefit</h4>
                            </div>
                            <div class="modal-body">
                              <div class="form-horizontal">
                                <div class="form-group">
                                    <p style="font-size:16px">This is a benefit list example. This is a benefit list example.This is a benefit list example. This is a benefit list example.</p>
                                <?php
                                  $query_message = mysql_query("SELECT * FROM advert WHERE advertID = {$row['advertID']}");
                                  $row_message = mysql_fetch_array($query_message, MYSQL_ASSOC);
                                    if($row_message['type'] == 'banner')
                                    {
                                      echo "<center><img src='../../menitnews/images/{$row_message['photo']}' width='100%'></center>";
                                    }
                                    else if($row_message['type'] == 'square')
                                    {
                                      // Video
                                      if(strpos($row_message['photo'], 'mp4') == true || strpos($row_message['photo'], 'webm') == true  || strpos($row_message['photo'], 'ogg') == true )
                                      {
                                          echo "<video width='100%' height = '100%' loop controls>"; 
                                          echo "<source src='../../menitnews/images/{$row_message['photo']}'></video>";
                                      }
                                      // Picture
                                      else
                                      {
                                          echo "<center><img src='../../menitnews/images/{$row_message['photo']}' width='60%' align='middle'></center>";
                                      }
                                      
                                    }
                                ?>
                                </div>
                              </div>
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                          </div>
                        </div>
                      </div>-->
                      
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