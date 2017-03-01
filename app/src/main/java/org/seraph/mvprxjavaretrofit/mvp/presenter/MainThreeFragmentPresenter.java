package org.seraph.mvprxjavaretrofit.mvp.presenter;

import android.content.Context;

import org.reactivestreams.Subscription;
import org.seraph.mvprxjavaretrofit.activity.MainActivity;
import org.seraph.mvprxjavaretrofit.mvp.view.BaseView;
import org.seraph.mvprxjavaretrofit.mvp.view.MainThreeFragmentView;
import org.seraph.mvprxjavaretrofit.request.ApiService;

/**
 * FragmentThee逻辑处理层
 * date：2017/2/15 11:27
 * author：xiongj
 * mail：417753393@qq.com
 **/
public class MainThreeFragmentPresenter extends BasePresenter {


    private MainThreeFragmentView mView;

    private MainActivity mainActivity;

    private float percentScroll = 0;

    @Override
    public void attachView(BaseView view) {
        super.attachView(view);
        this.mView = (MainThreeFragmentView) view;
    }

    @Override
    public void onAttach(Context context) {
        mainActivity = (MainActivity) context;
    }


    private Subscription subscription;

    private String title;

    public void initData() {
        title = " Three";
        setTitle(title);
    }


    public void setTitle(String title) {
        mainActivity.setTitle(title);
    }

    /**
     * 更新头部背景透明度
     *
     * @param percentScroll 进度百分比
     */
    public void upDataToolbarAlpha(float percentScroll) {
        this.percentScroll = percentScroll;
        mainActivity.mPresenter.upDataToolbarAlpha(percentScroll);
    }


    @Override
    public void restoreData() {
        super.restoreData();
        setTitle(title);
        upDataToolbarAlpha(percentScroll);
    }

    public void post12306Https() {
        ApiService.do12306().doOnSubscribe(subscription -> mView.showLoading()).subscribe(s -> {
        }, e -> {
            mView.hideLoading();
            //访问的为12306网站测试证书，所以无法用gson解析
            if (e instanceof javax.net.ssl.SSLHandshakeException) {
                mView.setTextView("缺少https证书");
            } else if (e instanceof com.google.gson.stream.MalformedJsonException) {
                mView.setTextView("访问成功");
            }
        });

    }
}
