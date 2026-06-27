package com.tpc.nudj.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpc.nudj.R
import com.tpc.nudj.ui.theme.LocalAppColors
import com.tpc.nudj.ui.theme.NudjTheme

@Composable
fun NudjLogo(modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(if(isSystemInDarkTheme()) R.drawable.nudj_logo_dark_theme else R.drawable.nudj_logo),
            contentDescription = "Nudj Logo",
            modifier = modifier.size(90.dp)
        )

        Image(
            painter = painterResource(if(isSystemInDarkTheme()) R.drawable.nudj_dark_theme else R.drawable.nudj),
            contentDescription = "Nudj",
            modifier = modifier
                .height(50.dp)
                .width(100.dp)
        )
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun preview(){
    NudjTheme{
        Box(
            modifier = Modifier.background(LocalAppColors.current.background)
                .padding(10.dp)
        ) {
            NudjLogo()
        }
    }
}