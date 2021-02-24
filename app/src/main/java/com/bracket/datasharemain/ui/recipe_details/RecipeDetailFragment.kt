package com.bracket.datasharemain.ui.recipe_details

import android.graphics.Point
import android.os.Bundle
import android.text.Html
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bracket.datasharemain.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_detail_fragment.*

class RecipeDetailFragment : Fragment() {

    companion object {
        fun newInstance() = RecipeDetailFragment()
    }

    private lateinit var viewModel: RecipeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipe_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: RecipeDetailFragmentArgs by navArgs()
        val heroImageView = view.findViewById<ImageView>(R.id.recipe_image)

        val display: Display = view.display
        val size = Point()
        display.getSize(size)
        val width: Int = size.x

        Picasso.with(context)
            .load(args.recipeInfo.image)
            .resize(width, width / 2)
            .into(heroImageView)

        recipe_name.text = args.recipeInfo.title
        recipe_instructions.text = Html.fromHtml(args.recipeInfo.summary)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
    }


}