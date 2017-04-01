package com.wm.remusic;

public interface I {

    interface REQUEST{
        String UPLOADUNCAUGHT="uploadUncaught";
        String PATH="Server";
        String SERVER_ROOT="http://192.168.1.100:8080/myMusic/";
        String LOGIN="login";
        String REGISTER="register";
        String DOWNMUSIC="downMusic";
        String SONGSHEET="songSheet";
        String SEARCHARTIST="searchArtist";
        String SEARCHSONG="searchSong";
        String MUSICINFO="musicInfo";
        String LOGOUT="logOut";
        String GETPUSH="getPush";
        String GETDOWNINFO="getDownInfo";

    }

    interface RESULT{
        int SUCCESS=1;
        int DEFEAT=0;
        String SUC="成功";
        String DEF="失败";
    }

    interface USER{
        String USER="name";
        String PASSWD="passwd";
        String TABLENAME="user";
        String STATUS="status";
    }
    interface ERROR{
        int EXIST=102;
        int DEFEAT=100;
    }
    interface STRERROR{
        String EXIST="账号已存在";
    }

    interface MUSIC{
        String NAME="name";
    }
    interface GEDANINFO{
        String LISTID="listid";
        String LISTENUM="listenum";
        String TITLE="title";
        String PIC="pic_300";
        String TAG="tag";
        String DESC="desc";
        String WIDTH="width";
        String HEIGHT="height";
    }
    interface MUSICDETAILINFO{
        String ARTIST_ID="artist_id";
        String PIC_SMALL="pic_small";
        String LRCLINK="lrclink";
        String PIC_RADIO="pic_radio";
        String SONG_ID="song_id";
        String TITLE="title";
        String AUTHOR="author";
        String ALBUM_ID="album_id";
        String ALBUM_TITLE="album_title";
        String CHARGE="charge";
        String ARTIST_NAME="artist_name";
    }

    interface SERACHARTISIINFO{
        String ARTIST_ID="artist_id";
        String AUTHOR="author";
        String TING_UID="ting_uid";
        String AVATAR_MIDDLE="avatar_middle";
        String TABLENAME = "SearchArtistInfo";
        String KEY ="key" ;
    }
    interface UNCAUGHT{
        String PATH="/Users/mac-yk/Downloads/Server/";
        String FILE_NAME="fileName";
        String FILE="file";
    }
    interface SEARCHSONGINFO{
        String TABLENAME="SearchSongInfo";
        String INFO="info";
        String SONG_ID="song_id";
        String TITLE="title";
        String AUTHOR="author";
        String ALBUM_ID="album_id";
        String ALBUM_TITLE="album_title";
        String CHARGE="charge";
        String ARTIST_ID="artist_id";
        String LRCLINK="lrclink";
    }
    interface MUSICFILEDOWNINFO{
        String TABLENAME="MusicFileDownInfo";
        String SHOW_LINK="show_link";
        String FREE_SIZE="free_size";
        String SONG_ID="song_id";
    }


}
