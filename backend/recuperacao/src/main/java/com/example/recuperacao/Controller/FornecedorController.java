package com.example.recuperacao.Controller;

import com.example.recuperacao.Model.Fornecedor;
import com.example.recuperacao.Model.Produto;
import com.example.recuperacao.Repository.FornecedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepositorio fornecedorRepositorio;

    @GetMapping
    public List<Fornecedor> ListarFornecedores(){ return (List<Fornecedor>) fornecedorRepositorio.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<?> AcharFornecedor(@PathVariable Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepositorio.findById(id);
        if( fornecedor.isPresent() ){
            return new ResponseEntity<>(fornecedor, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("FORNECEDOR NÃO FOI ENCONTRADO", null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity CadastrarFornecedor(@RequestBody Fornecedor fornecedor){
        if(fornecedorRepositorio.findByNome(fornecedor.getNome()).isEmpty() || fornecedorRepositorio.findByCidade(fornecedor.getCidade()).isEmpty()){
            fornecedorRepositorio.save(fornecedor);
            return new ResponseEntity<>(fornecedor, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("FORNECEDOR COM NOME E/OU CIDADE IGUAIS JÁ EXISTEM !!!", null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity MudarFornecedor(@PathVariable("id") Long id, @RequestBody Fornecedor fornecedor){
        return fornecedorRepositorio.findById(id).map(
                antigoFornecedor -> {
                    antigoFornecedor.setNome(fornecedor.getNome());
                    antigoFornecedor.setCidade(fornecedor.getCidade());
                    fornecedorRepositorio.save(antigoFornecedor);
                    return new ResponseEntity(fornecedor, null, HttpStatus.OK);
                })
                .orElseGet(() -> { return new ResponseEntity(fornecedor, null, HttpStatus.NOT_FOUND);}
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity DeletarFornecedor(@PathVariable Long id){
        if (fornecedorRepositorio.findById(id).isPresent()) {
            fornecedorRepositorio.deleteById(id);
            return new ResponseEntity("FORNECEDOR Nº " + id.toString() + " DELETADO COM SUCESSO", null, HttpStatus.OK);
        } else {
            return new ResponseEntity("FORNECEDOR NÃO ENCONTRADO", null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity DeletarTodosFornecedores(){
        fornecedorRepositorio.deleteAll();
        return new ResponseEntity("TODOS OS FORNECEDORES FORAM DELETADOS COM SUCESSO", null, HttpStatus.OK);
    }
}
