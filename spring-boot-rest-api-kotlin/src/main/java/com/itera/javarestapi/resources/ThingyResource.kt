package com.itera.javarestapi.resources


import com.itera.javarestapi.models.Thingy
import com.itera.javarestapi.services.ThingyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.websocket.server.PathParam

@RestController
class ThingyResource(private val thingyService: ThingyService) {

    @GetMapping(path = ["/thingies"])
    fun getAllThingies(): List<Thingy> = thingyService.getThingies()

    @GetMapping(path = ["/thingy"])
    fun getThingy(@PathParam(value = "id") id: Int?): Thingy {
        if (id == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        val thingy = thingyService.getThingy(id);
        return thingy ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping(path = ["/thingy"])
    fun deleteThingy(@PathParam(value = "id") id: Int?): Thingy {
        if (id == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        val thingy = thingyService.deleteThingy(id)
        return thingy ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping(path = ["/thingy"])
    @ResponseBody
    fun addThingy(@RequestBody thingy: Thingy): Thingy {
        return if (thingyService.addThingy(thingy)) {
            thingy
        } else {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Element with id: " + thingy.id + " already exists")
        }
    }

}