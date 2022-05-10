package com.application.zomat.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal

class MyApplication: Application() {
    private val oneSignalKey by lazy { "0b9a9cdd-b69d-4a1b-9755-dc904db24d8e" }
    private val keyDevAppsflyer by lazy { "ViYvZw6F8t8zp2Sdnkghda" }
    private var count1 = 0
    private var count2 = 0
    private var count3 = 0
    private var count4 = 0
    private var getData = false
    companion object{
        var liveDataAppsFlyer = MutableLiveData<MutableMap<String, Any>>()
    }

    override fun onCreate() {
        super.onCreate()
        if (count1 == 0) {
            AppsFlyerLib.getInstance().init(keyDevAppsflyer, appsFlyerConversion(), this)
            AppsFlyerLib.getInstance().start(this)
            OneSignal.initWithContext(this);
            OneSignal.setAppId(oneSignalKey)
        }
    }

    private fun appsFlyerConversion(): AppsFlyerConversionListener {

        return object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                if (count2 == 0){
                    if (!getData){
                        data?.let {
                            liveDataAppsFlyer.postValue(it)
                        }
                        getData = true
                    }
                }

            }

            override fun onConversionDataFail(error: String?) {
                if (count3 == 0) {
                    Log.d("error", "$error")
                }
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                if (count4 == 0) {
                    Log.d("data", "$data")
                }
            }

            override fun onAttributionFailure(error: String?) {
                Log.d("error", "$error")
            }
        }
    }
}