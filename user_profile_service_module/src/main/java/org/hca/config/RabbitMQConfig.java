package org.hca.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean(name = "profileUpdateQueue")
    Queue queueC(){
        return new Queue("q.C");
    }
    @Bean(name = "profileUpdateDirectExchange")
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct.profileUpdate");
    }

    @Bean
    Binding bindingC(@Qualifier("profileUpdateQueue") Queue queueC,@Qualifier("profileUpdateDirectExchange") DirectExchange exchange){
        return BindingBuilder
                .bind(queueC)
                .to(exchange)
                .with("Routing.C");
    }
    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
