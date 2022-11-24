package com.momen.nytimes.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.momen.nytimes.NewsApplication
import com.momen.nytimes.data.NewsResult
import com.momen.nytimes.databinding.FragmentMainBinding
import com.momen.nytimes.viewmodels.MainViewModel
import com.momen.nytimes.viewmodels.MainViewModelFactory
import javax.inject.Inject


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NewsAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as NewsApplication).applicationComponent.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        val listener = object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(newsItem: NewsResult) {
                val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(newsItem)
                findNavController().navigate(action)
            }
        }
        adapter = NewsAdapter(listener)
        binding.newsList.adapter = adapter
        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        binding.refreshLayout.setOnRefreshListener {
            viewModel.callApi()
        }

        viewModel.newsLiveData.observe(viewLifecycleOwner) {
            binding.refreshLayout.isRefreshing = false
            if (it.status == "OK") {
                binding.progressBar.visibility = View.GONE
                adapter.updateList(it.results)
            } else
                binding.apply {
                    tryAgain.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    newsList.visibility = View.GONE
                }
        }

        binding.apply {
            tryAgain.setOnClickListener {
                tryAgain.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                newsList.visibility = View.VISIBLE
                viewModel.callApi()
            }
        }



        return binding.root
    }


}