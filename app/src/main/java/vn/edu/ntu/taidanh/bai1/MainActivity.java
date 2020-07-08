package vn.edu.ntu.taidanh.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{
    EditText edtTen, edtNgaySinh, edtSDT,edtDiaChi;
    ImageView imgDate;
    RadioButton rbTienMat, rbNganHang, rbDienTu;
    Button btnDangKy;
    RadioGroup rdgThanhToan;
    //khai báo dành cho spinner
    String [] arrayDichVu;
    ArrayAdapter<String> adapterDichVu;
    Spinner listDichVu;
    public static final String INFOR = "Infor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        addEvents();
    }
    private  void addView(){
        edtTen = findViewById(R.id.edtTen);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSDT = findViewById(R.id.edtSDT);
        rbTienMat = findViewById(R.id.rbTienMat);
        rbNganHang = findViewById(R.id.rbNganHang);
        rbDienTu = findViewById(R.id.rbDienTu);
        btnDangKy = findViewById(R.id.btnDangKy);
        imgDate = findViewById(R.id.imgDate);
        rdgThanhToan = findViewById(R.id.rdgThanhToan);
        //khai báo cho spinner;
        listDichVu = findViewById(R.id.listDichVu);
        arrayDichVu = getResources().getStringArray(R.array.dich_vu);
        adapterDichVu = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayDichVu);
        listDichVu.setAdapter(adapterDichVu);
    }
    private void addEvents() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String  str = "";
                str += "Chúc mừng khách hàng: " + edtTen.getText();
                str += "\nSinh ngày: " + edtNgaySinh.getText();
                //-------Sự kiện lấy dữ liệu trong spinner-----------
                listDichVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String mess = adapterDichVu.getItem(i);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                //--------------------------------------------------------
                str += "\n" + listDichVu.getSelectedItem();
                str += "\nĐã đăng ký thành công dịch vụ: ";
                //-----------lấy dữ liệu radio khi check vào-----
                //phải khai báo nhóm group mơi được
                switch (rdgThanhToan.getCheckedRadioButtonId()) {
                    case R.id.rbNganHang:
                        str += "\nHình thức thanh toán: " + rbNganHang.getText().toString();
                        break;
                    case R.id.rbTienMat:
                        str += "\nHình thức thanh toán: " + rbTienMat.getText().toString();
                        break;
                    case R.id.rbDienTu:
                        str += "\nHình thức thanh toán: " + rbDienTu.getText().toString();
                        break;
                }
                //-----------------------------------------------
                str += "\nChúng tôi sẽ liên lạc với bạn theo số điện thoại: \n 0374167657";
                Intent intent = new Intent(MainActivity.this,ThongBao.class);
                intent.putExtra(INFOR,str);
                startActivity(intent);
            }
        });
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
//        btnDangKy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //---sử dụng intent để chuyển các activity
//                //--thông số đầu tiền truyền vào là Activity hiện tại
//                //-- thông số thứ 2 là activity muốn chuyển đến
//            }
//        });
    }
    private void ChonNgay(){
        final Calendar calendar = Calendar.getInstance(); //dùng để lấy ngày hiện tại
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        //----- sự kiện chọn ngày phải dùng datePickerDiaLog------------
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                calendar.set(year, month, date); //set lại thời gian mà người dùng chọn
                //đưa dữ liệu vào edittext()
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtNgaySinh.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam, thang, ngay);
        datePickerDialog.show();//---phải có này mơi khi click vào mới show ra lịch cho mình chọn-----
    }
}
