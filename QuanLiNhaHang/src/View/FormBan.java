
package View;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controller.sqlconnect;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class FormBan extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private sqlconnect con;
	private Connection connection;
	private Map<Integer, JButton> buttons;
	private JLabel txtSoBan;
	private JPanel panelCTHD;
	private JTable tblCTHD;
	private DefaultTableModel tblCTHDModel;
	private JComboBox cbbBan;
	private JLabel txtTongTien, lbNhanVien;
	private int currentTableNumber; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormBan frame = new FormBan("Default User");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormBan(String employeeName) {
		
		getContentPane().setForeground(Color.LIGHT_GRAY);
		con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
		connection = con.getCon();
		initialize();
		lbNhanVien.setText(employeeName);
		loadTableStatus();
	}

	private void initialize() {
		setTitle("Form Ban");
		getContentPane().setBackground(SystemColor.control);
		setBounds(256, 0, 1104, 731);
		getContentPane().setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Color.WHITE);
		panelHeader.setBounds(10, 10, 1077, 71);
		getContentPane().add(panelHeader);
		panelHeader.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tình trạng bàn");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 169, 30);
		panelHeader.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(FormBan.class.getResource("/Icon/username.png")));
		lblNewLabel_2.setBounds(894, 12, 36, 38);
		panelHeader.add(lblNewLabel_2);
		
		 lbNhanVien = new JLabel("<dynamic>");
		lbNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNhanVien.setBounds(940, 10, 127, 38);
		panelHeader.add(lbNhanVien);

		JPanel panelBan = new JPanel();
		panelBan.setBackground(Color.WHITE);
		panelBan.setBounds(10, 91, 607, 601);
		getContentPane().add(panelBan);
		panelBan.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(627, 91, 460, 601);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.RED);
		panel_1.setBounds(10, 10, 435, 90);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtSoBan = new JLabel("");
		txtSoBan.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtSoBan.setForeground(Color.WHITE);
		txtSoBan.setBounds(191, 29, 79, 30);
		panel_1.add(txtSoBan);
		
		panelCTHD = new JPanel();
		panelCTHD.setBackground(Color.WHITE);
		panelCTHD.setBounds(10, 110, 435, 359);
		panel.add(panelCTHD);
		panelCTHD.setLayout(new BorderLayout());
		
		tblCTHDModel= new DefaultTableModel();
		tblCTHD = new JTable(tblCTHDModel);
		tblCTHD.setBounds(10, 10, 415, 339);
		tblCTHDModel.setColumnIdentifiers(new Object[] {"Tên món ăn", "Số lượng", "Thành tiền"});
		JScrollPane jScrollPane = new JScrollPane(tblCTHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelCTHD.add(jScrollPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("Tổng tiền:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(45, 493, 100, 26);
		panel.add(lblNewLabel_3);
		
		txtTongTien = new JLabel();
		txtTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTongTien.setBounds(302, 493, 115, 26);
		panel.add(txtTongTien);
		
		JButton btnThanhToan = new JButton("THANH TOÁN");
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thanh toán?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			        if (result == JOptionPane.YES_OPTION) {
			            // Lấy số bàn hiện tại
			            int currentTableNumber = Integer.parseInt(txtSoBan.getText().replace("Bàn ", ""));
			            // Xóa dữ liệu trong bảngCTHD 
			            deleteBillDetails(currentTableNumber);
			            // Cập nhật trạng thái của bàn thành Trống
			            updateTableStatus(currentTableNumber);
			            // Cập nhật lại giao diện và danh sách bàn trống
			            loadTableStatus();
			            loadAvailableTables();
			            // Hiển thị thông báo
			            JOptionPane.showMessageDialog(null, "Thanh toán thành công.");
			            // Đặt lại dữ liệu trên giao diện
			            txtSoBan.setText("");
			            tblCTHDModel.setRowCount(0);
			            txtTongTien.setText("");
			        }
			}
		});
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setBackground(Color.RED);
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThanhToan.setBounds(23, 565, 129, 26);
		panel.add(btnThanhToan);
		
		JButton btnChuyenBan = new JButton("CHUYỂN BÀN");
		btnChuyenBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferTable();
			}
		});
		btnChuyenBan.setForeground(Color.WHITE);
		btnChuyenBan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChuyenBan.setBackground(Color.BLUE);
		btnChuyenBan.setBounds(23, 529, 129, 26);
		panel.add(btnChuyenBan);
	
		JLabel lblNewLabel_1 = new JLabel("Danh sách bàn trống");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(217, 528, 142, 26);
		panel.add(lblNewLabel_1);
		
		cbbBan = new JComboBox();
		cbbBan.setBounds(369, 533, 61, 21);
		panel.add(cbbBan);

		buttons = new HashMap<>();
		for (int i = 1; i <= 20; i++) {
			final int tableNumber = i;
			JButton btnBan = new JButton("Bàn " + i);
			btnBan.setForeground(new Color(255, 255, 255));
			btnBan.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnBan.setBounds((i - 1) % 5 * 119 + 10, (i - 1) / 5 * 87 + 10, 109, 77);
			panelBan.add(btnBan);
			buttons.put(i, btnBan);
			btnBan.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleTableButtonClick(tableNumber);
                }
            });
		}
		
		loadAvailableTables();
		
	}

	private void loadTableStatus() {
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call loadTableStatus(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);

			while (resultSet.next()) {
				int tableNumber = resultSet.getInt("soban");
				String status = resultSet.getString("kiemtraban");

				JButton button = buttons.get(tableNumber);
				if (button != null) {
					if ("Trong".equals(status)) {
						button.setBackground(new Color(50, 205, 50));
					} else {
						button.setBackground(new Color(255, 0, 0));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 private void handleTableButtonClick(int tableNumber) {
	        try {
	            Statement statement = connection.createStatement();
	            String query = "SELECT kiemtraban FROM ban WHERE soban = " + tableNumber;
	            ResultSet resultSet = statement.executeQuery(query);

	            if (resultSet.next()) {
	                String status = resultSet.getString("kiemtraban");
	                if ("Trong".equals(status)) {
	                    
	                    tblCTHDModel.setRowCount(0);
	                    txtSoBan.setText("");
	                    txtTongTien.setText("");
	                } else if ("Khong trong".equals(status)) {
	                    displayBillDetails(tableNumber);
	                    calculateAndDisplayTotalAmount();
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	 private void displayBillDetails(int tableNumber) {
	        try {
	            Statement statement = connection.createStatement();
	            String query = "SELECT menu.tenmonan, chitiethoadon.soluonghd AS soluong, chitiethoadon.thanhtienhd AS thanhtien " +
	                           "FROM chitiethoadon " +
	                           "JOIN menu ON chitiethoadon.mama = menu.mamonan " +
	                           "WHERE chitiethoadon.maban = (SELECT maban FROM ban WHERE soban = " + tableNumber + ")";
	            ResultSet resultSet = statement.executeQuery(query);

	            tblCTHDModel.setRowCount(0);
	            txtSoBan.setText("Bàn " + tableNumber);

	            while (resultSet.next()) {
	                String tenmonan = resultSet.getString("tenmonan");
	                int soluong = resultSet.getInt("soluong");
	                int thanhtien = resultSet.getInt("thanhtien");

	                tblCTHDModel.addRow(new Object[]{tenmonan, soluong, thanhtien});
	            }


	            tblCTHD.revalidate();
	            tblCTHD.repaint();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	    private void loadAvailableTables() {
	    	cbbBan.removeAllItems();
				try {
					CallableStatement preparedStatement=connection.prepareCall("{Call loadAvailableTables(?)}");
					preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		            preparedStatement.execute();
		            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
					while (resultSet.next()) {
						int soban = resultSet.getInt("soban");
						cbbBan.addItem(String.valueOf(soban));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	    
	    private void calculateAndDisplayTotalAmount() {
	        int rowCount = tblCTHDModel.getRowCount();
	        int totalAmount = 0;

	        for (int i = 0; i < rowCount; i++) {
	            int thanhtien = (int) tblCTHDModel.getValueAt(i, 2);
	            totalAmount += thanhtien;
	        }

	        txtTongTien.setText(totalAmount + " VND");
	    }
	    
	    private void transferTable() {
	        try {
	            String selectedTable = (String) cbbBan.getSelectedItem();
	            if (selectedTable == null) {
	                JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn cần chuyển đến.");
	                return;
	            }

	            int newTableNumber = Integer.parseInt(selectedTable);
	            String currentTableText = txtSoBan.getText();
	            if (currentTableText.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn cần chuyển.");
	                return;
	            }

	
	            currentTableNumber = Integer.parseInt(currentTableText.replace("Bàn ", ""));
	            PreparedStatement ps1 = connection.prepareStatement(
	                "UPDATE chitiethoadon " +
	                "SET maban = (SELECT maban FROM ban WHERE soban = ?) " +
	                "WHERE maban = (SELECT maban FROM ban WHERE soban = ?)");
	            ps1.setInt(1, newTableNumber);
	            ps1.setInt(2, currentTableNumber);
	            ps1.executeUpdate();

	            PreparedStatement ps2 = connection.prepareStatement(
	                "UPDATE ban SET kiemtraban = 'Trong' WHERE soban = ?");
	            ps2.setInt(1, currentTableNumber);
	            ps2.executeUpdate();

	            PreparedStatement ps3 = connection.prepareStatement(
	                "UPDATE ban SET kiemtraban = 'Khong trong' WHERE soban = ?");
	            ps3.setInt(1, newTableNumber);
	            ps3.executeUpdate();

	            loadTableStatus();
	            loadAvailableTables();
	            txtSoBan.setText("Bàn " + newTableNumber);
	            JOptionPane.showMessageDialog(null, "Chuyển bàn thành công.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    private void deleteBillDetails(int tableNumber) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM chitiethoadon WHERE maban = (SELECT maban FROM ban WHERE soban = ?)");
	            statement.setInt(1, tableNumber);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private void updateTableStatus(int tableNumber) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE ban SET kiemtraban = 'Trong' WHERE soban = ?");
	            statement.setInt(1, tableNumber);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	   
}