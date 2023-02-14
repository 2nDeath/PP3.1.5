fillTable();

async function fillTable() {
    $('#usersTable').empty()
    fetch("http://localhost:8080/api/getAll")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let tableWithUsers = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.firstName}</td>                                               
                            <td>${user.lastName}</td>
                            <td>${user.roles.map(role => " " + role.name.substring(5))}</td>
                            <td>
                                <button type="button" class="btn btn-info"
                                data-bs-toogle="modal"
                                data-bs-target="#modalEdit"
                                onclick="editModalData(${user.id})">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" 
                                data-toggle="modal"
                                data-bs-target="#modalDelete"
                                onclick="deleteModalData(${user.id})">Delete</button>
                            </td>
                        </tr>)`;
                $('#usersTable').append(tableWithUsers);
            })
        })
}