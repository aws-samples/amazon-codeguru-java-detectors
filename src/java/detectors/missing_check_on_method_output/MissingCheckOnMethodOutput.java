package rules.missing_check_on_method_output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.log4j.Log4j;

@Log4j
class MissingCheckOnMethodOutput {

    // {fact rule=missing-check-on-method-output@v1.0 defects=1}
    private void writeMessageNonCompliant(String dirName, String fileName, String message) {
        try {
            File dir = new File(dirName);
            if (!dir.exists()) {
                // Noncompliant: code does not handle the case when mkdirs fails.
                dir.mkdirs();
            }
            try (FileOutputStream fos = new FileOutputStream(new File(dir, fileName))) {
                fos.write(message.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // {/fact}

    // {fact rule=missing-check-on-method-output@v1.0 defects=0}
    private void writeMessageCompliant(String dirName, String fileName, String message) {
        try {
            File dir = new File(dirName);
            boolean ok = true;
            if (!dir.exists()) {
                // Compliant: code handles the case when mkdirs fails.
                ok = dir.mkdirs();
            }
            if (ok) {
                try (FileOutputStream fos = new FileOutputStream(new File(dir, fileName))) {
                    fos.write(message.getBytes());
                }
            } else {
                log.warn("output directory not created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // {/fact}
}
