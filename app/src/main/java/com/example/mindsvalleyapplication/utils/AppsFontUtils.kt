package com.example.mindsvalleyapplication.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.mindsvalleyapplication.R

object AppsFontUtils {
    val fontBold = FontFamily(Font(R.font.gilroy_bold, FontWeight.Bold))
    val fontRegular = FontFamily(Font(R.font.gilroy_regular, FontWeight.Normal))
    val fontLight = FontFamily(Font(R.font.gilroy_regular, FontWeight.Light))
    val fontMedium = FontFamily(Font(R.font.gilroy_regular, FontWeight.Medium))
}