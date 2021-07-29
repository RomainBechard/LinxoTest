package com.romainbechard.linxotest.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.navigation.fragment.navArgs
import com.romainbechard.linxotest.LinxoTestApplication
import com.romainbechard.linxotest.data.model.Photo
import com.romainbechard.linxotest.databinding.PhotosFragmentBinding
import com.romainbechard.linxotest.tools.EventObserver
import timber.log.Timber

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
        val id = navArgs<PhotosFragmentArgs>().value.albumId
        photoViewModel.configure(application.repository, id)
        photoViewModel.photoFetchedEvent.observe(
            viewLifecycleOwner,
            EventObserver {
                Timber.d("Photo List ${photoViewModel.photoUrlList[0]}")
                binding.simpleGridView.adapter =
                    PhotoAdapter(application, photoViewModel.photoUrlList)
            }
        )
    }
}