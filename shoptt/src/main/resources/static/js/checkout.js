var token = localStorage.getItem("token");
async function initCheckout(){
    if(token === null){
        alert("hãy đăng nhập để sử dụng chức năng này")
        window.location.replace(loginlink)
    }
    else{
        var url = 'http://'+urlFirst+'/api/userlogged';
        const response = await fetch(url, {
            method: 'POST',
            headers: new Headers({
                'Authorization': 'Bearer ' + token
            })
        });
        var user = await response.json();
        document.getElementById("hotencheck").innerText = user.fullname
        document.getElementById("sdtcheck").innerText = user.phone
        showInforCartCheckOut();

        var urladd = 'http://'+urlFirst+'/api/user/addressUser';
        const res = await fetch(urladd, {
            method: 'GET',
            headers: new Headers({
                'Authorization': 'Bearer ' + token
            })
        });
        var listAddress = await res.json();
        var main = ''
        var checkd = ''
        for (i = 0; i < listAddress.length; i++) {
            if(i==0){
                checkd = 'checked="checked"'
            }
            else{
                checkd = ''
            }
            main += '<label class="radio-custom">Địa chỉ: '+listAddress[i].tenDuong+','+
                ''+listAddress[i].village.name+','+listAddress[i].village.town.name+','+listAddress[i].village.town.province.name+''+
                '<input class="diachinhan" value="'+listAddress[i].id+'" type="radio" name="diachinhan" '+checkd+'>'+
                '<span class="checkmark"></span>'+
                '</label><hr>'
        }
        document.getElementById("listdcnhanhang").innerHTML = main
    }
}


async function requestPayMentMomo(){
    var iddiachinhanhang = null
    var tongtien = 0;
    var noidung = null;
    var checkCate = document.getElementsByClassName("diachinhan");
    for (i = 0; i < checkCate.length; i++) {
        if (checkCate[i].checked == true) {
            iddiachinhanhang = checkCate[i].value
        }
    }
    var list = JSON.parse(localStorage.getItem("cartshoptt"));
    for(i=0; i<list.length; i++){
        tongtien += list[i].price*list[i].quantity;
    }
    noidung = document.getElementById("noidungtt").value
    if(noidung == ""){
        noidung = "thanh toán đơn hàng shoptt"
    }

    // checksl sanpham
    var url = 'http://'+urlFirst+'/api/productByID?id=';
    for(i=0; i<list.length; i++){
        var urls = url+list[i].id
        const res = await fetch(urls, {
            method: 'GET',
            headers: new Headers({ })
        });
        var product = await res.json();
        if(product.quantity < list[i].quantity){
            swal({title: "Thông báo", text: "sản phẩm: "+product.name+" chỉ còn: "+product.quantity+" sản phẩm, hãy nhập lại số lượng", type: "warning"},
                function(){
                    window.location.replace(linkcart)
                });
        }
    }

    // gui yeu cau thanh toan momo
    var urlinit = 'http://'+urlFirst+'/api/urlpayment';
    var paymentDto = {
        "amount":tongtien,
        "content":"thanh toán đơn hàng shoptt",
        "returnUrl":returnurl,
        "notifyUrl":returnurl
    }
    console.log(paymentDto)
    const res = await fetch(urlinit, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(paymentDto)
    });
    var urlReturn = await res.json();
    if(res.status < 300){
        window.sessionStorage.setItem('iddcnhanhang', iddiachinhanhang);
        window.sessionStorage.setItem('noidungnhan', noidung);
    }
    window.open(urlReturn.url, '_blank');

}


async function thanhtoantructiep(){
    var iddiachinhanhang = null
    var tongtien = 0;
    var noidung = null;
    var checkCate = document.getElementsByClassName("diachinhan");
    for (i = 0; i < checkCate.length; i++) {
        if (checkCate[i].checked == true) {
            iddiachinhanhang = checkCate[i].value
        }
    }
    var list = JSON.parse(localStorage.getItem("cartshoptt"));
    for(i=0; i<list.length; i++){
        tongtien += list[i].price*list[i].quantity;
    }
    noidung = document.getElementById("noidungtt").value
    if(noidung == ""){
        noidung = "thanh toán đơn hàng shoptt"
    }

    // checksl sanpham
    var url = 'http://'+urlFirst+'/api/productByID?id=';
    for(i=0; i<list.length; i++){
        var urls = url+list[i].id
        const res = await fetch(urls, {
            method: 'GET',
            headers: new Headers({ })
        });
        var product = await res.json();
        if(product.quantity < list[i].quantity){
            swal({title: "Thông báo", text: "sản phẩm: "+product.name+" chỉ còn: "+product.quantity+" sản phẩm, hãy nhập lại số lượng", type: "warning"},
                function(){
                    window.location.replace(linkcart)
                });
        }
    }
    var tongmh = Number(0);
    for(i=0; i<list.length; i++){
        tongmh = Number(list[i].quantity) + Number(tongmh);
    }

    // gui yeu cau thanh toan momo
    var urlinit = 'http://'+urlFirst+'/api/user/addInvoicett';
    var invoice = {
        "totalAmount":tongtien,
        "note":noidung,
        "addressUser":{
            "id":iddiachinhanhang
        },
        "numOfProduct":tongmh
    }
    const resp = await fetch(urlinit, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(invoice)
    });
    var invoiceResult = await resp.json();
    for(i=0; i<list.length; i++){
        var detail = {
            "price":list[i].price,
            "quantity":list[i].quantity,
            "totalAmount":list[i].price * list[i].quantity,
            "product":{
                "id":list[i].id
            },
            "invoice":{
                "id":invoiceResult.id
            }
        }
        var urlinvoice = 'http://'+urlFirst+'/api/user/addDetailInvoice'
        const resp = await fetch(urlinvoice, {
            method: 'POST',
            headers: new Headers({
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            }),body: JSON.stringify(detail)
        });
    }
    swal({title: "Thông báo", text: "Đặt hàng thành công!", type: "success"},
        function(){
            window.location.replace(linkhistoryorder)
        });
}


