package com.example.mobile_app_hw1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)

        val recipes = listOf(
            Recipe(1, "STEAK", imageUrl = R.drawable.steak),
            Recipe(2, "KHINKALI", imageUrl = R.drawable.khinkali),
            Recipe(3, "KHACHAPURI", imageUrl = R.drawable.khachapuri)
        )

        val adapter = RecipeAdapter(
            recipes,
            itemClickListener = { recipe ->
                Toast.makeText(this, "Clicked: ${recipe.title}", Toast.LENGTH_SHORT).show()
                Log.d("Item Clicked", "${recipe.id}")
            },
            actionClickListener = { recipe, action ->
                Toast.makeText(this, "$action clicked: ${recipe.title}", Toast.LENGTH_SHORT).show()
                Log.d("Action Clicked", "$action clicked: ${recipe.id}")
            }
        )

        recyclerView.adapter = adapter
    }
}
