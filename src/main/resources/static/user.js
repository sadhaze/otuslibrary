function getAllUsers() {
    fetch('/api/users', {
        method:'GET'
    })
        .then(userse => userse.json())
        .then(users => {
            new Vue({
                el: '#users-table',
                data: {users: users}
            });
        })
}

function deleteUserById(id) {
    fetch("/api/users/" + id, {
        method: 'DELETE'
    })
        .then(response => response.json());
}

new Vue({
    el: '#create-user',
    methods: {
        createUser: function() {
            fetch("/api/users/" + this.value, {
                method: 'POST'
            })
                .then(response => response.json());
        }
    }
});