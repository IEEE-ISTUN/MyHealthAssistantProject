package com.zeyneptekin.myhealthassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.firebase.Timestamp;

import java.util.Map;
import java.util.HashMap;

public class saglikGecmisim extends AppCompatActivity {

    private TableLayout hastaliklarimTable;
    private EditText editText1;
    private EditText editTextAlerji;
    private EditText editTextAmeliyatAdi;
    private EditText editTextAmeliyatTarihi;
    private TableLayout alerjilerimTable;
    private TableLayout ameliyatlarimTable;
    private Button addRowButtonhastaliklarim;
    private Button addRowButtonameliyatlarim;
    private Button addRowButtonalerjilerim;
    private Button deleteButtonHastalik;
    private FirestoreHelper firestoreHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saglik_gecmisim);

        // FirestoreHelper sınıfını başlat
        FirestoreHelper db = new FirestoreHelper(this);

        addRowButtonhastaliklarim = findViewById(R.id.addRowButtonHastalik);
        deleteButtonHastalik = findViewById(R.id.deleteButtonHastalik);
        editText1 = findViewById(R.id.editText1);
        hastaliklarimTable = findViewById(R.id.hastaliklarim_table);
        alerjilerimTable = findViewById(R.id.alerjilerim_table);
        ameliyatlarimTable = findViewById(R.id.ameliyatlarim_table);

        addRowButtonhastaliklarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Yeni bir Firestore belgesi oluştur
                Map<String, Object> hastalikIsmi = new HashMap<>();

                String hastalikBilgisi = ""; // Boş bir String oluşturun

                hastalikBilgisi = editText1.getText().toString();

                db.addHastalikBilgisi(hastalikBilgisi);

                // Yeni bir satır oluştur
                TableRow newRow = new TableRow(saglikGecmisim.this);

                // EditText'i içeren yeni bir hücre oluştur
                EditText hastalikGirisText = new EditText(saglikGecmisim.this);
                hastalikGirisText.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 1f));
                hastalikGirisText.setHint("Hastalık ismi");
                hastalikGirisText.setTextSize(14); // Yazı boyutunu 14sp olarak ayarla
                hastalikGirisText.setGravity(Gravity.CENTER_HORIZONTAL);
                hastalikGirisText.setGravity(Gravity.CENTER); // Yazıyı ortala

                // Hücreyi satıra ekle
                newRow.addView(hastalikGirisText);

                // Satırı tabloya ekle
                hastaliklarimTable.addView(newRow);

                editText1 = hastalikGirisText;


                // SİL butonuna tıklandığında en son eklenen hastalık satırını kaldır
                Button deleteButtonHastalik = findViewById(R.id.deleteButtonHastalik);
                deleteButtonHastalik.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeLastRow(hastaliklarimTable);
                    }
                });
            }
        });

        alerjilerimTable = findViewById(R.id.alerjilerim_table);
        addRowButtonalerjilerim = findViewById(R.id.addRowButtonAlerji);
        editTextAlerji = findViewById(R.id.editTextAlerji);

        addRowButtonalerjilerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Yeni bir Firestore belgesi oluştur
                Map<String, Object> alerjiIsmi = new HashMap<>();

                String alerjiBilgisi = ""; // Boş bir String oluşturun

                alerjiBilgisi = editTextAlerji.getText().toString();

                db.addAlerjiBilgisi(alerjiBilgisi);

                // Yeni bir satır oluştur
                TableRow newRow = new TableRow(saglikGecmisim.this);

                // EditText'i içeren yeni bir hücre oluştur
                EditText alerjiGirisText = new EditText(saglikGecmisim.this);
                alerjiGirisText.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 1f));
                alerjiGirisText.setHint("Alerji ismi");
                alerjiGirisText.setTextSize(14); // Yazı boyutunu 14sp olarak ayarla
                alerjiGirisText.setGravity(Gravity.CENTER_HORIZONTAL);
                alerjiGirisText.setGravity(Gravity.CENTER); // Yazıyı ortala

                // Hücreyi satıra ekle
                newRow.addView(alerjiGirisText);

                // Satırı tabloya ekle
                alerjilerimTable.addView(newRow);

                editTextAlerji = alerjiGirisText;


                // SİL butonuna tıklandığında en son eklenen hastalık satırını kaldır
                Button deleteButtonAlerji = findViewById(R.id.deleteButtonAlerji);
                deleteButtonAlerji.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeLastRow(alerjilerimTable);
                    }
                });
            }
        });

        ameliyatlarimTable = findViewById(R.id.ameliyatlarim_table);
        editTextAmeliyatAdi = findViewById(R.id.editTextAmeliyatAdi);
        editTextAmeliyatTarihi = findViewById(R.id.editTextAmeliyatTarihi);
        addRowButtonameliyatlarim = findViewById(R.id.addRowButtonAmeliyat);

        addRowButtonameliyatlarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Yeni bir Firestore belgesi oluştur
                Map<String, Object> ameliyatIsmi = new HashMap<>();

                String ameliyatBilgisi = ""; // Boş bir String oluşturun

                ameliyatBilgisi = editTextAmeliyatAdi.getText().toString();

                String ameliyatTarihi = ""; // Boş bir String oluşturun

                ameliyatTarihi = editTextAmeliyatTarihi.getText().toString();

                db.addAmeliyatBilgisi(ameliyatBilgisi, ameliyatTarihi);


                // Yeni bir satır oluştur
                TableRow newRow = new TableRow(saglikGecmisim.this);

                // Ameliyat ismi EditText'i içeren yeni bir hücre oluştur
                EditText newEditTextAmeliyatAdi = new EditText(saglikGecmisim.this);
                newEditTextAmeliyatAdi.setLayoutParams(new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT, 1f));
                newEditTextAmeliyatAdi.setHint("Ameliyat İsmi");
                newEditTextAmeliyatAdi.setTextSize(14); // Yazı boyutunu 14sp olarak ayarla
                newEditTextAmeliyatAdi.setGravity(Gravity.CENTER_HORIZONTAL);

                // Ameliyat tarihi EditText'i içeren yeni bir hücre oluştur
                EditText newEditTextAmeliyatTarihi = new EditText(saglikGecmisim.this);
                newEditTextAmeliyatTarihi.setLayoutParams(new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT, 1f));
                newEditTextAmeliyatTarihi.setHint("Ameliyat Tarihi");
                newEditTextAmeliyatTarihi.setTextSize(14); // Yazı boyutunu 14sp olarak ayarla
                newEditTextAmeliyatTarihi.setGravity(Gravity.CENTER_HORIZONTAL);

                // Hücreleri satıra ekle
                newRow.addView(newEditTextAmeliyatAdi);
                newRow.addView(newEditTextAmeliyatTarihi);

                // Satırı tabloya ekle
                ameliyatlarimTable.addView(newRow);

                editTextAmeliyatAdi = newEditTextAmeliyatAdi;
                editTextAmeliyatTarihi = newEditTextAmeliyatTarihi;
            }
        });

        // SİL butonuna tıklandığında en son eklenen hastalık satırını kaldır
        Button deleteButtonAmeliyat = findViewById(R.id.deleteButtonAmeliyat);
        deleteButtonAmeliyat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLastRow(ameliyatlarimTable);
            }
        });
    }

    private void removeLastRow(TableLayout tableLayout) {
        int rowCount = tableLayout.getChildCount();
        if (rowCount > 0) {
            tableLayout.removeViewAt(rowCount - 1);
        }
    }
}
