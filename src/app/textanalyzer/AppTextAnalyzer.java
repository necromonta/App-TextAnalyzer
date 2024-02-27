/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app.textanalyzer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anagy
 */
public class AppTextAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
       //Davon,Etnevel,Necro
        FileReader fr = new FileReader("text.txt");
        Scanner sc = new Scanner(fr);

        ArrayList<String> words = new ArrayList();
        ArrayList<String> text = new ArrayList();
        ArrayList<Data> uniqueWords = new ArrayList();
        boolean unique;
        int sentences = 0;
        int commas = 0;
        int letters=0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] array = line.split(" ");
            for (int i = 0; i < array.length; i++) {
                unique = true;
                text.add(array[i]);
                //sentence
                if (array[i].contains(".")) {
                    sentences++;
                    array[i] = array[i].replace(".", "");
                }

                //tag mondat idk xd
                if (array[i].contains(",")) {
                    commas++;
                    array[i] = array[i].replace(",", "");
                }
                words.add(array[i]);
                letters+=array[i].length();

                //unique words
                if (i != 0) {

                    for (int j = 0; j < uniqueWords.size(); j++) {
                        if (uniqueWords.get(j).word.equals(array[i])) {
                            uniqueWords.get(j).bit++;
                            unique = false;

                        }
                    }
                    if (unique) {
                        uniqueWords.add(new Data(1, array[i]));

                    }
                }

            }
        }

        
        //Most used
        Data max = uniqueWords.get(0);
        for (int i = 1; i < uniqueWords.size(); i++) {
            if (uniqueWords.get(i).bit > max.bit) {
                max = uniqueWords.get(i);
            }
        }

        System.out.println("Number of letters: "+letters);
        System.out.println("Number of words: " + words.size());
        System.out.println("Number of sentences: " + sentences);
        System.out.println("Number of commas: " + commas);
        System.out.println("Number of unique words: " + uniqueWords.size());
        bubbleSort(uniqueWords);
        
    }
    
    static void bubbleSort(ArrayList<Data> arr) {  
        int n = arr.size();  
        Data temp =new Data(); 
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(arr.get(j-1).bit > arr.get(j).bit){  
                                 //swap elements  
                                 temp.bit = arr.get(j-1).bit;  
                                 temp.word = arr.get(j-1).word;  
                                 arr.get(j-1).bit = arr.get(j).bit;  
                                 arr.get(j-1).word = arr.get(j).word; 
                                 arr.get(j).bit = temp.bit;  
                                 arr.get(j).word = temp.word;  
                         }  
                          
                 }  
         }  
         //arr.size()-numberOfStuff
        for (int i = arr.size()-1;i>=0;i--) {
           System.out.println(i+". Most used word: \"" + arr.get(i).word + "\" " + arr.get(i).bit + " times");
        }
    }  

}
