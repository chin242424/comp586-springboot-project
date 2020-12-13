package springframework.bluemoonrestspring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import springframework.bluemoonrestspring.rest.temp.JacksonCustomDropDeserializer;
import springframework.bluemoonrestspring.rest.temp.JacksonCustomDropSerializer;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "drops")
@JsonSerialize(using = JacksonCustomDropSerializer.class)
@JsonDeserialize(using = JacksonCustomDropDeserializer.class)
public class Drop extends BaseEntity {

    @Column(name = "drop_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
    private Date date;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "appliance_id")
    private Appliance appliance;

    public Drop(){
        this.date = new Date();
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Appliance getAppliance() {
        return this.appliance;
    }

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }
}
