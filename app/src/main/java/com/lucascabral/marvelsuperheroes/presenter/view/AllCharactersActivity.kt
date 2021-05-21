package com.lucascabral.marvelsuperheroes.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.AllCharactersViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCharactersActivity : AppCompatActivity() {

    private val viewModel: AllCharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_characters)
        initViewModel()
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest { pagingData ->
                val page = pagingData
            }
        }
    }
}