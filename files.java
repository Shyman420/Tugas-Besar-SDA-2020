/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myworks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 *
 * @author Icad
 */


//Mengapa menggunakan final class?
/*Karena class ini tidak akan mempunyai subclass. Pada konstruktor
memiliki permasalahan akan berpotensi teroverride jika tidak menggunakan
final class.*/
public final class files {
    
    //deklarasi
    private String filePath;
    private BufferedReader reader = null;
    
    //konstruktor
    //Membuat objek files baru
    //Parameter :   String fileName
    //              Berfungsi untuk melengkapi filePath 
    public files(String fileName) throws FileNotFoundException{
        
        //set filePath
        setPath(fileName);
        reader = new BufferedReader(new FileReader(filePath));
    }
    
    //setPath
    //Mengatur variabel filePath dengan menambahkan nama file ke dalam filepath
    //Parameter :   String fileName
    //              Berfungsi melengkapi variabel filepath untuk sampai ke 
    //              dalam sebuah spesifik path file
    //I.S       :   Filepath belum menjangkau file
    //F.S       :   Filepath sudah menjangkau file
    public void setPath(String fileName){
         filePath = "F:\\Tugas\\Semester 2\\SDA\\Tugas Besar\\Treemap\\src\\treemap\\file\\" + fileName;
    }
    
    //printFreq()
    //
    //
    public void printFreq() throws IOException {
        
        //deklarasi variabel lokal
        String read;
        String delimiter = "\\s+|,\\s*|\\.\\s*"; 
        
        //membuat TreeMap
        TreeMap<String, Integer> word = new TreeMap<String, Integer>();
        
        //membaca file secara line by line
        while ((read = reader.readLine()) != null) {
            
            //memisahkan sebaris string menjadi kata per kata
            //memasukkan masing masing kata ke dalam array string
            String[] splited = read.toLowerCase().split(delimiter);
            
            //memasukkan kata dalam array string ke dalam TreeMap
            for (int i = 0; i < splited.length; i++) {
                
                Integer frequent = word.get(splited[i]);
                
                //memeriksa agar masing masing key dalam TreeMap tidak sama
                //jika key yang diperiksa belum terdapat dalam TreeMap, key tersebut akan disimpan dalam tree dan valuenya akan diset menjadi 1
                //jika terdapat key yang sama, value dari key tersebut akan bertambah 1
                if (word.get(splited[i]) == null) {
                    
                    word.put(splited[i], 1);
 
                } else {
                    
                    word.put(splited[i], ++frequent);
                }              
            }
        }

        //menampilkan TreeMap ke layar (key, value)
        word.entrySet().forEach((words) -> {
            System.out.println(words.getKey() + " = " + words.getValue());
        });
        reader.close();
    }
}