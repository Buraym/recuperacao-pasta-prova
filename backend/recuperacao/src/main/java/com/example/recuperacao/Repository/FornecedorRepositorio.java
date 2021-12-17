package com.example.recuperacao.Repository;

import com.example.recuperacao.Model.Fornecedor;
import com.example.recuperacao.Model.TipoProduto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorRepositorio extends PagingAndSortingRepository<Fornecedor, Long>{

    List<Fornecedor> findByCidade(String cidade);
    List<Fornecedor> findByNome(String nome);

}
