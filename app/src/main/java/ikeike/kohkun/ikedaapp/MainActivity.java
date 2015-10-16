package ikeike.kohkun.ikedaapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 検索するワードをを取得
                EditText etSearchWord = (EditText) findViewById(R.id.intputSearchWord);
                String searchWord = etSearchWord.getText().toString();


                try {
                    // geo:0,0で初期の位置を現在地とし、新しい場所を検索
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    intent.putExtra("searchWord", searchWord.toString());
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this,
                            getString(R.string.activate_higher_level),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.linkYoutube).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 検索するワードを取得
                        EditText youtubeSearchWord = (EditText) findViewById(R.id.intputyoutubeSearchWord);
                        String searchWord = youtubeSearchWord.getText().toString();

                        // 検索ワードが空っぽなら警告
                        if (TextUtils.isEmpty(searchWord)) {
                            Toast.makeText(
                                    MainActivity.this,
                                    getString(R.string.label_input_empty),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }


                        Intent intent = new Intent(Intent.ACTION_SEARCH);
                        intent.setPackage("com.google.android.youtube");
                        // 検索ワードを設定
                        intent.putExtra("query", searchWord);
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                        }
                    }
                });

        findViewById(R.id.music).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), List.class);

                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                        }
                    }
                });
    }


}




