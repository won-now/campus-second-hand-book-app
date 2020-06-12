package demo.com.campussecondbookrecycle.Views;

import android.content.Context;
import android.util.AttributeSet;

import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import demo.com.campussecondbookrecycle.R;

public class SpotView extends AppCompatTextView {

    private TextView mTvNumber;

    public SpotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_spot,null);
        mTvNumber = findViewById(R.id.tv_number);
    }

    public void setNumber(int number){
        mTvNumber.setText(number+"");
    }
}
