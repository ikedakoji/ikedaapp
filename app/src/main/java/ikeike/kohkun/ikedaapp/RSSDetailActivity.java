package ikeike.kohkun.ikedaapp;

/**
 * Created by ikedakoj on 2015/10/30.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RSSDetailActivity extends Activity {
    private TextView mTitle;
    private TextView mDescr;
    private String mLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();

        String title = intent.getStringExtra("TITLE");
        mTitle = (TextView) findViewById(R.id.item_detail_title);
        mTitle.setText(title);
        String descr = intent.getStringExtra("DESCRIPTION");
        mDescr = (TextView) findViewById(R.id.item_detail_descr);
        mDescr.setText(descr);
    }
}
