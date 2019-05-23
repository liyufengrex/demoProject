package com.rex.myapplication.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2018-10-23 上午10:20.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class PDemo implements Parcelable{

    private String a;
    private int b;
    private long c;

    protected PDemo(Parcel in) {
        a = in.readString();
        b = in.readInt();
        c = in.readLong();
    }

    public static final Creator<PDemo> CREATOR = new Creator<PDemo>() {
        @Override
        public PDemo createFromParcel(Parcel in) {
            return new PDemo(in);
        }

        @Override
        public PDemo[] newArray(int size) {
            return new PDemo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(a);
        dest.writeInt(b);
        dest.writeLong(c);
    }
    /**
     *  parcelable 与 Serializable 的区别
     *
     *  parcelable 直接操作在内存，速度快
     *  serializable 操作在io，过程中会创建大量临时对象，频繁GC
     */
}
