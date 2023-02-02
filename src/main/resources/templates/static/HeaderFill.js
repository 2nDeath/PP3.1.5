fill();

function fill() {
    fetch("http://localhost:8081/api/getAuthUser")
        .then(res => res.json())
        .then(data => {
            $('#headUsername').append(data.username);
            let rolesList = data.roles.map(role => role.name + " ");
            $('headRoles').append(rolesList)
        })
}