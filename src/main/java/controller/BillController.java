package controller;

import dao.BillDao;
import dao.ProductDao;
import entity.Bill;
import entity.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.BillView;


public class BillController {
    private BillDao billDao;
    private ProductDao productDao;

    private BillView billView;

    public BillController(BillView view) {
        this.billView = view;
        billDao = new BillDao();
        productDao = new ProductDao();
       
    }
    
     public void showBillView() {
        List<Bill> bills = billDao.getListBills();
        List<Product> billp = productDao.getListProducts();
        billView.setVisible(true);
        billView.showListBills(bills);
        billView.setListProducts(billp);
    }
     
     
    
     
   

  
}
