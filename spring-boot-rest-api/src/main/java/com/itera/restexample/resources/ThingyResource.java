package com.itera.restexample.resources;

import com.itera.restexample.models.Thingy;
import com.itera.restexample.services.ThingyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ThingyResource {
    private ThingyService thingyService;

    public ThingyResource(ThingyService thingyService) {
        this.thingyService = thingyService;
    }

    @GetMapping(path="/thingies")
    public List<Thingy> getAllThingies(){
        return thingyService.getThingies();
    }

    @GetMapping(path="/thingy")
    public Thingy getThingy(@PathParam(value = "id") Integer id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Thingy thingy = thingyService.getThingy(id);

        if(thingy != null){
            return thingy;
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/thingy")
    public Thingy deleteThingy(@PathParam(value = "id") Integer id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        Thingy thingy = thingyService.deleteThingy(id);

        if(thingy != null){
            return thingy;
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="/thingy")
    @ResponseBody
    public Thingy addThingy(@RequestBody Thingy thingy){

        if(thingyService.addThingy(thingy)){
            return thingy;
        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Element with id: " + thingy.getId() + " already exists");
        }
    }
}
