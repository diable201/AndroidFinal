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
import com.example.androidfinal.data.model.Country
import com.example.androidfinal.databinding.FragmentCountryListBinding
import com.example.androidfinal.ui.adapter.CountryListAdapter
import com.example.androidfinal.ui.adapter.CountrySelectListener
import com.example.androidfinal.view_model.CountryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CountryListFragment : Fragment(), CountrySelectListener {
    companion object {
        const val slugKey = "slug"
    }

    private val viewModel: CountryViewModel by viewModel()
    private lateinit var adapter: CountryListAdapter
    private lateinit var binding: FragmentCountryListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(inflater, container, false)

        configureAdapter()

        viewModel.loadCountryList()

        viewModel.countryList.observe(viewLifecycleOwner) { countryList ->
            binding.recyclerView.visibility = View.VISIBLE
            binding.emptyTextView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            Log.e("data", countryList.toString())
            adapter.submitList(countryList)
            if (countryList.isEmpty()) {
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
        adapter = CountryListAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    override fun getCountry(country: Country) {
        val bundle = bundleOf(slugKey to country.slug)
        findNavController().navigate(R.id.action_countryListFragment_to_countryDetailFragment, bundle)
    }
}