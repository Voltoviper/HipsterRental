package wak;

import java.io.File;

/**
 * Created by chris_000 on 21.10.2015.
 */
public class main {

    public static void main(String[] args) {

        File directory = new File("web/img/produkte/main.jpg");
        System.out.println(directory.getParentFile().mkdirs());




    }
}
