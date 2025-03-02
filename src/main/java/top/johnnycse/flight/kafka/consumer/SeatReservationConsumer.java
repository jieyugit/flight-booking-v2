package top.johnnycse.flight.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import top.johnnycse.flight.pojo.Cabin;
import top.johnnycse.flight.repository.CabinRepository;

import java.time.Duration;

@Component
public class SeatReservationConsumer {
    @Autowired
    private CabinRepository cabinRepository;

    @Autowired
    private StringRedisTemplate redisTemplate; // Redis 用于去重


    private final String TOPIC = "flight-tx-topic";
    private final String REDIS_KEY_PREFIX = "kafka_processed:";

    @KafkaListener(topics = TOPIC)
    public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        String message = record.value();
        String messageKey = REDIS_KEY_PREFIX + record.offset();



        //检查redis中是否有该消息
        if (Boolean.TRUE.equals(redisTemplate.hasKey(messageKey))) {
            System.out.println("Duplicate message detected, skipping: " + message);
            acknowledgment.acknowledge();
            return;
        }

        String[] parts = message.split(",");
        Long cabinId = Long.parseLong(parts[0].split(":")[1]);
        String seatNumber = parts[1].split(":")[1];

        try {
            // 使用乐观锁更新mysql
            boolean success = updateRemainingSeatsWithOptimisticLock(cabinId);
            if (!success) {
                throw new RuntimeException("MySQL update failed, retrying...");
            }

            // 成功后存入 Redis 防止重复消费
            redisTemplate.opsForValue().set(messageKey, "1", Duration.ofHours(1));

            // 手动提交offset
            acknowledgment.acknowledge();
        } catch (Exception e) {
            System.out.println("Processing failed, will retry later: " + message);
        }


    }

    private boolean updateRemainingSeatsWithOptimisticLock(Long cabinId) {
        for (int i = 0; i < 3; i++) {
            Cabin cabin = cabinRepository.findByCabinId(cabinId);
            if (cabin == null || cabin.getRemainingSeats() <= 0) {
                return false;
            }

            int updatedRows = cabinRepository.updateRemainingSeat(cabinId, cabin.getVersion());
            if (updatedRows > 0) {
                return true;
            }
        }
        return false;
    }
}
