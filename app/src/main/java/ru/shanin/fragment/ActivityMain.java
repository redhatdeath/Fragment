package ru.shanin.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

public class ActivityMain extends AppCompatActivity {

    private FragmentContainerView fragmentContainerView;
    private EditText et;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
    }

    private void initLayout() {
        fragmentContainerView = findViewById(R.id.fcv_am);
        new ButtonClickListener(this, R.id.bt + 0, R.id.et + 0);
    }

    private boolean isOnePaneMode() {
        return fragmentContainerView == null;
    }

    private final class ButtonClickListener implements View.OnClickListener {

        public ButtonClickListener(Activity activity, int btId, int etId) {
            et = activity.findViewById(etId);
            (activity.findViewById(btId)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String input = et.getText().toString();
            if (isOnePaneMode()) launchActivityResultWithStringData(input);
            else launchFragmentResultWithStringData(input);
        }
    }

    private void launchActivityResultWithStringData(String data) {
        Intent i = ActivityResult
                .newIntentActivityResultWithStringData(getApplicationContext(), data);
        startActivity(i);
    }

    private void launchFragmentResultWithStringData(String data) {
//        Fragment fragment = FragmentResult.newInstanceWithInputData(data);
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("null")
                .replace(R.id.fcv_am, FragmentResult.newInstanceWithInputData(data))
//                .add(R.id.fcv_am, fragment)
                .commit();
    }

}