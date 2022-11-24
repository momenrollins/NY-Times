package com.momen.nytimes.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.momen.nytimes.R


class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                /*Column(
                    Modifier
                        .padding(10.dp)
                        .width(IntrinsicSize.Max),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = args.newsItem.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    if (!args.newsItem.media.isNullOrEmpty() && !args.newsItem.media?.first()?.media_metadata.isNullOrEmpty())
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(
                                    args.newsItem.media?.first()?.media_metadata?.get(
                                        2
                                    )?.url
                                )
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.logo),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(RoundedCornerShape(4))
                        )
                    Text(
                        text = args.newsItem.abstract,
                    )
                }*/
                MainContent()
            }
        }
    }
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainContent() {
        Scaffold(
            content = { MyContent() }
        )
    }

    @Composable
    fun MyContent(){
        val mUrl = args.newsItem.url
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(mUrl)
            }
        }, update = {
            it.loadUrl(mUrl)
        })
    }
}