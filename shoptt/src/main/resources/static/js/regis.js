async function regis() {

    //upload image
    var uploadimg = 'http://'+urlFirst+'/api/public/upload';
    const filePath = document.getElementById('file')
    const formData = new FormData()
    formData.append("file", filePath.files[0])
    const response = await fetch(uploadimg, {
        method: 'POST',
        headers: new Headers({

        }),
        body: formData
    });
    var linkimage = await response.text();



    var url = 'http://'+urlFirst+'/api/register'
    var username = document.getElementById("username").value
    var fullname = document.getElementById("fullname").value
    var email = document.getElementById("email").value
    var phone = document.getElementById("phone").value
    var langKey = 'en'
    var password = document.getElementById("password").value
    var repassword = document.getElementById("repassword").value
    var tenduong = document.getElementById("tenduong").value
    var village = document.getElementById("xa").value
    var user = {
        "username": username,
        "fullname": fullname,
        "email": email,
        "phone": phone,
        "langKey": langKey,
        "avatar": linkimage,
        "password": password,
        "tenDuong":tenduong,
        "village": {
            "id": village
        },
        "authorities": [
            "ROLE_USER"
        ]
    }
    if(password != repassword){
        alert("Mật khẩu không trùng khớp")
        return;
    }
    if(password === "" || repassword === ""){
        alert("mật khẩu không được để trống!")
        return;
    }
    const res = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(user)
    });
    var result = await res.text();
    console.log(result)
    if (result === '1') {
        alert("email đã tồn tại")
    }
    else if (result === '2') {
        alert("username đã tồn tại")
    }
    else if (result === '0') {
        swal({
                title: "Thông báo",
                text: "đăng ký thành công! hãy check email của bạn!",
                type: "success"
            },
            function(){
                window.location.replace(loginlink)
            });
    }
}

async function forgot() {
    var email = document.getElementById("emailforget").value
    var url = 'http://'+urlFirst+'/api/resetpass'
    const res = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        body: email
    });
    if(res.status < 300){
        swal({
                title: "Thông báo",
                text: "hãy check email của bạn để nhận mật khẩu mới!",
                type: "success"
            },
            function(){
                window.location.replace(loginlink)
            });
    }
}