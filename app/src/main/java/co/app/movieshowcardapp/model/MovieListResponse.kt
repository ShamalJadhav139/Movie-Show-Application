package co.app.movieshowcardapp.model

data class MovieListResponse(

    val Search: List<SearchMovie>
) {
    data class SearchMovie(
        val Title: String,
        var Year: String,
        var imdbID: String,
        var Type: String,
        var Poster: String
    )
}