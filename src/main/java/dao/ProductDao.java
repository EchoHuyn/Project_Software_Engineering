package dao;

import entity.Product;
import entity.ProductXML;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.FileUtils;


/**
 * ProductDao class
 * 
 * @author viettuts.vn
 */
public class ProductDao {
    private static final String PRODUCT_FILE_NAME = "product.xml";
    private List<Product> listProducts;

    public ProductDao() {
        this.listProducts = readListProducts();
        System.out.println( this.listProducts.size());
        if (listProducts == null) {
            listProducts = new ArrayList<Product>();
        }
    }

    /**
     * Lưu các đối tượng product vào file product.xml
     * 
     * @param products
     */
    public void writeListProducts(List<Product> products) {
        ProductXML productXML = new ProductXML();
        productXML.setProduct(products);
        FileUtils.writeXMLtoFile(PRODUCT_FILE_NAME, productXML);
    }

    /**
     * Đọc các đối tượng product từ file product.xml
     * 
     * @return list product
     */
    public List<Product> readListProducts() {
        List<Product> list = new ArrayList<Product>();
        ProductXML productXML = (ProductXML) FileUtils.readXMLFile(
                PRODUCT_FILE_NAME, ProductXML.class);
        if (productXML != null) {
            list = productXML.getProduct();
        }
        return list;
    }
    

    /**
     * thêm product vào listProducts và lưu listProducts vào file
     * 
     * @param product
     */
    public void add(Product product) {
        int id = 1;
        if (listProducts != null && listProducts.size() > 0) {
            id = listProducts.size() + 1;
        }
        product.setId(id);
        listProducts.add(product);
        writeListProducts(listProducts);
    }

    /**
     * cập nhật product vào listProducts và lưu listProducts vào file
     * 
     * @param product
     */
    public void edit(Product product) {
        int size = listProducts.size();
        for (int i = 0; i < size; i++) {
            if (listProducts.get(i).getId() == product.getId()) {
                listProducts.get(i).setName(product.getName());
                listProducts.get(i).setPrice(product.getPrice());
                listProducts.get(i).setImage(product.getImage());
                listProducts.get(i).setQuality(product.getQuantity());
                writeListProducts(listProducts);
                break;
            }
        }
    }

    /**
     * xóa product từ listProducts và lưu listProducts vào file
     * 
     * @param product
     */
    public boolean delete(Product product) {
        boolean isFound = false;
        int size = listProducts.size();
        for (int i = 0; i < size; i++) {
            if (listProducts.get(i).getId() == product.getId()) {
                product = listProducts.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listProducts.remove(product);
            writeListProducts(listProducts);
            return true;
        }
        return false;
    }


    public void sortProductByName() {
        Collections.sort(listProducts, new Comparator<Product>() {
            public int compare(Product product1, Product product2) {
                return product1.getName().compareTo(product2.getName());
            }
        });
    }

 
    public void sortProductByQuality() {
        Collections.sort(listProducts, new Comparator<Product>() {
            public int compare(Product product1, Product product2) {
                if (product1.getQuantity()> product2.getQuantity()) {
                    return 1;
                }
                return -1;
            }
        });
    }
    
     public void sortProductByPrice() {
        Collections.sort(listProducts, new Comparator<Product>() {
            public int compare(Product product1, Product product2) {
                if (product1.getPrice()> product2.getPrice()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }
}