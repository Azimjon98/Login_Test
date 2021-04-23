package me.androiddev.logintestapp.ui.mv

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.core.util.Connectivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import me.androiddev.logintestapp.mvvm.LiveEvent
import me.androiddev.logintestapp.simulation.ApiSimulationClass
import me.androiddev.logintestapp.util.CoroutineContextProvider
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class LoginFragmentVM @Inject constructor(
    private val connectivity: Connectivity,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        apiBlockWindow.postValue(false)
    }

    var login = ObservableField<String>()
    var password = ObservableField<String>()

    val apiBlockWindow = MutableLiveData<Boolean>()
    val apiNoConnection = LiveEvent<Unit>()
    var loginErrorEvent = LiveEvent<Unit>()
    var passwordErrorEvent = LiveEvent<Unit>()
    var loginEvent = LiveEvent<Boolean>()

    fun validateData() {
        if (login.get().isNullOrBlank()) {
            loginErrorEvent.postValue(Unit)
        } else if (password.get().isNullOrBlank()) {
            passwordErrorEvent.postValue(Unit)
        } else {
            loginAction()
        }
    }

    private fun loginAction() {
        val login = this.login.get() ?: ""
        val password = this.password.get() ?: ""

        viewModelScope.launch(coroutineContextProvider.io + errorHandler) {
            if (connectivity.hasNetworkAccess().not()) {
                apiNoConnection.postValue(Unit)
                return@launch
            }

            apiBlockWindow.postValue(true)

            //Try api simulation and show result
            val apiResult = ApiSimulationClass.login(login, password)
            apiBlockWindow.postValue(false)
            if (apiResult)
                loginEvent.postValue(true)
            else
                loginEvent.postValue(false)


        }
    }
}