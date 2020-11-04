package co.app.movieshowcardapp.networkContracter

import android.content.Context
import co.app.movieshowcardapp.constants.ApiConstants

interface MainContractor {
    interface View{
        fun setViewData(data:String,view: ApiConstants)
    }
    // for network calling..
    interface Presenter {
        fun onClick(caseConstants: ApiConstants, parameters: Array<String>, context: Context, showProgressBar: Boolean?)
    }
}