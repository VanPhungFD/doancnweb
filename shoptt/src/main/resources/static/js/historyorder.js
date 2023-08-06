var token = localStorage.getItem("token");

async function loadListOrder() {
    var url = 'http://'+urlFirst+'/api/user/invoiceMySelf';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    var listInvoice = await response.json();
    var main = '';
    var trangthai = ''
    var paytype = 'thanh toán khi nhận hàng'

    for (i = 0; i < listInvoice.length; i++) {
        if(listInvoice[i].payType == 1){
            paytype = 'đã thanh toán qua momo '
        }
        else{
            paytype = 'thanh toán khi nhận hàng'
        }
        if(listInvoice[i].trangThai.id == 1){
            trangthai = 'dangcho'
        }
        if(listInvoice[i].trangThai.id == 2){
            trangthai = 'dataodon'
        }
        if(listInvoice[i].trangThai.id == 3){
            trangthai = 'dagui'
        }
        if(listInvoice[i].trangThai.id == 4){
            trangthai = 'danhanhang'
        }
        if(listInvoice[i].trangThai.id == 5){
            trangthai = 'dahuy'
        }
        if(listInvoice[i].trangThai.id == 6){
            trangthai = 'dahuy'
        }
        main += '<tr>'+
            '<td>#'+listInvoice[i].id+'</td>'+
            '<td>'+formatmoneyOrder(listInvoice[i].totalAmount)+'</td>'+
            '<td>'+listInvoice[i].numOfProduct+'</td>'+
            '<td>'+listInvoice[i].createdDate+'</td>'+
            '<td>'+paytype+'</td>'+
            '<td><p class="'+trangthai+'">'+listInvoice[i].trangThai.name+'</p></td>'+
            '<td><button onclick="chitietDh('+listInvoice[i].id+')" data-bs-toggle="modal" data-bs-target="#chitietdonhang" class="xemchitiet-dh">Xem chi tiết</button></td>'+
            '<td><button onclick="huyDon('+listInvoice[i].id+','+listInvoice[i].payType+')" class="huy-dh">Hủy đơn</button></td>'+
            '</tr>'
    }
    document.getElementById("listorder").innerHTML = main
}


async function huyDon(iddh, loaidon){
    if(loaidon == 1){
        swal({title: "Thông báo", text: "Đơn hàng đã thanh toán, không thể hủy!", type: "warning"},
            function(){
                return;
            });
    }
    else{
        var url = 'http://'+urlFirst+'/api/user/huydon?id='+iddh;
        const response = await fetch(url, {
            method: 'DELETE',
            headers: new Headers({
                'Authorization': 'Bearer ' + token
            })
        });
        if(response.status < 300){
            swal({title: "Thông báo", text: "đã hủy đơn hàng!", type: "success"},
                function(){
                    window.location.reload()
                });
        }
        else{
            swal({title: "Thông báo", text: "không thể hủy đơn hàng này!", type: "error"},
                function(){ });
        }
    }
}


async function chitietDh(id){
    var url = 'http://'+urlFirst+'/api/user/detailinvoiceByInvoice?id='+id;
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
            '<td><img src="'+listDtInvoice[i].product.imageBanner+'" class="img-invoice-detail"></td>'+
            '<td>'+listDtInvoice[i].product.name+'</td>'+
            '<td>'+listDtInvoice[i].quantity+'</td>'+
            '<td>'+formatmoneyOrder(listDtInvoice[i].price)+'</td>'+
            '</tr>'
    }
    document.getElementById("listorderdt").innerHTML = main
}


function formatmoneyOrder(money) {
    var VND = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
    return VND.format(money);
}

