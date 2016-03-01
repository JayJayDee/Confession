package com.jaydee.confession.manager;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.jaydee.confession.R;
import com.jaydee.confession.util.ConfessionUtil;

import java.util.HashMap;

/**
 * Created by jayjaydee on 15. 6. 24..
 */
public class ImageSwitcher {

    public interface ImageLoadListener {
        void onLoadStart();
        void onLoadSuccess();
    }

    private Activity mActivity = null;

    private ImageView[] mImgBufArr = null;
    private int mCurrentIdx = 1;
    private Bitmap mBlurredBitmap = null;
    private HashMap<Integer,Bitmap> mBitmapMap = null;
    private HashMap<Integer,Boolean> mBitmapLoaded = null;

    private ImageLoadListener mListener = null;

    public ImageSwitcher(Activity act, ImageLoadListener listener) {
        mActivity = act;
        mListener = listener;
        mBitmapMap = new HashMap<>();
        initLayout();
    }

    private void initLayout() {
        mImgBufArr = new ImageView[2];
        mImgBufArr[0] = (ImageView)mActivity.findViewById(R.id.imgMainBuffer1);
        mImgBufArr[1] = (ImageView)mActivity.findViewById(R.id.imgMainBuffer2);

        int i;
        for (i = 0; i < mImgBufArr.length; i++) {
            mImgBufArr[i].setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 이미지 표시
     */
    public void displayImage(final int imageResId) {
        if (mListener != null) {
            mListener.onLoadStart();
        }

        mDisplayHandler.removeMessages(0);
        Bitmap bit = mBitmapMap.get(imageResId);

        if (bit == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bit = BitmapFactory.decodeResource(mActivity.getResources(), imageResId);
                    Bitmap blurred = ConfessionUtil.createBlurredBitmap(mActivity, bit, 10);

                    mBitmapMap.put(imageResId, blurred);
                    mBlurredBitmap = blurred;
                    mDisplayHandler.sendEmptyMessage(0);
                }
            }).start();
        } else {
            mBlurredBitmap = bit;
            mDisplayHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 디스플레이 핸들러
     */
    private Handler mDisplayHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            final int prevIdx = mCurrentIdx;
            if (mCurrentIdx == mImgBufArr.length-1) {
                mCurrentIdx = 0;
            } else {
                mCurrentIdx++;
            }

            mImgBufArr[mCurrentIdx].setBackground(new BitmapDrawable(mBlurredBitmap));
            mImgBufArr[mCurrentIdx].setAlpha(0.0f);
            mImgBufArr[mCurrentIdx].setVisibility(View.VISIBLE);
            mImgBufArr[prevIdx].setAlpha(1.0f);

            mImgBufArr[mCurrentIdx].animate().alpha(1.0f).setDuration(1000);
            mImgBufArr[prevIdx].animate().alpha(0.0f).setDuration(1000).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {}
                @Override
                public void onAnimationCancel(Animator animator) {}
                @Override
                public void onAnimationRepeat(Animator animator) {}

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (mListener != null) {
                        mListener.onLoadSuccess();
                    }
                }
            });

            return false;
        }
    });
}
