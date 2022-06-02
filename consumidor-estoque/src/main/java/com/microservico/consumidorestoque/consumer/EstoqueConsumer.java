package com.microservico.consumidorestoque.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import constantes.RabbitMQConstantes;
import dto.EstoqueDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {

    @RabbitListener(queues = RabbitMQConstantes.FILA_ESTOQUE)
    private void consumidor(EstoqueDto estoqueDto) throws JsonProcessingException, InterruptedException {
        System.out.println(estoqueDto.codigoProduto);
        System.out.println(estoqueDto.quantidade);
        System.out.println("------------------------------------");

        //throw  new IllegalArgumentException("Argumento invalido");
    }
}
