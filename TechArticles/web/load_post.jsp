
<%@page import="com.tech.aart.dao.LikeDao"%>
<%@page import="com.tech.aart.entities.User"%>
<%@page import="com.tech.aart.entities.Post" %>
<%@page import="com.tech.aart.helper.ConnectionProvider" %>
<%@page import="java.util.List" %>
<%@page import="com.tech.aart.dao.PostDao" %>

<div class="row">
    <%
    //    Thread.sleep(1000);
        PostDao d = new PostDao(ConnectionProvider.getConnection());
        List<Post> posts = null;

        int cId = Integer.parseInt(request.getParameter("cid"));
        if (cId == 0) {
            posts = d.getAllPost();
        } else {
            posts = d.getPostById(cId);
        }
        if (posts.size() == 0) {
            out.println("<h2 class='display-3 text-center'>No post in this category</h2>");
            return;
        }
        for (Post p : posts) {
    %>
    <div class="col-md-6 mt-2">
        <div class="card">
            <img class="card-image-top" src="blogPostPic/<%=p.getpPic()%>" alt="Post related Image">
            <div class="card-body">
                <b><%=p.getpTitle()%></b>
                <p><%=p.getpContent()%></p>
                <pre><%=p.getpCode()%></pre>
            </div>
            <div class="card-footer text-center bg-white">
               <%
               User user= (User)session.getAttribute("currentUser");
               LikeDao lkd=new LikeDao(ConnectionProvider.getConnection());
               %>
                <a href="#!" onclick="doLike(<%=p.getpId()%>,<%=user.getId()%>)" class="btn btn-outline-primary btn-sm"><i class="fa fa-thumbs-o-up"></i><span class="like-counter"><%=lkd.countLike(p.getpId())%></span></a>
                <a href="#!" class="btn btn-outline-primary btn-sm"><i class="fa fa-commenting-o"></i><span>20</span></a>
                 <a href="show_blog.jsp?post_Id=<%=p.getpId()%>" class="btn btn-outline-primary btn-sm">Read More...</a>
            </div>
        </div>
    </div>

    <%
        }
    %>
</div>