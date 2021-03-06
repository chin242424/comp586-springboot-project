package springframework.bluemoonrestspring.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import springframework.bluemoonrestspring.model.ApplianceType;

@Profile("spring-data-jpa")
public interface ApplianceTypeRepositoryOverride {

    void delete(ApplianceType applianceType);
}
