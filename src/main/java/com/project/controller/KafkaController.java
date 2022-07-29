package com.project.controller;

import com.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, Object> template;

    private final String topic = "kafka-topic";

    @GetMapping("/publish/{word}")
    public String publishMessage(@PathVariable String word) {

        template.send(topic, "Kafka Message Sent: " + word);

        return "String Data Published";

    }

    @GetMapping("/publishJson")
    public String publishMessage() {

        User user = new User(1000, "Adam", new String[] { "420 Rainier Drive", "Seattle", "Washington" });
        template.send(topic, user);

        return "Json Data Published";

    }

}