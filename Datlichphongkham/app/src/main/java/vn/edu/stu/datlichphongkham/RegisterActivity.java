package vn.edu.stu.datlichphongkham;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    Button btnRegister;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edtRegisterName);
        edtEmail = findViewById(R.id.edtRegisterEmail);
        edtPassword = findViewById(R.id.edtRegisterPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        preferences = getSharedPreferences("TaiKhoan", MODE_PRIVATE);

        btnRegister.setOnClickListener(v -> dangKy());
    }

    private void dangKy() {
        String hoTen = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String matKhau = edtPassword.getText().toString();
        String xacNhan = edtConfirmPassword.getText().toString();

        if (hoTen.isEmpty() || email.isEmpty()
                || matKhau.isEmpty() || xacNhan.isEmpty()) {
            Toast.makeText(this,
                    "Vui lòng nhập đầy đủ thông tin!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (matKhau.length() < 6) {
            edtPassword.setError("Mật khẩu cần ít nhất 6 ký tự");
            return;
        }

        if (!matKhau.equals(xacNhan)) {
            edtConfirmPassword.setError("Mật khẩu nhập lại chưa khớp");
            return;
        }

        String emailDaLuu = preferences.getString("email", "");

        if (email.equalsIgnoreCase(emailDaLuu)) {
            edtEmail.setError("Tài khoản này đã được đăng ký");
            return;
        }

        preferences.edit()
                .putString("hoTen", hoTen)
                .putString("email", email)
                .putString("matKhau", matKhau)
                .apply();

        Toast.makeText(this,
                "Đăng ký thành công! Hãy đăng nhập.",
                Toast.LENGTH_LONG).show();

        finish();
    }
}
