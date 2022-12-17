package ru.shanin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ActivityResult extends AppCompatActivity {

    private static final String ARGUMENT_FROM_INPUT_TEXT = "input_Text";
    private static final String DEFAULT_TEXT = "There is no input text";
    private static final String EXTRA_MODE = "Extra_mode";
    private static final String EXTRA_MODE_SHOW_INPUT_DATA = "Extra_mode_input";
    private String input_data_text;


    public static Intent newIntentActivityResultWithStringData(Context context, String arg) {
        Intent intent = new Intent(context, ActivityResult.class);
        intent.putExtra(EXTRA_MODE, EXTRA_MODE_SHOW_INPUT_DATA);
        intent.putExtra(ARGUMENT_FROM_INPUT_TEXT, arg);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        parseIntent();
        if (savedInstanceState == null) {
            if (!input_data_text.equals(DEFAULT_TEXT))
                launchFragmentResultWithStringData(input_data_text);
        }
    }

    private void parseIntent() {
        input_data_text = DEFAULT_TEXT;
        if (!getIntent().hasExtra(EXTRA_MODE))
            throw new RuntimeException("Extra mode is absent");
        String mode = getIntent().getStringExtra(EXTRA_MODE);
        if (!mode.equals(EXTRA_MODE_SHOW_INPUT_DATA))
            throw new RuntimeException("Unknown extra mode!");
        if (!getIntent().hasExtra(ARGUMENT_FROM_INPUT_TEXT))
            throw new RuntimeException("Input text data is absent");
        input_data_text = getIntent().getStringExtra(ARGUMENT_FROM_INPUT_TEXT);
    }

    private void launchFragmentResultWithStringData(String data) {
        Fragment fragment = FragmentResult.newInstanceWithInputData(data);
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("null")
                .replace(R.id.fcv_ar, fragment)
                .commit();
    }

}