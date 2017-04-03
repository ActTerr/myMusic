package com.wm.remusic.net;

import android.util.Log;
import android.util.SparseArray;

import com.google.gson.JsonObject;
import com.wm.remusic.MainApplication;
import com.wm.remusic.json.MusicDetailInfo;
import com.wm.remusic.uitl.L;

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

//            ApiWrapper<ServerAPI> wrapper=new ApiWrapper<>();
//            wrapper.targetClass(ServerAPI.class).getAPI().getSongDetail(id)
//                    .compose(wrapper.<MusicDetailInfo>applySchedulers())
//                    .subscribe(new Subscriber<MusicDetailInfo>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            if(ExceptionFilter.filter(MainApplication.context,e)){
//                                ToastUtil.showToast(MainApplication.context,"加载失败");
//                            }
//                        }
//
//                        @Override
//                        public void onNext(MusicDetailInfo musicDetailInfo) {
//                                info=musicDetailInfo;
//                        }
//                    });

            JsonObject jsonObject = HttpUtil.getResposeJsonObject("歌曲基本信息1：",BMA.Song.songBaseInfo(id).trim()).get("result")
                    .getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject();
            info = MainApplication.gsonInstance().fromJson(jsonObject, MusicDetailInfo.class);
            L.e("zhenshizuile",info.toString());
            synchronized (this) {
                Log.e("arraylist", "size" + arrayList.size());
                arrayList.put(p, info);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}