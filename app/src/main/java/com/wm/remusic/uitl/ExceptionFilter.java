package com.wm.remusic.uitl;

import android.content.Context;

import com.wm.remusic.I;
import com.wm.remusic.net.APIException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;



/**
 * Created by mac-yk on 2017/3/19.
 */

public class ExceptionFilter {
    public static boolean filter(Context context,Throwable e){
        boolean flag=false;
        if (e instanceof APIException) {
            APIException exception = (APIException) e;
            L.e("main","进入error分支");
            if (exception.code== I.RESULT.DEFEAT){

                flag=true;
            }
//            ToastUtil.showToast(context,exception.message);
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showNetWorkBad(context);
        } else if (e instanceof ConnectException) {
            ToastUtil.showNetWorkBad(context);
        }
        return flag;
    }
}
