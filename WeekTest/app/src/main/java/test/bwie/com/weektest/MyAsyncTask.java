package test.bwie.com.weektest;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @ Description:
 * @ Date:2017/4/7
 * @ Author:刘刚
 */

public class MyAsyncTask extends AsyncTask<String,Integer,List<GsonBean.ListBean>>{

   private ListView lv;
    private Context mContext;
    private String s;

    public MyAsyncTask(String s, Context context, ListView lv) {
        this.s = s;
        mContext = context;
        this.lv = lv;
    }

    @Override
    protected List<GsonBean.ListBean> doInBackground(String... params) {
        String s = doPost(params[0]);
        if (s!=null){
            Gson gson = new Gson();
            GsonBean gsonBean = gson.fromJson(s, GsonBean.class);
            if (gsonBean!=null){
                List<GsonBean.ListBean> list = gsonBean.getList();
                return  list;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(final List<GsonBean.ListBean> listBeen) {
        super.onPostExecute(listBeen);
        final MyAdapter adapter = new MyAdapter(mContext, listBeen);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, listBeen.get(position).getId()+"", Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listBeen.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private  String doPost(String uri){
        try {
            URL url = new URL(uri);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setConnectTimeout(5000);
            if (con.getResponseCode()==200){
                InputStream ins = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ins, "utf-8"));
                String line="";
                StringBuilder sb = new StringBuilder();
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
