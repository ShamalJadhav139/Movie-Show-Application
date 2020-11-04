package co.app.movieshowcardapp.adapter


import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.app.movieshowcardapp.R
import co.app.movieshowcardapp.databinding.ItemMovieListBinding
import co.app.movieshowcardapp.model.MovieListResponse

import com.squareup.picasso.Picasso


class MovieListAdapter(): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    lateinit var itemMovieListBinding: ItemMovieListBinding
    var movieList: List<MovieListResponse.SearchMovie> = emptyList()
    var filterList: List<MovieListResponse.SearchMovie>
    init {
        filterList = movieList
    }
    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemMovieListBinding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return ViewHolder(itemMovieListBinding)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemMovieListBinding = holder.itemMovieListBinding

        itemMovieListBinding.movieName.text = movieList[position].Title
        itemMovieListBinding.year.text = movieList[position].Year

        if (movieList[position].Poster.isNotEmpty()) {
            Picasso.get().load(movieList[position].Poster).fit().centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(itemMovieListBinding.movieImage)
        }



    }

    fun setData(movieList: List<MovieListResponse.SearchMovie>) {
        this.movieList = movieList
        this.filterList = movieList
        notifyDataSetChanged()
    }

    class ViewHolder(val itemMovieListBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(itemMovieListBinding.root)

    fun filterData(s: CharSequence) {
        var tmpList: List<MovieListResponse.SearchMovie>? = mutableListOf()
        if (s.length > 0) {
            for (model in movieList) {
                if (tmpList != null) {
                    if (model.Title!!.toLowerCase().contains(s.toString().toLowerCase())) {
                        tmpList += model
                    }
                }
            }
            this.filterList = tmpList!! as ArrayList<MovieListResponse.SearchMovie>

        } else {
            this.filterList = movieList
        }
        notifyDataSetChanged()
    }





}