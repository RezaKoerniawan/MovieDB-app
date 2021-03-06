package com.example.moviedb.ui.screen.popularmovie

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentLoadmoreRefreshBinding
import com.example.moviedb.ui.base.BaseListAdapter
import com.example.moviedb.ui.base.getNavController
import com.example.moviedb.ui.base.loadmorerefresh.BaseLoadMoreRefreshFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMovieFragment :
    BaseLoadMoreRefreshFragment<FragmentLoadmoreRefreshBinding, PopularMovieViewModel, Movie>() {

    companion object {

        const val TYPE = "TYPE"

        fun newInstance(type: Int) = PopularMovieFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE, type)
            }
        }
    }

    override val viewModel: PopularMovieViewModel by viewModels()
    override val listAdapter: BaseListAdapter<Movie, out ViewDataBinding> by lazy {
        PopularMovieAdapter(
            itemClickListener = { toMovieDetail(it) }
        )
    }
    override val swipeRefreshLayout: SwipeRefreshLayout
        get() = viewBinding.refreshLayout
    override val recyclerView: RecyclerView
        get() = viewBinding.recyclerView

    override fun getLayoutManager(): RecyclerView.LayoutManager = GridLayoutManager(context, 2)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.container.setBackgroundColor(Color.WHITE)

        viewModel.apply {
            mode.value = arguments?.getInt(TYPE)
        }
    }

    private fun toMovieDetail(movie: Movie) {
        getNavController()?.navigate(
            PopularMovieFragmentDirections.toGraphMovieDetail(movie)
        )
    }
}
