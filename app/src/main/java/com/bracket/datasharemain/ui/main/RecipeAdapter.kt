package com.bracket.datasharemain.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bracket.datasharemain.BuildConfig
import com.bracket.datasharemain.R
import com.bracket.datasharemain.data.model.NormalRecipe
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private val recipes: List<NormalRecipe>,
    private val width:Int
) : Adapter<RecipeAdapter.RecipeViewHolder>() {

    init {
        if (BuildConfig.DEBUG && width <= 0) {
            error("width parameter is invalid with the following value: $width")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_recipe_overview, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    inner class RecipeViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(info: NormalRecipe) {

            val titleView = itemView.findViewById<TextView>(R.id.recipe_title)
            titleView.text = info.title

            val imageView = itemView.findViewById<ImageView>(R.id.recipe_image)
            imageView.transitionName = "${info.title}_hero"

            Picasso.with(itemView.context)
                .load(info.imageUrl)
                .resize(width, width / 2)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        itemView.setOnClickListener {
                            val extras = FragmentNavigatorExtras(imageView to "hero_image")
                            val action =
                                MainFragmentDirections.actionMainFragmentToRecipeDetailFragment(info)
                            itemView.findNavController().navigate(action, extras)
                        }
                    }

                    override fun onError() {
                        // If image loads in error, use default navigation with no animation.
                    }
                })

        }
    }
}

