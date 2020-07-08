package vn.edu.ntu.taidanh.bai1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThongBao extends AppCompatActivity {
    Button btnThoat;
    TextView txtInfor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongbao);
        btnThoat = findViewById(R.id.btnThoat);
        txtInfor = findViewById(R.id.txtInfor);
        Intent intent = getIntent();
        txtInfor.setText(intent.getStringExtra(MainActivity.INFOR));
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongBao.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
