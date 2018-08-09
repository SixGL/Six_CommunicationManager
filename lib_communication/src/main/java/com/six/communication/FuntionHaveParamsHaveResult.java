package com.six.communication;

/**
 * Created by admin on 2018/7/25.
 */

public abstract class FuntionHaveParamsHaveResult<Result, Params> extends Funtion {

    public FuntionHaveParamsHaveResult(String mFuntionName) {
        super(mFuntionName);
    }

    public abstract Result funtion(Params data);
}
