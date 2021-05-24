package com.lucascabral.marvelsuperheroes.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.ActivityCharacterDetailsBinding

class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var detailsBinding: ActivityCharacterDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(detailsBinding.root)

        val (name: String?, uri: String?, description: String?) = getDataExtra()

        setupViews(name, uri, description)
        setupToolbar(name)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun getDataExtra(): Triple<String?, String?, String?> {
        val name: String? = intent.getStringExtra(HERO_NAME)
        val uri: String? = intent.getStringExtra(HERO_URI)
        val description: String? = intent.getStringExtra(HERO_DESCRIPTION)
        return Triple(name, uri, description)
    }

    private fun setupViews(name: String?, uri: String?, description: String?) {
        detailsBinding.apply {
            Glide.with(this@CharacterDetailsActivity).load(uri).into(descriptionCharacterImageView)
            if (description.isNullOrEmpty()) {
                descriptionCharacterTextView.text = getString(R.string.character_unknown, name)
            } else {
                descriptionCharacterTextView.text = description
            }
        }
    }

    private fun setupToolbar(name: String?) {
        supportActionBar?.apply {
            title = name
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    companion object {
        const val HERO_NAME = "name"
        const val HERO_DESCRIPTION = "description"
        const val HERO_URI = "uri"
    }
}