package order.management.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import order.management.payload.OrderPayload;

public class CommisionValidator implements ConstraintValidator<Commission, OrderPayload> {

    private static final Double COMMISSION_MIN = 0.5;

    @Override
    public boolean isValid(OrderPayload orderPayload, ConstraintValidatorContext context) {
        double commission = orderPayload.getAmount() / 100D < COMMISSION_MIN ? COMMISSION_MIN : orderPayload.getAmount() / 100;
        return orderPayload.getCommission().equals(commission);
    }

}
