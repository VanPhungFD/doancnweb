var token = localStorage.getItem("token");

async function loadAllBlog() {
    var url = 'http://'+urlFirst+'/api/public/allBlog';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listBlog = await response.json();
    var main = '';
    for (i = 0; i < listBlog.length; i++) {
        main += '<tr>'+
            '<td>#'+listBlog[i].id+'</td>'+
            '<td>'+listBlog[i].createdDate+'</td>'+
            '<td>'+listBlog[i].title+'</td>'+
            '<td>'+listBlog[i].description+'</td>'+
            '<td><button onclick="deleteBlog('+listBlog[i].id+')" class="btn btn-danger"><i class="fa fa-trash"></i> Xóa</button></td>'+
            '<td><a href='+linkAddBlog+'?id='+listBlog[i].id+' class="btn btn-success"><i class="fa fa-trash"></i> Sửa</a></td>'+
            '</tr>'
    }
    document.getElementById("listblog").innerHTML = main
    $('#example').DataTable();
}

async function saveBlog() {
    var id = window.location.search.split('=')[1];
    var url = 'http://'+urlFirst+'/api/admin/addOrUpdateBlog';

    var tieude = document.getElementById("tieude").value
    var mota = document.getElementById("mota").value
    var content = tinyMCE.get('editor').getContent()
    if(tieude == "" || mota == ""){
        alert("dữ liệu không được để trống");
        return;
    }

    const filePath = document.getElementById('imagebanner')
    const formData = new FormData()
    formData.append("file", filePath.files[0])
    var urlUpload = 'http://localhost:8080/api/public/upload';
    const res = await fetch(urlUpload, {
        method: 'POST',
        headers: new Headers({
        }),
        body: formData
    });
    var linkbanner = await res.text();
    if(res.status > 300){
        linkbanner = null;
    }


    var blog = {
        "id": id,
        "title": tieude,
        "description":mota,
        "imageBanner":linkbanner,
        "content":content
    }

    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(blog)
    });
    if(response.status < 300){
        swal({
                title: "Thông báo",
                text: "thêm/sửa blog thành công!",
                type: "success"
            },
            function(){
                window.location.replace(linkblogadmin)
            });
    }
    else{
        swal({
                title: "Thông báo",
                text: "thêm/sửa blog thất bại!",
                type: "error"
            },
            function(){
            });
    }
}

async function loadABlog() {
    var id = window.location.search.split('=')[1];
    if(id != null){
        var url = 'http://'+urlFirst+'/api/public/blogById?id='+id;
        const response = await fetch(url, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var blog = await response.json();
        document.getElementById("tieude").value = blog.title
        document.getElementById("mota").value = blog.description
        document.getElementById("imgblog").src = blog.imageBanner
        tinyMCE.get('editor').setContent(blog.content)

    }
}


async function deleteBlog(id) {
    var url = 'http://'+urlFirst+'/api/admin/deleteBlog?id=' + id;
    const response = await fetch(url, {
        method: 'DELETE',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    if (response.status < 300) {
        swal({
                title: "Thông báo",
                text: "xóa bài viết thành công!",
                type: "success"
            },
            function(){
                window.location.reload();
            });
    }
    else {
        swal({
                title: "Thông báo",
                text: "không thể xóa bài viết",
                type: "error"
            },
            function(){
                window.location.reload();
            });
    }
}