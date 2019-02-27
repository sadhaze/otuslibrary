package otus.library;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(LibraryApplication.class, args);

        Console.main(args);
    }

}
