package ro.tuc.ds2022.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.dtos.ConsumptionDto;
import ro.tuc.ds2022.service.ConsumptionService;

import javax.annotation.PostConstruct;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Component
public class Receiver {


    private final ObjectMapper objectMapper;

    @Autowired
    private ConsumptionService consumptionService;

    public Receiver() {
        objectMapper = new ObjectMapper();
    }

    @PostConstruct  // Runs the following code after the bean's creation
    public void init() throws Exception {
        String uri = System.getenv("CLOUDAMQP_URL");
        if (uri == null) uri = "amqps://jaowbnjz:kq-6vyc-7exfLavMbyGsHdhTpcP_NzsL@goose.rmq2.cloudamqp.com/jaowbnjz";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);

        //Recommended settings
        factory.setConnectionTimeout(30000);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queue = "ProiectSD";     //queue name
        boolean durable = false;    //durable - RabbitMQ will never lose the queue if a crash occurs
        boolean exclusive = false;  //exclusive - if queue only will be used by one connection
        boolean autoDelete = false; //autodelete - queue is deleted when last consumer unsubscribes

        channel.queueDeclare(queue, durable, exclusive, autoDelete, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(message);

            ConsumptionDto consumptionDto = objectMapper.readValue(message, ConsumptionDto.class);
            System.out.println(consumptionDto);


            consumptionService.createConsumption(consumptionDto);

        };
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
        });
    }
}
