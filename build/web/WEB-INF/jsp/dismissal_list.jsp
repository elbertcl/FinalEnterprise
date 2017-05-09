<%@include file="include/header.jsp" %>

 <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
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
      
      if(invalid=="false"){
        $("#false").show();
      }
        });
     </script>

    <!-- iCheck -->
    <link href="${pageContext.request.contextPath}/resources/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
 
<%@include file="include/navigation.jsp" %>

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Dismissal List</h3>
              </div>
            </div>
            
            <div class="clearfix"></div>
            <div id="false" class="alert alert-success alert-dismissible fade in" style="display:none;"> 
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
              <strong>User's status has been changed.</strong>
            </div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>List of Dismissed Employees</h2>

                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th style="width:8%">Employee ID</th>
                          <th>Name</th>
                          <th>Last Division</th>
                          <th>Last Position</th>
                          <th>Dismissed Date</th>
                          <th>Dismissal Fee</th>
                        </tr>
                      </thead>

                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>Jeremy</td>
                          <td>Production</td>
                          <td>Packaging</td>
                          <td>2017/3/10</td>
                          <td>IDR 2000000</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>Matthew</td>
                          <td>Sales</td>
                          <td>Sales Rep</td>
                          <td>2017/3/10</td>
                          <td>IDR 1000000</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>William</td>
                          <td>Marketing</td>
                          <td>Digital Promotion</td>
                          <td>2017/3/10</td>
                          <td>IDR 500000</td>
                        </tr>
                      <!-- <?php
                          $query=mysql_query("SELECT * FROM user");
                          $row=mysql_fetch_array($query, MYSQL_ASSOC);
                          if($row==false)
                          {
                            echo '<h4>There is no user registered.</h4>';
                          }
                          else
                          {
                            do
                            {
                                echo '<tr>';
                                echo '<td>'.$userID = $row['userID'].'</td>';
                                echo '<td>'.$row['name'].'</td>';
                                echo '<td>'.$row['username'].'</td>';
                                echo '<td><a href="mailto:' . $row['email'] . '">' . $row['email'] . '</a></td>';
                                echo '<td>'.$row['status'].'</td>';
                                echo '<td>';
                                  echo '<form action="controller/userStatus.php" method="POST">';
                                  echo "<input type='hidden' name='userID' value='{$row['userID']}' >";
                                  echo "<input type='hidden' name='currentStatus' value='{$row['status']}' >";
                                  if($row['status'] == "active")
                                  {
                                    echo '<button type="submit" name="submit" class="btn btn-danger">Deactivate</button>';
                                  }
                                  if($row['status'] == "inactive")
                                  {
                                    echo '<button type="submit" name="submit" class="btn btn-success">Activate</button>';
                                  }
                                  echo '</form>';
                                  // echo '<select id="heard" name="selStatus" class="form-control" required>';
                                  // if($row['status'] == "active")
                                  // {
                                  //   echo '<option value="active" selected>Active</option>';
                                  //   echo '<option value="inactive">Inactive</option>';
                                  //   if($_POST['selStatus'] === 'inactive') mysql_query($deactivate);
                                  //   else mysql_query($active);
                                  // }
                                  // else if($row['status'] == "inactive")
                                  // {
                                  //   echo '<option value="active" >Active</option>';
                                  //   echo '<option value="inactive" selected>Inactive</option>';
                                  //   if($_POST['selStatus'] == 'active') mysql_query($activate);
                                  //   else mysql_query($inactive);
                                  // }
                                  // echo '</select>';
                                echo '</td>';
                              echo '</tr>';
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
          ajax: "${pageContext.request.contextPath}/resources/js/datatables/json/scroller-demo.json",
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