package com.shaunhossain.dogsinformation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.shaunhossain.dogsinformation.R
import com.shaunhossain.dogsinformation.adapter.DogsAdapter
import com.shaunhossain.dogsinformation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    @Inject
    lateinit var dogsAdapter : DogsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initRecyclerView()

        lifecycleScope.launchWhenStarted {
            homeViewModel.getAllDogsInfo().collectLatest { response->
                binding.apply {
                    recyclerview.isVisible = true
                    progressBar.isVisible = false
                }
                dogsAdapter.submitData(response)
            }
        }
        return root
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireContext(),2)
                adapter = dogsAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}