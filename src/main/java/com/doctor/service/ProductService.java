package com.doctor.service;

import com.doctor.mapper.ProductMapper;
import com.doctor.model.Org;
import com.doctor.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;


    public List<Product> listAll() {
        return productMapper.listAll();
    }

    public void insert(Product product) {
        productMapper.insert(product);
    }
}
