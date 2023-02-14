fill();

async function fill() {
    fetch("http://localhost:8080/api/getAuthUser")
        .then(res => res.json())
        .then(data => {
            $('#headUsername').append(data.username);
            let rolesList = data.roles.map(role => role.name.substring(5).concat(" ")).toString().replaceAll(",", "");
            $('#headRoles').append(rolesList);

            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.username}</td>
                <td>${data.firstName}</td>
                <td>${data.lastName}</td>
                <td>${rolesList}</td>
            </tr>
            )`;

            $('#profileTableBody').append(user);
        })
}