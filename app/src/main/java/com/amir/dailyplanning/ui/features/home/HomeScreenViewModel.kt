package com.amir.dailyplanning.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.dailyplanning.model.data.ChatMessage
import com.amir.dailyplanning.model.repository.PlaningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val  planingRepository: PlaningRepository
): ViewModel() {

   private val _error = MutableStateFlow<Exception?>(null)
    val error = _error.asStateFlow()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()



    fun getData(plan : String){

        viewModelScope.launch {

            _messages.value = _messages.value + ChatMessage(plan,true)

            try {

              val data = planingRepository.getPlan(plan)

                _messages.value = _messages.value + ChatMessage(data.result , false)

            }catch (e: Exception){

                _error.value = e

            }
        }
    }
}