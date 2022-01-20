/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.json_parser_length;

import hivemall.utils.io.FastByteArrayInputStream;
import org.apache.hadoop.io.Text;
import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

public class JsonParserLength {

  // {fact rule=json-parser-length@v1.0 defects=1}
    public void createJsonParserNoncompliant(Text text) throws IOException {
        // Noncompliant: length of input byte array not specified.
        new JsonFactory().createJsonParser(new FastByteArrayInputStream(text.getBytes()));
    }
    // {/fact}

    // {fact rule=json-parser-length@v1.0 defects=0}
    public void createJsonParserCompliant(Text text) throws IOException {
        // Compliant: length of input byte array specified.
        new JsonFactory().createJsonParser(new FastByteArrayInputStream(text.getBytes(), text.getLength()));
    }
    // {/fact}
}
