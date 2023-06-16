package com.capstone.infentor.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.infentor.R
import com.capstone.infentor.adapter.GlosariumAdapter
import com.capstone.infentor.databinding.ActivityGlosariumBinding
import com.capstone.infentor.response.JobsItem
import com.capstone.infentor.viewmodel.GlosariumViewModel

class GlosariumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlosariumBinding
    private val glosariumViewModel: GlosariumViewModel by viewModels()
    private lateinit var adapter: GlosariumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlosariumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupRecyclerView()
        observeGlosarium()
    }

    private fun setupRecyclerView() {
        adapter = GlosariumAdapter(emptyList())
        binding.rvGlosarium.adapter = adapter
        binding.rvGlosarium.layoutManager = LinearLayoutManager(this)
        binding.rvGlosarium.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

    private fun observeGlosarium() {
        glosariumViewModel.getGlosarium()
        glosariumViewModel.glosarium.observe(this) {
            it.let {
                setData(it)
            }
        }
    }

    private fun setData(data: List<JobsItem>) {
        adapter = GlosariumAdapter(data)
        binding.rvGlosarium.adapter =adapter
    }
}
