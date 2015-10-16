package ikeike.kohkun.ikedaapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setResult(Activity.RESULT_CANCELED);
        //インテントからのぱらーめた取得
        Intent intent = this.getIntent();
        String searchword= intent.getStringExtra("searchword");
        EditText editText = (EditText) findViewById(R.id.edittext);
        editText.setText(searchword);
        // if(extras != null)searchword.getString("searchword")
        final String srachtext = editText.getText().toString();
        Toast.makeText(this, srachtext, Toast.LENGTH_LONG);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //エンターキーが押されたか判定
                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    //ソフトキーボードを閉じる
                    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                    //検索処理的な何か
// 検索ワードが空っぽなら警告
                    if (TextUtils.isEmpty(srachtext)) {
                        Toast.makeText(MapsActivity.this,
                                getString(R.string.Noword),
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        // geo:0,0で初期の位置を現在地とし、新しい場所を検索
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                                .parse("geo:0,0?q="
                                        + URLEncoder.encode(srachtext, "UTF-8")));
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MapsActivity.this,
                                getString(R.string.Noword),
                                Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    public void onMapReady(GoogleMap map) {//地図準備
        mMap = map;
        mMap.getUiSettings().setZoomControlsEnabled(true);//拡大縮小ボタン
        mMap.setMyLocationEnabled(true);//現在位置ボタン
        mMap.getUiSettings().setCompassEnabled(true);//コンパス有効
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);//地図タイプ設定
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.681382, 139.766084), 10));
    }








}
