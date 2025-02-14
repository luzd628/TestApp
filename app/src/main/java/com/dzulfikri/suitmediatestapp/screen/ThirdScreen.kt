package com.dzulfikri.suitmediatestapp.screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzulfikri.suitmediatestapp.R
import com.dzulfikri.suitmediatestapp.adapter.LoadingStateAdapter
import com.dzulfikri.suitmediatestapp.adapter.UserPagingAdapter
import com.dzulfikri.suitmediatestapp.databinding.ActivityThirdScreenBinding

class ThirdScreen : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding

    private lateinit var adapter: UserPagingAdapter

    private val viewModel by viewModels<UserViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setToolbar()
        getData()
        swipeRefresh()
    }

    fun setToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun getData(){
        adapter = UserPagingAdapter()
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                adapter.retry()
            }
        )
        adapter.addLoadStateListener { loadstate ->
            val isListEmpty = adapter.itemCount == 0 && loadstate.refresh is LoadState.NotLoading
            binding.emptyState.isVisible = isListEmpty || loadstate.refresh is LoadState.Error
            binding.rvUser.isVisible = !isListEmpty
            binding.swipeRefresh.isRefreshing = loadstate.refresh is LoadState.Loading
        }

        viewModel.getUser().observe(this){
            adapter.submitData(lifecycle, it)
        }
    }

    private fun swipeRefresh(){
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            adapter.refresh()

            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefresh.isRefreshing = false
            }, 5000)
        }
    }
}