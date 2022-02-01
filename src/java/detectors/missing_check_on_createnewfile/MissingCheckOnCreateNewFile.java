package rules.missing_check_on_createnewfile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import lombok.extern.log4j.Log4j;

@Log4j
class MissingCheckOnCreateNewFile {

    // {fact rule=missing-check-on-createnewfile@v1.0 defects=1}
    public File createFileNonCompliant(File outputFolder, final String fileName) throws IOException {
        File file = new File(outputFolder, fileName);
        // Noncompliant: does not check if createNewFile succeeded or failed.
        file.createNewFile();
        return file;
    }
    // {/fact}

    // {fact rule=missing-check-on-createnewfile@v1.0 defects=0}
    public Optional<File> createFileCompliant(File outputFolder, final String fileName) throws IOException {
        File file = new File(outputFolder, fileName);
        // Compliant: handles the case when createNewFile fails.
        if (!file.createNewFile()) {
            log.debug("File already exists, using existing file " + file.getAbsolutePath() + ".");
            return Optional.empty();
        }
        return Optional.of(file);
    }
    // {/fact}
}
