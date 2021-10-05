package com.example.heartsound.Result_analysis.LineChartData;

import android.graphics.Color;


import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_4;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.example.heartsound.R;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class LineChartData_4 {
    ResultDepartment_4 context;
    LineChart lineChart;

    public LineChartData_4(LineChart lineChart, ResultDepartment_4 context){
        this.context = context;
        this.lineChart = lineChart;
    }

    public void initDataSet(ArrayList<Entry> valuesY) {
        if(valuesY.size()>0){
            for(int i = 0 ;i<valuesY.size();i++){
                final LineDataSet set;
                set = new LineDataSet(valuesY, "心率");
                set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
                set.setColor(context.getResources().getColor(R.color.black));//線的顏色
                set.setCircleColor(context.getResources().getColor(R.color.black));//圓點顏色
                set.setCircleRadius(4);//圓點大小
                set.setDrawCircleHole(false);//圓點為實心(預設空心)
                set.setLineWidth(2);//線寬
                set.setDrawValues(true);//顯示座標點對應Y軸的數字(預設顯示)
                set.setValueTextSize(8);//座標點數字大小

                set.setAxisDependency(YAxis.AxisDependency.LEFT);
//                set.setFillColor(Color.RED);
                set.setFillAlpha(50);
                set.setDrawFilled(true);
//                set.setValueTextColor(Color.BLACK);
                set.setDrawValues(false);
                lineChart.setDragEnabled(true);
                lineChart.setTouchEnabled(true);
//                lineChart.setVisibleXRange(0,100);
                Legend legend = lineChart.getLegend();
                legend.setEnabled(false);//不顯示圖例 (預設顯示)
                Description description = lineChart.getDescription();
                description.setEnabled(false);//不顯示Description Label (預設顯示)


                LineData data = new LineData(set);
                lineChart.setData(data);//一定要放在最後
                lineChart.moveViewToX(data.getEntryCount());
            }
        }else{
            lineChart.setNoDataText("暫時沒有數據");
            lineChart.setNoDataTextColor(Color.BLUE);//文字顏色
        }

        //重新整理顯示
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();//繪製圖表

    }

    public void initX(ArrayList dateList) {
        XAxis xAxis = lineChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X軸標籤顯示位置(預設顯示在上方，分為上方內/外側、下方內/外側及上下同時顯示)
        xAxis.setTextColor(Color.GRAY);//X軸標籤顏色
        xAxis.setTextSize(12);//X軸標籤大小

        xAxis.setLabelCount(6);//X軸標籤個數 xAxis.setLabelCount(dateList.size());
        xAxis.setSpaceMin(0.5f);//折線起點距離左側Y軸距離
        xAxis.setSpaceMax(0.5f);//折線終點距離右側Y軸距離

        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);//不顯示每個座標點對應X軸的線 (預設顯示)

        xAxis.setValueFormatter(new IndexAxisValueFormatter(dateList));
    }

    public void initY(Float min, Float max) {
        YAxis rightAxis = lineChart.getAxisRight();//獲取右側的軸線
        rightAxis.setEnabled(false);//不顯示右側Y軸
        YAxis leftAxis = lineChart.getAxisLeft();//獲取左側的軸線
        leftAxis.setLabelCount(5);//Y軸標籤個數
        leftAxis.setTextColor(Color.GRAY);//Y軸標籤顏色
        leftAxis.setTextSize(12);//Y軸標籤大小

        leftAxis.setAxisMinimum(50);//Y軸標籤最小值
        leftAxis.setAxisMaximum(100);//Y軸標籤最大值
        leftAxis.setValueFormatter(new LineChartData_4.MyYAxisValueFormatter());
    }

    class MyYAxisValueFormatter implements IAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyYAxisValueFormatter() {
            mFormat = new DecimalFormat("###,##");//Y軸數值格式及小數點位數
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mFormat.format(value);
        }
    }
}
