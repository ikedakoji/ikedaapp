package ikeike.kohkun.ikedaapp;

/**
 * Created by ikedakoj on 2015/10/30.
 */
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import java.util.List;

public class RssListAdapter extends ArrayAdapter<Item> {
    private LayoutInflater mInflater;
    private TextView mTitle;
    private TextView mDescr;
    private String mLink;

    public RssListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // ビューを生成
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_row, null);
        }

        // 現在参照しているリストの位置からItemを取得する
        Item item = this.getItem(position);
        if (item != null) {
            //Itemからデータを取り出し
            String title = item.getTitle().toString();
            mTitle = (TextView) view.findViewById(R.id.item_title);
            mTitle.setText(title);
            String descr = item.getDescription().toString();
            mDescr = (TextView) view.findViewById(R.id.item_descr);
            mDescr.setText(descr);
            String link;
        }
        return view;
    }
}
