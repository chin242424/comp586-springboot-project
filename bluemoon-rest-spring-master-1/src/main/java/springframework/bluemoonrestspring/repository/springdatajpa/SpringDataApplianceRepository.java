package springframework.bluemoonrestspring.repository.springdatajpa;


import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import springframework.bluemoonrestspring.model.Appliance;
import springframework.bluemoonrestspring.model.ApplianceType;
import springframework.bluemoonrestspring.repository.ApplianceRepository;
import springframework.bluemoonrestspring.repository.springdatajpa.ApplianceRepositoryOverride;

import java.util.List;

@Profile("spring-data-jpa")
public interface SpringDataApplianceRepository extends ApplianceRepository, Repository<Appliance, Integer>, ApplianceRepositoryOverride {

    @Override
    @Query("SELECT atype FROM ApplianceType atype ORDER BY atype.name")
    List<ApplianceType> findApplianceTypes() throws DataAccessException;
}
