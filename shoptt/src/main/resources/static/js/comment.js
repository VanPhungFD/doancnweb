var token = localStorage.getItem("token");

async function loadAllComment() {

    if(token === null){
        document.getElementById("mycomment").style.display = 'none'
    }

    var id = window.location.search.split('=')[1];
    var url = 'http://'+urlFirst+'/api/public/commentByPro?id='+id;
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var listComment = await response.json();
    console.log(listComment)
    var main = '';
    var deletecmt = ''
    var username = ''
    for (i = 0; i < listComment.length; i++) {
        if(listComment[i].myComment == 1){
            deletecmt = '<i onclick="deleteComment('+listComment[i].id+')" class="fa fa-trash xoabl"></i>'
            username = 'Bạn'
        }
        else{
            deletecmt = ''
            username = listComment[i].user.username
        }
        main += '<div class="col-md-4">'+
            '<div class="comment-user">'+
            '<img src="'+listComment[i].user.avatar+'">'+
            '<div class="content-comment">'+
            '<div class="top-comment-user">'+
            '<p class="user-comment">'+username+'</p>'+
            '<p class="time-comment"><i class="fa fa-clock"></i>'+listComment[i].createdDate+'</p>'+
            deletecmt+
            '</div>'+
            '<p class="contentcmt">'+listComment[i].content+'</p>'+
            '</div>'+
            '</div>'+
            '</div>'
    }
    document.getElementById("listcomment").innerHTML = main
}

async function saveComment() {
    var id = window.location.search.split('=')[1];

    var url = 'http://'+urlFirst+'/api/user/addcomment';
    var noidungbl = document.getElementById("noidungbl").value
    if(noidungbl == ""){
        alert("bạn chưa nhập nội dung")
        return;
    }
    var comment = {
        "content": noidungbl,
        "product":{
            "id":id
        }
    }
    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(comment)
    });
    if (response.status < 300) {
        swal({title: "Thông báo", text: "đã đăng bình luận của bạn!", type: "success"},
            function(){
                window.location.reload()
            });
    }
    else {
        swal({title: "Thông báo", text: "không thể bình luận!",type: "error"},
            function(){ });
    }
}

async function deleteComment(id){
    var url = 'http://'+urlFirst+'/api/user/deletecomment?id='+id;
    const response = await fetch(url, {
        method: 'DELETE',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    if (response.status < 300) {
        swal({title: "Thông báo", text: "đã xóa bình luận của bạn!", type: "success"},
            function(){
                window.location.reload()
            });
    }
    else {
        swal({title: "Thông báo", text: "không thể xóa bình luận!",type: "error"},
            function(){ });
    }
}
