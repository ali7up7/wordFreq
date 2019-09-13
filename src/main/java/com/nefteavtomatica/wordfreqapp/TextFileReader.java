package com.nefteavtomatica.wordfreqapp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.*;

import javax.lang.model.type.UnknownTypeException;

public class TextFileReader {
        private String fileName;
        private File file;
        protected List<String> wordsList;

        public TextFileReader(String fileName) {
            this.fileName = fileName;
        } 

        public File getTxtFile() throws Exception{        
            if (extensionIsTxt())  {
            } else
                throw new UnknownTypeException(null, fileName);
            readFile(fileName);         
            return file;
            } 

        private void readFile(String fileName) throws NoSuchFileException {
            try {
              File possiblyFile = new File(fileName);
              if (possiblyFile.exists()) file = possiblyFile;
              else throw new NoSuchFileException(fileName); 
            } catch (UnknownTypeException e) {
                System.out.println("unknown file type");
                e.printStackTrace(); 
            }          
        }    
   
        boolean extensionIsTxt() throws IOException {
            Optional<String> maybeExtension = getExtension();
            String extension = maybeExtension.orElseThrow(IOException::new);
                if ("txt".equals(extension)) 
                 return true;
            return false;           
        }
    
        private Optional<String> getExtension() {
            return Optional.ofNullable(fileName)
                 .filter(f -> f.contains("."))
                 .map(f -> f.substring(fileName.lastIndexOf(".") + 1));
        }

        public void parseToList() throws FileNotFoundException {
            Scanner jogger = new Scanner(file);
            wordsList = new ArrayList<String>();
            while (jogger.hasNext())
                wordsList.add(jogger.next());
            jogger.close();
            }
    }