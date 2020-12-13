package springframework.bluemoonrestspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springframework.bluemoonrestspring.model.Appliance;
import springframework.bluemoonrestspring.model.ApplianceType;
import springframework.bluemoonrestspring.service.ApplianceService;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;


@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/appliances")
public class ApplianceRestController {


    @Autowired
    private ApplianceService applianceService;


    @RequestMapping(value = "/{applianceId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Appliance> getAppliance(@PathVariable("applianceId") int applianceId){
        Appliance appliance = this.applianceService.findApplianceById(applianceId);
        if(appliance == null){
            return new ResponseEntity<Appliance>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Appliance>(appliance, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<Appliance>> getAppliances(){
        Collection<Appliance> appliances = this.applianceService.findAllAppliances();
        if(appliances.isEmpty()){
            return new ResponseEntity<Collection<Appliance>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Appliance>>(appliances, HttpStatus.OK);
    }


    @RequestMapping(value = "/appliancetypes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<ApplianceType>> getApplianceTypes(){
        return new ResponseEntity<Collection<ApplianceType>>(this.applianceService.findApplianceTypes(), HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Appliance> addAppliance(@RequestBody @Valid Appliance appliance, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
//
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (appliance == null)){
//
            return new ResponseEntity<Appliance>(headers, HttpStatus.BAD_REQUEST);
        }
        this.applianceService.saveAppliance(appliance);
        headers.setLocation(ucBuilder.path("/api/appliances/{id}").buildAndExpand(appliance.getId()).toUri());
        return new ResponseEntity<Appliance>(appliance, headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{applianceId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Appliance> updateAppliance(@PathVariable("applianceId") int applianceId, @RequestBody @Valid Appliance appliance, BindingResult bindingResult){
//
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (appliance == null)){
//
            return new ResponseEntity<Appliance>(headers, HttpStatus.BAD_REQUEST);
        }
        Appliance currentAppliance = this.applianceService.findApplianceById(applianceId);
        if(currentAppliance == null){
            return new ResponseEntity<Appliance>(HttpStatus.NOT_FOUND);
        }
        currentAppliance.setPurchaseDate(appliance.getPurchaseDate());
        currentAppliance.setName(appliance.getName());
        currentAppliance.setType(appliance.getType());
        currentAppliance.setCustomer(appliance.getCustomer());
        this.applianceService.saveAppliance(currentAppliance);
        return new ResponseEntity<Appliance>(currentAppliance, HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/{applianceId}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<Void> deleteAppliance(@PathVariable("applianceId") int applianceId){
        Appliance appliance = this.applianceService.findApplianceById(applianceId);
        if(appliance == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.applianceService.deleteAppliance(appliance);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}


