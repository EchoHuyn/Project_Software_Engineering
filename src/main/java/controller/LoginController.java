/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDao;
import entity.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.LoginView;
import view.MenuView;

public class LoginController {
        private UserDao userDao;
        private LoginView loginView;
    
        public LoginController(LoginView view) {
            this.loginView = view;
            this.userDao = new UserDao();
        }

        public void showLoginView() {
            loginView.setVisible(true);
        }

        /**
         * Lớp LoginListener 
         * chứa cài đặt cho sự kiện click button "Login"
         * 
         * @author viettuts.vn
         */
        class LoginListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                User user = loginView.getUser();
                if (userDao.checkUser(user)) {
                    // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
//                    studentView = new StudentView();
    //                StudentController studentController = new StudentController(studentView);
    //                studentController.showStudentView();


                    MenuView menuview = new MenuView();
                    menuview.setVisible(true);
                    loginView.setVisible(false);
                } else {
                    loginView.showMessage("username hoặc password không đúng.");
                }
            }
        }

}
