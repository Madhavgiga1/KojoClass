package com.example.reater.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reater.data.Repository
import com.example.reater.models.*
import com.example.reater.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val repository: Repository, application: Application) : AndroidViewModel(application) {

    var StudentProfileResponse: MutableLiveData<NetworkResult<LoginResponse>> = MutableLiveData()



    fun getStudentProfile(loginRequest: LoginRequest) = viewModelScope.launch {
        getStudentProfileSafely(loginRequest)
    }

    private suspend fun getStudentProfileSafely(loginRequest: LoginRequest) {
        StudentProfileResponse.value = NetworkResult.Loading()

        if (hasInternetConnection()) {
            try {
                var response = repository.remote.SigninStudent(loginRequest)
                StudentProfileResponse.value = handleStudentProfileResponse(response)
            } catch (e: Exception) {
                StudentProfileResponse.value = NetworkResult.Error("Error Connecting to the API")
            }

        } else {
            StudentProfileResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private fun handleStudentProfileResponse(response: Response<LoginResponse>): NetworkResult<LoginResponse>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.isSuccessful -> {
                val studentProfile = response.body()
                return NetworkResult.Success(studentProfile!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    var studentSignupProfile:MutableLiveData<NetworkResult<LoginResponse>> = MutableLiveData()

    fun signupStudent(signupRequest: SignupRequest) = viewModelScope.launch {
        SignupStudentSafely(signupRequest)
    }
    private suspend fun SignupStudentSafely(signupRequest: SignupRequest) {
        StudentProfileResponse.value = NetworkResult.Loading()

        if (hasInternetConnection()) {
            try {
                var response = repository.remote.RegisterStudent(signupRequest)
                studentSignupProfile.value = handleStudentProfileResponse(response)
            } catch (e: Exception) {
                studentSignupProfile.value = NetworkResult.Error("Error Connecting to the API")
            }

        } else {
            studentSignupProfile.value = NetworkResult.Error("No Internet Connection")
        }
    }


    var TeacherProfileResponse: MutableLiveData<NetworkResult<TeacherLoginResponse>> = MutableLiveData()


    fun getTeacherProfile(loginRequest: LoginRequest) = viewModelScope.launch {
        getTeacherProfileSafely(loginRequest)
    }

    private suspend fun getTeacherProfileSafely(loginRequest: LoginRequest) {
        TeacherProfileResponse.value = NetworkResult.Loading()

        if (hasInternetConnection()) {
            try {
                var response = repository.remote.LoginTeacher(loginRequest)
                TeacherProfileResponse.value = handleTeacherProfileResponse(response)
            }
            catch (e: Exception) {
                TeacherProfileResponse.value = NetworkResult.Error("Error Connecting to the API")
            }

        } else {
            TeacherProfileResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private fun handleTeacherProfileResponse(response: Response<TeacherLoginResponse>): NetworkResult<TeacherLoginResponse>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.isSuccessful -> {
                val teacherProfile = response.body()
                return NetworkResult.Success(teacherProfile!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}