package com.sursun.houck.im;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by houck on 2015/8/12.
 */
public class HKMessage implements Parcelable, Cloneable{

    private static final String TAG = HKMessage.class.getSimpleName();

    protected HKMessage.Type type;
    protected HKMessage.Direction direction;

    private String from;
    private String to;
    private long msgTime;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static enum Direction {
        SEND,
        RECEIVE,
        DRAFT;

        private Direction() {
        }
    }

    public static enum Type {
        NONE,
        TXT,
        VOICE,
        VIDEO,
        IMAGE,
        LOCATION,
        FILE;

        private Type() {
        }
    }
}
