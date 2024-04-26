package com.example.quizzapp.screen.homeScreen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentHomeBinding
import com.example.quizzapp.models.QuizSet
import com.example.quizzapp.screen.homeScreen.adapters.QuizSetAdapter
import com.example.quizzapp.state.QuizSetDataState
import com.example.quizzapp.viewmodel.QuizSetViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControls()
        initEvents()
    }

    private fun initEvents() {
        var x = 0
        homeBinding.textViewHi.setOnClickListener {
            val quizSet = QuizSet(
                name = "Front End Developer $x",
                des = "no des",
                dateCreated = "24/05/2024",
                createdBy = "Admin"
            )
            quizSetViewModel.insertQuizSet(quizSet)
            x++
        }
    }

    private fun initControls() {
        val adapter: QuizSetAdapter = QuizSetAdapter(requireContext(), onClickItem, deleteItem)
        homeBinding.recycleViewQuizSet.setHasFixedSize(true)
        homeBinding.recycleViewQuizSet.layoutManager = LinearLayoutManager(requireContext())
        homeBinding.recycleViewQuizSet.adapter = adapter

        quizSetViewModel.getAllQuizSet()
        // Livedata
//        quizSetViewModel.getAllQuizSet().observe(requireActivity(), Observer {
//            adapter.setQuizSet(it)
//        })
        // Flow
//        lifecycleScope.launch {
//            quizSetViewModel.getAllQuizSet().collect{
//                adapter.setQuizSet(it)
//            }
//        }
        // StateFlow
        lifecycleScope.launch {
            quizSetViewModel._quizSetStateFlow.collect { it ->
                when (it) {
                    is QuizSetDataState.Loading -> {
                        Toast.makeText(
                            requireContext(),
                            "Loading data...",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is QuizSetDataState.Failure -> {
                        Toast.makeText(
                            requireContext(),
                            "Error Get Data Quiz ${it.msg}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is QuizSetDataState.Success ->{
                        Toast.makeText(
                            requireContext(),
                            "Get Data Success!",
                            Toast.LENGTH_LONG
                        ).show()
                        adapter.setQuizSet(it.data)
                    }
                    is QuizSetDataState.Empty -> {
                        Toast.makeText(
                            requireContext(),
                            "Data Is Empty",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }
        }

    }

    private val onClickItem: (QuizSet) -> Unit = {

    }
    private val deleteItem: (QuizSet) -> Unit = {

    }


    private val quizSetViewModel: QuizSetViewModel by lazy {
        val application = requireActivity().application
        ViewModelProvider(
            this,
            QuizSetViewModel.QuizSetViewModelFactory(application)
        )[QuizSetViewModel::class.java]
    }


}