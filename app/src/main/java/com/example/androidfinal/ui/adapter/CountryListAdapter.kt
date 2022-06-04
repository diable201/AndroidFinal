package com.example.androidfinal.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.R
import com.example.androidfinal.data.model.Country

class CountryListAdapter(private val selectListener: CountrySelectListener) :
    ListAdapter<Country, CountryListAdapter.ViewHolder>(DIFF_CONFIG) {
    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<Country>() {
            override fun areContentsTheSame(
                oldItem: Country,
                newItem: Country
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem === newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var country = getItem(position)
        holder.bind(country)
        holder.itemView.setOnClickListener {
            selectListener.getCountry(country)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemCountry: TextView = itemView.findViewById(R.id.country)
        var itemSlug: TextView = itemView.findViewById(R.id.slug)
        var itemISO2: TextView = itemView.findViewById(R.id.iso2)

        fun bind(country: Country) {
            itemCountry.text = country.country
            itemSlug.text = country.slug
            itemISO2.text = country.iso2
        }
    }

    override fun submitList(list: List<Country>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

}

interface CountrySelectListener {
    fun getCountry(country: Country)
}