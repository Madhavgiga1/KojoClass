package com.example.reater.ui.fragments.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.reater.R
import com.example.reater.databinding.FragmentSigninBinding
import com.example.reater.models.LoginRequest
import com.example.reater.ui.activities.MainActivity
import com.example.reater.utils.NetworkResult
import com.example.reater.viewmodels.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

class SigninFragment : Fragment() {
    private var _binding:FragmentSigninBinding?=null
    private val binding get() = _binding!!
    private lateinit var authenticationViewModel: AuthenticationViewModel


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        authenticationViewModel= ViewModelProvider(requireActivity())[AuthenticationViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        _binding= FragmentSigninBinding.inflate(inflater, container, false)
        binding.progressBar.visibility=View.INVISIBLE
        binding.loginbox.setOnClickListener {
            var enrollment=binding.emailEditText.text.toString()
            var password=binding.passwordEditText.text.toString()
            loginStudent(LoginRequest(enrollment,password))
        }
        return binding.root
    }

    private fun loginStudent(loginRequest: LoginRequest) {
        authenticationViewModel.getStudentProfile(loginRequest)

        authenticationViewModel.StudentProfileResponse.observe(viewLifecycleOwner,{response->
            when (response){
                is NetworkResult.Loading->{
                    binding.progressBar.visibility=View.VISIBLE
                }

                is NetworkResult.Error->{
                    binding.progressBar.visibility=View.INVISIBLE
                    Toast.makeText(requireContext(),"Error Connecting to Server",Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Success->{
                    binding.progressBar.visibility=View.INVISIBLE
                    Toast.makeText(requireContext(),"Successfully Logged In ",Toast.LENGTH_SHORT).show()
                    var intent=Intent(requireContext(),MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent)


                }
            }
        })
    }


}