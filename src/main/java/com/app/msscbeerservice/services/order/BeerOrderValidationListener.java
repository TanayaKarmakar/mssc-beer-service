package com.app.msscbeerservice.services.order;

import com.app.common.models.events.ValidateOrderRequest;
import com.app.common.models.events.ValidateOrderResult;
import com.app.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author t0k02w6 on 05/06/21
 * @project mssc-beer-service
 */
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {
    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) {
        Boolean isValid = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrderDto());

        ValidateOrderResult validateOrderResult = new ValidateOrderResult();
        validateOrderResult.setIsValid(isValid);
        validateOrderResult.setOrderId(validateOrderRequest.getBeerOrderDto().getId());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, validateOrderRequest);
    }
}
