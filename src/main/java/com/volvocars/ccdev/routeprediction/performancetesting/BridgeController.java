package com.volvocars.ccdev.routeprediction.performancetesting;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BridgeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BridgeController.class);

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        //LOGGER.info("Hit send endpoint");

        String topic = "bridgetopic.t";
        String message = "Message from controller";
        kafkaTemplate.send(topic, "YV12345678", message);

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
