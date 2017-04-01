package com.wm.remusic.json;

/**
 * Created by wm on 2016/4/16.
 */
public class MusicFileDownInfo {

    /**
     * show_link : http://zhangmenshiting.baidu.com/data2/music/66764024/66764024.mp3?xcode=7c8f1aaf10a3704c350a5c038dd045b5
     * down_type : 0
     * original : 0
     * free : 1
     * replay_gain : 0.000000
     * song_file_id : 66764024
     * file_size : 1775862
     * file_extension : mp3
     * file_duration : 221
     * can_see : 1
     * can_load : true
     * preload : 40
     * file_bitrate : 64
     * file_link : http://yinyueshiting.baidu.com/data2/music/66764024/66764024.mp3?xcode=7c8f1aaf10a3704c350a5c038dd045b5
     * is_udition_url : 1
     * hash : d8bc896f901186562c200a9e18a96a5429c59a82
     */

    private String show_link;
    private int file_size;


    public String getShow_link() {
        return show_link;
    }




    public int getFile_size() {
        return file_size;
    }


    public void setShow_link(String show_link) {
        this.show_link = show_link;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }
}
