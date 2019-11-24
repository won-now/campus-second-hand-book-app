package demo.com.campussecondbookrecycle.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import demo.com.campussecondbookrecycle.R;

public class TabView extends FrameLayout {
    private ImageView mIvIcon, mIvIconSelected;
    private TextView mTvTitle;

    public static boolean selected;

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.view_tab, this);

        mIvIcon = findViewById(R.id.iv_icon);
        mIvIconSelected = findViewById(R.id.iv_icon_selected);
        mTvTitle = findViewById(R.id.tv_title);
        mIvIconSelected.setVisibility(INVISIBLE);
    }

    public void setContent(int icon, int iconSelected, String title){
        mTvTitle.setText(title);
        mIvIcon.setImageResource(icon);
        mIvIconSelected.setImageResource(iconSelected);
    }

    public void setSelected(boolean selected){
        if(selected){
            mIvIconSelected.setVisibility(VISIBLE);
            mTvTitle.setTextColor(getResources().getColor(R.color.colorMain));
        }else {
            mIvIconSelected.setVisibility(INVISIBLE);
            mTvTitle.setTextColor(getResources().getColor(R.color.colorTab));
        }
    }

}
