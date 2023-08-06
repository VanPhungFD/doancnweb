var token = localStorage.getItem("token");
async function loadAllCategory() {
    var url = 'http://'+urlFirst+'/api/public/allcategory';
    const response = await fetch(url, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var listcategory = await response.json();
    var main = '';
    for (i = 0; i < listcategory.length; i++) {
        main += '<tr>'+
            '<td>#'+listcategory[i].id+'</td>'+
            '<td>'+listcategory[i].name+'</td>'+
            '<td><button onclick="deleteCategory('+listcategory[i].id+')" class="btn btn-danger"><i class="fa fa-trash"></i> Xóa</button></td>'+
            '<td><a href='+linkAddCategory+'?id='+listcategory[i].id+' class="btn btn-success"><i class="fa fa-trash"></i> Sửa</a></td>'+
            '</tr>'
    }
    document.getElementById("listcategory").innerHTML = main
    $('#example').DataTable();
}

async function loadACategory() {
    var id = window.location.search.split('=')[1];
    if(id != null){
        var url = 'http://'+urlFirst+'/api/public/categoryById?id='+id;
        const response = await fetch(url, {
            method: 'GET',
            headers: new Headers({
            })
        });
        var category = await response.json();
        document.getElementById("idcategory").value = category.id
        document.getElementById("catename").value = category.name
    }
}

async function saveCategory() {
    var id = window.location.search.split('=')[1];

    var url = 'http://'+urlFirst+'/api/admin/addOrUpdateCategory';
    var tencategory = document.getElementById("catename").value
    if(tencategory == ""){
        alert("tên danh mục không được để trống");
        return;
    }
    var category = null
    if (id == null) {
        category = {
            "name": tencategory
        }
    }
    else {
        category = {
            "id": id,
            "name": tencategory
        }

    }
    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(category)
    });
    if (response.status < 300) {
        swal({
                title: "Thông báo",
                text: "thêm/sửa danh mục thành công!",
                type: "success"
            },
            function(){
                window.location.replace(linkcategory)
            });
    }
    else {
        swal({
                title: "Thông báo",
                text: "thêm/sửa danh mục thất bại, Tên danh mục đã tồn tại",
                type: "error"
            },
            function(){
                window.location.reload();
            });
    }
}


async function deleteCategory(id) {
    var url = 'http://'+urlFirst+'/api/admin/deleteCategory?id=' + id;
    const response = await fetch(url, {
        method: 'DELETE',
        headers: new Headers({
            'Authorization': 'Bearer ' + token
        })
    });
    if (response.status < 300) {
        swal({
                title: "Thông báo",
                text: "xóa danh mục thành công!",
                type: "success"
            },
            function(){
                window.location.reload();
            });
    }
    else {
        swal({
                title: "Thông báo",
                text: "không thể xóa, đã có sản phẩm trong danh mục này",
                type: "error"
            },
            function(){
                window.location.reload();
            });
    }
}
