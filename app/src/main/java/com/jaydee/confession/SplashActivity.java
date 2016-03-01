package com.jaydee.confession;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class SplashActivity extends Activity {

    private LinearLayout layoutSplashLogo = null;
    private RelativeLayout layoutBtnSignIn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initLayout();
        startAnimation();
    }

    /**
     * 레이아웃 초기화
     */
    private void initLayout() {
        layoutSplashLogo = (LinearLayout) findViewById(R.id.layoutSplashLogo);
        layoutBtnSignIn = (RelativeLayout) findViewById(R.id.layoutBtnSignIn);

        layoutBtnSignIn.setOnClickListener(mClickListener);
    }

    /**
     * 애니메이션 시작
     */
    private void startAnimation() {
        AlphaAnimation alphaAnim = new AlphaAnimation(0.0f, 1.0f);
        alphaAnim.setDuration(2100);
        alphaAnim.setFillAfter(true);
        alphaAnim.setFillEnabled(true);
        layoutSplashLogo.startAnimation(alphaAnim);

        AlphaAnimation alphaAnimBtn = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimBtn.setDuration(1000);
        alphaAnimBtn.setStartTime(1500);
        alphaAnimBtn.setFillAfter(true);
        alphaAnimBtn.setFillEnabled(true);
        layoutBtnSignIn.startAnimation(alphaAnimBtn);
    }

    /**
     * 뷰 클릭 리스너
     */
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.layoutBtnSignIn) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }
    };
}
