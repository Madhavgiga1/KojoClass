package com.example.reater.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.reater.data.Repository
import com.example.reater.models.*
import com.example.reater.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, application: Application) : AndroidViewModel(application) {

    var StudentClassResponse: MutableLiveData<NetworkResult<Subjects>> = MutableLiveData()


    fun getStudentClasses(queries: Map<String,String>)=viewModelScope.launch{
        getStudentClassesSafely(queries)
    }

    private suspend fun getStudentClassesSafely(queries: Map<String,String>) {
        StudentClassResponse.value=NetworkResult.Loading()

        if(hasInternetConnection()){
            try{
                var response=repository.remote.getStudentClasses(queries)
                StudentClassResponse.value = handleApiResponse(response)
            }
            catch (e:Exception){
                StudentClassResponse.value =NetworkResult.Error("Error Connecting to the API")
            }

        }
        else{
            StudentClassResponse.value =NetworkResult.Error("No Internet Connection")
        }
    }

    private inline fun <reified T> handleApiResponse(response: Response<T>): NetworkResult<T> {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                NetworkResult.Error("API Key Limited.")
            }
            response.isSuccessful -> {
                val data = response.body()
                if (data != null) {
                    NetworkResult.Success(data)
                } else {
                    NetworkResult.Error("Response body is null")
                }
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    var StudentQuizzes: MutableLiveData<NetworkResult<List<Quiz>>> = MutableLiveData()
    fun getStudentQuizzes(queries: Map<String,String>)=viewModelScope.launch{
        fetchStudentQuizzesSafely(queries)
    }

    private suspend fun fetchStudentQuizzesSafely(queries: Map<String,String>) {
        StudentQuizzes.value=NetworkResult.Loading()

        if(hasInternetConnection()){
            try{
                var response=repository.remote.getQuizzes(queries)
                StudentQuizzes.value=handleApiResponse(response)
            }
            catch (e:Exception){
                StudentQuizzes.value=NetworkResult.Error("Error Connecting to the API")
            }
        }
        else{
            StudentQuizzes.value=NetworkResult.Error("No internet connection,please try again later")
        }
    }


    fun postStudentQuiz(quizResult: QuizResult)=viewModelScope.launch{
        submitQuiz(quizResult)
    }
    suspend fun submitQuiz(quizResult:QuizResult){
        if(hasInternetConnection()){
            var response=repository.remote.submitStudentQuiz(quizResult)
            if(response.isSuccessful){
                Toast.makeText(getApplication(),"Successfully submitted",Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(getApplication(),"Cannot be  submitted,Dont go back",Toast.LENGTH_SHORT).show()
        }
    }


    var studentAssignments: MutableLiveData<NetworkResult<List<Assignment>>> = MutableLiveData()
    fun getStudentAssignments(queries: Map<String, String>) = viewModelScope.launch {
        fetchStudentAssignmentsSafely(queries)
    }
    private suspend fun fetchStudentAssignmentsSafely(queries: Map<String, String>) {
        studentAssignments.value = NetworkResult.Loading()

        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getAssignments(queries)
                studentAssignments.value = handleApiResponse(response)
            } catch (e: Exception) {
                studentAssignments.value = NetworkResult.Error("Error connecting to the API")
            }
        } else {
            studentAssignments.value = NetworkResult.Error("No internet connection, please try again later")
        }
    }

    fun postStudentAssignment(assignmentUpload: AssignmentUpload)=viewModelScope.launch{
        turninAssignment(assignmentUpload)
    }
    suspend fun turninAssignment(assignmentUpload: AssignmentUpload){
        if(hasInternetConnection()){
            var response=repository.remote.turninAssignment(assignmentUpload)
            if(response.isSuccessful){
                Toast.makeText(getApplication(),"Successfully submitted",Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(getApplication(),"Cannot be  submitted,Dont go back",Toast.LENGTH_SHORT).show()
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