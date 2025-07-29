package com.infinitysoftware.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infinitysoftware.calculator.ui.theme.*

val ButtonList = listOf(
    "C","(",")","/",
    "7","8","9","*",
    "4","5","6","+",
    "1","2","3","-",
    "AC","0",".","=",
)

@Composable
fun Calculator (modifier: Modifier = Modifier, viewModel: CalculatorViewModel, defaultFontSize: TextUnit = 30.sp, defaultResultFontSize: TextUnit = 40.sp, defaultPadding: Dp = 24.dp) {

    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Box(modifier = Modifier) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.End) {
            Text(modifier = Modifier.padding(defaultPadding),
                text = equationText.value?:"",
                style = TextStyle(
                    fontSize = defaultFontSize,
                    textAlign = TextAlign.End),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
                )

            Spacer(modifier = Modifier.weight(1F))

            Text(modifier = Modifier.padding(defaultPadding),
                text = resultText.value?:"",
                style = TextStyle(
                    fontSize = defaultResultFontSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End),
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                items(ButtonList) {
                    CalculatorButton(buttonName = it, onClick = { viewModel.onButtonClick(it) })
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(buttonName: String, onClick: () -> Unit, defaultButtonSize: Dp = 80.dp, defaultFontButtonSize: TextUnit = 30.sp) {
    Box(modifier = Modifier.padding(8.dp)) {

        FloatingActionButton(
            modifier = Modifier.size(defaultButtonSize),
            shape = CircleShape,
            onClick = onClick,
            containerColor = getButtonColorByButtonAction(buttonName),
            contentColor = Color.White
        ) {
            Text(modifier = Modifier, text = buttonName, fontSize = defaultFontButtonSize)
        }
    }
}

@Composable
fun getButtonColorByButtonAction(buttonName: String): Color {
    return when (buttonName) {"C", "(", ")", "/", "*", "+", "-", "=" -> secondaryButtonColor
        else -> primaryButtonColor
    }
}