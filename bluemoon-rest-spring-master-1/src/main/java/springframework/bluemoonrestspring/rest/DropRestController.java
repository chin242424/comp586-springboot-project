package springframework.bluemoonrestspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springframework.bluemoonrestspring.model.Drop;
import springframework.bluemoonrestspring.service.ApplianceService;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/drops")
public class DropRestController {

    @Autowired
    private ApplianceService applianceService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<Drop>> getAllDrops(){
        Collection<Drop> drops = new ArrayList<Drop>();
        drops.addAll(this.applianceService.findAllDrops());
        if (drops.isEmpty()){
            return new ResponseEntity<Collection<Drop>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Drop>>(drops, HttpStatus.OK);
    }

    @RequestMapping(value = "/{dropId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Drop> getDrop(@PathVariable("dropId") int dropId){
        Drop drop = this.applianceService.findDropById(dropId);
        if(drop == null){
            return new ResponseEntity<Drop>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Drop>(drop, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Drop> addDrop(@RequestBody @Valid Drop drop, BindingResult bindingResult, UriComponentsBuilder ucBuilder){

        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (drop == null) || (drop.getAppliance() == null)){

            return new ResponseEntity<Drop>(headers, HttpStatus.BAD_REQUEST);
        }
        this.applianceService.saveDrop(drop);
        headers.setLocation(ucBuilder.path("/api/drops/{id}").buildAndExpand(drop.getId()).toUri());
        return new ResponseEntity<Drop>(drop, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{dropId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Drop> updateDrop(@PathVariable("dropId") int dropId, @RequestBody @Valid Drop drop, BindingResult bindingResult){
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (drop == null) || (drop.getAppliance() == null)){

            return new ResponseEntity<Drop>(headers, HttpStatus.BAD_REQUEST);
        }
        Drop currentDrop = this.applianceService.findDropById(dropId);
        if(currentDrop == null){
            return new ResponseEntity<Drop>(HttpStatus.NOT_FOUND);
        }
        currentDrop.setDate(drop.getDate());
        currentDrop.setDescription(drop.getDescription());
        currentDrop.setAppliance(drop.getAppliance());
        this.applianceService.saveDrop(currentDrop);
        return new ResponseEntity<Drop>(currentDrop, HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/{dropId}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<Void> deleteDrop(@PathVariable("dropId") int dropId){
        Drop drop = this.applianceService.findDropById(dropId);
        if(drop == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.applianceService.deleteDrop(drop);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
