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
import java.util.Scanner;

/**
 *
 * @author Icad
 */

// Mengapa menggunakan final class?
/*
 * Karena class ini tidak akan mempunyai subclass. Pada konstruktor memiliki
 * permasalahan akan berpotensi teroverride jika tidak menggunakan final class.
 * 
 */
public final class files {

    // deklarasi
    private String filePath;
    private String fileName;
    private BufferedReader reader = null;

    // konstruktor
    // Membuat objek files baru
    // Parameter : String fileName
    // Berfungsi untuk melengkapi filePath
    public files(String fileName) throws FileNotFoundException {

        setFileName(fileName);
        // set filePath
        setPath(fileName);
        reader = new BufferedReader(new FileReader(filePath));
    }

    // setPath
    // Mengatur variabel filePath dengan menambahkan nama file ke dalam filepath
    // Parameter : String fileName
    // Berfungsi melengkapi variabel filepath untuk sampai ke
    // dalam sebuah spesifik path file
    // I.S : Filepath belum menjangkau file
    // F.S : Filepath sudah menjangkau file
    public void setPath(String fileName) {
        filePath = "F:\\Tugas\\Semester 2\\SDA\\Tugas Besar\\Treemap\\src\\treemap\\file\\" + fileName;
    }

    // setFileName
    // Mengubah value variabel fileName
    // Parameter : String fileName
    // I.S : sembarang fileName
    // F.S : value fileName sudah diset
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    // read()
    // Procedure untuk membaca file
    //
    public void read() throws IOException {

        // deklarasi variabel lokal
        String read;
        String delimiter = "\\s+|,\\s*|\\.\\s*";
        int j = 0;

        // membuat linked list
        LinkedList<LinkedList<String>> list = new LinkedList<>();

        // membaca file secara line by line
        while ((read = reader.readLine()) != null) {

            list.add(new LinkedList<String>());

            // memisahkan sebaris string menjadi kata per kata
            // memasukkan masing masing kata ke dalam array of string
            String[] splited = read.split(delimiter);

            for (int i = 0; i < splited.length; i++) {

                list.get(j).add(splited[i]);
            }

            j++;
        }
        printList(list);
        findReplace(list);

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

    public void findReplace(LinkedList l) {
        Scanner in = new Scanner(System.in);
        boolean found = false;        

        // membaca kata yang akan diganti
        System.out.print("\nMasukkan kata yang ingin diganti : ");
        String oldWord = in.nextLine();

        System.out.print("Masukkan kata pengganti : ");
        String newWord = in.nextLine();

        for (int i = 0; i < l.size(); i++) {

            LinkedList<String> sublists = (LinkedList<String>) l.get(i);
            for (int j = 0; j < sublists.size(); j++) {

                String temp = String.valueOf(sublists.get(j));

                if (temp.equalsIgnoreCase(oldWord)) {
                    sublists.set(j, newWord);
                    found = true;
                }
            }
        }

        if (found == false) {
            System.out.println("There is no word '" + oldWord + "' in this file");
        } else {
            printList(l);
        }

        in.close();
    }
}