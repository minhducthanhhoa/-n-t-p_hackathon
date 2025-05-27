package com.data.on_tap_hackathon.controller;

import com.data.on_tap_hackathon.model.Product;
import com.data.on_tap_hackathon.service.ProductService;
import com.data.on_tap_hackathon.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService = new ProductServiceImpl();

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAll());
        return "list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "form";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        if (product == null) {
            model.addAttribute("errorMessage", "Sản phẩm không tồn tại");
            return "error";
        }
        model.addAttribute("product", product);
        return "form";
    }

    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute("product") Product product,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        productService.update(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/product";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("products", productService.search(keyword));
        return "list";
    }
}
