package com.wm.remusic.downmusic;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.wm.remusic.MainApplication;
import com.wm.remusic.json.MusicDetailInfo;
import com.wm.remusic.json.MusicFileDownInfo;
import com.wm.remusic.net.ApiWrapper;
import com.wm.remusic.net.BMA;
import com.wm.remusic.net.HttpUtil;
import com.wm.remusic.net.ServerAPI;
import com.wm.remusic.uitl.ExceptionFilter;
import com.wm.remusic.uitl.IConstants;
import com.wm.remusic.uitl.L;
import com.wm.remusic.uitl.ToastUtil;

import rx.Subscriber;

/**
 * Created by wm on 2016/5/30.
 */
public class Down {


    public static void downMusic(final Context context, final String id, final String name, final String artist) {

        ApiWrapper<ServerAPI> wrapper = new ApiWrapper<ServerAPI>();
        wrapper.targetClass(ServerAPI.class).getAPI().getDownInfo(id)
                .compose(wrapper.<MusicFileDownInfo>applySchedulers())
                .subscribe(new Subscriber<MusicFileDownInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (ExceptionFilter.filter(context, e)) {
                            ToastUtil.showToast(context, "下载失败");
                        }
                    }

                    @Override
                    public void onNext(MusicFileDownInfo musicFileDownInfo) {
                        if (musicFileDownInfo != null && musicFileDownInfo.getShow_link() != null) {
                            Intent i = new Intent(DownService.ADD_DOWNTASK);
                            i.setAction(DownService.ADD_DOWNTASK);
                            i.putExtra("id", id);
                            L.e("rxjava", "fileNmae:" +name);
                            if (name == null) {
                                i.putExtra("name", "cao");
                            }else{
                                i.putExtra("name",name);
                            }
                            i.putExtra("artist", artist);
                            i.putExtra("url", musicFileDownInfo.getShow_link());
                            i.setPackage(IConstants.PACKAGE);
                            context.startService(i);
                        } else {
                            Toast.makeText(context, "该歌曲没有下载连接", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
//        new AsyncTask<String, String, MusicFileDownInfo>() {
//            @Override
//            protected MusicFileDownInfo doInBackground(final String... name) {
//                try {
//                    JsonArray jsonArray = HttpUtil.getResposeJsonObject("歌曲信息和下载地址2:",BMA.Song.songInfo(id).trim()).get("songurl")
//                            .getAsJsonObject().get("url").getAsJsonArray();
//                    int len = jsonArray.size();
//
//                    int downloadBit = PreferencesUtility.getInstance(context).getDownMusicBit();
//                    MusicFileDownInfo musicFileDownInfo;
//                    for (int i = len - 1; i > -1; i--) {
//                        int bit = Integer.parseInt(jsonArray.get(i).getAsJsonObject().get("file_bitrate").toString());
//                        if (bit == downloadBit) {
//                            musicFileDownInfo = MainApplication.gsonInstance().fromJson(jsonArray.get(i), MusicFileDownInfo.class);
//                            return musicFileDownInfo;
//                        } else if (bit < downloadBit && bit >= 64) {
//                            musicFileDownInfo = MainApplication.gsonInstance().fromJson(jsonArray.get(i), MusicFileDownInfo.class);
//                            return musicFileDownInfo;
//                        }
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(MusicFileDownInfo musicFileDownInfo) {
////                if (musicFileDownInfo != null && musicFileDownInfo.getShow_link() != null) {
////                    Intent i = new Intent(DownService.ADD_DOWNTASK);
////                    i.setAction(DownService.ADD_DOWNTASK);
////                    i.putExtra("id", id);
////                    i.putExtra("name", name);
////                    i.putExtra("artist", artist);
////                    i.putExtra("url", musicFileDownInfo.getShow_link());
////                    L.e("url:",musicFileDownInfo.getShow_link());
////                    i.setPackage(IConstants.PACKAGE);
////                    context.startService(i);
////                }else {
////                    Toast.makeText(context,"该歌曲没有下载连接",Toast.LENGTH_SHORT).show();
////                }
//            }
//        }.execute();
//    }


    public static MusicFileDownInfo getUrl(final Context context, final String id) {
        MusicFileDownInfo musicFileDownInfo = null;
        try {
            JsonArray jsonArray = HttpUtil.getResposeJsonObject("歌曲信息和下载地址3:",BMA.Song.songInfo(id).trim(), context, false).get("songurl")
                    .getAsJsonObject().get("url").getAsJsonArray();
            int len = jsonArray.size();
            int downloadBit = 192;

            for (int i = len - 1; i > -1; i--) {
                int bit = Integer.parseInt(jsonArray.get(i).getAsJsonObject().get("file_bitrate").toString());
                if (bit == downloadBit) {
                    musicFileDownInfo = MainApplication.gsonInstance().fromJson(jsonArray.get(i), MusicFileDownInfo.class);

                } else if (bit < downloadBit && bit >= 64) {
                    musicFileDownInfo = MainApplication.gsonInstance().fromJson(jsonArray.get(i), MusicFileDownInfo.class);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return musicFileDownInfo;
    }

    public static MusicDetailInfo getInfo(final String id) {
        MusicDetailInfo info = null;
        try {
            JsonObject jsonObject = HttpUtil.getResposeJsonObject("歌曲基本信息：",BMA.Song.songBaseInfo(id).trim()).get("result")
                    .getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject();
            info = MainApplication.gsonInstance().fromJson(jsonObject, MusicDetailInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return info;
    }


    static class getUrl extends Thread {
        boolean isRun = true;
        String id;
        MusicFileDownInfo musicFileDownInfo;

        public getUrl(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            JsonArray jsonArray = HttpUtil.getResposeJsonObject("歌曲信息和下载地址4:",BMA.Song.songInfo(id).trim()).get("songurl")
                    .getAsJsonObject().get("url").getAsJsonArray();
            int len = jsonArray.size();

            int downloadBit = 128;

            for (int i = len - 1; i > -1; i--) {
                int bit = Integer.parseInt(jsonArray.get(i).getAsJsonObject().get("file_bitrate").toString());
                if (bit == downloadBit) {
                    musicFileDownInfo = MainApplication.gsonInstance().fromJson(jsonArray.get(i), MusicFileDownInfo.class);

                } else if (bit < downloadBit && bit >= 64) {
                    musicFileDownInfo = MainApplication.gsonInstance().fromJson(jsonArray.get(i), MusicFileDownInfo.class);
                }
            }
        }
    }

}
