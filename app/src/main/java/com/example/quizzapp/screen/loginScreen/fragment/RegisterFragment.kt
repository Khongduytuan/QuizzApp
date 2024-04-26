package com.example.quizzapp.screen.loginScreen.fragment

import android.os.Build
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentRegisterBinding
import com.example.quizzapp.models.Account
import com.example.quizzapp.viewmodel.AccountViewModel
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)

class RegisterFragment : Fragment() {

    private var _registerBinding: FragmentRegisterBinding? = null
    private val registerBinding get() = _registerBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBinding.textViewLoginRegisterFragment.setOnClickListener {

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


        registerBinding.btnLoginFragment.setOnClickListener {
            if (registerBinding.editTextFullnameRegisterFragment.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Enter your full name", Toast.LENGTH_SHORT).show()
            } else if (registerBinding.editTextUsernameRegisterFragment.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Enter your email", Toast.LENGTH_SHORT).show()
            } else if (!isEmailValid(registerBinding.editTextUsernameRegisterFragment.text.toString())) {
                Toast.makeText(
                    requireContext(),
                    "Enter your email not invalidate",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (registerBinding.editTextPasswordRegisterFragment.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Enter your password", Toast.LENGTH_SHORT).show()
            } else if (!isStrongPassword(registerBinding.editTextPasswordRegisterFragment.text.toString())) {
                Toast.makeText(requireContext(), "Your password is not strong", Toast.LENGTH_SHORT)
                    .show()
            } else if (registerBinding.editTextConfirmPasswordRegisterFragment.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(requireContext(), "Enter your confirm password", Toast.LENGTH_SHORT)
                    .show()
            } else if (!checkPassword(
                    //edtPasswordRegis
                    registerBinding.editTextPasswordRegisterFragment.text.toString(),
                    registerBinding.editTextConfirmPasswordRegisterFragment.text.toString()
                )
            ) {
                Toast.makeText(requireContext(), "Password not match", Toast.LENGTH_SHORT).show()
            } else {
                val isAmin = checkAccountList()
                val username = registerBinding.editTextUsernameRegisterFragment.text.toString()
                val password = registerBinding.editTextPasswordRegisterFragment.text.toString()
                val fullname = registerBinding.editTextFullnameRegisterFragment.text.toString()
                val dateCreated = getCurrentDateTime().toString()
                val isActive = true
                val account = Account(
                    username,
                    password,
                    fullname,
                    dateCreated,
                    isAmin,
                    true
                )
                accountViewModel.insertAccount(account)
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _registerBinding = null
    }

    private val accountViewModel: AccountViewModel by lazy {
        val application = requireActivity().application
        ViewModelProvider(
            this,
            AccountViewModel.AccountViewModelFactory(application)
        )[AccountViewModel::class.java]
    }

    fun isStrongPassword(password: String): Boolean {

        //regex
        if (password.length < 8) {
            return false
        }
        val hasLetter = password.any { it.isLetter() }

        val hasDigit = password.any { it.isDigit() }

        return hasLetter && hasDigit
    }

    fun getCurrentDateTime(): LocalDateTime {
        return LocalDateTime.now()
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun checkPassword(str1: String, str2: String): Boolean {
        return str1 == str2
    }

    fun checkAccountList(): Boolean {
        var accounts: List<Account>? = null
        accountViewModel.getAllAccount().observe(requireActivity(), Observer {
            accounts = it
        })
        return accounts?.isEmpty() == true

    }

//    quizSetViewModel.getAllQuizSet().observe(requireActivity(), Observer {
//        adapter.setQuizSet(it)
//    })
}