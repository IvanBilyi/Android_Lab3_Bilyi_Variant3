package com.example.lab3_bilyi_variant3;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class OutputFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_output, container, false);
        DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        TextView res = v.findViewById(R.id.resultTextView);
        Button cancel = v.findViewById(R.id.btnCancel);

        model.inputText.observe(getViewLifecycleOwner(), s -> res.setText(s));
        model.selectedFont.observe(getViewLifecycleOwner(), id -> {
            if (id == R.id.radioSans) res.setTypeface(Typeface.SANS_SERIF);
            else if (id == R.id.radioSerif) res.setTypeface(Typeface.SERIF);
            else res.setTypeface(Typeface.MONOSPACE);
        });

        cancel.setOnClickListener(view -> {
            res.setText("");
            model.clearFormSignal.setValue(true);
        });
        return v;
    }
}