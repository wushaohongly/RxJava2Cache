package com.wushaohong.rxjava2cache.source;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.wushaohong.rxjava2cache.MyApp;
import com.wushaohong.rxjava2cache.data.Data;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * author : wushaohong
 * e-mail : 576218811@qq.com
 * date   : 2019/08/26
 * desc   : 磁盘数据源
 * version: 1.0
 */
public class DiskDataSource {

    private Data data;
    private SharedPreferences sharedPreferences;

    public DiskDataSource() {
        sharedPreferences = MyApp.getInstance().getSharedPreferences("test", Context.MODE_PRIVATE);
    }

    public Observable<Data> getData() {

        return Observable.create(new ObservableOnSubscribe<Data>() {
            @Override
            public void subscribe(ObservableEmitter<Data> emitter) throws Exception {

                String string = sharedPreferences.getString("key", "");
                if (!TextUtils.isEmpty(string)) {
                    data = new Data();
                    data.setSource("磁盘");
                    data.setString(string);
                    Log.e("my_info", "Disk Cache : " + data.getString());
                    emitter.onNext(data);
                    saveData(data);
                }

                emitter.onComplete();
            }
        });
    }

    public void saveData(Data data) {
        String string = data.getString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", string);
        editor.apply();
    }

    public void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
