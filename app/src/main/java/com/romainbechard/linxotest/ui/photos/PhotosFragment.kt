package com.romainbechard.linxotest.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.romainbechard.linxotest.R
import com.romainbechard.linxotest.databinding.PhotosFragmentBinding

class PhotosFragment : Fragment() {

    private lateinit var binding: PhotosFragmentBinding
    private val photoViewModel by viewModels<PhotosViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhotosFragmentBinding.inflate(layoutInflater, container, false).apply {
            viewModel = photoViewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}