package com.xh.douyinvideoplayer;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.tencent.liteav.demo.superplayer.SuperPlayerGlobalConfig;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.FOCUS_BEFORE_DESCENDANTS;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTiktok;
    private TikTokRecyclerViewAdapter mAdapter;
    private ViewPagerLayoutManager mViewPagerLayoutManager;

    private int mCurrentPosition = -1;

    private TXVodPlayer[] mPlayer;
    private List<String> mdata = new ArrayList<>();
    private TXCloudVideoView playView;
    private int currenPlayer;



    private  boolean isFirst =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .request();

        SuperPlayerGlobalConfig prefs = SuperPlayerGlobalConfig.getInstance();
        // 播放器默认缓存个数
        prefs.maxCacheItem = 10;
        // 设置播放器渲染模式
        prefs.enableHWAcceleration = true;
        prefs.renderMode = TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;

        for (int i = 0; i < Urls.videoUrls[3].length; i++) {
            mdata.add(Urls.videoUrls[3][i]);
        }

        ViewPager2 viewPager2 = new ViewPager2(this);


        rvTiktok = findViewById(R.id.rv_tiktok);

        mAdapter = new TikTokRecyclerViewAdapter(this);
        mViewPagerLayoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        rvTiktok.setLayoutManager(mViewPagerLayoutManager);
        rvTiktok.setAdapter(mAdapter);

        rvTiktok.setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
        mViewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                //自动播放第一条
                autoPlayVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
//                if (mCurrentPosition == position) {
//                    Jzvd.releaseAllVideos();
//                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                if (mCurrentPosition == position) {
                    return;
                }
                autoPlayVideo(position);
                mCurrentPosition = position;
            }
        });

        mPlayer = new TXVodPlayer[3];
        for (int i = 0; i < mPlayer.length; i++) {
            mPlayer[i] = new TXVodPlayer(this);
            mPlayer[i].setAutoPlay(false);
        }
//        Button btn = findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPlayer[0].stopPlay(true);
//            }
//        });
    }

    private void autoPlayVideo(int position) {
        if (rvTiktok == null || rvTiktok.getChildAt(0) == null) {
            return;
        }

        int index = position == 0 ? 0 : 1;
        TXCloudVideoView txCloudVideoView = rvTiktok.getChildAt(index).findViewById(R.id.player_view);
        final ImageView iv = rvTiktok.getChildAt(index).findViewById(R.id.iv);
        playView = txCloudVideoView;
        LogUtils.e("当前播放位置---" + position + "--" + txCloudVideoView);
        currenPlayer = position % 3;

        initPlayer(position);


        mPlayer[currenPlayer].setPlayerView(txCloudVideoView);
        mPlayer[currenPlayer].setVodListener(new ITXVodPlayListener() {
            @Override
            public void onPlayEvent(TXVodPlayer txVodPlayer, int i, Bundle bundle) {
                if (i == TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED) {
                    mPlayer[currenPlayer].resume();
                } else if (i == TXLiveConstants.PLAY_EVT_PLAY_BEGIN) {
                    iv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

            }
        });

        mPlayer[currenPlayer].resume();
    }


    private void initPlayer(int position) {


        if (isFirst ) {
            isFirst = false;
            mPlayer[currenPlayer].startPlay(mdata.get(position));
        }
        if (position + 1 < mdata.size()) {
            int next = (position + 1) % 3;
            if (mPlayer[next] != null && mPlayer[next].isPlaying()) {
                mPlayer[next].stopPlay(true);
                playView.onDestroy();
            }

            mPlayer[next].startPlay(mdata.get(position + 1));
        }
        if (position - 1 >= 0) {
            int pre = (position - 1) % 3;
            if (mPlayer[pre] != null && mPlayer[pre].isPlaying()) {
                mPlayer[pre].stopPlay(true);
                playView.onDestroy();
            }
            mPlayer[pre].startPlay(mdata.get(position - 1));
        }

    }


}
