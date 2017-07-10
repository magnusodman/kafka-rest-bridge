package com.volvocars.ccdev.routeprediction.performancetesting;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BridgeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BridgeController.class);

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void send(@RequestBody String positionMessageString) throws IOException {
        LOGGER.info("Hit send endpoint {}", positionMessageString);

        ObjectMapper objectMapper = new ObjectMapper();
        PositionMessage positionMessage = objectMapper.readValue(positionMessageString, PositionMessage.class);
        String topic = "bridgetopic.t";
        String message = "Message from controller";
        kafkaTemplate.send(topic, positionMessage.getVin(), objectMapper.writeValueAsString(positionMessage));

        /*
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);

        // register a callback with the listener to receive the result of the send asynchronously
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("sent message='{}' with offset={}", message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("unable to send message='{}'", message, ex);
            }
        });
        */
    }
}
