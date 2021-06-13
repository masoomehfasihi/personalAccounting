package ir.masoumeh.myaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ir.masoumeh.myaccount.databinding.ActivityLoginBinding;
import ir.masoumeh.myaccount.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {

    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.user.setText(UserInfo.getEmail(this));

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo.saveBoolPreference(SettingActivity.this, "isLogin", false);
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}