package com.wm.remusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wm.remusic.MainApplication;
import com.wm.remusic.R;
import com.wm.remusic.fragment.BitSetFragment;
import com.wm.remusic.fragment.TimingFragment;
import com.wm.remusic.fragment.simpleMainFrag;
import com.wm.remusic.service.MusicPlayer;
import com.wm.remusic.uitl.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class simpleActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawLayout)
    DrawerLayout drawLayout;
    simpleMainFrag mainfrag;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
        mainfrag=new simpleMainFrag();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),mainfrag,R.id.frame);
        initToolBar();
        setNavView();
    }

    private void setNavView() {
        if (navView!=null){
            navView.inflateMenu(R.menu.simple);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.close:
                        TimingFragment fragment3 = new TimingFragment();
                        fragment3.show(getSupportFragmentManager(), "timing");
                        break;
                    case R.id.quality:
                        BitSetFragment bfragment = new BitSetFragment();
                        bfragment.show(getSupportFragmentManager(), "bitset");
                        break;
                    case R.id.common:
                        Intent intent=new Intent(simpleActivity.this,MainActivity.class);
                        intent.putExtra("from",1);
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
        switch (item.getItemId()){
            case android.R.id.home:
                drawLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

}
