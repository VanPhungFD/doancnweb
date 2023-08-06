async function loadAllBlogUser() {
    var url = 'http://'+urlFirst+'/api/public/allBlog';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listBlog = await response.json();
    var main = '';
    for (i = 0; i < listBlog.length; i++) {
        main += '<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 blogs">'+
            '<div class="blog-single">'+
            '<img src="'+listBlog[i].imageBanner+'">'+
            '<p class="time-blog">'+listBlog[i].createdDate+'</p>'+
            '<p class="ten-blog">'+listBlog[i].title+'</p>'+
            '<p class="mota-blog">'+listBlog[i].description+'</p>'+
            '<a class="xemthem-blog" href="'+linkdetailblog+'?id='+listBlog[i].id+'">Xem thêm ></a>'+
            '</div>'+
            '</div>'
    }
    document.getElementById("blog-list").innerHTML = main
}

async function loadABlogUser() {
    var id = window.location.search.split('=')[1];
    if(id != null){
        var url = 'http://'+urlFirst+'/api/public/blogById?id='+id;
        const response = await fetch(url, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var blog = await response.json();
        document.getElementById("blog-detail-name").innerText = blog.title
        document.getElementById("updateat").innerText = blog.title
        document.getElementById("contentblog").innerHTML = blog.content

    }
}
