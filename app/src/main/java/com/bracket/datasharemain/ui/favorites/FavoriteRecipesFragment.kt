package com.bracket.datasharemain.ui.favorites

import android.graphics.Point
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bracket.datasharemain.MainApplication
import com.bracket.datasharemain.R
import com.bracket.datasharemain.data.RecipePersistence
import com.bracket.datasharemain.ui.main.RecipeAdapter
import kotlinx.android.synthetic.main.favorite_recipes_fragment.*
import javax.inject.Inject

class FavoriteRecipesFragment : Fragment() {

    @Inject
    lateinit var recipePersistence: RecipePersistence

    private lateinit var viewModel: FavoriteRecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.dataProvider.inject(this)
        viewModel =
            ViewModelProvider(this, FavoriteRecipesViewModel.Factory(recipePersistence)).get(
                FavoriteRecipesViewModel::class.java
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Favorites"
        return inflater.inflate(R.layout.favorite_recipes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        viewModel.favoriteRecipes.observe(viewLifecycleOwner) {
            favorite_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            favorite_list.adapter = RecipeAdapter(it, getImageWidth(view)) { info, extras ->
                val action =
                    FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToRecipeDetailFragment(
                        info
                    )
                findNavController().navigate(action, extras)
            }
        }
    }

    private fun getImageWidth(view: View): Int {
        val display: Display = view.display
        val size = Point()
        display.getSize(size)
        return size.x
    }
}