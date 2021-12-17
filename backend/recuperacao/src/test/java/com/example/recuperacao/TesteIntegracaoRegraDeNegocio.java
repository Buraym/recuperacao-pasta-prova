package com.example.recuperacao;

import com.example.recuperacao.Model.Fornecedor;
import com.example.recuperacao.Model.Produto;
import com.example.recuperacao.Model.TipoProduto;
import com.example.recuperacao.Repository.FornecedorRepositorio;
import com.example.recuperacao.Repository.ProdutoRepositorio;
import com.example.recuperacao.Repository.TipoProdutoRepositorio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.google.gson.Gson;


@SpringBootTest
@AutoConfigureMockMvc
public class TesteIntegracaoRegraDeNegocio {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private TipoProdutoRepositorio tipoProdutoRepositorio;

    @Autowired
    private FornecedorRepositorio fornecedorRepositorio;

    @AfterEach
    void tearDown() {
        produtoRepositorio.deleteAll();
        tipoProdutoRepositorio.deleteAll();
        fornecedorRepositorio.deleteAll();
    }

    @Test
    void RESET_GERAL_E_CADASTRO_GERAL() throws Exception {
        TipoProduto t1 = new TipoProduto("Alimenticio");
        Fornecedor f1 = new Fornecedor("MacDonalds", "Foz do iguaçu");
        Produto p1 = new Produto("MacBurguer Pro", 12.0, 18.0, f1, t1);
        Produto p2 = new Produto("MacBurguer EPIC", 12.0, 18.0, f1, t1);
        Produto p3 = new Produto("MacBurguer ULTIMATE", 12.0, 18.0, f1, t1);

        this.mockMvc.perform(delete("/produtos")).andExpect(status().isOk());
        this.mockMvc.perform(delete("/fornecedores")).andExpect(status().isOk());
        this.mockMvc.perform(delete("/tipos")).andExpect(status().isOk());

        this.mockMvc.perform(get("/fornecedores"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
        this.mockMvc.perform(post("/fornecedores")
                .content(new Gson().toJson(f1))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/tipos"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
        this.mockMvc.perform(post("/tipos")
                .content(new Gson().toJson(t1))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
        this.mockMvc.perform(post("/produtos")
                .content(new Gson().toJson(p1))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void REGRA_DE_NEGOCIO_CADASTRO_IGUAL() throws Exception {
        TipoProduto t1 = new TipoProduto("Alimenticio"); // eles são
        TipoProduto t2 = new TipoProduto("Alimenticio"); // iguais

        Fornecedor f1 = new Fornecedor("MacDonalds", "Foz do iguaçu"); // eles são
        Fornecedor f2 = new Fornecedor("MacDonalds", "Foz do iguaçu"); // iguais


        Produto p1 = new Produto("MacBurguer Pro", 12.0, 18.0, f1, t1); // eles são
        Produto p2 = new Produto("MacBurguer Pro", 12.0, 18.0, f1, t1); // iguais
        Produto p3 = new Produto("cyberpunk 2077", 300.0, 250.0, f1, t1); // dá prejuizo para quem vende

        /*this.mockMvc.perform(delete("/produtos"));
        this.mockMvc.perform(delete("/fornecedores"));
        this.mockMvc.perform(delete("/tipos"));*/


        this.mockMvc.perform(post("/fornecedores")
                .content(new Gson().toJson(f1))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/fornecedores")
                .content(new Gson().toJson(f2))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());


        this.mockMvc.perform(post("/tipos")
                .content(new Gson().toJson(t1))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/tipos")
                .content(new Gson().toJson(t2))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post("/produtos")
                .content(new Gson().toJson(p1))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/produtos")
                .content(new Gson().toJson(p2))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post("/produtos")
                .content(new Gson().toJson(p3))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
