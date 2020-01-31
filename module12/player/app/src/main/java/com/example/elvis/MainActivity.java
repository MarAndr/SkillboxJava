package com.example.elvis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    MyPlayer myPlayer = new MyPlayer();
    Button btnStart;
    TextView tvElvis;
    AssetManager assetManager;
    String[] songsName;
    AssetFileDescriptor assetFileDescriptor_0_falling;
    AssetFileDescriptor assetFileDescriptor_1_hotel;
    AssetFileDescriptor assetFileDescriptor_2_jaihouse;
    AssetFileDescriptor assetFileDescriptor_3_tutti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnPlay);
        tvElvis = findViewById(R.id.tvElvis);

        assetManager = getAssets();
        songsName = getFileNames("songs");


        try {
            assetFileDescriptor_0_falling = getApplicationContext().getAssets().openFd("songs/" + songsName[0]);
            MediaPlayer mediaPlayerFalling = new MediaPlayer();
            mediaPlayerFalling.setDataSource(assetFileDescriptor_0_falling.getFileDescriptor(), assetFileDescriptor_0_falling.getStartOffset(), assetFileDescriptor_0_falling.getLength());
            myPlayer.addMelody(mediaPlayerFalling);
            mediaPlayerFalling.prepare();

            assetFileDescriptor_1_hotel = getApplicationContext().getAssets().openFd("songs/" + songsName[1]);
            MediaPlayer mediaPlayerHotel = new MediaPlayer();
            mediaPlayerHotel.setDataSource(assetFileDescriptor_1_hotel.getFileDescriptor(), assetFileDescriptor_1_hotel.getStartOffset(), assetFileDescriptor_1_hotel.getLength());
            myPlayer.addMelody(mediaPlayerHotel);
            mediaPlayerHotel.prepare();

            assetFileDescriptor_2_jaihouse = getApplicationContext().getAssets().openFd("songs/" + songsName[2]);
            MediaPlayer mediaPlayerJailhouse = new MediaPlayer();
            mediaPlayerJailhouse.setDataSource(assetFileDescriptor_2_jaihouse.getFileDescriptor(), assetFileDescriptor_2_jaihouse.getStartOffset(), assetFileDescriptor_2_jaihouse.getLength());
            myPlayer.addMelody(mediaPlayerJailhouse);
            mediaPlayerJailhouse.prepare();

            assetFileDescriptor_3_tutti = getApplicationContext().getAssets().openFd("songs/" + songsName[3]);
            MediaPlayer mediaPlayerTutti = new MediaPlayer();
            mediaPlayerTutti.setDataSource(assetFileDescriptor_3_tutti.getFileDescriptor(), assetFileDescriptor_3_tutti.getStartOffset(), assetFileDescriptor_3_tutti.getLength());
            myPlayer.addMelody(mediaPlayerTutti);
            mediaPlayerTutti.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String[] getFileNames(String path) {

        String[] list = null;
        try {

            list = assetManager.list(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void play(View view) {

        myPlayer.startMelody();
        if (btnStart.getText().equals("Play")) {
            btnStart.setText("Pause");
            myPlayer.startMelody();
        } else {
            btnStart.setText("Play");
            myPlayer.pauseMelody();
        }
    }


    public void next(View view) {

        myPlayer.nextMelody();
        btnStart.setText("Pause");
    }

    public void onChoose(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Выбор композиции");

        builder.setItems(songsName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myPlayer.startMelody(which);
                btnStart.setText("Pause");
            }
        });

        builder.setCancelable(true);
        AlertDialog alert = builder.create();
        alert.show();
    }


}






