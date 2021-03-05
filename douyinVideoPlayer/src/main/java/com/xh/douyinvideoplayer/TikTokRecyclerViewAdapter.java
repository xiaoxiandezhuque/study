package com.xh.douyinvideoplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TikTokRecyclerViewAdapter extends RecyclerView.Adapter<TikTokRecyclerViewAdapter.MyViewHolder> {

    public static final String TAG = "AdapterTikTokRecyclerView";
    int[] videoIndexs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private Context context;
    //    private TXVodPlayer[] mPlayer;
//    private MyViewHolder[] holders;
    private List<String> mdata = new ArrayList<>();

    private int lastPosition = -1;


    private Map<Integer, MyViewHolder> map = new HashMap();

    public TikTokRecyclerViewAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < Urls.videoUrls[3].length; i++) {
            mdata.add(Urls.videoUrls[3][i]);
        }
//        mPlayer = new TXVodPlayer[3];
//        holders = new MyViewHolder[3];
//        for (int i = 0; i < mPlayer.length; i++) {
//            mPlayer[i] = new TXVodPlayer(context);
//            mPlayer[i].setAutoPlay(false);
//        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_tiktok, parent,
                false));
        return holder;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.iv.setVisibility(View.VISIBLE);
        Glide.with(context).load(Urls.videoPosters[0][position]).into(holder.iv);
        holder.player.startPlay(mdata.get(position));
        LogUtils.e("onBindViewHolder--" + position);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        LogUtils.e("onViewAttachedToWindow" + holder.getPosition());

        map.put(holder.getPosition(), holder);

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
//        if (holder != addViewHolder) {
//            curViewHolder = addViewHolder;
//        }
        map.remove(holder.getPosition());
        if (holder.player.isPlaying()){
            holder.player.pause();
            holder.player.seek(0);
// 暂停播放

        }

    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
        holder.player.stopPlay(true);
        holder.txCloudVideoView.onDestroy();
    }

    public void play(int position) {
        LogUtils.e("play" + position);
        if (position == lastPosition) {
            return;
        }
        lastPosition = position;
        final MyViewHolder curViewHolder = map.get(position);
        curViewHolder.player.setVodListener(new ITXVodPlayListener() {
            @Override
            public void onPlayEvent(TXVodPlayer txVodPlayer, int i, Bundle bundle) {
                if (i == TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED) {
                    curViewHolder.player.resume();
                } else if (i == TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME) {
                    curViewHolder.iv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

            }
        });
        curViewHolder.player.resume();
    }

    @Override
    public int getItemCount() {
        return videoIndexs.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TXCloudVideoView txCloudVideoView;
        View view;
        ImageView iv;
        TXVodPlayer player;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txCloudVideoView = itemView.findViewById(R.id.player_view);
            iv = itemView.findViewById(R.id.iv);
            player = new TXVodPlayer(itemView.getContext());
            player.setAutoPlay(false);
            player.setLoop(true);
            player.setPlayerView(txCloudVideoView);
        }
    }

}
