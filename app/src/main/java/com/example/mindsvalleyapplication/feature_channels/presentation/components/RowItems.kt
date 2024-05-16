package com.example.mindsvalleyapplication.feature_channels.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mindsvalleyapplication.R
import com.example.mindsvalleyapplication.feature_channels.domain.model.HorizontalItemModel
import com.example.mindsvalleyapplication.utils.AppsFontUtils

object RowItems {
    @Composable
    fun SetRowItems(list: List<HorizontalItemModel>) {
        LazyRow(modifier = Modifier.fillMaxSize().wrapContentHeight()) {
            itemsIndexed(list) { index: Int, item: HorizontalItemModel ->
                if (index < 6) {
                    Spacer(modifier = Modifier.width(10.dp))
                    RowItem(item = item)
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }

    @Composable
    fun RowItem(item: HorizontalItemModel) {
        Column(
            modifier = Modifier.width(152.dp).wrapContentHeight(),
            horizontalAlignment = Alignment.Start) {
            val painter = rememberImagePainter(item.image)

            Image(
                modifier = Modifier.fillMaxWidth().height(228.dp).clip(RoundedCornerShape(10.dp)),
                painter = painter,
                contentDescription = item.title,
                contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(10.dp))

            ComposeUtils.CustomTextView(
                modifier = Modifier.padding(start = 10.dp),
                text = item.title,
                textSize = 17,
                textColor = colorResource(id = R.color.white),
                fontFamily = AppsFontUtils.fontBold,
                letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp)
            )

            if (item.subTitle.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))

                ComposeUtils.CustomTextView(
                    modifier = Modifier.width(142.dp).padding(start = 10.dp),
                    text = item.subTitle.uppercase(),
                    textSize = 13,
                    textColor = colorResource(id = R.color.grey_secondary),
                    fontFamily = AppsFontUtils.fontBold,
                    letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp)
                )
            }
        }
    }
}