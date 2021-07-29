package com.romainbechard.linxotest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.romainbechard.linxotest.LinxoTestApplication
import com.romainbechard.linxotest.databinding.MainFragmentBinding
import com.romainbechard.linxotest.tools.EventObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch
import timber.log.Timber

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: AlbumAdapter
    private lateinit var application: LinxoTestApplication
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false).apply {
            viewModel = mainViewModel
        }
        setUpAdapter()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvAlbums.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        application = requireContext().applicationContext as LinxoTestApplication
        mainViewModel.configure(application.repository)

        mainViewModel.goToPhotosEvent.observe(
            viewLifecycleOwner,
            EventObserver {
                val albumId = MainFragmentDirections.actionGoToPhotosFragment(it)
                findNavController().navigate(albumId)
            }
        )
    }

    private fun setUpAdapter() {
        val viewModel = binding.viewModel
        if (viewModel != null) {
            adapter = AlbumAdapter(viewModel)
            binding.rvAlbums.adapter = adapter
        } else {
            Timber.d("Error during adapter setup")
        }
    }

}