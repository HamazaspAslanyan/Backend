package com.example.backend.services;
import java.io.File;

public class Scanner {


    public static void main( String [] args ) {
//        File actual = new File(".");
//        File downloadsFolder = new File("https://www.aripari.am/music");
        File downloadsFolder = new File("C:\\Users\\HamazaspAslanyan\\Downloads");
        for( File f : downloadsFolder.listFiles()){
            System.out.println( f.getName() );
        }
    }

}
