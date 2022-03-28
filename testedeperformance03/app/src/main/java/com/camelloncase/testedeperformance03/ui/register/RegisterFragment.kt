package com.camelloncase.testedeperformance03.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.camelloncase.testedeperformance03.R
import com.camelloncase.testedeperformance03.database.Surveyor
import com.camelloncase.testedeperformance03.databinding.FragmentRegisterBinding
import com.camelloncase.testedeperformance03.util.Resource
import com.camelloncase.testedeperformance03.util.checkEmailPattern
import com.camelloncase.testedeperformance03.util.checkPasswordPattern
import com.camelloncase.testedeperformance03.util.showMessageToUser
import com.camelloncase.testedeperformance03.viewmodel.AuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: AuthenticationViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity?)!!.supportActionBar?.hide()

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthenticationViewModel::class.java]

        auth = Firebase.auth

        binding.signUpButton.setOnClickListener {

            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val retypePassword = binding.repeatPasswordEditText.text.toString()

            when {
                !checkEmailPattern(email) -> {
                    showMessageToUser(context, "Enter a valid email!")
                }
                !checkPasswordPattern(password) -> {
                    showMessageToUser(context, "Weak password!")
                }
                password != retypePassword -> {
                    showMessageToUser(context, "Passwords don't matches!")
                }
                else -> {
                    viewModel.createUser(email, password)
                }
            }
        }

        binding.cancelButton.setOnClickListener {
            navigateToLoginScreen()
        }

        viewModel.userRegistrationStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    enableProgressBar(true)
                }
                is Resource.Success -> {
                    createRoomUser()
                    enableProgressBar(false)
                    showMessageToUser(context, "Registered Successfully!")
                    navigateToLoginScreen()
                }
                is Resource.Error -> {
                    enableProgressBar(false)
                    showMessageToUser(context, it.message.toString())
                }
            }
        })

        return binding.root
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(
            R.id.action_navigation_register_to_navigation_login
        )
    }

    private fun enableProgressBar(choice: Boolean) {
        binding.registerProgressBar.isVisible = choice
    }

    private fun createRoomUser() {
        val user = auth.currentUser
        val surveyor = Surveyor(user?.uid.toString(), user?.displayName.toString(), user?.email.toString())
        showMessageToUser(context, user?.uid.toString())
//        surveyViewModel.addSurveyor(surveyor)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}