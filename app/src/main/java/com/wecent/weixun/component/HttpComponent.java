package com.wecent.weixun.component;

import com.wecent.weixun.ui.belle.BelleFragment;
import com.wecent.weixun.ui.news.NewsFragment;
import com.wecent.weixun.ui.news.NewsDetailActivity;
import com.wecent.weixun.ui.news.NewsListFragment;
import com.wecent.weixun.ui.video.VideoDetailActivity;
import com.wecent.weixun.ui.video.VideoFragment;
import com.wecent.weixun.ui.video.VideoListFragment;

import dagger.Component;

/**
 * desc:
 * author: wecent
 * date: 2018/9/2
 */
@Component(dependencies = ApplicationComponent.class)
public interface HttpComponent {

    void inject(NewsFragment newsFragment);

    void inject(NewsListFragment newsListFragment);

    void inject(NewsDetailActivity newsDetailActivity);

    void inject(VideoFragment videoFragment);

    void inject(VideoListFragment videoListFragment);

    void inject(VideoDetailActivity videoDetailActivity);

    void inject(BelleFragment belleFragment);

}
