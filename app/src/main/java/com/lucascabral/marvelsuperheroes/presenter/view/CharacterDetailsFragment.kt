package com.lucascabral.marvelsuperheroes.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.data.network.model.comics.Comic
import com.lucascabral.marvelsuperheroes.databinding.FragmentCharacterDetailsBinding
import com.lucascabral.marvelsuperheroes.presenter.adapter.ComicsAdapter
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : Fragment() {

    val args: CharacterDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel: CharacterDetailsViewModel by viewModel()
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val (uri, description, name) = getDetailsWithSafeArgs()
        setupViews(uri, description, name)

        viewModel.comics.observe(viewLifecycleOwner) { comicList ->
            populateRecyclerView(comicList)
        }
        viewModel.getComicsByCharacterId(args.character.id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.popBackStack(R.id.allCharactersFragment, false)
        return super.onOptionsItemSelected(item)
    }

    private fun getDetailsWithSafeArgs(): Triple<String, String, String> {
        val uri = args.character.thumbnail.path + "." + args.character.thumbnail.extension
        val description = args.character.description
        val name = args.character.name
        return Triple(uri, description, name)
    }

    private fun setupViews(uri: String, description: String, name: String) {
        binding.apply {
            Glide.with(this@CharacterDetailsFragment).load(uri).into(detailsCharacterThumbImageView)
            detailsCharacterNameTextView.text = name
            if (description.isEmpty()) {
                detailsCharacterDescriptionTextView.text = getString(R.string.character_unknown, name)
            } else {
                detailsCharacterDescriptionTextView.text = description
            }
        }
    }

    private fun populateRecyclerView(comicList: List<Comic>) {
        binding.detailsCharacterRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = ComicsAdapter(comicList)
        }
    }
}