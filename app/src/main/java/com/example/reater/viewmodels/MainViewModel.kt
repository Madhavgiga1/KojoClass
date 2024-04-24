package com.example.reater.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.reater.data.Repository
import com.example.reater.models.Student
import com.example.reater.models.Subjects
import com.example.reater.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, application: Application) : AndroidViewModel(application) {

    var StudentProfileResponse: MutableLiveData<NetworkResult<Student>> =MutableLiveData()
    var StudentClassResponse: MutableLiveData<NetworkResult<Subjects>> = MutableLiveData()

    fun getStudentProfile(queries: Map<String,String>)=viewModelScope.launch{
        getStudentProfileSafely(queries)
    }

    private suspend fun getStudentProfileSafely(queries: Map<String,String>) {
        StudentProfileResponse.value=NetworkResult.Loading()

        if(hasInternetConnection()){
            try{
                var response=repository.remote.getStudentProfile(queries)
                StudentProfileResponse.value = handleStudentProfileResponse(response)
            }
            catch (e:Exception){
                StudentProfileResponse.value =NetworkResult.Error("Error COnnecting to the API")
            }

        }
        else{
            StudentProfileResponse.value =NetworkResult.Error("No Internet Connection")
        }
    }

    private fun handleStudentProfileResponse(response: Response<Student>): NetworkResult<Student>? {
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


    fun getStudentClasses(queries: Map<String,String>)=viewModelScope.launch{
        getStudentClassesSafely(queries)
    }

    private suspend fun getStudentClassesSafely(queries: Map<String,String>) {
        StudentClassResponse.value=NetworkResult.Loading()

        if(hasInternetConnection()){
            try{
                var response=repository.remote.getStudentClasses(queries)
                StudentClassResponse.value = handleStudentClassesResponse(response)
            }
            catch (e:Exception){
                StudentProfileResponse.value =NetworkResult.Error("Error Connecting to the API")
            }

        }
        else{
            StudentProfileResponse.value =NetworkResult.Error("No Internet Connection")
        }
    }

    private fun handleStudentClassesResponse(response: Response<Subjects>): NetworkResult<Subjects>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }

            response.isSuccessful -> {
                val studentClasses = response.body()
                return NetworkResult.Success(studentClasses!!)
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