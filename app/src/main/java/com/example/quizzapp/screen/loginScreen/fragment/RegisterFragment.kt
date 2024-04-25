package com.example.quizzapp.screen.loginScreen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentRegisterBinding


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
    }


    override fun onDestroy() {
        super.onDestroy()
        _registerBinding = null
    }


}