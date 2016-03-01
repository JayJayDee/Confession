package com.jaydee.confession.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jaydee.confession.R;

import java.util.HashMap;

/**
 * Created by jaydee on 2015. 6. 11..
 * 폰트를 Custom XML로 정의 가능한 TextView
 */
public class FontTextView extends TextView {

    public static final String FONT_PATH = "fonts/";
    public static final String DEFAULT_TYPE_FACE = "NotoSans"; //기본 typeface이름

    private Context mContext = null;
    private String mTypefaceName = null;

    private static HashMap<String, String> mFontPathMap = null;
    private static HashMap<String, Typeface> mFontMap = null;

    public FontTextView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FontTextView(Context context, AttributeSet attr) {
        super(context, attr);
        mContext = context;
        init();

        TypedArray array = context.obtainStyledAttributes(attr, R.styleable.FontTextView);
        String typeFaceExpr = array.getString(R.styleable.FontTextView_typeFace);

        if (typeFaceExpr == null) {
            typeFaceExpr = DEFAULT_TYPE_FACE;
        }

        mTypefaceName = typeFaceExpr;
        this.setTypefaceName(mTypefaceName);
    }

    /**
     * FontTextView 초기화
     * Font이름 - Font파일 명으로 이루어진 Map을 만들어 준다.
     */
    private void init() {
        if (mFontMap == null || mFontPathMap == null) {
            // 폰트 맵, 폰트 이름-폰트파일명 맵을 초기화.
            mFontMap = new HashMap<> ();
            mFontPathMap = new HashMap<> ();
            mFontPathMap.put("NotoSans", "notosanskr_regular.otf");
            mFontPathMap.put("NotoSansMedium", "notosanskr_medium.otf");
        }
        this.setIncludeFontPadding(false);
    }

    /**
     * 설정한 이름에 맞는 Typeface instance를 가져옴
     */
    private Typeface getTypefaceInstance(String typeFaceName) {
        if (mFontPathMap.containsKey(typeFaceName) == false) {
            return null;
        }

        Typeface typeFace = mFontMap.get(typeFaceName);
        if (typeFace == null) {
            typeFace = Typeface.createFromAsset(mContext.getAssets(),FONT_PATH+mFontPathMap.get(typeFaceName));
            mFontMap.put(typeFaceName,typeFace);
        }
        return typeFace;
    }

    /**
     * typeFace 설정
     * @param typeFaceName 폰트 이름
     */
    public void setTypefaceName (String typeFaceName) {
        Typeface typeFaceInst = this.getTypefaceInstance(typeFaceName);
        if (typeFaceInst != null) {
            this.setTypeface(typeFaceInst);
            this.forceLayout();
        }
    }
}
