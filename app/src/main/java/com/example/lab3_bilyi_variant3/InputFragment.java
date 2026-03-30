package com.example.lab3_bilyi_variant3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.io.FileOutputStream;

public class InputFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input, container, false);
        DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        EditText edit = v.findViewById(R.id.inputEditText);
        RadioGroup group = v.findViewById(R.id.fontRadioGroup);
        Button ok = v.findViewById(R.id.btnOk);
        Button open = v.findViewById(R.id.btnOpen);

        ok.setOnClickListener(view -> {
            String text = edit.getText().toString();
            int checked = group.getCheckedRadioButtonId();
            if (text.isEmpty() || checked == -1) {
                Toast.makeText(getActivity(), "Заповніть все!", Toast.LENGTH_SHORT).show();
                return;
            }

            model.inputText.setValue(text);
            model.selectedFont.setValue(checked);

            String fontName = (checked == R.id.radioSans) ? "Sans" : (checked == R.id.radioSerif) ? "Serif" : "Monospace";
            String dataToSave = "Текст: " + text + " | Шрифт: " + fontName + "\n";

            try {
                FileOutputStream fos = getActivity().openFileOutput("history.txt", Context.MODE_APPEND);
                fos.write(dataToSave.getBytes());
                fos.close();
                Toast.makeText(getActivity(), "Дані успішно збережено у сховище!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Помилка запису!", Toast.LENGTH_SHORT).show();
            }
        });

        open.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), HistoryActivity.class);
            startActivity(intent);
        });

        model.clearFormSignal.observe(getViewLifecycleOwner(), clear -> {
            if (clear) { edit.setText(""); group.clearCheck(); }
        });
        return v;
    }
}