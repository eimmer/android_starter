package com.bracket.datasharemain.ui.recipe_details

import android.graphics.Point
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bracket.datasharemain.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_detail_fragment.*

class RecipeDetailFragment : Fragment() {

    companion object {
        fun newInstance() = RecipeDetailFragment()
    }

    private lateinit var viewModel: RecipeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipe_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: RecipeDetailFragmentArgs by navArgs()

        val display: Display = view.display
        val size = Point()
        display.getSize(size)
        val width: Int = size.x

        Picasso.with(context)
            .load(args.recipeInfo.image)
            .resize(width, width / 2)
            .into(recipe_image)

        recipe_name.text = args.recipeInfo.title
        recipe_instructions.text = args.recipeInfo.summary
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
    }



}