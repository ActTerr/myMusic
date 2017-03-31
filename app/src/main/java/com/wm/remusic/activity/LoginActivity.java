package com.wm.remusic.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wm.remusic.MainApplication;
import com.wm.remusic.R;
import com.wm.remusic.net.ApiWrapper;
import com.wm.remusic.net.ServerAPI;
import com.wm.remusic.uitl.ExceptionFilter;
import com.wm.remusic.uitl.L;
import com.wm.remusic.uitl.MFGT;
import com.wm.remusic.uitl.SpUtil;
import com.wm.remusic.uitl.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginActivity extends AppCompatActivity {
    Context context;
    @BindView(R.id.userName)
    EditText name;
    @BindView(R.id.passwd)
    EditText passwd;
    Subscription subscription;
    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = this;
        progressDialog=new ProgressDialog(context);
    }

    Observer<String> observer=new Observer<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            progressDialog.dismiss();
            if (ExceptionFilter.filter(context,e)){
                Toast.makeText(LoginActivity.this, "密码或者用户名错误!" , Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNext(String s) {
            progressDialog.dismiss();
            if (!passwd.getText().toString().equals("1234")){
                ToastUtil.showToast(context,"密码或者用户名错误，请重试!");
            }else {
                ToastUtil.showToast(context,"登陆成功！");
                SpUtil.saveLoginUser(context,name.getText().toString());
                Intent intent = new Intent(context, MainActivity.class);
                MainApplication.setUserName(name.getText().toString());
                startActivity(intent);
                MFGT.finish((Activity) context);
            }

        }
    };
    public void onLogin(View view) {
//        Observable.just("succcess").subscribe(observer);
        progressDialog.show();
        ApiWrapper<ServerAPI> ApiWrapper =new ApiWrapper<>();
        L.e("longin",name.getText().toString());
        subscription= ApiWrapper.targetClass(ServerAPI.class).getAPI().login(name.getText().toString(),
                passwd.getText().toString()).compose(ApiWrapper.<String>applySchedulers())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
