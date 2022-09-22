<%-- 
    Document   : profile
    Created on : 22 Jul, 2022, 2:37:57 AM
    Author     : risha
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.aart.entities.Category"%>
<%@page import="com.tech.aart.helper.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.tech.aart.dao.PostDao"%>
<%@page import="com.tech.aart.entities.Message"%>
<%@page import="com.tech.aart.entities.User"%>
<%@page errorPage="error_page.jsp" %>
<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/mystyle.css" type="text/css"></link>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .banner-background{
                clip-path: polygon(100% 0, 100% 35%, 100% 98%, 77% 95%, 50% 100%, 23% 95%, 0 99%, 0% 35%, 0 0);

            }
        </style>
    </head>
    <body>
        <!--navbar start-->
        <nav class="navbar navbar-expand-lg navbar-dark primary-background">
            <a class="navbar-brand" href="index.jsp"><span class="fa fa-check-square-o"></span> TechArticles</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><span class="fa fa-mortar-board"></span> My Courses<span class="sr-only">(current)</span></a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="fa fa-reorder"> </span> Categories
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Programming Language</a>
                            <a class="dropdown-item" href="#">Project Implementation</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Data Structures</a>
                        </div>
                    </li
                    <li class="nav-item">
                        <a class="nav-link" href="#"><span class="fa fa-phone-square"></span> Contact</a>
                    </li>
                    <!--post button-->
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="modal" data-target="#addPostModal" href="#"><span class="fa fa-pencil-square-o"></span> Do Post</a>
                    </li>

                </ul>
                <ul class="navbar-nav mr-right">

                    <li class="nav-item">
                        <a class="nav-link" href="#!" data-toggle="modal" data-target="#profileModal"><span class="fa fa-user-circle"></span> <%=user.getName()%></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout"><span class="fa fa-sign-out"></span> Log out</a>
                    </li>
                </ul>
            </div>
        </nav>
        <!--navbar end-->


        <!--Message to show on the current page-->
        <!--Profile update successfully-->
        <%
            Message msg = (Message) session.getAttribute("message");

            if (msg != null) {
        %>
        <div class="alert <%=msg.getCssclass()%>" role="alert"><%
            if (msg.getType() == "success") {%>
            <span class="fa fa-thumbs-up">
                <%=msg.getContent()%>
            </span> 

            <%} else {

            %><span class="fa fa-warning"></span>
            <%=msg.getContent()%>
            <%}%>

        </div>
        <%
                session.removeAttribute("message");
            }
        %>

        <!--profile update or edit-->

        <!--main body of the content here we divide the page in two parts first for categories and other for category content-->
        <!--main body start-->
        <main>
            <!--for banner-->
            <div class="container">

                <div class="row mt-4">
                    <!--first column-->
                    <div class="col-md-4">
                        <!--for categories-->
                        <div class="list-group">
                            <a href="#" onclick="getPosts(0,this)" class="c-link list-group-item list-group-item-action">
                                All Posts
                            </a>
                            <!--                            Categories-->
                            <%
                                PostDao d = new PostDao(ConnectionProvider.getConnection());
                                ArrayList<Category> list1 = d.getAllCategories();
                                for (Category cc : list1) {

                            %>
                            <a href="#" onclick="getPosts(<%=cc.getCid()%>,this)" class="c-link list-group-item list-group-item-action"><%=cc.getName()%></a>
                            <%}%>                         
                        </div>
                    </div
                    <!--second column-->
                    <div class="col-md-8">
                        <!--categories content-->
                        <div class="container text-center" id="loader">
                            <i class="fa fa-refresh fa-spin fa-3x"></i>
                            <h3 class="mt-2">Loading......</h3>
                        </div>
                        <div class="container-fluid" id="postContainer">

                        </div>
                    </div>
                </div>
            </div>
        </main>

        <!--main body end-->
        <!--profile modal-->
        <!-- Button trigger modal -->

        <!-- Modal -->
        <div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header primary-background text-white text-center">
                        <h5 class="modal-title" id="exampleModalLabel">TechArticles</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container text-center">
                            <img src="pics/<%=user.getProfile()%>" class="img-fluid" style="border-radius:50%; max-width:150px">
                            <br>
                            <h4 class="modal-title mt-2" id="exampleModalLabel"><%=user.getName()%></h4>
                            <!--details-->
                            <!--profile details-->
                            <div class="container" id="profile-detail">
                                <table class="table">

                                    <tbody>
                                        <tr>
                                            <th scope="row">Id</th>
                                            <td><%=user.getId()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Email</th>
                                            <td><%=user.getEmail()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Gender</th>
                                            <td><%=user.getGender()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Status</th>
                                            <td><%=user.getAbout()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">registered on:</th>
                                            <td><%=user.getDateTime().toString()%></td>

                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!--profile-edit-->
                            <div id="profile-edit" style="display:none">
                                <h5 class="mt-2">Please Edit Carefully</h5>
                                <!--enctype is used to send image and video-->
                                <form action="EditServlet" method="post" enctype="multipart/form-data">
                                    <table class="table">
                                        <tr>
                                            <td>Id</td>
                                            <td><%=user.getId()%></td>
                                        </tr>
                                        <tr>
                                            <td>Email</td>
                                            <td><input name="editEmail" type="email" class="form-control" value="<%=user.getEmail()%>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Name</td>
                                            <td><input name="editName" type="text" class="form-control" value="<%=user.getName()%>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Password</td>
                                            <td><input name="editPassword" type="password" class="form-control" value="<%=user.getPassword()%>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Gender</td>
                                            <td><%=user.getGender().toUpperCase()%></td>
                                        </tr>
                                        <tr>
                                            <td>About</td>
                                            <td>   
                                                <textarea class="form-control" rows="3" name="editAbout" ><%=user.getAbout()%></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>New Profile:</td>
                                            <td><input type="file" class="form-control" name="editImg">
                                            </td>
                                        </tr>
                                    </table>
                                    <div class="container">
                                        <button type="submit" class="btn btn-outline-primary text">Save</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button id="edit-profile-btn" type="button" class="btn btn-primary">Edit</button>
                    </div>
                </div>
            </div>
        </div>
        <!--profile modal end-->

        <!-- start of post modal-->

        <div class="modal fade" id="addPostModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title" id="addPostModal">Provide the Post Details...</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="addPostForm" method="POST" action="PostServlet">
                            <div class="form-group">
                                <select class="form-control" name="cId">
                                    <option selected disabled>Select Category</option>
                                    <%
                                        PostDao psd = new PostDao(ConnectionProvider.getConnection());
                                        ArrayList<Category> list = psd.getAllCategories();
                                        for (Category c : list) {
                                    %>
                                    <option value="<%=c.getCid()%>"><%=c.getName()%></option>
                                    <%}
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <input name="pTitle" type="text" placeholder="Enter the Title" class="form-control">
                            </div>
                            <div class="form-group">
                                <textarea name="pContent" style="height:200px" placeholder="Enter the content" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <textarea name="pCode" style="height:200px" placeholder="Enter the code (if any)" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label>Select Pic</label>
                                <br>
                                <input name="pPic" type="file">
                            </div>
                            <div class="container text-center">
                                <button class="btn btn-outline-primary" type="submit">Post</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        <!--end of post modal-->
        <!--Javascript-->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="js/myjs.js" type="text/javascript"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script>
                                $(document).ready(function () {
                                let editStatus = false;
                                $('#edit-profile-btn').click(function () {
                                //                  alert("button cliked");
                                if (editStatus == false) {
                                //if first click the edit before that it was false 
                                //                        after clicking the edit btn then it will true then perform following operations
                                $('#profile-detail').hide();
                                $('#profile-edit').show();
                                editStatus = true;
                                $(this).text("Back");
                                } else {
                                $('#profile-detail').show();
                                $('#profile-edit').hide();
                                editStatus = false;
                                $(this).text("Edit");
                                }

                                })
                                })
        </script>
        <!--now this post javascript-->
        <script>

                    $(document).ready(function (e) {

            $("#addPostForm").on("submit", function (event) {
            event.preventDefault();
            let f = new FormData(this);
            console.log("ready");
            $.ajax({

            url: "PostServlet",
                    type: 'POST',
                    data: f,
                    success: function (data, textStatus, jqXHR) {
                    console.log(data);
                    if (data.trim() == "success") {
                    swal({
                    title: "Good job!",
                            text: "Post Successfully",
                            icon: "success",
                    });
                    } else {
                    swal({
                    title: "Error!",
                            text: "Something went wrong!",
                            icon: "error",
                    });
                    }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                    swal({
                    title: "Error!",
                            text: "Something went wrong!",
                            icon: "error",
                    });
                    },
                    processData: false,
                    contentType: false
            })
            })
            })
        </script>
        <script>
                    function getPosts(catId, temp){
                    $("#loader").show();
                    $("#postContainer").hide();
                    $('.c-link').removeClass("active");
                    $.ajax({
                    url:"load_post.jsp",
                            data:{cid:catId},
                            success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            $("#loader").hide();
                            $("#postContainer").show();
                            $("#postContainer").html(data);
                            $(temp).addClass("active");
                            }
                    })
                    }
            $(document).ready(function(e){
                    let activePostRef = $('.c-link')[0]
                    getPosts(0, activePostRef)
            })
        </script>
    </body>
</html>
