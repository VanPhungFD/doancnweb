var mot = null;
async function loadAddres() {
    var urladd = 'http://'+urlFirst+'/api/public/province';
    const response = await fetch(urladd, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var province = await response.json();
    var tinh = document.getElementById("tinh");
    tinh.innerHTML = ''
    for (i = 0; i < province.length; i++) {
        var option = document.createElement("option");
        option.text = province[i].name;
        option.value = province[i].id;
        tinh.add(option);
    }
}
async function loadHuyen() {
    var huyen = document.getElementById("huyen");
    huyen.innerHTML = '';
    var idtinh = document.getElementById("tinh").value
    var urladd = 'http://'+urlFirst+'/api/public/town?id='+idtinh;
    const res = await fetch(urladd, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var town = await res.json();
    for (i = 0; i < town.length; i++) {
        var option = document.createElement("option");
        option.text = town[i].name;
        option.value = town[i].id;
        huyen.add(option);
    }
}

async function loadXa() {
    var xa = document.getElementById("xa");
    xa.innerHTML = '';
    var idhuyen = document.getElementById("huyen").value
    var urlxa = 'http://'+urlFirst+'/api/public/village?id='+idhuyen;
    const res = await fetch(urlxa, {
        method: 'GET',
        headers: new Headers({
        })
    });
    var village = await res.json();
    for (i = 0; i < village.length; i++) {
        var option = document.createElement("option");
        option.text = village[i].name;
        option.value = village[i].id;
        xa.add(option);
    }
}



