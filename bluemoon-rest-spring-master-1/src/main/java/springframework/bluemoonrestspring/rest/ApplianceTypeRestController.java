package springframework.bluemoonrestspring.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springframework.bluemoonrestspring.model.ApplianceType;
import springframework.bluemoonrestspring.service.ApplianceService;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/appliancetypes")
public class ApplianceTypeRestController {


    @Autowired
    private ApplianceService applianceService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<ApplianceType>> getAllApplianceTypes(){
        Collection<ApplianceType> applianceTypes = new ArrayList<ApplianceType>();
        applianceTypes.addAll(this.applianceService.findAllApplianceTypes());
        if (applianceTypes.isEmpty()){
            return new ResponseEntity<Collection<ApplianceType>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<ApplianceType>>(applianceTypes, HttpStatus.OK);
    }


    @RequestMapping(value = "/{applianceTypeId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ApplianceType> getApplianceType(@PathVariable("applianceTypeId") int applianceTypeId){
        ApplianceType applianceType = this.applianceService.findApplianceTypeById(applianceTypeId);
        if(applianceType == null){
            return new ResponseEntity<ApplianceType>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ApplianceType>(applianceType, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ApplianceType> addApplianceType(@RequestBody @Valid ApplianceType applianceType, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
//
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (applianceType == null)){
//
            return new ResponseEntity<ApplianceType>(headers, HttpStatus.BAD_REQUEST);
        }
        this.applianceService.saveApplianceType(applianceType);
        headers.setLocation(ucBuilder.path("/api/appliancetypes/{id}").buildAndExpand(applianceType.getId()).toUri());
        return new ResponseEntity<ApplianceType>(applianceType, headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{applianceTypeId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<ApplianceType> updateApplianceType(@PathVariable("applianceTypeId") int applianceTypeId, @RequestBody @Valid ApplianceType applianceType, BindingResult bindingResult){
//
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (applianceType == null)){
//
            return new ResponseEntity<ApplianceType>(headers, HttpStatus.BAD_REQUEST);
        }
        ApplianceType currentApplianceType = this.applianceService.findApplianceTypeById(applianceTypeId);
        if(currentApplianceType == null){
            return new ResponseEntity<ApplianceType>(HttpStatus.NOT_FOUND);
        }
        currentApplianceType.setName(applianceType.getName());
        this.applianceService.saveApplianceType(currentApplianceType);
        return new ResponseEntity<ApplianceType>(currentApplianceType, HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/{applianceTypeId}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<Void> deleteApplianceType(@PathVariable("applianceTypeId") int applianceTypeId){
        ApplianceType applianceType = this.applianceService.findApplianceTypeById(applianceTypeId);
        if(applianceType == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.applianceService.deleteApplianceType(applianceType);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
