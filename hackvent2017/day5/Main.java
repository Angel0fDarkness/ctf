public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		int[] crcTable = makeCRCTable(); // standard crc32 table
		int initCrc = 0; // Since we don't want to fix some existing CRC32 hash, we use 0 as the starting hash
		int desiredCrc = 0x97d1acf4;//0xb1b827f4;//0x9d26aaff;//0xdf45873c;//0xc2c8c11c;//0x69355f71;
		int[] pIs = getPatchIndices(desiredCrc, crcTable);
		System.out.println("PatchIndices:");
		for (int i = 0; i < pIs.length; i++)
			System.out.println("entry " + pIs[i] + ": " + crcTable[pIs[i]]);
		int[] patchBytes = getPatchBytes(desiredCrc, initCrc, crcTable, pIs);
		System.out.println("PatchBytes:");
		for (int i = 0; i < patchBytes.length; i++)
			System.out.println("PatchByte " + i + ": " + Integer.toHexString(patchBytes[i]) + " : " + (char)patchBytes[i]);
		System.out.println(Integer.toHexString(crc32(patchBytes, crcTable)));
	}
	
	private int[] getPatchBytes(int desiredCrc, int initCrc, int[] crcTable, int[] pIs){
		int[] patchBytes = new int[4];
		int curCrc = ~initCrc;
		for (int i = 0; i < patchBytes.length; i++){
			//patchBytes[i] = (curCrc & 0xff) ^ pIs[i];
			patchBytes[i] = (curCrc ^ pIs[i]) & 0xff ;
			curCrc = (curCrc >>> 8) ^ crcTable[patchBytes[i] ^ (curCrc & 0xff)];
		}
		return patchBytes;
	}
	
	private int[] getPatchIndices(int desiredCrc, int[] crcTable){
		// crc32 inverses the hash while working on it, so it has to be inverted
		// when accepting it as parameter and again when returning it
		int curCrc = ~desiredCrc;
		int[] pIs = new int[4]; // only 4 bytes needed to fix crc32
		for (int j = pIs.length - 1; j >= 0; j--) // calculate each pI
			for (int i = 0; i < crcTable.length; i++){ // check each index in the crcTable
				// TODO: more efficient table lookup
				// let the desiredCrc be AABBCCDD, with each 2 letters representing a byte
				// if entry's most sig. byte is the xor-inverse of AA, it's the correct one
				if ((curCrc ^ crcTable[i]) >> 24 == 0){ 
					// save index
					pIs[j] = i;
					// the index for AA has been found. Since the final BB was created in
					// the same manner as AA was but was shifted right one byte and then
					// XOR'd during the creation of AA, the creation of AA  and the shift
					// has to be reversed in order to find the index for BB.
					// CC and DD are calculated analogously.
					curCrc = (curCrc ^ crcTable[i]) << 8;
					break;
				}
			}
		return pIs;
	}
	
	private int[] makeCRCTable() {
        int c;
        int[] crcTable = new int[256];
        for (int n = 0; n < 256; n++) {
            c = n;
            for (int k = 0; k < 8; k++) {
                c = ((c & 1) == 1 ? (0xEDB88320 ^ (c >>> 1)) : (c >>> 1));
            }
            crcTable[n] = c;
        }
        return crcTable;
    }
	
	private int crc32(int[] msg, int[]crcTable) {
        int crc = 0 ^ (-1);
        for (int i = 0; i < msg.length; i++) {
            crc = (crc >>> 8) ^ crcTable[(crc ^ msg[i]) & 0xFF];
        }
        return ((crc & 0xFFFFFFFF) ^ (-1)) >>> 0;
    }

}
