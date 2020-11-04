package co.app.movieshowcardapp.fragment


import android.os.Bundle

import android.text.Editable
import android.text.TextWatcher
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager


import co.app.movieshowcardapp.adapter.MovieListAdapter
import co.app.movieshowcardapp.constants.ApiConstants
import co.app.movieshowcardapp.databinding.FragmentMovieListBinding
import co.app.movieshowcardapp.model.MovieListResponse
import co.app.movieshowcardapp.networkContracter.MainActivityPresenter
import co.app.movieshowcardapp.networkContracter.MainContractor

import com.google.gson.Gson


class MovieListFragment : Fragment(), MainContractor.View {
    private var fragmentMovieListBinding: FragmentMovieListBinding? = null
    private var presenter: MainContractor.Presenter? = null
    var movieListAdapter = MovieListAdapter()
    lateinit var gridlayoutManager: GridLayoutManager
    var count: Int = 0


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
        fragmentMovieListBinding!!.movieListRecycleView.setHasFixedSize(true)
        gridlayoutManager = GridLayoutManager(activity, 3)
        fragmentMovieListBinding!!.movieListRecycleView.layoutManager = gridlayoutManager
        fragmentMovieListBinding!!.movieListRecycleView.adapter = movieListAdapter

        fragmentMovieListBinding!!.nextText.setOnClickListener {
            count += 1
            callGetMovieApi(fragmentMovieListBinding!!.searchText.text.toString(), count.toString())
            if (count > 1) {
                fragmentMovieListBinding!!.previousText.visibility = View.VISIBLE
            } else {
                fragmentMovieListBinding!!.previousText.visibility = View.GONE
            }

        }

        fragmentMovieListBinding!!.previousText.setOnClickListener {
            count -= 1
            callGetMovieApi(fragmentMovieListBinding!!.searchText.text.toString(), count.toString())
            if (count > 1) {
                fragmentMovieListBinding!!.previousText.visibility = View.VISIBLE
            } else {
                fragmentMovieListBinding!!.previousText.visibility = View.GONE
            }

        }


        if (fragmentMovieListBinding!!.searchText.text.toString().trim().isEmpty()) {
            callGetMovieApi("batman", "1")
        }
        fragmentMovieListBinding!!.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (fragmentMovieListBinding!!.searchText.text.toString().length > 2) {
                    callGetMovieApi(fragmentMovieListBinding!!.searchText.text.toString(), "")
                }


            }
        })


    }


    fun callGetMovieApi(s: String, page: String) {
        val paramArray = arrayOf<String>(
            s,
            "4b64a943",
            page
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