package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import Controller.sqlconnect;

import javax.swing.JTextField;

public class FormMainTaiQuay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDesktopPane desktopPane;
	private String loggedInEmployeeName;
	JPanel Form_Ban;
	private sqlconnect con;
	private Connection connection;
	private String manvString;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMainTaiQuay frame = new FormMainTaiQuay("Logged-in User");
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
	public FormMainTaiQuay(String employeeName) {
		setTitle("Quan Li Nha Hang");
		con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
	    connection = con.getCon();
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
		lblNewLabel.setIcon(new ImageIcon(FormMainTaiQuay.class.getResource("/Icon/food.png")));
		lblNewLabel.setBounds(60, 10, 159, 134);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 157, 226, 2);
		panel.add(separator);
		
		JButton btn__Form_menuMoAn = new JButton("Món Ăn");
		btn__Form_menuMoAn.setEnabled(false);
		btn__Form_menuMoAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuTaiQuay menu = new FormMenuTaiQuay(loggedInEmployeeName);
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
		btn__Form_menuMoAn.setHorizontalAlignment(SwingConstants.LEFT);
		btn__Form_menuMoAn.setIcon(new ImageIcon(FormMainTaiQuay.class.getResource("/Icon/foodmenu.png")));
		btn__Form_menuMoAn.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn__Form_menuMoAn.setBackground(SystemColor.text);
		btn__Form_menuMoAn.setBounds(30, 365, 189, 46);
		panel.add(btn__Form_menuMoAn);
		
		JButton btn_Thoat = new JButton("Đăng xuất");
		btn_Thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FormDangNhap().setVisible(true);
				setVisible(false);
			}
		});
		btn_Thoat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_Thoat.setBackground(Color.WHITE);
		btn_Thoat.setBounds(30, 672, 189, 52);
		panel.add(btn_Thoat);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 651, 226, 11);
		panel.add(separator_1);
		
		JButton btn__Form_menuDatBan = new JButton("Bàn");
		btn__Form_menuDatBan.setEnabled(false);
		btn__Form_menuDatBan.addActionListener(new ActionListener() {
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
		btn__Form_menuDatBan.setIcon(new ImageIcon(FormMainTaiQuay.class.getResource("/Icon/ban.png")));
		btn__Form_menuDatBan.setHorizontalAlignment(SwingConstants.LEFT);
		btn__Form_menuDatBan.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn__Form_menuDatBan.setBackground(Color.WHITE);
		btn__Form_menuDatBan.setBounds(30, 445, 189, 46);
		panel.add(btn__Form_menuDatBan);
		
		JButton btn__Form_menuMoAn_1 = new JButton("Bắt đầu làm việc");
		btn__Form_menuMoAn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				batDauLamViec();
				btn__Form_menuMoAn.setEnabled(true);
				btn__Form_menuDatBan.setEnabled(true);
			}
		});
		btn__Form_menuMoAn_1.setHorizontalAlignment(SwingConstants.LEFT);
		btn__Form_menuMoAn_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn__Form_menuMoAn_1.setBackground(SystemColor.text);
		btn__Form_menuMoAn_1.setBounds(30, 179, 189, 46);
		panel.add(btn__Form_menuMoAn_1);
		
		JButton btn__Form_menuMoAn_2 = new JButton(" Kết thúc làm việc");
		btn__Form_menuMoAn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ketThucLamViec();
			}
		});
		btn__Form_menuMoAn_2.setHorizontalAlignment(SwingConstants.LEFT);
		btn__Form_menuMoAn_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn__Form_menuMoAn_2.setBackground(SystemColor.text);
		btn__Form_menuMoAn_2.setBounds(30, 254, 189, 46);
		panel.add(btn__Form_menuMoAn_2);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 325, 226, 11);
		panel.add(separator_1_1);
		manvString=getMaNV(employeeName);
		
	}
	private void batDauLamViec() {
	    try {
	        String maNhanVien = loggedInEmployeeName;
	        

	        if (maNhanVien.length() > 10) {
	            maNhanVien = maNhanVien.substring(0, 10);
	        }
	        CallableStatement preparedStatement=connection.prepareCall("{Call bat_dau_lam_viec(?,?)}");
	        preparedStatement.setString(1, manvString);
	        preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	        JOptionPane.showMessageDialog(Form_Ban, "Bắt đầu làm việc");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	private void ketThucLamViec() {
	    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() throws Exception {
	            try {
	                // Lấy mã nhân viên từ tên nhân viên
	                String manvString = getMaNV(loggedInEmployeeName);
	                
	                // Kiểm tra xem mã nhân viên có hợp lệ không
	                if (manvString != null && !manvString.isEmpty()) {
	                    CallableStatement preparedStatement = connection.prepareCall("{Call ket_thuc_lam_viec(?,?)}");
	                    Timestamp endTime = Timestamp.valueOf(LocalDateTime.now());
	                    preparedStatement.setString(1, manvString);
	                    preparedStatement.setTimestamp(2, endTime);
	                    
	                    int rowsUpdated = preparedStatement.executeUpdate();
	                    preparedStatement.close();
	                    
	                    if (rowsUpdated > 0) {
	                        System.out.println("End time recorded successfully.");
	                        JOptionPane.showMessageDialog(FormMainTaiQuay.this, "Kết thúc làm việc");
	                        FormDangNhap dangNhap = new FormDangNhap();
	                        dangNhap.setVisible(true);
	                        setVisible(false);
	                    } else {
	                        System.out.println("Không thể kết thúc làm việc.");
	                        JOptionPane.showMessageDialog(FormMainTaiQuay.this, "Không có bản ghi nào được cập nhật. Nhân viên có thể đã kết thúc làm việc trước đó.");
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(FormMainTaiQuay.this, "Không thể lấy mã nhân viên.");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(FormMainTaiQuay.this, "Lỗi khi cập nhật thời gian kết thúc: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
	            return null;
	        }
	    };
	    
	    worker.execute();
	}


	private String getMaNV(String employeeName) {
        String maNhanVien = null;
        String query = "SELECT manhanvien FROM nhanvien WHERE tennv = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, employeeName);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    maNhanVien = rs.getString("manhanvien");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy mã nhân viên: " + e.getMessage());
        }
        return maNhanVien;
    }
}
