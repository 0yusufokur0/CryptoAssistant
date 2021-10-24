package com.resurrection.cryptoassistant.ui.main.favorite

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.resurrection.cryptoassistant.BR
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel
import com.resurrection.cryptoassistant.databinding.CryptoCurrencyItemBinding
import com.resurrection.cryptoassistant.databinding.FragmentFavoriteBinding
import com.resurrection.cryptoassistant.ui.base.BaseAdapter
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.favorite.favoritedetail.FavoriteDetailFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    private val viewModel: FavoriteViewModel by viewModels()
    private var cryptoIDList: List<FavouriteCryptoModel>? = null
    private var favoriteCryptoModels = ArrayList<CryptoMarketModel>()
    private var cryptoDetail: FavoriteDetailFragment? = null

    override fun getLayoutRes(): Int = R.layout.fragment_favorite

    override fun init(savedInstanceState: Bundle?) {
        viewModel.getAllFavoriteCrypto()
        cryptoDetail = FavoriteDetailFragment()
        setViewModelObserve()

    }
    
    private fun setViewModelObserve(){
        viewModel.allFavoriteCrypto.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.data?.size != 0) {
                    var adapter = BaseAdapter<CryptoMarketModel, CryptoCurrencyItemBinding>(
                        R.layout.crypto_currency_item,
                        it as ArrayList<CryptoMarketModel>,
                        BR.crypto,
                        this::adapterOnCLick
                    )
                    binding.cryptoFavoriteRecyclerView.adapter = adapter
                } else { getFromFirebase() }
            }
        })

        viewModel.cryptoDetail.observe(viewLifecycleOwner, Observer {
            it?.data?.let {
                var cmm = CryptoMarketModel(
                    it.id,
                    it.marketData.currentPrice.usd,
                    it.marketData.highestPrice24h.usd,
                    it.image.large, it.lastUpdated,
                    it.marketData.lowestPrice24h.usd,
                    it.name,
                    it.marketData.priceChange24h,
                    it.marketData.priceChangePercentage24h,
                    it.symbol
                )
                favoriteCryptoModels.add(cmm)

                var adapter = BaseAdapter<CryptoMarketModel, CryptoCurrencyItemBinding>(
                    R.layout.crypto_currency_item,
                    favoriteCryptoModels,
                    BR.crypto,
                    this::adapterOnCLick
                )
                binding.cryptoFavoriteRecyclerView.adapter = adapter
            }
        })
    }

    private fun adapterOnCLick(cmm: CryptoMarketModel) {
        val bundle = Bundle()
        bundle.putString("cryptoId", cmm.cryptoId.toString())
        cryptoDetail!!.arguments = bundle
        cryptoDetail!!.show(childFragmentManager, "Bottom Sheet")
    }
    private fun getFromFirebase() {
        val user = Firebase.auth.currentUser
        if (user != null) {
            val database = Firebase.database
            
                database.getReference(user.uid).child("favorite").get().addOnSuccessListener {
                    println(it)
                    it.children.forEach {
                        var fcm: FavouriteCryptoModel =
                            it.getValue(FavouriteCryptoModel::class.java)!!
                        viewModel.getCryptoDetailById(fcm.id)
                    }
                }.addOnFailureListener { }
            
            val db = FirebaseFirestore.getInstance()
            db.collection(user.uid).get()
                .addOnSuccessListener { documentReference ->
                    documentReference.documents.forEach {
                        viewModel.getCryptoDetailById(it.get("id") as String)
                    }
                }
                .addOnFailureListener { e -> }
        } else { /*No user is signed in*/ }

    }
}