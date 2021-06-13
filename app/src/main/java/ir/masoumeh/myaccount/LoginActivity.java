package ir.masoumeh.myaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ir.masoumeh.myaccount.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String email = binding.edtUser.getText().toString().trim();
        String pass = binding.edtPass.getText().toString().trim();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && pass.length() >= 6) {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        UserInfo.saveEmail(LoginActivity.this, email);
                        UserInfo.saveBoolPreference(LoginActivity.this, "isLogin", true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "اطلاعات خود را چک کنید و دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            if (email.isEmpty()) {
                binding.edtUser.setError("ایمیل را وارد کنید");

            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtUser.setError("لطفا ایمیل را صحیح وارد کنید");

            }

            if (pass.length() < 6) {
                binding.edtPass.setError("طول رمز عبور باید حداقل 6 رقم باشد");

            }
        }
    }


}