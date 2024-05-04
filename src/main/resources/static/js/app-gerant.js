var form = document.getElementById("myForm"),
    imgInput = document.querySelector(".img"),
    file = document.getElementById("imgInput"),
    Name = document.getElementById("name"),
    sexe = document.getElementById("sexe"),
    adresse = document.getElementById("adresse"),
    telephone = document.getElementById("telephone"),
    password = document.getElementById("password"),
    submitBtn = document.querySelector(".submit"),
    gerantInfo = document.getElementById("data"),
    modal = document.getElementById("gerantForm"),
    modalTitle = document.querySelector("#gerantForm .modal-title"),
    newgerantBtn = document.querySelector(".newgerant")

let getData = localStorage.getItem('gerantProfile') ? JSON.parse(localStorage.getItem('gerantProfile')) : [];


let isEdit = false, editId
showInfo()

newgerantBtn.addEventListener('click', ()=> {
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
    document.querySelectorAll('.gerantDetails').forEach(info => info.remove())
    getData.forEach((element, index) => {
        let createElement = `<tr class="gerantDetails">
            <td>${index+1}</td>
            <td><img src="${element.picture}" alt="" width="50" height="50"></td>
            <td>${element.gerantName}</td>
            <td>${element.gerantSexe}</td>
            <td>${element.gerantAdresse}</td>
            <td>${element.geranttelephone}</td>
            <td>${element.gerantEmail}</td>
            <td>${element.gerantPassword}</td>
            


            <td>
                <button class="btn btn-success" onclick="readInfo('${element.picture}', '${element.gerantName}', '${element.gerantSexe}', '${element.gerantAdresse}', '${element.geranttelephone}', '${element.gerantEmail}', '${element.gerantPassword}')" data-bs-toggle="modal" data-bs-target="#readData"><i class="bi bi-eye"></i></button>

                <button class="btn btn-primary" onclick="editInfo(${index}, '${element.picture}', '${element.gerantName}', '${element.gerantSexe}', '${element.gerantAdresse}', '${element.geranttelephone}', '${element.gerantEmail}', '${element.gerantPassword}')" data-bs-toggle="modal" data-bs-target="#gerantForm"><i class="bi bi-pencil-square"></i></button>

                <button class="btn btn-danger" onclick="deleteInfo(${index})"><i class="bi bi-trash"></i></button>
                            
            </td>
        </tr>`

        gerantInfo.innerHTML += createElement
    })
}
showInfo()


function readInfo(pic, Name, sexe, adresse, telephone, email, password) {
    document.querySelector('.showImg').src = pic;
    document.querySelector("#showName").value = Name;
    document.querySelector("#showSexe").value = sexe;
    document.querySelector("#showAdresse").value = adresse;
    document.querySelector("#showTelephone").value = telephone;
    document.querySelector("#showemail").value = email;
    document.querySelector("#showPassword").value = password;
}



function editInfo(index, pic, Name, sexe, adresse, telephone, email, password) {
    isEdit = true;
    editId = index;
    imgInput.src = pic;
    document.getElementById("name").value = Name;
    document.getElementById("sexe").value = sexe;
    document.getElementById("adresse").value = adresse;
    document.getElementById("telephone").value = telephone;
    document.getElementById("email").value = email;
    document.getElementById("password").value = password;

    submitBtn.innerText = "Update";
    modalTitle.innerText = "Modifier les informations";
}



function deleteInfo(index){
    if(confirm("Are you sure want to delete?")){
        getData.splice(index, 1)
        localStorage.setItem("gerantProfile", JSON.stringify(getData))
    

        showInfo()
    }
}


form.addEventListener('submit', (e) => {
    e.preventDefault();

    const information = {
        picture: imgInput.src || "./image/Profile Icon.webp",
        gerantName: Name.value, // Ensure correct references
        gerantSexe: sexe.value,
        gerantAdresse: adresse.value,
        geranttelephone: telephone.value,
        gerantEmail: email.value,
        gerantPassword: password.value
    };

    if (!isEdit) {
        getData.push(information);
    } else {
        getData[editId] = information;
        isEdit = false;
    }

    localStorage.setItem('gerantProfile', JSON.stringify(getData));

    submitBtn.innerText = "Submit";
    modalTitle.innerText = "Remplir les informations";

    showInfo(); // Refresh the table

    form.reset();
    imgInput.src = "./image/Profile Icon.webp";  
});
