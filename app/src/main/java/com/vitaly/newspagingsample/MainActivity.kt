package com.vitaly.newspagingsample


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.vitaly.newspagingsample.adapter.NewsListAdapter
import com.vitaly.newspagingsample.entity.State
import com.vitaly.newspagingsample.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val viewModel: NewsListViewModel by lazy {NewsListViewModel()}
    private lateinit var newsListAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        initState()
    }

    private fun initRecycler() {
        newsListAdapter = NewsListAdapter { viewModel.retry() }
        recycler_view.adapter = newsListAdapter
        viewModel.newsList.observe(this, {
            newsListAdapter.submitList(it)
        })
    }

    private fun initState() {
        main_txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            main_progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            main_txt_error.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                newsListAdapter.setState(state ?: State.DONE)
            }
        })
    }
}