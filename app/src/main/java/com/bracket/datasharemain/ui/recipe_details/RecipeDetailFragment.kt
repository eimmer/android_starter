package com.bracket.datasharemain.ui.recipe_details

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
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
import com.bracket.datasharemain.data.model.NormalRecipe
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
        favorite_button.setOnClickListener {
            viewModel.markAsFavorite()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args: RecipeDetailFragmentArgs by navArgs()
        val recipe = NormalRecipe(
            args.recipeInfo.id,
            args.recipeInfo.title,
            args.recipeInfo.summary,
            args.recipeInfo.imageUrl
        )

        viewModel = ViewModelProvider(
            this,
            RecipeDetailViewModel.Factory(recipe, recipePersistence = recipePersistence)
        ).get(RecipeDetailViewModel::class.java)


        view?.let { v ->
            viewModel.liveRecipe.observe(viewLifecycleOwner, { handleRecipeDisplay(it, v) })
        }
        viewModel.isRecipeFavorite.observe(viewLifecycleOwner, { handleFavorite(it) })
    }

    private fun handleFavorite(isFavorite: Boolean) {
        val resourceId =
            if (isFavorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
        val drawable = ResourcesCompat.getDrawable(resources, resourceId, null)
        favorite_button.setImageDrawable(drawable)
    }

    private fun handleRecipeDisplay(recipe: NormalRecipe, view: View) {
        setupHeroWidget(view, recipe.imageUrl)
        setupTitle(recipe.title)
        setupSummary(recipe.summary)
    }

    private fun setupSummary(summary: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            recipe_summary.text = Html.fromHtml(summary, FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            recipe_summary.text = Html.fromHtml(summary)
        }
    }

    private fun setupTitle(title: String) {
        recipe_name.text = title
    }

    private fun setupHeroWidget(view: View, url: String?) {
        val heroImageView = view.findViewById<ImageView>(R.id.recipe_image)

        if (url == null) {
            heroImageView.visibility = View.GONE

        } else {

            val display: Display = view.display
            val size = Point()
            display.getSize(size)
            val width: Int = size.x

            Picasso.with(context)
                .load(url)
                .resize(width, width / 2)
                .into(heroImageView)
        }
    }
}