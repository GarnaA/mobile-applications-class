package com.example.mobile_app_hw1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val itemClickListener: (Recipe) -> Unit,
    private val actionClickListener: (Recipe, String) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleRecipe: TextView = view.findViewById(R.id.titleRecipe)
        private val buttonLike: Button = view.findViewById(R.id.buttonLike)
        private val buttonShare: Button = view.findViewById(R.id.buttonShare)
        private val imageRecipe: ImageView = view.findViewById(R.id.imageRecipe)

        fun bind(recipe: Recipe) {
            titleRecipe.text = recipe.title
            Glide.with(imageRecipe.context).load(recipe.imageUrl).into(imageRecipe)

            itemView.setOnClickListener {
                itemClickListener(recipe)
            }

            buttonLike.setOnClickListener {
                actionClickListener(recipe, "like")
            }

            buttonShare.setOnClickListener {
                actionClickListener(recipe, "share")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size
}
