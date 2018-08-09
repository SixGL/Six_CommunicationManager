package com.six.webview;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.six.communication.FuntionManager;


/**
 * Created by admin on 2018/7/25.
 */

public class BaseFragment extends Fragment {


    public FuntionManager mFuntionManager;
    private FragmentAndActivity fragmentAndActivity;

    // 进行manager赋值绑定
    public void setFuntionManager(FuntionManager funtionManager) {
        this.mFuntionManager = funtionManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 当activity与fragment关联时 进行Manager绑定 ，绑定的过程是一个逆向过程
        if (context instanceof FragmentAndActivity) {
            fragmentAndActivity = (FragmentAndActivity) context;
            fragmentAndActivity.setFuntionFragment(getTag());
        }else if (context instanceof TwoFragmentAndActivity){
            TwoFragmentAndActivity twofragmentAndActivity = (TwoFragmentAndActivity) context;
            twofragmentAndActivity.setFuntionFragment(getTag());
        }
    }
}
