import java.util.ArrayList;
import java.util.Arrays;


public class RoutingTable {
	
	public ArrayList<_ROUTING_ENTRY_> routingTable = new ArrayList<>();
	
	class _ROUTING_ENTRY_{
		
		private byte[] RT_DEST_IP;		// Routing Table destination IP Address
		private byte[] RT_NETMASK;		// Routing Table Masking
		private byte[] RT_GATEWAY;		// Routing Table Gateway Address
		private String RT_FLAG;			// Routing Table FLAG : U, G, H 조합
		private String RT_INTERFACE;	// Routing Table Interface information
		private int RT_METRIC;			// Routing Table Metric information
		
		/* 생성자 */
		public _ROUTING_ENTRY_(){
			RT_DEST_IP = new byte[4];
			RT_NETMASK = new byte[4];
			RT_GATEWAY = new byte[4];
			RT_FLAG = "";
			RT_INTERFACE = "";
			RT_METRIC = 0;
		}
		
		public void setRoutingEntry(byte[] dstIP, byte[] netmask, byte[] gateway, String flag, String inter, int metric) {
			
			System.arraycopy(dstIP, 0, RT_DEST_IP, 0, 4);
			System.arraycopy(netmask, 0, RT_NETMASK, 0, 4);
			System.arraycopy(gateway, 0, RT_GATEWAY, 0, 4);
			RT_FLAG = flag;
			RT_INTERFACE = inter;
			RT_METRIC = metric;
			
		}

		public byte[] getRT_DEST_IP() {
			return RT_DEST_IP;
		}

		public byte[] getRT_NETMASK() {
			return RT_NETMASK;
		}

		public byte[] getRT_GATEWAY() {
			return RT_GATEWAY;
		}

		public String getRT_FLAG() {
			return RT_FLAG;
		}

		public String getRT_INTERFACE() {
			return RT_INTERFACE;
		}

		public int getRT_METRIC() {
			return RT_METRIC;
		}
	}
	
	// Routing Table에 Entry를 추가하는 함수
	public void addRoutingEntry(byte[] dstIP, byte[] netmask, byte[] gateway, String flag, String inter, int metric){
		_ROUTING_ENTRY_ entry = new _ROUTING_ENTRY_();		//Entry 생성
		entry.setRoutingEntry(dstIP, netmask, gateway, flag, inter, metric);	//Entry Setting
		routingTable.add(entry);		//Table에 Add
	}
	
	// index를 이용하여 Table에서 Entry찾아서 삭제
	public boolean deleteRoutingEntry(int index){
		// Table 범위 안에 들어가는 경우
		if(index >= 0 && index < routingTable.size()-1){
			routingTable.remove(index);
			return true;
		}
		return false;
	}
	
	// TODO: Routing Table에서 Masking하는 함수
	public _ROUTING_ENTRY_ findMatchingEntry(byte[] dstIP) {
		for (int i = 0; i < routingTable.size(); i++) {
			// current entry object
			_ROUTING_ENTRY_ currentEntry = routingTable.get(i);

			byte[] subnetMask = currentEntry.RT_NETMASK;
			byte[] maskingResult = maskingDstIP(dstIP, subnetMask);

			// matching success
			if (Arrays.equals(currentEntry.RT_DEST_IP, maskingResult))
				return currentEntry;
		}

		return null;
	}

	// masking dstIP for subnetMask
	public byte[] maskingDstIP(byte[] dstIP, byte[] subnetMask) {
		byte[] maskingResult = new byte[4];
		for (int i = 0; i < 4; i++) {
			maskingResult[i] = (byte) (dstIP[i] & subnetMask[i]);
		}
		
		return maskingResult;
	}


	// TODO: Routing Table에 있는지 확인하는 함수
	
	public _ROUTING_ENTRY_ checkTable(byte[] destination) {
		
		if(routingTable.size() < 1) return null;
		
		for(_ROUTING_ENTRY_ entry : routingTable) {
			byte[] count = new byte[4];
			byte[] result = new byte[4];
			
			//subnet mask 계산
			for(int i = 0; i < 4; i++) {
				if((entry.getRT_NETMASK()[i]&0xFF) == 255) 
					count[i] += 8;
				else {
					int n = entry.getRT_NETMASK()[i]&0xFF;
					while(n != 0) {
						count[i] += n%2;
						n /= 2;
					}
				}
			}
			//마스킹
			for (int i = 0; i < 4; i++) {
				result[i] = (byte) (destination[i] & count[i]);							
					}
			//테이블에 마스킹된 값이 존재하는지 확인
			if(Arrays.equals(result, entry.getRT_DEST_IP())) {
				return entry;
				}
				
			}
		
		
		return routingTable.get(routingTable.size() - 1);
	}
}
