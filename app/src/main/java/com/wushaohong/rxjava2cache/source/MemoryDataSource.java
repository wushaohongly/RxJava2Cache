package com.wushaohong.rxjava2cache.source;

import android.util.Log;

import com.wushaohong.rxjava2cache.data.Data;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * author : wushaohong
 * e-mail : 576218811@qq.com
 * date   : 2019/08/26
 * desc   : 内存数据源
 * version: 1.0
 */
public class MemoryDataSource {

    private Data data;

    public Observable<Data> getData() {

        return Observable.create(new ObservableOnSubscribe<Data>() {
            @Override
            public void subscribe(ObservableEmitter<Data> emitter) throws Exception {
                if (data != null) {
                    data.setSource("内存");
                    Log.e("my_info", "Memory Cache : " + data.getString());
                    emitter.onNext(data);
                }
                emitter.onComplete();
            }
        });
    }

    public void saveData(Data data) {
        this.data = data;
    }

    public void clearData() {
        this.data = null;
    }
}
