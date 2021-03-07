package com.bracket.datasharemain.ui.recipe_details

import android.graphics.Point
import android.os.Bundle
import android.text.Html
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bracket.datasharemain.MainApplication.Companion.dataProvider
import com.bracket.datasharemain.R
import com.bracket.datasharemain.data.RecipePersistence
import com.bracket.datasharemain.data.entities.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_detail_fragment.*
import javax.inject.Inject

class RecipeDetailFragment : Fragment() {

    @Inject
    lateinit var recipePersistence: RecipePersistence

    private lateinit var viewModel: RecipeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataProvider.inject(this)

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

        val heroImageView = view.findViewById<ImageView>(R.id.recipe_image)

        val display: Display = view.display
        val size = Point()
        display.getSize(size)
        val width: Int = size.x

        val args: RecipeDetailFragmentArgs by navArgs()
        Picasso.with(context)
            .load(args.recipeInfo.image)
            .resize(width, width / 2)
            .into(heroImageView)


        recipe_name.text = args.recipeInfo.title
        recipe_instructions.text = Html.fromHtml(args.recipeInfo.summary)

        favorite_button.setOnClickListener {
            viewModel.markAsFavorite()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args: RecipeDetailFragmentArgs by navArgs()
        val recipe = Recipe(args.recipeInfo.id, args.recipeInfo.title, args.recipeInfo.summary)

        viewModel = ViewModelProvider(
            this,
            RecipeDetailViewModel.Factory(recipe, recipePersistence = recipePersistence)
        ).get(RecipeDetailViewModel::class.java)


        viewModel.isRecipeFavorite.observe(viewLifecycleOwner, {
            val resourceId =
                if (it) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            val drawable = ResourcesCompat.getDrawable(resources, resourceId, null)
            favorite_button.setImageDrawable(drawable)
        })
    }

}