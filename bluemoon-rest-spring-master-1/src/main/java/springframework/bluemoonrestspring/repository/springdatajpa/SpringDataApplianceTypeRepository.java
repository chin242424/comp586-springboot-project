package springframework.bluemoonrestspring.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import springframework.bluemoonrestspring.model.ApplianceType;
import springframework.bluemoonrestspring.repository.ApplianceTypeRepository;
import springframework.bluemoonrestspring.repository.springdatajpa.ApplianceTypeRepositoryOverride;

@Profile("spring-data-jpa")
public interface SpringDataApplianceTypeRepository extends ApplianceTypeRepository, Repository<ApplianceType, Integer>, ApplianceTypeRepositoryOverride {

}
