package demo.com.campussecondbookrecycle.Views;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import demo.com.campussecondbookrecycle.R;

public class NavBarView extends FrameLayout{

    private TextView mTvTitle;
    private ImageView mIvBack;
    private View mView;

    private String title;
    private boolean isShow;

    public NavBarView(Context context) {
        super(context);
        init(context,null);
    }

    public NavBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        if(attrs == null) return;
//          获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes
                (attrs, R.styleable.navBar);
        title = typedArray.getString(R.styleable.navBar_page_title);
        isShow = typedArray.getBoolean(R.styleable.navBar_show_back,false);
        typedArray.recycle();

        //绑定layout布局
        mView = LayoutInflater.from(context).inflate(R.layout.view_nav_bar,
                this,false);
        mIvBack = mView.findViewById(R.id.iv_back);
        mTvTitle = mView.findViewById(R.id.tv_title);
//          布局关联属性
        mIvBack.setVisibility(isShow ? VISIBLE : INVISIBLE);
        mTvTitle.setText(title);

        mIvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity)context;
                activity.finish();
            }
        });
        addView(mView);
    }

}
