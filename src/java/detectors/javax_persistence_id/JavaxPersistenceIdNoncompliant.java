/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.javax_persistence_id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// {fact rule=javax-persistence-id@v1.0 defects=1}
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JavaxPersistenceIdNoncompliant {

    // Noncompliant: attaches an auto-incremented ID to 32-bit data
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_sla_id", nullable = false)
    private Integer id;

    @Column(name = "type_id", nullable = false)
    private Integer typeId;

    @Column(name = "type", nullable = false, length = 255)
    private String type;
}
// {/fact}
