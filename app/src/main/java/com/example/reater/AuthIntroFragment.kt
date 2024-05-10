package com.example.reater

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.reater.databinding.FragmentAuthIntroBinding
import com.example.reater.databinding.FragmentHomeBinding


class AuthIntroFragment : Fragment() {
    private var _binding: FragmentAuthIntroBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAuthIntroBinding.inflate(layoutInflater,container,false)
        binding.studentBox.setOnClickListener {
            findNavController().navigate(R.id.action_authIntroFragment_to_authHomeFragment)
        }
        return binding.root
    }

}