package com.wm.remusic.net;


import com.squareup.okhttp.RequestBody;
import com.wm.remusic.I;
import com.wm.remusic.bean.Result;
import com.wm.remusic.json.MusicFileDownInfo;
import com.wm.remusic.json.SearchArtistInfo;
import com.wm.remusic.json.SearchSongInfo;

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mac-yk on 2017/3/18.
 */

public interface ServerAPI {


    @GET(I.REQUEST.PATH+I.REQUEST.LOGIN)
    Observable<Result<String>> login(@Query(I.USER.USER) String name, @Query(I.USER.PASSWD) String passwd);

    @GET(I.REQUEST.PATH+I.REQUEST.LOGOUT)
    Observable<Result<String>> logOut(@Query(I.USER.USER) String name);

    @GET(I.REQUEST.PATH+I.REQUEST.SEARCHARTIST)
    Observable<Result<SearchArtistInfo[]>> searchArtist(@Query(I.SERACHARTISIINFO.KEY) String key);

    @GET(I.REQUEST.PATH+I.REQUEST.SEARCHSONG)
    Observable<Result<SearchSongInfo[]>> searchSong(@Query(I.USER.USER) String name,@Query(I.SERACHARTISIINFO.KEY) String key);

    @GET(I.REQUEST.PATH+I.REQUEST.GETDOWNINFO)
    Observable<Result<MusicFileDownInfo>> getDownInfo(@Query(I.MUSICFILEDOWNINFO.SONG_ID) String id);

//    @GET(I.)

    @POST(I.REQUEST.PATH+I.REQUEST.UPLOADUNCAUGHT)
    @Multipart
    Observable<Result<String>> uploadCrash(
            @Part("file\";filename=\"throwable.log\"") RequestBody file,
            @Query(I.UNCAUGHT.PATH) String path, @Query(I.UNCAUGHT.FILE_NAME) String name);
    
    @GET(I.REQUEST.PATH+I.REQUEST.GETPUSH)
    Observable<Result<String>> getPush(@Query(I.USER.USER) String user);

}
