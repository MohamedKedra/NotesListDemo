package com.example.noteslistdemo.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteslistdemo.R
import com.example.noteslistdemo.databinding.FragmentListBinding
import com.example.noteslistdemo.remote.ItemResult
import com.example.noteslistdemo.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val listViewModel by viewModels<ListViewModel>()
    lateinit var adapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NoteListAdapter(requireContext()) {
            val action = ListFragmentDirections.actionListFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }

        initObserver()
    }

    private fun initObserver() {

        listViewModel.refreshListItems().observe(viewLifecycleOwner) {

            when (it.getStatus()) {

                DataState.DataStatus.LOADING -> {
                    showHideLoading(isLoading = true)
                }
                DataState.DataStatus.SUCCESS -> {
                    showHideLoading()
                    adapter.submitList(it.getData()?.results as ArrayList<ItemResult>)
                    binding.rvNotes.adapter = adapter
                }
                DataState.DataStatus.ERROR -> {
                    showHideLoading(hasError = true, txt = it.getError()?.message.toString())
                }
                DataState.DataStatus.NO_INTERNET -> {
                    showHideLoading(
                        hasError = true,
                        txt = getString(R.string.no_internet_connection)
                    )
                }
            }

        }
    }

    private fun showHideLoading(
        isLoading: Boolean = false,
        hasError: Boolean = false,
        txt: String = ""
    ) {
        with(binding) {
            pbProgressbar.isVisible = isLoading
            tvError.isVisible = hasError
            tvError.text = txt
            btnRetry.isVisible = hasError
            btnRetry.setOnClickListener { initObserver() }
        }
    }
}