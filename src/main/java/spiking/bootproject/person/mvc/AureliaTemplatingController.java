package spiking.bootproject.person.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cspiking
 */
@Controller
public class AureliaTemplatingController
{
    @GetMapping("/aureliajspm/aurelia_src/**/{filepath:.*-templated\\.html}")
    public String handle(Model model, @PathVariable String filepath, HttpServletRequest request) {
        model.addAttribute("name", filepath);
        return request.getServletPath();
    }
}
