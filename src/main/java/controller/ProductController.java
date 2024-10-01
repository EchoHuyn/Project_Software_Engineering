package controller;

import dao.ProductDao;
import entity.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.MenuView;
import view.ProductView;


public class ProductController {
    private ProductDao productDao;
    private ProductView productView;

    public ProductController(ProductView view) {
        this.productView = view;
        productDao = new ProductDao();

        view.addAddProductListener(new AddProductListener());
        view.addEdiProductListener(new EditProductListener());
        view.addDeleteProductListener(new DeleteProductListener());
        view.addClearListener(new ClearProductListener());
        view.addSortProductQualityListener(new SortProductQualityListener());
        view.addSortProductPriceListener(new SortProductPriceListener());
        view.addSortProductNameListener(new SortProductNameListener());
        view.addListProductSelectionListener(new ListProductSelectionListener());
        
        
        view.addSorbackButtonListener(new backButtonListener());
        
    }

    public void showProductView() {
        List<Product> productList = productDao.getListProducts();
        productView.setVisible(true);
        productView.showListProducts(productList);
    }


    class AddProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Product product = productView.getProductInfo();
            if (product != null) {
                productDao.add(product);
                productView.showProduct(product);
                productView.showListProducts(productDao.getListProducts());
                productView.showMessage("Thêm thành công!");
            }
        }
    }


    class EditProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Product product = productView.getProductInfo();
            if (product != null) {
                productDao.edit(product);
                productView.showProduct(product);
                productView.showListProducts(productDao.getListProducts());
                productView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp DeleteProductListener 
     * chứa cài đặt cho sự kiện click button "Delete"
     * 
     * @author viettuts.vn
     */
    class DeleteProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Product product = productView.getProductInfo();
            if (product != null) {
                productDao.delete(product);
                productView.clearProductInfo();
                productView.showListProducts(productDao.getListProducts());
                productView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp ClearProductListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     * 
     * @author viettuts.vn
     */
    class ClearProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            productView.clearProductInfo();
        }
    }

    /**
     * Lớp SortProductGPAListener 
     * chứa cài đặt cho sự kiện click button "Sort By GPA"
     * 
     * @author viettuts.vn
     */
    class SortProductQualityListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            productDao.sortProductByQuality();
            productView.showListProducts(productDao.getListProducts());
        }
    }
    
    
     class SortProductPriceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            productDao.sortProductByPrice();
            productView.showListProducts(productDao.getListProducts());
        }
    }


    /**
     * Lớp SortProductGPAListener 
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     * 
     * @author viettuts.vn
     */
    class SortProductNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            productDao.sortProductByName();
            productView.showListProducts(productDao.getListProducts());
        }
    }

    /**
     * Lớp ListProductSelectionListener 
     * chứa cài đặt cho sự kiện chọn product trong bảng product
     * 
     * @author viettuts.vn
     */
    class ListProductSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            productView.fillProductFromSelectedRow();
        }
    }
    
    
    class backButtonListener implements ActionListener{
         public void actionPerformed(ActionEvent e) {
             productView.setVisible(false);
             MenuView menuv = new MenuView();
             menuv.setVisible(true);
             
        }
    }
}
