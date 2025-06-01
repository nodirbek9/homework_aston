package ru.aston.homework.Exception;

public class Main {
    public static void main(String[] args) {

        FileHandler handler = new FileHandler();
        String path = "c:/Users/ayitb/Downloads/Programs/reader.txt";

        try {
            handler.writeToFile(path, "Hello world!");
            String content = handler.readFromFile(path);
            System.out.println("Содержимое файла:\n" + content);
        } catch (MyFileException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
