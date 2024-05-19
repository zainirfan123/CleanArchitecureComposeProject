package com.example.mindsvalleyapplication.feature_channels.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindsvalleyapplication.R
import com.example.mindsvalleyapplication.utils.AppsFontUtils
import com.example.mindsvalleyapplication.utils.TestTags
import com.example.mindsvalleyapplication.utils.TestTags.ERROR_SCREEN

object ComposeUtils {

  @Composable
  fun CustomTextView(
      text: String,
      modifier: Modifier = Modifier,
      textSize: TextUnit = MaterialTheme.typography.titleMedium.fontSize,
      textColor: Color = Color.Black,
      textAlign: TextAlign? = TextAlign.Start,
      fontFamily: FontFamily? = null,
      fontWeight: FontWeight? = null,
      letterSpacing: TextUnit
  ) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = textSize,
        color = textColor,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis)
  }

  @Composable
  fun Divider() {
    Image(
        modifier =
            Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 5.dp),
        painter = painterResource(id = R.drawable.divider),
        contentDescription = "divider",
        contentScale = ContentScale.Crop)
    Spacer(modifier = Modifier.height(10.dp))
  }

  @Composable
  fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize().testTag(TestTags.LOADING_INDICATOR), contentAlignment = Alignment.Center) {
      CircularProgressIndicator()
    }
  }

  @Composable
  fun ErrorScreen(errorMessage: String) {
    Box(modifier = Modifier.fillMaxSize().padding(20.dp).testTag(ERROR_SCREEN), contentAlignment = Alignment.Center) {
      Text(
          text = "Error: $errorMessage",
          color = Color.Red,
          textAlign = TextAlign.Center,
          fontFamily = AppsFontUtils.fontBold,
          modifier = Modifier.padding(horizontal = 16.dp))
    }
  }
}
