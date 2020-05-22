package com.phz.iconcenteredittextdemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.phz.iconcenteredittextdemo.MyApplication;
import com.phz.iconcenteredittextdemo.R;
import com.phz.iconcenteredittextdemo.adatper.MyAdapter;
import com.phz.iconcenteredittextdemo.bean.MachineInfo;
import com.phz.iconcenteredittextdemo.bean.MachineInfoDao;
import com.phz.iconcenteredittextdemo.util.KeyboardUtil;
import com.phz.iconcenteredittextdemo.view.IconCenterEditText;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author haizhuo
 */
public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private IconCenterEditText iconCenterEditText;
    private RecyclerView mRecyclerView;

    private MachineInfoDao machineInfoDao;

    private String[] name;
    private String[] address;


    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        mRecyclerView=findViewById(R.id.recycleView);
        iconCenterEditText=findViewById(R.id.icon_center_et);
        initData();
    }

    private void initData(){
        name=getResources().getStringArray(R.array.name);
        address=getResources().getStringArray(R.array.address);
        //模拟网络请求，填充机器数据列表
        machineInfoDao= MyApplication.getInstance().getDaoSession().getMachineInfoDao();
        machineInfoDao.deleteAll();
        List<MachineInfo> list=new ArrayList<>();
        for (int i=0;i<name.length;i++){
            list.add(new MachineInfo(name[i],address[i]));
        }
        machineInfoDao.insertInTx(list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(R.layout.item_info, list);
        mRecyclerView.setAdapter(adapter);

        iconCenterEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    KeyboardUtil.hideKeyBoard(MainActivity.this,v);
                }
                return false;
            }
        });

        iconCenterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                blurryInfo(s.toString());
            }
        });
    }

    /**
     * 模糊查询
     * @param like 字段
     */
    private void blurryInfo(String like) {
        List<MachineInfo> list=new ArrayList<>();
        if (TextUtils.isEmpty(like)){
            list=machineInfoDao.loadAll();
        }else {
            list=machineInfoDao.queryBuilder().where(MachineInfoDao.Properties.Address.like("%"+like+"%")).list();
        }
        adapter.setNewData(list);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            View view=this.getCurrentFocus();
            if (view!=null&&view instanceof IconCenterEditText){
                int[] l = {0, 0};
                view.getLocationInWindow(l);
                int left = l[0], top = l[1], bottom = top + view.getHeight(), right = left
                        + view.getWidth();
                if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                    // 触摸的是IconCenterEditText控件，忽略它
                } else {
                    KeyboardUtil.closeKeyboard(this);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
