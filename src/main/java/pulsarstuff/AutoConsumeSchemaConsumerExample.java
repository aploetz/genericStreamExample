package pulsarstuff;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionInitialPosition;
import org.apache.pulsar.client.api.schema.GenericRecord;
import org.apache.pulsar.client.impl.schema.generic.GenericAvroRecord;
import org.apache.pulsar.common.schema.KeyValue;

public class AutoConsumeSchemaConsumerExample {

    //private static final Logger log = LoggerFactory.getLogger(AutoConsumeSchemaConsumerExample.class);

    private static final String TOPIC = "persistent://sales_tenant/sales_ns/data-sales.sales_orders";

    public static void main(final String[] args) {

        final String pulsarServiceUrl = "pulsar://127.0.0.1:6650";

        try (PulsarClient client = PulsarClient.builder()
             .serviceUrl(pulsarServiceUrl)
             .build()) {

            try (Consumer<GenericRecord> consumer = client.newConsumer(Schema.AUTO_CONSUME())
                 .topic(TOPIC)
                 .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                 .subscriptionName("sarma-sales")
                 .subscribe()) {

                while (true) {
                    Message<GenericRecord> msg = consumer.receive();

                    final String key = msg.getKey();
                    final GenericRecord record = msg.getValue();
                    KeyValue nativeObj = (KeyValue) record.getNativeObject();
                    GenericAvroRecord nKey = (GenericAvroRecord) nativeObj.getKey();
                    GenericAvroRecord nVal = (GenericAvroRecord) nativeObj.getValue();
                    
                    System.out.printf("key = %s, value = {\"order_code\": \"%s\", \"email_id\": %s, \"platform\": %s, \"phone\": %s, \"state\": %s, \"products\": %d , \"username\": %s}%n",
                        key, nKey.getField("order_code"),
                        nVal.getField("user_email_id"), nVal.getField("user_platform"),
                        nVal.getField("user_phone_number"), nVal.getField("user_state_code"),
                        nVal.getField("order_number_of_products"), nVal.getField("user_name")
                        );
                        
                }
            }
        } catch (PulsarClientException e) {
            //log.error("Failed to consume generic records from pulsar", e);
        	System.out.println("Failed to consume generic records from pulsar=" + e);
            Runtime.getRuntime().exit(-1);
        }
    }

}