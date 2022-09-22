function doLike(pid, uid) {
    console.log(pid + "," + uid)
    const d = {
        userid: uid, //woh user whose login at the current time 
        postid: pid,  //id of the post
        operation: 'like'
    }
    $.ajax({
        url: "LikeServlet",
        data: d,
        success: function (data, textStatus, jqXHR) {
            console.log(data);
            if(data.trim()=="true"){
                let c=$('.like-counter').html();
                c++;
                $('.like-counter').html(c);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {

        },
        processdata: false,
        contentType: false
    })
}

