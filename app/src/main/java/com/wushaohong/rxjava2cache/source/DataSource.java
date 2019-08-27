package com.wushaohong.rxjava2cache.source;

import com.wushaohong.rxjava2cache.data.Data;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * author : wushaohong
 * e-mail : 576218811@qq.com
 * date   : 2019/08/26
 * desc   : 数据源，统一管理数据
 * version: 1.0
 */
public class DataSource {

    private MemoryDataSource memoryDataSource;
    private DiskDataSource diskDataSource;
    private NetDataSource netDataSource;

    public DataSource(MemoryDataSource memoryDataSource, DiskDataSource diskDataSource,
                      NetDataSource netDataSource) {
        this.memoryDataSource = memoryDataSource;
        this.diskDataSource = diskDataSource;
        this.netDataSource = netDataSource;
    }

    public Observable<Data> getDataFromMemory() {
        return memoryDataSource.getData();
    }

    public Observable<Data> getDataFromDisk() {
        return diskDataSource.getData().doOnNext(new Consumer<Data>() {
            @Override
            public void accept(Data data) throws Exception {
                memoryDataSource.saveData(data);
            }
        });
    }

    public Observable<Data> getDataFromNetWord() {
        return netDataSource.getData().doOnNext(new Consumer<Data>() {
            @Override
            public void accept(Data data) throws Exception {
                diskDataSource.saveData(data);
                memoryDataSource.saveData(data);
            }
        });
    }

    public void clear() {
        memoryDataSource.clearData();
        diskDataSource.clearData();
    }

}
