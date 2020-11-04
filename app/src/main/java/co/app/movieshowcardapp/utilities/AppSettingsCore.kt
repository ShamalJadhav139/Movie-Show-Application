package co.app.movieshowcardapp.utilities

import android.content.Context
import android.content.SharedPreferences
import co.app.movieshowcardapp.constants.APP_NAME


internal object AppSettingsCore{

    private const val SHARED_PREFERENCES_NAME = APP_NAME

    private val preferences: SharedPreferences?
        get() {
            try {
                if (AppDetails.context != null)
                    return AppDetails.context!!.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

     private val editor: SharedPreferences.Editor
        get() = preferences!!.edit()



    fun removePrefData() {
        val editor = editor
        editor.clear()
        editor.commit()
    }
}
