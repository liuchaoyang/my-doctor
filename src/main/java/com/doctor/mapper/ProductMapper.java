package com.doctor.mapper;

import com.doctor.model.Product;

import java.util.List;

public interface ProductMapper {

    int insert(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> listAll();

}