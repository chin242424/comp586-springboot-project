package springframework.bluemoonrestspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springframework.bluemoonrestspring.model.*;
import springframework.bluemoonrestspring.repository.*;
import java.util.Collection;


@Service
public class ApplianceServiceImpl implements ApplianceService {

    private CustomerRepository customerRepository;
    private ApplianceRepository applianceRepository;
    private DropRepository dropRepository;
    private ApplianceTypeRepository applianceTypeRepository;

    @Autowired
    public ApplianceServiceImpl(ApplianceRepository applianceRepository, CustomerRepository customerRepository, DropRepository dropRepository, ApplianceTypeRepository applianceTypeRepository) {
        this.customerRepository = customerRepository;
        this.applianceRepository = applianceRepository;

        this.dropRepository = dropRepository;
        this.applianceTypeRepository = applianceTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Appliance findApplianceById(int id) throws DataAccessException {

            Appliance appliance = null;
            try {
                appliance = applianceRepository.findById(id);
            } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e){
                return null;
            }
            return appliance;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Appliance> findAllAppliances() throws DataAccessException {
        return applianceRepository.findAll();
    }

    @Override
    @Transactional
    public void saveAppliance(Appliance appliance) throws DataAccessException {
        applianceRepository.save(appliance);
    }

    @Override
    @Transactional
    public void deleteAppliance(Appliance appliance) throws DataAccessException {
        applianceRepository.delete(appliance);
    }

    @Override
    @Transactional(readOnly = true)
    public Drop findDropById(int dropId) throws DataAccessException {
        Drop drop = null;
        try{
            drop = dropRepository.findById(dropId);
        }catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        }
        return drop;
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<Drop> findAllDrops() throws DataAccessException {
        return dropRepository.findAll();
    }

    @Override
    @Transactional
    public void saveDrop(Drop drop) throws DataAccessException {
        dropRepository.save(drop);
    }

    @Override
    @Transactional
    public void deleteDrop(Drop drop) throws DataAccessException {
        dropRepository.delete(drop);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findCustomerById(int id) throws DataAccessException {
        Customer customer = null;
        try{
            customer = customerRepository.findById(id);
        }catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        }return customer;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Customer> findCustomerByLastName(String lastName) throws DataAccessException {
        return customerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Customer> findAllCustomers() throws DataAccessException {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) throws DataAccessException {
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Customer customer) throws DataAccessException {
        customerRepository.delete(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public ApplianceType findApplianceTypeById(int applianceTypeId) {
        ApplianceType applianceType = null;
        try{
            applianceType = applianceTypeRepository.findById(applianceTypeId);
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        } return applianceType;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<ApplianceType> findAllApplianceTypes() throws DataAccessException {
        return applianceTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<ApplianceType> findApplianceTypes() throws DataAccessException {
        return applianceRepository.findApplianceTypes();
    }

    @Override
    @Transactional
    public void saveApplianceType(ApplianceType applianceType) throws DataAccessException {
        applianceTypeRepository.save(applianceType);
    }

    @Override
    @Transactional
    public void deleteApplianceType(ApplianceType applianceType) throws DataAccessException {
        applianceTypeRepository.delete(applianceType);
    }

}
