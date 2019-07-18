function getAllAuthors() {
    fetch('/flux/authors', {
        method:'GET'
    })
        .then(authorses => authorses.json())
        .then(authors => {
            new Vue({
                el: '#authors-table',
                data: {authors: authors}
            });
        })
}

function deleteAuthorById(id) {
    fetch("/flux/authors/" + id, {
        method: 'DELETE'
    })
        .then(response => response.json());
}

new Vue({
    el: '#create-author',
    data: {
        lname: '',
        fname: ''
    },
    methods: {
        createAuthor: function() {
            fetch("/flux/authors?lname=" + this.lname + "&fname=" + this.fname, {
                method: 'POST'
            })
                .then(response => response.json());
        }
    }
});

function editAuthor(id) {
    var alname = document.getElementById("authorlnameid-" + id).innerHTML;
    var afname = document.getElementById("authorfnameid-" + id).innerHTML;
    fetch("/flux/authors/" + id + "?fname=" + afname + "&lname=" + alname, {
        method: 'PUT'
    })
        .then(response => response.json());
}