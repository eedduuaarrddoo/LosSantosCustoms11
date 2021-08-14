package tads.eaj.ufrn.lossantoscustoms.controller;
import tads.eaj.ufrn.lossantoscustoms.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tads.eaj.ufrn.lossantoscustoms.model.Item;
import tads.eaj.ufrn.lossantoscustoms.service.ItemService;
import javax.validation.Valid;
import java.util.List;

@Controller

public class ItemController {

    ItemService service;
    FileStorageService fileStorageService;


    @Autowired
    public void setFileStorageService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Autowired
    public void setService(ItemService service) {
        this.service = service;
    }


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getHome(Model model){
        List<Item> listaItem = service.findAll();
        model.addAttribute("listaItem", listaItem);
        return "index";
    }
    @RequestMapping("/cadastro")
    public String getFormCadastro(Model model){
        Item item = new Item();
        model.addAttribute("Item", item);
        return "cadastro";
    }
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String doSalvar(@ModelAttribute @Valid Item item, Errors errors, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){

        if (errors.hasErrors()){
            return "cadastro";
        }else{
            item.setImageUri(file.getOriginalFilename());
            service.save(item);
            fileStorageService.save(file);


            redirectAttributes.addAttribute("msg", "Cadastro realizado com sucesso");
            return "redirect:/cadastro";
        }
    }
    @RequestMapping("/deletar/{id}")
    public String doDelete(@PathVariable(name = "id") Long id){
        service.delete(id);
        return "redirect:/";
    }
    @RequestMapping("/editar/{id}")
    public ModelAndView getFormEdicao(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("editar");
        Item item = service.findById(id);
        modelAndView.addObject("item", item);
        return modelAndView;
    }
    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String getAdmin(Model model) {
        var listaItem = service.findAll();
        model.addAttribute("listaItem", listaItem);
        return "admin";
    }


    }
