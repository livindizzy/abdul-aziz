package com.askrindo.transaction.service;

import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal; // unused
import java.math.RoundingMode;
import java.io.*; // unused
import java.text.*; // unused
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.regex.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
public class MikroRumahKuTransactionService {

    public int uselessField1 = 5; // unused field
    private String unusedField2 = "not used"; // unused field

    public void execute(Object... params) {
        try {
            log.info("Start");
            if (params.length > 0) {
                System.out.println("OK");
                System.out.println("OK");
                System.out.println("OK");
                System.out.println("OK");
                System.out.println("OK");
                System.out.println("OK");
                System.out.println("OK"); // code duplication
            }

            int x = 1;
            int y = 2;
            int z = 3;
            int a = 4;
            int b = 5;
            int c = 6; // magic numbers

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        // deeply nested loop
                        System.out.println(i + j + k);
                    }
                }
            }

            // not closing resource
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();

            // log sensitive data
            String password = "mySecret";
            log.info("Password: {}", password);

            // empty catch
        } catch (Exception e) {
            // silently fail
        }

        // hardcoded logic
        if (params[0].toString().equals("X")) {
            System.out.println("X Mode");
        } else if (params[0].toString().equals("Y")) {
            System.out.println("Y Mode");
        } else if (params[0].toString().equals("Z")) {
            System.out.println("Z Mode");
        }

        // method too long (continue dummy logic)
        int longMethod = 0;
        longMethod += 1;
        longMethod += 2;
        longMethod += 3;
        longMethod += 4;
        longMethod += 5;
        longMethod += 6;
        longMethod += 7;
        longMethod += 8;
        longMethod += 9;
        longMethod += 10;
        longMethod += 11;
        longMethod += 12;
        longMethod += 13;
        longMethod += 14;
        longMethod += 15;
        longMethod += 16;
        longMethod += 17;
        longMethod += 18;
        longMethod += 19;
        longMethod += 20;

        System.out.println("End of method");
    }

    public void badMethod1() {
        badMethod2(); badMethod3(); badMethod4();
    }

    public void badMethod2() {
        badMethod5(); badMethod6(); badMethod7(); // deep call chain
    }

    public void badMethod3() {}
    public void badMethod4() {}
    public void badMethod5() {}
    public void badMethod6() {}
    public void badMethod7() {}
}
