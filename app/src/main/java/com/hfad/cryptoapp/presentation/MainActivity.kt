package com.hfad.cryptoapp.presentation


import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.hfad.cryptoapp.R
import com.hfad.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.hfad.cryptoapp.data.network.model.CoinInfoDto
import com.hfad.cryptoapp.databinding.ActivityMainBinding
import com.hfad.cryptoapp.domain.CoinInfo
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfoDto: CoinInfo) {
                if (isOnePaneMode()){
                    launchDetailActivity(coinInfoDto.fromSymbol)
                }else{
                    launchDetailFragment(coinInfoDto.fromSymbol)
                }
            }
        }

        binding.recyclerViewCoinPriceList.adapter = adapter
        binding.recyclerViewCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
        //viewModel.loadData() init in CoinViewModel

        viewModel.coinInfoList.observe(this){
            adapter.submitList(it)
//            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
        }

//        viewModel.getDetailInfo("BTC").observe(this, Observer {
//            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
//        })
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null


    private fun launchDetailActivity(fromSymbol: String){
        val intent = CoinDetailActivity.newIntent(
            this@MainActivity,
            fromSymbol
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null).commit()
    }
}