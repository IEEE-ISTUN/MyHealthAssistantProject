package com.zeyneptekin.myhealthassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zeyneptekin.myhealthassistant.ui.theme.KaloriKahvalti;

public class FoodActivity extends AppCompatActivity {

    private CardView SaglikliTarif;
    private CardView VeganTarif;
    private CardView GlutensizTarif;
    private CardView Kahvalti;
    private CardView OgleYemegi;
    private CardView Atistirmalik;
    private CardView AksamYemegi;

    //private LinearLayout KisiselAsistan;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        // LinearLayout'leri bul ve tıklama olayını ayarla
        SaglikliTarif = findViewById(R.id.SaglikliLayout);
        SaglikliTarif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, saglikliTarifler.class);
                startActivity(intent);
            }
        });


        VeganTarif = findViewById(R.id.veganLayout);
        VeganTarif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, veganTarifler.class);
                startActivity(intent);
            }
        });

        GlutensizTarif = findViewById(R.id.glutensizLayout);
        GlutensizTarif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, glutensizTarifler.class);
                startActivity(intent);
            }
        });
        /*
        KisiselAsistan = findViewById(R.id.AsistanLayout);
        KisiselAsistan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this,KisiselAistan.class);
                startActivity(intent);
            }
        });
        */


        Kahvalti = findViewById(R.id.kahvaltiCard);
        Kahvalti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, KaloriKahvalti.class);
                startActivity(intent);
            }
        });



        OgleYemegi = findViewById(R.id.ogleCard);
        OgleYemegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, KaloriOgle.class);
                startActivity(intent);
            }
        });


        Atistirmalik = findViewById(R.id.atistirmalikCard);
        Atistirmalik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, KaloriAtistirmalik.class);
                startActivity(intent);
            }
        });

        AksamYemegi = findViewById(R.id.aksamCard);
        AksamYemegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, KaloriAksam.class);
                startActivity(intent);
            }
        });
    }

}