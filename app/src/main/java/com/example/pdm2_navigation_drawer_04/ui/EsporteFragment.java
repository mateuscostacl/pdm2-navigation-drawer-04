package com.example.pdm2_navigation_drawer_04.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pdm2_navigation_drawer_04.MainActivity;
import com.example.pdm2_navigation_drawer_04.R;
import com.example.pdm2_navigation_drawer_04.Util;
import com.example.pdm2_navigation_drawer_04.pojo.Categoria;
import com.example.pdm2_navigation_drawer_04.services.Service;

import java.util.ArrayList;
import java.util.List;

public class EsporteFragment extends Fragment {

    View root;

    Categoria c;
    Boolean jaAcertou = false;

    Button btResponder;
    EditText etResposta;
    TextView tvDica1;
    TextView tvDica2;
    TextView tvDica3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_esporte, container, false);

        conectarComViewport();
        escutarCliques();
        enviarDicasPraTela();

        return root;
    }

    private void conectarComViewport() {
        etResposta = root.findViewById(R.id.et_resposta);
        btResponder = root.findViewById(R.id.bt_responder);
        tvDica1 = root.findViewById(R.id.tv_dica1);
        tvDica2 = root.findViewById(R.id.tv_dica2);
        tvDica3 = root.findViewById(R.id.tv_dica3);
    }

    private void escutarCliques() {
        btResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tratarResposta(view);
            }
        });
    }

    private List<Categoria> pegarPorCategorias() {
        Service service = new Service();
        return service.pegarPorCategoria("esportes");
    }

    private void enviarDicasPraTela() {

        List<Categoria> categorias = new ArrayList<>();
        categorias = pegarPorCategorias();

        c = categorias.get(Util.random(categorias.size()));

        tvDica1.setText("1. " + c.getDicas().get(0));
        tvDica2.setText("2. " + c.getDicas().get(1));
        tvDica3.setText("3. " + c.getDicas().get(2));
    }

    public void tratarResposta(View v) {

        if (jaAcertou) {
            Util.mostrarUmaMensagem_Toast(getContext(), "Você já acertou esta.");
            return;
        }

        if (c.getNome().equals(etResposta.getText().toString())) {
            MainActivity.resultado += 1;
            Util.mostrarUmaMensagem_Toast(getContext(), "Parabéns, você acertou!!!");
            jaAcertou = true;
        } else
            Util.mostrarUmaMensagem_Toast(getContext(), "Você errou!!!");
    }

}