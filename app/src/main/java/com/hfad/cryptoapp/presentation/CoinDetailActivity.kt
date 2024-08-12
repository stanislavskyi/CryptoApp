package com.hfad.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hfad.cryptoapp.R
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private lateinit var tvPriceLabel: TextView
    private lateinit var tvMinPrice: TextView
    private lateinit var tvMaxPrice: TextView
    private lateinit var tvLastMarket: TextView
    private lateinit var tvLastUpdate: TextView

    private lateinit var tvFromSymbol: TextView
    private lateinit var tvToSymbol: TextView

    private lateinit var ivLogoCoin: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coin_detail)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }

        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol.toString()).observe(this) {
            tvPriceLabel = findViewById(R.id.tvPriceLabel)
            tvPriceLabel.text = it.price.toString()

            tvMinPrice = findViewById(R.id.tvMinPrice)
            tvMinPrice.text = it.lowDay.toString()

            tvMaxPrice = findViewById(R.id.tvMaxPrice)
            tvMaxPrice.text = it.highDay.toString()

            tvLastMarket = findViewById(R.id.tvLastMarket)
            tvLastMarket.text = it.lastMarket

            tvLastUpdate = findViewById(R.id.tvLastUpdate)
            tvLastUpdate.text = it.lastUpdate

            tvFromSymbol = findViewById(R.id.tvFromSymbol)
            tvFromSymbol.text = it.fromSymbol

            tvToSymbol = findViewById(R.id.tvToSymbol)
            tvToSymbol.text = it.toSymbol

            ivLogoCoin = findViewById(R.id.ivLogoCoin)
            Picasso.get().load(it.imageUrl).into(ivLogoCoin)
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent{
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}