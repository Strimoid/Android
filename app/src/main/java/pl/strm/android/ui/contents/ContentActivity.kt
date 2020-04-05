package pl.strm.android.ui.contents

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.content_content.*
import pl.strm.android.R
import pl.strm.android.domain.Content

class ContentActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        setSupportActionBar(toolbar)

        intent.getParcelableExtra<Content>("content").let { content: Content ->
            title = content.title

            webview.webViewClient = WebViewClient()
            webview.settings.javaScriptEnabled = true
            webview.loadUrl(content.url)
        }


    }
}