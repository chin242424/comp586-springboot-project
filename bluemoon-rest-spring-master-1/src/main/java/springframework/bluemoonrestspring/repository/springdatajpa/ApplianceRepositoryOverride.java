package springframework.bluemoonrestspring.repository.springdatajpa;


import org.springframework.context.annotation.Profile;
import springframework.bluemoonrestspring.model.Appliance;

@Profile("spring-data-jpa")
public interface ApplianceRepositoryOverride {
    void delete(Appliance appliance);
}
