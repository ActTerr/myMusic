package com.wm.remusic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.wm.remusic.MainApplication;
import com.wm.remusic.R;
import com.wm.remusic.fragment.MainFragment;
import com.wm.remusic.service.MusicPlayer;
import com.wm.remusic.uitl.ActivityUtils;
import com.wm.remusic.uitl.ThemeHelper;
import com.wm.remusic.uitl.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wm.remusic.MainApplication.context;

public class simpleActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawLayout)
    DrawerLayout drawLayout;
    MainFragment fragment;
    @BindView(R.id.back)
    LinearLayout back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
        fragment = new MainFragment();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.frame);
        initToolBar();
        setNavView();
    }

    private void setNavView() {
        if (navView != null) {
            navView.inflateMenu(R.menu.simple);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.close:
//                        TimingFragment fragment3 = new TimingFragment();
//                        fragment3.show(getSupportFragmentManager(), "timing");
//                        break;
//                    case R.id.quality:
//                        BitSetFragment bfragment = new BitSetFragment();
//                        bfragment.show(getSupportFragmentManager(), "bitset");
//                        break;
                    case R.id.common:
                        Intent intent = new Intent(simpleActivity.this, MainActivity.class);
                        intent.putExtra("from", 1);
                        MainApplication.setSimple(false);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.exit:
                        if (MusicPlayer.isPlaying()) {
                            MusicPlayer.playOrPause();
                        }
                        unbindService();
                        finish();
                        break;
                }
                drawLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("极简模式");
        back.setBackgroundColor(getResources().getColor(getTheme(this)));
        toolBar.setBackgroundColor(getResources().getColor(getTheme(this)));
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }





    private int getTheme(Context context) {
        if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_STORM) {
            return R.color.blue;
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_HOPE) {
            return R.color.purple;
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_WOOD) {
            return R.color.green;
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_LIGHT) {
            return R.color.green_light;
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_THUNDER) {
            return R.color.yellow;
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_SAND) {
            return R.color.orange;
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_FIREY) {
            return R.color.red;
        } else if(ThemeHelper.getTheme(context)==ThemeHelper.CARD_SIMPLE){
            return R.color.black2;
        }
        return R.color.red;
    }
    private long FirstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long SecondTime = System.currentTimeMillis();
                if (SecondTime - FirstTime > 2000) {
                    ToastUtil.showToast(context, "再按一次退出应用");
                    FirstTime = SecondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
