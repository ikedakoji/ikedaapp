package ikeike.kohkun.ikedaapp;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class List extends FragmentActivity
        implements SimpleCursorAdapter.ViewBinder {

            private ListView mListSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListSongs = (ListView) findViewById(R.id.listSongs);

        loadSongs();
    }

            private void loadSongs() {
                Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, null, null, null);


                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.activity_list_list, cursor, new String[] { MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION }, new int[] {
                        R.id.textTitle, R.id.textArtist, R.id.textTime }, 0);//曲名、アーティスト、タイトル

                adapter.setViewBinder(this);

                mListSongs.setAdapter(adapter);
            }

            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                int index = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
                if (index == columnIndex) {
                    TextView textTime = (TextView) view;
                    long durationMs = cursor.getLong(columnIndex);
                    long duration = durationMs / 1000;
                    long h = duration / 3600;
                    long m = (duration - h * 3600) / 60;
                    long s = duration - (h * 3600 + m * 60);
                    textTime.setText(String.format("%02d:%02d", m, s));
                    return true;
                }
                return false;
            }




}



