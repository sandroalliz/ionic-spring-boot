package com.sandro.cursojava.resources;

import com.sandro.cursojava.domain.Order;
import com.sandro.cursojava.domain.Product;
import com.sandro.cursojava.dto.ProductDTO;
import com.sandro.cursojava.resources.utils.URL;
import com.sandro.cursojava.services.OrderService;
import com.sandro.cursojava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        Product product = productService.get(id);
        return ResponseEntity.ok(product);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> listByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                       @RequestParam(value = "categories", defaultValue = "") String categories,
                                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> domains = productService.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> products = domains.map(obj -> new ProductDTO(obj));

        return ResponseEntity.ok(products);
    }
}
