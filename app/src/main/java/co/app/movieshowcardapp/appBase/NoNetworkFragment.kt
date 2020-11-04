package co.app.movieshowcardapp.appBase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.app.movieshowcardapp.R


class NoNetworkFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_network, container, false)
    }

    companion object {
        private var instance: NoNetworkFragment? = null
        fun getInstance(): NoNetworkFragment {
            if (instance == null) {
                instance = NoNetworkFragment()
            }
            return instance!!
        }
    }
}
