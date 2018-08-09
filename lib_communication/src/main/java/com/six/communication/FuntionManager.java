package com.six.communication;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by admin on 2018/7/25.
 * 实现fragment与activity交互解耦
 * 适用于，多个fragment依赖一个activity的情况
 */

public class FuntionManager {
    private static FuntionManager ourInstance;
    private HashMap<String, FuntionHaveParamsHaveResult> mHaveParamsAndResult;
    private HashMap<String, FuntionNoParamsNoResult> mNoParamsAndResult;
    private HashMap<String, FuntionHaveParamsNoResult> mHaveParamsNoResult;
    private HashMap<String, FuntionNoParamsHaveResult> mNoParamsHaveResut;

    private FuntionManager() {
        //初始化 4中类型的容器
        mHaveParamsAndResult = new HashMap();
        mNoParamsAndResult = new HashMap();
        mHaveParamsNoResult = new HashMap();
        mNoParamsHaveResut = new HashMap();
    }

    public static FuntionManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new FuntionManager();
        }
        return ourInstance;
    }


    /**
     * 无参无返回值     =================================================================================
     * FuntionNoParamsNoResult 添加进容器  无参无返回值
     */
    public FuntionManager addFuntion(FuntionNoParamsNoResult funtion) {
        //funtion.mFuntionName 存入容器里的标识key ， 必须唯一，不得相同
        mNoParamsAndResult.put(funtion.mFuntionName, funtion);
        return this;
    }

    public void invoke(String funtionName) {
        if (TextUtils.isEmpty(funtionName) == true) {
            return;
        }
        if (mNoParamsAndResult != null) {
            // 从对应容器里找到 调用标识
            FuntionNoParamsNoResult f = mNoParamsAndResult.get(funtionName);
            if (f != null) {
                f.funtion();
            }
            if (f == null) {
                try {
                    throw new FuntionException("Have No Funtion->" + funtionName);
                } catch (FuntionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 无参有返回值 =================================================================================
     * FuntionNoParamsHaveResult 添加进容器
     */
    public FuntionManager addFuntion(FuntionNoParamsHaveResult funtion) {
        //funtion.mFuntionName 存入容器里的标识key ， 必须唯一，不得相同
        mNoParamsHaveResut.put(funtion.mFuntionName, funtion);
        return this;
    }

    public <Result> Result invoke(String funtionName, Class<Result> c) {
        if (TextUtils.isEmpty(funtionName) == true) {
            return null;
        }
        if (mNoParamsHaveResut != null) {
            // 从对应容器里找到 调用标识
            FuntionNoParamsHaveResult f = mNoParamsHaveResut.get(funtionName);
            if (f != null) {
                if (c != null) {
                    return c.cast(f.funtion());
                } else {
                    return (Result) f.funtion();
                }
            }
            if (f == null) {
                try {
                    throw new FuntionException("Have  No Funtion->" + funtionName);
                } catch (FuntionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 有参无返回值   =======================================================================================
     * FuntionNoParamsHaveResult 添加进容器
     */
    public FuntionManager addFuntion(FuntionHaveParamsNoResult funtion) {
        //funtion.mFuntionName 存入容器里的标识key ， 必须唯一，不得相同
        mHaveParamsNoResult.put(funtion.mFuntionName, funtion);
        return this;
    }

    public <Params> void invoke(String funtionName, Params data) {
        if (TextUtils.isEmpty(funtionName) == true) {
            return;
        }
        if (mHaveParamsNoResult != null) {
            // 从对应容器里找到 调用标识
            FuntionHaveParamsNoResult f = mHaveParamsNoResult.get(funtionName);
            if (f != null) {
                f.funtion(data);
            } else {
                try {
                    throw new FuntionException("Have  No Funtion->" + funtionName);
                } catch (FuntionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 有参有返回值   =======================================================================================
     * FuntionNoParamsHaveResult 添加进容器
     */
    public FuntionManager addFuntion(FuntionHaveParamsHaveResult funtion) {
        //funtion.mFuntionName 存入容器里的标识key ， 必须唯一，不得相同
        mHaveParamsAndResult.put(funtion.mFuntionName, funtion);
        return this;
    }

    public <Result,Params> Result  invoke(String funtionName, Params data, Class<Result> c) {
        if (TextUtils.isEmpty(funtionName) == true) {
            return null;
        }
        if (mHaveParamsAndResult != null) {
            // 从对应容器里找到 调用标识
            FuntionHaveParamsHaveResult f = mHaveParamsAndResult.get(funtionName);
            if (f != null) {
                if (c != null) {
                    return c.cast(f.funtion(data));
                } else {
                    return (Result) f.funtion(data);
                }
            } else {
                try {
                    throw new FuntionException("Have  No Funtion->" + funtionName);
                } catch (FuntionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
