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
import java.util.LinkedList;

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
        int j =0;

        //membuat linked list
        LinkedList<LinkedList<String>> list = new LinkedList<>();
        //LinkedList<String> sublists;
        //membaca file secara line by line
        while ((read = reader.readLine()) != null) {
            //sublists = new LinkedList<>();
            list.add(new LinkedList<String>());
            //memisahkan sebaris string menjadi kata per kata
            //memasukkan masing masing kata ke dalam array string
            String[] splited = read.split("\\s+");
            for (int i = 0; i < splited.length; i++) {
                //sublists.add(splited[i]);
//                list.add(splited[i]);
                list.get(j).add(splited[i]);
            }
            //list.add(sublists);
            //memasukkan kata dalam array string ke dalam TreeMap
            j++;
        }
        printList(list);
        //menampilkan TreeMap ke layar (key, value)
        //word.entrySet().forEach((words) -> {
          //  System.out.println(words.getKey() + " = " + words.getValue());
        //});
        reader.close();
    }
    
    
    public void printList(LinkedList l) {
        for (int i = 0; i < l.size(); i++) {
            
            LinkedList<String> get = (LinkedList<String>) l.get(i);
            for (int j = 0; j < get.size(); j++) {
                
                System.out.print(get.get(j) + " ");
            }
            System.out.println();
        }
    }

    
    public void replace(String oldWord, String newWord, LinkedList l) {
        boolean find = false;

        for (int i = 0; i < l.size(); i++) {

            LinkedList<String> sublists = (LinkedList<String>) l.get(i);
            for (int j = 0; j < sublists.size(); j++) {

                String temp = String.valueOf(sublists.get(j));

                if (temp.equalsIgnoreCase(oldWord)) {
                    sublists.set(j, newWord);
                    find = true;
                }
            }
        }

        if (find == false) {
            System.out.println("There is no word " + oldWord + " in this file");
        }
    }

    public void find(String word, boolean found){
        found = true;
    }
}