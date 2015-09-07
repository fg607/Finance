package org.xdty.finance.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.xdty.finance.R;
import org.xdty.finance.model.Market;
import org.xdty.finance.model.MarketPrice;
import org.xdty.finance.model.Price;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import retrofit.RestAdapter;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";

    @ViewById(R.id.chart)
    protected LineChartView mLineChartView;

    @AfterViews
    public void afterViews() {
        listPrice();
    }

    @Background
    public void listPrice() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://market.xdty.org")
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        Market market = restAdapter.create(Market.class);
        List<MarketPrice> prices = market.price("USDCNH:CUR", "1_MONTH");



        List<Line> lines = new ArrayList<>();
        List<PointValue> pointValues = new ArrayList<>();

        int i = 0;
        for (Price price : prices.get(0).getPrice()) {
            Log.d(TAG, "" + price.getValue());
            pointValues.add(new PointValue(i++, price.getValue().floatValue()));
        }

        Line line = new Line(pointValues);
        line.setColor(ChartUtils.COLOR_ORANGE);
        lines.add(line);

        Log.d(TAG, "aaa");

        LineChartData lineChartData = new LineChartData();
        lineChartData.setBaseValue(6.0f);
        lineChartData.setLines(lines);

        setChart(lineChartData);
    }

    @UiThread
    public void setChart(LineChartData lineChartData) {
        mLineChartView.setInteractive(true);
        mLineChartView.setZoomEnabled(true);
        mLineChartView.setZoomType(ZoomType.HORIZONTAL);
        mLineChartView.setLineChartData(lineChartData);
        mLineChartView.setBackgroundColor(ChartUtils.COLOR_GREEN);
    }

}
