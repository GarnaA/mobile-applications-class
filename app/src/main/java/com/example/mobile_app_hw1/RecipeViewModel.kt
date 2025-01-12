package com.example.mobile_app_hw1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val allRecipes = listOf(
        Recipe(1, "STEAK", imageUrl = R.drawable.steak),
        Recipe(2, "KHINKALI", imageUrl = R.drawable.khinkali),
        Recipe(3, "KHACHAPURI", imageUrl = R.drawable.khachapuri)
    )

    private val _recipes = MutableStateFlow(allRecipes)
    val recipes: StateFlow<List<Recipe>> get() = _recipes

    private var lastQuery: String = ""

    fun filterRecipes(query: String) {
        if (query == lastQuery) return

        viewModelScope.launch {
            val filtered = if (query.length < 3) {
                allRecipes
            } else {
                allRecipes.filter { it.title.contains(query, ignoreCase = true) }
            }
            if (_recipes.value != filtered) {
                _recipes.value = filtered
            }
            lastQuery = query
        }
    }
}
