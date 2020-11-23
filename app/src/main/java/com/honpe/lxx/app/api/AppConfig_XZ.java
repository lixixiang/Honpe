package com.honpe.lxx.app.api;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * FileName: AppConfig
 * Author: asus
 * Date: 2020/8/27 11:29
 * Description:应用程序配置类：用于保存用户相关信息及设置
 */
@SuppressLint("NewApi")
public class AppConfig_XZ {
    public static String APP_ID = "1104510714";
    private final static String APP_CONFIG_XZ = "app_config_xz";
    public final static String KEY_USER = "Users_xz";
    public final static String KEY_USER_TEMP = "UsersTemp_xz";//临时保存
    public final static String KEY_BACK_NEW_DATA = "BackNewData_xz"; //返回到首页时的新界面
    public final static String KEY_All_XZ = "All_xz";

    private Context mContext;
    private static AppConfig_XZ appConfig;

    public static AppConfig_XZ getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig_XZ();
            appConfig.mContext = context;
        }
        return appConfig;
    }

    public String get(String key) {
        Properties props = get();
        return (props != null) ? props.getProperty(key) : null;
    }

    public Properties get() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            // 读取files目录下的config
            // fis = activity.openFileInput(APP_CONFIG);

            // 读取app_config目录下的config
            File dirConf = mContext.getDir(APP_CONFIG_XZ, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator + APP_CONFIG_XZ);

            props.load(fis);
        } catch (Exception e) {
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return props;
    }

    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try {
            // 把config建在files目录下
            // fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

            // 把config建在(自定义)app_config的目录下
            File dirConf = mContext.getDir(APP_CONFIG_XZ, Context.MODE_PRIVATE);
            File conf = new File(dirConf, APP_CONFIG_XZ);
            fos = new FileOutputStream(conf);

            p.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    public void set(Properties ps) {
        Properties props = get();
        props.putAll(ps);
        setProps(props);
    }

    public void set(String key, String value) {
        Properties props = get();
        props.setProperty(key, value);
        setProps(props);
    }

    public void remove(String... key) {
        Properties props = get();
        for (String k : key)
            props.remove(k);
        setProps(props);
    }
}