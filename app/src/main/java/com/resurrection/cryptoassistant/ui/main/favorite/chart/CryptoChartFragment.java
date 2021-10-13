package com.resurrection.cryptoassistant.ui.main.favorite.chart;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.resurrection.cryptoassistant.R;
import com.resurrection.cryptoassistant.databinding.FragmentCryptoChartBinding;
import com.resurrection.cryptoassistant.ui.base.BaseFragment;
import com.resurrection.cryptoassistant.ui.main.favorite.favoritedetail.FavoriteDetailFragment;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CryptoChartFragment extends BaseFragment<FragmentCryptoChartBinding> {

/*
    private CryptoChartViewModel viewModel = ViewModelProviders.of(this).get(CryptoChartViewModel.class);
*/

    private CryptoChartViewModel viewModel;/* = ViewModelProviders.of(getParentFragment().requireActivity()).get(CryptoChartViewModel.class);*/

    /*
        viewModel = ViewModelProviders(this).get(CryptoChartViewModel::clas);
    */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_crypto_chart;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(CryptoChartViewModel.class);

  /*              val data = arguments?.getString("cryptoId")
        val bundle = Bundle()
        bundle.putString("cryptoId", cmm.cryptoId.toString())
        cryptoDetail!!.arguments = bundle*/
        String data = getArguments().getString("cryptoId");

/*
        viewModel.getCryptoChartById(data);
*/

        GraphView graph = requireView().findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        viewModel.getCryptoChart().observe(getViewLifecycleOwner(), cryptoChartModelList-> {
/*            LineGraphSeries<DataPoint> mySeries; *//*= new LineGraphSeries<DataPoint>(new DataPoint[]{
            });*//*

            DataPoint[] dataPoints = new DataPoint[1];
            dataPoints[0] = new DataPoint(1,2);


            mySeries = new LineGraphSeries<DataPoint>(dataPoints);

        graph.addSeries(mySeries);
*//*
            mySeries.appendData(new DataPoint(1,2),true,2,true);
*/

 /*                   LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);*/
        });


    }
    public Context requirceContext() {
        Context context = getContext();
        if (context == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to a context.");
        }
        return context;
    }


}