package com.itera.restexample.services

import com.itera.restexample.models.Thingy
import org.springframework.stereotype.Service

@Service
class ThingyService {
    private var thingies = mutableListOf<Thingy>(
        Thingy(1, "Thingy1"),
        Thingy(2, "Thingy2"),
        Thingy(3, "Thingy3"),
        Thingy(4, "Thingy4"),
        Thingy(5, "Thingy5")
    )


    fun getThingies(): List<Thingy> = thingies


    fun addThingy(thingy: Thingy): Boolean {
        return if(thingies.any { t -> t.id == thingy.id }){
            false
        } else thingies.add(thingy)

    }

    fun getThingy(id: Int): Thingy? = thingies.find { t -> t.id == id }


    fun deleteThingy(id: Int): Thingy? {
        val thingy = getThingy(id)

        if (thingy != null) {
            thingies.remove(thingy)
            return thingy
        }
        return null
    }
}
