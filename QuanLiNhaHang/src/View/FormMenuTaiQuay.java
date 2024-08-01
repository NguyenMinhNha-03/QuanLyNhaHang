
package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controller.sqlconnect;
import Model.LoaiMonAn;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class FormMenuTaiQuay extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField editTimSP;
	private sqlconnect con;
	private Connection connection;
	DefaultTableModel dModelMenu;
	private JTable tblOrder;
	private JComboBox cbbBan,cbSapXep;
	private DefaultTableModel orderTableModel;
	private JLabel txtTongTien;
	private JLabel txtNgayLapHD;
	private JLabel lbMaHD,lbNhanVien;
	private String manvString;
	private JPanel panel_menu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuTaiQuay frame = new FormMenuTaiQuay("Default User");
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
	 public FormMenuTaiQuay(String employeeName) {
		 setTitle("From Mon An");
	        con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
	        connection = con.getCon();
	        
	        setBounds(100, 100, 1132, 711);
	        getContentPane().setLayout(null);

	        JPanel panelHeader = new JPanel();
	        panelHeader.setLayout(null);
	        panelHeader.setBackground(Color.WHITE);
	        panelHeader.setBounds(10, 10, 1100, 71);
	        getContentPane().add(panelHeader);

	        JLabel lblMenu = new JLabel("Menu");
	        lblMenu.setForeground(Color.BLACK);
	        lblMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
	        lblMenu.setBounds(10, 10, 169, 30);
	        panelHeader.add(lblMenu);
	        
	        JLabel lblNewLabel_2_1 = new JLabel("New label");
	        lblNewLabel_2_1.setIcon(new ImageIcon(FormMenuTaiQuay.class.getResource("/Icon/username.png")));
	        lblNewLabel_2_1.setBounds(900, 9, 36, 38);
	        panelHeader.add(lblNewLabel_2_1);
	        
	        lbNhanVien = new JLabel("<dynamic>");
	        lbNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lbNhanVien.setBounds(946, 7, 127, 38);
	        panelHeader.add(lbNhanVien);
	        lbNhanVien.setText(employeeName);

	        manvString=getMaNV(employeeName);
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(255, 255, 255));
	        panel.setBounds(10, 91, 1100, 584);
	        getContentPane().add(panel);
	        panel.setLayout(null);

	       

	        
	     
	        editTimSP = new JTextField();
	        editTimSP.setBounds(10, 38, 113, 21);
	        panel.add(editTimSP);
	        editTimSP.setColumns(10);
	        
	        JButton btnTimKieButton=new JButton("Search");
	        btnTimKieButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		timKiemSP();
	        	}
	        });
	        btnTimKieButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnTimKieButton.setBounds(130, 38, 113, 21);
	        panel.add(btnTimKieButton);
	        
	        cbSapXep=new JComboBox();
	    
	        cbSapXep.setBounds(540, 38, 130, 21);
			panel.add(cbSapXep);
	        
	        

	        panel_menu = new JPanel();
	        panel_menu.setBackground(Color.WHITE);
	        panel_menu.setBounds(10, 69, 660, 510);
	        panel_menu.setLayout(new GridLayout(0, 3, 10, 10));
	        panel.add(panel_menu);
	        
	        JScrollPane scrollPane = new JScrollPane(panel_menu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scrollPane.setBounds(10, 69, 660, 510);
	        panel.add(scrollPane);
	        
	        loadMenuItems(panel_menu);
	        
	        JPanel panel_datban = new JPanel();
	        panel_datban.setBackground(Color.WHITE);
	        panel_datban.setBounds(683, 10, 407, 557);
	        panel.add(panel_datban);
	        panel_datban.setLayout(null);
	        
	        JPanel panel_2 = new JPanel();
	        panel_2.setBackground(SystemColor.textHighlight);
	        panel_2.setBounds(10, 10, 387, 84);
	        panel_datban.add(panel_2);
	        panel_2.setLayout(null);
	        
	        JLabel lblNewLabel_1 = new JLabel("New Order");
	        lblNewLabel_1.setForeground(new Color(255, 255, 255));
	        lblNewLabel_1.setBounds(21, 10, 92, 24);
	        panel_2.add(lblNewLabel_1);
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
	        
	        JLabel lblNewLabel_2 = new JLabel("Bàn trống");
	        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_2.setForeground(new Color(255, 255, 255));
	        lblNewLabel_2.setBounds(203, 39, 76, 21);
	        panel_2.add(lblNewLabel_2);
	        
	        cbbBan = new JComboBox();
	        cbbBan.setForeground(Color.BLACK);
	        cbbBan.setBounds(296, 41, 81, 21);
	        panel_2.add(cbbBan);
	        
	        txtNgayLapHD = new JLabel("New label");
	        txtNgayLapHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        txtNgayLapHD.setForeground(new Color(255, 255, 255));
	        txtNgayLapHD.setBounds(21, 44, 92, 16);
	        panel_2.add(txtNgayLapHD);
	        LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        txtNgayLapHD.setText(currentDate.format(formatter));
	        
	        lbMaHD = new JLabel("");
	        lbMaHD.setForeground(new Color(255, 255, 255));
	        lbMaHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lbMaHD.setBounds(301, 12, 76, 21);
	        panel_2.add(lbMaHD);
	        
	        JLabel lblMHan = new JLabel("Mã hóa đơn:");
	        lblMHan.setForeground(Color.WHITE);
	        lblMHan.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblMHan.setBounds(203, 13, 92, 21);
	        panel_2.add(lblMHan);
	        
	        JPanel panelOrder = new JPanel();
	        panelOrder.setBackground(Color.WHITE);
	        panelOrder.setBounds(10, 104, 387, 351);
	        panel_datban.add(panelOrder);
	        panelOrder.setLayout(null);
	        
	        String[] columnNames = {"Tên món ăn", "Số lượng", "Giá", "Xóa"};
	        orderTableModel = new DefaultTableModel(columnNames, 0);
	        tblOrder = new JTable(orderTableModel) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return column == 3; // Only allow editing (clicking) in the "Xóa" column
	            }
	        };
	        tblOrder.addMouseListener((MouseListener) new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	 int column = tblOrder.getColumnModel().getColumnIndexAtX(e.getX()); // Lấy chỉ số cột được click
	                 int row = e.getY() / tblOrder.getRowHeight(); // Lấy chỉ số dòng được click
	                 
	                 // Kiểm tra nếu là cột "Xóa" và dòng hợp lệ
	                 if (row < tblOrder.getRowCount() && column == 3) {
	                     // Lấy số lượng hiện tại
	                     int quantity = (int) orderTableModel.getValueAt(row, 1);
	                     int giaBan = (int) orderTableModel.getValueAt(row, 2) / quantity;
	                     // Nếu số lượng lớn hơn 1, giảm đi 1
	                     if (quantity > 1) {
	                         orderTableModel.setValueAt(quantity - 1, row, 1);
	                         orderTableModel.setValueAt((quantity - 1) * giaBan, row, 2); // Cập nhật lại giá tiền
	                     } else {
	                         // Nếu số lượng là 1, xóa dòng
	                         orderTableModel.removeRow(row);
	                     }
	                     updateTotalAmount();
	                 }
	            }
	        });
	        tblOrder.setBounds(10, 10, 367, 331);
	        panelOrder.add(tblOrder); 
	        
	        JLabel lblNewLabel_3 = new JLabel("Tổng tiền:");
	        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
	        lblNewLabel_3.setBounds(25, 465, 100, 26);
	        panel_datban.add(lblNewLabel_3);
	     
	        
	        JButton btnDeleteAll = new JButton("Xóa chọn tất cả");
	        btnDeleteAll.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		orderTableModel.setRowCount(0); // Clear all rows
					updateTotalAmount();
	        	}
	        });
	        btnDeleteAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnDeleteAll.setBounds(135, 521, 137, 26);
	        panel_datban.add(btnDeleteAll);
	        
	        JButton btnBaoCheBien = new JButton("Báo chế biến");
	        btnBaoCheBien.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		baoCheBien();
	        	}
	        });
	        btnBaoCheBien.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnBaoCheBien.setBounds(276, 521, 121, 26);
	        panel_datban.add(btnBaoCheBien);
	        
	        txtTongTien = new JLabel();
	        txtTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
	        txtTongTien.setBounds(264, 474, 115, 13);
	        panel_datban.add(txtTongTien);
	        
	        JButton btnLapHD = new JButton("Lập hóa đơn");
	        btnLapHD.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		themHD();
	        	}
	        });
	        btnLapHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        btnLapHD.setBounds(10, 521, 115, 26);
	        panel_datban.add(btnLapHD);
	        
	        showBanTrong();
	       // showCbLoai();
	        sapXepTheoLoai();
	    }
	 
	 private void loadMenuItems(JPanel panel) {
   String query = "SELECT tenmonan, giaban, hinhanhmonan FROM menu";
   try {
       PreparedStatement pst = connection.prepareStatement(query);
       ResultSet rs = pst.executeQuery();
       while (rs.next()) {
           String tenMonAn = rs.getString("tenmonan");
           int giaBan = rs.getInt("giaban");
           String hinhAnhMonAn = rs.getString("hinhanhmonan");

           JPanel itemPanel = new JPanel();
           itemPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
           itemPanel.setBackground(new Color(255, 255, 255));
           itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

           JLabel lbHinhAnh = new JLabel();
	       ImageIcon originalIcon = new ImageIcon(hinhAnhMonAn);
	       Image scaledImage = originalIcon.getImage().getScaledInstance(203, 128, Image.SCALE_SMOOTH);
	       ImageIcon scaledIcon = new ImageIcon(scaledImage);
	       lbHinhAnh.setIcon(scaledIcon);
	       //lbHinhAnh.setBounds(0, 0, 203, 128);
	       lbHinhAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
           itemPanel.add(lbHinhAnh);

           JLabel txtTenMA = new JLabel(tenMonAn);
           txtTenMA.setFont(new Font("Tahoma", Font.BOLD, 14));
           //txtTenMA.setBounds(71, 149, 100, 21);
           txtTenMA.setAlignmentX(Component.CENTER_ALIGNMENT);
           txtTenMA.setBorder(new EmptyBorder(5, 5, 5, 5));
           itemPanel.add(txtTenMA);

           JLabel txtGia = new JLabel(String.valueOf(giaBan) + " VND");
           txtGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
           //txtGia.setBounds(54, 180, 100, 19);
           txtGia.setAlignmentX(Component.CENTER_ALIGNMENT);
           txtGia.setBorder(new EmptyBorder(8, 8, 8, 8));
           itemPanel.add(txtGia);

           JButton btnDat = new JButton("Đặt");
           btnDat.setBackground(SystemColor.textHighlight);
           btnDat.setForeground(new Color(255, 255, 255));
           btnDat.setFont(new Font("Tahoma", Font.PLAIN, 14));
           //btnDat.setBounds(54, 209, 85, 21);
           btnDat.setAlignmentX(Component.CENTER_ALIGNMENT);
           btnDat.setBorder(new EmptyBorder(5, 20, 5, 20));
           itemPanel.add(btnDat);
           btnDat.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   addItemToOrder(tenMonAn, giaBan);
               }
           });
           
           panel.add(itemPanel);         
       }
   } catch (SQLException e) {
       e.printStackTrace();
   }
}
	 private void loadMenuItemsByLoai(String maLoai) {
		    String query = "SELECT tenmonan, giaban, hinhanhmonan FROM menu WHERE maloai = ?";
		    try {
		        PreparedStatement pst = connection.prepareStatement(query);
		        pst.setString(1, maLoai);
		        ResultSet rs = pst.executeQuery();
		        while (rs.next()) {
		            String tenMonAn = rs.getString("tenmonan");
		            int giaBan = rs.getInt("giaban");
		            String hinhAnhMonAn = rs.getString("hinhanhmonan");

		            JPanel itemPanel = new JPanel();
		            itemPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		            itemPanel.setBackground(new Color(255, 255, 255));
		            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

		            JLabel lbHinhAnh = new JLabel();
		            ImageIcon originalIcon = new ImageIcon(hinhAnhMonAn);
		            Image scaledImage = originalIcon.getImage().getScaledInstance(203, 128, Image.SCALE_SMOOTH);
		            ImageIcon scaledIcon = new ImageIcon(scaledImage);
		            lbHinhAnh.setIcon(scaledIcon);
		            lbHinhAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
		            itemPanel.add(lbHinhAnh);

		            JLabel txtTenMA = new JLabel(tenMonAn);
		            txtTenMA.setFont(new Font("Tahoma", Font.BOLD, 14));
		            txtTenMA.setAlignmentX(Component.CENTER_ALIGNMENT);
		            txtTenMA.setBorder(new EmptyBorder(5, 5, 5, 5));
		            itemPanel.add(txtTenMA);

		            JLabel txtGia = new JLabel(String.valueOf(giaBan) + " VND");
		            txtGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		            txtGia.setAlignmentX(Component.CENTER_ALIGNMENT);
		            txtGia.setBorder(new EmptyBorder(8, 8, 8, 8));
		            itemPanel.add(txtGia);

		            JButton btnDat = new JButton("Đặt");
		            btnDat.setBackground(SystemColor.textHighlight);
		            btnDat.setForeground(new Color(255, 255, 255));
		            btnDat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		            btnDat.setAlignmentX(Component.CENTER_ALIGNMENT);
		            btnDat.setBorder(new EmptyBorder(5, 20, 5, 20));
		            itemPanel.add(btnDat);
		            btnDat.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                    addItemToOrder(tenMonAn, giaBan);
		                }
		            });

		            panel_menu.add(itemPanel);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	 private void sapXepTheoLoai() {
		    try {
		        CallableStatement preparedStatement = connection.prepareCall("{Call showAllLoaiMonAn(?)}");
		        preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		        preparedStatement.execute();
		        ResultSet resultSet = (ResultSet) preparedStatement.getObject(1);
		        while (resultSet.next()) {
		            String maLoai = resultSet.getString("maloaimonan");
		            String tenLoai = resultSet.getString("tenloaimonan");
		            LoaiMonAn loaiMonAn = new LoaiMonAn(maLoai, tenLoai);
		            cbSapXep.addItem(loaiMonAn);
		        }
		     
		        cbSapXep.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                LoaiMonAn selectedLoai = (LoaiMonAn) cbSapXep.getSelectedItem();
		                String maLoai = selectedLoai.getMaLoai();
		                panel_menu.removeAll(); 
		                panel_menu.revalidate();
		                panel_menu.repaint();
		                loadMenuItemsByLoai(maLoai);
		            }
		        });
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

    private void showBanTrong() {
			String query = "SELECT soban FROM ban WHERE kiemtraban = 'Trong'";
			try {
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					cbbBan.addItem(rs.getString("soban"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    
	    
	    private void addItemToOrder(String tenMonAn, int giaBan) {
	        int rowCount = orderTableModel.getRowCount();
	        boolean itemExists = false;
	        for (int i = 0; i < rowCount; i++) {
	            if (orderTableModel.getValueAt(i, 0).equals(tenMonAn)) {
	                int quantity = (int) orderTableModel.getValueAt(i, 1);
	                orderTableModel.setValueAt(quantity + 1, i, 1);
	                orderTableModel.setValueAt((quantity + 1) * giaBan, i, 2);

	                itemExists = true;
	                break;
	            }
	        }

	        if (!itemExists) {
	            Object[] rowData = {tenMonAn, 1, giaBan, "Xóa"};
	            orderTableModel.addRow(rowData);
	        }
	        updateTotalAmount();
	    }
	    
	    private void updateTotalAmount() {
			int rowCount = orderTableModel.getRowCount();
			int totalAmount = 0;
			for (int i = 0; i < rowCount; i++) {
				int price = (int) orderTableModel.getValueAt(i, 2);
				totalAmount += price;
			}
			txtTongTien.setText(totalAmount + " VND");
		}
	    
	    private void themHD() {
	        // Get the date and total amount from the labels
	        String ngayLapHD = txtNgayLapHD.getText();
	        String tongTienText = txtTongTien.getText().replace(" VND", "");
	        int tongTien = Integer.parseInt(tongTienText);

	        try {
	            // Insert the new invoice into the database
	            String insertInvoiceQuery = "INSERT INTO hoadon (ngaylaphoadon, tongtienhoadon) VALUES (?, ?)";
	            PreparedStatement insertInvoiceStmt = connection.prepareStatement(insertInvoiceQuery, new String[]{"mahoadon"});
	            insertInvoiceStmt.setDate(1, Date.valueOf(LocalDate.now()));
	            insertInvoiceStmt.setInt(2, tongTien);
	            insertInvoiceStmt.executeUpdate();

	            // Retrieve the generated invoice ID
	            ResultSet generatedKeys = insertInvoiceStmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int maHoaDon = generatedKeys.getInt(1);
	                lbMaHD.setText(String.valueOf(maHoaDon));
	            } else {
	                JOptionPane.showMessageDialog(this, "Failed to create invoice. No ID obtained.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error creating invoice: " + e.getMessage());
	        }
	    }
	    private void baoCheBien() {
	        String selectedBan = (String) cbbBan.getSelectedItem();
	        // Lấy mã hóa đơn
	        String maHoaDon = lbMaHD.getText();
	        String nhanVienString=lbNhanVien.getText().trim();
	        if (selectedBan == null || selectedBan.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn.");
	            return;
	        }

	        if (maHoaDon == null || maHoaDon.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Mã hóa đơn không hợp lệ.");
	            return;
	        }

	        // Kiểm tra xem bàn có tồn tại không
	        String checkTableExistQuery = "select COUNT(*) from ban WHERE maban = ?";
	        try (PreparedStatement pstCheckTable = connection.prepareStatement(checkTableExistQuery)) {
	            pstCheckTable.setInt(1, Integer.parseInt(selectedBan));
	            try (ResultSet rsCheckTable = pstCheckTable.executeQuery()) {
	                if (rsCheckTable.next()) {
	                    int tableCount = rsCheckTable.getInt(1);
	                    if (tableCount == 0) {
	                        JOptionPane.showMessageDialog(null, "Bàn không tồn tại. Vui lòng chọn bàn hợp lệ.");
	                        return;
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra bàn: " + e.getMessage());
	            return;
	        }

	        // Tiếp tục thêm chi tiết hóa đơn
	        try {
	            // Cập nhật trạng thái bàn thành 'khong trong'
	            String updateTableStatusQuery = "UPDATE ban SET kiemtraban = 'Khong trong' WHERE maban = ?";
	            try (PreparedStatement pstUpdateTable = connection.prepareStatement(updateTableStatusQuery)) {
	                pstUpdateTable.setInt(1, Integer.parseInt(selectedBan));
	                pstUpdateTable.executeUpdate();
	            }

	            // Lấy dữ liệu từ bảng tblOrder
	            int rowCount = orderTableModel.getRowCount();
	            for (int i = 0; i < rowCount; i++) {
	                String tenMonAn = (String) orderTableModel.getValueAt(i, 0);
	                int soLuong = (int) orderTableModel.getValueAt(i, 1);
	                int thanhTien = (int) orderTableModel.getValueAt(i, 2);

	                // Lấy mã món ăn từ tên món ăn
	                String selectMaMonAnQuery = "SELECT mamonan FROM menu WHERE tenmonan = ?";
	                String maMonAn = null;
	                try (PreparedStatement selectMaMonAnStmt = connection.prepareStatement(selectMaMonAnQuery)) {
	                    selectMaMonAnStmt.setString(1, tenMonAn);
	                    try (ResultSet rs = selectMaMonAnStmt.executeQuery()) {
	                        if (rs.next()) {
	                            maMonAn = rs.getString("mamonan");
	                        } else {
	                            JOptionPane.showMessageDialog(this, "Không tìm thấy mã món ăn cho: " + tenMonAn);
	                            continue;
	                        }
	                    }
	                }

	                // Thêm chi tiết hóa đơn vào bảng chitiethoadon
	                try (CallableStatement insertChiTietHDStmt = connection.prepareCall("{Call them_chitiet_hoadon(?,?,?,?,?,?)}")) {
	                    insertChiTietHDStmt.setInt(1, Integer.parseInt(maHoaDon));
	                    insertChiTietHDStmt.setString(2, selectedBan);
	                    insertChiTietHDStmt.setString(3, maMonAn);
	                    insertChiTietHDStmt.setString(4, manvString); 
	                    insertChiTietHDStmt.setInt(5, soLuong);
	                    insertChiTietHDStmt.setInt(6, thanhTien);
	                    insertChiTietHDStmt.executeUpdate();
	                }
	            }

	            JOptionPane.showMessageDialog(this, "Đã báo chế biến và cập nhật chi tiết hóa đơn thành công.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Lỗi khi báo chế biến: " + e.getMessage());
	        }
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
	            JOptionPane.showMessageDialog(this, "Error fetching employee ID: " + e.getMessage());
	        }
	        return maNhanVien;
	    }
	    private void timKiemSP() {
	        String keyword = editTimSP.getText().trim();
	        if (keyword.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm.");
	            return;
	        }

	        // Clear previous search results
	        panel_menu.removeAll();
	        panel_menu.revalidate();
	        panel_menu.repaint();

	        String query = "SELECT tenmonan, giaban, hinhanhmonan FROM menu WHERE tenmonan LIKE ?";
	        try {
	            PreparedStatement pst = connection.prepareStatement(query);
	            pst.setString(1, "%" + keyword + "%");
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                String tenMonAn = rs.getString("tenmonan");
	                int giaBan = rs.getInt("giaban");
	                String hinhAnhMonAn = rs.getString("hinhanhmonan");

	                JPanel itemPanel = new JPanel();
	                itemPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
	                itemPanel.setBackground(new Color(255, 255, 255));
	                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

	                JLabel lbHinhAnh = new JLabel();
	                ImageIcon originalIcon = new ImageIcon(hinhAnhMonAn);
	                Image scaledImage = originalIcon.getImage().getScaledInstance(203, 128, Image.SCALE_SMOOTH);
	                ImageIcon scaledIcon = new ImageIcon(scaledImage);
	                lbHinhAnh.setIcon(scaledIcon);
	                lbHinhAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
	                itemPanel.add(lbHinhAnh);

	                JLabel txtTenMA = new JLabel(tenMonAn);
	                txtTenMA.setFont(new Font("Tahoma", Font.BOLD, 14));
	                txtTenMA.setAlignmentX(Component.CENTER_ALIGNMENT);
	                txtTenMA.setBorder(new EmptyBorder(5, 5, 5, 5));
	                itemPanel.add(txtTenMA);

	                JLabel txtGia = new JLabel(String.valueOf(giaBan) + " VND");
	                txtGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
	                txtGia.setAlignmentX(Component.CENTER_ALIGNMENT);
	                txtGia.setBorder(new EmptyBorder(8, 8, 8, 8));
	                itemPanel.add(txtGia);

	                JButton btnDat = new JButton("Đặt");
	                btnDat.setBackground(SystemColor.textHighlight);
	                btnDat.setForeground(new Color(255, 255, 255));
	                btnDat.setFont(new Font("Tahoma", Font.PLAIN, 14));
	                btnDat.setAlignmentX(Component.CENTER_ALIGNMENT);
	                btnDat.setBorder(new EmptyBorder(5, 20, 5, 20));
	                itemPanel.add(btnDat);
	                btnDat.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        addItemToOrder(tenMonAn, giaBan);
	                    }
	                });

	                panel_menu.add(itemPanel);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage());
	        }
	    }

}