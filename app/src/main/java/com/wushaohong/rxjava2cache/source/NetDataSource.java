package com.wushaohong.rxjava2cache.source;

import android.content.Context;
import android.content.SharedPreferences;
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
 * desc   : 网络数据源
 * version: 1.0
 */
public class NetDataSource {

    private Data data;
    private SharedPreferences sharedPreferences;

    public NetDataSource() {
        sharedPreferences = MyApp.getInstance().getSharedPreferences("test", Context.MODE_PRIVATE);
    }

    public Observable<Data> getData() {

        return Observable.create(new ObservableOnSubscribe<Data>() {
            @Override
            public void subscribe(ObservableEmitter<Data> emitter) throws Exception {

                data = new Data();
                data.setSource("网络");
                data.setString("我是数据");

                Log.e("my_info", "Net Data : " + data.getString());
                emitter.onNext(data);
                emitter.onComplete();
            }
        });
    }

}
