package com.example.toovendendo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout lytTpPedido;
    private ScrollView sclPedido;
    private EditText edNrPedidoPesquisa;
    private Button btNovoPedido;
    private Button btPesquisaPedido;
    private TextView tvPedidos;
    private EditText edNrPedido;
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
    private Button btVoltar;
    private double AuxVlrPedido;
    private int AuxQtdItens;

    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private Pedido pedido;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lytTpPedido = findViewById(R.id.lytTpPedido);
        sclPedido = findViewById(R.id.sclPedido);
        edNrPedidoPesquisa = findViewById((R.id.edNrPedidoPesquisa));
        btNovoPedido = findViewById(R.id.btNovoPedido);
        btPesquisaPedido = findViewById(R.id.btPesquisaPedido);
        tvPedidos = findViewById(R.id.tvPedidos);
        edNrPedido = findViewById(R.id.edNrPedido);
        edNome = findViewById(R.id.edNome);
        edCpf = findViewById(R.id.edCpf);
        edItem = findViewById(R.id.edItem);
        edQtdItem = findViewById(R.id.edQtdItem);
        edVlrUnit = findViewById(R.id.edVlrUnit);
        btAddItem = findViewById(R.id.btAddItem);
        tvItensPedido = findViewById(R.id.tvItensPedido);
        edVlrTotal = findViewById(R.id.edVlrTotal);
        edQtdItens = findViewById(R.id.edQtdItens);
        btAvista = findViewById(R.id.btAvista);
        btAprazo = findViewById(R.id.btAprazo);
        tvQtdParcelas = findViewById(R.id.tvQtdParcelas);
        edQtdParcelas = findViewById(R.id.edQtdParcelas);
        tvVlrParcelas = findViewById(R.id.tvVlrParcelas);
        edVlrParcelas = findViewById(R.id.edVlrParcelas);
        tvVlrPedido = findViewById(R.id.tvVlrPedido);
        edVlrPedido = findViewById(R.id.edVlrPedido);
        btConcluirPedido = findViewById(R.id.btConcluirPedido);
        btVoltar = findViewById(R.id.btVoltar);

        btNovoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedido = new Pedido();

                pedido.geraNrPedido(pedidos);
                edNrPedido.setText(String.valueOf(pedido.getNrPedido()));

                sclPedido.setVisibility(View.VISIBLE);
                btConcluirPedido.setVisibility(View.GONE);
                edVlrPedido.setVisibility(View.GONE);
                tvVlrPedido.setVisibility(View.GONE);
                edVlrParcelas.setVisibility(View.GONE);
                tvVlrParcelas.setVisibility(View.GONE);
                edQtdParcelas.setVisibility(View.GONE);
                tvQtdParcelas.setVisibility(View.GONE);

                btVoltar.setVisibility(View.GONE);

                lytTpPedido.setVisibility(View.GONE);
            }
        });

        btPesquisaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(!edNrPedidoPesquisa.getText().toString().trim().equals("")){
                        int nrPedido = Integer.parseInt(edNrPedidoPesquisa.getText().toString());
                        pedido = new Pedido();

                        pedido = pedido.pesquisaPedido(pedidos, nrPedido);

                        if (!(pedido == null)){
                            sclPedido.setVisibility(View.VISIBLE);
                            sclPedido.setEnabled(false);

                            lytTpPedido.setVisibility(View.GONE);

                            edNrPedido.setText(String.valueOf(nrPedido));
                            edNome.setText(pedido.getNome());
                            edCpf.setText(pedido.getCpf());
                            tvItensPedido.setText(pedido.listaItensPedido());
                            pedido.calculaVlPedido();
                            edVlrTotal.setText("R$ " + String.valueOf(pedido.getVlTotal()));
                            pedido.calculaQtdItens();
                            edQtdItens.setText(String.valueOf(pedido.getQtdItens()));
                            edVlrPedido.setText(String.valueOf(pedido.getVlPedido()));

                            btVoltar.setVisibility(View.VISIBLE);

                            if (pedido.getQtdParcelas()>0){
                                edVlrParcelas.setText(String.valueOf(pedido.getVlParcelas()));
                                edQtdParcelas.setText(String.valueOf(pedido.getQtdParcelas()));
                            } else {
                                btConcluirPedido.setVisibility(View.GONE);
                                edVlrParcelas.setVisibility(View.GONE);
                                tvVlrParcelas.setVisibility(View.GONE);
                                edQtdParcelas.setVisibility(View.GONE);
                                tvQtdParcelas.setVisibility(View.GONE);
                            }
                        }else {
                            String text = "Pedido " + edNrPedidoPesquisa.getText().toString() + " não existe!";
                            Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                            edNrPedidoPesquisa.setText("");
                        }
                    }else {
                        edNrPedidoPesquisa.setError("Informe um pedido válido para pesquisar!");
                    }
                }catch (Exception ex) {
                    Log.e("ERRO", ex.getMessage());
                }
            }
        });

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

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sclPedido.setVisibility(View.GONE);
                lytTpPedido.setVisibility(View.VISIBLE);

                tvPedidos.setText(listarPedidos());
            }
        });


    }

    private void addItem() {
        try {
            String nome = edNome.getText().toString().trim();
            String cpf = edCpf.getText().toString().trim();
            String item = edItem.getText().toString().trim();
            if (edQtdItem.getText().toString().trim().equals("")){
                edQtdItem.setError("Informe uma quantidade válida!");
            }
            if (edVlrUnit.getText().toString().trim().equals("")){
                edVlrUnit.setError("Informe um valor válido!");
            }

            Integer qtdItem = Integer.parseInt(edQtdItem.getText().toString());
            Double vlUnit = Double.parseDouble(edVlrUnit.getText().toString());

            if (!nome.trim().equals("") &
                    !cpf.trim().equals("") &
                    !item.trim().equals("") &
                    qtdItem > 0 &
                    vlUnit > 0) {

                edVlrTotal.setVisibility(View.VISIBLE);
                edQtdItens.setVisibility(View.VISIBLE);
                btAvista.setVisibility(View.VISIBLE);
                btAprazo.setVisibility(View.VISIBLE);

                pedido.setNome(nome);
                pedido.setCpf(cpf);
                pedido.addItem(item,qtdItem,vlUnit);
                tvItensPedido.setText(pedido.listaItensPedido());

                edNome.setEnabled(false);
                edCpf.setEnabled(false);

                edItem.setText("");
                edQtdItem.setText("");
                edVlrUnit.setText("");

                edVlrTotal.setText("R$" + String.valueOf(pedido.getVlTotal()));
                edQtdItens.setText(String.valueOf(pedido.getQtdItens()));

                Toast.makeText(this, "Item adicionado com Sucesso!", Toast.LENGTH_LONG).show();

            } else {
                if (nome.trim().equals("")) {
                    edNome.setError("Informe o Nome do cliente!");
                }

                if (cpf.trim().equals("")) {
                    edCpf.setError("Informe o CPF do cliente!");
                }

                if (item.trim().equals("")) {
                    edItem.setError("Informe o Item para a venda!");
                }

                if (qtdItem <= 0) {
                    edQtdItem.setError("Informe uma quantidade maior do que 0!");
                }

                if (vlUnit <= 0) {
                    edVlrUnit.setError("Informe um valor maior do que 0!");
                }
            }
        } catch (Exception ex) {
            Log.e("ERRO", ex.getMessage());
        }
    }

    private void pagamentoAVista() {
        try {
            if (pedido.getVlTotal() > 0) {
                pedido.calculaVlAVista();
                edVlrPedido.setText("R$" + String.valueOf(pedido.getVlPedido()));
                tvQtdParcelas.setVisibility(View.GONE);
                edQtdParcelas.setVisibility(View.GONE);
                edVlrParcelas.setVisibility(View.GONE);
                tvVlrParcelas.setVisibility(View.GONE);
                tvVlrPedido.setVisibility(View.VISIBLE);
                edVlrPedido.setVisibility(View.VISIBLE);
                btConcluirPedido.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e("ERRO", ex.getMessage());
        }
    }

    private void pagamentoAPrazo() {
        try {
            if (pedido.getVlTotal() > 0) {
                pedido.calculaVlAPrazo();
                edVlrPedido.setText("R$" + String.valueOf(pedido.getVlPedido()));
                tvQtdParcelas.setVisibility(View.VISIBLE);
                edQtdParcelas.setVisibility(View.VISIBLE);
                edQtdParcelas.setText("");
                edVlrParcelas.setVisibility(View.VISIBLE);
                edVlrParcelas.setText("");
                tvVlrParcelas.setVisibility(View.VISIBLE);
                tvVlrPedido.setVisibility(View.VISIBLE);
                edVlrPedido.setVisibility(View.VISIBLE);
                btConcluirPedido.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e("ERRO", ex.getMessage());
        }
    }

    private void verificaParcelas() {
        try {
            if (!edQtdParcelas.getText().toString().trim().equals("")){
                pedido.setQtdParcelas(Integer.parseInt(edQtdParcelas.getText().toString()));
            }
            pedido.calculaVlParcela();

            edVlrParcelas.setText("R$" + String.valueOf(pedido.getVlParcelas()));
        } catch (Exception ex) {
            Log.e("ERRO", ex.getMessage());
        }
    }

    private void concluirPedido() {
        try {
            String nome = edNome.getText().toString();
            String cpf = edCpf.getText().toString();
            String qtdItens = edQtdItens.getText().toString();
            String vlrPedido = edVlrPedido.getText().toString();

            Toast.makeText(this, "Pedido para o cliente "
                            + pedido.getNome()
                            + " com "
                            + String.valueOf(pedido.getQtdItens())
                            + " itens totalizando "
                            + String.valueOf(pedido.getVlPedido())
                            + ", finalizado com sucesso!"
                    , Toast.LENGTH_LONG).show();

            edNrPedido.setText("");
            edNome.setText("");
            edCpf.setText("");
            edItem.setText("");
            edQtdItem.setText("");
            edVlrUnit.setText("");
            tvItensPedido.setText("");
            edQtdItens.setText("");
            edVlrTotal.setText("");
            edQtdParcelas.setText("");
            edVlrParcelas.setText("");
            edVlrPedido.setText("");

            pedidos.add(pedido);

            sclPedido.setVisibility(View.GONE);
            lytTpPedido.setVisibility(View.VISIBLE);

            tvPedidos.setText(listarPedidos());

        } catch (Exception ex) {
            Log.e("ERRO", ex.getMessage());
        }
    }

    private String listarPedidos(){
        String resultado = "";

        for (Pedido pedido:pedidos) {
            resultado += "Cliente: "
                    + pedido.getNome()
                    + " | CPF: "
                    + pedido.getCpf()
                    + " | Qtd. Itens: "
                    + pedido.getQtdItens()
                    + " | Valor Pedido: R$ "
                    + pedido.getVlPedido()
                    + "\n";
        }
        return resultado;
    }

    @Override
    public void onBackPressed(){
        sclPedido.setVisibility(View.GONE);
        lytTpPedido.setVisibility(View.VISIBLE);
    }
}