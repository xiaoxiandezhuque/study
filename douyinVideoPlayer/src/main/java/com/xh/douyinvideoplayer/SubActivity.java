package com.xh.douyinvideoplayer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.tencent.liteav.demo.superplayer.SuperPlayerGlobalConfig;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class SubActivity extends AppCompatActivity {

    private int index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .request();

        SuperPlayerGlobalConfig prefs = SuperPlayerGlobalConfig.getInstance();
        // 播放器默认缓存个数
        prefs.maxCacheItem = 10;
        // 设置播放器渲染模式
        prefs.enableHWAcceleration = true;
        prefs.renderMode = TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;


        final TXVodPlayer  player = new TXVodPlayer(this);
        player.setAutoPlay(false);
        player.startPlay(Urls.videoUrls[3][0]);
//        final TXVodPlayer  player1 = new TXVodPlayer(this);
//        final TXVodPlayer  player2 = new TXVodPlayer(this);
//
        final TXCloudVideoView txCloudVideoView= (TXCloudVideoView) findViewById(R.id.player_view);
//        player.setPlayerView((TXCloudVideoView) findViewById(R.id.player_view));
        player.setVodListener(new ITXVodPlayListener() {
            @Override
            public void onPlayEvent(TXVodPlayer txVodPlayer, int i, Bundle bundle) {
                if (i==TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED){
                    player.resume();
                }
                LogUtils.e(i);
            }

            @Override
            public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

            }
        });
//        txCloudVideoView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                player.resume();
//            }
//        },1000);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.startPlay(Urls.videoUrls[3][index++]);
                player.setPlayerView(txCloudVideoView);
                player.resume();
            }
        });

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               player.stopPlay(true);
               txCloudVideoView.onDestroy();
            }
        });
    }
}
