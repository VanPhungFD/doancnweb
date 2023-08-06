
var token = localStorage.getItem("token");
async function checkRoleAdmin(){
    var token = localStorage.getItem("token");
    if(token === null){
        window.location.replace(loginlink)
        return
    }
    var url = 'http://'+urlFirst+'/api/admin/checkroleadmin';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    if(response.status > 300){
    alert("need admin role")
        window.location.replace(loginlink)
    }
}
function loadMenuAdmin(){
    var main = '<nav class="nav nav-dark ">'+
        '<div> '+
        '<a href="#" class="nav_logo"> '+
        '<i class="bx bx-layer nav_logo-icon"></i> <span class="nav_logo-name">Quản trị</span> '+
        '</a>'+
        '<div class="nav_list"> '+
        '<a href="'+linkindexAdmin+'" class="nav_link"> '+
        '<i class="fa fa-home"></i> <span class="nav_name">Trang chủ</span> '+
        '</a> '+
        '<a href="'+linkuser+'" class="nav_link"> '+
        '<i class="bx bx-user nav_icon"></i> <span class="nav_name">Người dùng</span> '+
        '</a> '+
        '<a href="'+linksanpham+'" class="nav_link"> '+
        '<i class="fas fa-tshirt"></i> <span class="nav_name">Sản phẩm</span> '+
        '</a> '+
        '<a href="'+linkdonhang+'" class="nav_link"> '+
        '<i class="fa fa-shopping-cart"></i> <span class="nav_name">Đơn hàng</span> '+
        '</a> '+
        '<a href="'+linkcategory+'" class="nav_link"> '+
        '<i class="fa fa-list-alt"></i> <span class="nav_name">Danh mục sản phẩm</span> '+
        '</a> '+
        '<a href="'+linkblogadmin+'" class="nav_link"> '+
        '<i class="fa fa-list-alt"></i> <span class="nav_name">Bài viết</span> '+
        '</a> '+
        '</div>'+
        '</div> '+
        '<a href="#" class="nav_link"> '+
        '<i class="bx bx-log-out nav_icon"></i> <span onclick="logoutAdmin()" class="nav_name">SignOut</span> '+
        '</a>'+
        '</nav>'

    document.getElementById("nav-bar").innerHTML = main;
    document.getElementById("img-login").src = 'https://i.imgur.com/hczKIze.jpg'
}


function logoutAdmin(){
    localStorage.removeItem("token");
    window.location.replace(loginlinkadmin)
}







document.addEventListener("DOMContentLoaded", function(event) {

    const showNavbar = (toggleId, navId, bodyId, headerId) =>{
        const toggle = document.getElementById(toggleId),
            nav = document.getElementById(navId),
            bodypd = document.getElementById(bodyId),
            headerpd = document.getElementById(headerId)
        nav.classList.toggle('show')
        // change icon
        toggle.classList.toggle('bx-x')
        // add padding to body
        bodypd.classList.toggle('body-pd')
        // add padding to header
        headerpd.classList.toggle('body-pd')
        // Validate that all variables exist
        if(toggle && nav && bodypd && headerpd){
            toggle.addEventListener('click', ()=>{
                // show navbar
                nav.classList.toggle('show')
                // change icon
                toggle.classList.toggle('bx-x')
                // add padding to body
                bodypd.classList.toggle('body-pd')
                // add padding to header
                headerpd.classList.toggle('body-pd')
            })
        }
    }

    showNavbar('header-toggle','nav-bar','body-pd','header')

    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')

    function colorLink(){
        if(linkColor){
            linkColor.forEach(l=> l.classList.remove('active'))
            this.classList.add('active')
        }
    }
    linkColor.forEach(l=> l.addEventListener('click', colorLink))

    // Your code to run since DOM is loaded and ready

});

