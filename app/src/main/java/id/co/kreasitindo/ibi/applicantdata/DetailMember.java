package id.co.kreasitindo.ibi.applicantdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailMember extends AppCompatActivity {
    ListView listOfMember;
    Button createNew;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_member);
        createNew = findViewById(R.id.btnCreate);

        //intent = getIntent();
        DbHelper dbHelpers = new DbHelper(DetailMember.this);
        ArrayList<HashMap<String, String>> memberMap = dbHelpers.GetMembers();
        listOfMember = findViewById(R.id.MemberList);
        ListAdapter adapter = new SimpleAdapter(DetailMember.this, memberMap, R.layout.activity_list_member_row,
                new String[]{"NamaLengkap", "JenisKelamin", "TempatLahir", "TanggalLahir", "AlamatLengkap"},
                new int[]{R.id.lblNamaLengkap, R.id.lblJeniKelamin, R.id.lblTempatLahir, R.id.lblTanggalLahir, R.id.lblAlamatLengkap});
        listOfMember.setAdapter(adapter);

        listOfMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            intent = new Intent(DetailMember.this, MainActivity.class);
            //MembersList membersList = (ListView)listOfMember.getItemAtPosition(position);

            //String fullName = membersList.getFullname();
            }
        });
    }

    public void CreateNew(View view){
        intent = new Intent(DetailMember.this, MainActivity.class);
        startActivity(intent);
    }
}
