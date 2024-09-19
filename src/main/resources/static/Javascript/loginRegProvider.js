function sendLogin(){
    const password = document.getElementById('password').value
    const login = document.getElementById('login').value
    fetch('/logining',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            password: password,
            login: login
        })
    })   .then(response => response.json())
        .then(data => {
            if (data.redirectUrl) {
                window.location.href = data.redirectUrl;
            }
        })
}
function onTelegramAuth(user) {
    alert('Logged in as ' + user.first_name + ' ' + user.last_name + ' (' + user.id + (user.username ? ', @' + user.username : '') + ')');
    fetch('/telegram-login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: user.id,
            first_name: user.first_name,
            last_name: user.last_name,
            username: user.username
        })
    })    .then(response => response.json())
        .then(data => {
            if (data.redirectUrl) {
                window.location.href = data.redirectUrl; // Редирект на другой контроллер
            }
        })
}
function sendRegistation(){
    const lastname = document.getElementById('lastname').value
    const name = document.getElementById('name').value
    var patronymic = "";
    const phone = document.getElementById('phone').value
    const email = document.getElementById('email').value
    const password = document.getElementById('password').value
    const doppassword = document.getElementById('doppassword').value
    patronymic= document.getElementById('patronymic').value;
    if(doppassword==password){
        fetch('/registration',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                last_name: lastname,
                first_name: name,
                patronymic: patronymic,
                phone: phone,
                email: email,
                password: password,

            })
        })   .then(response => response.json())
            .then(data => {
                if (data.redirectUrl) {
                    window.location.href = data.redirectUrl;
                }
            })
    }
}