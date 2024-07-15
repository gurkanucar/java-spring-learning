package org.gucardev.rediscachingexample.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@RedisHash(value = "token")
public class Token implements Serializable {

    @Serial
    private static final long serialVersionUID = 2632534028843297157L;

    @Id
    private String id;
    private String info;
    @TimeToLive
    private Long expiration;
}
