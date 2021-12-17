package com.example.recuperacao.Model;
import javax.persistence.*;

@Entity
@Table(name = "tipoproduto")
public class TipoProduto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_tipo_produto;

    @Column(nullable = false)
    private String nome;


    public Long getId_tipo_produto() { return id_tipo_produto; }
    public void setId_tipo_produto(Long id_tipo_produto) {this.id_tipo_produto = id_tipo_produto;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public TipoProduto() {}

    public TipoProduto(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TipoProduto{" +
                "id_tipo_produto=" + id_tipo_produto +
                ", nome='" + nome + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
