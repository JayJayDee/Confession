package com.jaydee.confession.manager;

import android.app.Activity;
import android.widget.RelativeLayout;

/**
 * Created by jayjaydee on 15. 6. 25..
 * Scene 애니메이터
 */
public class SceneAnimator {

    public static final String TAG = SceneAnimator.class.getSimpleName();

    private Activity mActivity = null;
    private RelativeLayout mAnimBackground = null;

    public SceneAnimator(Activity activity) {
        mActivity = activity;
    }

    /**
     * 레이아웃 초기화
     */
    private void initLayout() {

    }

    /**
     * Scene 애니메이션 시작
     * @param sceneId
     */
    public void startSceneAnimation(String sceneId) {

        if (sceneId.compareTo("scene1-intro") == 0) {
            startScene1Animation();
        }
    }

    /**
     * Scene1 애니메이션 시작.
     */
    private void startScene1Animation() {

    }
}
