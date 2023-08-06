
async function loadNewProduct(page) {
    var url = 'http://'+urlFirst+'/api/public/productNewIndexPage?size=8&page='+page;
    var urlimg = 'http://'+urlFirst+'/api/public/imageProByPro?id=';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listproduct = await response.json();
    var main = '';
    for (i = 0; i < listproduct.content.length; i++) {
        var mainimg = '';
        const res = await fetch(urlimg+listproduct.content[i].id, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var listimage = await res.json();
        for(j=0; j<listimage.length; j++){
            mainimg += '<img src="'+listimage[j].link+'">';
        }
        main += '<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3 signle-item" >'+
            '<a href="'+linkdetail+'?id='+listproduct.content[i].id+'"><img class="img-item" src="'+listproduct.content[i].imageBanner+'"></a>'+
            '<div class="small-image">'+
            mainimg+
            '</div>'+
            '<div class="content-item-signle">'+
            '<a href=""><p class="name-signle-item">'+listproduct.content[i].name+'</p>'+
            '<p style="text-align: center;" class="price-item">'+formatmoney(listproduct.content[i].price)+'</p>'+
            '</div>'+
            '</div>'
    }
    document.getElementById("listNewProduct").innerHTML = main


    var totalPage = listproduct.totalPages

    var mainpage = ''
    for(i=1; i<= totalPage; i++){
        mainpage += '<li class="page-item"><button onclick="loadNewProduct('+(i-1)+')" class="page-link">'+i+'</button></li>'
    }
    document.getElementById("listpage").innerHTML = mainpage
}

function checkSpMơi(){
    document.getElementById("profile-tab").style.color = '#000'
    document.getElementById("home-tab").style.color = '#b5ae9a'
}

async function loadspbanchay(page) {
    document.getElementById("profile-tab").style.color = '#b5ae9a'
    document.getElementById("home-tab").style.color = '#000'
    var url = 'http://'+urlFirst+'/api/public/getSpBanChay?size=8&page='+page;
    var urlimg = 'http://'+urlFirst+'/api/public/imageProByPro?id=';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listproduct = await response.json();
    var main = '';
    for (i = 0; i < listproduct.content.length; i++) {
        var mainimg = '';
        const res = await fetch(urlimg+listproduct.content[i].id, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var listimage = await res.json();
        for(j=0; j<listimage.length; j++){
            mainimg += '<img src="'+listimage[j].link+'">';
        }
        main += '<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3 signle-item" >'+
            '<a href="'+linkdetail+'?id='+listproduct.content[i].id+'"><img class="img-item" src="'+listproduct.content[i].imageBanner+'"></a>'+
            '<div class="small-image">'+
            mainimg+
            '</div>'+
            '<div class="content-item-signle">'+
            '<a href=""><p class="name-signle-item">'+listproduct.content[i].name+'</p>'+
            '<p style="text-align: center;" class="price-item">'+formatmoney(listproduct.content[i].price)+'</p>'+
            '</div>'+
            '</div>'
    }
    document.getElementById("listspbanchay").innerHTML = main


    var totalPage = listproduct.totalPages

    var mainpage = ''
    for(i=1; i<= totalPage; i++){
        mainpage += '<li class="page-item"><button onclick="loadspbanchay('+(i-1)+')" class="page-link">'+i+'</button></li>'
    }
    document.getElementById("listpagebanchay").innerHTML = mainpage
}


async function searchParam(page) {
    var search = window.location.search.split('search=')[1];
    var category = window.location.search.split('category=')[1];
    var urlimg = 'http://'+urlFirst+'/api/public/imageProByPro?id=';
    var url = ''
    var listproduct = null
    if(search == null && category == null){
        loadNewProduct(0);
        return;
    }
    if(search != null){
        url = 'http://'+urlFirst+'/api/public/search?s='+search+'&size=8&page='+page;
        const response = await fetch(url, {
            method: 'GET',
            headers: new Headers({
            })
        });
        listproduct = await response.json();
    }
    if(category != null){
        var url = 'http://'+urlFirst+'/api/public/searchFull?size=8&page='+page;
        var searchDto = {
            "categories":category,
            "priceSmall":0,
            "priceLarge":1000000000
        }
        const response = await fetch(url, {
            method: 'POST',
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body:JSON.stringify(searchDto)
        });
        listproduct = await response.json();
    }

    var main = '';
    for (i = 0; i < listproduct.content.length; i++) {
        var mainimg = '';
        const res = await fetch(urlimg+listproduct.content[i].id, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var listimage = await res.json();
        for(j=0; j<listimage.length; j++){
            mainimg += '<img src="'+listimage[j].link+'">';
        }
        main += '<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3 signle-item" >'+
            '<a href="'+linkdetail+'?id='+listproduct.content[i].id+'"><img class="img-item" src="'+listproduct.content[i].imageBanner+'"></a>'+
            '<div class="small-image">'+
            mainimg+
            '</div>'+
            '<div class="content-item-signle">'+
            '<a href=""><p class="name-signle-item">'+listproduct.content[i].name+'</p>'+
            '<p style="text-align: center;" class="price-item">'+formatmoney(listproduct.content[i].price)+'</p>'+
            '</div>'+
            '</div>'
    }
    document.getElementById("listNewProduct").innerHTML = main


    var totalPage = listproduct.totalPages

    var mainpage = ''
    for(i=1; i<= totalPage; i++){
        mainpage += '<li class="page-item"><button onclick="searchFull('+(i-1)+')" class="page-link">'+i+'</button></li>'
    }
    document.getElementById("listpage").innerHTML = mainpage
}


async function loadSearchCategory() {
    var url = 'http://'+urlFirst+'/api/public/allcategory';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listcategory = await response.json();
    var main = '';
    var checked = ''
    for (i = 0; i < listcategory.length; i++) {
        if(i == 0){
            checked = 'checked'
        }
        else{
            checked = ''
        }
        main += ' <label class="radio-custom">'+listcategory[i].name+''+
            '<input '+checked+' class="loaisp" name="loaisp" value="'+listcategory[i].id+'" type="radio">'+
            '<span class="checkmark"></span>'+
            '</label>'
    }
    document.getElementById("listsearchCategory").innerHTML = main
}



async function searchFull(page) {
    var category = null
    var khoanggia = null;
    var checkCate = document.getElementsByClassName("loaisp");
    for (i = 0; i < checkCate.length; i++) {
        if (checkCate[i].checked == true) {
            category = checkCate[i].value
        }
    }
    var radios = document.getElementsByName('khoangia');
    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            khoanggia = radios[i].value
            break;
        }
    }
    var searchDto = {
        "categories":category,
        "priceSmall":khoanggia.split("-")[0],
        "priceLarge":khoanggia.split("-")[1]
    }

    var url = 'http://'+urlFirst+'/api/public/searchFull?size=8&page='+page;
    var urlimg = 'http://'+urlFirst+'/api/public/imageProByPro?id=';
    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        body:JSON.stringify(searchDto)
    });
    var listproduct = await response.json();
    var main = '';
    for (i = 0; i < listproduct.content.length; i++) {
        var mainimg = '';
        const res = await fetch(urlimg+listproduct.content[i].id, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var listimage = await res.json();
        for(j=0; j<listimage.length; j++){
            mainimg += '<img src="'+listimage[j].link+'">';
        }
        main += '<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3 signle-item" >'+
            '<a href="'+linkdetail+'?id='+listproduct.content[i].id+'"><img class="img-item" src="'+listproduct.content[i].imageBanner+'"></a>'+
            '<div class="small-image">'+
            mainimg+
            '</div>'+
            '<div class="content-item-signle">'+
            '<a href=""><p class="name-signle-item">'+listproduct.content[i].name+'</p>'+
            '<p style="text-align: center;" class="price-item">'+formatmoney(listproduct.content[i].price)+'</p>'+
            '</div>'+
            '</div>'
    }
    document.getElementById("listNewProduct").innerHTML = main


    var totalPage = listproduct.totalPages

    var mainpage = ''
    for(i=1; i<= totalPage; i++){
        mainpage += '<li class="page-item"><button onclick="searchFull('+(i-1)+')" class="page-link">'+i+'</button></li>'
    }
    document.getElementById("listpage").innerHTML = mainpage
}





