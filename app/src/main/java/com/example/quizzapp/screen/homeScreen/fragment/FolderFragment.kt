package com.example.quizzapp.screen.homeScreen.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentFolderBinding
import com.example.quizzapp.models.QuizSet
import com.example.quizzapp.screen.homeScreen.adapters.QuizSetAdapter
import com.example.quizzapp.state.QuizSetDataState
import com.example.quizzapp.viewmodel.QuizSetViewModel
import kotlinx.coroutines.launch


class FolderFragment : Fragment() {

    private lateinit var folderBinding: FragmentFolderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        folderBinding = FragmentFolderBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return folderBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControls()
        initEvents()
    }

    private fun initEvents() {

    }

    private fun initControls() {
        val adapter: QuizSetAdapter = QuizSetAdapter(requireContext(), onClickItem, deleteItem)
        folderBinding.recycleViewQuizSetFolder.setHasFixedSize(true)
        folderBinding.recycleViewQuizSetFolder.layoutManager = LinearLayoutManager(requireContext())
        folderBinding.recycleViewQuizSetFolder.adapter = adapter

        quizSetViewModel.getAllQuizSet()

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
                        adapter.setQuizSet(it.data)
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