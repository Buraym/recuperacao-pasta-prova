package com.example.recuperacao.Model;
import javax.persistence.*;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_fornecedor;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cidade;

    public Long getId_fornecedor() { return id_fornecedor; }
    public void setId_fornecedor(Long id_fornecedor) { this.id_fornecedor = id_fornecedor; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public Fornecedor() {}

    public Fornecedor(String nome, String cidade) {
        this.id_fornecedor = id_fornecedor;
        this.nome = nome;
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id_fornecedor=" + id_fornecedor +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
