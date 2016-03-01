package com.jaydee.confession.vo;

/**
 * Created by jayjaydee on 15. 6. 23..
 */
public class SceneVO {

    public static final String TAG = SceneVO.class.getSimpleName();

    public static final int TYPE_FINAL = 0;
    public static final int TYPE_NORMAL = 1;

    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_RIGHT = 2;

    private String mId = null;
    private int mType = TYPE_NORMAL;
    private int mImageResId = -1;
    private int mHorizontalAlign = ALIGN_CENTER;
    private int mFontSize = 15;
    private String mText = null;
    private String mSubText = null;
    private String mFontFace = "NotoSans";

    public String getId() {
        return mId;
    }
    public void setId(String id) {
        mId = id;
    }

    public int getType() {
        return mType;
    }
    public void setType(int type) {
        mType = type;
    }

    public String getText() {
        return mText;
    }
    public void setText(String text) {
        mText = text;
    }

    public String getSubText() {
        return mSubText;
    }
    public void setSubText(String subText) {
        mSubText = subText;
    }

    public int getFontSize() {
        return mFontSize;
    }
    public void setFontSize(int fontSize) {
        mFontSize = fontSize;
    }

    public String getFontFace() {
        return mFontFace;
    }
    public void setFontFace(String fontFace) {
        mFontFace = fontFace;
    }

    public int getHorizontalAlign() {
        return mHorizontalAlign;
    }
    public void setHorizontalAlign(int align) {
        mHorizontalAlign = align;
    }

    public int getImageResId() {
        return mImageResId;
    }
    public void setImageResId(int resId) {
        mImageResId = resId;
    }
}
