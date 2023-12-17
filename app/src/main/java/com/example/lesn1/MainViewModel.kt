package com.example.lesn1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesn1.repository.PokRepository
import com.example.lesn1.repository.Pokemons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val PokRepository = PokRepository()

    private val mutablePokData = MutableLiveData<List<Pokemons>>()
    val immutablePokData: LiveData<List<Pokemons>> = mutablePokData

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val request = PokRepository.getPokResponse()
                Log.d("MainViewModel", "request return code: ${request.code()}")
                if (request.isSuccessful) {
                    val pokemon = request.body()?.results
                    mutablePokData.postValue(pokemon)
                } else{
                    Log.e("MainViewModel", "Request failed, ${request.errorBody()}")
                }
            }
                catch (e: Exception){
                    Log.e("MainViewModel", "Nie udalo sie pobrac", e)
                }
            }

        }

    }
