package com.mylong.dragonite.activities.blasting

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
import com.mylong.dragonite.activities.drilling.DrillingActivity
import com.mylong.dragonite.data.adapters.BlastingAdapter
import com.mylong.dragonite.data.config.Constants
import com.mylong.dragonite.data.models.Blasting
import com.mylong.dragonite.data.repository.BlastingRepository
import java.text.Normalizer

class BlastingActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var adapter: BlastingAdapter
    private lateinit var blastingFilter: SearchView
    private val blastingMutableList: MutableList<Blasting> = BlastingRepository.blastingList.toMutableList()

    private lateinit var goToDrillingActivity: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blasting)

        //Toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black))
        toolbar.title = "DarkStone Blast"
        setSupportActionBar(toolbar)

        goToDrillingActivity = findViewById(R.id.llGoToDrillingActivity)
        goToDrillingActivity.setOnClickListener { navigateToDrillingActivity() }

        //Filter
        blastingFilter = findViewById(R.id.svBlastingFilter)
        blastingFilter.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    blastingFilter.clearFocus() // Cerrar el teclado al presionar el botón de búsqueda
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchBlasting(newText.orEmpty())
                    return true
                }
            })
            setOnSearchClickListener { blastingFilter.clearFocus() } // Cerrar el teclado al tocar el ícono de búsqueda
        }

        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = BlastingAdapter(
            blastingList = blastingMutableList,
            btnClick = { b -> goToBlastingDetailsActivity(b) })

        val gridLayoutManager = GridLayoutManager(this, 1)
        val recyclerView: RecyclerView = findViewById(R.id.rvBlasting)
        recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = this@BlastingActivity.adapter
        }
    }

    private fun goToBlastingDetailsActivity(blasting: Blasting) {
        val intent = Intent(this, BlastingDetailsActivity::class.java)
        intent.putExtra(Constants.BLASTING_KEY, blasting)
        startActivity(intent)
    }

    private fun removeDiacritics(input: String): String {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
            .replace("\\p{M}".toRegex(), "")
    }

    private fun searchBlasting(query: String) {
        val normalizedQuery = removeDiacritics(query.lowercase())

        val mealsFiltered = blastingMutableList.filter { blasting ->
            val normalizedFields = listOf(
                blasting.title,
                blasting.definition,
                blasting.concepts,
                blasting.relevant,
                blasting.principles,
                blasting.applications,
                blasting.challenges,
                blasting.developments,
                blasting.conclusion,
                blasting.resources
            ).map { removeDiacritics(it.lowercase()) }

            normalizedFields.any { it.contains(normalizedQuery) }
        }
        adapter.updateBlasting(mealsFiltered)
    }

    private fun navigateToDrillingActivity() {
        val i = Intent(this, DrillingActivity::class.java)
        startActivity(i)
    }
}
