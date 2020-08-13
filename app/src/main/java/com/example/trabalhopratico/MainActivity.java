package com.example.trabalhopratico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static  final String SALARIO_BRUTO = MainActivity.SALARIO_BRUTO;
    public static final String DEPENDENTES = MainActivity.DEPENDENTES;
    public static final String DESCONTOS = MainActivity.DESCONTOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);

        final EditText edtTxtSalario = (EditText) findViewById(R.id.edtTxtSalarioBruto);
        final EditText edtTxtDependentes = (EditText) findViewById(R.id.edtTxtDependentes);
        final EditText edtTxtDescontos = (EditText) findViewById(R.id.edtTxtDescontos);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateFields();
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                 intent.putExtra("SALARIO_BRUTO", Double.parseDouble(edtTxtSalario.getText().toString()));
                 intent.putExtra("DEPENDENTES", Integer.parseInt(edtTxtDependentes.getText().toString()));
                 intent.putExtra("DESCONTO", Double.parseDouble(edtTxtDescontos.getText().toString()));
                 startActivity(intent);
            }
        });
    }
            private void validateFields() {

                EditText edtTxtSalario = (EditText)findViewById(R.id.edtTxtSalarioBruto);
                EditText edtTxtDependentes = (EditText)findViewById(R.id.edtTxtDependentes);
                EditText edtTxtDescontos = (EditText)findViewById(R.id.edtTxtDescontos);

                if(edtTxtSalario.getText().length()<=0) {
                    edtTxtSalario.setText("0");
                }
                if(edtTxtDependentes.getText().length()<=0) {
                    edtTxtDependentes.setText("0");
                }
                if(edtTxtDescontos.getText().length()<=0) {
                    edtTxtDescontos.setText("0");
                }
            }
}