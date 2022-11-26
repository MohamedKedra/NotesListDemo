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
import com.example.noteslistdemo.databinding.FragmentFirstBinding
import com.example.noteslistdemo.remote.ItemResult
import com.example.noteslistdemo.utils.Constant
import com.example.noteslistdemo.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.loading_notes_layout.*

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val listViewModel by viewModels<ListViewModel>()
    lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListAdapter(requireContext()) {

            val bundle = Bundle()
            bundle.putString(Constant.name, it.name)
            bundle.putString(Constant.price, it.price)
            bundle.putString(Constant.dateTime, it.created_at)
            bundle.putString(Constant.image, it.image_urls[0])

            findNavController().navigate(R.id.action_ListFragment_to_DetailsFragment, bundle)
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
                    adapter.addItems(it.getData()?.results as ArrayList<ItemResult>)
                    rv_notes.adapter = adapter
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
            pb_progressbar.isVisible = isLoading
            tv_error.isVisible = hasError
            tv_error.text = txt
            btn_retry.isVisible = hasError
            btn_retry.setOnClickListener {
                initObserver()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}