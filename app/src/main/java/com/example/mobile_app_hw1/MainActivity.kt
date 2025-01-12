package com.example.mobile_app_hw1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val searchView: androidx.appcompat.widget.SearchView = findViewById(R.id.searchView)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.addItemDecoration(divider)

        val adapter = RecipeAdapter(
            listOf(),
            itemClickListener = { recipe ->
                Toast.makeText(this, "Clicked ID: ${recipe.id}", Toast.LENGTH_SHORT).show()
            },
            actionClickListener = { recipe, action ->
                Toast.makeText(this, "$action clicked, ID: ${recipe.id}", Toast.LENGTH_SHORT).show()
            }
        )

        recyclerView.adapter = adapter

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipes.collectLatest { recipes ->
                    adapter.submitList(recipes)
                }
            }
        }

        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterRecipes(newText ?: "")
                return true
            }
        })
    }
}
