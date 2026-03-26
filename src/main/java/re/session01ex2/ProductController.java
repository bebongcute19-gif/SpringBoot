package re.session01ex2;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
    // DELETE - Xóa
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        boolean removed = productService.deleteProduct(id);
        return removed ? "Xóa thành công" : "Không tìm thấy sản phẩm";
    }
}
