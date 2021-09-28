package com.resurrection.cryptoassistant.ui.main.market.cryptocurrency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.databinding.CryptoCurrencyItemBinding
import com.squareup.picasso.Picasso

class CryptoCurrencyAdapter(cryptoList:ArrayList<CryptoMarketModel>, coinOnClick: (CryptoMarketModel) -> Unit) : RecyclerView.Adapter<CryptoCurrencyAdapter.Holder>() {

    var cryptoMarketModels = cryptoList

    private var coinOnClick = coinOnClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(CryptoCurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),coinOnClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var cryptoMarketModel = cryptoMarketModels.get(position)
        holder.bind(cryptoMarketModel)
    }

    override fun getItemCount(): Int {
        return cryptoMarketModels.size
    }

    class Holder(private var binding:CryptoCurrencyItemBinding, private val itemOnClick: (CryptoMarketModel) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoMarketModel : CryptoMarketModel){
            binding.txtCoinName.text = cryptoMarketModel.name
            Picasso.get().load(cryptoMarketModel.cryptoImage).into(binding.imgCoin)
            itemView.setOnClickListener {
                itemOnClick(cryptoMarketModel)
            }

        }

    }

}