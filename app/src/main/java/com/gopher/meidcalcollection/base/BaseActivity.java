package com.gopher.meidcalcollection.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.gopher.meidcalcollection.IBaseActivity;
import com.gopher.meidcalcollection.MApp;
import com.gopher.meidcalcollection.R;
import com.gopher.meidcalcollection.util.Operation;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/11/16.
 */

public abstract class BaseActivity extends Activity implements IBaseActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    /**
     * 是否运行截屏
     **/
    private boolean isCanScreenshot = true;
    /**
     * 共通操作
     **/
    protected Operation mOperation = null;

    /**
     * 当前Activity的弱引用，防止内存泄露
     **/
    private WeakReference<Activity> context = null;

    /***整个应用Applicaiton**/
    private App mApplication = null;

    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseActivity-->onCreate()");

        //获取应用Application
        mApplication = (App) getApplicationContext();

        //将当前Activity压入栈
        context = new WeakReference<Activity>(this);
        mApplication.pushTask(context);

        //实例化共通操作
        mOperation = new Operation(this);

        //初始化参数
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mAnimationType = bundle.getInt(ANIMATION_TYPE, NONE);
        } else {
            bundle = new Bundle();
        }
        initParms(bundle);

        //设置渲染视图View
        View mView = bindView();
        if (null == mView) {
            mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        } else {
            mContextView = mView;
        }
        setContentView(mContextView);

        //初始化控件
        initView(mContextView);

        //业务操作
        doBusiness(this);


        //是否可以截屏
        if (!isCanScreenshot) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "BaseActivity-->onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "BaseActivity-->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "BaseActivity-->onResume()");
        resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "BaseActivity-->onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "BaseActivity-->onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "BaseActivity-->onDestroy()");

        destroy();
        MApp.removeTask(context);
    }

    /**
     * 获取当前Activity
     *
     * @return
     */
    protected Activity getContext() {
        if (null != context)
            return context.get();
        else
            return null;
    }


    /**
     * 获取共通操作机能
     */
    public Operation getOperation() {
        return this.mOperation;
    }


    /**
     * 设置是否可截屏
     *
     * @param isCanScreenshot
     */
    public void setCanScreenshot(boolean isCanScreenshot) {
        this.isCanScreenshot = isCanScreenshot;
    }

    /**
     * Actionbar点击返回键关闭事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 动画类型
     **/
    private int mAnimationType = NONE;

    public void finish() {
        super.finish();
        switch (mAnimationType) {
            case IBaseActivity.LEFT_RIGHT:
                overridePendingTransition(0, R.anim.base_slide_right_out);
                break;
            case IBaseActivity.TOP_BOTTOM:
                overridePendingTransition(0, R.anim.push_up_out);
                break;
            case IBaseActivity.FADE_IN_OUT:
                overridePendingTransition(0, R.anim.fade_out);
                break;
            default:
                break;
        }
        mAnimationType = NONE;
    }
}