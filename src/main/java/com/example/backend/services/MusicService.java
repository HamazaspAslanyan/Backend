package com.example.backend.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.mpatric.mp3agic.*;

public class MusicService {

    public void changeFileCover(String mp3FilePath, String coverPhotoPath){
        try {
            // Load the MP3 file
            Mp3File mp3file = new Mp3File(mp3FilePath);

            // Check if the file has ID3v2 tag
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();

                // Load the cover photo image
                File imageFile = new File(coverPhotoPath);
                byte[] imageData = loadFile(imageFile);

                // Set the cover photo
                id3v2Tag.setAlbumImage(imageData, "image/jpeg");

                // Save changes to the MP3 file
                mp3file.save(mp3FilePath);
            } else {
                System.out.println("No ID3v2 tag found in the MP3 file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // Create a byte array to store the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
