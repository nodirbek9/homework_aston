package ru.aston.homework.Exception;

import java.io.*;

public class FileHandler {

    public void writeToFile(String filePath, String data) throws MyFileException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
        } catch (IOException e) {
            throw new MyFileException("Ошибка при записи в файл", e);
        }
    }

    public String readFromFile(String filePath) throws MyFileException{
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
                String line;
                while((line = reader.readLine()) != null){
                    result.append(line).append("\n");
                }
            }catch (IOException e) {
            throw new MyFileException("Ошибка при чтении из файла", e);

        }
        return result.toString();
    }

    }
