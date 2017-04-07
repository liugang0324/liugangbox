package test.bwie.com.weektest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @ Description:
 * @ Date:2017/4/7
 * @ Author:刘刚
 */

public class MyAdapter extends BaseAdapter {
    private List<GsonBean.ListBean>mList;
    private Context mContext;

    public MyAdapter(Context context, List<GsonBean.ListBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(mContext,R.layout.item,null);
            holder=new ViewHolder();
            holder.text1= (TextView) convertView.findViewById(R.id.textView2);
            holder.text2= (TextView) convertView.findViewById(R.id.textView3);
           convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.text1.setText(mList.get(position).getSite_name());
        holder.text2.setText(mList.get(position).getAddress());
        return convertView;
    }

    class ViewHolder{
        TextView text1;
        TextView text2;
    }
}
