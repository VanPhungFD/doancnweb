var token = localStorage.getItem("token");
const listFile = [];
async function loadAllProduct() {
    var url = 'http://'+urlFirst+'/api/allproduct';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listproduct = await response.json();
    var main = '';
    for (i = 0; i < listproduct.length; i++) {
        main += '<tr>'+
            '<td>#'+listproduct[i].id+'</td>'+
            '<td><img src="'+listproduct[i].imageBanner+'" class="img-sp-admin"></td>'+
            '<td>'+listproduct[i].name+'</td>'+
            '<td>'+listproduct[i].quantity+'</td>'+
            '<td>'+listproduct[i].createdDate+'</td>'+
            '<td>'+formatmoney(listproduct[i].price)+'</td>'+
            '<td>'+listproduct[i].salePercent+'%</td>'+
            '<td><button onclick="deleteProduct('+listproduct[i].id+')" class="btn btn-danger"><i class="fa fa-trash"></i> Xóa</button></td>'+
            '<td><a href='+linkAddProduct+'?id='+listproduct[i].id+' class="btn btn-success"><i class="fa fa-trash"></i> Sửa</a></td>'+
            // '<td><button data-bs-toggle="modal" data-bs-target="#motasp" class="btn btn-success"><i class="fa fa-trash"></i> Mô tả</button></td>'+
            '</tr>'
    }
    document.getElementById("listproduct").innerHTML = main
    $('#example').DataTable();
}

