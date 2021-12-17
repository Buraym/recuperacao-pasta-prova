package com.example.recuperacao.Controller;

import com.example.recuperacao.Model.Produto;
import com.example.recuperacao.Model.TipoProduto;
import com.example.recuperacao.Repository.ProdutoRepositorio;
import com.example.recuperacao.Repository.TipoProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tipos")
public class TipoProdutoController {

    @Autowired
    private TipoProdutoRepositorio tipoProdutoRepositorio;

    @GetMapping
    public List<TipoProduto> ListaTipos(){ return (List<TipoProduto>) tipoProdutoRepositorio.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity AcharTipo(@PathVariable Long id) {
        Optional<TipoProduto> tipo = tipoProdutoRepositorio.findById(id);
        if( tipo.isPresent() ){
            return new ResponseEntity(tipo, null, HttpStatus.OK);
        } else {
            return new ResponseEntity("PRODUTO NÃO FOI ENCONTRADO", null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity CadastrarTipo(@RequestBody TipoProduto tipo){
        if( tipoProdutoRepositorio.findByNome(tipo.getNome()).isEmpty()){
            tipoProdutoRepositorio.save(tipo);
            return new ResponseEntity(tipo, null, HttpStatus.OK);
        } else {
            return new ResponseEntity("TIPO DE PRODUTO COM NOME IGUAL JA EXISTE !!!", null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity MudarProduto(@PathVariable("id") Long id, @RequestBody TipoProduto tipo){
        return tipoProdutoRepositorio.findById(id).map(
                antigoTipo -> {
                    antigoTipo.setNome(tipo.getNome());
                    tipoProdutoRepositorio.save(antigoTipo);
                    return new ResponseEntity(tipo, null, HttpStatus.OK);
                })
                .orElseGet(() -> { return new ResponseEntity(tipo, null, HttpStatus.NOT_FOUND);}
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity DeletarProduto(@PathVariable Long id){
        if (tipoProdutoRepositorio.findById(id).isPresent()) {
            tipoProdutoRepositorio.deleteById(id);
            return new ResponseEntity("TIPO DE PRODUTO Nº " + id.toString() + " DELETADO COM SUCESSO", null, HttpStatus.OK);
        } else {
            return new ResponseEntity("TIPO DE PRODUTO NÃO ENCONTRADO", null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity DeletarTodosProdutos(){
        tipoProdutoRepositorio.deleteAll();
        return new ResponseEntity("TODOS OS TIPOS DE PRODUTO FORAM DELETADOS COM SUCESSO", null, HttpStatus.OK);
    }
}
