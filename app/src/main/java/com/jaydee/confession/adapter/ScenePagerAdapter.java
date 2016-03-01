package com.jaydee.confession.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydee.confession.R;
import com.jaydee.confession.custom.FontTextView;
import com.jaydee.confession.vo.SceneVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jayjaydee on 15. 6. 23..
 */
public class ScenePagerAdapter extends PagerAdapter {

    public static final String TAG = ScenePagerAdapter.class.getSimpleName();

    public static final int FINAL_ACCEPT = 0;
    public static final int FINAL_DENY = 1;
    public static final int FINAL_DELAY = 2;

    public interface FinalSceneInteractionListener {
        void onInteractionArrival(int type);
    }

    private class ViewHolder {
        public FontTextView textRowSceneText = null;
        public FontTextView textRowSceneSubText = null;
        public LinearLayout layoutRowSceneWrapper = null;
    }

    private List<SceneVO> mList = null;
    private Context mContext = null;
    private LayoutInflater mInflater = null;
    private HashMap<Integer,ViewHolder> mHolderMap = null;
    private HashMap<Integer,View> mViewMap = null;
    private View mFinalView = null;
    private FinalSceneInteractionListener mListener = null;

    public ScenePagerAdapter(Context ctx, List<SceneVO> sceneList, FinalSceneInteractionListener listener) {
        super();
        mList = sceneList;
        mContext = ctx;
        mInflater = LayoutInflater.from(mContext);
        mViewMap = new HashMap<>();
        mHolderMap = new HashMap<>();
        mListener = listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SceneVO scene = mList.get(position);
        View viewRoot = null;

        if (scene.getType() == SceneVO.TYPE_NORMAL) {
            // 보통의 씬일 경우.

            ViewHolder holder = mHolderMap.get(position);
            if (holder == null) {
                Log.i(TAG, ">>> ITEM " + Integer.toString(position));
                viewRoot = mInflater.inflate(R.layout.row_scene, container, false);
                mViewMap.put(position, viewRoot);

                holder = new ViewHolder();
                holder.textRowSceneText = (FontTextView)viewRoot.findViewById(R.id.textRowSceneText);
                holder.textRowSceneSubText = (FontTextView)viewRoot.findViewById(R.id.textRowSceneSubText);
                holder.layoutRowSceneWrapper = (LinearLayout)viewRoot.findViewById(R.id.layoutRowSceneWrapper);
                mHolderMap.put(position, holder);
            } else {
                viewRoot = mViewMap.get(position);
            }

            applyNormalView(holder, scene);

        } else if (scene.getType() == SceneVO.TYPE_FINAL) {
            // 마지막 씬 (선택씬) 인 경우.

            if (mFinalView == null) {
                mFinalView = mInflater.inflate(R.layout.row_scene_final, container, false);
                viewRoot = mFinalView;
            } else {
                viewRoot = mFinalView;
            }

            applyFinalView(viewRoot);
        }

        container.addView(viewRoot);
        return viewRoot;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 뷰홀더에 데이터를 적용
     * @param vh
     */
    private void applyNormalView(ViewHolder vh, SceneVO scene) {
        vh.textRowSceneText.setText(scene.getText());
        int fontInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scene.getFontSize(), mContext.getResources().getDisplayMetrics());
        vh.textRowSceneText.setTextSize((float)fontInPx);

        if (scene.getSubText() != null) {
            vh.textRowSceneSubText.setText(scene.getSubText());
            vh.textRowSceneSubText.setVisibility(View.VISIBLE);
        } else {
            vh.textRowSceneSubText.setVisibility(View.GONE);
        }

        switch(scene.getHorizontalAlign()) {
            case SceneVO.ALIGN_LEFT:
                vh.layoutRowSceneWrapper.setGravity(Gravity.LEFT);
                vh.textRowSceneText.setGravity(Gravity.LEFT);
                vh.textRowSceneSubText.setGravity(Gravity.LEFT);
                break;

            case SceneVO.ALIGN_CENTER:
                vh.layoutRowSceneWrapper.setGravity(Gravity.CENTER);
                vh.textRowSceneText.setGravity(Gravity.CENTER);
                vh.textRowSceneSubText.setGravity(Gravity.CENTER);
                break;

            case SceneVO.ALIGN_RIGHT:
                vh.layoutRowSceneWrapper.setGravity(Gravity.RIGHT);
                vh.textRowSceneText.setGravity(Gravity.RIGHT);
                vh.textRowSceneSubText.setGravity(Gravity.RIGHT);
                break;
        }
    }

    /**
     * 마지막 Scene 이벤트 바인딩
     */
    private void applyFinalView(View viewRoot) {

        viewRoot.findViewById(R.id.layoutBtnFinalAccept).setOnClickListener(mClickListener);
        viewRoot.findViewById(R.id.layoutBtnFinalDeny).setOnClickListener(mClickListener);
        viewRoot.findViewById(R.id.layoutBtnFinalDelay).setOnClickListener(mClickListener);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * 뷰 클릭 리스너
     */
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            switch(id) {
                case R.id.layoutBtnFinalAccept:
                    mListener.onInteractionArrival(FINAL_ACCEPT);
                    break;

                case R.id.layoutBtnFinalDeny:
                    mListener.onInteractionArrival(FINAL_DENY);
                    break;

                case R.id.layoutBtnFinalDelay:
                    mListener.onInteractionArrival(FINAL_DELAY);
                    break;
            }
        }
    };
}
