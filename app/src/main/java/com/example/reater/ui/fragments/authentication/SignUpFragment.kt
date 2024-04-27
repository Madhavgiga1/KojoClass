package com.example.reater.ui.fragments.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.reater.R
import com.example.reater.databinding.FragmentSignUpBinding
import com.example.reater.models.LoginResponse
import com.example.reater.models.SignupRequest
import com.example.reater.models.Student
import com.example.reater.ui.activities.MainActivity
import com.example.reater.utils.Constants.Companion.DEGREE_MAP
import com.example.reater.utils.NetworkResult
import com.example.reater.viewmodels.AuthenticationViewModel

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var authenticationViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authenticationViewModel = ViewModelProvider(requireActivity()).get(AuthenticationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.progressBar2.visibility=View.INVISIBLE
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.Degree_Names,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.Class_Names,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.classIdSpinner.adapter = adapter
        }

        binding.submit.setOnClickListener {
            val enrolmentNumber = binding.EnrollmentNumbersignup.text.toString()
            val password = binding.PasswordSignup.text.toString()
            val enrolmentYear = binding.EnrollmentYearSignup.text.toString()
            val fullname = binding.FullNameSignupValue.text.toString()
            val dob = binding.editTextDate.text.toString()
            val phone = binding.PhoneNumber.text.toString()
            var degreename = binding.spinner.selectedItem.toString()
            var classid=binding.classIdSpinner.selectedItem.toString()
            var email=binding.EmailSignupValue.text.toString()

            if (enrolmentNumber.isNotEmpty() && password.isNotEmpty()&& enrolmentYear.isNotEmpty() && fullname.isNotEmpty() && dob.isNotEmpty() && phone.isNotEmpty()) {
                val signupRequest = SignupRequest(enrolmentNumber,password,
                    Student(enrolmentNumber,fullname,DEGREE_MAP[degreename].toString(),classid,phone,enrolmentYear,email, dob)
                )
                sendStudentSignupRequest(signupRequest)
            }
            else {
                showToast("Please fill in all fields.")
            }
        }

        return binding.root
    }

    private fun sendStudentSignupRequest(signupRequest: SignupRequest) {
        authenticationViewModel.signupStudent(signupRequest)
        showToast("Sending signup request...")
        authenticationViewModel.studentSignupProfile.observe(viewLifecycleOwner,{response->
            when (response){
                is NetworkResult.Loading->{
                    binding.progressBar2.visibility=View.VISIBLE
                }

                is NetworkResult.Error->{
                    binding.progressBar2.visibility=View.INVISIBLE
                    Toast.makeText(requireContext(),"Error Connecting to Server",Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Success->{
                    binding.progressBar2.visibility=View.INVISIBLE
                    Toast.makeText(requireContext(),"Successfully Logged In ",Toast.LENGTH_SHORT).show()
                    var intent= Intent(requireContext(), MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent)


                }
            }

        })

    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
