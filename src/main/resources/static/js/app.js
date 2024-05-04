var form = document.getElementById("myForm"),
    imgInput = document.querySelector(".img"),
    file = document.getElementById("imgInput"),
    marque = document.getElementById("marque"),
    categorie = document.getElementById("categorie"),
    prix = document.getElementById("prix"),
    description = document.getElementById("description"),
    quantité = document.getElementById("quantité"),
    date1 = document.getElementById("date1"),
    submitBtn = document.querySelector(".submit"),
    productInfo = document.getElementById("data"),
    modal = document.getElementById("productForm"),
    modalTitle = document.querySelector("#productForm .modal-title"),
    newproductBtn = document.querySelector(".newproduct")

let getData = localStorage.getItem('productProfile') ? JSON.parse(localStorage.getItem('productProfile')) : [];


let isEdit = false, editId
showInfo()

newproductBtn.addEventListener('click', ()=> {
    submitBtn.innerText = 'Submit',
    modalTitle.innerText = "Fill the Form"
    isEdit = false
    imgInput.src = "./image/Profile Icon.webp"
    form.reset()
})


file.onchange = function(){
    if(file.files[0].size < 1000000){  // 1MB = 1000000
        var fileReader = new FileReader();

        fileReader.onload = function(e){
            imgUrl = e.target.result
            imgInput.src = imgUrl
        }

        fileReader.readAsDataURL(file.files[0])
    }
    else{
        alert("This file is too large!")
    }
}


function showInfo(){
    document.querySelectorAll('.productDetails').forEach(info => info.remove())
    getData.forEach((element, index) => {
        let createElement = `<tr class="productDetails">
            <td>${index+1}</td>
            <td><img src="${element.picture}" alt="" width="50" height="50"></td>
            <td>${element.productMarque}</td>
            <td>${element.productCategorie}</td>
            <td>${element.productPrix}</td>
            <td>${element.productDescription}</td>
            <td>${element.productQuantite}</td>
            <td>${element.productDate}</td>
            


            <td>
                <button class="btn btn-success" onclick="readInfo('${element.picture}', '${element.productMarque}', '${element.productCategorie}', '${element.productPrix}', '${element.productDescription}', '${element.productQuantite}', '${element.productDate}')" data-bs-toggle="modal" data-bs-target="#readData"><i class="bi bi-eye"></i></button>

                <button class="btn btn-primary" onclick="editInfo(${index}, '${element.picture}', '${element.productMarque}', '${element.productCategorie}', '${element.productPrix}', '${element.productDescription}', '${element.productQuantite}', '${element.productDate}')" data-bs-toggle="modal" data-bs-target="#productForm"><i class="bi bi-pencil-square"></i></button>

                <button class="btn btn-danger" onclick="deleteInfo(${index})"><i class="bi bi-trash"></i></button>
                            
            </td>
        </tr>`

        productInfo.innerHTML += createElement
    })
}
showInfo()


function readInfo(pic, marque, categorie, prix, description, quantite, date1) {
    document.querySelector('.showImg').src = pic;
    document.querySelector("#showMarque").value = marque;
    document.querySelector("#showCatégorie").value = categorie;
    document.querySelector("#showPrix").value = prix;
    document.querySelector("#showDescription").value = description;
    document.querySelector("#showQuantité").value = quantite;
    document.querySelector("#showDate").value = date1;
}



function editInfo(index, pic, marque, categorie, prix, description, quantite, date1) {
    isEdit = true;
    editId = index;
    imgInput.src = pic;
    document.getElementById("marque").value = marque;
    document.getElementById("categorie").value = categorie;
    document.getElementById("prix").value = prix;
    document.getElementById("description").value = description;
    document.getElementById("quantité").value = quantite;
    document.getElementById("date1").value = date1;

    submitBtn.innerText = "Update";
    modalTitle.innerText = "Modifier les informations";
}



function deleteInfo(index){
    if(confirm("Are you sure want to delete?")){
        getData.splice(index, 1)
        localStorage.setItem("productProfile", JSON.stringify(getData))
    

        showInfo()
    }
}


form.addEventListener('submit', (e) => {
    e.preventDefault();

    const information = {
        picture: imgInput.src || "./image/Profile Icon.webp",
        productMarque: marque.value, // Ensure correct references
        productCategorie: categorie.value,
        productPrix: prix.value,
        productDescription: description.value,
        productQuantite: quantité.value,
        productDate: date1.value
    };

    if (!isEdit) {
        getData.push(information);
    } else {
        getData[editId] = information;
        isEdit = false;
    }

    localStorage.setItem('productProfile', JSON.stringify(getData));

    submitBtn.innerText = "Submit";
    modalTitle.innerText = "Remplir les informations";

    showInfo(); // Refresh the table

    form.reset();
    imgInput.src = "./image/Profile Icon.webp";  
});
