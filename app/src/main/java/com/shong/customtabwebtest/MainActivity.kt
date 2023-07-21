package com.shong.customtabwebtest

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoSessionSettings
import org.mozilla.geckoview.GeckoSessionSettings.USER_AGENT_MODE_MOBILE
import org.mozilla.geckoview.GeckoView


class MainActivity : AppCompatActivity() {
    private lateinit var geckoView: GeckoView
    private val geckoSession = GeckoSession()
    private val url = "https://google.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val intent = builder.build()
            intent.launchUrl(this, Uri.parse(url))
        }

        findViewById<Button>(R.id.btn2).setOnClickListener {
            val settings = GeckoSessionSettings.Builder()
                .usePrivateMode(true)
                .useTrackingProtection(true)
                .userAgentMode(USER_AGENT_MODE_MOBILE)
                .userAgentOverride("")
                .suspendMediaWhenInactive(true)
                .allowJavascript(true)

            setupGeckoView()
        }
        setupGeckoView()
    }

    private fun setupGeckoView() {
        // 1
        geckoView = findViewById(R.id.geckoView)

        // 2
        val runtime = GeckoRuntime.create(this)
        geckoSession.open(runtime)

        // 3
        geckoView.setSession(geckoSession)

        // 4
        geckoSession.loadUri(url)
    }
}