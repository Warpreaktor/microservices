package ru.warpreaktor.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.warpreaktor.domain.Category;
import ru.warpreaktor.domain.Product;
import ru.warpreaktor.service.CategoryService;
import ru.warpreaktor.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    //Показать все продукты
    @GetMapping()
    public String getProducts(Model model, @RequestParam(required = false) Long category){

        if (category == null){
            List<Product> products = productService.findAll();
            List<Category> categories = categoryService.findAll();
            model.addAttribute("products", products);
            model.addAttribute("categories", categories);
            return "products";
        }
            //TODO переделать на стримы
            //Фильтруем продукты по категории
            List<Product> products = new ArrayList<>();
            List<Category> categories = categoryService.findAll();
            if (category != null){
                for (Product pr: productService.findAll()){
                    if (pr.getProductCategory() != null) {
                        if (pr.getProductCategory().getId().equals(category)) {
                            products.add(pr);
                        }
                    }
                }
            }
            model.addAttribute("products", products);
            model.addAttribute("categories", categories);
            return "products";
    }
    @GetMapping("/{id}")
    public String getProductById(@PathVariable(name = "id") Long id, Model model){
            Product product = productService.findById(id).get();
            model.addAttribute("product", product);
            return "one-product";
    }
    @GetMapping("new")
    public String newProduct(Model model, @ModelAttribute("error") String error){
        model.addAttribute("product", new Product());
        model.addAttribute("categoryId", new Category());
        model.addAttribute("error", error);
        return "new-product";
    }
    @PostMapping("new")
    public RedirectView postProduct(Model model,
                                    @ModelAttribute Product product,
                                    @RequestParam(required = false) String categoryId,
                                    RedirectAttributes attributes){
        if (product.getName().isEmpty()){
            attributes.addFlashAttribute("error", "field name can not be empty");
            return new RedirectView("/products/new");
        }
        Optional<Category> category = categoryService.findById(Long.parseLong(categoryId));
        if (category.isPresent()){
            product.setProductCategory(category.get());
        }
        model.addAttribute("product", product);
            productService.save(product);
        return new RedirectView("/products");
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception e){
        System.err.println("Request: " + req.getRequestURL() + " raised " + e);

        return "error";
    }
}
