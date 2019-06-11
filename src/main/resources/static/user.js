function getAllUsers() {
    fetch('/api/users')
        .then(userse => userse.json())
    .then(users => {
        new Vue({
            el: '#users-table',
            data: {
                users: users
            }
        })
    });
}