var token = localStorage.getItem("token");

async function loadINdexAdmin(){
    var url = 'http://'+urlFirst+'/api/admin/thongkeindex';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var lists = await response.json()
    document.getElementById("tongdonhang").innerText = lists[0]
    document.getElementById("tongsp").innerText = lists[1]
    document.getElementById("tonguser").innerText = lists[2]

    var urls = 'http://'+urlFirst+'/api/admin/getUserNotAdminThisMonth';
    const res = await fetch(urls, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var listuser = await res.json()

    var main = ''
    for(i=0; i<listuser.length; i++){
        main += '<tr>'+
            '<td>#'+listuser[i].id+'</td>'+
            '<td>'+listuser[i].username+'</td>'+
            '<td>'+listuser[i].fullname+'</td>'+
            '<td>'+listuser[i].email+'</td>'+
            '<td>'+listuser[i].created_date+'</td>'+
            '</tr>'
    }
    document.getElementById("listnewuiser").innerHTML = main


    var urldh = 'http://'+urlFirst+'/api/admin/donhangmoi';
    const resp = await fetch(urldh, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var listdh = await resp.json()

    var mains = ''
    var loaitt = ''
    for(i=0; i<listdh.length; i++){
        if(+listdh[i].payType == 1){
            loaitt = 'thanh toán momo'
        }
        else{
            loaitt = 'thanh toán khi nhận hàng';
        }
        mains += '<tr>'+
            '<td>#'+listdh[i].id+'</td>'+
            '<td>'+listdh[i].createdDate+'</td>'+
            '<td>'+formatmoneyP(listdh[i].totalAmount)+'</td>'+
            '<td>'+loaitt+'</td>'+
            '<td>'+listdh[i].addressUser.user.username+'</td>'+
            '</tr>'
    }
    document.getElementById("listdhmoi").innerHTML = mains
}

async function loadAllTrangThai() {
    var url = 'http://'+urlFirst+'/api/public/trangthaiall';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listtrangthai = await response.json();
    var main = '';
    var mains = ''
    for (i = 0; i < listtrangthai.length; i++) {
        main += '<option value="'+listtrangthai[i].id+'">'+listtrangthai[i].name+'</option>'
        if(listtrangthai[i].id != 1){
            mains += '<option value="'+listtrangthai[i].id+'">'+listtrangthai[i].name+'</option>'
        }
    }
    document.getElementById("listtrangthai").innerHTML = main
    document.getElementById("trangthaicp").innerHTML = mains
    // $('#example').DataTable();
}

async function loadAllDonHang() {
    var url = 'http://'+urlFirst+'/api/admin/allInvoice';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var listInvoice = await response.json();
    var main = '';
    var paytype = 'thanh toán khi nhận hàng'
    for (i = 0; i < listInvoice.length; i++) {
        if(listInvoice[i].payType == 1){
            paytype = 'đã thanh toán qua momo '
        }else{
            paytype = 'thanh toán khi nhận hàng'
        }
        main += '<tr>'+
            '<td>#'+listInvoice[i].id+'</td>'+
            '<td>'+listInvoice[i].createdDate+'</td>'+
            '<td>'+listInvoice[i].trangThai.name+'</td>'+
            '<td>'+formatmoneyP(listInvoice[i].totalAmount)+'</td>'+
            '<td>'+paytype+'</td>'+
            '<td>'+listInvoice[i].note+'</td>'+
            '<td>'+listInvoice[i].addressUser.user.fullname+'</td>'+
            '<td>'+listInvoice[i].addressUser.tenDuong+', ' +listInvoice[i].addressUser.village.name+','+listInvoice[i].addressUser.village.town.name+','+listInvoice[i].addressUser.village.town.province.name+'</td>'+
            '<td><button onclick="setIdDonHang('+listInvoice[i].id+')" data-bs-toggle="modal" data-bs-target="#capnhatdonhang" class="btn btn-primary"><i class="fa fa-edit"></i> Cập nhật</button></td>'+
            '<td><button onclick="chitietDh('+listInvoice[i].id+')" data-bs-toggle="modal" data-bs-target="#chitietdonhang" class="btn btn-success"><i class="fa fa-list"></i> chi tiết</button></td>'+
            '</tr>'
    }
    document.getElementById("listinvoice").innerHTML = main
    $('#example').DataTable();
}

async function searchAll() {

    var start = document.getElementById("start").value
    var end = document.getElementById("end").value
    var trangthai = document.getElementById("listtrangthai").value
    var url = 'http://'+urlFirst+'/api/admin/allInvoicefull?id='+trangthai+"&start="+start+"&end="+end;
    if(start == "" || end == "" ){
        url = 'http://'+urlFirst+'/api/admin/allInvoicefull?id='+trangthai;
    }
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var listInvoice = await response.json();
    var main = '';
    var paytype = 'thanh toán khi nhận hàng'
    for (i = 0; i < listInvoice.length; i++) {
        if(listInvoice[i].payType == 1){
            paytype = 'đã thanh toán qua momo '
        }else{
            paytype = 'thanh toán khi nhận hàng'
        }
        main += '<tr>'+
            '<td>#'+listInvoice[i].id+'</td>'+
            '<td>'+listInvoice[i].createdDate+'</td>'+
            '<td>'+listInvoice[i].trangThai.name+'</td>'+
            '<td>'+formatmoneyP(listInvoice[i].totalAmount)+'</td>'+
            '<td>'+paytype+'</td>'+
            '<td>'+listInvoice[i].note+'</td>'+
            '<td>'+listInvoice[i].addressUser.user.fullname+'</td>'+
            '<td>'+listInvoice[i].addressUser.tenDuong+', ' +listInvoice[i].addressUser.village.name+','+listInvoice[i].addressUser.village.town.name+','+listInvoice[i].addressUser.village.town.province.name+'</td>'+
            '<td><button onclick="setIdDonHang('+listInvoice[i].id+')" data-bs-toggle="modal" data-bs-target="#capnhatdonhang" class="btn btn-primary"><i class="fa fa-edit"></i> Cập nhật</button></td>'+
            '<td><button onclick="chitietDh('+listInvoice[i].id+')" data-bs-toggle="modal" data-bs-target="#chitietdonhang" class="btn btn-success"><i class="fa fa-list"></i> chi tiết</button></td>'+
            '</tr>'
    }
    document.getElementById("listinvoice").innerHTML = main
    $('#example').DataTable();
}

async function setIdDonHang(id){
   // alert(2)
    document.getElementById("iddonhang").value = id
}

async function chitietDh(id){
    var url = 'http://'+urlFirst+'/api/admin/detailinvoiceByInvoiceAdmin?id='+id;
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var listDtInvoice = await response.json();
    var main = '';
    for (i = 0; i < listDtInvoice.length; i++) {
        main += '<tr>'+
            '<td><img style="width:150px" src="'+listDtInvoice[i].product.imageBanner+'" class="img-invoice-detail"></td>'+
            '<td>'+listDtInvoice[i].product.name+'</td>'+
            '<td>'+listDtInvoice[i].quantity+'</td>'+
            '<td>'+formatmoneyP(listDtInvoice[i].price)+'</td>'+
            '</tr>'
    }
    document.getElementById("listorderdt").innerHTML = main
}


async function openupdatedon(){
    var iddh = document.getElementById("iddonhang").value
    var idtrangthai = document.getElementById("trangthaicp").value
    var url = 'http://'+urlFirst+'/api/admin/updatetrangthai?id='+iddh+"&trangthaiid="+idtrangthai;
    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    if(response.status < 300){
        swal({
                title: "Thông báo",
                text: "cập nhật trạng thái thành công!",
                type: "success"
            },
            function(){
                window.location.reload();
            });
    }
    else{
        swal({
                title: "Thông báo",
                text: "cập nhật trạng thái thất bại!",
                type: "error"
            },
            function(){
                window.location.reload();
            });
    }
}


async function doanhthu(){
    var url = 'http://'+urlFirst+'/api/admin/calMoneySixMonth';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });

    var dt = await response.json();
    var thang = []
    var giatri = []
    for(i=0; i<dt.length; i++){
        thang.push(dt[i].split(",")[0])
        giatri.push(dt[i].split(",")[1])
    }
    thang = thang.reverse()
    giatri = giatri.reverse()
    var lb = 'doanh thu 6 tháng gần đây' ;
    const ctx = document.getElementById("chart").getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: thang,
            datasets: [{
                label: lb,
                backgroundColor: 'rgba(161, 198, 247, 1)',
                borderColor: 'rgb(47, 128, 237)',
                data: giatri,
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                    }
                }]
            }
        },
    });
}


function formatmoneyP(money) {
    var VND = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
    return VND.format(money);
}
