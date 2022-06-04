package com.example.androidfinal.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.R
import com.example.androidfinal.data.model.CountryDetail

class CountryDetailAdapter(private val selectListener: CountryDetailSelectListener) :
    ListAdapter<CountryDetail, CountryDetailAdapter.ViewHolder>(DIFF_CONFIG) {
    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<CountryDetail>() {
            override fun areContentsTheSame(
                oldItem: CountryDetail,
                newItem: CountryDetail
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: CountryDetail, newItem: CountryDetail): Boolean {
                return oldItem === newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_detail_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var countryDetail = getItem(position)
        holder.bind(countryDetail)
        holder.itemView.setOnClickListener {
            selectListener.getCountryDetail(countryDetail)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemCountry: TextView = itemView.findViewById(R.id.country)
        var itemCountryCode: TextView = itemView.findViewById(R.id.country_code)
        var itemLat: TextView = itemView.findViewById(R.id.lat)
        var itemLon: TextView = itemView.findViewById(R.id.lon)
        var itemConfirmed: TextView = itemView.findViewById(R.id.confirmed)
        var itemDeaths: TextView = itemView.findViewById(R.id.deaths)
        var itemRecovered: TextView = itemView.findViewById(R.id.recovered)
        var itemActive: TextView = itemView.findViewById(R.id.active)
        var itemDate: TextView = itemView.findViewById(R.id.date)

        fun bind(countryDetail: CountryDetail) {
            itemCountry.text = countryDetail.country
            itemCountryCode.text = countryDetail.countryCode
            itemLat.text = countryDetail.lat.toString()
            itemLon.text = countryDetail.lon.toString()
            itemConfirmed.text = countryDetail.confirmed.toString()
            itemDeaths.text = countryDetail.deaths.toString()
            itemRecovered.text = countryDetail.recovered.toString()
            itemActive.text = countryDetail.active.toString()
            itemDate.text = countryDetail.date
        }
    }

    override fun submitList(list: List<CountryDetail>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

}

interface CountryDetailSelectListener {
    fun getCountryDetail(countryDetail: CountryDetail)
}