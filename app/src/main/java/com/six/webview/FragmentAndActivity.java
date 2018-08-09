package com.six.webview;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.six.communication.FuntionHaveParamsHaveResult;
import com.six.communication.FuntionHaveParamsNoResult;
import com.six.communication.FuntionManager;
import com.six.communication.FuntionNoParamsHaveResult;
import com.six.communication.FuntionNoParamsNoResult;

import java.util.ArrayList;
import java.util.List;

public class FragmentAndActivity extends AppCompatActivity {
    private String[] titles = new String[]{"无参无返回值", "无参有返回值", "有参无返回值", "有参有返回值"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_and);
        TabLayout tablayout = findViewById(R.id.tablayout);
        ViewPager viewpager = findViewById(R.id.viewpager);
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            int type = i + 1;
            fragmentList.add(TesFragment.newInstance(type, titles[i]));
        }
        TabLayoutUtils tabLayoutUtils = new TabLayoutUtils();
        tabLayoutUtils.setTabItem(this, tablayout, fragmentList, viewpager, fragmentList.size());
    }

    /**
     * fragment在创建与activity关联时 调用
     */
    public void setFuntionFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment baseFragment = (BaseFragment) fm.findFragmentByTag(tag);
        FuntionManager funtionManager = FuntionManager.getInstance();
//       调用 addFuntion是把对应的哪种通信添加到对应的map容器里
        baseFragment.setFuntionManager(funtionManager.addFuntion(new FuntionNoParamsNoResult(TesFragment.NAME_FLAG) {
            @Override
            public void funtion() {
                Toast.makeText(FragmentAndActivity.this, "成功调用无参数无返回值接口", Toast.LENGTH_SHORT).show();
            }
        }).addFuntion(new FuntionNoParamsHaveResult<String>(TesFragment.NAME_FLAG) {
            @Override
            public String funtion() {
                return "Six->:this is return data!";
            }
        }).addFuntion(new FuntionHaveParamsNoResult<String>(TesFragment.NAME_FLAG) {
            @Override
            public void funtion(String data) {
                Toast.makeText(FragmentAndActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        }).addFuntion(new FuntionHaveParamsHaveResult<String, String>(TesFragment.NAME_FLAG) {
            @Override
            public String funtion(String data) {
                return "返回传递的参数-》：" + data;
            }
        }));

    }
}
