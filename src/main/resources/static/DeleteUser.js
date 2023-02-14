let formDelete = document.forms["deleteModal"];
deleteUser();

async function deleteModalData(id) {
    const modal = new bootstrap.Modal(document.querySelector('#modalDelete'));
    modal.show();
    let user = await getUser(id);
    formDelete.id.value = user.id;
    formDelete.firstName.value = user.firstName;
    formDelete.lastName.value = user.lastName;
    formDelete.age.value = user.age;
    formDelete.username.value = user.username;
    formDelete.roles.value = user.roles[0].id;
    switch (formDelete.roles.value) {
        case '1':
            formDelete.roles.value = 'USER';
            break;
        case '2':
            formDelete.roles.value = 'ADMIN';
            break;
    }
}

function deleteUser() {
    formDelete .addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8080/api/deleteUser/" + formDelete.id.value, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#deleteFormCloseButton').click();
            fillTable();
        });
    });
}