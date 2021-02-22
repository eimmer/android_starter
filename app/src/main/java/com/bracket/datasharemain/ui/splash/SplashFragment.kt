package com.bracket.datasharemain.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.bracket.datasharemain.R

class SplashFragment : Fragment() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            SplashViewModel.SplashViewModelFactory()
        ).get(SplashViewModel::class.java)

        viewModel.liveLoadingState.observeForever {

            when (it) {
                NextScreen.HomeScreen -> {
                    val direction = SplashFragmentDirections.actionOpenMainFragment()
                    findNavController(this).navigate(direction)
                }
                else -> Log.e(this.tag, "Unknown: $it", IllegalStateException("Unknown view"))
            }

        }
    }
}