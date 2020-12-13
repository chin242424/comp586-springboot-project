package springframework.bluemoonrestspring.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import springframework.bluemoonrestspring.model.Appliance;
import springframework.bluemoonrestspring.model.ApplianceType;
import java.util.Collection;
import java.util.List;

@Repository
public interface ApplianceRepository {

    List<ApplianceType> findApplianceTypes() throws DataAccessException;
    Appliance findById(int id) throws DataAccessException;
    void save(Appliance appliance) throws DataAccessException;
    Collection<Appliance> findAll() throws DataAccessException;
    void delete(Appliance appliance) throws DataAccessException;
}
