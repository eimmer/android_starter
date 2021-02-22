package com.bracket.datasharemain.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bracket.datasharemain.R
import com.bracket.datasharemain.data.model.RecipeInformation
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private val recipes: List<RecipeInformation>,
    private val targetWidth: Int,
    private val viewModel: MainViewModel
) : Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_recipe_overview, parent, false)
        return RecipeViewHolder(view, targetWidth)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    inner class RecipeViewHolder(view: View, private val targetWidth: Int) :
        RecyclerView.ViewHolder(view) {

        fun bind(info: RecipeInformation) {
            itemView.findViewById<TextView>(R.id.recipe_title).text = info.title
            Picasso.with(itemView.context)
                .load(info.image)
                .resize(targetWidth, targetWidth / 2)
                .into(itemView.findViewById<ImageView>(R.id.recipe_image))
            itemView.setOnClickListener {
                viewModel.newEvent(RecipeSelectedEvent(info))
            }
        }
    }
}

