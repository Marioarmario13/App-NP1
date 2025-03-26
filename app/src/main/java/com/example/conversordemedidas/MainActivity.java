package com.example.conversordemedidas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnitSpinner, toUnitSpinner;
    Button convertButton;
    TextView resultText;

    String[] units = {"Metro", "Quilômetro", "Centímetro", "Milímetro", "Milha", "Jarda", "Polegada"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.editTextValor);
        fromUnitSpinner = findViewById(R.id.spinnerDe);
        toUnitSpinner = findViewById(R.id.spinnerPara);
        convertButton = findViewById(R.id.buttonConverter);
        resultText = findViewById(R.id.textViewResultado);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLength();
            }
        });
    }

    private void convertLength() {
        String inputStr = inputValue.getText().toString();

        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Digite um valor", Toast.LENGTH_SHORT).show();
            return;
        }

        double input = Double.parseDouble(inputStr);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double meters = convertToMeters(input, fromUnit);
        double result = convertFromMeters(meters, toUnit);

        // Usando DecimalFormat para evitar zeros extras
        DecimalFormat df = new DecimalFormat("#.####");
        resultText.setText(df.format(result) + " " + toUnit);
    }

    private double convertToMeters(double value, String unit) {
        double result;

        switch (unit) {
            case "Metro":
                result = value;
                break;
            case "Quilômetro":
                result = value * 1000;
                break;
            case "Centímetro":
                result = value / 100;
                break;
            case "Milímetro":
                result = value / 1000;
                break;
            case "Milha":
                result = value * 1609.34;
                break;
            case "Jarda":
                result = value * 0.9144;
                break;
            case "Polegada":
                result = value * 0.0254;
                break;
            default:
                result = value;
        }

        return result;
    }

    private double convertFromMeters(double value, String unit) {
        double result;

        switch (unit) {
            case "Metro":
                result = value;
                break;
            case "Quilômetro":
                result = value / 1000;
                break;
            case "Centímetro":
                result = value * 100;
                break;
            case "Milímetro":
                result = value * 1000;
                break;
            case "Milha":
                result = value / 1609.34;
                break;
            case "Jarda":
                result = value / 0.9144;
                break;
            case "Polegada":
                result = value / 0.0254;
                break;
            default:
                result = value;
        }

        return result;
    }
}
