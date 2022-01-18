package com.example.demo.service.Impl;

import com.example.demo.model.Exceptions.ProductAlreadyInSC;
import com.example.demo.model.Exceptions.ProductNotFoundException;
import com.example.demo.model.Exceptions.ShoppingCartNotFoundException;
import com.example.demo.model.Exceptions.UserNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.ShoppingCartStatus;
import com.example.demo.repository.InMemoryProductRepository;
import com.example.demo.repository.InMemoryShoppingCartRepository;
import com.example.demo.repository.InMemoryUserRepository;
import com.example.demo.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final InMemoryShoppingCartRepository inMemoryShoppingCartRepository;
    private final InMemoryUserRepository inMemoryUserRepository;
    private final InMemoryProductRepository inMemoryProductRepository;
    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository inMemoryShoppingCartRepository,
                                   InMemoryUserRepository inMemoryUserRepository,
                                   InMemoryProductRepository inMemoryProductRepository){
        this.inMemoryShoppingCartRepository=inMemoryShoppingCartRepository;
        this.inMemoryUserRepository=inMemoryUserRepository;
        this.inMemoryProductRepository=inMemoryProductRepository;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart=inMemoryShoppingCartRepository.findById(cartId).orElseThrow(()->new ShoppingCartNotFoundException(cartId));
        return shoppingCart.getProductList();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return inMemoryShoppingCartRepository.findByUsernameAndStatus(username, ShoppingCartStatus.CREATED).orElseGet(()->{
                User user = inMemoryUserRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
                ShoppingCart shoppingCart=new ShoppingCart(user);
                return inMemoryShoppingCartRepository.save(shoppingCart);
        });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart=inMemoryShoppingCartRepository.findByUsernameAndStatus(username,ShoppingCartStatus.CREATED).orElseThrow(()->new UserNotFoundException(username));
        if (shoppingCart.getProductList().stream().filter(s->s.getId().equals(productId))
                .collect(Collectors.toList()).size()>0) throw new ProductAlreadyInSC(productId,username);
        shoppingCart.getProductList().add(inMemoryProductRepository.findById(productId).orElseThrow(()->new ProductNotFoundException(productId)));
        return shoppingCart;
    }
}
