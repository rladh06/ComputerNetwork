import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;


public class RouterDlg {

	private JFrame frmStaticRouter;
	private JTable table;
	private JTable ARPCacheTable;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouterDlg window = new RouterDlg();
					window.frmStaticRouter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RouterDlg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStaticRouter = new JFrame();
		frmStaticRouter.setTitle("Static Router");
		frmStaticRouter.setBounds(100, 100, 1114, 581);
		frmStaticRouter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStaticRouter.getContentPane().setLayout(null);
		
		JPanel StaticRouterPane = new JPanel();
		StaticRouterPane.setBounds(14, 12, 585, 510);
		frmStaticRouter.getContentPane().add(StaticRouterPane);
		StaticRouterPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Static Routing Table");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(157, 12, 260, 29);
		StaticRouterPane.add(lblNewLabel);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Metric", "Destination", "NetMask", "Gateway", "Flag", "Interface"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		table.getColumnModel().getColumn(2).setPreferredWidth(141);
		table.getColumnModel().getColumn(3).setPreferredWidth(132);
		table.getColumnModel().getColumn(5).setPreferredWidth(139);
		table.setBounds(14, 53, 557, 387);
		StaticRouterPane.add(table);
		
		JButton btnRouterAdd = new JButton("Add");
		btnRouterAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddRouterDlg();
			}
		});
		btnRouterAdd.setBounds(157, 459, 105, 27);
		StaticRouterPane.add(btnRouterAdd);
		
		JButton btnRouterDelete = new JButton("Delete");
		btnRouterDelete.setBounds(342, 459, 105, 27);
		StaticRouterPane.add(btnRouterDelete);
		
		JPanel ARPCachePane = new JPanel();
		ARPCachePane.setBounds(605, 12, 477, 270);
		frmStaticRouter.getContentPane().add(ARPCachePane);
		ARPCachePane.setLayout(null);
		
		JLabel lblARPCache = new JLabel("ARP Cache Table");
		lblARPCache.setHorizontalAlignment(SwingConstants.CENTER);
		lblARPCache.setFont(new Font("굴림", Font.BOLD, 23));
		lblARPCache.setBounds(111, 12, 260, 29);
		ARPCachePane.add(lblARPCache);
		
		ARPCacheTable = new JTable();
		ARPCacheTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ARPCacheTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IP Address", "Ethernet Address", "Interface", "Flag"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		ARPCacheTable.getColumnModel().getColumn(0).setResizable(false);
		ARPCacheTable.getColumnModel().getColumn(0).setPreferredWidth(157);
		ARPCacheTable.getColumnModel().getColumn(1).setPreferredWidth(169);
		ARPCacheTable.setBounds(14, 62, 449, 158);
		ARPCachePane.add(ARPCacheTable);
		
		JButton btnARPCacheAdd = new JButton("Add");
		btnARPCacheAdd.setBounds(189, 231, 105, 27);
		ARPCachePane.add(btnARPCacheAdd);
		
		JPanel ProxyARPPane = new JPanel();
		ProxyARPPane.setBounds(605, 294, 477, 228);
		frmStaticRouter.getContentPane().add(ProxyARPPane);
		ProxyARPPane.setLayout(null);
		
		JLabel lblProxyArpTable = new JLabel("Proxy ARP Table");
		lblProxyArpTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblProxyArpTable.setFont(new Font("굴림", Font.BOLD, 23));
		lblProxyArpTable.setBounds(120, 10, 260, 29);
		ProxyARPPane.add(lblProxyArpTable);
		
		table_1 = new JTable();
		table_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IP Address", "Ethernet Address", "Interface"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(172);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(106);
		table_1.setBounds(14, 51, 449, 125);
		ProxyARPPane.add(table_1);
		
		JButton btnProxyAdd = new JButton("Add");
		btnProxyAdd.setBounds(136, 188, 105, 27);
		ProxyARPPane.add(btnProxyAdd);
		
		JButton btnProxyDelete = new JButton("Delete");
		btnProxyDelete.setBounds(265, 188, 105, 27);
		ProxyARPPane.add(btnProxyDelete);
	}
}

class AddRouterDlg {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the application.
	 */
	public AddRouterDlg() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.setTitle("Add Routing Table");
		frame.setBounds(100, 100, 450, 331);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(42, 32, 85, 18);
		frame.getContentPane().add(lblDestination);
		
		JLabel lblNetmask = new JLabel("Netmask");
		lblNetmask.setBounds(42, 72, 85, 18);
		frame.getContentPane().add(lblNetmask);
		
		JLabel lblGateway = new JLabel("Gateway");
		lblGateway.setBounds(42, 112, 85, 18);
		frame.getContentPane().add(lblGateway);
		
		JLabel lblFlag = new JLabel("Flag");
		lblFlag.setBounds(42, 154, 79, 18);
		frame.getContentPane().add(lblFlag);
		
		JLabel lblInterface = new JLabel("Interface");
		lblInterface.setBounds(42, 194, 85, 18);
		frame.getContentPane().add(lblInterface);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(92, 239, 105, 27);
		frame.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(228, 239, 105, 27);
		frame.getContentPane().add(btnCancel);
		
		textField = new JTextField();
		textField.setBounds(142, 29, 231, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(142, 69, 231, 24);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(142, 109, 231, 24);
		frame.getContentPane().add(textField_2);
		
		JCheckBox chckbxUp = new JCheckBox("UP");
		chckbxUp.setBounds(142, 150, 55, 27);
		frame.getContentPane().add(chckbxUp);
		
		JCheckBox chckbxGateway = new JCheckBox("Gateway");
		chckbxGateway.setBounds(214, 150, 85, 27);
		frame.getContentPane().add(chckbxGateway);
		
		JCheckBox chckbxHost = new JCheckBox("Host");
		chckbxHost.setBounds(313, 150, 71, 27);
		frame.getContentPane().add(chckbxHost);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(142, 191, 231, 24);
		frame.getContentPane().add(comboBox);
	}
}
