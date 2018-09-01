package com.dreamit.currentweatherapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.dreamit.currentweatherapp.cities.view.CitiesFragment
import com.dreamit.currentweatherapp.net.WeatherService
import com.dreamit.currentweatherapp.net.getRestClient
import io.realm.Realm
import io.realm.RealmConfiguration


class MainActivity : AppCompatActivity() {

    val realm: Realm by lazy {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("quiz.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build()
        Realm.setDefaultConfiguration(config)
        Realm.getDefaultInstance()
    }
    val weathersService: WeatherService by lazy {
        getRestClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(CitiesFragment(), false)
    }

    fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus?.let {
            it
        } ?: View(this)

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun loadFragment(fragment: Fragment, addToBackStack: Boolean) {
        if (window.currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)
        }

        val fragmentTransaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content, fragment, fragment::class.java.simpleName)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        } else {
            clearFragmentBackStack()
        }

        fragmentTransaction.commitAllowingStateLoss()
    }

    private fun clearFragmentBackStack() {
        val fm = supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }
    }
}
