package com.lucascabral.marvelsuperheroes.presenter.view

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.lucascabral.marvelsuperheroes.databinding.FragmentAllCharactersBinding
import com.lucascabral.marvelsuperheroes.extension.navigateWithAnimations
import com.lucascabral.marvelsuperheroes.helper.NetworkChecker
import com.lucascabral.marvelsuperheroes.presenter.adapter.AllCharactersAdapter
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.AllCharactersViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCharactersFragment : Fragment() {

    private val viewModel: AllCharactersViewModel by viewModel()
    private val networkChecker by lazy {
        NetworkChecker(ContextCompat.getSystemService(requireContext(), ConnectivityManager::class.java))
    }
    private lateinit var binding: FragmentAllCharactersBinding
    private lateinit var allCharactersAdapter: AllCharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setRefreshListener()
        networkChecker.performActionIfConnected { initViewModel() }
    }

    private fun initRecyclerView() {
        binding.charactersRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            allCharactersAdapter = AllCharactersAdapter() { character ->
                val directions = AllCharactersFragmentDirections
                    .actionAllCharactersFragmentToCharacterDetailsFragment(character)
                findNavController().navigateWithAnimations(directions)
            }
            adapter = allCharactersAdapter

            allCharactersAdapter.addLoadStateListener { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && allCharactersAdapter.itemCount == 0
                showEmptyList(isListEmpty)

                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            }
        }
    }

    private fun showEmptyList(listEmpty: Boolean) {
        if (listEmpty) {
            binding.emptyListTextView.visibility = View.VISIBLE
            binding.charactersRecyclerView.visibility = View.GONE

        } else {
            binding.emptyListTextView.visibility = View.GONE
            binding.charactersRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun setRefreshListener() {
        binding.allCharactersRefresh.setOnRefreshListener {
            initViewModel()
        }
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            binding.allCharactersRefresh.isRefreshing = false
            viewModel.getListData().collectLatest { pagingData ->
                allCharactersAdapter.submitData(pagingData)
            }
        }
    }
}