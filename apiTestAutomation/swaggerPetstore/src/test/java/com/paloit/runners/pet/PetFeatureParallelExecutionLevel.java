package com.paloit.runners.pet;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/pet.feature"},
        glue = {"com.paloit.stepdefinitions.pet"},
        tags = ""
)
public class PetFeatureParallelExecutionLevel {
}
