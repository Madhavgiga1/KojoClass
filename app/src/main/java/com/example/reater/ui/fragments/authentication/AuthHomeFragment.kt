package com.example.reater.ui.fragments.authentication

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.reater.R
import com.example.reater.databinding.FragmentAuthHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthHomeFragment : Fragment() {

    private var _binding:FragmentAuthHomeBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentAuthHomeBinding.inflate(inflater,container,false)
        val navController=findNavController()
        binding.Signup.setOnClickListener {
            navController.navigate(R.id.action_authHomeFragment_to_signUpFragment2)
        }
        binding.signin.setOnClickListener {
            navController.navigate(R.id.action_authHomeFragment_to_signinFragment)
        }
        return binding.root
    }






}