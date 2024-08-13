package com.hfad.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.hfad.cryptoapp.R
import com.hfad.cryptoapp.databinding.ItemCoinInfoBinding
import com.hfad.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter(
    private val context: Context
) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = price.toString()
                tvLastUpdate.text =
                    String.format(lastUpdateTemplate, lastUpdate)
                Picasso.get().load(imageUrl).into(ivLogoCoin)

                holder.itemView.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
//                root.setOnClickListener {
//                    onCoinClickListener?.onCoinClick(this)
//                } ВОЗНИКАЕТ ОШИБКА
            }
        }
//        holder.itemView.setOnClickListener{
//            onCoinClickListener?.onCoinClick(coin)
//        }
    }


    interface OnCoinClickListener {
        fun onCoinClick(coinInfoDto: CoinInfo)
    }
}