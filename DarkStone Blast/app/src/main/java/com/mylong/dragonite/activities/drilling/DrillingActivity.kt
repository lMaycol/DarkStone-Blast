package com.mylong.dragonite.activities.drilling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mylong.dragonite.R
import com.mylong.dragonite.activities.blasting.BlastingActivity
import com.mylong.dragonite.data.adapters.DrillingAdapter
import com.mylong.dragonite.data.config.Constants
import com.mylong.dragonite.data.models.Drilling
import com.mylong.dragonite.data.repository.DrillingRepository
import java.text.Normalizer

class DrillingActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var adapter: DrillingAdapter
    private lateinit var drillingFilter: SearchView
    private val drillingMutableList: MutableList<Drilling> = DrillingRepository.drillingList.toMutableList()

    private lateinit var goToBlastingActivity: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drilling)

        //Toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black))
        toolbar.title = "DarkStone Blast"
        setSupportActionBar(toolbar)

        goToBlastingActivity = findViewById(R.id.llGoToBlastingActivity)
        goToBlastingActivity.setOnClickListener { navigateToBlastingActivity() }

        //Filter
        drillingFilter = findViewById(R.id.svDrillingFilter)
        drillingFilter.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    drillingFilter.clearFocus() // Cerrar el teclado al presionar el botón de búsqueda
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchDrilling(newText.orEmpty())
                    return true
                }
            })
            setOnSearchClickListener { drillingFilter.clearFocus() } // Cerrar el teclado al tocar el ícono de búsqueda
        }

        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = DrillingAdapter(
            drillingList = drillingMutableList,
            btnClick = { d -> goToBlastingDetailsActivity(d) }
        )

        val gridLayoutManager = GridLayoutManager(this, 1)

        val recyclerView: RecyclerView = findViewById(R.id.rvDrilling)
        recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = this@DrillingActivity.adapter
        }
    }

    private fun goToBlastingDetailsActivity(drilling: Drilling) {
        val intent = Intent(this, DrillingDetailsActivity::class.java)
        intent.putExtra(Constants.DRILLING_KEY, drilling)
        startActivity(intent)
    }

    private fun removeDiacritics(input: String): String {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
            .replace("\\p{M}".toRegex(), "")
    }

    private fun searchDrilling(query: String) {
        val normalizedQuery = removeDiacritics(query.lowercase())

        val drillingFiltered = drillingMutableList.filter { drilling ->
            val normalizedFields = listOf(
                drilling.title,
                drilling.definition,
                drilling.concepts,
                drilling.relevant,
                drilling.principles,
                drilling.applications,
                drilling.challenges,
                drilling.developments,
                drilling.conclusion,
                drilling.resources
            ).map { removeDiacritics(it.lowercase()) }

            normalizedFields.any { it.contains(normalizedQuery) }
        }
        adapter.updateDrilling(drillingFiltered)
    }

    private fun navigateToBlastingActivity() {
        val i = Intent(this, BlastingActivity::class.java)
        startActivity(i)
    }

}