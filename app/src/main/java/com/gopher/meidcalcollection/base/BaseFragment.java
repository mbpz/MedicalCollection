package com.gopher.meidcalcollection.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gopher.meidcalcollection.util.Operation;

/**
 * Created by Administrator on 2017/11/17.
 */

public abstract class BaseFragment extends Fragment implements IBaseFragment {
    /**
     * 当前Fragment渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 共通操作
     **/
    protected Operation mOperation = null;
    /**
     * 依附的Activity
     **/
    protected Activity mContext = null;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //缓存当前依附的activity
        mContext = activity;
        Log.d(TAG, "BaseFragment-->onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseFragment-->onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragment-->onCreateView()");
        // 渲染视图View
        if (null == mContextView) {
            //初始化参数
            initParms(getArguments());

            View mView = bindView();
            if (null == mView) {
                mContextView = inflater.inflate(bindLayout(), container, false);
            } else {
                mContextView = mView;
            }
            // 控件初始化
            initView(mContextView);
            // 实例化共通操作
            mOperation = new Operation(getActivity());
            // 业务处理
            doBusiness(getActivity());
        }

        return mContextView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragment-->onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "BaseFragment-->onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "BaseFragment-->onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "BaseFragment-->onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "BaseFragment-->onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "BaseFragment-->onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "BaseFragment-->onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "BaseFragment-->onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mContextView != null && mContextView.getParent() != null) {
            ((ViewGroup) mContextView.getParent()).removeView(mContextView);
        }
    }

    /**
     * 获取当前Fragment依附在的Activity
     *
     * @return
     */
    public Activity getContext() {
        return getActivity();
    }

    /**
     * 获取共通操作机能
     */
    public Operation getOperation() {
        return this.mOperation;
    }
}
