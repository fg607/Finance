package org.xdty.finance.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.xdty.finance.R;
import org.xdty.finance.model.Config;
import org.xdty.finance.model.Market;
import org.xdty.finance.model.MarketID;
import org.xdty.finance.model.MarketPrice;
import org.xdty.finance.model.MarketTime;
import org.xdty.finance.model.Price;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import retrofit.RestAdapter;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";

    @ViewById(R.id.usdcnh)
    LinearLayout mUsdcnhLayout;

    @ViewById(R.id.usdcny)
    LinearLayout mUsdcnyLayout;

    @ViewById(R.id.eurcny)
    LinearLayout mEurcnyLayout;

    @ViewById(R.id.shcomp)
    LinearLayout mShcompLayout;

    long mLastToast = 0;

    @AfterViews
    public void afterViews() {

        setChartTitle(mUsdcnhLayout, R.string.usdcnh);
        setChartTitle(mUsdcnyLayout, R.string.usdcny);
        setChartTitle(mEurcnyLayout, R.string.eurcny);
        setChartTitle(mShcompLayout, R.string.shcomp);

        setChartSpinner(mUsdcnhLayout);
        setChartSpinner(mUsdcnyLayout);
        setChartSpinner(mEurcnyLayout);
        setChartSpinner(mShcompLayout);

        mUsdcnhLayout.setTag(MarketID.USDCNH);
        mUsdcnyLayout.setTag(MarketID.USDCNY);
        mEurcnyLayout.setTag(MarketID.EURCNY);
        mShcompLayout.setTag(MarketID.SHCOMP);

        getPriceData(mUsdcnhLayout, MarketTime.MONTH);
        getPriceData(mUsdcnyLayout, MarketTime.MONTH);
        getPriceData(mEurcnyLayout, MarketTime.MONTH);
        getPriceData(mShcompLayout, MarketTime.MONTH);

    }

    @Background
    public void getPriceData(LinearLayout layout, MarketTime time) {

        MarketID id = (MarketID) layout.getTag();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Config.PROVIDER_URI)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        Market market = restAdapter.create(Market.class);
        List<MarketPrice> prices = null;

        try {
            prices = market.price(id.toString(), time.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (prices == null) {
            if (System.currentTimeMillis() - mLastToast > 30 * 1000) {
                makeToast(getString(R.string.no_network));
                mLastToast = System.currentTimeMillis();
            }
            return;
        }

        List<Line> lines = new ArrayList<>();
        List<PointValue> pointValues = new ArrayList<>();

        int i = 0;
        int count = 0;
        for (Price price : prices.get(0).getPrice()) {

            // There are too much points.
            if (time != MarketTime.MONTH && i++ % 5 != 0 && i < prices.get(0).getPrice().size()) {
                continue;
            }

            Log.d(TAG, "" + price.getValue());
            pointValues.add(new PointValue(count++, price.getValue().floatValue()));
        }

        Line line = new Line(pointValues);
        line.setColor(ChartUtils.COLOR_RED);
        lines.add(line);

        LineChartData lineChartData = new LineChartData();
        lineChartData.setBaseValue(6.0f);
        lineChartData.setLines(lines);

        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);

        axisX.setName("");
        axisY.setName(" ");

        lineChartData.setAxisXBottom(axisX);
        lineChartData.setAxisYLeft(axisY);

        setChart(layout, lineChartData);
    }

    @UiThread
    public void setChart(LinearLayout chartLayout, LineChartData lineChartData) {

        LineChartView chartView = (LineChartView) chartLayout.findViewById(R.id.chart);
        chartView.setInteractive(true);
        chartView.setZoomEnabled(true);
        chartView.setZoomType(ZoomType.HORIZONTAL);
        chartView.setBackgroundColor(ChartUtils.COLOR_BLUE);
        chartView.setLineChartData(lineChartData);
    }

    @UiThread
    public void setChartTitle(LinearLayout layout, int id) {
        TextView title = (TextView) layout.findViewById(R.id.title);
        title.setText(id);
    }

    @UiThread
    public void setChartSpinner(final LinearLayout layout) {
        Spinner spinner = (Spinner) layout.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.market_times, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getPriceData(layout, MarketTime.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @UiThread
    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
