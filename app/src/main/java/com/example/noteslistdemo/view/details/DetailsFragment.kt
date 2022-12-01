package com.example.noteslistdemo.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.noteslistdemo.databinding.FragmentDetailsBinding
import com.example.noteslistdemo.utils.options
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding : FragmentDetailsBinding

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            args.item?.apply {
                tvName.text = name
                tvPrice.text = price
                val dateTime = created_at.split(" ")
                tvDate.text = dateTime[0]
                tvTime.text = dateTime[1].substring(0, 5)
                Glide.with(requireContext()).setDefaultRequestOptions(options).load(image_urls[0])
                    .into(ivPic)
            }
        }
    }
}