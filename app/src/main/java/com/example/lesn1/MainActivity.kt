package com.example.lesn1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lesn1.ui.theme.Lesn1Theme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getData()

        setContent {
            Lesn1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   ShowPokList(viewModel = viewModel)
                }
            }
        }
    }
}
@Composable
fun ShowPokList(viewModel: MainViewModel){
    val pokemon by viewModel.immutablePokData.observeAsState(emptyList())

    Log.d("MainActivity", "ALL Pokemons: ${pokemon.size}" )

    if (pokemon.isNotEmpty()){
        pokemon.forEach { pokemon ->
            Log.d("MainActivity", "${pokemon.name}, ${pokemon.urls}" )}
        }
    }

@Composable
fun MainView(){

    Column {
        Text(text = "JIGGLYPUFF!", color = Color.Green)
        Text(text = "Power: 57", fontSize = 20.sp, fontStyle = FontStyle.Normal)
        Text(text = "Color: Pink", fontSize = 10.sp, fontStyle = FontStyle.Normal, color = Color.Magenta)
        Row {
            Text(text = "type: Electric ")

        }
        Image(painter = painterResource(id = R.drawable.pok),
            contentDescription ="pok",

            )

    }


}


@Preview(showBackground = true)
@Composable
fun MainViewPreview(){
    Lesn1Theme {
        MainView()
    }

}
