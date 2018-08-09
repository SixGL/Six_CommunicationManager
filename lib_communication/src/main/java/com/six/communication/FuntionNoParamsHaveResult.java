package com.six.communication;

/**
 * Created by admin on 2018/7/25.
 */

public abstract class FuntionNoParamsHaveResult<Result> extends Funtion {

    public FuntionNoParamsHaveResult(String mFuntionName) {
        super(mFuntionName);
    }

    public abstract Result funtion();
}
