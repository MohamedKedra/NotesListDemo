package com.example.noteslistdemo.view.details

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.noteslistdemo.R
import com.example.noteslistdemo.databinding.FragmentSecondBinding
import com.example.noteslistdemo.remote.ItemResult
import com.example.noteslistdemo.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            arguments?.apply {
                tvName.text = getString(Constant.name)
                tvPrice.text = getString(Constant.price)
                val dateTime = getString(Constant.dateTime)?.split(" ")
                tvDate.text = dateTime?.get(0)
                tvTime.text = dateTime?.get(1)?.substring(0, 5)
                Glide.with(requireContext()).load(getString(Constant.image))
                    .apply(
                        RequestOptions()
                            .placeholder(androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha)
                            .error(R.drawable.ic_launcher_background)
                    )
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.d("aa", "===${e}")
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    }).into(ivPic)
            }
           }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}