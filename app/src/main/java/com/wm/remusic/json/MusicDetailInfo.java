package com.wm.remusic.json;

/**
 * Created by wm on 2016/6/14.
 */
public class MusicDetailInfo {


    /**
     * artist_id : 304
     * all_artist_id : 304
     * album_no : 17
     * pic_big : http://qukufile2.qianqian.com/data2/pic/115994777/115994777.jpg
     * pic_small : http://qukufile2.qianqian.com/data2/pic/115994792/115994792.jpg
     * relate_status : 0
     * resource_type : 0
     * copy_type : 1
     * lrclink : http://musicdata.baidu.com/data2/lrc/13862677/13862677.lrc
     * pic_radio : http://musicdata.baidu.com/data2/pic/115994767/115994767.jpg
     * toneid : 0
     * all_rate : 24,64,128,192,256,320,flac
     * play_type :
     * has_mv_mobile : 1
     * pic_premium : http://musicdata.baidu.com/data2/pic/115994762/115994762.jpg
     * pic_huge : http://musicdata.baidu.com/data2/pic/115994758/115994758.jpg
     * versions :
     * song_id : 7317513
     * title : Rain
     * ting_uid : 1184
     * author : 范晓萱
     * album_id : 7311803
     * album_title : 国语经典101
     * is_first_publish : 0
     * havehigh : 2
     * charge : 1
     * has_mv : 1
     * learn : 1
     * song_source : web
     * piao_id : 0
     * korean_bb_song : 0
     * resource_type_ext : 0
     * mv_provider : 0100000000
     * song_title : Rain
     * artist_name : 范晓萱
     */

    private String artist_id;
    private String lrclink;
    private String pic_radio;
    private String song_id;
    private String album_title;
    private String artist_name;

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getLrclink() {
        return lrclink;
    }

    public void setLrclink(String lrclink) {
        this.lrclink = lrclink;
    }

    public String getPic_radio() {
        return pic_radio;
    }

    public void setPic_radio(String pic_radio) {
        this.pic_radio = pic_radio;
    }

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }


    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    @Override
    public String toString() {
        return "MusicDetailInfo{" +
                "artist_id='" + artist_id + '\'' +
                ", lrclink='" + lrclink + '\'' +
                ", pic_radio='" + pic_radio + '\'' +
                ", song_id='" + song_id + '\'' +
                ", album_title='" + album_title + '\'' +
                ", artist_name='" + artist_name + '\'' +
                '}';
    }
}
