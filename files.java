
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

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
    private String fileName;

    // konstruktor
    // Membuat objek files baru
    // Parameter : String fileName
    // Berfungsi untuk melengkapi filePath 
    public files(String fileName) throws FileNotFoundException {

        //set fileName dari input user
        setFileName(fileName);

        //set filePath
        setPath(fileName);

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
    public void setFileName(String fileName) {

        this.fileName = fileName;
    }

    // read()
    // Procedure untuk membaca file 
    //
    public void read() throws IOException {

        //deklarasi variabel lokal
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String read;
        String delimiter = "\\s+|,\\s*|\\.\\s*";
        int j = 0;
        
        //membuat linked list
        LinkedList<LinkedList<String>> list = new LinkedList<>();

        //membaca file secara line by line
        while ((read = reader.readLine()) != null) {

            list.add(new LinkedList<String>());

            //memisahkan sebaris string menjadi kata per kata
            //memasukkan masing masing kata ke dalam array string
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

    // printList()
    // Procedure untuk menampilkan list (isi file) ke layar
    //
    public void printList(LinkedList list) {

        System.out.println("\nIsi File :");

        for (int i = 0; i < list.size(); i++) {
            // Memisahkan isi file per satu baris
            LinkedList<String> sublists = (LinkedList<String>) list.get(i);

            // Memisahkan sebaris string menjadi kata per kata
            for (int j = 0; j < sublists.size(); j++) {
                System.out.print(sublists.get(j) + " ");
            }
            System.out.println();
        }
    }

    // findReplace()
    // Procedure untuk mencari kata yang ingin diganti dan mengganti kata tersebut dengan kata baru
    //
    public void findReplace(LinkedList list) throws IOException {

        try (Scanner in = new Scanner(System.in)) {
            boolean found = false;
            
            // membaca kata yang akan di replace
            System.out.print("\nMasukkan kata yang akan diganti : ");
            String oldWord = in.nextLine();
            
            System.out.print("Masukkan kata pengganti : ");
            String newWord = in.nextLine();
            
            for (int i = 0; i < list.size(); i++) {
                // Memisahkan isi file per satu baris
                LinkedList<String> sublists = (LinkedList<String>) list.get(i);
                
                for (int j = 0; j < sublists.size(); j++) {
                    // Memisahkan sebaris string menjadi kata per kata
                    String temp = sublists.get(j);
                    
                    // Membandingkan kata yang dicari dengan kata yang terdapat di dalam list
                    if (temp.equalsIgnoreCase(oldWord)) {
                        sublists.set(j, newWord);
                        found = true;
                    }
                }
            }
            
            if (found == false) {
                System.out.println("Tidak ada kata '" + oldWord + "' di dalam file.");
            } else {
                printList(list);
                printToFile(list);
            }
        }
    }

    // printToFile()
    // Prosedur untuk menulis list ke dalam file
    //
    public void printToFile(LinkedList list) throws IOException {
        setFileName("new" + fileName);
        setPath(fileName);
        try (FileWriter filewriter = new FileWriter(filePath)) {
            for (int i = 0; i < list.size(); i++) {
                // Memisahkan isi file per satu baris
                LinkedList<String> sublists = (LinkedList<String>) list.get(i);
                
                // Memisahkan sebaris string menjadi kata per kata
                for (int j = 0; j < sublists.size(); j++) {
                    
                    // Menulis isi list ke dalam file
                    filewriter.write(sublists.get(j) + " ");
                }
                filewriter.write("\n");
            }
        }
    }
}
