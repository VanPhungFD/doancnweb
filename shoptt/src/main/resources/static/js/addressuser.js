var token = localStorage.getItem("token");

async function loadAddressUser() {

    if(token === null){
        alert("bạn chưa đăng nhập");window.location.replace(loginlink);
    }

    var url = 'http://'+urlFirst+'/api/user/addressUser';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var listAddress = await response.json();
    console.log(listAddress)
    var main = '';
    for (i = 0; i < listAddress.length; i++) {
        main += '<tr>'+
            '<td>#'+listAddress[i].id+'</td>'+
            '<td>'+listAddress[i].tenDuong+'</td>'+
            '<td>'+listAddress[i].village.name+'</td>'+
            '<td>'+listAddress[i].village.town.name+'</td>'+
            '<td>'+listAddress[i].village.town.province.name+'</td>'+
            '<td><button onclick="deleteAddUser('+listAddress[i].id+')" class="btn btn-danger">Xóa</button></td>'+
            '<td><button onclick="loadAnAddress('+listAddress[i].id+')" class="btn btn-success">Sửa</button></td>'+
            '</tr>'
    }
    document.getElementById("listAdd").innerHTML = main
}


async function deleteAddUser(id) {
    swal({
            title: "Thông báo",
            text: "Bạn muốn xóa địa chỉ này?",
            type: "warning"
        },
        async function(){
            var url = 'http://'+urlFirst+'/api/user/deleteAdressUser?id=' + id;
            const response = await fetch(url, {
                method: 'DELETE',
                headers: new Headers({
                    'Authorization': 'Bearer ' + token
                })
            }); window.location.reload();

        });


}

async function updateAddress() {
    var url = 'http://'+urlFirst+'/api/user/addAddressUser';
    var id = document.getElementById("idadduser").value
    var tenduong = document.getElementById("tenduongu").value
    var village = document.getElementById("xa").value
    var addUser = {
        "id":id,
        "tenDuong":tenduong,
        "village":{
            "id":village
        }
    }

    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(addUser)
    });
    if(response.status < 300){
        swal({
                title: "Thông báo",
                text: "thêm/sửa địa chỉ nhận hàng thành công!",
                type: "success"
            },
            function(){
                window.location.reload()
            });
    }else {
        swal({
                title: "Thông báo",
                text: "thêm/sửa địa chỉ nhận hàng thất bại",
                type: "error"
            },
            function(){
                window.location.reload();
            });
    }
}





async function loadAnAddress(id) {
    var url = 'http://'+urlFirst+'/api/user/addressUserById?id='+id;
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var add = await response.json();
    document.getElementById("tenduongu").value = add.tenDuong
    document.getElementById("idadduser").value = add.id

    const tinh = document.querySelector('#tinh');
    tinh.value = add.village.town.province.id
    loadHuyenUser(add.village.town.id, add.village.id);
}

async function loadHuyenUser(idhuyen, idxa) {
    var huyen = document.getElementById("huyen");
    huyen.innerHTML = '';
    var idtinh = document.getElementById("tinh").value
    var urladd = 'http://'+urlFirst+'/api/public/town?id='+idtinh;
    const res = await fetch(urladd, {
        method: 'GET',
        headers: new Headers({ })
    });
    var town = await res.json();
    for (i = 0; i < town.length; i++) {
        var option = document.createElement("option");
        option.text = town[i].name;
        option.value = town[i].id;
        huyen.add(option);
        if(town[i].id == idhuyen){
            option.selected = true
        }
    }
    loadXaUser(idxa)
}

async function loadXaUser(idXa) {
    var xa = document.getElementById("xa");
    xa.innerHTML = '';
    var idhuyen = document.getElementById("huyen").value
    var urlxa = 'http://'+urlFirst+'/api/public/village?id='+idhuyen;
    const res = await fetch(urlxa, {
        method: 'GET',
        headers: new Headers({ })
    });
    var village = await res.json();
    for (i = 0; i < village.length; i++) {
        var option = document.createElement("option");
        option.text = village[i].name;
        option.value = village[i].id;
        xa.add(option);
        if(village[i].id == idXa){
            option.selected = true
        }
    }
}