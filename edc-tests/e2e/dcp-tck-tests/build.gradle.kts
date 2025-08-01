/********************************************************************************
 * Copyright (c) 2025 Cofinity-X GmbH
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/

plugins {
    java
}

dependencies {

    constraints {
        // netty's mockserver depends on an older version of the json schema validator, but TCK needs this:
        implementation("com.networknt:json-schema-validator:1.5.8") {
            because("This version is required by the TCK")
        }
    }

    testImplementation(testFixtures(project(":edc-tests:e2e-fixtures")))
    testRuntimeOnly(libs.dcp.testcases)
    testImplementation(libs.edc.junit)
    testImplementation(libs.edc.spi.identity.trust )
    testImplementation(libs.edc.spi.identity.did )
    testImplementation(libs.nimbus.jwt)
    testImplementation(libs.restAssured)
    testImplementation(libs.assertj)
    testImplementation(libs.junit.jupiter.api)

    testImplementation(libs.dcp.tck.runtime)
    testImplementation(libs.dcp.system)
    testImplementation(libs.dsp.core)
    testImplementation(libs.mockserver.netty)
    testImplementation(libs.junit.platform.launcher)
    testImplementation(libs.testcontainers.junit)
}

edcBuild {
    publish.set(false)
}
