package com.example.mindsvalleyapplication.feature_channels.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.mindsvalleyapplication.R

object LoadImage {

  @Composable
  fun ImageWithLoader(url: String, modifier: Modifier = Modifier) {

    Box(modifier = modifier) {
      SubcomposeAsyncImage(
          model =
              ImageRequest.Builder(LocalContext.current)
                  .data(url)
                  .crossfade(true)
                  .memoryCachePolicy(CachePolicy.ENABLED)
                  .diskCachePolicy(CachePolicy.ENABLED)
                  .fallback(R.drawable.no_image_loaded)
                  .build(),
          contentDescription = null,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize(),
          loading = {
            CircularProgressIndicator(
                color = colorResource(id = R.color.progress_color),
                modifier = Modifier.width(50.dp).height(50.dp).align(Alignment.Center))
          },
          error = {
              Image(
                  painter = painterResource(id = R.drawable.no_image_loaded),
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  contentScale = ContentScale.Crop
              )
          })

    }
  }
}
