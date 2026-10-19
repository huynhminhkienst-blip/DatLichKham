package vn.edu.stu.datlichphongkham;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView tvRegister, tvForgotPassword;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        preferences = getSharedPreferences("TaiKhoan", MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> dangNhap());

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );
            startActivity(intent);
        });

        tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(
                        LoginActivity.this,
                        "Vui lòng liên hệ quản trị viên để hỗ trợ lấy lại mật khẩu.",
                        Toast.LENGTH_LONG
                ).show()
        );
    }

    private void dangNhap() {
        String email = edtEmail.getText().toString().trim();
        String matKhau = edtPassword.getText().toString();

        if (email.isEmpty() || matKhau.isEmpty()) {
            Toast.makeText(this,
                    "Em hãy nhập đầy đủ thông tin!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String emailDaLuu =
                preferences.getString("email", "");
        String matKhauDaLuu =
                preferences.getString("matKhau", "");

        if (email.equals(emailDaLuu)
                && matKhau.equals(matKhauDaLuu)
                && !emailDaLuu.isEmpty()) {

            Toast.makeText(this,
                    "Đăng nhập thành công!",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(
                    LoginActivity.this,
                    MainActivity.class
            );
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this,
                    "Tài khoản hoặc mật khẩu không đúng!",
                    Toast.LENGTH_LONG).show();

            edtPassword.setText("");
            edtPassword.requestFocus();
        }
    }
}
