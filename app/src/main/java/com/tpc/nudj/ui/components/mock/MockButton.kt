package com.tpc.nudj.ui.components.mock

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tpc.nudj.ui.theme.NudjTheme

@Composable
fun MockButton(
    text:String,
    onClick:()->Unit,
    modifier: Modifier = Modifier,
    enabled:Boolean = true,
){
    val isSystemDark = isSystemInDarkTheme()

    val buttonColor = if(isSystemDark) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
    val disabledBackgroundColor = buttonColor.copy(alpha = 0.6f)

    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor ,
            contentColor = Color.White,
            disabledContainerColor = disabledBackgroundColor,
            disabledContentColor = Color.White.copy(alpha = 0.6f)
        ),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview()
@Composable
fun previewLightButton(){
    NudjTheme {
        MockButton(
                text = "Mock Button",
            onClick = {},
            enabled = true,
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun previewDarkButton(){
    NudjTheme {
        MockButton(
            text = "Mock Button",
            onClick = {},
            enabled = true,
        )
    }
}