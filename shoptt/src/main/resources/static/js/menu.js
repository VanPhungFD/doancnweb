var token = localStorage.getItem("token");

async function checkRoleUser(){
    var token = localStorage.getItem("token");
    if(token === null){
        window.location.replace(loginlink)
        return
    }
    var url = 'http://'+urlFirst+'/api/user/checkroleUser';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    if(response.status > 300){
        window.location.replace(loginlink)
    }
}

async function loadMenu(){
    var onclicks = 'href='+loginlink
    var authen = '<li class="nav-item" id="login-menu"><a class="nav-link authens" '+onclicks+'>Đăng nhập</a></li>'
    var history = ''
    if(token != null){
        authen = ''
        onclicks = 'onclick="logout()"'
        history = '<li class="nav-item dropdown">'+
            '<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">'+
            'Tài khoản'+
            '</a>'+
            '<ul id="listcategoryheader" class="dropdown-menu" aria-labelledby="navbarDropdown">'+
            '<li><a class="dropdown-item authens" '+onclicks+'>Đăng xuát</a></li>'+
            '<li><a class="dropdown-item" href="'+linkhistoryorder+'">Lịch sử đặt hàng</a></li>'+
            '</ul>'+
            '</li>'
    }

    var main = '<div class="menu-top">'+
        '<div class="container">'+
        '<div class="row">'+
        '<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">'+
        '<p id="title-menu-top">Giảm giá toàn bộ cửa hàng trong 30 ngày</p>'+
        '</div>'+
        '<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">'+
        '<ul class="list-menu-top">'+
        '<li><a href="'+linkcart+'">Giỏ hàng</a></li>'+
        '</ul>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '<div class="menu-bottom">'+
        '<div class="container">'+
        '<nav class="navbar navbar-expand-lg">'+
        '<a href="/"><img class="logo-menu" src="../images/logo.png"></a>'+
        '<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>'+
        '<div class="collapse navbar-collapse" id="navbarSupportedContent">'+
        '<ul class="navbar-nav me-auto mb-2 mb-lg-0">'+
        '<li class="nav-item"><a class="nav-link" href="'+linkindex+'">Trang chủ</a></li>'+
        '<li class="nav-item"><a class="nav-link" href="'+linksanphamuser+'">Sản phẩm</a></li>'+
        '<li class="nav-item dropdown">'+
        '<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">'+
        'Danh mục'+
        '</a>'+
        '<ul id="listcategoryheader" class="dropdown-menu" aria-labelledby="navbarDropdown">'+
        '<li><a class="dropdown-item" href="#">Action</a></li>'+
        '<li><a class="dropdown-item" href="#">Another action</a></li>'+
        '<li><a class="dropdown-item" href="#">Something else here</a></li>'+
        '</ul>'+
        '</li>'+
        '<li class="nav-item"><a class="nav-link" href="'+linkblog+'">Bài viết</a></li>'+
        ''+authen+''+
        ''+history+''+
        '</ul>'+
        '<div class="d-flex">'+
        '<a><i  data-bs-toggle="modal" data-bs-target="#exampleModal" class="fa fa-search"></i></a>'+
        '<a href="'+linkcart+'"><i class="fa fa-shopping-cart"></i></a>'+
        '<a href="'+linktaikhoan+'"><i class="fa fa-user"></i></a>'+
        '</div>'+
        '</div>'+
        '</nav>'+
        '</div>'+
        '</div>'+
        '<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">'+
        '<div class="modal-dialog modal-lg">'+
        '<div class="modal-content">'+
        '<div class="modal-header"><h5 class="modal-title" id="exampleModalLabel">Tìm kiếm sản phẩm</h5> <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>'+
        '<div class="modal-body">'+
        '<form method="GET" action="'+linksanphamuser+'">'+
        '<input name="search" type="text" placeholder="tìm kiếm sản phẩm" class="form-control"><br>'+
        '<button class="btn btn-primary form-control">Tìm kiếm</button>'+
        '</form>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '</div>'

    document.getElementById("menu").innerHTML = main;


    var url = 'http://'+urlFirst+'/api/public/allcategory';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listcategory = await response.json();
    var mains = '';
    for (i = 0; i < listcategory.length; i++) {
        mains += '<li><a class="dropdown-item" href="'+linksanphamuser+'?category='+listcategory[i].id+'">'+listcategory[i].name+'</a></li>'
    }
    document.getElementById("listcategoryheader").innerHTML = mains
}

async function logout(){
    localStorage.removeItem("token");
    window.location.replace(loginlink)
}

function loadFooter(){
    main = '<div class="container">'+
        '<footer class="text-center text-lg-start text-muted">'+
        '<section class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">'+
        '<div class="me-5 d-none d-lg-block"><span>Theo dõi chúng tôi tại:</span></div>'+
        '<div>'+
        '<a href="" class="me-4 text-reset"><i class="fab fa-facebook-f"></i></a>'+
        '<a href="" class="me-4 text-reset"><i class="fab fa-twitter"></i></a>'+
        '<a href="" class="me-4 text-reset"><i class="fab fa-google"></i></a>'+
        '<a href="" class="me-4 text-reset"><i class="fab fa-instagram"></i></a>'+
        '<a href="" class="me-4 text-reset"><i class="fab fa-linkedin"></i></a>'+
        '<a href="" class="me-4 text-reset"><i class="fab fa-github"></i></a>'+
        '</div>'+
        '</section>'+
        '<section class="">'+
        '<div class=" text-center text-md-start mt-5">'+
        '<div class="row mt-3">'+
        '<div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">'+
        '<h6 class="text-uppercase fw-bold mb-4"><i class="fas fa-gem me-3"></i>SmartCat</h6>'+
        '<p>'+
        'Chúng tôi cung cấp dịch vụ thời trang giá rẻ cho nam, nữ giới trẻ với phong cách bắt kịp trend hiện nay'+
        '</p>'+
        '</div>'+
        '<div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">'+
        '<h6 class="text-uppercase fw-bold mb-4">Sản phẩm</h6>'+
        '<p><a href="#!" class="text-reset">Uy tín</a></p>'+
        '<p><a href="#!" class="text-reset">Chất lượng</a></p>'+
        '<p><a href="#!" class="text-reset">Nguồn gốc rõ ràng</a></p>'+
        '<p><a href="#!" class="text-reset">Giá rẻ</a></p>'+
        '</div>'+
        '<div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">'+
        '<h6 class="text-uppercase fw-bold mb-4">Dịch vụ</h6>'+
        '<p><a href="#!" class="text-reset">24/7</a></p>'+
        '<p><a href="#!" class="text-reset">bảo hành 6 tháng</a></p>'+
        '<p><a href="#!" class="text-reset">free ship</a></p>'+
        '<p><a href="#!" class="text-reset">lỗi 1 đổi 1</a></p>'+
        '</div>'+
        '<div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">'+
        '<h6 class="text-uppercase fw-bold mb-4">Liên hệ</h6>'+
        '<p><i class="fas fa-home me-3"></i> Hà nội, Việt Nam</p>'+
        '<p><i class="fas fa-envelope me-3"></i> shop@gmail.com</p>'+
        '<p><i class="fas fa-phone me-3"></i> + 01 234 567 88</p>'+
        '<p><i class="fas fa-print me-3"></i> + 01 234 567 89</p>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '</section>'+
        '</footer>'+
        '</div>'
    document.getElementById("footer").innerHTML = main
}
