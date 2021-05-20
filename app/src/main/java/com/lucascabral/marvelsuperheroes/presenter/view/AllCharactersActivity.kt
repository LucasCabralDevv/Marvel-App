package com.lucascabral.marvelsuperheroes.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.AllCharactersViewModel
import kotlinx.coroutines.flow.collectLatest

class AllCharactersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_characters)
        initViewModel()
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(AllCharactersViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest { pagingData ->
                val page = pagingData
            }
        }
    }
}