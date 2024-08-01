package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.sqlconnect;
import Model.DangNhap;
import Model.Menu;
import Model.NguyenLieu;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormNguyenLieu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultTableModel defaultTableModel;
	ArrayList<NguyenLieu> listNguyenLieu;
	private sqlconnect con;
	private Connection connection;
	private JTextField editMaNL;
	private JTextField editTenNL;
	private JTable tableNguyenLieu;
	private JComboBox cbDVT;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormNguyenLieu frame = new FormNguyenLieu();
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
	public FormNguyenLieu() {
		con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
		connection = con.getCon();

		setTitle("Form Nguyen Lieu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 382);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin nguy\u00EAn li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(415, 22, 206, 204);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã nguyên liệu:");
		lblNewLabel.setBounds(10, 49, 96, 13);
		panel.add(lblNewLabel);
		
		editMaNL = new JTextField();
		editMaNL.setBounds(122, 46, 74, 19);
		panel.add(editMaNL);
		editMaNL.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên nguyên liệu:");
		lblNewLabel_1.setBounds(10, 98, 96, 13);
		panel.add(lblNewLabel_1);
		
		editTenNL = new JTextField();
		editTenNL.setText("");
		editTenNL.setBounds(122, 95, 74, 19);
		panel.add(editTenNL);
		editTenNL.setColumns(10);
		
		cbDVT = new JComboBox();
		cbDVT.setBounds(122, 140, 74, 21);
		panel.add(cbDVT);
		
		JLabel lblNewLabel_1_1 = new JLabel("Đơn vị tính:");
		lblNewLabel_1_1.setBounds(10, 144, 96, 13);
		panel.add(lblNewLabel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(42, 39, 363, 187);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout());
		defaultTableModel =new DefaultTableModel();
		tableNguyenLieu = new JTable(defaultTableModel);
		tableNguyenLieu.setBounds(10, 10, 343, 167);
		tableNguyenLieu.setBackground(new Color(255,255,255));
		panel_1.setLayout(new BorderLayout());
		JScrollPane jScrollPane=new JScrollPane(tableNguyenLieu,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(jScrollPane,BorderLayout.CENTER);
	
		
		JButton btnThemNguyenLieu = new JButton("Thêm nguyên liệu");
		btnThemNguyenLieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themNguyenLieu();
			}
		});
		btnThemNguyenLieu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemNguyenLieu.setBounds(42, 236, 140, 30);
		contentPane.add(btnThemNguyenLieu);
		
		JButton btnXoaNguyenLieu = new JButton("Xóa nguyên liệu");
		btnXoaNguyenLieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaNguyenLieu();
			}
		});
		btnXoaNguyenLieu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXoaNguyenLieu.setBounds(265, 236, 140, 30);
		contentPane.add(btnXoaNguyenLieu);
		
		JButton btnCapNhatNguyenLieu = new JButton("Cập nhật nguyên liệu");
		btnCapNhatNguyenLieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatNguyenLieu();
			}
		});
		btnCapNhatNguyenLieu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCapNhatNguyenLieu.setBounds(129, 276, 193, 30);
		contentPane.add(btnCapNhatNguyenLieu);
		listNguyenLieu=getListNguyenlieu();
		defaultTableModel=(DefaultTableModel)tableNguyenLieu.getModel();
		defaultTableModel.setColumnIdentifiers(new Object[] {
				"Mã nguyên liệu","Tên nguyên liệu","Đơn vị tính"
		});
		tableNguyenLieu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(tableNguyenLieu.getSelectedRow() >=0)
				{
					NguyenLieu nguyenLieu = listNguyenLieu.get(tableNguyenLieu.getSelectedRow());
		            editMaNL.setText(nguyenLieu.getMaNguyenLieu());
		            editTenNL.setText(nguyenLieu.getTenNguyenLieu());
		            cbDVT.setSelectedItem(nguyenLieu.getDonViTinh());                
				}
			}
		});
		showTableNguyenLieu();
		showCbDonViTinh();
	}
	public ArrayList<NguyenLieu>getListNguyenlieu()
	{
		ArrayList<NguyenLieu>list=new ArrayList<NguyenLieu>();
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call GET_NGUYENLIEU_LIST(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				NguyenLieu nguyenLieu=new NguyenLieu();
				nguyenLieu.setMaNguyenLieu(resultSet.getString("manguyenlieu"));
				nguyenLieu.setTenNguyenLieu(resultSet.getString("tennguyenlieu"));
				nguyenLieu.setDonViTinh(resultSet.getString("donvitinh"));
				list.add(nguyenLieu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public void showTableNguyenLieu()
	{
		defaultTableModel.setRowCount(0);
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		String sql="{Call hien_thi_nguyen_lieu(?)}";
		try {
			callableStatement=connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
			callableStatement.execute();
			resultSet=(ResultSet)callableStatement.getObject(1);
			
			while(resultSet.next())
			{
				String maNL=resultSet.getString("manguyenlieu");
				String tenNL=resultSet.getString("tennguyenlieu");
				String DVT=resultSet.getString("donvitinh");
				defaultTableModel.addRow(new Object[] {maNL,tenNL,DVT});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị nguyên liệu"+e.getMessage());
		} finally
		{
			try {
				if(resultSet !=null) resultSet.close();
				if(callableStatement !=null)
				callableStatement.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
	}
	public void showCbDonViTinh()
	{
		String[] donViTinhs = { "Chai", "Kg", "Con", "Goi", "Bo", "Qua" };
		for (String dvt : donViTinhs) {
			cbDVT.addItem(dvt);
		}
	}
	public void themNguyenLieu()
	{
		String maNL=editMaNL.getText().trim();
		String tenNL=editTenNL.getText().trim();
		String DVT=(String)cbDVT.getSelectedItem();
		if(maNL.isEmpty() || tenNL.isEmpty() || DVT == null )
		{
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin! ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String sqlString="{Call themnguyenlieu(?,?,?)}";
		try {
			CallableStatement callableStatement=connection.prepareCall(sqlString);
			callableStatement.setString(1, maNL);
			callableStatement.setString(2, tenNL);
			callableStatement.setString(3, DVT);
			callableStatement.execute();
			
			JOptionPane.showMessageDialog(this, "Thêm nguyên liệu thành công!");
			listNguyenLieu.add(new NguyenLieu(maNL,tenNL,DVT));
			defaultTableModel.addRow(new Object[] {maNL,tenNL,DVT});
			editMaNL.setText("");
			editTenNL.setText("");
			cbDVT.setSelectedItem("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi thêm nguyên liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);

		}		
	}
	public void xoaNguyenLieu()
	{
		 int selectedRow = tableNguyenLieu.getSelectedRow();
		    if (selectedRow >= 0) {
		        String maNL = listNguyenLieu.get(selectedRow).getMaNguyenLieu();
		        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            try (CallableStatement callableStatement = connection.prepareCall("{Call xoaNguyenLieu(?)}")) {
		                callableStatement.setString(1, maNL);
		                callableStatement.execute();

		                JOptionPane.showMessageDialog(this, "Xóa nguyên liệu thành công!");
		                listNguyenLieu.remove(selectedRow);
		                defaultTableModel.removeRow(selectedRow);
		                editMaNL.setText("");
		    			editTenNL.setText("");
		    			cbDVT.setSelectedItem("");
		            } catch (SQLException e) {
		                if (e.getErrorCode() == 20001) { 
		                    JOptionPane.showMessageDialog(this, "Không tìm thấy nguyên liệu để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa nguyên liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		                e.printStackTrace();
		            }
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn một nguyên liệu để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		    }
	}
	public void capNhatNguyenLieu()
	{
		String maNL=editMaNL.getText().trim();
		String tenNL=editTenNL.getText().trim();
		String DVT=(String)cbDVT.getSelectedItem();
		if(maNL.isEmpty() || tenNL.isEmpty() || DVT == null)
		{
			 JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		     return;
		}
		try(CallableStatement callableStatement=connection.prepareCall("{Call sua_nguyenlieu(?,?,?)}")) {
			callableStatement.setString(1, tenNL);
			callableStatement.setString(2, DVT);
			callableStatement.setString(3, maNL);
			callableStatement.executeUpdate();
			JOptionPane.showMessageDialog(this, "Cập nhật nguyên liệu thành công!");
			
			int selectRow= tableNguyenLieu.getSelectedRow();
			if(selectRow >=0)
			{
				listNguyenLieu.get(selectRow).setTenNguyenLieu(tenNL);
				listNguyenLieu.get(selectRow).setDonViTinh(tenNL);
				listNguyenLieu.get(selectRow).setMaNguyenLieu(maNL);
				defaultTableModel.setValueAt(maNL, selectRow, 0);
				defaultTableModel.setValueAt(tenNL, selectRow, 1);
				defaultTableModel.setValueAt(DVT, selectRow, 2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật nguyên liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
}
