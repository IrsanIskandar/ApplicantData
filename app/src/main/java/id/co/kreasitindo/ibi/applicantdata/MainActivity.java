package id.co.kreasitindo.ibi.applicantdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText namaLengkap;
    EditText jenisKelamin;
    EditText tempatLahir;
    EditText tanggalLahir;
    EditText alamatLengkap;
    Button saveButton;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaLengkap = findViewById(R.id.txtNamaLengkap);
        jenisKelamin = findViewById(R.id.txtJnsKelamin);
        tempatLahir = findViewById(R.id.txtTmpLahir);
        tanggalLahir = findViewById(R.id.txtTglLahir);
        alamatLengkap = findViewById(R.id.txtAlamatLeng);
        saveButton = findViewById(R.id.btnSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, DetailMember.class);
                String nama = namaLengkap.getText().toString();
                String jnskelamin = jenisKelamin.getText().toString();
                String tmplahir = tempatLahir.getText().toString();
                String tgllahir = tanggalLahir.getText().toString();
                String alamatleng = alamatLengkap.getText().toString();

                DbHelper dbHelper = new DbHelper(MainActivity.this);
                dbHelper.CreateMembers(nama, jnskelamin, tmplahir, tgllahir, alamatleng);

                Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void DetailsMembers(View view){
        intent = new Intent(MainActivity.this, DetailMember.class);
        startActivity(intent);
    }
}
