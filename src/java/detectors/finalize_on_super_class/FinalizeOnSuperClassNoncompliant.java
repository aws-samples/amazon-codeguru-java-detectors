/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.finalize_on_super_class;

import java.io.BufferedReader;
import java.io.IOException;

// {fact rule=finalize-on-super-class@v1.0 defects=1}
public class FinalizeOnSuperClassNoncompliant {
    protected abstract class FeedParser {
        @Override
        protected abstract void finalize() throws IOException;
    }

    protected abstract class ETLFeedParser extends FeedParser {
        private BufferedReader feedReader;
        // Noncompliant: does not call super.finalize().
        @Override
        protected void finalize() throws IOException {
            feedReader.close();
        }
    }
}
// {/fact}
