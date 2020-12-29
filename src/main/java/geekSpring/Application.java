package geekSpring;

import geekSpring.Videos.Serial;
import geekSpring.Videos.Series;
import geekSpring.Videos.MainActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@SpringBootApplication
@EnableScheduling
public class Application {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Queue myQueue() {
        return new Queue("Test", true);
    }

    @RabbitListener(queues = "Test")
    public void list(String msg) {
        System.out.println("Message from my queue: " + msg);
        StringReader reader = new StringReader(msg);
        ObjectMapper mapper = new ObjectMapper();
        try {
            MainActor subscriber = mapper.readValue(reader, MainActor.class);
            System.out.println(subscriber.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 5000L)
    private void SendMessage() {
        MainActor mainActor = new MainActor("Chris ","Taker" , "Bezbashenye",
                new Serial("Bezbashenye",100,"16.10.2010"));
        mainActor.addSeries(new Series("Mahinator","54.15"));
        mainActor.addSeries(new Series("Bezdomnye","52.13"));
        mainActor.addSeries(new Series("Storm","50.10"));
        System.out.println(".....Объект.....");
        System.out.println(mainActor.toString());
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, mainActor);

        } catch (IOException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend("example-777", "", writer.toString());
    }


}
