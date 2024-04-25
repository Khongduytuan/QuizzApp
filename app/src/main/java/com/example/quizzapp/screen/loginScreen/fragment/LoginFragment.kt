package com.example.quizzapp.screen.loginScreen.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentLoginBinding
import com.example.quizzapp.screen.homeScreen.HomeActivity


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


        loginBinding.btnLoginFragment.setOnClickListener {
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            Log.d("btnLoginFragment", "Click")
            requireActivity().startActivity(intent)

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _loginBinding = null
    }
}