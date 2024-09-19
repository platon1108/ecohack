const scales = document.querySelector("#scales");
const patronymic = document.querySelector("#patronymic");
const patronymiccss = document.querySelector(".patronymic_input");

const checkpass = document.querySelector("#checkpass");
const password = document.querySelector("#password");
const doppassword = document.querySelector("#doppassword");

const doc = document.querySelector("#doc");
const register = document.querySelector(".register");

password.type = "password";
doppassword.type = "password";

scales.addEventListener("change", () => {
    if (scales.checked) {
        patronymic.disabled = true;
        patronymic.value = '';

        patronymiccss.style.background = "#E6E9ED";
        patronymiccss.style.outline = "none";
        patronymiccss.blur(); 
    } else {
        patronymic.disabled = false;

        patronymiccss.style.background = "";
        patronymiccss.style.outline = "";
        patronymic.focus();
    }
});
checkpass.addEventListener("change", () => {
    if (checkpass.checked){
        password.type = "text";
        doppassword.type = "text";
    }
    else{
        password.type = "password";
        doppassword.type = "password";
    }
})
doc.addEventListener("change", () => {
    if (doc.checked){
        register.disabled = false;
    }
    else{
        register.disabled = true;
    }
})