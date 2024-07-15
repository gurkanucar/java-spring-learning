package org.gucardev.redisstorageexample.notificationExample;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "UserNotification", timeToLive = 1000)
@Data
public class UserNotification {
    @Id
    private String username;
    @Indexed
    private String surname;
    private Long lastNotificationTime;
    @Indexed
    private Boolean notificationDelivered;
}
