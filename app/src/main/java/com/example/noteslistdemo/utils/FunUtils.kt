package com.example.noteslistdemo.utils

import com.bumptech.glide.request.RequestOptions
import com.example.noteslistdemo.R

val options = RequestOptions()
    .centerCrop()
    .error(R.drawable.ic_error_image)
    .placeholder(R.drawable.ic_launcher_background)