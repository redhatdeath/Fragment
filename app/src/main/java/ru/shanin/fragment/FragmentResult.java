package ru.shanin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentResult extends Fragment {

    private static final String ARGUMENT_FROM_INPUT_TEXT = "input_text";
    private static final String ARGUMENT_FROM_INPUT_TEXT_DEFAULT = "There is no input text";
    private String input_data_text;

    public static FragmentResult newInstanceWithInputData(String input) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_FROM_INPUT_TEXT, input);
        FragmentResult fragment = new FragmentResult();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseParams();
    }

    private void parseParams() {
        input_data_text = ARGUMENT_FROM_INPUT_TEXT_DEFAULT;
        Bundle args = requireArguments();
        if (!args.containsKey(ARGUMENT_FROM_INPUT_TEXT))
            throw new RuntimeException("Arguments are absent");
        input_data_text = args.getString(ARGUMENT_FROM_INPUT_TEXT);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_result,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
    }

    private void initLayout(View view) {
        TextView tv_output = view.findViewById(R.id.result_tv);
        tv_output.setText(input_data_text);
        (view.findViewById(R.id.result_bt)).setOnClickListener(
                v -> requireActivity().onBackPressed());
    }

}