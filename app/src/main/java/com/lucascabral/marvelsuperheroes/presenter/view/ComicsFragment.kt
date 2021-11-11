package com.lucascabral.marvelsuperheroes.presenter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.FragmentComicsBinding

class ComicsFragment : Fragment() {

    private lateinit var binding: FragmentComicsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicsBinding.inflate(inflater, container, false)
        return binding.root
    }
}