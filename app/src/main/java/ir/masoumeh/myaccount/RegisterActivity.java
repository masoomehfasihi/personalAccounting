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
import com.google.firebase.database.FirebaseDatabase;

import ir.masoumeh.myaccount.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        binding.btnLogin.setOnClickListener(v -> register());
    }

    private void register() {
        String email = binding.edtEmail.getText().toString();
        String pass = binding.edtPass.getText().toString();
        String user = binding.edtUser.getText().toString();
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && pass.length() >= 6 && !user.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User userData = new User(user, email);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            UserInfo.saveEmail(RegisterActivity.this, email);
                                            UserInfo.saveBoolPreference(RegisterActivity.this, "isLogin", true);
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(RegisterActivity.this, "some errors", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            if (email.isEmpty()) {
                binding.edtEmail.setError("ایمیل را وارد کنید");

            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmail.setError("لطفا ایمیل را صحیح وارد کنید");

            }
            if (user.isEmpty()) {
                binding.edtUser.setError("شناسه کاربری را وارد کنید");

            }
            if (pass.length() < 6) {
                binding.edtPass.setError("طول رمز عبور باید حداقل 6 رقم باشد");

            }
        }
    }
}