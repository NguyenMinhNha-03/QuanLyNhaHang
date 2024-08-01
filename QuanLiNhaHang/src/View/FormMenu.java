package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Controller.sqlconnect;
import Model.DangNhap;
import Model.Menu;
import Model.LoaiMonAn;
import javax.swing.JComboBox;

public class FormMenu extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	DefaultTableModel defaultTableModel;
	ArrayList<Menu> listMenu;
	private sqlconnect con;
	private Connection connection;
	private JTextField editTimKiemMonAn;
	private JTable tableMonAn;
	private JTextField editMaMonAn;
	private JTextField editTenMonAn;
	private JTextField editGiaBan;
	private JLabel lbHinhAnh, lbNhanVien;
	private JComboBox cbLoai;
	private String selectedImagePath = "/path/to/images/default.png";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenu frame = new FormMenu("Default User");
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
	public FormMenu(String employeeName) {
		con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
		connection = con.getCon();
		initialize();
		lbNhanVien.setText(employeeName);
	}

	private void initialize() {
		setTitle("Form Mon An");
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(256, 0, 1160, 731);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Thông tin món ăn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(676, 387, 398, 236);
		getContentPane().add(panel);
		panel.setLayout(null);

		editMaMonAn = new JTextField();
		editMaMonAn.setBounds(173, 26, 191, 19);
		panel.add(editMaMonAn);
		editMaMonAn.setColumns(10);

		editTenMonAn = new JTextField();
		editTenMonAn.setColumns(10);
		editTenMonAn.setBounds(173, 63, 191, 19);
		panel.add(editTenMonAn);

		editGiaBan = new JTextField();
		editGiaBan.setColumns(10);
		editGiaBan.setBounds(173, 94, 191, 19);
		panel.add(editGiaBan);

		JButton btnThemMenu = new JButton("Thêm");
		btnThemMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themMonAn();
			}
		});
		btnThemMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThemMenu.setBounds(21, 176, 85, 30);
		panel.add(btnThemMenu);

		JLabel lblNewLabel = new JLabel("Mã món ăn:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(34, 23, 105, 21);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tên món ăn:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(34, 61, 105, 19);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Giá bán:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(34, 93, 105, 16);
		panel.add(lblNewLabel_1_1);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaMonAn();
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoa.setBounds(136, 176, 85, 30);
		panel.add(btnXoa);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatMonAn();
			}
		});
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCapNhat.setBounds(244, 176, 117, 30);
		panel.add(btnCapNhat);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Loại món ăn");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(34, 129, 105, 16);
		panel.add(lblNewLabel_1_1_1);
		
		cbLoai = new JComboBox();
		cbLoai.setBounds(173, 129, 188, 21);
		panel.add(cbLoai);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new LineBorder(null, 0));
		panel_1.setBounds(20, 143, 627, 480);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout());
		defaultTableModel = new DefaultTableModel();
		tableMonAn = new JTable(defaultTableModel);
		tableMonAn.setBackground(new Color(255, 255, 255));
		panel_1.setLayout(new BorderLayout());
		JScrollPane jScrollPane = new JScrollPane(tableMonAn, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(jScrollPane, BorderLayout.CENTER);

		editTimKiemMonAn = new JTextField();
		editTimKiemMonAn.setBounds(20, 105, 175, 28);
		getContentPane().add(editTimKiemMonAn);
		editTimKiemMonAn.setColumns(10);

		JButton btnTimKiemMonAn = new JButton("Search");
		btnTimKiemMonAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenMonAn=editTimKiemMonAn.getText().toString().trim();
				if(!tenMonAn.isEmpty())
				{
					 defaultTableModel.setRowCount(0);
			         String callProcedure = "{CALL timMenu(?, ?)}";
			         CallableStatement callableStatement;
			         try {
			               callableStatement = connection.prepareCall(callProcedure);
			               callableStatement.setString(1, tenMonAn);
			               callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);
			               callableStatement.execute();
			                
			               ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
			               while(resultSet.next())
						{
							String maMonAn=resultSet.getString("mamonan");
							String ten=resultSet.getString("tenmonan");
							int giaBan=resultSet.getInt("giaban");
							
							defaultTableModel.addRow(new Object[] {maMonAn,ten,giaBan});
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(btnTimKiemMonAn, "Không tìm thấy món ăn theo yêu cầu!");
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(btnTimKiemMonAn, "Vui lòng nhập tên nhân viên để tìm kiếm!");
				}
			}
		});
		btnTimKiemMonAn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTimKiemMonAn.setBounds(216, 105, 88, 28);
		getContentPane().add(btnTimKiemMonAn);

		JButton btnNguyenLieu = new JButton("Cập nhật nguyên liệu");
		btnNguyenLieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormNguyenLieu nguyenLieu=new FormNguyenLieu();
				nguyenLieu.setVisible(true);
			}
		});
		btnNguyenLieu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNguyenLieu.setBounds(477, 105, 170, 28);
		getContentPane().add(btnNguyenLieu);

		ImageIcon editIcon = new ImageIcon(FormMenu.class.getResource("/Icon/edit.png"));
		Image editImage = editIcon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
		ImageIcon clearEditIcon = new ImageIcon(editImage);

		JButton btnEdit = new JButton("");
		btnEdit.setIcon(clearEditIcon);
		btnEdit.setBounds(864, 358, 28, 28);
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBorderPainted(false);
		getContentPane().add(btnEdit);

		btnEdit.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
                ImageIcon icon = new ImageIcon(selectedImagePath);
                Image img = icon.getImage().getScaledInstance(lbHinhAnh.getWidth(), lbHinhAnh.getHeight(),
                        Image.SCALE_SMOOTH);
                lbHinhAnh.setIcon(new ImageIcon(img));
            }
        });
		
		lbHinhAnh = new JLabel("");
		lbHinhAnh.setBounds(676, 105, 398, 243);
		getContentPane().add(lbHinhAnh);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(FormMenu.class.getResource("/Icon/username.png")));
		lblNewLabel_2.setBounds(901, 10, 36, 38);
		getContentPane().add(lblNewLabel_2);
		
		lbNhanVien = new JLabel("<dynamic>");
		lbNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNhanVien.setBounds(947, 8, 127, 38);
		getContentPane().add(lbNhanVien);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nhân Viên");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(20, 10, 144, 38);
		getContentPane().add(lblNewLabel_1_2);

		listMenu = getListMenu();
		defaultTableModel = (DefaultTableModel) tableMonAn.getModel();
		defaultTableModel.setColumnIdentifiers(new Object[] { "Mã món ăn", "Tên món ăn", "Giá bán" });
		tableMonAn.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (tableMonAn.getSelectedRow() >= 0) {
					Menu menu = listMenu.get(tableMonAn.getSelectedRow());
					editMaMonAn.setText(menu.getMaMonAn());
					editTenMonAn.setText(menu.getTenMonAn());
					editGiaBan.setText(menu.getGiaBan() + "");
					String imagePath = menu.getHinhAnhMonAn();
					ImageIcon icon = null;
					try {
						icon = new ImageIcon(imagePath);
					} catch (NullPointerException ex) {
						System.out.println("Image not found: " + imagePath);
					}
					if (icon != null) {
						Image img = icon.getImage().getScaledInstance(lbHinhAnh.getWidth(), lbHinhAnh.getHeight(),
								Image.SCALE_SMOOTH);
						lbHinhAnh.setIcon(new ImageIcon(img));
					} else {
						lbHinhAnh.setIcon(null);
					}
				}
			}
		});
		showTableMenu();
		showCbLoai();
	}
	public void showCbLoai() {
	    try {
	        CallableStatement preparedStatement = connection.prepareCall("{Call showAllLoaiMonAn(?)}");
	        preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
	        preparedStatement.execute();
	        ResultSet resultSet = (ResultSet) preparedStatement.getObject(1);
	        while (resultSet.next()) {
	            String maLoai = resultSet.getString("maloaimonan");
	            String tenLoai = resultSet.getString("tenloaimonan");
	            LoaiMonAn loaiMonAn = new LoaiMonAn(maLoai, tenLoai);
	            cbLoai.addItem(loaiMonAn);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public ArrayList<Menu> getListMenu() {
		ArrayList<Menu> list = new ArrayList<Menu>();
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call GET_MENU_LIST(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while (resultSet.next()) {

				Menu menu = new Menu();
				menu.setMaMonAn(resultSet.getString("mamonan"));
				menu.setHinhAnhMonAn(resultSet.getString("hinhanhmonan"));
				menu.setTenMonAn(resultSet.getString("tenmonan"));
				menu.setGiaBan(resultSet.getInt("giaban"));

				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void showTableMenu() {
		defaultTableModel.setRowCount(0);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		String sql = "{Call hien_thi_menu(?)}";
		try {
			callableStatement = connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
			callableStatement.execute();
			resultSet = (ResultSet) callableStatement.getObject(1);

			while (resultSet.next()) {
				String maMonAn = resultSet.getString("mamonan");
				String tenMonAn = resultSet.getString("tenmonan");
				String giaBan = resultSet.getString("giaban");
				defaultTableModel.addRow(new Object[] { maMonAn, tenMonAn, giaBan });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị tài khoản" + e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (callableStatement != null)
					callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void themMonAn() {
	    String maMonAn = editMaMonAn.getText();
	    String tenMonAn = editTenMonAn.getText();
	    int giaBan = Integer.parseInt(editGiaBan.getText());
	    LoaiMonAn selectedLoai = (LoaiMonAn) cbLoai.getSelectedItem();
	    String maLoai = selectedLoai.getMaLoai();

	    try {
	        CallableStatement callableStatement = connection.prepareCall("{call themmenu(?, ?, ?, ?, ?)}");
	        callableStatement.setString(1, maMonAn);
	        callableStatement.setString(2, selectedImagePath);  // Set the image path
	        callableStatement.setString(3, tenMonAn);
	        callableStatement.setInt(4, giaBan);
	        callableStatement.setString(5, maLoai);

	        callableStatement.execute();
	        JOptionPane.showMessageDialog(this, "Thêm món ăn thành công");
	        listMenu.add(new Menu(maMonAn, selectedImagePath, tenMonAn, giaBan));
	        defaultTableModel.addRow(new Object[] { maMonAn, tenMonAn, giaBan });
	        editMaMonAn.setText("");
	        editTenMonAn.setText("");
	        editGiaBan.setText("");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Lỗi khi thêm món ăn: " + e.getMessage());
	    }
	}

	public void xoaMonAn()
	{
		 int selectedRow = tableMonAn.getSelectedRow();
		    if (selectedRow >= 0) {
		        String maMonAn = listMenu.get(selectedRow).getMaMonAn();
		        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            try (CallableStatement callableStatement = connection.prepareCall("{Call xoaMenu(?)}")) {
		                callableStatement.setString(1, maMonAn);
		                callableStatement.execute();

		                JOptionPane.showMessageDialog(this, "Xóa món ăn thành công!");
		                listMenu.remove(selectedRow);
		                defaultTableModel.removeRow(selectedRow);
		                editMaMonAn.setText("");
		                editTenMonAn.setText("");
		                editGiaBan.setText("");
		            } catch (SQLException e) {
		                if (e.getErrorCode() == 20001) { 
		                    JOptionPane.showMessageDialog(this, "Không tìm thấy món để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa món ăn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		                e.printStackTrace();
		            }
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn một món ăn để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		    }
	}
	public void capNhatMonAn() {
		String maMonAn = editMaMonAn.getText();
        String tenMonAn = editTenMonAn.getText();
        int giaBan = Integer.parseInt(editGiaBan.getText());
        try (CallableStatement callableStatement = connection.prepareCall("{call sua_menu(?, ?, ?, ?)}")) {
            callableStatement.setString(1, selectedImagePath);
            callableStatement.setString(2, tenMonAn);
            callableStatement.setInt(3, giaBan);
            callableStatement.setString(4, maMonAn);
            callableStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cập nhật món ăn thành công!");
            
            int selectedRow = tableMonAn.getSelectedRow();
            if(selectedRow >=0)
			{
				listMenu.get(selectedRow).setHinhAnhMonAn(selectedImagePath);
				listMenu.get(selectedRow).setTenMonAn(tenMonAn);
				listMenu.get(selectedRow).setGiaBan(giaBan);
				listMenu.get(selectedRow).setMaMonAn(maMonAn);
				defaultTableModel.setValueAt(maMonAn, selectedRow, 0);
				defaultTableModel.setValueAt(tenMonAn, selectedRow, 1);
				defaultTableModel.setValueAt(giaBan, selectedRow, 2);
			}
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật món ăn: " + e.getMessage());
            e.printStackTrace();
        }
	    
	}
}
