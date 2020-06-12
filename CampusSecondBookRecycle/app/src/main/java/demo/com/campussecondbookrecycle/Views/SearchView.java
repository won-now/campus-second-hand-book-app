package demo.com.campussecondbookrecycle.Views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.activities.SearchBookListActivity;
import demo.com.campussecondbookrecycle.common.Const;

public class SearchView extends LinearLayout implements View.OnClickListener {

    private ImageView mIvSearch,mIvCancle;
    private EditText mEtContent;

    private String mInputHint;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        mInputHint = typedArray.getString(R.styleable.SearchView_input_hint);
        typedArray.recycle();

        inflate(context, R.layout.view_search, this);
        mIvSearch = findViewById(R.id.iv_search);
        mEtContent = findViewById(R.id.et_content);
        mIvCancle = findViewById(R.id.iv_cancle);
        mEtContent.setHint(mInputHint);
        mEtContent.setMaxLines(1);
        mIvSearch.setOnClickListener(this);
        mIvCancle.setOnClickListener(this);
    }

    public String getInputSrc(){
        return mEtContent.getText().toString().trim();
    }

    public void setInput(String keyword){
        mEtContent.setText(keyword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_cancle:
                mEtContent.setText("");
                break;
            case R.id.iv_search:
                Intent intent = new Intent(getContext(), SearchBookListActivity.class);
                intent.putExtra(Const.SEARCH_KEYWORD,getInputSrc());
                getContext().startActivity(intent);
                break;
        }
    }
}
