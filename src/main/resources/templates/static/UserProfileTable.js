let currentUser = "";
fetch("http://localhost:8080/api/getAuthUser").then(res => res.json())
    .then(data => currentUser = data)
showOneUser();

function showOneUser() {
    let temp = "";
    temp += "<tr>"
    temp += "<td>" + currentUser.id + "</td>"
    temp += "<td>" + currentUser.name + "</td>"
    temp += "<td>" + currentUser.lastName + "</td>"
    temp += "<td>" + currentUser.age + "</td>"
    temp += "<td>" + currentUser.username + "</td>"
    temp += "<td>" + currentUser.roles.map(role => role.name.substring(5)) + "</td>"
    temp += "</tr>"
    document.getElementById("profileTableBody").innerHTML = temp;
}