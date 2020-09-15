package com.gustavomaciel.springbintermediate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavomaciel.springbintermediate.domain.Categoria;
import com.gustavomaciel.springbintermediate.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
