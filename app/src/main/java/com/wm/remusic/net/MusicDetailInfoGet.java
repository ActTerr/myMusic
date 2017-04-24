package com.wm.remusic.net;

import android.util.Log;
import android.util.SparseArray;

import com.wm.remusic.MainApplication;
import com.wm.remusic.json.MusicDetailInfo;
import com.wm.remusic.uitl.ExceptionFilter;
import com.wm.remusic.uitl.L;
import com.wm.remusic.uitl.ToastUtil;

import rx.Subscriber;

public class MusicDetailInfoGet implements Runnable {
    String id;
    int p;
    SparseArray<MusicDetailInfo> arrayList;
    MusicDetailInfo info = null;

    public MusicDetailInfoGet(String id, int position, SparseArray<MusicDetailInfo> arrayList) {
        this.id = id;
        p = position;
        this.arrayList = arrayList;
    }

    @Override
    public void run() {
        try {
//
            ApiWrapper<ServerAPI> wrapper=new ApiWrapper<>();
            wrapper.targetClass(ServerAPI.class).getAPI().getSongDetail(id)
                    .compose(wrapper.<MusicDetailInfo>applySchedulers())
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<MusicDetailInfo>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if(ExceptionFilter.filter(MainApplication.context,e)){
                                ToastUtil.showToast(MainApplication.context,"加载失败");
                            }
                        }

                        @Override
                        public void onNext(MusicDetailInfo musicDetailInfo) {
                                info=musicDetailInfo;
//                            synchronized (this) {
                                Log.e("arraylist", "size" + arrayList.size());
                                arrayList.put(p, info);
                                L.e("zhenshizuile", p+":" +info.toString());

                        }
                    });

//            JsonObject jsonObject = HttpUtil.getResposeJsonObject("歌曲基本信息1：",BMA.Song.songBaseInfo(id).trim()).get("result")
//                    .getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject();
//            info = MainApplication.gsonInstance().fromJson(jsonObject, MusicDetailInfo.class);
//            synchronized (this) {
//                Log.e("arraylist", "size" + arrayList.size());
//
//                arrayList.put(p, info);
//                L.e("zhenshizuile", p+":" +info.toString());
//            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}