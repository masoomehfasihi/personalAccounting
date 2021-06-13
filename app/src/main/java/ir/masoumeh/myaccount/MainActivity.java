package ir.masoumeh.myaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ir.masoumeh.myaccount.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Adapter adapterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        adapterFragment= new Adapter(getSupportFragmentManager());
        setupViewPager(binding.viewpager);
        binding.txtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , SettingActivity.class);
                startActivity(intent);
            }
        });
        binding.txtUser.setText(UserInfo.getEmail(this) +" خوش آمدید ");
    }

    private void setupViewPager(ViewPager viewPager) {

        adapterFragment.addFragment(new IncomeFragment(), "درآمد");
        adapterFragment.addFragment(new OutComeFragment(), "هزینه");
        adapterFragment.addFragment(new HomeFragment(), "گزارش");

        viewPager.setAdapter(adapterFragment);
        viewPager.setCurrentItem(0);
        binding.tabs.setupWithViewPager(viewPager);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}