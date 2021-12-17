package com.example.recuperacao.Controller;

import com.example.recuperacao.Model.Produto;
import com.example.recuperacao.Repository.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @GetMapping
    public List<Produto> ListaProdutos(){ return (List<Produto>) produtoRepositorio.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<?> AcharProduto(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepositorio.findById(id);
        if( produto.isPresent() ){
            return new ResponseEntity<>(produto, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("PRODUTO NÃO FOI ENCONTRADO", null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity CadastrarProduto(@RequestBody Produto produto){
        if ( produtoRepositorio.findByNome(produto.getNome()).isEmpty() && ((produto.getPreco().compareTo(produto.getVenda())) < 0) ) {
            produtoRepositorio.save(produto);
            return new ResponseEntity(produto, null, HttpStatus.OK);
        } else {
            return new ResponseEntity("PRODUTO COM VALOR DE VENDA MAIOR QUE O VALOR DE COMPRA E/OU NOME IGUAL A OUTRO PRODUTO !!!", null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity MudarProduto(@PathVariable("id") Long id, @RequestBody Produto produto){
        return produtoRepositorio.findById(id).map(
                antigoProduto -> {
                    antigoProduto.setNome(produto.getNome());
                    antigoProduto.setPreco(produto.getPreco());
                    antigoProduto.setVenda(produto.getVenda());
                    antigoProduto.setFornecedor(produto.getFornecedor());
                    antigoProduto.setTipo(produto.getTipo());
                    produtoRepositorio.save(antigoProduto);
                    return new ResponseEntity(produto, null, HttpStatus.OK);
                })
                .orElseGet(() -> { return new ResponseEntity(produto, null, HttpStatus.NOT_FOUND);}
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity DeletarProduto(@PathVariable Long id){
        if (produtoRepositorio.findById(id).isPresent()) {
            produtoRepositorio.deleteById(id);
            return new ResponseEntity("PRODUTO Nº " + id.toString() + " DELETADO COM SUCESSO", null, HttpStatus.OK);
        } else {
            return new ResponseEntity("PRODUTO NÃO ENCONTRADO", null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity DeletarTodosProduto(){
        produtoRepositorio.deleteAll();
        return new ResponseEntity("TODOS OS PRODUTOS FORAM DELETADOS COM SUCESSO", null, HttpStatus.OK);
    }
}
