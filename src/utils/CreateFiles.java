package utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CreateFiles {
	
	 public void createFile(Path path , List<Float> tab) {
	        String s = "";
	        for (int i = 0; i < tab.size() ; i++) {
	            s = s + tab.get(i) + "\n";
	        }
	        byte[] inputBytes = s.getBytes();
	        ByteBuffer writeBuffer = ByteBuffer.wrap(inputBytes);
	        FileChannel writeChannel;
	        try {
	            writeChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
	            writeChannel.write(writeBuffer);
	            //int noOfBytesWritten = writeChannel.write(writeBuffer);
	 
	            writeChannel.close();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	 
	    }
	 
	 

}
