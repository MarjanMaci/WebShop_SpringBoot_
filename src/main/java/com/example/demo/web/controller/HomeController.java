package com.example.demo.web.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Exceptions.ProductNotFoundException;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ManufacturerService;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;
    public HomeController(ProductService productService,CategoryService categoryService, ManufacturerService manufacturerService){
        this.productService=productService;
        this.categoryService=categoryService;
        this.manufacturerService=manufacturerService;
    }

    @GetMapping
    public String getProducts(HttpServletRequest request,Model model){
        User user= (User) request.getSession().getAttribute("user");
        model.addAttribute("productList",productService.findAll());
        model.addAttribute("user",user);
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id,HttpServletRequest request,Model model){
        productService.deleteById(id);
        model.addAttribute("user",request.getSession().getAttribute("user"));
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id,Model model){
        model.addAttribute("product", (Product)productService.findById(id).orElseThrow(()->new ProductNotFoundException(id)));
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("manufacturer", manufacturerService.findAll());
        return "edit-product";
    }

    @PostMapping("/edit/{id}")
    public String editProduct2(@PathVariable Long id,HttpServletRequest request,Model model){
        Product pro=(Product) productService.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        Long catId=pro.getCategory().getId();
        Long manId=pro.getManufacturer().getId();
        String name=request.getParameter("name");
        Double price=Double.parseDouble(request.getParameter("price").toString());
        Integer quantity=Integer.parseInt(request.getParameter("quantity").toString());
        productService.edit(id,name,price,quantity,catId,manId);
        model.addAttribute("user",request.getSession().getAttribute("user"));
        return "redirect:/home";
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("manufacturer", manufacturerService.findAll());
        return "add-product";
    }
    @PostMapping("/add")
    public String addProduct2(HttpServletRequest request, Model model){
        String name=request.getParameter("name");
        Double price=Double.parseDouble(request.getParameter("price").toString());
        Integer quantity=Integer.parseInt(request.getParameter("quantity").toString());
        Category category=categoryService.findByName(request.getParameter("category"));
        Manufacturer manufacturer=manufacturerService.findByName(request.getParameter("manufacturer"));
        productService.save(name,price,quantity,category.getId(),manufacturer.getId());
        model.addAttribute("user",request.getSession().getAttribute("user"));
        return "redirect:/home";
    }
}
