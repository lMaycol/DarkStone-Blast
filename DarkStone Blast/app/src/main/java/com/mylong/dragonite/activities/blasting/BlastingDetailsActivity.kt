package com.mylong.dragonite.activities.blasting

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.mylong.dragonite.R
import com.mylong.dragonite.data.config.Constants
import com.mylong.dragonite.data.models.Blasting

class BlastingDetailsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var llGoToLinkedin: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blasting_details)

        //Toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black))
        toolbar.title = "Regresar"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.navigationIcon?.setTint(resources.getColor(R.color.black_light))

        llGoToLinkedin = findViewById(R.id.llLinkToSocialNetwork)

        val tvTitle = findViewById<TextView>(R.id.tvBlastingTitle)
        val tvDefinition = findViewById<TextView>(R.id.tvBlastingDefinition)
        val ivBlasting = findViewById<ImageView>(R.id.ivBlasting)
        val tvConcepts = findViewById<TextView>(R.id.tvBlastingConcepts)
        val tvRelevant = findViewById<TextView>(R.id.tvBlastingRelevant)
        val tvPrinciples = findViewById<TextView>(R.id.tvBlastingPrinciples)
        val tvApplications = findViewById<TextView>(R.id.tvBlastingApplications)
        val tvChallenges = findViewById<TextView>(R.id.tvBlastingChallenges)
        val tvDevelopments = findViewById<TextView>(R.id.tvBlastingDevelopments)
        val tvConclusion = findViewById<TextView>(R.id.tvBlastingConclusion)
        val tvResources = findViewById<TextView>(R.id.tvBlastingResources)

        val getItems = intent.getParcelableExtra<Blasting>(Constants.BLASTING_KEY)

        if (getItems != null) {
            tvTitle.text = getItems.title
            tvDefinition.text = getItems.definition
            tvConcepts.text = getItems.concepts
            tvRelevant.text = getItems.relevant
            tvPrinciples.text = getItems.principles
            tvApplications.text = getItems.applications
            tvChallenges.text = getItems.challenges
            tvDevelopments.text = getItems.developments
            tvConclusion.text = getItems.conclusion
            tvResources.text = getItems.resources
            ivBlasting.setImageResource(getItems.image)
        }

        llGoToLinkedin.setOnClickListener { goToLinkedin() }

    }

    private fun goToLinkedin() {
        val url = "https://www.linkedin.com/in/mickaelbenavides"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}