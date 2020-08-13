package com.example.trabalhopratico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button btnVoltar = (Button)findViewById(R.id.btnRetornar);

        Intent intent = getIntent();

        double salarioBruto = intent.getDoubleExtra(MainActivity.SALARIO_BRUTO, 0);
        int dependentes = intent.getIntExtra(MainActivity.DEPENDENTES, 0);
        double descontos = intent.getDoubleExtra(MainActivity.DESCONTOS, 0);

        buildLayout(salarioBruto, dependentes, descontos );



        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }

    private void buildLayout(double salarioBruto, int dependentes, double descontos)
    {
        TextView txtSalarioBruto = (TextView)findViewById(R.id.txtSalarioBruto);
        TextView txtINSS = (TextView)findViewById(R.id.txtINSS);
        TextView txtIRRF = (TextView)findViewById(R.id.txtIRRF);
        TextView txtDescontos = (TextView) findViewById(R.id.txtDescontos);
        TextView txtSalarioLiquido = (TextView)findViewById(R.id.txtSalarioLiquido);
        TextView txtPercentualDescontos = (TextView)findViewById(R.id.txtPercentualDesconto);



        double contribuicaoINSS = calculaINSS(salarioBruto);
        double baseCalculo = salarioBruto - contribuicaoINSS - (dependentes*189.59);
        double contribuicaoIRRF = calculaIRRF(baseCalculo);
        double salarioLiquido = salarioBruto - contribuicaoINSS - contribuicaoIRRF - descontos;
        double percentualDescontos = (1-salarioLiquido/salarioBruto)*100;

        txtSalarioBruto.setText(String.valueOf(formatDouble(salarioBruto)));
        txtINSS.setText(String.valueOf(formatDouble(contribuicaoINSS*-1)));
        txtIRRF.setText(String.valueOf(formatDouble(contribuicaoIRRF*-1)));
        txtDescontos.setText(String.valueOf(formatDouble(descontos*-1)));
        txtSalarioLiquido.setText(String.valueOf(formatDouble(salarioLiquido)));
        txtPercentualDescontos.setText(String.valueOf(formatDouble(percentualDescontos))+"%");
    }
    private double calculaIRRF(double baseCalculo){
        if(baseCalculo<=1903.98)
            return 0;
        if (baseCalculo<=2826.65)
            return (baseCalculo*0.0075) -142.80;
        if(baseCalculo<=3751.05)
            return(baseCalculo*0.15)-354.80;
        if(baseCalculo<=4664.68)
            return(baseCalculo*0.225)-636.13;
        return(baseCalculo*0.275)-869.36;
    }
    private double calculaINSS(double salarioBruto) {
        if(salarioBruto<=1045)
            return  salarioBruto*0.0075;

        if(salarioBruto<=2089.60)
            return  (salarioBruto*0.009)-15.67;
        if(salarioBruto<=3134.40)
            return  (salarioBruto*0.012)-78.36;
        if(salarioBruto<=6101.06)
            return  (salarioBruto*0.014)-141.05;
        else
            return 713.10;
    }

    private double formatDouble(double d){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.valueOf(decimalFormat.format(d));
    }




}