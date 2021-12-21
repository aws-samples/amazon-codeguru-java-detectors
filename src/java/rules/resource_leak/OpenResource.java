/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.resource_leak;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class OpenResource {
    // {fact rule=resource-leak@v1.0 defects=1}
    public List<Path> streamFilesNonCompliant(final Path path) throws Exception {
        final List<Path> files;
        // Noncompliant: does not close the auto-closable streams of file system objects.
        Stream<Path> pathStream = Files.walk(path);
        files = pathStream.filter(p -> Files.isRegularFile(p))
                .map(path::relativize)
                .collect(Collectors.toList());
        log.info("Relativized files: {}", files);
        return files;
    }
    // {/fact}

    // {fact rule=resource-leak@v1.0 defects=0}
    public List<Path> streamFilesCompliant(final Path path) throws Exception {
        final List<Path> files;
        // Compliant: using try-with-resources block takes care of closing the auto-closable streams of file system objects.
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
