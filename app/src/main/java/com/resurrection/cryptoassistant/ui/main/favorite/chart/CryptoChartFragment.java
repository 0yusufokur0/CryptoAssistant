package com.resurrection.cryptoassistant.ui.main.favorite.chart;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.resurrection.cryptoassistant.R;
import com.resurrection.cryptoassistant.databinding.FragmentCryptoChartBinding;
import com.resurrection.cryptoassistant.ui.base.BaseFragment;
import com.resurrection.cryptoassistant.ui.main.favorite.favoritedetail.FavoriteDetailFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Provides;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CryptoChartFragment extends BaseFragment<FragmentCryptoChartBinding> {

    private CryptoChartViewModel viewModel;/* = ViewModelProviders.of(getParentFragment().requireActivity()).get(CryptoChartViewModel.class);*/

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

        String data = getArguments().getString("cryptoId");

        viewModel.getCryptoChartById(data);

        GraphView graph = requireView().findViewById(R.id.graph);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

/*        viewModel.getCryptoChart().observe(getViewLifecycleOwner(), cryptoChartModelList-> {
            DataPoint[] dataPoints = new DataPoint[cryptoChartModelList.getData().getPrices().size()];
            for (List<Double> d:cryptoChartModelList.getData().getPrices()) {
                System.out.println(d.get(0));

            }

            for (int i = 0; i < cryptoChartModelList.getData().getPrices().size(); i++) {
                List<Double> doubleList = cryptoChartModelList.getData().getPrices().get(i);
                dataPoints[i] = new DataPoint(doubleList.get(0),doubleList.get(1));
            }

            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
            graph.addSeries(series);

        });*/

    }

}

/*        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        String[] dates = new String[]{"1","2","3","4"};
        staticLabelsFormatter.setHorizontalLabels(dates);

        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(dates.length-1);*/

/*        GridLabelRenderer renderer = graph.getGridLabelRenderer();
        renderer.setHorizontalLabelsAngle(5);*/
/*
        graph.getGraphViewStyle().setNumHorizontalLabels(5);
*/

/*               graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        //Підписи осей
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("X Axis Title");
        gridLabel.setVerticalAxisTitle("Y Axis Title");*/
/*        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"old", "middle", "new"});
        staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);*/
/*
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
*/




/*
            System.out.println(cryptoChartModelList.toString());
*/
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