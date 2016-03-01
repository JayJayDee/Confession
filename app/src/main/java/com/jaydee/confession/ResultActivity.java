package com.jaydee.confession;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.jaydee.confession.adapter.ScenePagerAdapter;
import com.jaydee.confession.vo.SceneVO;


public class ResultActivity extends Activity {

    private RelativeLayout layoutResultSuccess = null;
    private RelativeLayout layoutResultDelay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initLayout();
        init();
    }

    private void initLayout() {
        layoutResultSuccess = (RelativeLayout)findViewById(R.id.layoutResultSuccess);
        layoutResultDelay = (RelativeLayout)findViewById(R.id.layoutResultDelay);
    }

    /**
     * 데이터 초기화
     */
    private void init() {
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",0);

        if (type == ScenePagerAdapter.FINAL_ACCEPT) {
            layoutResultSuccess.setVisibility(View.VISIBLE);
        }

        else if (type == ScenePagerAdapter.FINAL_DELAY) {
            layoutResultDelay.setVisibility(View.VISIBLE);
        }
    }
}
