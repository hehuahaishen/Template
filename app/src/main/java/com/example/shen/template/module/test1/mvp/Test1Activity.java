package com.example.shen.template.module.test1.mvp;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.common.util.FontSetting;
import com.example.common.util.ToastUtils;
import com.example.shen.template.R;
import com.example.shen.template.mvpbase.MVPActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:  shen
 * date:    2019/2/11
 */
public class Test1Activity extends MVPActivity<Test1PresenterImpl> implements Test1View {
    @BindView(R.id.tv_message_Test1Activity)
    TextView mTvMessage;

    @Override
    protected Test1PresenterImpl createPresenter() {
        return new Test1PresenterImpl(this);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_test1;
    }

    @Override
    protected void initDataEvent() {

    }


    @Override
    public void showVersion(String s) {
        mTvMessage.setText(s);
    }

    @OnClick(R.id.tv_loading_Test1Activity)
    public void onViewClicked() {
        mPresenter.versionUpdated();
    }

    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //菜单选项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {

            //字体工具类使用assets文件夹类的字体文件
            case R.id.one:
                FontSetting.setFont(this, mTvMessage, "fonts/MengYuanti.ttf");
                break;

            case R.id.two:
                ToastUtils.showToast(true);
                break;

            case R.id.three:
                ToastUtils.showToast(10);
                break;

            case R.id.four:
                ToastUtils.showToast(10.22);
                break;

            case R.id.five:
                ToastUtils.showToast(-10.22);
                break;

            case R.id.six:
                ToastUtils.showToast("one");
                break;

            case R.id.seven:
                break;

            case R.id.eight:
                break;

        }
        return true;
    }
}
