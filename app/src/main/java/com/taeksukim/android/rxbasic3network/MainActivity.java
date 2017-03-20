package com.taeksukim.android.rxbasic3network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        Observable<String> naverObservable =
                Observable.create(emitter ->{
                emitter.onNext(Remote.getUrlByGet("naver.com"));

                }
                );

        Observable<String> cnetObservable =
                Observable.create(emitter ->{
                            emitter.onNext(Remote.getUrlByGet("www.cnet.co.kr"));

                        }
                );

        cnetObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> textView1.setText(result)
                );

        naverObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> textView2.setText(result)
                );
    }
}
