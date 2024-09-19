function create(){
    const name = document.getElementById('name').value
    const description = document.getElementById('description').value
    const format = document.getElementById('format').value
    const duration = document.getElementById('duration').value
    const address = document.getElementById('address').value
    const personid = document.getElementById('ids').value


    fetch('/event',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            description: description,
            format: format,
            duration: duration,
            address: address,
            personid: personid,
        })
    })   .then(response => response.json())
        .then(data => {
            if (data.redirectUrl) {
                window.location.href = data.redirectUrl;
            }
        })
}

function edit(){
    const name = document.getElementById('name').value
    const description = document.getElementById('description').value
    const format = document.getElementById('format').value
    const duration = document.getElementById('duration').value
    const address = document.getElementById('address').value
    const personid = document.getElementById('ids').value
    const idt = document.getElementById('idt').value


    fetch('/event',{
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            id: idt,
            description: description,
            format: format,
            duration: duration,
            address: address,
            personid: personid,
        })
    })   .then(response => response.json())
        .then(data => {
            if (data.redirectUrl) {
                window.location.href = data.redirectUrl;
            }
        })
}
function delet(){
    const idt = document.getElementById('idt').value
    const personid = document.getElementById('ids').value

    fetch('/event',{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: idt,
            personid: personid
        })
    })   .then(response => response.json())
        .then(data => {
            if (data.redirectUrl) {
                window.location.href = data.redirectUrl;
            }
        })
}