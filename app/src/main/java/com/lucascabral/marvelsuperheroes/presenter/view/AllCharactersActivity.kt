package com.lucascabral.marvelsuperheroes.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.lucascabral.marvelsuperheroes.databinding.ActivityAllCharactersBinding
import com.lucascabral.marvelsuperheroes.presenter.adapter.AllCharactersAdapter
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.AllCharactersViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCharactersActivity : AppCompatActivity() {

    private val viewModel: AllCharactersViewModel by viewModel()
    private lateinit var viewBinding: ActivityAllCharactersBinding
    private lateinit var allCharactersAdapter: AllCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAllCharactersBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        viewBinding.charactersRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            allCharactersAdapter = AllCharactersAdapter()
            adapter = allCharactersAdapter
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