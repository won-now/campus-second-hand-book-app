package demo.com.campussecondbookrecycle.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.common.Const;

public class CollapsibleTextView extends LinearLayout implements View.OnClickListener {

    private TextView mTvContent,mTvOpDesc;
    private LinearLayout mLlOp;
    private ImageView mIvop;
    private boolean isCollapsible = false;
    private boolean folded = true;
    private boolean flag = false;
    private int mMaxHeight =200;

    public CollapsibleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.view_collapsible,this);

        mTvContent = view.findViewById(R.id.tv_content);
        mTvOpDesc = view.findViewById(R.id.tv_op_desc);
        mLlOp = view.findViewById(R.id.ll_op);
        mIvop = view.findViewById(R.id.iv_op);
        mLlOp.setOnClickListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int count = 0;
        count++;
        Log.d("count",count+"");
        if (!flag){
            if (mTvContent.getLineCount() > Const.TEXT_MAX_LINE){
                Log.d("linecount",mTvContent.getLineCount()+"");
                mTvContent.setMaxLines(Const.TEXT_MAX_LINE);
                mLlOp.setVisibility(VISIBLE);
                mIvop.setImageResource(R.drawable.down);
                mTvOpDesc.setText("查看更多");
                isCollapsible = true;
                folded = true;
            }else {
                mLlOp.setVisibility(GONE);
                isCollapsible = false;
            }
        }else {
            if (isCollapsible && folded){
                mTvContent.setMaxLines(Integer.MAX_VALUE);
                mTvOpDesc.setText("收起");
                mIvop.setImageResource(R.drawable.up);
            }else if(isCollapsible && !folded){
                mTvContent.setMaxLines(Const.TEXT_MAX_LINE);
                mTvOpDesc.setText("查看更多");
                mIvop.setImageResource(R.drawable.down);
            }
        }
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        if (!flag){
//            if (mTvContent.getLineCount() > Const.TEXT_MAX_LINE){
//                Log.d("linecount",mTvContent.getLineCount()+"");
//                mTvContent.setMaxLines(Const.TEXT_MAX_LINE);
//                mLlOp.setVisibility(VISIBLE);
//                mIvop.setImageResource(R.drawable.down);
//                mTvOpDesc.setText("查看更多");
//                isCollapsible = true;
//                collapsible = true;
//                flag = true;
//            }else {
//                mLlOp.setVisibility(GONE);
//                isCollapsible = false;
//            }
//        }else {
//            if(isCollapsible && collapsible){
//                mTvContent.setMaxLines(Integer.MAX_VALUE);
//                mLlOp.setVisibility(VISIBLE);
//                mIvop.setImageResource(R.drawable.up);
//                mTvOpDesc.setText("收起");
//                isCollapsible = true;
//                collapsible = false;
//            }else if(isCollapsible && !collapsible){
//                mIvop.setImageResource(R.drawable.down);
//                mTvOpDesc.setText("查看更多");
//                collapsible = true;
//            }
//        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    public void setText(String text){
        mTvContent.setText(text);
        requestLayout();
    }

    @Override
    public void onClick(View v) {
        flag = true;
        if (folded){
            requestLayout();
            folded = false;
        }else {
            requestLayout();
            folded = true;
        }
//        if(isCollapsible && collapsible){
//            Log.d("click","2222");
//            mTvContent.setMaxLines(Integer.MAX_VALUE);
//            mIvop.setImageResource(R.drawable.up);
//            mTvOpDesc.setText("收起");
//            collapsible = false;
//        }else if(isCollapsible && !collapsible){
//            Log.d("click","13331");
//            mTvContent.setMaxLines(Const.TEXT_MAX_LINE);
//            mIvop.setImageResource(R.drawable.down);
//            collapsible = true;
//        }
//        post(new InnerRunnable());
    }

//    class InnerRunnable implements Runnable{
//        @Override
//        public void run() {
//            Log.d("click","111111");
//            if(isCollapsible && collapsible){
//                Log.d("click","2222");
//                mTvContent.setMaxLines(Integer.MAX_VALUE);
//                mIvop.setImageResource(R.drawable.up);
//                mTvOpDesc.setText("收起");
//                collapsible = false;
//                return;
//            }
//            if(isCollapsible && !collapsible){
//                Log.d("click","13331");
//                mTvContent.setMaxLines(Const.TEXT_MAX_LINE);
//                mIvop.setImageResource(R.drawable.down);
//                collapsible = true;
//            }
//        }
//    }
}
