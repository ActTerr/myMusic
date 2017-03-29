package com.wm.remusic.uitl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.wm.remusic.R;
import com.wm.remusic.activity.MainActivity;


/**
 * Created by mac-yk on 2017/3/2.
 */

public class MFGT {


    public static void startActivity(Context context,Intent intent){
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }
    public static void gotoLoginActivity(Context context) {
        Intent intent=new Intent(context,MainActivity.class);
        startActivity(context,intent);
    }

    public static void gotoMainActivity(Context context){
        Intent intent=new Intent(context, MainActivity.class);
        startActivity(context,intent);
    }
    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
}
