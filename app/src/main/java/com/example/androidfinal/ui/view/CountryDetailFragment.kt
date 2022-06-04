package com.example.androidfinal.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfinal.R
import com.example.androidfinal.data.model.CountryDetail
import com.example.androidfinal.databinding.FragmentCountryDetailBinding
import com.example.androidfinal.databinding.FragmentCountryListBinding
import com.example.androidfinal.ui.adapter.CountryDetailAdapter
import com.example.androidfinal.ui.adapter.CountryDetailSelectListener
import com.example.androidfinal.view_model.CountryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CountryDetailFragment : Fragment(), CountryDetailSelectListener {
    private val viewModel: CountryViewModel by viewModel()
    private lateinit var adapter: CountryDetailAdapter
    private lateinit var binding: FragmentCountryDetailBinding
    private var slug = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailBinding.inflate(inflater, container, false)

        configureAdapter()

        slug = arguments?.getString(CountryListFragment.slugKey)!!

        Log.e("slug", slug)
        viewModel.loadCountryDetails(slug = slug)

        viewModel.countryDetailList.observe(viewLifecycleOwner) { countryDetailList ->
            binding.recyclerView.visibility = View.VISIBLE
            binding.emptyTextView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            Log.e("data", countryDetailList.toString())
            adapter.submitList(countryDetailList)
            if (countryDetailList.isEmpty()) {
                binding.emptyList.visibility = View.VISIBLE
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            binding.emptyTextView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            Log.e("error loading", errorMessage)
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun configureAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        adapter = CountryDetailAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    override fun getCountryDetail(countryDetail: CountryDetail) {
        TODO("Not yet implemented")
    }
}