async function deleteImage(id) {
    var url = 'http://'+urlFirst+'/api/admin/deleteImageProduct?id='+id;
    const response = await fetch(url, {
        method: 'DELETE',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    if(response.status < 300){
        swal({
                title: "Thông báo",
                text: "xóa ảnh sản phẩm thành công!",
                type: "success"
            },
            function(){
                window.location.reload();
            });
    }
    else{
        swal({
                title: "Thông báo",
                text: "xóa ảnh sản phẩm thất bại!",
                type: "error"
            },
            function(){
            });
    }
}

async function deleteProduct(id){
    var url = 'http://'+urlFirst+'/api/admin/deleteProduct?id='+id;
    const response = await fetch(url, {
        method: 'DELETE',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    if(response.status < 300){
        swal({
                title: "Thông báo",
                text: "xóa sản phẩm thành công!",
                type: "success"
            },
            function(){
                window.location.reload();
            });
    }
    else{
        swal({
                title: "Thông báo",
                text: "xóa sản phẩm thất bại!",
                type: "error"
            },
            function(){
            });
    }
}

async function saveProduct() {
    document.getElementById("loading_uploadroom").style.display = 'block'
    var id = window.location.search.split('=')[1];

    var url = 'http://'+urlFirst+'/api/admin/addOrUpdateproduct';
    var tensp = document.getElementById("tensp").value
    var quantity = document.getElementById("quantity").value
    var price = document.getElementById("price").value
    var danhmuc = document.getElementById("danhmuc").value
    var description = tinyMCE.get('editor').getContent()

    const filePath = document.getElementById('imagebanner')
    const formData = new FormData()
    formData.append("file", filePath.files[0])
    var urlUpload = 'http://localhost:8080/api/public/upload';
    const res = await fetch(urlUpload, {
        method: 'POST',
        headers: new Headers({
        }),
        body: formData
    });
    var linkbanner = await res.text();

    if(res.status > 300){
        linkbanner = null;
    }

    if(tensp == "" || quantity == "" || price == "" || description == ""){
        alert("dữ liệu không được để trống");
        return;
    }

    var product = {
        "id": id,
        "name": tensp,
        "price":price,
        "imageBanner":linkbanner,
        "quantity":quantity,
        "description":description,
        "category":{
            "id":danhmuc
        }
    }

    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(product)
    });
    var result = await response.json();
    console.log(result)
    if (response.status < 300) {
        var urladdImage = 'http://'+urlFirst+'/api/admin/addImageproduct';
        var urlUpload = 'http://'+urlFirst+'/api/public/upload';

        for(i=0; i<listFile.length; i++){
            const formData = new FormData()
            formData.append("file", listFile[i])
            const res = await fetch(urlUpload, {
                method: 'POST',
                headers: new Headers({
                }),
                body: formData
            });
            var linkbanner = await res.text();

            var imgProduct = {
                "link":linkbanner,
                "product":{
                    "id":result.id
                }
            }
            const response = await fetch(urladdImage, {
                method: 'POST',
                headers: new Headers({
                    'Authorization': 'Bearer ' + token,
                    'Content-Type': 'application/json'
                }),
                body: JSON.stringify(imgProduct)
            });
        }
        swal({
                title: "Thông báo",
                text: "thêm/sửa sản phẩm thành công!",
                type: "success"
            },
            function(){
                document.getElementById("loading_uploadroom").style.display = 'none'
                window.location.replace(linksanpham)
            });
    }
    else {
        swal({
                title: "Thông báo",
                text: "thêm/sửa sản phẩm thất bại",
                type: "error"
            },
            function(){
                document.getElementById("loading_uploadroom").style.display = 'none'
                window.location.reload();
            });
    }
}

async function saveListImage(idproduct) {

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
        document.getElementById("tensp").value = product.name
        document.getElementById("id").value = product.id
        document.getElementById("quantity").value = product.quantity
        document.getElementById("price").value = product.price
        document.getElementById("danhmuc").value = product.category.id
        tinyMCE.get('editor').setContent(product.description)

        var url = 'http://'+urlFirst+'/api/public/allcategory';
        const res = await fetch(url, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var listcategory = await res.json();
        var main = '';
        var selected = '';
        for (i = 0; i < listcategory.length; i++) {
            if(listcategory[i].id ==  product.category.id){
                selected = 'selected'
            }
            else{
                selected = ''
            }
            main += '<option '+selected+' value="'+listcategory[i].id+'">'+listcategory[i].name+'</option>'
        }
        document.getElementById("danhmuc").innerHTML = main


        var url = 'http://'+urlFirst+'/api/public/imageProByPro?id='+id;
        const resp = await fetch(url, {
            method: 'GET',
            headers: new Headers({
            })
        });

        var listImage = await resp.json()
        var maini = ''
        for(i=0; i<listImage.length; i++){
            maini += '<div class="col-md-4">'+
                '<img style="width: 90%;" src="'+listImage[i].link+'" class="image-upload">'+
                '<button onclick="deleteImage('+listImage[i].id+')" class="btn btn-danger form-control">Xóa ảnh</button>'+
                '</div>'
        }
        document.getElementById("anhdathem").innerHTML += maini
    }
    else{
        document.getElementById("anhdathem").innerHTML = ''
    }
}

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function formatmoney(money) {
    return VND.format(money);
}


async function loadCategory() {
    var url = 'http://'+urlFirst+'/api/public/allcategory';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listcategory = await response.json();
    var main = '';
    for (i = 0; i < listcategory.length; i++) {
        main += '<option value="'+listcategory[i].id+'">'+listcategory[i].name+'</option>'
    }
    document.getElementById("danhmuc").innerHTML = main

}

function loadInit() {
    $('input#choosefile').change(function () {
        var files = $(this)[0].files;
    });
    document.querySelector('#choosefile').addEventListener("change", previewImages);
    function previewImages() {
        var files = $(this)[0].files;
        for (i = 0; i < files.length; i++) {
            listFile.push(files[i]);
        }

        var preview = document.querySelector('#preview');

        // if (this.files) {
        //     [].forEach.call(this.files, readAndPreview);
        // }
        for (i = 0; i < files.length; i++) {
            readAndPreview(files[i]);
        }
        function readAndPreview(file) {

            if (!/\.(jpe?g|png|gif)$/i.test(file.name)) {
                return alert(file.name + " is not an image");
            }

            var reader = new FileReader(file);

            reader.addEventListener("load", function () {
                document.getElementById("chon-anhs").className = 'col-sm-4';
                document.getElementById("chon-anhs").style.height = '100px';
                document.getElementById("chon-anhs").style.marginTop = '5px';
                document.getElementById("choose-image").style.height = '120px';
                document.getElementById("numimage").innerHTML = '';
                document.getElementById("camera").style.fontSize = '20px';
                document.getElementById("camera").style.marginTop = '40px';
                document.getElementById("camera").className = 'fas fa-plus';
                document.getElementById("choose-image").style.width = '90%';

                var div = document.createElement('div');
                div.className = 'col-md-4';
                div.style.height = '120px';
                div.marginTop = '100px';
                preview.appendChild(div);

                var img = document.createElement('img');
                img.src = this.result;
                img.style.height = '85px';
                img.style.width = '90%';
                img.className = 'image-upload';
                img.style.marginTop = '5px';
                div.appendChild(img);

                var button = document.createElement('button');
                button.style.height = '30px';
                button.style.width = '90%';
                button.innerHTML = 'xóa'
                button.className = 'btn btn-warning';
                div.appendChild(button);

                button.addEventListener("click", function () {
                    div.remove();
                    console.log(listFile.length)
                    for(i=0; i<listFile.length; i++){
                        if(listFile[i] === file){
                            listFile.splice(i, 1);
                        }
                    }
                    console.log(listFile.length)
                });
            });

            reader.readAsDataURL(file);

        }

    }

}
