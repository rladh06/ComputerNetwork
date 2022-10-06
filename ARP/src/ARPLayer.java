import java.util.ArrayList;

public class ARPLayer implements BaseLayer{

    public int nUpperLayerCount = 0;
    public String pLayerName = null;
    public BaseLayer p_UnderLayer = null;
    public ArrayList<BaseLayer> p_aUpperLayer = new ArrayList<BaseLayer>();
    
    // ARP Cache Table
    public ArrayList<_ARP_Cache> ArpCacheTable = new ArrayList<>();
    //Proxy Entry Table
    public ArrayList<_Proxy_Entry> ProxyEntryTable = new ArrayList<>();

    private class _IP_ADDR {
        private byte[] addr = new byte[4];

        public _IP_ADDR() {
            this.addr[0] = (byte) 0x00;
            this.addr[1] = (byte) 0x00;
            this.addr[2] = (byte) 0x00;
            this.addr[3] = (byte) 0x00;
        }
    }

    private class _ETHERNET_ADDR {
        private byte[] addr = new byte[6];

        public _ETHERNET_ADDR() {
            this.addr[0] = (byte) 0x00;
            this.addr[1] = (byte) 0x00;
            this.addr[2] = (byte) 0x00;
            this.addr[3] = (byte) 0x00;
            this.addr[4] = (byte) 0x00;
            this.addr[5] = (byte) 0x00;
        }
    }


    private class _ARP_MSG {
        byte[] hardType;                // 2bytes. Type of Hardware Address
        byte[] protType;                // 2bytes. Type of Protocol Address
        byte hardSize;                  // 1byte. (Ethernet - 6bytes)
        byte protSize;                  // 1byte. (IP - 4bytes)
        byte[] opCode;                  // 2bytes. [1 : ARP Request / 2 : ARP Reply]
        _ETHERNET_ADDR srcMacAddr;      //Sender's Ethernet Address(MAC주소 : 6bytes)
        _IP_ADDR srcIPAddr;             //Sender's IP Address(IP주소 : 4bytes)
        _ETHERNET_ADDR dstMacAddr;      // Target's Ethernet Address;(MAC주소 : 6bytes)
        _IP_ADDR dstIPAddr;             //Target's IP Address;(IP주소 : 4bytes)


        public _ARP_MSG() {
            this.hardType = new byte[2];
            this.protType = new byte[2];
            this.hardSize = (byte) 0x00;
            this.protSize = (byte) 0x00;
            this.opCode = new byte[2];
            this.srcMacAddr = new _ETHERNET_ADDR();
            this.srcIPAddr = new _IP_ADDR();
            this.dstMacAddr = new _ETHERNET_ADDR();
            this.dstIPAddr = new _IP_ADDR();
        }
    }
    
    // ARP Cache
    public class _ARP_Cache {
    	byte[] ipAddr;
    	byte[] macAddr;
    	boolean status;				// complete == true, incomplete == false
    	
    	public _ARP_Cache(byte[] ipAddr, byte[] macAddr, boolean status) {
    		this.ipAddr = ipAddr;
    		this.macAddr = macAddr;
    		this.status = status;
    	}
    }
    
    public class _Proxy_Entry {
    	String hostName;
    	byte[] ipAddr;
    	byte[] macAddr;
    	
    	public _Proxy_Entry (String hostName, byte[] ipAddr, byte[] macAddr) {
    		this.hostName = hostName;
    		this.ipAddr = ipAddr;
    		this.macAddr = macAddr;
    	}
    }

    /*
    *   TODO
    *    - ARP Send / Receive 함수 구현
    *    - Proxy ARP
    */

    // Send 함수(GARP는 따로 생성)
    public boolean Send(byte[] OpCode, _ETHERNET_ADDR srcMacAddr, _IP_ADDR srcIPAddr, _ETHERNET_ADDR dstMacAddr, _IP_ADDR dstIPAddr) {

        // Cache Entry Table에서 확인(있으면 거기로 전송, 없으면 캐시 추가)


        return true;
    }

    // Reply Send할 때 쓸 함수
    public boolean ReplySend(_ARP_MSG arpMsg) {
        _ARP_MSG rplMsg = new _ARP_MSG();
        // opcode 2로 설정
        
        //Sender & Target 정보 서로 변경
        
        // 하위 Layer로 내려보냄

        return true;
    }

    // Receive 함수
    public boolean Receive(byte[] input) {

        // case 0: Basic ARP or Proxy ARP
        // 나한테 온 메세지인지 확인 -> 나한테 온 거면 ARP Reply 전송해야
        // 나한테 온 메세지인지 확인하는 방법 : 내 IP주소와 일치하는지? 나의 Proxy Table에 해당 IP가 존재하는지?

        // case 1: Gratuitous ARP
        // Cache Table Update

        // case 2 : ARP Reply
        // Cache Table Update

        return true;
    }

    // _ARP_MSG Object를 byte[]로 바꿔주는 함수
    public byte[] ObjToByte(_ARP_MSG arpMsg, byte[] input, int length) {
        byte[] buf = new byte[27 + length];
        
        return buf;
    }

    //

    @Override
    public void SetUnderLayer(BaseLayer pUnderLayer) {
        if (pUnderLayer == null)
            return;
        this.p_UnderLayer = pUnderLayer;
    }

    @Override
    public void SetUpperLayer(BaseLayer pUpperLayer) {
        if (pUpperLayer == null)
            return;
        this.p_aUpperLayer.add(nUpperLayerCount++, pUpperLayer);
    }

    @Override
    public String GetLayerName() {
        // TODO Auto-generated method stub
        return pLayerName;
    }

    @Override
    public BaseLayer GetUnderLayer() {
        if (p_UnderLayer == null)
            return null;
        return p_UnderLayer;
    }

    @Override
    public BaseLayer GetUpperLayer(int nindex) {
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
