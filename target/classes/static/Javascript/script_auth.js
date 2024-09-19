const scales = document.querySelector("#scales");
const password = document.querySelector("#password");

password.type = "password";

scales.addEventListener("change", () => {
    if (scales.checked){
        password.type = "text";
    }
    else{
        password.type = "password";
    }
})
