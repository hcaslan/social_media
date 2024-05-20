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
    @Bean(name = "registerQueue")
    Queue queueA(){
        return new Queue("q.A");
    }
    @Bean(name = "statusUpdateQueue")
    Queue queueB(){
        return new Queue("q.B");
    }

    @Bean(name = "registerDirectExchange")
    DirectExchange registerExchange(){
        return new DirectExchange("exchange.direct.register");
    }
    @Bean(name = "statusUpdateDirectExchange")
    DirectExchange statusUpdateExchange(){
        return new DirectExchange("exchange.direct.statusUpdate");
    }

    @Bean
    Binding bindingA(@Qualifier("registerQueue") Queue queueA,@Qualifier("registerDirectExchange") DirectExchange registerExchange){
        return BindingBuilder
                .bind(queueA)
               .to(registerExchange)
                .with("Routing.A");
    }
    @Bean
    Binding bindingB(@Qualifier("statusUpdateQueue") Queue queueB,@Qualifier("statusUpdateDirectExchange") DirectExchange statusUpdateExchange){
        return BindingBuilder
                .bind(queueB)
                .to(statusUpdateExchange)
                .with("Routing.B");
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
