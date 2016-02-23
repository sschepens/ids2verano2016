package ar.com.caece.ids2.barapp.facturacion.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebastian Schepens on 22/2/2016.
 */
@RestController
public class Controller {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Map<String, String> test(@RequestParam(value = "name", defaultValue = "test") String name) {
        Map<String, String> m = new HashMap<>();
        m.put("test", name);
        return m;
    }
}
