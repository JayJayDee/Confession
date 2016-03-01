package com.jaydee.confession;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jaydee.confession.adapter.ScenePagerAdapter;
import com.jaydee.confession.manager.ImageSwitcher;
import com.jaydee.confession.vo.SceneVO;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;


public class MainActivity extends FragmentActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private VerticalViewPager viewPagerMain = null;
    private RelativeLayout layoutMainProg = null;

    private ScenePagerAdapter mFragAdapter = null;
    private ImageSwitcher mSwitcher = null;
    private List<SceneVO> mSceneList = null;
    private MediaPlayer mMediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        init();
    }

    @Override
    protected void onPause() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying() == true) {
                mMediaPlayer.pause();
            }
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAndPlayMusic();
    }

    /**
     * 레이아웃 초기화
     */
    private void initLayout() {
        viewPagerMain = (VerticalViewPager)findViewById(R.id.viewPagerMain);
        layoutMainProg = (RelativeLayout)findViewById(R.id.layoutMainProg);
        layoutMainProg.setOnTouchListener(mTouchListener);
    }

    /**
     * 데이터 초기화
     */
    private void init() {
        initSceneData();
        mFragAdapter = new ScenePagerAdapter(this, mSceneList, mFinalInteractionListener);
        viewPagerMain.setAdapter(mFragAdapter);
        viewPagerMain.setOnPageChangeListener(mPageChangeListener);
        mSwitcher = new ImageSwitcher(this, mImageLoadListener);

        mSwitcher.displayImage(mSceneList.get(0).getImageResId());
    }

    /**
     * 현재 음악을 플레이할지를 체크하고 재생
     */
    private void checkAndPlayMusic() {
        if (viewPagerMain.getCurrentItem() < 11) {
            return;
        }
        if (mMediaPlayer == null ) {
            mMediaPlayer = new MediaPlayer();
            try {
                AssetFileDescriptor afd = getAssets().openFd("fall.mp3");
                mMediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                mMediaPlayer.setLooping(true);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            if (mMediaPlayer.isPlaying() == false) {
                mMediaPlayer.start();
            }
        }
    }

    /**
     * Scene 데이터 초기화
     */
    private void initSceneData() {
        mSceneList = new ArrayList<>();

        SceneVO scene = new SceneVO();
        scene.setText(getString(R.string.scene1_text));
        scene.setSubText(getString(R.string.scene1_text_sub));
        scene.setImageResId(R.drawable.bg1);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene2_text));
        scene.setImageResId(R.drawable.bg2);
        scene.setFontSize(27);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene3_text));
        scene.setSubText(getString(R.string.scene3_text_sub));
        scene.setImageResId(R.drawable.bg3);
        scene.setFontSize(15);
        scene.setHorizontalAlign(SceneVO.ALIGN_RIGHT);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene4_text));
        scene.setImageResId(R.drawable.bg2);
        scene.setFontSize(22);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene5_text));
        scene.setImageResId(R.drawable.bg4);
        scene.setFontSize(22);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene6_text));
        scene.setImageResId(R.drawable.bg5);
        scene.setFontSize(18);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene7_text));
        scene.setSubText(getString(R.string.scene7_text_sub));
        scene.setImageResId(R.drawable.scene_start);
        scene.setFontSize(29);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene8_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_RIGHT);
        scene.setImageResId(R.drawable.scene_daegu);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene9_text));
        scene.setSubText(getString(R.string.scene9_text_sub));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.bg4);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene10_text));
        scene.setFontSize(22);
        scene.setImageResId(R.drawable.bg6);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene11_text));
        scene.setSubText(getString(R.string.scene11_text_sub));
        scene.setFontSize(17);
        scene.setImageResId(R.drawable.scene_6month);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene12_text));
        scene.setFontSize(23);
        scene.setImageResId(R.drawable.scene_rain);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene13_text));
        scene.setFontSize(23);
        scene.setHorizontalAlign(SceneVO.ALIGN_RIGHT);
        scene.setImageResId(R.drawable.scene_sea);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene14_text));
        scene.setFontSize(17);
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_coffee);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene15_text));
        scene.setSubText(getString(R.string.scene15_text_sub));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_party);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene16_text));
        scene.setSubText(getString(R.string.scene16_text_sub));
        scene.setFontSize(23);
        scene.setHorizontalAlign(SceneVO.ALIGN_RIGHT);
        scene.setImageResId(R.drawable.scene_shells);
        mSceneList.add(scene);


        scene = new SceneVO();
        scene.setText(getString(R.string.scene17_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_LEFT);
        scene.setImageResId(R.drawable.scene_bike);
        mSceneList.add(scene);


        scene = new SceneVO();
        scene.setText(getString(R.string.scene18_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_RIGHT);
        scene.setImageResId(R.drawable.scene_girl1);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene23_2_text));
        scene.setSubText(getString(R.string.scene23_2_text_sub));
        scene.setHorizontalAlign(SceneVO.ALIGN_LEFT);
        scene.setImageResId(R.drawable.scene_poet);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene19_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_coffee2);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene23_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_ol);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene20_text));
        scene.setSubText(getString(R.string.scene20_text_sub));
        scene.setHorizontalAlign(SceneVO.ALIGN_RIGHT);
        scene.setImageResId(R.drawable.scene_doraemon);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene21_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_LEFT);
        scene.setImageResId(R.drawable.scene_girl1);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene22_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_RIGHT);
        scene.setImageResId(R.drawable.scene_sticker);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene24_text));
        scene.setFontSize(20);
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_rose);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene25_text));
        scene.setSubText(getString(R.string.scene25_text_sub));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_app_name);
        mSceneList.add(scene);


        scene = new SceneVO();
        scene.setText(getString(R.string.scene26_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_confess);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene27_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_give_flower);
        mSceneList.add(scene);


        scene = new SceneVO();
        scene.setText(getString(R.string.scene28_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_confess);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene29_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_confess);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene30_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_confess);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene31_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_confess);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene32_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_LEFT);
        scene.setImageResId(R.drawable.scene_confess);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setText(getString(R.string.scene33_text));
        scene.setHorizontalAlign(SceneVO.ALIGN_CENTER);
        scene.setImageResId(R.drawable.scene_confess);
        mSceneList.add(scene);

        scene = new SceneVO();
        scene.setType(SceneVO.TYPE_FINAL);
        scene.setImageResId(R.drawable.scene_propose);
        mSceneList.add(scene);
    }

    /**
     * 이미지 로드 리스너
     */
    private ImageSwitcher.ImageLoadListener mImageLoadListener = new ImageSwitcher.ImageLoadListener() {
        @Override
        public void onLoadStart() {
            layoutMainProg.setVisibility(View.VISIBLE);
        }

        @Override
        public void onLoadSuccess() {
            layoutMainProg.setVisibility(View.GONE);
        }
    };

    /**
     * 뷰페이저 페이지 전환 리스너
     */
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            SceneVO scene = mSceneList.get(position);
            mSwitcher.displayImage(scene.getImageResId());
            checkAndPlayMusic();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 마지막 씬 선택 리스너
     */
    private ScenePagerAdapter.FinalSceneInteractionListener mFinalInteractionListener = new ScenePagerAdapter.FinalSceneInteractionListener() {
        @Override
        public void onInteractionArrival(int type) {

            if (type == ScenePagerAdapter.FINAL_DENY) {
                Toast.makeText(MainActivity.this, "그건 선택할 수 없어요! ㅠ_ㅠ", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("type", type);
            startActivity(intent);
            MainActivity.this.finish();
        }
    };

    /**
     * 뷰 터치 리스너
     */
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    };
}
