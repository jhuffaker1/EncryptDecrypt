import java.io.*;

public class HexBuilder {
    public HexBuilder() {

    }

    public static String getHex(File inputFile) throws FileNotFoundException, IOException {

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(inputFile);
        int read;
        while((read = fis.read(buffer)) != -1) {
            os.write(buffer, 0 , read);
        }
        fis.close();
        os.close();

        String outputString = "";
        for (int i=0; i < os.toByteArray().length; i++) {
            Byte currentByte = os.toByteArray()[i];
            int currentInt = currentByte.intValue();
            if (i % 22 == 1) {
                String zeroPaddedOffset = "";
                int offset = (i / 22) * 22;
                String offsetString = Integer.toHexString(offset);
                if (offsetString.length() > 5) {
                    System.out.println("Error: The file is too big to be completely parsed in the Hex Builder.");
                    break;
                } else {
                    int numberOfZeros = 5 - offsetString.length();
                    for (int j=0; j < numberOfZeros; j++) {
                        zeroPaddedOffset = zeroPaddedOffset + "0";
                    }
                    zeroPaddedOffset = zeroPaddedOffset + offsetString;
                }
                outputString = outputString + "\nOffset: " + zeroPaddedOffset + " Hex: " + Integer.toHexString(currentInt) + " ";
            } else {
                outputString = outputString + Integer.toHexString(currentInt) + " ";
            }
        }
        return outputString;
    }

}
