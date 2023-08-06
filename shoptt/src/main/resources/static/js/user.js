var token = localStorage.getItem("token");

async function loadUser() {
    if(token == null){
        alert("bạn chưa đăng nhập")
        window.location.replace(loginlink)
    }
    var url = 'http://'+urlFirst+'/api/userlogged';
    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var user = await response.json();
    console.log(user)
    document.getElementById("fullname").value = user.fullname
    document.getElementById("phone").value = user.phone
    document.getElementById("email").value = user.email
}


async function changeUser() {
    var token = localStorage.getItem("token");
    var url = 'http://'+urlFirst+'/api/user/updateinfor';
    var fullname = document.getElementById("fullname").value
    var phone = document.getElementById("phone").value
    var email = document.getElementById("email").value
    if(fullname == "" || phone== "" || email ==""){
        alert("dữ liệu không được để trống");
        return;
    }
    var userDto = {
        "fullname":fullname,
        "phone":phone,
        "email":email
    }

    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(userDto)
    });
    if (response.status < 300) {
        swal({
                title: "Thông báo",
                text: "cập nhật thông tin tài khoản thành công!",
                type: "success"
            },
            function(){
                window.location.reload()
            });
    }
    else {
        swal({
                title: "Thông báo",
                text: "cập nhật thông tin tài khoản thất bại",
                type: "error"
            },
            function(){ });
    }
}


async function changePassword() {
    var token = localStorage.getItem("token");
    var oldpass = document.getElementById("oldpass").value
    var newpass = document.getElementById("newpass").value
    var renewpass = document.getElementById("renewpass").value
    var url = 'http://'+urlFirst+'/api/user/changePassword?old='+oldpass+"&new="+newpass;
    if(fullname == "" || phone== "" || email ==""){
        alert("dữ liệu không được để trống");
        return;
    }
    if(newpass != renewpass){
        alert("mật khẩu mới không trùng khớp");
        return;
    }

    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    if (response.status < 300) {
        swal({
                title: "Thông báo",
                text: "cập nhật mật khẩu thành công, hãy đăng nhập lại",
                type: "success"
            },
            function(){
                window.location.reload()
            });
    }
    else {
        swal({
                title: "Thông báo",
                text: "cập nhật mật khẩun thất bại, mật khẩu không chính xác",
                type: "error"
            },
            function(){ });
    }
}