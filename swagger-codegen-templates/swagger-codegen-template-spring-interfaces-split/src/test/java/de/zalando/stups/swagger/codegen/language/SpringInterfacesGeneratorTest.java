/**
 * Copyright (C) 2015 Zalando SE (http://tech.zalando.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.zalando.stups.swagger.codegen.language;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.zalando.stups.swagger.codegen.CodegenerationException;
import org.zalando.stups.swagger.codegen.StandaloneCodegenerator;

public class SpringInterfacesGeneratorTest {
    @Test
    public void testGenerationFromJsonResponseEntityNoAnnotations() throws CodegenerationException {
        StandaloneCodegenerator generator = StandaloneCodegenerator.builder().withApiFile(getApiJsonFile())
                                                                   .forLanguage(
                                                                       "springinterfacesSplitResponseEntityNoSwaggerAnnotations")
                                                                   .withApiPackage("de.zalando.swagger.api")
                                                                   .withModelPackage("de.zalando.swagger.model")
                                                                   .writeResultsTo(generateOutputDir()).build();

        generator.generate();
    }

    @Test
    public void testGenerationFromYamlRepositoryEntityNoSwaggerAnnotations() throws CodegenerationException {
        StandaloneCodegenerator generator = StandaloneCodegenerator.builder().withApiFile(getApiYamlFile())
                                                                   .forLanguage(
                                                                       "springinterfacesSplitResponseEntityNoSwaggerAnnotations")
                                                                   .withApiPackage("de.zalando.swagger.api")
                                                                   .withModelPackage("de.zalando.swagger.model")
                                                                   .writeResultsTo(generateOutputDir()).enable303(true)
                                                                   .build();

        generator.generate();
    }

    @Test
    public void testGenerationFromYamlRepositoryEntityNoSwaggerAnnotationsWithBuilder() throws CodegenerationException {
        StandaloneCodegenerator generator = StandaloneCodegenerator.builder().withApiFile(getApiYamlFile())
                                                                   .forLanguage(
                                                                       "springinterfacesSplitResponseEntityNoSwaggerAnnotations")
                                                                   .withApiPackage("de.zalando.swagger.api")
                                                                   .withModelPackage("de.zalando.swagger.model")
                                                                   .writeResultsTo(generateOutputDir()).enable303(true)
                                                                   .enableBuilderSupport(true).build();

        generator.generate();
    }

    @Test
    public void testGenerationFromYamlSkipApiGeneration() throws CodegenerationException {
        StandaloneCodegenerator generator = StandaloneCodegenerator.builder().withApiFile(getApiYamlFile())
                                                                   .forLanguage(
                                                                       "springinterfacesSplitResponseEntityNoSwaggerAnnotations")
                                                                   .withApiPackage(null)
                                                                   .skipApigeneration(true)
                                                                   .withModelPackage("de.zalando.swagger.model")
                                                                   .writeResultsTo(generateOutputDir()).enable303(true)
                                                                   .enableBuilderSupport(true).build();

        generator.generate();
    }
    // @formatter:on

    protected File getFormUploadYamlFile() {
        return new File(getClass().getResource("/formUpload.yaml").getFile());
    }

    protected File getApiJsonFile() {
        return new File(getClass().getResource("/petstore.json").getFile());
    }

    protected File getApiYamlFile() {
        return new File(getClass().getResource("/vacations.yaml").getFile());
    }

    protected File generateOutputDir() {
        File userDir = new File(System.getProperty("user.dir"));
        File outputDirectory = new File(userDir, "/target/" + UUID.randomUUID().toString());
        if (!outputDirectory.mkdirs()) {
            System.out.println("NOT_CREATED at " + outputDirectory.getAbsolutePath());
        }

        System.out.println("OUTPUT TO : " + outputDirectory.getAbsolutePath());
        return outputDirectory;
    }
}
