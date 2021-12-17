package com.example.recuperacao.Repository;

import com.example.recuperacao.Model.Fornecedor;
import com.example.recuperacao.Model.Produto;
import com.example.recuperacao.Model.TipoProduto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepositorio extends PagingAndSortingRepository<Produto, Long>{

    List<Produto> findByNome(String nome);

}