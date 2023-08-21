package com.mylong.dragonite.activities.drilling

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
import com.mylong.dragonite.data.models.Drilling

class DrillingDetailsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var llGoToLinkedin: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drilling_details)

        //Toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black))
        toolbar.title = "Regresar"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.navigationIcon?.setTint(resources.getColor(R.color.black_light))

        llGoToLinkedin = findViewById(R.id.llLink)

        val tvTitle = findViewById<TextView>(R.id.tvDrillingTitle)
        val tvDefinition = findViewById<TextView>(R.id.tvDrillingDefinition)
        val imgDrilling = findViewById<ImageView>(R.id.ivDrilling)
        val tvConcepts = findViewById<TextView>(R.id.tvDrillingConcepts)
        val tvRelevant = findViewById<TextView>(R.id.tvDrillingRelevant)
        val tvPrinciples = findViewById<TextView>(R.id.tvDrillingPrinciples)
        val tvApplications = findViewById<TextView>(R.id.tvDrillingApplications)
        val tvChallenges = findViewById<TextView>(R.id.tvDrillingChallenges)
        val tvDevelopments = findViewById<TextView>(R.id.tvDrillingDevelopments)
        val tvConclusion = findViewById<TextView>(R.id.tvDrillingConclusion)
        val tvResources = findViewById<TextView>(R.id.tvDrillingResources)

        val getItems = intent.getParcelableExtra<Drilling>(Constants.DRILLING_KEY)

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
            imgDrilling.setImageResource(getItems.image)
        }

        llGoToLinkedin.setOnClickListener { goToLinkedin() }

    }

    private fun goToLinkedin() {
        val url = "https://www.linkedin.com/in/mickaelbenavides"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}