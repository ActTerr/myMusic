package com.wm.remusic.json;

/**
 * Created by wm on 2016/4/15.
 */
public class GeDanGeInfo {

    /**
     * title : 遇见
     * song_id : 7330424
     * author : 情歌伴侣
     * album_id : 7313624
     * album_title : 情歌伴侣 Vol. 1
     * relate_status : 0
     * is_charge : 1
     * all_rate : 24,64,128,192,256,320,flac
     * high_rate : 320
     * all_artist_id : 1077175
     * copy_type : 1
     * has_mv : 0
     * toneid : 0
     * resource_type : 0
     * is_ksong : 0
     * has_mv_mobile : 0
     * ting_uid : 208167
     * is_first_publish : 0
     * havehigh : 2
     * charge : 1
     * learn : 0
     * song_source : web
     * piao_id : 0
     * korean_bb_song : 0
     * resource_type_ext : 0
     * mv_provider : 0000000000
     * share : http://music.baidu.com/song/7330424
     */

    private String title;
    private String song_id;
    private String author;
    private String album_id;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    @Override
    public String toString() {
        return "GeDanGeInfo{" +
                "title='" + title + '\'' +
                ", song_id='" + song_id + '\'' +
                ", author='" + author + '\'' +
                ", album_id='" + album_id + '\'' +
                '}';
    }
}
