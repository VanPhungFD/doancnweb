var listcart = localStorage.getItem("cartshoptt");
function muangay(){
    document.getElementById("soluongdetail").value = 1;
    addCart();
    window.location.replace(linkcart)
}

async function addCart(){
    var id = window.location.search.split('=')[1];
    var url = 'http://'+urlFirst+'/api/productByID?id='+id;
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var data = await response.json();

    var numbers = document.getElementById("soluongdetail").value
    if(numbers == "" ){return;}
    if(Number(numbers) < Number(1) ){
        swal({title: "Thông báo", text: "số lượng sản phẩm phải lớn hơn hoặc bằng 1!", type: "error"},
            function(){ });return;
    }
    if(Number(numbers) > Number(data.quantity)){
        swal({title: "Thông báo", text: "số lượng sản phẩm không được quá: "+data.quantity, type: "error"},
            function(){  });return;
    }
    var product = {
        "id": data.id,
        "name":data.name,
        "image":data.imageBanner,
        "price": data.price,
        "quantity": numbers
    };
    if (localStorage.getItem("cartshoptt") === null) {
        var listproduct = [];
        listproduct.push(product);
        window.localStorage.setItem('cartshoptt', JSON.stringify(listproduct));
    }
    else{
        var list = JSON.parse(localStorage.getItem("cartshoptt"));
        var check = 0;
        for(i=0; i<list.length; i++){
            if(list[i].id === data.id){
                list[i].quantity = Number(numbers)+Number(list[i].quantity);
                check = 1;
            }
        }
        if(check === 0){
            list.push(product);
        }
        window.localStorage.setItem('cartshoptt', JSON.stringify(list));
    }

    if(response.status < 300){
        swal({title: "Thông báo", text: "thêm giỏ hàng thành công!", type: "success"},
            function(){
                window.location.reload()
            });
    }
}

function showAllCart(){
    var list = JSON.parse(localStorage.getItem("cartshoptt"));
    var main = '';
    var total = 0;
    var down = -1;
    var up = 1;
    var tongtien = 0;
    var tongsl = Number(0);
    for(i=0; i<list.length; i++){
        total = list[i].price*list[i].quantity;
        tongtien += list[i].price*list[i].quantity;
        tongsl = Number(list[i].quantity)+ Number(tongsl);
        main += '<tr>'+
            '<td><img class="img-cart" src="'+list[i].image+'"></td>'+
            '<td><a class="linkcart" href="'+linkdetail+'?id='+list[i].id+'">'+list[i].name+'</a></td>'+
            '<td class="range-price"><button onclick="updateQuantity('+list[i].id+','+down+')">-</button><input value="'+list[i].quantity+'" type="number"><button onclick="updateQuantity('+list[i].id+','+up+')">+</button></td>'+
            '<td>'+formatmoneyCart(total)+'</td>'+
            '<td><button onclick="remove('+list[i].id+')"><i class="fa fa-trash"></i></button></td>'+
            '</tr>';
    }

    document.getElementById("list-cart").innerHTML = main;
    document.getElementById("tongtien").innerText = formatmoneyCart(tongtien)
    document.getElementById("tongsl").innerText = tongsl
}

function updateQuantity(id, sl){
    var list = JSON.parse(localStorage.getItem("cartshoptt"));
    for(i=0; i<list.length; i++){
        if(list[i].id == id){
            list[i].quantity = Number(list[i].quantity) + sl
        }
        if(Number(list[i].quantity) < Number(1)){
            var remainingArr = list.filter(data => data.id != list[i].id);
            window.localStorage.setItem('cartshoptt', JSON.stringify(remainingArr));
        }
        else{
            window.localStorage.setItem('cartshoptt', JSON.stringify(list));
        }
    }
    showAllCart();
}



function remove(id){
    var list = JSON.parse(localStorage.getItem("cartshoptt"));
    var remainingArr = list.filter(data => data.id != id);
    window.localStorage.setItem('cartshoptt', JSON.stringify(remainingArr));
    swal({title: "Thông báo", text: "đã xóa sản phẩm khỏi giỏ hàng!", type: "success"},
        function(){
            window.location.reload()
        });
}


function formatmoneyCart(money) {
    var VND = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
    return VND.format(money);
}

function showInforCartCheckOut(){
    var list = JSON.parse(localStorage.getItem("cartshoptt"));
    var main = '';
    var total = 0;
    var tongtien = 0;
    var tongsl = Number(0);
    for(i=0; i<list.length; i++){
        total = list[i].price*list[i].quantity;
        tongtien += list[i].price*list[i].quantity;
        tongsl = Number(list[i].quantity) + Number(tongsl);;
    }

    document.getElementById("tongtien").innerText = formatmoneyCart(tongtien)
    document.getElementById("tongsl").innerText = tongsl
}
