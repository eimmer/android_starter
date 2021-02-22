package com.bracket.datasharemain.ui.splash

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class NextScreen {
    Loading,
    HomeScreen,
}

class SplashViewModel : ViewModel() {

    private val loadingStatus = MutableLiveData(NextScreen.Loading)
    val liveLoadingState: LiveData<NextScreen> = loadingStatus

    init {
        processData()
    }

    private fun processData() {
        viewModelScope.launch(Dispatchers.Default) {
            delay(1500L)
            loadingStatus.postValue(NextScreen.HomeScreen)
        }
    }

    class SplashViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SplashViewModel() as T
        }
    }
}