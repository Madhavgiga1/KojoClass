package com.example.reater.teacherapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.reater.databinding.FragmentTeacherSigninBinding
import com.example.reater.models.LoginRequest
import com.example.reater.teacherapp.TeacherMainActivity
import com.example.reater.viewmodels.AuthenticationViewModel


class TeacherSigninFragment : Fragment() {

    private var _binding: FragmentTeacherSigninBinding?=null
    private val binding get() = _binding!!
    private lateinit var authenticationViewModel: AuthenticationViewModel


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        authenticationViewModel= ViewModelProvider(requireActivity())[AuthenticationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTeacherSigninBinding.inflate(inflater, container, false)
        binding.teacherSigninProgressBar.visibility=View.INVISIBLE
        binding.teacherLoginBox.setOnClickListener {
            /*var id=binding.teacherEmailEditText.text.toString()
            var password=binding.teacherPasswordEditText.text.toString()
            loginTeacher(LoginRequest(id,password))*/
            var intent= Intent(requireContext(), TeacherMainActivity::class.java)
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)
        }
        return binding.root
    }

    private fun loginTeacher(loginRequest:LoginRequest){

    }


}