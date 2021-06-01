package sb.bisht.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import sb.bisht.userservice.model.UserOrder;

import java.util.Date;
import java.util.Optional;

@RestController
public class UserOrderController {

    @Autowired
    KafkaTemplate<String, UserOrder> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topic;

    @PostMapping("/order")
    public String userOrder(@RequestParam Optional<Long> userId, @RequestBody UserOrder userOrder) throws Exception {
        Long user = userId.orElseThrow(() -> new Exception());
        System.out.println("Item is " + user + " & topic is " + topic);
        Integer partition = userOrder.getOrder().charAt(0) > 'N' ? 0 : 1;
        userOrder.setUserId(user);
        userOrder.setOrderPlacedOn(new Date());
        ListenableFuture<SendResult<String, UserOrder>> future = kafkaTemplate.send(topic, partition, null, userOrder);


        future.addCallback(new ListenableFutureCallback<SendResult<String, UserOrder>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Unable to send message=["
                        + userOrder + "] due to : " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, UserOrder> stringStringSendResult) {
                System.out.println("Sent message=[" + userOrder +
                        "] with partition=[" + stringStringSendResult.getRecordMetadata().offset() + "]");
            }
        });
        return "Successfully send...";
    }
}
