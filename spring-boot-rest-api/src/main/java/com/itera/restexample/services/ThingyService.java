package com.itera.restexample.services;

import com.itera.restexample.models.Thingy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ThingyService {
    private ArrayList<Thingy> thingies = new ArrayList<>(Arrays.asList(
            new Thingy(1, "Thingy1"),
            new Thingy(2, "Thingy2"),
            new Thingy(3, "Thingy3"),
            new Thingy(4, "Thingy4"),
            new Thingy(5, "Thingy5")
    ));

    public List<Thingy> getThingies(){
        return thingies;
    }

    public boolean addThingy(Thingy thingy){
        if(thingies.stream().anyMatch(t -> t.getId() == thingy.getId())){
            return false;
        }

        return thingies.add(thingy);
    }

    public Thingy getThingy(int id){
        return thingies.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public Thingy deleteThingy(int id){
        Thingy thingy = getThingy(id);

        if(thingy != null){
            thingies.remove(thingy);
            return thingy;
        }

        return null;
    }

}
