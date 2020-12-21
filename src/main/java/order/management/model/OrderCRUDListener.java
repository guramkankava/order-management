package order.management.model;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import order.management.exception.ApiException;
import order.management.payload.OrderPayload;

@Component
public class OrderCRUDListener extends AbstractMongoEventListener<Order> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<Order> beforeSaveEvent) {
        int payloadSize = beforeSaveEvent.getDocument().toJson().getBytes().length;
        if(payloadSize > OrderPayload.PAYLOAD_LIMIT) {
            throw new ApiException("Invalid order object, size exceeds permited 1000 bytes", 
                    HttpStatus.BAD_REQUEST, 
                    "object.size.limit",
                    new String [] {OrderPayload.PAYLOAD_LIMIT.toString()}
                    );
        }
        beforeSaveEvent.getDocument().append("payloadSize", payloadSize);
        super.onBeforeSave(beforeSaveEvent);
    }

}
