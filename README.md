# Six_CommunicationManager
Activity和Fragment通信，进行耦合分解，降低耦合
### gradle依赖
    `  implementation 'com.github.SixGL:Six_CommunicationManager:V1.1.0'`
### 使用方式
#### Step 1
    
> 首先在自己的BaseFragment声明一个方法用来初始化manager对象

`public FuntionManager mFuntionManager;
   public void setFuntionManager(FuntionManager funtionManager) {
        this.mFuntionManager = funtionManager;
    }`   

> 在BaseFragmen里重写onAttach

       @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 当activity与fragment关联时 进行Manager绑定 ，绑定的过程是一个逆向过程
        if (context instanceof FragmentAndActivity) {//这里需要判断你当前的fragment是在哪一个Activity下进行通信
            A a = (A) context;
            a.setFuntionFragment(getTag());
        }else if (context instanceof TwoFragmentAndActivity){
            B b = (B) context;
            b.setFuntionFragment(getTag());
        }
    }

#### Step 2
> 在对应的Activity下声明一个方法是供Step 1 onAttach里调用的

      /**
     * fragment在创建与activity关联时 调用
     * FuntionNoParamsNoResult 无参无返回值
     * FuntionNoParamsHaveResult 无参有返回值
     * FuntionHaveParamsNoResult  有参无返回值
     * FuntionHaveParamsHaveResult  有参有返回值
     * TesFragment.NAME_FLAG 唯一标识(必须保证唯一)，要跟调用时保持一致（Manager 就是通过这个标识来进行通信的）
     */
    public void setFuntionFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment baseFragment = (BaseFragment) fm.findFragmentByTag(tag);
        FuntionManager funtionManager = FuntionManager.getInstance();
        //    调用 addFuntion是把对应的哪种通信添加到对应的map容器里
        baseFragment.setFuntionManager(funtionManager.addFuntion(new FuntionNoParamsNoResult(TesFragment.NAME_FLAG) {
            @Override
            public void funtion() {
                // do Something
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

> Step 3  调用
    
      mFuntionManager.invoke(NAME_FLAG); //无参数无返回值

      String invoke = mFuntionManager.invoke(NAME_FLAG, String.class);//无参数有返回值
      Toast.makeText(getContext(), invoke, Toast.LENGTH_SHORT).show();`

	  mFuntionManager.invoke(NAME_FLAG, "This Have Params  ，no Result"); //有参数无返回值

      String invoke1 = mFuntionManager.invoke(NAME_FLAG, "Have Params", String.class);// 有参数有返回值
      Toast.makeText(getContext(), invoke1, Toast.LENGTH_SHORT).show();

