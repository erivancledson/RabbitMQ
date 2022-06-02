package com.microservico.consumidorestoque.config;

import com.microservico.consumidorestoque.exceptions.TratamentoErroHandler;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO); //a confirmação da mensagem é automatica

        factory.setPrefetchCount(4); //4 mensagens na fila de cada cliente
        factory.setConsumersPerQueue(20); //quantas theades por fila
        //factory.setGlobalQos(true);

        // Utilizando implementação da ErrorHandler
         factory.setErrorHandler(new TratamentoErroHandler());

        return factory;
    }

}