package vn.edu.stu.datlichphongkham;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtPatient, edtPhone, edtDoctor;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPatient = findViewById(R.id.edtPatient);
        edtPhone = findViewById(R.id.edtPhone);
        edtDoctor = findViewById(R.id.edtDoctor);
        btnBooking = findViewById(R.id.btnBooking);

        btnBooking.setOnClickListener(v -> {
            String patient = edtPatient.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String doctor = edtDoctor.getText().toString().trim();

            if (patient.isEmpty()
                    || phone.isEmpty()
                    || doctor.isEmpty()) {

                Toast.makeText(this,
                        "Vui lòng nhập đầy đủ thông tin",
                        Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this,
                        "Đã nhận yêu cầu đặt lịch của " + patient,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
