package demo.com.campussecondbookrecycle.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.com.campussecondbookrecycle.R;

public class SearchView extends LinearLayout {

    private ImageView mIvSearch;
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
        mEtContent.setHint(mInputHint);
    }


}
