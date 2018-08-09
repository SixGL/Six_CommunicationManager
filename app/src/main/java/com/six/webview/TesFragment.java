package com.six.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by admin on 2018/7/25.
 */

public class TesFragment extends BaseFragment {

    private static final String PARAMS_FLAG = "flag";
    private static final String TITLE = "title";
    public static String NAME_FLAG = null;
    private int anInt;

    public static TesFragment newInstance(int flag, String title) {
        TesFragment tesFragment = new TesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARAMS_FLAG, flag);
        bundle.putString(TITLE, title);
        NAME_FLAG = TesFragment.class.getName() + flag;
        tesFragment.setArguments(bundle);
        return tesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment, null);
        Button viewById = inflate.findViewById(R.id.btn_frag);
        Bundle arguments = getArguments();
        anInt = arguments.getInt(PARAMS_FLAG, 0);
        String title = arguments.getString(TITLE, "");
        viewById.setText(title);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_frag();
            }
        });
        return inflate;
    }

    public void btn_frag() {
        switch (anInt) {
            case 1:
                mFuntionManager.invoke(NAME_FLAG); //无参数无返回值
                break;
            case 2:
                String invoke = mFuntionManager.invoke(NAME_FLAG, String.class);//无参数有返回值
                Toast.makeText(getContext(), invoke, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                mFuntionManager.invoke(NAME_FLAG, "This Have Params  ，no Result");
                break;
            case 4:
                String invoke1 = mFuntionManager.invoke(NAME_FLAG, "Have Params", String.class);
                Toast.makeText(getContext(), invoke1, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), TwoFragmentAndActivity.class);
                startActivity(intent);
                break;
        }
    }

}
