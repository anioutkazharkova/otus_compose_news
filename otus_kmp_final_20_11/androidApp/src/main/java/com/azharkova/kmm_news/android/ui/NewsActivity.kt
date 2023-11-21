package com.azharkova.kmm_news.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.azharkova.newsapp.android.R
import com.azharkova.test_kmm.App
import moe.tlaster.precompose.lifecycle.PreComposeActivity

class NewsActivity : PreComposeActivity() {
  // private val vm: NewsViewModel? = KoinDIFactory.resolve(NewsViewModel::class)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
       setContent {
           App()
          // vm?.let {
            // NewsListScreen(viewModel = vm)
          // }
       }

    }


}
