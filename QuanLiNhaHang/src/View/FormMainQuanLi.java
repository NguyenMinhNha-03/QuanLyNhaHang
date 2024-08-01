package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Menu;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.FlatteningPathIterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.sqlconnect;

public class FormMainQuanLi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDesktopPane desktopPane;
	private String loggedInEmployeeName;
	JPanel Form_Ban;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMainQuanLi frame = new FormMainQuanLi("Logged-in User");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	CardLayout cardLayout;
	public FormMainQuanLi(String employeeName) {
		setTitle("Quan Li Nha Hang");
		loggedInEmployeeName = employeeName;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1377, 771);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(256, 0, 1104, 734);
		contentPane.add(desktopPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 246, 734);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FormMainQuanLi.class.getResource("/Icon/food.png")));
		lblNewLabel.setBounds(60, 10, 159, 134);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 154, 226, 5);
		panel.add(separator);
		
		JButton btn_Form_Lich_su_nhap_hang = new JButton("Lịch sử nhập");
		btn_Form_Lich_su_nhap_hang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLichSuNhapHang chiTietNhapHang = new FormLichSuNhapHang(loggedInEmployeeName);
		        chiTietNhapHang.setVisible(true); 
		        chiTietNhapHang.setSize(desktopPane.getSize());
		        chiTietNhapHang.setLocation(0, 0);
		        desktopPane.add(chiTietNhapHang);
		        try {
		            chiTietNhapHang.setSelected(true);
		        } catch (java.beans.PropertyVetoException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btn_Form_Lich_su_nhap_hang.setIcon(new ImageIcon(FormMainQuanLi.class.getResource("/Icon/khohang.png")));
		btn_Form_Lich_su_nhap_hang.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Form_Lich_su_nhap_hang.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_Form_Lich_su_nhap_hang.setBackground(Color.WHITE);
		btn_Form_Lich_su_nhap_hang.setBounds(30, 374, 206, 46);
		panel.add(btn_Form_Lich_su_nhap_hang);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 657, 226, 11);
		panel.add(separator_1);
		
		JButton btn_Form_Menu = new JButton("Menu");
		btn_Form_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenu menu = new FormMenu(loggedInEmployeeName);
		        menu.setVisible(true); 
		        menu.setSize(desktopPane.getSize());
		        menu.setLocation(0, 0);
		        desktopPane.add(menu);
		        try {
		            menu.setSelected(true);
		        } catch (java.beans.PropertyVetoException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btn_Form_Menu.setIcon(new ImageIcon(FormMainQuanLi.class.getResource("/Icon/foodmenu.png")));
		btn_Form_Menu.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Form_Menu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_Form_Menu.setBackground(Color.WHITE);
		btn_Form_Menu.setBounds(30, 283, 206, 46);
		panel.add(btn_Form_Menu);
		
		JButton btn_Form_lich_ban = new JButton("Lịch sử bàn");
		btn_Form_lich_ban.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLichSuBan ban = new FormLichSuBan(loggedInEmployeeName);
		        ban.setVisible(true); 
		        ban.setSize(desktopPane.getSize());
		        ban.setLocation(0, 0);
		        desktopPane.add(ban);
		        try {
		            ban.setSelected(true);
		        } catch (java.beans.PropertyVetoException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btn_Form_lich_ban.setIcon(new ImageIcon(FormMainQuanLi.class.getResource("/Icon/ban.png")));
		btn_Form_lich_ban.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Form_lich_ban.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_Form_lich_ban.setBackground(Color.WHITE);
		btn_Form_lich_ban.setBounds(30, 568, 206, 46);
		panel.add(btn_Form_lich_ban);
		
		
		JButton btn_Form_NhanVien = new JButton("Nhân viên");
		btn_Form_NhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormNhanVien nhanVien = new FormNhanVien(loggedInEmployeeName);
		        nhanVien.setVisible(true); 
		        nhanVien.setSize(desktopPane.getSize());
		        nhanVien.setLocation(0, 0);
		        desktopPane.add(nhanVien);
		        try {
		            nhanVien.setSelected(true);
		        } catch (java.beans.PropertyVetoException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btn_Form_NhanVien.setIcon(new ImageIcon(FormMainQuanLi.class.getResource("/Icon/nhaphang.png")));
		btn_Form_NhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Form_NhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_Form_NhanVien.setBackground(Color.WHITE);
		btn_Form_NhanVien.setBounds(30, 199, 206, 46);
		panel.add(btn_Form_NhanVien);
		
		JButton btn__Form_menuDatBan_1 = new JButton("Bàn");
		btn__Form_menuDatBan_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormBan ban = new FormBan(loggedInEmployeeName);
				ban.setVisible(true);
				ban.setSize(desktopPane.getSize());
				ban.setLocation(0, 0);
				desktopPane.add(ban);
				try {
		            ban.setSelected(true);
		        } catch (java.beans.PropertyVetoException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btn__Form_menuDatBan_1.setIcon(new ImageIcon(FormMainQuanLi.class.getResource("/Icon/hoadon.png")));
		btn__Form_menuDatBan_1.setHorizontalAlignment(SwingConstants.LEFT);
		btn__Form_menuDatBan_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn__Form_menuDatBan_1.setBackground(Color.WHITE);
		btn__Form_menuDatBan_1.setBounds(30, 470, 206, 46);
		panel.add(btn__Form_menuDatBan_1);
		
		JButton btn_Form_Dang_nhap = new JButton("Đăng xuất");
		btn_Form_Dang_nhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FormDangNhap().setVisible(true);
				setVisible(false);
			}
		});
		btn_Form_Dang_nhap.setIcon(new ImageIcon(FormMainQuanLi.class.getResource("/Icon/exit.png")));
		btn_Form_Dang_nhap.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Form_Dang_nhap.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_Form_Dang_nhap.setBackground(Color.WHITE);
		btn_Form_Dang_nhap.setBounds(30, 678, 206, 46);
		panel.add(btn_Form_Dang_nhap);
	}
}
