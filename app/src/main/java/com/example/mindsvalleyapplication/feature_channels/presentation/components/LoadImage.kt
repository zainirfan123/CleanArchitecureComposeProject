package com.example.mindsvalleyapplication.feature_channels.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.mindsvalleyapplication.R

object LoadImage {

  @OptIn(ExperimentalCoilApi::class)
  @Composable
  fun ImageWithLoader(url: String, modifier: Modifier = Modifier) {
    val painter =
        rememberImagePainter(
            data = url,
            builder = {
              scale(Scale.FILL)
              crossfade(true)
              placeholder(R.drawable.placeholder_image)
            })

    Box(modifier = modifier) {
      Image(
          painter = painter,
          contentDescription = null,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize())

      if (painter.state is ImagePainter.State.Loading) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.progress_color), modifier = Modifier.align(Alignment.Center))
      }
    }
  }
}
