package Utility;

import model.Book.Book;
import model.Book.Novel;
import model.Book.ScienceFiction;
import model.Book.StudyBook;
import model.Library;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Data {

    private Library library;
    public Data(Library library) throws IOException {
        this.library = library;
        getData();
    }

    private void getData() throws IOException {
        String apikey = "39ede6a09bc76ede1a72f2c5a6b9c8e1c209fb0e97d8295251bd6d5766ee2099"; //fill this in with the API key they email you
        String UBCLibraryURL = "https://oc2-index.library.ubc.ca/search/6.2.3?q=books&from=0&size=50&index=oc&source=creator,title,type";
        String theURL;


        BufferedReader br = null;

        try {
            theURL = UBCLibraryURL + apikey; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            String result = new String();

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JSONObject jsStr = JSONObject.fromObject(result);
            for(int i = 0; i<50; i++) {
                addBooks(((JSONObject)((JSONArray)((JSONObject)jsStr.get("data")).getJSONObject("hits").get("hits")).getJSONObject(i).get("_source")).get("title").toString());
            }

        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    private void addBooks(String bookName) {
        Book newBook;
        bookName = bookName.substring(2,bookName.length()-2);
        System.out.println(bookName);
        int randomNum = (int)(Math.random() * 3);
        if (randomNum == 0) {
            newBook = new StudyBook(bookName,true);
            library.getBookList().add(newBook);
            library.getRecordBookList().add(newBook);
            library.getCategoryBookList().get("studyBook").add(newBook);
        }
        else if (randomNum == 1){
            newBook = new Novel(bookName,true);
            library.getBookList().add(newBook);
            library.getRecordBookList().add(newBook);
            library.getCategoryBookList().get("novel").add(newBook);
        }
        else if (randomNum == 2) {
            newBook = new ScienceFiction(bookName,true);
            library.getBookList().add(newBook);
            library.getRecordBookList().add(newBook);
            library.getCategoryBookList().get("scienceFiction").add(newBook);
        }
    }


}
