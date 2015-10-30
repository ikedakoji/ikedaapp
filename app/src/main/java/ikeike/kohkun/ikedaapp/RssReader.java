package ikeike.kohkun.ikedaapp;

/**
 * Created by ikedakoj on 2015/10/30.
 */
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class RssReader extends ListActivity implements NetReceiver.Observer {

    private static final String RSS_FEED_URL = "http://itpro.nikkeibp.co.jp/rss/ITpro.rdf";
    public static final int MENU_ITEM_RELOAD = Menu.FIRST;
    private RssListAdapter mAdapter;
    private ArrayList<Item> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_reader);

        mItems = new ArrayList<Item>();
        mAdapter = new RssListAdapter(this, mItems);

        RssParser task = new RssParser(this, mAdapter);
        task.execute(RSS_FEED_URL);

        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        NetReceiver receiver = new NetReceiver(this);
        registerReceiver(receiver, filter);
    }

    // リストの項目を選択した時の処理
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Item item = mItems.get(position);
        Intent intent = new Intent(this, RSSDetailActivity.class);
        intent.putExtra("TITLE", item.getTitle());
        intent.putExtra("DESCRIPTION", item.getDescription());
        startActivity(intent);
    }

    // MENUボタンを押したときの処理
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_ITEM_RELOAD, 0, "更新");
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 更新
            case MENU_ITEM_RELOAD:
                // アダプタを初期化し、タスクを起動する
                mItems = new ArrayList();
                mAdapter = new RssListAdapter(this, mItems);
                // タスクはその都度生成する
                RssParser task = new RssParser(this, mAdapter);
                task.execute(RSS_FEED_URL);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        //unregisterReceiver(receiver);//エラーが出るのでコメントアウト
        super.onDestroy();
    }

    /**通知機能**/
    private void sendNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //Intent notificationIntent = new Intent(this, RssReaderActivity.class);//起動するアクティビティ
        //PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification mNotification = new NotificationCompat.Builder(this)
               .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("NoInterNet")
                .setContentTitle("ネットに繋がっていませんよ")
                .setContentText("ネットに繋がってないので記事が更新できません")
                        //      .setContentIntent(contentIntent)
                .build();
        notificationManager.notify(1, mNotification);
    }

    @Override
    public void onConnect() {
        //ネットワークに接続した時の処理//
    }

    @Override
    public void onDisconnect() {
        //ネットワークが切断された時の処理
        sendNotification();
    }

}
