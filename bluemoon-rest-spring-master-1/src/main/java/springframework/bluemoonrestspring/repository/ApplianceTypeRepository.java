package springframework.bluemoonrestspring.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import springframework.bluemoonrestspring.model.ApplianceType;
import java.util.Collection;

@Repository
public interface ApplianceTypeRepository {

    ApplianceType findById(int id) throws DataAccessException;

    Collection<ApplianceType> findAll() throws DataAccessException;

    void save(ApplianceType applianceType) throws DataAccessException;

    void delete(ApplianceType applianceType) throws DataAccessException;
}
