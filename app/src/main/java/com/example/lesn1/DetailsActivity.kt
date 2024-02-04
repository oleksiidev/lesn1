package com.example.lesn1


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lesn1.repository.PokemonDetails
import com.example.lesn1.ui.theme.Lesn1Theme

class DetailsActivity : ComponentActivity() {
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("CUSTOM_NAME")
        if (name != null) {
            viewModel.getDetailsData(name)
        }
        setContent {
            Lesn1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //MainView()
                    ShowDetails(viewModel)
                }
            }
        }

        Toast.makeText(this, "details $name", Toast.LENGTH_SHORT).show()
    }
}
@Composable
fun ShowDetails(viewModel: DetailsViewModel) {
    val uiState by viewModel.immutablePokemonDetailsData.observeAsState(UiState())

    when {
        uiState.isLoading -> {
            DetailsLoadingView()
        }
        uiState.error != null -> {
            DetailsErrorView()
        }
        uiState.data != null -> {
            Log.d("DetailsViewModel", "Details")
            uiState.data?.let {DetailsView(pokemonDetails = it)}
        }
    }
}

@Composable
fun DetailsErrorView() {
    Text(text = "ERROR", fontSize=70.sp, fontStyle = FontStyle.Italic, color = Color.Red)
}

@Composable
fun DetailsLoadingView() {
    CircularProgressIndicator()
}

@Composable
fun DetailsView(pokemonDetails: PokemonDetails) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DetailItem("Name", pokemonDetails.name.uppercase(), Color.Black)
        DetailItem("Id", pokemonDetails.id.toString(), Color.Red)
        DetailItem("Weight", pokemonDetails.weight.toString(), Color.Red)
        DetailItem("Order", pokemonDetails.order.toString(), Color.Red)
    }
}

@Composable
private fun DetailItem(label: String, value: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            color = color,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            color = color
        )
    }
}
