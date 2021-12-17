package com.example.recuperacao.Model;
import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_produto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Double venda;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fornecedor", referencedColumnName = "id_fornecedor")
    private Fornecedor fornecedor;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "tipoproduto", referencedColumnName = "id_tipo_produto")
    private TipoProduto tipo;

    public Long getId_produto() {return id_produto;}
    public void setId_produto(Long id_produto) {this.id_produto = id_produto;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Double getPreco() {return preco;}
    public void setPreco(Double preco) {this.preco = preco;}

    public Double getVenda() {return venda;}
    public void setVenda(Double venda) {this.venda = venda;}

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }

    public TipoProduto getTipo() { return tipo; }
    public void setTipo(TipoProduto tipo) { this.tipo = tipo; }

    public Produto() {}

    public Produto(String nome, Double preco, Double venda, Fornecedor fornecedor, TipoProduto tipo) {
        this.nome = nome;
        this.preco = preco;
        this.venda = venda;
        this.fornecedor = fornecedor;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id_produto=" + id_produto +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", venda=" + venda +
                ", fornecedor=" + fornecedor +
                ", tipo=" + tipo +
                '}';
    }
}
