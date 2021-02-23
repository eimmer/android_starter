package com.bracket.datasharemain.ui.main

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
import com.bracket.datasharemain.MainApplication.Companion.dataProvider
import com.bracket.datasharemain.R
import com.bracket.datasharemain.network.CookingService
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject


class MainFragment : Fragment() {

    @Inject
    lateinit var cookingService: CookingService

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataProvider.inject(this)

        viewModel = ViewModelProvider(
            this,
            MainViewModel.Factory(cookingService)
        ).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = "Recipes"
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        val display: Display = view.display
        val size = Point()
        display.getSize(size)
        val width: Int = size.x

        recipe_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        viewModel.recipes.observe(viewLifecycleOwner, {
            loading_spinner.visibility = View.GONE
            recipe_list.adapter = RecipeAdapter(it, width, viewModel)
        })

        viewModel.selectedRecipe.observe(viewLifecycleOwner, {
            it?.let {
                val action = MainFragmentDirections.actionMainFragmentToRecipeDetailFragment(it)
                findNavController().navigate(action)
            }
        })
    }
}