package mc.cas.center;


import mc.cas.center.conf.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Starter{
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationContext.class);
        applicationContext.start();
        System.out.println("mc.cas.center project start...");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("end"));
    }
}
