package dao;

import entity.Bill;
import entity.BillXML;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.FileUtils;


/**
 * BillDao class
 * 
 * @author viettuts.vn
 */
public class BillDao {
    private static final String BILL_FILE_NAME = "bill.xml";
    private List<Bill> listBills;

    public BillDao() {
        this.listBills = readListBills();
        System.out.println( this.listBills.size());
        if (listBills == null) {
            listBills = new ArrayList<Bill>();
        }
    }

    /**
     * Lưu các đối tượng bill vào file bill.xml
     * 
     * @param bills
     */
    public void writeListBills(List<Bill> bills) {
        BillXML billXML = new BillXML();
        billXML.setBill(bills);
        FileUtils.writeXMLtoFile(BILL_FILE_NAME, billXML);
    }

    /**
     * Đọc các đối tượng bill từ file bill.xml
     * 
     * @return list bill
     */
    public List<Bill> readListBills() {
        List<Bill> list = new ArrayList<Bill>();
        BillXML billXML = (BillXML) FileUtils.readXMLFile(
                BILL_FILE_NAME, BillXML.class);
        if (billXML != null) {
            list = billXML.getBill();
        }
        return list;
    }
    

    /**
     * thêm bill vào listBills và lưu listBills vào file
     * 
     * @param bill
     */
    public void add(Bill bill) {
        int id = 1;
        if (listBills != null && listBills.size() > 0) {
            id = listBills.size() + 1;
        }
        bill.setId(id);
        listBills.add(bill);
        writeListBills(listBills);
    }

    /**
     * cập nhật bill vào listBills và lưu listBills vào file
     * 
     * @param bill
     */
    public void edit(Bill bill) {
        int size = listBills.size();
        for (int i = 0; i < size; i++) {
            if (listBills.get(i).getId() == bill.getId()) {
                listBills.get(i).setCustomerName(bill.getCustomerName());
                listBills.get(i).setPrice(bill.getPrice());
                listBills.get(i).setQuality(bill.getQuality());
                listBills.get(i).setDay(bill.getDay());
                writeListBills(listBills);
                break;
            }
        }
    }

    /**
     * xóa bill từ listBills và lưu listBills vào file
     * 
     * @param bill
     */
    public boolean delete(Bill bill) {
        boolean isFound = false;
        int size = listBills.size();
        for (int i = 0; i < size; i++) {
            if (listBills.get(i).getId() == bill.getId()) {
                bill = listBills.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listBills.remove(bill);
            writeListBills(listBills);
            return true;
        }
        return false;
    }


    

 
    public void sortBillByQuality() {
        Collections.sort(listBills, new Comparator<Bill>() {
            public int compare(Bill bill1, Bill bill2) {
                if (bill1.getQuality()> bill2.getQuality()) {
                    return 1;
                }
                return -1;
            }
        });
    }
    
     public void sortBillByPrice() {
        Collections.sort(listBills, new Comparator<Bill>() {
            public int compare(Bill bill1, Bill bill2) {
                if (bill1.getPrice()> bill2.getPrice()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public List<Bill> getListBills() {
        return listBills;
    }

    public void setListBills(List<Bill> listBills) {
        this.listBills = listBills;
    }
}