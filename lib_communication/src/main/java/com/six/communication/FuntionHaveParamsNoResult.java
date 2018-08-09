package com.six.communication;

/**
 * Created by admin on 2018/7/25.
 */

public abstract class FuntionHaveParamsNoResult<Params> extends Funtion {

    public FuntionHaveParamsNoResult(String mFuntionName) {
        super(mFuntionName);
    }

    public abstract void funtion(Params data);
}
