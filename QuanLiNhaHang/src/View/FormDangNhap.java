package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controller.sqlconnect;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class FormDangNhap extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField editUserName;
    private JPasswordField editPassword;
    private sqlconnect con;
    private Connection connection;
    private JComboBox<String> cbNhanVien;
    private HashMap<String, String> employeeMap;

    public static void main(String[] args) throws ClassNotFoundException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormDangNhap frame = new FormDangNhap();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FormDangNhap() {
        con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
        connection = con.getCon();
        employeeMap = new HashMap<>();
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 764, 452);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 750, 415);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(0, 0, 376, 420);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(121, 85, 128, 128);
        lblNewLabel_2.setIcon(new ImageIcon(FormDangNhap.class.getResource("/Icon/food.png")));
        panel_1.add(lblNewLabel_2);

        JLabel lblNewLabel_3_2 = new JLabel("Welcome to Restaurant 71 Group");
        lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblNewLabel_3_2.setBounds(10, 223, 356, 47);
        panel_1.add(lblNewLabel_3_2);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(192, 192, 192));
        panel_2.setBounds(374, 0, 376, 420);
        panel.add(panel_2);
        panel_2.setLayout(null);

        editUserName = new JTextField();
        editUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        editUserName.setText("");
        editUserName.setBounds(118, 125, 210, 37);
        panel_2.add(editUserName);
        editUserName.setColumns(10);

        editPassword = new JPasswordField();
        editPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        editPassword.setToolTipText("");
        editPassword.setBounds(118, 204, 210, 37);
        panel_2.add(editPassword);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(FormDangNhap.class.getResource("/Icon/user.png")));
        lblNewLabel.setBounds(48, 108, 48, 54);
        panel_2.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(FormDangNhap.class.getResource("/Icon/pass.png")));
        lblNewLabel_1.setBounds(48, 193, 48, 48);
        panel_2.add(lblNewLabel_1);

        JLabel lblNewLabel_3_1 = new JLabel("Login");
        lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel_3_1.setBounds(84, 51, 223, 47);
        panel_2.add(lblNewLabel_3_1);

        JButton btnDangNhap = new JButton("Đăng Nhập");
        btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDangNhap.setBounds(118, 309, 101, 37);
        panel_2.add(btnDangNhap);

        JButton btnThoat = new JButton("Thoát");
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnThoat.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThoat.setBounds(227, 309, 101, 37);
        panel_2.add(btnThoat);

        cbNhanVien = new JComboBox<>();
        cbNhanVien.setBounds(118, 265, 210, 21);
        panel_2.add(cbNhanVien);
        showCbNhanVien();
    }

    public void login() throws SQLException {
        String userNameString = editUserName.getText().toString().trim();
        String passwordString = new String(editPassword.getPassword()).trim();
        String nhanVien = (String) cbNhanVien.getSelectedItem();

        if (nhanVien != null) {
            String manv = employeeMap.get(nhanVien);

            try {
                String query = "SELECT * FROM taikhoan, nhanvien WHERE username = ? AND password = ? and taikhoan.manv=? and taikhoan.manv=nhanvien.manhanvien";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userNameString);
                preparedStatement.setString(2, passwordString);
                preparedStatement.setString(3, manv);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int quyenTruyCap = resultSet.getInt("quyentruycap");
                    if (quyenTruyCap == 1) {
                        JOptionPane.showMessageDialog(this, "Đăng nhập thành công với quyền nhân viên!");
                        new FormMainTaiQuay(nhanVien).setVisible(true);
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Đăng nhập thành công với quyền admin!");
                        new FormMainQuanLi(nhanVien).setVisible(true);
                        this.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void showCbNhanVien() {
        try {
            CallableStatement preparedStatement = connection.prepareCall("{Call showNhanVien(?)}");
            preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
            while (resultSet.next()) {
                String manv = resultSet.getString("manhanvien");
                String tennv = resultSet.getString("tennv");
                employeeMap.put(tennv, manv);
                cbNhanVien.addItem(tennv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
