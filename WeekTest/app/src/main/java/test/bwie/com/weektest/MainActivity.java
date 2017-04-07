package test.bwie.com.weektest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private List<GsonBean.ListBean>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        MyAsyncTask myAsyncTask = new MyAsyncTask("http://result.eolinker.com/t4uA2Q52c14f04fa8be3ec1721297782c021407c8b04922?uri=site",MainActivity.this,mLv);
        myAsyncTask.execute("http://result.eolinker.com/t4uA2Q52c14f04fa8be3ec1721297782c021407c8b04922?uri=site");


    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.lv);
    }
}
