package com.resurrection.cryptoassistant.ui.main.favorite

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel
import com.resurrection.cryptoassistant.databinding.FragmentFavoriteBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.market.cryptocurrency.CryptoCurrencyAdapter
import com.resurrection.cryptoassistant.ui.main.market.details.CryptoDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    private val viewModel: FavoriteViewModel by viewModels()
    private var cryptoIDList: List<FavouriteCryptoModel>? = null
    private var favoriteCryptoModels = ArrayList<CryptoMarketModel>()
    private var cryptoDetail: CryptoDetailFragment? = null

    override fun getLayoutRes(): Int {
        return R.layout.fragment_favorite
    }

    fun adapterOnCLick(cmm: CryptoMarketModel) {
        val bundle = Bundle()
        bundle.putString("cryptoId", cmm.cryptoId.toString())
        cryptoDetail!!.arguments = bundle
        cryptoDetail!!.show(childFragmentManager, "Bottom Sheet")
    }

    override fun init(savedInstanceState: Bundle?) {
        viewModel.getAllFavoriteCrypto()
        cryptoDetail = CryptoDetailFragment(requireContext())


        viewModel.allFavoriteCrypto.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.size != 0) {
                    var adapter = CryptoCurrencyAdapter(
                        it as ArrayList<CryptoMarketModel>,
                        this::adapterOnCLick
                    )
                    binding.cryptoFavoriteRecyclerView.adapter = adapter
                } else {
                    println("veri yok firebase den çekicez")
                    getfireStoreGetFavoriteList()
                }
            }
        })

        viewModel.cryptoDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
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
                var adapter = CryptoCurrencyAdapter(favoriteCryptoModels, this::adapterOnCLick)
                binding.cryptoFavoriteRecyclerView.adapter = adapter
            }
        })
    }

    fun getfireStoreGetFavoriteList() {
        val user = Firebase.auth.currentUser
        if (user != null) {
            var favList= ArrayList<FavouriteCryptoModel>()
            val database = Firebase.database
            val myRef = database.getReference(user.uid).child("favorite").get().addOnSuccessListener {
                println(it)
                it.children.forEach {
                    println("--------------------")
                    var fcm :FavouriteCryptoModel = it.getValue(FavouriteCryptoModel::class.java)!!
                    println(fcm.id+" "+fcm.currentPrice)
                    viewModel.getCryptoDetailById(fcm.id)
                }
            }.addOnFailureListener {

            }


            val db = FirebaseFirestore.getInstance()
            db.collection(user.uid).get()
                .addOnSuccessListener { documentReference ->
                    documentReference.documents.forEach {
                        viewModel.getCryptoDetailById(it.get("id") as String)
                    }
                }
                .addOnFailureListener { e -> }
        } else {
            // No user is signed in
        }



/*        val database = Firebase.database
        val myRef = database.getReference(user.uid).child("favorite")
            .child(checkId).get().addOnSuccessListener {
                if (it.value == null){
                    isFavorite.value = false
                    println(it)
                }else{
                    isFavorite.value = true
                }

                println(it)
            }.addOnFailureListener {
                isFavorite.value = false
            }*/



    }
}