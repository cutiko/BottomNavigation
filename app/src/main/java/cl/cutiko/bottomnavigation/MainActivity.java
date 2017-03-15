package cl.cutiko.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFirst();
                    return true;
                case R.id.navigation_dashboard:
                    replaceSecond();
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFirst();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setFirst(){
        FragmentTransaction ft = sanitizer(false);
        ft.add(R.id.content, new FirstFragment());
        ft.commitAllowingStateLoss();
    }

    private void replaceFirst(){
        FragmentTransaction ft = sanitizer(true);
        ft.replace(R.id.content, new FirstFragment());
        ft.commitAllowingStateLoss();
    }

    private void replaceSecond(){
        FragmentTransaction ft = sanitizer(true);
        ft.replace(R.id.content, new SecondFragment());
        ft.commitAllowingStateLoss();
    }

    private FragmentTransaction sanitizer(boolean animation){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentById(R.id.content);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        if (animation) {
            ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        }
        return ft;
    }

}
