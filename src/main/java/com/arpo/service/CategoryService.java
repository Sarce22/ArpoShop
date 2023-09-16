package com.arpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpo.models.CategoryProduct;
import com.arpo.repository.ICategoryRepository;



@Service
public class CategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	public List<CategoryProduct> listCategory (){
		return categoryRepository.findAll();
	}
	
	 public CategoryProduct findById(Long id) {
	    return categoryRepository.findById(id).orElse(null);
	 }

	 public CategoryProduct saveCategory(CategoryProduct category) {
        return categoryRepository.save(category);
	 }

	 public void deleteCategory(Long id) {
		 categoryRepository.deleteById(id);
	 }
}
