package com.example.recuperacao.Repository;

import com.example.recuperacao.Model.TipoProduto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoProdutoRepositorio extends PagingAndSortingRepository<TipoProduto, Long> {
    List<TipoProduto> findByNome(String nome);
}
