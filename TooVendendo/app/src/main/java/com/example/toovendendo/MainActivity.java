package com.example.toovendendo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edNome;
    private EditText edCpf;
    private EditText edItem;
    private EditText edQtdItem;
    private EditText edVlrUnit;
    private Button btAddItem;
    private TextView tvItensPedido;
    private EditText edVlrTotal;
    private EditText edQtdItens;
    private Button btAvista;
    private Button btAprazo;
    private TextView tvQtdParcelas;
    private EditText edQtdParcelas;
    private TextView tvVlrParcelas;
    private EditText edVlrParcelas;
    private TextView tvVlrPedido;
    private EditText edVlrPedido;
    private Button btConcluirPedido;

    private double AuxVlrPedido;
    private int AuxQtdItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNome           = findViewById(R.id.edNome);
        edCpf            = findViewById(R.id.edCpf);
        edItem           = findViewById(R.id.edItem);
        edQtdItem        = findViewById(R.id.edQtdItem);
        edVlrUnit        = findViewById(R.id.edVlrUnit);
        btAddItem        = findViewById(R.id.btAddItem);
        tvItensPedido    = findViewById(R.id.tvItensPedido);
        edVlrTotal       = findViewById(R.id.edVlrTotal);
        edQtdItens       = findViewById(R.id.edQtdItens);
        btAvista         = findViewById(R.id.btAvista);
        btAprazo         = findViewById(R.id.btAprazo);
        tvQtdParcelas    = findViewById(R.id.tvQtdParcelas);
        edQtdParcelas    = findViewById(R.id.edQtdParcelas);
        tvVlrParcelas    = findViewById(R.id.tvVlrParcelas);
        edVlrParcelas    = findViewById(R.id.edVlrParcelas);
        tvVlrPedido      = findViewById(R.id.tvVlrPedido);
        edVlrPedido      = findViewById(R.id.edVlrPedido);
        btConcluirPedido = findViewById(R.id.btConcluirPedido);

        btAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        btAvista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagamentoAVista();
            }
        });

        btAprazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagamentoAPrazo();
            }
        });

        edQtdParcelas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verificaParcelas();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verificaParcelas();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                verificaParcelas();
            }
        });

        btConcluirPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concluirPedido();
            }
        });
    }

    private void addItem (){
        try{
            String nome = edNome.getText().toString();
            String cpf = edCpf.getText().toString();
            String item = edItem.getText().toString();
            String qtdItem = edQtdItem.getText().toString();
            String vlUnit = edVlrUnit.getText().toString();
            String resultado = tvItensPedido.getText().toString();

            if (!nome.trim().equals("") &&
                    !cpf.trim().equals("") &&
                    !item.trim().equals("") &&
                    !qtdItem.trim().equals("") &&
                    !vlUnit.trim().equals("")){
                Integer auxQtdItem = Integer.parseInt(qtdItem);
                Double auxVlUnit = Double.parseDouble(vlUnit);
                if (auxQtdItem <= 0){
                    edQtdItem.setError("Informe uma quantidade maior do que 0!");
                } else if (auxVlUnit <= 0){
                    edVlrUnit.setError("Informe um valor maior do que 0!");
                }else {
                    resultado += "Cliente: " + nome + ", CPF: " + cpf + ", Item: " + item + ", Valor Unit.: R$" + vlUnit + "\n";
                    tvItensPedido.setText(resultado);

                    AuxVlrPedido += (auxVlUnit * auxQtdItem);
                    AuxQtdItens += auxQtdItem;

                    edItem.setText("");
                    edQtdItem.setText("");
                    edVlrUnit.setText("");

                    edVlrTotal.setText("R$" + String.valueOf(AuxVlrPedido));
                    edQtdItens.setText(String.valueOf(AuxQtdItens));

                    Toast.makeText(this, "Item adicionado com Sucesso!", Toast.LENGTH_LONG).show();
                }
            }else {
                if (nome.trim().equals("")){
                    edNome.setError("Informe o Nome do cliente!");
                }

                if (cpf.trim().equals("")){
                    edCpf.setError("Informe o CPF do cliente!");
                }

                if (item.trim().equals("")){
                    edItem.setError("Informe o Item para a venda!");
                }

                if (qtdItem.trim().equals("")){
                    edQtdItem.setError("Informe uma quantidade maior do que 0!");
                }

                if (vlUnit.trim().equals("")){
                    edVlrUnit.setError("Informe um valor maior do que 0!");
                }
            }
            }catch (Exception ex){
            Log.e("ERRO", ex.getMessage());
        }
    }

    private void pagamentoAVista(){
        try {
            Double auxVlFinal = AuxVlrPedido;
            auxVlFinal = auxVlFinal*.95;
            edVlrPedido.setText("R$" + String.valueOf(auxVlFinal));
            tvQtdParcelas.setVisibility(View.GONE);
            edQtdParcelas.setVisibility(View.GONE);
            edVlrParcelas.setVisibility(View.GONE);
            tvVlrParcelas.setVisibility(View.GONE);
            tvVlrPedido.setVisibility(View.VISIBLE);
            edVlrPedido.setVisibility(View.VISIBLE);
            btConcluirPedido.setVisibility(View.VISIBLE);
        }catch (Exception ex){
            Log.e("ERRO", ex.getMessage());
        }
    }

    private void pagamentoAPrazo(){
        try {
            Double auxVlFinal = AuxVlrPedido;
            auxVlFinal = auxVlFinal*1.05;
            edVlrPedido.setText("R$" + String.valueOf(auxVlFinal));
            tvQtdParcelas.setVisibility(View.VISIBLE);
            edQtdParcelas.setVisibility(View.VISIBLE);
            edQtdParcelas.setText("");
            edVlrParcelas.setVisibility(View.VISIBLE);
            edVlrParcelas.setText("");
            tvVlrParcelas.setVisibility(View.VISIBLE);
            tvVlrPedido.setVisibility(View.VISIBLE);
            edVlrPedido.setVisibility(View.VISIBLE);
            btConcluirPedido.setVisibility(View.VISIBLE);
        }catch (Exception ex){
            Log.e("ERRO", ex.getMessage());
        }
    }

    private void verificaParcelas(){
        try {
            Double auxVlFinal = AuxVlrPedido;
            auxVlFinal = auxVlFinal*1.05;

            Integer qtdParcelas = Integer.parseInt(edQtdParcelas.getText().toString());

            edVlrParcelas.setText("R$" + String.valueOf(auxVlFinal/qtdParcelas));
        }catch (Exception ex){
            Log.e("ERRO", ex.getMessage());
        }
    }

    private void concluirPedido(){
        try {
            String nome = edNome.getText().toString();
            String cpf = edCpf.getText().toString();
            String qtdItens = edQtdItens.getText().toString();
            String vlrPedido = edVlrPedido.getText().toString();

            Toast.makeText(this, "Pedido para o cliente " + nome +
                    " com " + qtdItens + " iten totalizando " + vlrPedido + ", finalizado com sucesso!"
                    , Toast.LENGTH_LONG).show();

            edNome.setText("");
            edCpf.setText("");
            edItem.setText("");
            edQtdItem.setText("");
            edVlrUnit.setText("");
            edQtdParcelas.setText("");
            edVlrParcelas.setText("");
            edVlrPedido.setText("");
        }catch (Exception ex){
            Log.e("ERRO", ex.getMessage());
        }
    }
}