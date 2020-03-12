import java.text.*;
import java.io.*;
import java.util.*;

public class Arq {

    public static void main(String[] args) {

       try {
            
            RandomAccessFile arq = new RandomAccessFile("arq.txt", "rw");
            int n = MyIO.readInt();
            for (int i = 0; i < n; i++) {
                arq.writeDouble(MyIO.readDouble());
            }
	  
	    NumberFormat format = NumberFormat.getNumberInstance(Locale.ENGLISH);
	    format.setMinimumFractionDigits(0);
            arq.close();
            RandomAccessFile raf = new RandomAccessFile("arq.txt", "r");
            long seek = raf.length() - 8;
            for(long i = seek; i >= 0; i -= 8) {
		raf.seek(i);
		
                System.out.println(format.format(raf.readDouble()));
	
            }
            
        
            raf.close();

            

        } catch (Exception e) {
            MyIO.print(e.getMessage());
        }

        

    }


}
