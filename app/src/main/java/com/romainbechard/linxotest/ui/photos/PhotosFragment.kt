package com.romainbechard.linxotest.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.romainbechard.linxotest.LinxoTestApplication
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val application = requireContext().applicationContext as LinxoTestApplication
        photoViewModel.configure(application.repository)
    }
}