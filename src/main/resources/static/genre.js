function getAllGenres() {
    fetch('/api/genres', {
        method:'GET'
    })
        .then(genrese => genrese.json())
        .then(genres => {
            new Vue({
                el: '#genres-table',
                data: {genres: genres}
            });
        })
}

function deleteGenreById(id) {
    fetch("/api/genres/" + id, {
        method: 'DELETE'
    })
        .then(response => response.json());
}

new Vue({
    el: '#create-genre',
    methods: {
        createGenre: function() {
            fetch("/api/genres/name/" + this.value, {
                method: 'POST'
            })
                .then(response => response.json());
        }
    }
});

function editGenre(id) {
    var gname = document.getElementById("genrenameid-" + id).innerHTML;
    fetch("/api/genres/" + id + "/name/" + gname, {
        method: 'PUT'
    })
        .then(response => response.json());
}