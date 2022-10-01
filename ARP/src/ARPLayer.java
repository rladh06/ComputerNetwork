import java.util.ArrayList;

public class ARPLayer implements BaseLayer{

    public int nUpperLayerCount = 0;
    public String pLayerName = null;
    public BaseLayer p_UnderLayer = null;
    public ArrayList<BaseLayer> p_aUpperLayer = new ArrayList<BaseLayer>();


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

    /*
     * [Description of ARP Field]
     * hardType      // 2bytes. Type of Hardware Address
     * protType      // 2bytes. Type of Protocol Address
     * hardSize      // 1byte. (Ethernet - 6bytes)
     * protSize      // 1byte. (IP - 4bytes)
     * opCode        // 2bytes.
     * sender's Ethernet & IP Address
     * target's Ehternet & IP Address
     */

    private class _ARP_MSG {
        byte[] hardType;                // 2bytes. Type of Hardware Address
        byte[] protType;                // 2bytes. Type of Protocol Address
        byte hardSize;                  // 1byte. (Ethernet - 6bytes)
        byte protSize;                  // 1byte. (IP - 4bytes)
        byte[] opCode;                  // 2bytes.
        _ETHERNET_ADDR srcMacAddr;      //Sender's Ethernet Address
        _IP_ADDR srcIPAddr;             //Sender's IP Address
        _ETHERNET_ADDR dstMacAddr;      // Target's Ethernet Address;
        _IP_ADDR dstIPAddr;             //Target's IP Address;


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
