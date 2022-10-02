import javax.swing.*;
import java.util.ArrayList;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

public class ApplicationLayer extends JFrame implements BaseLayer{

    public int nUpperLayerCount = 0;
    public String pLayerName = null;
    public BaseLayer p_UnderLayer = null;
    public ArrayList<BaseLayer> p_aUpperLayer = new ArrayList<BaseLayer>();

    String path;

    private static LayerManager m_LayerMgr = new LayerManager();

	private JPanel contentPanel;				// 전체 화면 JPanel
    
    private JPanel BasicPanel;					// Basic ARP용 JPanel
    private JPanel ARPAreaPanel;				// ARP Cache Border
    private JTextArea ARPCacheTableArea;		// APR Cache Table용 Text Area
    private JButton btnItemDelete;				// Basic ARP Selected Item Delete Button
    private JButton btnAllDelete;				// Basic ARP All ITem Delete Button
    private JTextField IPAddrInput;				// Basic ARP IP Input Field
    private JButton btnBasicSend;				// Basic ARP IP Send Button
    
    private JPanel ProxyPane;					// Proxy ARP용 Panel
    private JPanel ProxyEntryPanel;				// Proxy Entry 표현용 Panel
    private JTextArea ProxyEntryArea;			// Proxy Entry를 나타내는 Text Area
    private JButton btnProxyAdd;				// Proxy Entry Add Button
    private JButton btnProxyDelete;				// Proxy Entry Delete Button
    
    private JPanel GratuitousPane;				// Gratuitous ARP용 Panel
    private JTextField MACAddrInput;			// Gratuitous ARP MAC Address Input Field
    private JButton btnGratSend;				// Gratuitous ARP Mac Address Send Button
    
    private JButton btnCancel;					// 취소 버튼(?)
    private JButton btnExit;					// ARP Protocol 종료 및 창 닫기(?)

    /*
    *  TODO LIST - main function
    *   1. Add All Layer
    *   2. Connect Each Layer Bidirectional(*)
    * */
    public static void main(String[] args) {
        m_LayerMgr.AddLayer(new NILayer("NI"));

		m_LayerMgr.AddLayer(new ApplicationLayer("GUI"));
        //m_LayerMgr.ConnectLayers( );
    }


	/* GUI Code */
	public ApplicationLayer(String pName) {
		setTitle("TestARP");
		pLayerName = pName;

		setBounds(250, 250, 752, 492);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		BasicPanel = new JPanel();
		BasicPanel.setBorder(new TitledBorder(null, "ARP Cache", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		BasicPanel.setBounds(14, 29, 351, 333);
		contentPanel.add(BasicPanel);
		BasicPanel.setLayout(null);
		
		ARPAreaPanel = new JPanel();
		ARPAreaPanel.setBounds(13, 25, 322, 195);
		ARPAreaPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		BasicPanel.add(ARPAreaPanel);
		ARPAreaPanel.setLayout(null);
		
		ARPCacheTableArea = new JTextArea();
		ARPCacheTableArea.setBounds(1, 1, 318, 194);
		ARPCacheTableArea.setEditable(false);
		ARPAreaPanel.add(ARPCacheTableArea);
		ARPCacheTableArea.setColumns(65);
		ARPCacheTableArea.setRows(17);
		
		btnItemDelete = new JButton("Item Delete");
		btnItemDelete.setBounds(34, 232, 127, 42);
		BasicPanel.add(btnItemDelete);
		btnItemDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnAllDelete = new JButton("All Detete");
		btnAllDelete.setBounds(199, 232, 127, 42);
		btnAllDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BasicPanel.add(btnAllDelete);
		
		JLabel IPAddrLabel = new JLabel("IP주소");
		IPAddrLabel.setBounds(13, 294, 42, 18);
		BasicPanel.add(IPAddrLabel);
		
		
		btnBasicSend = new JButton("Send");
		btnBasicSend.setBounds(272, 286, 65, 35);
		BasicPanel.add(btnBasicSend);
		
		IPAddrInput = new JTextField();
		IPAddrInput.setBounds(58, 286, 206, 35);
		BasicPanel.add(IPAddrInput);
		IPAddrInput.setColumns(10);
		
		btnExit = new JButton("종료");
		btnExit.setBounds(260, 388, 105, 34);
		contentPanel.add(btnExit);
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(393, 388, 105, 34);
		contentPanel.add(btnCancel);
		
		ProxyPane = new JPanel();
		ProxyPane.setBorder(new TitledBorder(null, "Proxy ARP Entry", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ProxyPane.setBounds(371, 29, 349, 236);
		contentPanel.add(ProxyPane);
		ProxyPane.setLayout(null);
		
		ProxyEntryPanel = new JPanel();
		ProxyEntryPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ProxyEntryPanel.setBounds(14, 30, 312, 149);
		ProxyPane.add(ProxyEntryPanel);
		ProxyEntryPanel.setLayout(null);
		
		ProxyEntryArea = new JTextArea();
		ProxyEntryArea.setBounds(1, 1, 312, 157);
		ProxyEntryPanel.add(ProxyEntryArea);
		
		btnProxyAdd = new JButton("Add");
		btnProxyAdd.setBounds(33, 191, 105, 33);
		ProxyPane.add(btnProxyAdd);
		
		btnProxyDelete = new JButton("Delete");
		btnProxyDelete.setBounds(193, 191, 105, 33);
		ProxyPane.add(btnProxyDelete);
		
		GratuitousPane = new JPanel();
		GratuitousPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gratuitous ARP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GratuitousPane.setBounds(371, 277, 349, 85);
		contentPanel.add(GratuitousPane);
		GratuitousPane.setLayout(null);
		
		JLabel lblHwA = new JLabel("H/W주소");
		lblHwA.setHorizontalAlignment(SwingConstants.CENTER);
		lblHwA.setBounds(0, 25, 75, 45);
		GratuitousPane.add(lblHwA);
		

		btnGratSend = new JButton("Send");
		btnGratSend.setBounds(257, 28, 75, 39);
		GratuitousPane.add(btnGratSend);
		
		MACAddrInput = new JTextField();
		MACAddrInput.setBounds(76, 30, 178, 34);
		GratuitousPane.add(MACAddrInput);
		MACAddrInput.setColumns(10);
		
		setVisible(true);

	}

    @Override
    public void SetUnderLayer(BaseLayer pUnderLayer) {
        // TODO Auto-generated method stub
        if (pUnderLayer == null)
            return;
        this.p_UnderLayer = pUnderLayer;
    }

    @Override
    public void SetUpperLayer(BaseLayer pUpperLayer) {
        // TODO Auto-generated method stub
        if (pUpperLayer == null)
            return;
        this.p_aUpperLayer.add(nUpperLayerCount++, pUpperLayer);
        // nUpperLayerCount++;
    }

    @Override
    public String GetLayerName() {
        // TODO Auto-generated method stub
        return pLayerName;
    }

    @Override
    public BaseLayer GetUnderLayer() {
        // TODO Auto-generated method stub
        if (p_UnderLayer == null)
            return null;
        return p_UnderLayer;
    }

    @Override
    public BaseLayer GetUpperLayer(int nindex) {
        // TODO Auto-generated method stub
        if (nindex < 0 || nindex > nUpperLayerCount || nUpperLayerCount < 0)
            return null;
        return p_aUpperLayer.get(nindex);
    }

    @Override
    public void SetUpperUnderLayer(BaseLayer pUULayer) {
        this.SetUpperLayer(pUULayer);
        pUULayer.SetUnderLayer(this);

    }
}
