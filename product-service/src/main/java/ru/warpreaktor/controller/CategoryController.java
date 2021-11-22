package ru.warpreaktor.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.warpreaktor.domain.Category;
import ru.warpreaktor.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    public String getCategories(Model model){
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
        return "categories";
    }
    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable(name = "id") Long id, Model model){
            Category category = categoryService.findById(id).get();
            model.addAttribute("category", category);
            return "one-category";
    }
    @GetMapping("new")
    public String newProduct(Model model, @ModelAttribute("error") String error){
        model.addAttribute("category", new Category());
        model.addAttribute("error", error);
        return "category";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception e){
        System.err.println("Request: " + req.getRequestURL() + " raised " + e);

        return "error";
    }
}
