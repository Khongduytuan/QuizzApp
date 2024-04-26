package com.example.quizzapp.screen.loginScreen.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentLoginBinding
import com.example.quizzapp.screen.homeScreen.HomeActivity
import com.example.quizzapp.viewmodel.AccountViewModel


class LoginFragment : Fragment() {

    private var _loginBinding: FragmentLoginBinding? = null
    private val loginBinding get() = _loginBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return loginBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBinding.textViewAccountLoginFragment.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

       with(loginBinding){

       }
        loginBinding.btnLoginFragment.setOnClickListener {
//            val intent = Intent(requireActivity(), HomeActivity::class.java)
//            Log.d("btnLoginFragment", "Click")
//            requireActivity().startActivity(intent)

            if (loginBinding.editTextUsernameFragment.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Enter your username", Toast.LENGTH_SHORT).show()
            } else if (loginBinding.editTextPasswordFragment.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Enter your password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val username = loginBinding.editTextUsernameFragment.text.toString()
                val password = loginBinding.editTextPasswordFragment.text.toString()
//                Log.d("LoginBtn", "$username $password")
//                var accounts: List<Account>? = null
                accountViewModel.getAllAccountWithUsernameAndPassword(username, password)
                    .observe(requireActivity(), Observer {
                        if(it != null){
                            val intent = Intent(requireActivity(), HomeActivity::class.java)
                            Log.d("btnLoginFragment", "Click")
                            requireActivity().startActivity(intent)
                        }
                        else{
                            Toast.makeText(requireContext(), "Login error", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })

//                Log.d("LoginBtn", (accounts?.isNotEmpty() == true).toString())
//                if (accounts?.isNotEmpty() == true) {
//                    Log.d("LoginBtn", accounts?.size.toString())
//                    val intent = Intent(requireActivity(), HomeActivity::class.java)
//                    Log.d("btnLoginFragment", "Click")
//                    requireActivity().startActivity(intent)
//                } else {
//                    Log.d("LoginBtn", accounts?.size.toString())
//                    Toast.makeText(requireContext(), "Login error", Toast.LENGTH_SHORT)
//                        .show()
//                }
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _loginBinding = null
    }

    private val accountViewModel: AccountViewModel by lazy {
        val application = requireActivity().application
        ViewModelProvider(
            this,
            AccountViewModel.AccountViewModelFactory(application)
        )[AccountViewModel::class.java]
    }
}