package com.resurrection.cryptoassistant.ui.main.favorite

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel
import com.resurrection.cryptoassistant.databinding.FragmentFavoriteBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.market.cryptocurrency.CryptoCurrencyAdapter
import com.resurrection.cryptoassistant.ui.main.market.details.CryptoDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.Contexts.getApplication

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    private val viewModel: FavoriteViewModel by viewModels()
    private var cryptoIDList :List<FavouriteCryptoModel>? = null
    private var favoriteCryptoModels :ArrayList<CryptoDetailItem> = ArrayList<CryptoDetailItem>()
    private var cryptoDetail: CryptoDetailFragment? = null

    override fun getLayoutRes(): Int {
        return R.layout.fragment_favorite
    }

    fun adapterOnCLick(cmm :CryptoMarketModel){
        val bundle = Bundle()
        bundle.putString("cryptoId", cmm.cryptoId.toString())
        cryptoDetail!!.arguments = bundle

        cryptoDetail!!.show(childFragmentManager, "Bottom Sheet")

    }


    private var cryptoIds : ArrayList<CryptoDetailItem>? = ArrayList()
    override fun init(savedInstanceState: Bundle?) {
        viewModel.getAllFavoriteCrypto()
        cryptoDetail = CryptoDetailFragment(requireContext())

        viewModel.allFavoriteCrypto!!.observe(viewLifecycleOwner, Observer {
            it?.let {
                var adapter = CryptoCurrencyAdapter(it as ArrayList<CryptoMarketModel>,this::adapterOnCLick)
                binding.cryptoFavoriteRecyclerView.adapter = adapter
            }
        })



        viewModel.cryptoDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                favoriteCryptoModels.add(it)
            }

        })

/*
        viewModel.getFavorite()

        viewModel.cryptoDetails.observe(viewLifecycleOwner, Observer {
            cryptoIDList = it
            cryptoIDList!!.forEach {
                viewModel.getCryptoById(it.id)
                println("<<<"+it.id)
            }
        })

        var str = ""
        viewModel.cryptoModel.observe(viewLifecycleOwner, Observer {
            it?.let {
                favoriteCryptoModels.add(it)
                str += it.name.toString()
                println(">>>"+str)





                binding.textDashboard.text = it.name
            }
                binding.textDashboard.text = it.name

        })
*/

/*
        viewModel.getFavoriteListByIds()
        viewModel.cryptoModels.observe(viewLifecycleOwner, Observer {
        it?.let {
            it!!.forEach {
                println(it!!.name)
            }
        }

        })
*/


    }


    fun fireStoreGetFavoriteList(){
        val user = Firebase.auth.currentUser
        if (user != null) {

            val db = FirebaseFirestore.getInstance()
            db.collection(user.uid).get()
                .addOnSuccessListener { documentReference ->
/*
                    println(documentReference.documents.get(0).get("favorite_crypto_id"))
*/
                    documentReference.documents.forEach {
                        viewModel.getCryptoDetailById(it.get("favorite_crypto_id")as String)

                    }


                }
                .addOnFailureListener { e ->

                }

        } else {
            // No user is signed in
        }
    }
}