const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function formatmoney(money) {
    return VND.format(money);
}


async function loadAProduct() {
    var id = window.location.search.split('=')[1];
    if(id != null){
        var url = 'http://'+urlFirst+'/api/productByID?id='+id;
        const response = await fetch(url, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var product = await response.json();

        document.getElementById("name-detail").innerText = product.name
        document.getElementById("price-detail").innerText =formatmoney( product.price)
        document.getElementById("img-detail").src = product.imageBanner
        document.getElementById("motasp").innerHTML += product.description


        var urlimg = 'http://'+urlFirst+'/api/public/imageProByPro?id='+product.id;

        var mainimg = '<div class="singel-img-detail">'+
            '<img onmouseover="changeImageDetail(this)" class="list-img-detail" src="'+product.imageBanner+'">'+
            '</div>';
        const res = await fetch(urlimg, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var listimage = await res.json();
        console.log(listimage)
        for(j=0; j<listimage.length; j++){
            mainimg += '<div class="singel-img-detail">'+
                '<img onmouseover="changeImageDetail(this)" class="list-img-detail" src="'+listimage[j].link+'">'+
                '</div>';
        }
        document.getElementById("list-image-detail").innerHTML = mainimg
    }
    else{
        alert("khÔng tìm thấy sản phẩm này")
        window.location.replace(linkindex)
    }
}

async function loadSpCungDanhMuc(){
    var id = window.location.search.split('=')[1];
    var url = 'http://'+urlFirst+'/api/public/sanPhamCungDanhMuc?id='+id;
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listproduct = await response.json();
    var main = ''
    for(i=0; i< listproduct.length; i++){
        main += '<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 signle-item" >'+
            '<a href="'+linkdetail+'?id='+listproduct[i].id+'"><img class="img-item" src="'+listproduct[i].imageBanner+'"></a>'+
            '<div class="content-item-signle">'+
            '<a href=""><p class="name-signle-item">'+listproduct[i].name+'</p>'+
            '<p style="text-align: center;" class="price-item">'+formatmoney(listproduct[i].price)+'</p>'+
            '</div>'+
            '</div>'
    }
    document.getElementById("spcungdanhmuc").innerHTML = main
}

function changeImageDetail(e){
    document.getElementById("img-detail").src = e.src
}