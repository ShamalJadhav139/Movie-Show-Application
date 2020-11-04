package co.app.movieshowcardapp.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager

import co.app.movieshowcardapp.R
import co.app.movieshowcardapp.adapter.MovieListAdapter
import co.app.movieshowcardapp.appBase.BaseFragment
import co.app.movieshowcardapp.constants.ApiConstants
import co.app.movieshowcardapp.databinding.FragmentMovieListBinding
import co.app.movieshowcardapp.model.MovieListResponse
import co.app.movieshowcardapp.networkContracter.MainActivityPresenter
import co.app.movieshowcardapp.networkContracter.MainContractor

import com.google.gson.Gson


class MovieListFragment : BaseFragment(), MainContractor.View {
    private var fragmentMovieListBinding: FragmentMovieListBinding? = null
    private var presenter: MainContractor.Presenter? = null
    var movieListAdapter = MovieListAdapter()
    lateinit var gridlayoutManager: GridLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieListBinding =
            FragmentMovieListBinding.inflate(LayoutInflater.from(context), container, false)
        presenter = MainActivityPresenter(this)
        return fragmentMovieListBinding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieListAdapter = MovieListAdapter()
        fragmentMovieListBinding!!.bookListRecycleView.setHasFixedSize(true)
        gridlayoutManager = GridLayoutManager(activity, 3)
        fragmentMovieListBinding!!.bookListRecycleView.layoutManager = gridlayoutManager
        fragmentMovieListBinding!!.bookListRecycleView.adapter = movieListAdapter

        callGetMovieApi("batman")
        fragmentMovieListBinding!!.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (fragmentMovieListBinding!!.searchText.text.toString().length > 3) {
                    callGetMovieApi(fragmentMovieListBinding!!.searchText.text.toString())
                }


            }
        })


    }


    fun callGetMovieApi(s:String) {
        val paramArray = arrayOf<String>(
            s,
            "4b64a943"
        )
        presenter!!.onClick(
            ApiConstants.getMovies,
            paramArray,
            requireContext(),
            true
        )
    }


    override fun setViewData(data: String, view: ApiConstants) {
        when (view) {
            ApiConstants.getMovies -> {
                val response = Gson().fromJson(data, MovieListResponse::class.java)
                if (response.Search != null) {
                    movieListAdapter.setData(response.Search)
                } else {
                    Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}