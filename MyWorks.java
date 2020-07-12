/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myworks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Icad
 */
public class MyWorks {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException {
        
        //deklarasi
        Scanner in = new Scanner(System.in);
        System.out.print("input nama file: ");
        String fileName = in.nextLine();
        
        //gunakan IOException untuk mengantisipasi 
        /*  Reading a network file and got disconnected.
            Reading a local file that was no longer available.
            Using some stream to read data and some other process closed the stream.
            Trying to read/write a file but don't have permission.
            Trying to write to a file but disk space was no longer available.
        
            source : (https://stackoverflow.com/questions/13216148/java-what-throws-an-ioexception)
        */
        try {
            //membuat object files baru
            files myFile = new files(fileName);
            boolean found = false;
            //membaca kata yang akan diganti
            while(!found){
                String oldWord = in.next();
                
                //find and replace
                myFile.find(oldWord, found);
            }
            
            //melakukan print frekuensi kata yang muncul dalam file
            myFile.printFreq();
            
        } catch (FileNotFoundException ex) {
            
            //open file gagal
            Logger.getLogger(MyWorks.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
   
}

