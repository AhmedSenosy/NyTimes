package com.senosy.nytimes.ui.home

import android.icu.lang.UCharacter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senosy.nytimes.R
import com.senosy.nytimes.databinding.FragmentHomeBinding
import com.senosy.nytimes.repository.ArticleRepository
import com.senosy.nytimes.ui.ArticleViewModel


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private val viewModel : ArticleViewModel by activityViewModels {
        ArticleViewModel.Factory(
            ArticleRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.rvArticles.adapter = ArticleAdapter()
        binding.rvArticles.layoutManager = LinearLayoutManager(context)
        binding.rvArticles.addItemDecoration(DividerItemDecoration(context,RecyclerView.VERTICAL))

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.getArticles(1)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}