package com.wushaohong.rxjava2cache;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.wushaohong.rxjava2cache.data.Data;
import com.wushaohong.rxjava2cache.source.DataSource;
import com.wushaohong.rxjava2cache.source.DiskDataSource;
import com.wushaohong.rxjava2cache.source.MemoryDataSource;
import com.wushaohong.rxjava2cache.source.NetDataSource;

public class MainActivity extends AppCompatActivity {

    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new DataSource(new MemoryDataSource(), new DiskDataSource(),
                new NetDataSource());
    }

    @SuppressLint("CheckResult")
    public void getData(View view) {

        Log.e("my_info", "点击获取数据 ---loading--- ");

        Observable<Data> memory = dataSource.getDataFromMemory();
        Observable<Data> disk = dataSource.getDataFromDisk();
        Observable<Data> net = dataSource.getDataFromNetWord();

        Observable.concat(memory, disk, net)
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        Log.e("my_info", "onSubscribe");
                    }

                    @Override
                    public void onNext(Data data) {
//                        Log.e("my_info", "onNext" + data.getString());
                        Log.e("my_info", "获取到的数据 : " + data.getString());
                        Toast.makeText(MainActivity.this, data.getSource() + " : " + data.getString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        Log.e("my_info", "onError");
                    }

                    @Override
                    public void onComplete() {
//                        Log.e("my_info", "onComplete");
                    }
                });

    }

    public void clearData(View view) {
        dataSource.clear();
        Toast.makeText(MainActivity.this, "清理缓存完成", Toast.LENGTH_SHORT).show();
    }
}
