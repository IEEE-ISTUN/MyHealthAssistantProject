package com.zeyneptekin.myhealthassistant;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zeyneptekin.myhealthassistant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            int itemId=item.getItemId();

            if (itemId==R.id.home){
                replaceFragment(new HomeFragment());
            }
            else if(itemId==R.id.categories){
                replaceFragment(new CategoriesFragment());
            }
            else if(itemId==R.id.profile){
                replaceFragment(new ProfileFragment());
            }
             return true;
        });


    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout2,fragment);
        fragmentTransaction.commit();
    }
}