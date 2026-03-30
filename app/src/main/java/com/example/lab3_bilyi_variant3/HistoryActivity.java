package com.example.lab3_bilyi_variant3;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class HistoryActivity extends AppCompatActivity {

    private TextView historyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyTextView = findViewById(R.id.historyTextView);
        Button btnClearHistory = findViewById(R.id.btnClearHistory);

        loadHistory();

        btnClearHistory.setOnClickListener(v -> {
            try {
                FileOutputStream fos = openFileOutput("history.txt", Context.MODE_PRIVATE);
                fos.write("".getBytes());
                fos.close();

                historyTextView.setText("Сховище пусте");
                Toast.makeText(this, "Історію успішно видалено", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            finish();
        });

    }

    private void loadHistory() {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = openFileInput("history.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sb.toString().trim().isEmpty()) {
            historyTextView.setText("Сховище пусте");
        } else {
            historyTextView.setText(sb.toString());
        }
    }
}