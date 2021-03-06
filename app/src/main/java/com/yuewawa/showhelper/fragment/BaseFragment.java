package com.yuewawa.showhelper.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuewawa.showhelper.constant.CustomConstant;

/**
 * Created by yuewawa on 2016-07-29.
 */
public class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    protected FragmentTransaction ft = null;
    protected Fragment fragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        Log.i(TAG, "onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        Log.i(TAG, "onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        Log.i(TAG, "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        Log.i(TAG, "onStop()");
        super.onStop();
    }

    public static BaseFragment newInstance(String tag){
        BaseFragment baseFragment = null;
        if (TextUtils.equals(tag, CustomConstant.HOME_FRAGMENT)){
            baseFragment = new HomeFragment();
        } else if (TextUtils.equals(tag, CustomConstant.FAVORITE_FRAGMENT)) {
            baseFragment = new FavoriteFragment();
        } else if (TextUtils.equals(tag, CustomConstant.MINE_FRAGMENT)) {
            baseFragment = new MineFragment_();
        }
        return baseFragment;
    }

    /**
     * 初始化Fragement
     * @param tag 标签
     * @return
     * */
    private Fragment initFragment(String tag, FragmentManager fm){
        fragment = fm.findFragmentByTag(tag);
        if (fragment==null){
            fragment = BaseFragment.newInstance(tag);
        }
        return fragment;
    }

    /**
     * 初始化FragementTransaction
     * @return FragmentTransaction
     * */
    private FragmentTransaction initFragTrans(FragmentManager fm){
        if (ft==null){
            ft = fm.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
        return ft;
    }

    /**
     * 加载Fragment
     * @param fragmentLayout 布局
     * @param fragment 分段
     * @param tag 标签
     * */
    private void attachFragment(int fragmentLayout, Fragment fragment, String tag, FragmentManager fm){
        if (fragment!=null){
            if (fragment.isDetached()){
                initFragTrans(fm);
                ft.attach(fragment);
            }
            else if (!fragment.isAdded()){
                initFragTrans(fm);
                ft.add(fragmentLayout, fragment, tag);
            }
        }
    }

    /**
     * 提交FragmentTransaction
     * @param tag 标签
     * */
    private void commitFragTrans(String tag, String currentTag){
        if (ft!=null && !ft.isEmpty()){
            ft.commit();
            currentTag = tag;
            ft = null;
        }
    }

    /**
     * 移除Fragement
     * @param fragment 分段
     * */
    private void detachFragment(Fragment fragment, FragmentManager fm){
        if (fragment!=null && !fragment.isDetached()){
            initFragTrans(fm);
            ft.detach(fragment);
        }
    }

    /**
     * 切换Fragment
     * @param tag 标签
     * */
    public void changeFragment(int fragmentLayout, String tag, String currentTag, FragmentManager fm){
        if (TextUtils.equals(tag, currentTag)){
            return;
        }
        if (currentTag!=null && !currentTag.equals("")){
            detachFragment(initFragment(currentTag, fm), fm);
        }
        attachFragment(fragmentLayout, initFragment(tag, fm), tag, fm);
        commitFragTrans(tag, currentTag);
    }

    /**
     * 设置默认显示的Fragment
     * @param tag 标签
     * */
    public void setDefaultFragment(int fragmentLayout, String tag, String currentTag, FragmentManager fm){
        changeFragment(fragmentLayout, tag, currentTag, fm);
    }
}

