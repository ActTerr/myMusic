package com.wm.remusic.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wm.remusic.MainApplication;
import com.wm.remusic.R;
import com.wm.remusic.net.ApiWrapper;
import com.wm.remusic.net.ServerAPI;
import com.wm.remusic.showCauseActivity;
import com.wm.remusic.uitl.ExceptionFilter;
import com.wm.remusic.uitl.MFGT;
import com.wm.remusic.uitl.SpUtil;
import com.wm.remusic.uitl.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserActivity extends AppCompatActivity {
    @BindView(R.id.user)
    TextView user;
    @BindView(R.id.lock)
    ImageView lock;
    @BindView(R.id.shoushiText)
    TextView shoushiText;
    @BindView(R.id.right_btn)
    ImageView rightBtn;

    Context context;
    ProgressDialog pd;
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        context = this;
        pd = new ProgressDialog(context);
        user.setText(MainApplication.getUserName());

    }

    @OnClick({R.id.rlUser, R.id.rlPasswd, R.id.logOut, R.id.auxiliary})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlUser:
                Toast.makeText(this, "账号不可修改", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rlPasswd:
//                MFGT.gotoGestureActivity(context);
                break;
            case R.id.auxiliary:
//                Intent intent = new Intent(this, AuxiliaryActivity.class);
//                startActivity(intent);
            case R.id.logOut:
                pd.show();
                ApiWrapper<ServerAPI> wrapper = new ApiWrapper<>();
                subscription= wrapper.targetClass(ServerAPI.class).getAPI().logOut(MainApplication.getUserName())
                        .compose(wrapper.<String>applySchedulers())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                pd.dismiss();
                                if (          ExceptionFilter.filter(context,e)){
                                    ToastUtil.showToast(context,"请求失败");
                                }

                            }

                            @Override
                            public void onNext(String s) {
                                pd.dismiss();
                                MainApplication.setUserName(null);
                                SpUtil.saveLoginUser(context, "");
                                Intent intent=new Intent(context,showCauseActivity.class);
                                context.startActivity(intent);
//                                MFGT.gotoLoginActivity(context);
                                MFGT.finish((Activity) context);
                            }
                        });

        }
    }
}
