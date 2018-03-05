package com.gopher.meidcalcollection.util;

import android.os.Bundle;
import android.widget.RemoteViews;

import com.gopher.meidcalcollection.Message;

/**
 * Created by Administrator on 2017/11/16.
 */

public class NotificationMessage extends Message {
    /**
     * 状态栏提示信息图标
     */
    private int iconResId;

    /**
     * 状态栏提示信息图标
     */
    private String statusBarText;

    /**
     * 消息标题
     */
    private String msgTitle;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 点击消息跳转的界面
     */
    private Class forwardComponent;

    /**
     * 点击消息跳转界面需携带的数据
     */
    private Bundle extras;

    /**
     * 自定义消息通知布局View
     */
    private RemoteViews mRemoteViews;

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getStatusBarText() {
        return statusBarText;
    }

    public void setStatusBarText(String statusBarText) {
        this.statusBarText = statusBarText;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Class getForwardComponent() {
        return forwardComponent;
    }

    public void setForwardComponent(Class forwardComponent) {
        this.forwardComponent = forwardComponent;
    }

    public Bundle getExtras() {
        return extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    public RemoteViews getmRemoteViews() {
        return mRemoteViews;
    }

    public void setmRemoteViews(RemoteViews mRemoteViews) {
        this.mRemoteViews = mRemoteViews;
    }
}

