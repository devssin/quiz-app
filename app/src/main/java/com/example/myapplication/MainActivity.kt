package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.applovin.sdk.AppLovinSdk
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), MaxAdViewAdListener {
    private var interstitialAd: MaxInterstitialAd? = null
    private var retryAttempt = 0.0
    private lateinit var nativeAdLoader: MaxNativeAdLoader
    private var nativeAd: MaxAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility =View.SYSTEM_UI_FLAG_FULLSCREEN

        // Make sure to set the mediation provider value to "max" to ensure proper functionality
        AppLovinSdk.getInstance( this ).mediationProvider = "max"
        AppLovinSdk.getInstance( this ).initializeSdk {
            // AppLovin SDK is initialized, start loading ads
        }
        AppLovinSdk.getInstance(this).settings.testDeviceAdvertisingIds = arrayListOf("0c8e98b3-4048-4f21-b0f9-91e18edd9d53")

        createBannerAd()
        createNativeAd()


        interstitialAd = MaxInterstitialAd("38021e2015a2d62a", this)
        interstitialAd?.setListener(this)

        interstitialAd?.loadAd()






        val btnStart : Button = findViewById(R.id.btnStart)
        val txtName: TextView = findViewById(R.id.txtName)
        btnStart.setOnClickListener(){ it ->
            if (txtName.text.isEmpty()) {
                Snackbar.make(it, "You must Enter your name",Snackbar.LENGTH_SHORT)
                    .setAction("Dismiss", View.OnClickListener {

                    }).show()
            }else{
                interstitialAd?.let {
                    if ( it.isReady)
                    {
                        it.showAd();
                    }
                }

                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, txtName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }


    fun createNativeAd()
    {
        val nativeAdContainer = findViewById<FrameLayout>(R.id.native_ad_content)
        nativeAdLoader = MaxNativeAdLoader( "38021e2015a2d62a", this )
        nativeAdLoader.setNativeAdListener(object : MaxNativeAdListener() {

            override fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd)
            {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if ( nativeAd != null )
                {
                    nativeAdLoader.destroy( nativeAd )
                }

                // Save ad for cleanup.
                nativeAd = ad

                // Add ad view to view.
                nativeAdContainer.removeAllViews()
                nativeAdContainer.addView( nativeAdView )
            }

            override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError)
            {
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            override fun onNativeAdClicked(ad: MaxAd)
            {
                // Optional click callback
            }
        })
        nativeAdLoader.loadAd()
    }

    @SuppressLint("ResourceAsColor")
    fun createBannerAd()
    {
        var adView = MaxAdView("3e51698a7561bd7a", this)
        adView?.setListener(this)

        // Stretch to the width of the screen for banners to be fully functional
        val width = ViewGroup.LayoutParams.MATCH_PARENT

        // Banner height on phones and tablets is 50 and 90, respectively
        val heightPx = resources.getDimensionPixelSize(R.dimen.banner_height)

        val p = RelativeLayout.LayoutParams(width, heightPx)
        p.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        adView?.layoutParams = p


        // Set background or background color for banners to be fully functional
        adView?.setBackgroundColor(R.color.white)

        val rootView = findViewById<ViewGroup>(R.id.parent)

        rootView.addView(adView)

        // Load the ad
        adView?.loadAd()
    }

    override fun onAdLoaded(ad: MaxAd?) {
        TODO("Not yet implemented")

        retryAttempt = 0.0
    }

    override fun onAdDisplayed(ad: MaxAd?) {
        TODO("Not yet implemented")
    }

    override fun onAdHidden(ad: MaxAd?) {
        TODO("Not yet implemented")
    }

    override fun onAdClicked(ad: MaxAd?) {
        TODO("Not yet implemented")
    }

    override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
        // Interstitial ad failed to load
        // AppLovin recommends that you retry with exponentially higher delays up to a maximum delay (in this case 64 seconds)

        retryAttempt++
        val delayMillis = TimeUnit.SECONDS.toMillis( Math.pow( 2.0, Math.min( 6.0, retryAttempt ) ).toLong() )

        Handler().postDelayed( { interstitialAd?.loadAd() }, delayMillis )
    }

    override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
        TODO("Not yet implemented")
    }

    override fun onAdExpanded(ad: MaxAd?) {
        TODO("Not yet implemented")
    }

    override fun onAdCollapsed(ad: MaxAd?) {
        TODO("Not yet implemented")
    }
}



