package com.wecent.weixun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.wecent.weixun.R;
import com.wecent.weixun.component.ApplicationComponent;
import com.wecent.weixun.ui.base.BaseActivity;
import com.wecent.weixun.ui.base.SupportFragment;
import com.wecent.weixun.ui.belle.BelleFragment;
import com.wecent.weixun.ui.mine.MineFragment;
import com.wecent.weixun.ui.news.NewsFragment;
import com.wecent.weixun.ui.video.VideoFragment;
import com.wecent.weixun.utils.AppUtils;
import com.wecent.weixun.utils.ToastUtils;
import com.wecent.weixun.widget.table.BottomBar;
import com.wecent.weixun.widget.table.BottomTab;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bb_main_table)
    BottomBar bbMainTable;

    private long exitTime = 0;
    private SupportFragment[] mFragments = new SupportFragment[4];

    public static void launch(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(R.color.config_color_trans);
        setStatusBarDark(true);
        setFitsSystemWindows(false);
        if (savedInstanceState == null) {
            mFragments[0] = NewsFragment.newInstance();
            mFragments[1] = VideoFragment.newInstance();
            mFragments[2] = BelleFragment.newInstance();
            mFragments[3] = MineFragment.newInstance();

            getSupportDelegate().loadMultipleRootFragment(R.id.fl_main_content, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);
        } else {
            mFragments[0] = findFragment(NewsFragment.class);
            mFragments[1] = findFragment(VideoFragment.class);
            mFragments[2] = findFragment(BelleFragment.class);
            mFragments[3] = findFragment(MineFragment.class);
        }

        bbMainTable.addItem(new BottomTab(this, R.drawable.ic_tab_news, "新闻"))
                .addItem(new BottomTab(this, R.drawable.ic_tab_video, "视频"))
                .addItem(new BottomTab(this, R.drawable.ic_tab_belle, "妹子"))
                .addItem(new BottomTab(this, R.drawable.ic_tab_mine, "我的"));
        bbMainTable.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                getSupportDelegate().showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }

    @Override
    public void bindData() {

    }

    @Override
    public void onReload() {

    }

    @Override
    public void onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showShort("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                AppUtils.exitApp();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
