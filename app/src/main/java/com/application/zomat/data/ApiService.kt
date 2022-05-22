package com.application.zomat.data

import com.application.zomat.data.dto.Container
import retrofit2.http.*


interface ApiService {


    @GET("BookofJungle1.php")
    suspend fun setDataServer(
        @Query("google_adid") google_adid: String,
        @Query("af_userid") af_userid: String,
        @Query("fb_deepLink") fb_deepLink: String,
        @Query("tmz") tmz: String,
        @Query("adb") adb: String,
        @Query("redirect_response_data") redirect_response_data: String,
        @Query("adgroup_id") adgroup_id: String,
        @Query("engmnt_source") engmnt_source: String,
        @Query("retargeting_conversion_type") retargeting_conversion_type: String,
        @Query("is_incentivized") is_incentivized: String,
        @Query("orig_cost") orig_cost: String,
        @Query("is_first_launch") is_first_launch: String,
        @Query("af_click_lookback") af_click_lookback: String,
        @Query("af_cpi") af_cpi: String,
        @Query("iscache") iscache: String,
        @Query("click_time") click_time: String,
        @Query("is_branded_link") is_branded_link: String,
        @Query("match_type") match_type: String,
        @Query("adset") adset: String,
        @Query("af_channel") af_channel: String,
        @Query("campaign_id") campaign_id: String,
        @Query("install_time") install_time: String,
        @Query("media_source") media_source: String,
        @Query("agency") agency: String,
        @Query("af_siteid") af_siteid: String,
        @Query("af_status") af_status: String,
        @Query("af_sub1") af_sub1: String,
        @Query("cost_cents_USD") cost_cents_USD: String,
        @Query("af_sub5") af_sub5: String,
        @Query("af_sub4") af_sub4: String,
        @Query("af_sub3") af_sub3: String,
        @Query("af_sub2") af_sub2: String,
        @Query("adset_id") adset_id: String,
        @Query("esp_name") esp_name: String,
        @Query("campaign") campaign: String,
        @Query("http_referrer") http_referrer: String,
        @Query("is_universal_link") is_universal_link: String,
        @Query("is_retargeting") is_retargeting: String,
        @Query("adgroup") adgroup: String
    ): Container
}