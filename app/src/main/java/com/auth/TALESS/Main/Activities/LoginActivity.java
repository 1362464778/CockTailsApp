package com.auth.TALESS.Main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.auth.TALESS.Database.SenpaiDB;
import com.auth.TALESS.R;
import com.auth.TALESS.Util.SessionManager;
import com.auth.TALESS.Database.User;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private SenpaiDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 检查是否已登录
//        long userId = SessionManager.getUserId(this);
//        if (userId != -1L) {
//            navigateToMain(userId);
//            return;
//        }

        setContentView(R.layout.activity_login);

        db = SenpaiDB.getInstance(this);
        db.openDatabase(); // 确保数据库已打开

        usernameEditText = findViewById(R.id.edit_text_username);
        passwordEditText = findViewById(R.id.edit_text_password);
        Button loginButton = findViewById(R.id.button_login);
        Button registerButton = findViewById(R.id.button_register);

        loginButton.setOnClickListener(v -> loginUser());
        registerButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // 在后台线程执行数据库操作
        new Thread(() -> {
            long newUserId = db.insertUser(username, password);
            runOnUiThread(() -> {
                if (newUserId == -1) {
                    Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void loginUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            User user = db.getUserByUsername(username);
            runOnUiThread(() -> {
                if (user != null && user.password.equals(password)) {
                    SessionManager.saveUserId(this, user.id);
                    navigateToMain(user.id);
                } else {
                    Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void navigateToMain(long userId) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        // 增加一个非空判断
        if (db != null) {
            db.close();
        }
        super.onDestroy();
    }
}