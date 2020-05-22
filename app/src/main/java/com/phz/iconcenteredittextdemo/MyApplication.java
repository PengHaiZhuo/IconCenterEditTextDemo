package com.phz.iconcenteredittextdemo;

import android.app.Application;

import com.phz.iconcenteredittextdemo.bean.DaoMaster;
import com.phz.iconcenteredittextdemo.bean.DaoSession;
import com.phz.iconcenteredittextdemo.config.Constract;

import org.greenrobot.greendao.database.Database;

/**
 * @author haizhuo
 * @introduction 自定义工程类
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;
    /**
     * greenDao会话
     */
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        initGreenDao();
    }


    /**
     * 获取实例
     *
     * @return myApplication
     */
    public static MyApplication getInstance() {
        return myApplication;
    }

    /**
     * greenDoa初始化
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constract.DB_NAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
