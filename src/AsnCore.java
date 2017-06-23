import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AsnCore {

    private byte[] bytes;
    int position = 0;

    public AsnCore(byte[] bytes){
        this.bytes = bytes;
    }

    public String parse(){
        StringBuilder stringBuilder = new StringBuilder();
        if (position < bytes.length) {


            switch (bytes[position++]) {
                case 0x30:
                    parseSequenceOrSet("Sequence");
                    parse();
                    break;
                case 0x02:
                    parseInteger();
                    parse();
                    break;
                case 0x06:
                    parseOID();
                    parse();
                    break;
                case 0x05:
                    parseNULL();
                    parse();
                    break;
                case 0x31:
                    parseSequenceOrSet("Set");
                    parse();
                    break;
                case 0x13:
                    parseString("PrintableString");
                    parse();
                    break;
                case 0x0c:
                    parseString("UTF8String");
                    parse();
                    break;
                case 0x16:
                    parseString("IA5String");
                    parse();
                    break;
                case 0x17:
                    parseString("UTCTime");
                    parse();
                    break;
                case 0x03:
                    parseBitString();
                    parse();
                    break;
            }
        }
        return stringBuilder.toString();
    }

    private void parseSequenceOrSet(String type){
        System.out.print(type + ": ");
        int bitesCount = bytes[position++];
        if (bitesCount < 0){
            bitesCount = bitesCount & 127;
            int length = position + bitesCount;
            byte[] data = new byte[bitesCount];
            for (int i = position, j = 0; i < length; i++, j++, position++){
                data[j] = bytes[i];
            }
            String hexString = bytesToHex(data);
            System.out.println("length = " + Integer.valueOf(hexString, 16));
        } else {
            System.out.println("length = " + bitesCount);
        }
    }

    //переделать, епта
    private void parseInteger(){
        System.out.print("Integer: ");
        int bitesCount = bytes[position++] & 127;
        int length = position + bitesCount;
        byte[] data = new byte[bitesCount];
        for (int i = position, j = 0; i < length; i++, j++, position++){
            data[j] = bytes[i];
        }
        String hexString = bytesToHex(data);
        System.out.println("value = " + new BigInteger(hexString, 16));
    }

    private void parseOID(){
        System.out.print("Object identifier: ");
        int bitesCount = bytes[position++];
        int first = bytes[position++];
        System.out.print(first/40 + "." + first % 40);
        int length = position + bitesCount;
        List<Byte> list = new ArrayList<>();
        for (int i = position; i < length - 1; i++, position++){
            list.add(bytes[i]);
            if (bytes[i] > 0){
                int resultNumber = 0;
                for (int j = 0; j < list.size(); j++){
                    int byteFromList = list.get(j);
                    if (byteFromList < 0)
                        byteFromList = byteFromList & 127;
                    resultNumber += byteFromList * (int)Math.pow(128, list.size() - 1 - j);
                }
                System.out.print("." + resultNumber);
                list.clear();
            }
        }
        System.out.println();
    }

    private void parseNULL(){
        System.out.println("NULL");
        position++;
    }

    private void parseString(String type){
        System.out.print(type + ": ");
        int bitesCount = bytes[position++];
        bitesCount = bitesCount & 127;
        int length = position + bitesCount;
        byte[] data = new byte[bitesCount];
        for (int i = position, j = 0; i < length; i++, j++, position++){
            data[j] = bytes[i];
        }
        try {
            System.out.println(new String(data, "UTF-8") + ", length = " + bitesCount);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void parseBitString(){
        System.out.print("BitString: ");
        int bitesCount = bytes[position++];
        if (bitesCount < 0){
            bitesCount = bitesCount & 127;
            int length = position + bitesCount;
            byte[] data = new byte[bitesCount];
            for (int i = position, j = 0; i < length; i++, j++, position++){
                data[j] = bytes[i];
            }
            String hexString = bytesToHex(data);
            System.out.println("length = " + Integer.valueOf(hexString, 16));
            position += Integer.valueOf(hexString, 16);
        } else {
            System.out.println("length = " + bitesCount);
        }
    }

    private String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
