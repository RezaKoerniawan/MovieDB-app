package com.example.moviedb.ui.screen.oldmain

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviedb.R
import com.example.moviedb.data.constants.MovieListType
import com.example.moviedb.databinding.FragmentOldMainBinding
import com.example.moviedb.ui.base.BaseFragment
import com.example.moviedb.ui.screen.favoritemovie.FavoriteMovieFragment
import com.example.moviedb.ui.screen.popularmovie.PopularMovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OldMainFragment : BaseFragment<FragmentOldMainBinding, OldMainViewModel>() {

    companion object {
        private const val FRAGMENT_TAG = "FRAGMENT_TAG"
    }

    override val layoutId: Int = R.layout.fragment_old_main

    override val viewModel: OldMainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTopNavigation()
    }

    private fun initTopNavigation() {
        viewBinding.layoutTabPopular.layoutItemTopNav.setOnClickListener {
            onClickBottomNavigationItem(Tab.POPULAR.position)
        }
        viewBinding.layoutTabRated.layoutItemTopNav.setOnClickListener {
            onClickBottomNavigationItem(Tab.TOP_RATED.position)
        }
        viewBinding.layoutTabFavorite.layoutItemTopNav.setOnClickListener {
            onClickBottomNavigationItem(Tab.FAVORITE.position)
        }
        onClickBottomNavigationItem(Tab.POPULAR.position)
    }

    private fun onClickBottomNavigationItem(position: Int): Boolean {
        val currentTag = getTabFragmentTag(viewModel.currentTab.value ?: Tab.POPULAR.position)
        val newTag = getTabFragmentTag(position)

        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val currentFragment = fragmentManager.findFragmentByTag(currentTag)
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment)
        }

        var newFragment: Fragment? = fragmentManager.findFragmentByTag(newTag)
        if (newFragment == null) {
            newFragment = newFragmentInstance(position)
            if (newFragment.isAdded) {
                fragmentTransaction.show(newFragment)
            } else {
                fragmentTransaction.add(R.id.frame_layout, newFragment, newTag)
            }
        } else {
            fragmentTransaction.show(newFragment)
        }

        viewModel.currentTab.value = position
        fragmentTransaction.commit()

        return true
    }

    private fun getTabFragmentTag(position: Int) = FRAGMENT_TAG + position

    private fun newFragmentInstance(position: Int): Fragment {
        return when (position) {
            Tab.POPULAR.position -> PopularMovieFragment.newInstance(
                MovieListType.POPULAR.type
            )
            Tab.TOP_RATED.position -> PopularMovieFragment.newInstance(
                MovieListType.TOP_RATED.type
            )
            Tab.FAVORITE.position -> FavoriteMovieFragment.newInstance(
                MovieListType.FAVORITE.type
            )
            else -> Fragment()
        }
    }

}
