/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.resource_leak;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ResourceLeak {
    // {fact rule=resource-leak@v1.0 defects=1}
    public List<Path> autoCloseableStreamNoncompliant(final Path path) throws Exception {
        final List<Path> files;
        // Noncompliant: does not close the auto-closeable streams of file system objects.
        Stream<Path> pathStream = Files.walk(path);
        files = pathStream.filter(p -> Files.isRegularFile(p))
                .map(path::relativize)
                .collect(Collectors.toList());
        log.info("Relativized files: {}", files);
        return files;
    }
    // {/fact}

    // {fact rule=resource-leak@v1.0 defects=0}
    public List<Path> autoCloseableStreamCompliant(final Path path) throws Exception {
        final List<Path> files;
        // Compliant: the try-with-resources block auto-closes any auto-closeable streams when done.
        try (Stream<Path> pathStream = Files.walk(path)) {
            files = pathStream.filter(p -> Files.isRegularFile(p))
                    .map(path::relativize)
                    .collect(Collectors.toList());
        }
        log.info("Relativized files: {}", files);
        return files;
    }
    // {/fact}
}
