package com.lucascabral.marvelsuperheroes.presenter.view

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.ActivityAllCharactersBinding
import com.lucascabral.marvelsuperheroes.helper.NetworkChecker
import com.lucascabral.marvelsuperheroes.presenter.adapter.AllCharactersAdapter
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.AllCharactersViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCharactersActivity : AppCompatActivity() {

    private val viewModel: AllCharactersViewModel by viewModel()
    private val networkChecker by lazy {
        NetworkChecker(ContextCompat.getSystemService(this, ConnectivityManager::class.java))
    }
    private lateinit var viewBinding: ActivityAllCharactersBinding
    private lateinit var allCharactersAdapter: AllCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAllCharactersBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initRecyclerView()
        networkChecker.performActionIfConnected { initViewModel() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.marvel_brasil_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.marvelMenu -> {
                val intent = Intent(this, MarvelYoutubeActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        viewBinding.charactersRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            allCharactersAdapter = AllCharactersAdapter()
            adapter = allCharactersAdapter

            allCharactersAdapter.addLoadStateListener { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && allCharactersAdapter.itemCount == 0
                showEmptyList(isListEmpty)

                viewBinding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            }
        }
    }

    private fun showEmptyList(listEmpty: Boolean) {
        if (listEmpty) {
            viewBinding.emptyListTextView.visibility = View.VISIBLE
            viewBinding.charactersRecyclerView.visibility = View.GONE

        } else {
            viewBinding.emptyListTextView.visibility = View.GONE
            viewBinding.charactersRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest { pagingData ->
                allCharactersAdapter.submitData(pagingData)
            }
        }
    }
}