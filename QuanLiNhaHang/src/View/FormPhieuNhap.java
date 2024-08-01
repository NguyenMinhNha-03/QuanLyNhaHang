package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.sqlconnect;
import Model.DangNhap;
import Model.NguyenLieu;
import Model.PhieuNhap;
import javax.swing.border.EtchedBorder;

public class FormPhieuNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ArrayList<PhieuNhap>listPhieuNhap;
	private sqlconnect con;
	private Connection connection;
	private JTextField editNgayNhap;
	private JTextField editTongTien;
	private JTable tablePhieuNhap;
	private JComboBox cbNCC;
	DefaultTableModel defaultTableModel;
	private JTextField editMaPN;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPhieuNhap frame = new FormPhieuNhap();
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
	public FormPhieuNhap() {
		con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
	    connection = con.getCon();
	    initialize();
		
	}
	public void initialize()
	{
		setTitle("Form Phieu Nhap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 382);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin phi\u1EBFu nh\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(471, 22, 274, 204);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã PN:");
		lblNewLabel.setBounds(10, 43, 96, 13);
		panel.add(lblNewLabel);
		
		editNgayNhap = new JTextField();
		editNgayNhap.setBounds(105, 74, 125, 19);
		panel.add(editNgayNhap);
		editNgayNhap.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ngày nhập:");
		lblNewLabel_1.setBounds(10, 80, 96, 13);
		panel.add(lblNewLabel_1);
		
		editTongTien = new JTextField();
		editTongTien.setEnabled(false);
		editTongTien.setText("");
		editTongTien.setBounds(105, 111, 125, 19);
		panel.add(editTongTien);
		editTongTien.setColumns(10);
		
		cbNCC = new JComboBox();
		cbNCC.setBounds(105, 151, 125, 21);
		panel.add(cbNCC);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tổng tiền");
		lblNewLabel_1_1.setBounds(10, 117, 96, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nhà cung cấp:");
		lblNewLabel_1_1_1.setBounds(10, 159, 96, 13);
		panel.add(lblNewLabel_1_1_1);
		
		editMaPN = new JTextField();
		editMaPN.setColumns(10);
		editMaPN.setBounds(105, 40, 125, 19);
		panel.add(editMaPN);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(42, 39, 419, 187);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout());
		defaultTableModel =new DefaultTableModel();
		tablePhieuNhap = new JTable(defaultTableModel);
		tablePhieuNhap.setBounds(10, 10, 343, 167);
		tablePhieuNhap.setBackground(new Color(255,255,255));
		panel_1.setLayout(new BorderLayout());
		JScrollPane jScrollPane=new JScrollPane(tablePhieuNhap,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(jScrollPane,BorderLayout.CENTER);
	
		
		JButton btnThemPN = new JButton("Thêm phiếu nhập");
		btnThemPN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themPhieuNhap();
			}
		});
		btnThemPN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemPN.setBounds(42, 236, 140, 30);
		contentPane.add(btnThemPN);
		
		JButton btnCapNhatPN = new JButton("Cập nhật tổng tiền");
		btnCapNhatPN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTongTien();
			}
		});
		btnCapNhatPN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCapNhatPN.setBounds(212, 236, 193, 30);
		contentPane.add(btnCapNhatPN);
		listPhieuNhap=getListPN();
		defaultTableModel=(DefaultTableModel)tablePhieuNhap.getModel();
		defaultTableModel.setColumnIdentifiers(new Object[] {
				"Mã NCC","Ngày nhập","Tổng tiền","Nhà cung cấp"
		});
		tablePhieuNhap.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(tablePhieuNhap.getSelectedRow() >=0)
				{
					PhieuNhap phieuNhap = listPhieuNhap.get(tablePhieuNhap.getSelectedRow());
					editMaPN.setText(phieuNhap.getMaPN());
		            editNgayNhap.setText(phieuNhap.getNgayNhap()+"");
		            editTongTien.setText(phieuNhap.getTongTienNhap()+"");
		            cbNCC.setSelectedItem(phieuNhap.getMaNCC());                
				}
			}
		});
		showTablePN();
		showCbNCC();
	}
	public ArrayList<PhieuNhap>getListPN()
	{
		ArrayList<PhieuNhap>listPN=new ArrayList<PhieuNhap>();
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call showALLPhieuNhap(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				PhieuNhap phieuNhap=new PhieuNhap();
				phieuNhap.setMaPN(resultSet.getString("maphieunhap"));
				phieuNhap.setNgayNhap(resultSet.getDate("ngaynhap"));
				phieuNhap.setTongTienNhap(resultSet.getInt("tongtiennhap"));
				phieuNhap.setMaNCC(resultSet.getString("mancc"));;
	
				listPN.add(phieuNhap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listPN;
	}
	public void showTablePN()
	{
		defaultTableModel.setRowCount(0);
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		String sql="{Call hien_thi_phieu_nhap(?)}";
		try {
			callableStatement=connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
			callableStatement.execute();
			resultSet=(ResultSet)callableStatement.getObject(1);
			
			while(resultSet.next())
			{
				String maPN=resultSet.getString("maphieunhap");
				String ngayNhap=resultSet.getString("ngaynhap");
				String tongtien=resultSet.getString("tongtiennhap");
				String mancc=resultSet.getString("mancc");
				defaultTableModel.addRow(new Object[] {maPN,ngayNhap,tongtien,mancc});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị phiếu nhập"+e.getMessage());
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
	public void showCbNCC()
	{
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call showMaNCC(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				cbNCC.addItem(resultSet.getString("mancc"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void themPhieuNhap()
	{
		String maPN=editMaPN.getText().trim();
		String ngaynhap=editNgayNhap.getText().trim();
		String ncc=(String)cbNCC.getSelectedItem();
		int tongTienString=Integer.parseInt(editTongTien.getText().trim());
		if(maPN.isEmpty() || ngaynhap.isEmpty() || ncc == null)
		{
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin! ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
	        java.text.SimpleDateFormat inputFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
	        java.text.SimpleDateFormat outputFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date date = inputFormat.parse(ngaynhap);
	        ngaynhap = outputFormat.format(date);
	    } catch (java.text.ParseException e) {
	        JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

		String sqlString="{Call themphieunhap(?,TO_DATE(?, 'YYYY-MM-DD'),?)}";
		try {
			CallableStatement callableStatement=connection.prepareCall(sqlString);
			callableStatement.setString(1, maPN);
			callableStatement.setString(2, ngaynhap);
			callableStatement.setString(3, ncc);
			callableStatement.execute();
			JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thành công!");
			listPhieuNhap.add(new PhieuNhap(maPN,Date.valueOf(ngaynhap),tongTienString,ncc));
			defaultTableModel.addRow(new Object[] {maPN,ngaynhap,tongTienString,ncc});
			editMaPN.setText("");
			editNgayNhap.setText("");
			editTongTien.setText("");
			cbNCC.setSelectedItem("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi thêm phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);

		}		
	}
	private void updateTongTien() {
        CallableStatement callableStatement = null;
        try {
            String sql = "{CALL update_tongtiennhap}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.execute();
            JOptionPane.showMessageDialog(this, "Cập nhật tổng tiền thành công!");
            showTablePN();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật tổng tiền: " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
