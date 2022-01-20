/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.finalize_on_super_class;

import java.io.BufferedReader;
import java.io.IOException;

// {fact rule=finalize-on-super-class@v1.0 defects=0}
public class FinalizeOnSuperClassCompliant {
    protected abstract class FeedParser {
        @Override
        protected void finalize() throws IOException {
            System.out.println("finalize-class");
        }
    }

    protected abstract class ETLFeedParserCompliant extends FeedParser {
        private BufferedReader feedReader;
        @Override
        // Compliant: calls super.finalize() explicitly.
        protected void finalize() throws IOException {
            try {
                feedReader.close();
            }
            finally {
                super.finalize();
            }
        }
    }
}
// {/fact}
