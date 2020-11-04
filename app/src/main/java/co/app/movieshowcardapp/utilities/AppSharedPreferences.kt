package co.app.movieshowcardapp.utilities


import co.app.movieshowcardapp.utilities.AppSettingsCore.removePrefData



class AppSharedPreferences{
    companion object {
        fun clearUserData() {
            removePrefData()
        }
    }
}