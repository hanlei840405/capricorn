package com.galaxy.framework.capricorn.consumer;

import com.galaxy.framework.capricorn.entity.Owner;
import com.galaxy.framework.capricorn.service.OwnerService;
import com.galaxy.framework.pisces.vo.capricorn.OwnerVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class OwnerConsumer {

    @Autowired
    private OwnerService ownerService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "owner.save", durable = "true"),
            exchange = @Exchange(value = "basic", type = "topic", durable = "true"), key = "basic.owner.save.*"))
    public void saveOwner(@Payload OwnerVo ownerVo) {
        if (ownerVo != null) {
            Owner owner = new Owner();
            BeanUtils.copyProperties(ownerVo, owner);
            owner.setStatus("启用");
            ownerService.save(owner);
            if (StringUtils.isEmpty(ownerVo.getCode())) {
                ownerVo.setCode(owner.getCode());
            }
        }
    }
}
