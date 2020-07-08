package com.rex.myapplication.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2019-05-23 11:23 AM.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：chenxingsheng <chenxingsheng@syswin.com>
 * @Charge: 李宇峰
 */
public class RxJavaTest {

    public static void main(String[] args) {
        RxJavaTest test = new RxJavaTest();
        //        test.testCreate();
//        test.testMap();
//        test.testZip();
//        test.testConcat();
        test.testFilter();
    }

    private void testCreate() {

        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                out("onSubscribe: " + d.isDisposed());
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                out("onNext : " + integer);
                i++;
                //                if(i == 2){
                //                    mDisposable.dispose();
                //                    out("Disposable");
                //                }
            }

            @Override
            public void onError(Throwable e) {
                out("onError");
            }

            @Override
            public void onComplete() {
                out("onComplete");
            }
        });

    }

    private void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                out(s);
            }
        });
    }

    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onNext("C");
                emitter.onComplete();
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        });
    }

    /**
     * 1+1的合并事件 严格按照发送顺序 与最少事件数目保持一致
     */
    private void testZip() {
        Observable.zip(getIntegerObservable(), getStringObservable(),
                new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s) throws Exception {
                        return integer + s;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                out(s);
            }
        });
    }

    /**
     * 同类型的合并事件
     */
    private void testConcat() {
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        out(integer + "");
                    }
                });
    }


    /**
     * 无序 concatMap是有序的
     */
    private void testFlatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("i am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MICROSECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                out(s);
            }
        });
    }

    /**
     * 去重
     */
    private void testDistinct() {
        Observable.just(1, 2, 2, 2, 3).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                out(integer + "");
            }
        });
    }

    private void testFilter() {
        Observable.just(1, 20, -10, 10).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 0;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                out(integer + "");
            }
        });
    }

    private void testBuffer(){
        Observable.just(1,2,3,4,5)
                .buffer(3,2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        out("buffer size = "+integers.size());

                    }
                });
    }

    private void out(String msg) {
        System.out.println(msg);
    }

}
