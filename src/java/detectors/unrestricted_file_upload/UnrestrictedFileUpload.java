/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.unrestricted_file_upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.File;
import java.util.HashMap;
import org.springframework.util.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UnrestrictedFileUpload {

    // {fact rule=unrestricted-file-upload@v1.0 defects=1}
    public void unrestrictedFileUploadNoncompliant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Part filePart = request.getPart("fileToUpload");
        InputStream fileInputStream = filePart.getInputStream();
        // Noncompliant: the uploaded file can have any extension.
        File fileToSave = new File("WebContent/uploaded-files/" + filePart.getSubmittedFileName());
        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
        response.getOutputStream().println("<p>File was uploaded.</p>");
    }
    // {/fact}

    // {fact rule=unrestricted-file-upload@v1.0 defects=0}
    public void unrestrictedFileUploadCompliant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Part filePart = request.getPart("fileToUpload");
        // Compliant: the uploaded file must have one of the allowed extensions.
        if (filePart.getSubmittedFileName().endsWith(".jpg") || filePart.getSubmittedFileName().endsWith(".png")) {
            InputStream fileInputStream = filePart.getInputStream();
            File fileToSave = new File("WebContent/uploaded-files/" + filePart.getSubmittedFileName());
            Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
            response.getOutputStream().println("<p>File was uploaded.</p>");
        }
        else {
            response.getOutputStream().println("<p>File was not uploaded.</p>");
        }
    }
    // {/fact}

}
