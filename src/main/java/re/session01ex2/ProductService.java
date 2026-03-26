package re.session01ex2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products= new ArrayList<Product>();
    public ProductService() {
        products.add(new Product(1L,"Product 1", 10.0));
        products.add(new Product(2L,"Product 2", 20.0));
        products.add(new Product(3L,"Product 3", 30.0));
    }
    public List<Product> getAllProducts() {
        return products;
    }
    //POST
    public Product addProduct(Product product) {

    products.add(product);
    return product;
    }
    //Put
    public Product updateProduct(Long id, Product product) {
        for(Product p:products){
            if(p.getId()==product.getId()){
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                return p;
            }
        }
        return null;
    }
    //Delete
    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}