package Utility;

import model.Book.Book;
import model.Book.Novel;
import model.Book.ScienceFiction;
import model.Book.StudyBook;
import model.Library;
import model.Loadable;
import model.Person.User;
import model.Saveable;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static Utility.JOptionPane.confirmWindow;
import static Utility.JOptionPane.warningWindow;

public class LoadAndSave implements Loadable, Saveable {
    private List<String> lines;
    @Override
    public void load(File inputFile, File inputFile2, File inputFile3, Library library) {
            try {
                ArrayList<Integer> lineNumber = new ArrayList<>();
                this.lines = Files.readAllLines(inputFile.toPath());
                int count = 0;
                for(String line: lines) {
                    switch (line) {
                        case "*studyBook:":
                            lineNumber.add(count);
                            break;
                        case "*novel:":
                            lineNumber.add(count);
                            break;
                        case "*scienceFiction:":
                            lineNumber.add(count);
                            break;
                    }
                    count++;
                }
                lineNumber.add(count);

                int countNumber = 0;
                for(String line: lines) {
                    ArrayList<String> eachLine = splitOnSpace(line);
                    Book newBook;

                    if (!(line.equals("*studyBook:")) && countNumber  > lineNumber.get(0) && countNumber  < lineNumber.get(1)) {
                        if (eachLine.get(1).charAt(1) == 'A') {
                            newBook = new StudyBook(eachLine.get(0),true);
                        }
                        else {
                            newBook = new StudyBook(eachLine.get(0),false);
                            String username = eachLine.get(2);
                            newBook.setHolder(username);
                        }
                        library.getBookList().add(newBook);
                        library.getRecordBookList().add(newBook);
                        library.getCategoryBookList().get("studyBook").add(newBook);
                    }
                    if (!(line.equals("*studyBook:")) && countNumber  > lineNumber.get(1) && countNumber  < lineNumber.get(2)) {
                        if (eachLine.get(1).charAt(1) == 'A') {
                            newBook = new Novel(eachLine.get(0),true);
                        }
                        else {
                            newBook = new Novel(eachLine.get(0),false);
                            String username = eachLine.get(2);
                            newBook.setHolder(username);
                        }
                        library.getBookList().add(newBook);
                        library.getRecordBookList().add(newBook);
                        library.getCategoryBookList().get("novel").add(newBook);

                    }
                    if (!(line.equals("*studyBook:")) && countNumber  > lineNumber.get(2) && countNumber  < lineNumber.get(3)) {
                        if (eachLine.get(1).charAt(1) == 'A') {
                            newBook = new ScienceFiction(eachLine.get(0),true);
                        }
                        else {
                            newBook = new ScienceFiction(eachLine.get(0),false);
                            String username = eachLine.get(2);
                            newBook.setHolder(username);
                        }
                        library.getBookList().add(newBook);
                        library.getRecordBookList().add(newBook);
                        library.getCategoryBookList().get("scienceFiction").add(newBook);

                    }
                    countNumber ++;
                }
            } catch (IOException e) {
                warningWindow("We cannot find the file. " +
                        "\nSo, we assume you don't want to load");
            }

            try {
                this.lines = Files.readAllLines(inputFile2.toPath());

                for(String line: lines) {
                    ArrayList<String> eachLine = splitOnSpace(line);
                    String userName = eachLine.get(0);
                    String password = eachLine.get(1);
                    User user = new User(userName, password,library, library.getLibraryLog());
                    String bookName;

                    if (eachLine.size() == 3) {
                        if (!(eachLine.get(2).length() == 2)) {
                            int bookNameFirst = eachLine.get(2).indexOf('[');
                            int bookNameLast = eachLine.get(2).indexOf(']');
                            bookName = eachLine.get(2).substring(bookNameFirst+1,bookNameLast);
                            for (Book book: library.getBookList()){
                                if (book.getBookName().equals(bookName)){user.getPersonalBookList().add(book);}
                            }
                        }
                    }
                    else if (eachLine.size() > 3) {
                        for (int i =2; i < eachLine.size(); i++) {
                            if (i == 2) {
                                int bookNameFirst = eachLine.get(i).indexOf('[');
                                int bookNameLast = eachLine.get(i).indexOf(',');
                                bookName = eachLine.get(i).substring(bookNameFirst+1,bookNameLast);
                            }
                            else if (i == eachLine.size()-1) {
                                int bookNameFirst = 0;
                                int bookNameLast = eachLine.get(i).indexOf(']');
                                bookName = eachLine.get(i).substring(bookNameFirst,bookNameLast);
                            }
                            else {
                                int bookNameFirst = 0;
                                int bookNameLast = eachLine.get(i).indexOf(',');
                                bookName = eachLine.get(i).substring(bookNameFirst,bookNameLast);
                            }

                            for (Book book: library.getBookList()){
                                if (book.getBookName().equals(bookName)){user.getPersonalBookList().add(book);}
                            }
                        }
                    }

                    library.getDatabase().getUserBookList().put(user, user.getPersonalBookList());
                }

                this.lines = Files.readAllLines(inputFile3.toPath());
                for(String line: lines) {
                    library.getLibraryLog().record(line);
                }
            }
            catch (IOException e) {
                warningWindow("We cannot find the file. " +
                        "\nSo, we assume you don't want to load");
            }
    }

    @Override
    public void save(String outputFile, Library library) {
        PrintWriter writer = null;
        PrintWriter writer1 = null;
        PrintWriter writer2 = null;
            try {
                writer = new PrintWriter(outputFile, "UTF-8");
                writer.println("Book:");
                String categoryName;

                for (int i=0;i<library.getCategoryName().size(); i++) {
                    categoryName = library.getCategoryName().get(i);
                    writer.println("*" + categoryName +":") ;
                    for (Book book: library.getCategoryBookList().get(categoryName)) {
                        if (book.checkAvailability()) {
                            writer.println(book.getBookName() + " (Available)");
                        }
                        else writer.println(book.getBookName() + " (Unavailable, " + book.getHolder() +" is using the book)");
                    }
                }

                outputFile = "UsersBookList.txt";
                writer1 = new PrintWriter(outputFile, "UTF-8");
                Set<User> userList;
                userList = library.getDatabase().getUserBookList().keySet();
                for (User user: userList) {
                    writer1.print(user + " " + user.getPassword() + " ");
                    writer1.println(user.getPersonalBookList());
                }

                outputFile = "LibaryLog.txt";
                writer2 = new PrintWriter(outputFile, "UTF-8");
                writer2.println(library.getLibraryLog().getRecordString());


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } finally {
                writer.close();
                writer1.close();
                writer2.close();
            }
    }
    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
