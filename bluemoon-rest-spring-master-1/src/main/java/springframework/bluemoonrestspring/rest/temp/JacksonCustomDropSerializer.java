package springframework.bluemoonrestspring.rest.temp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import springframework.bluemoonrestspring.model.Appliance;
import springframework.bluemoonrestspring.model.ApplianceType;
import springframework.bluemoonrestspring.model.Customer;
import springframework.bluemoonrestspring.model.Drop;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

public class JacksonCustomDropSerializer extends StdSerializer<Drop> {

    public JacksonCustomDropSerializer() {
        this(null);
    }

    protected JacksonCustomDropSerializer(Class<Drop> t) {
        super(t);
    }

    @Override
    public void serialize(Drop drop, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        if ((drop == null) || (drop.getAppliance() == null)) {
            throw new IOException("Cannot serialize Drop object - drop or drop.appliance is null");
        }
        Format formatter = new SimpleDateFormat("yyyy/MM/dd");
        jgen.writeStartObject();
        if (drop.getId() == null) {
            jgen.writeNullField("id");
        } else {
            jgen.writeNumberField("id", drop.getId());
        }
        jgen.writeStringField("date", formatter.format(drop.getDate()));
        jgen.writeStringField("description", drop.getDescription());

        Appliance appliance = drop.getAppliance();
        jgen.writeObjectFieldStart("appliance");
        if (appliance.getId() == null) {
            jgen.writeNullField("id");
        } else {
            jgen.writeNumberField("id", appliance.getId());
        }
        jgen.writeStringField("name", appliance.getName());
        jgen.writeStringField("purchaseDate", formatter.format(appliance.getPurchaseDate()));

        ApplianceType applianceType = appliance.getType();
        jgen.writeObjectFieldStart("type");
        if (applianceType.getId() == null) {
            jgen.writeNullField("id");
        } else {
            jgen.writeNumberField("id", applianceType.getId());
        }
        jgen.writeStringField("name", applianceType.getName());
        jgen.writeEndObject();

        Customer customer = appliance.getCustomer();
        jgen.writeObjectFieldStart("customer");
        if (customer.getId() == null) {
            jgen.writeNullField("id");
        } else {
            jgen.writeNumberField("id", customer.getId());
        }
        jgen.writeStringField("firstName", customer.getFirstName());
        jgen.writeStringField("lastName", customer.getLastName());
        jgen.writeStringField("address", customer.getAddress());
        jgen.writeStringField("city", customer.getCity());
        jgen.writeStringField("telephone", customer.getTelephone());
        jgen.writeEndObject();
        jgen.writeEndObject();
        jgen.writeEndObject();

    }
}
