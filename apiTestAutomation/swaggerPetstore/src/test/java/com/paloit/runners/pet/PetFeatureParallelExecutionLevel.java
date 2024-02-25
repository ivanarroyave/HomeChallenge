package com.paloit.runners.pet;


import io.cucumber.junit.CucumberSerenityRunner;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/pet"},
        glue = {"com.paloit.stepdefinitions.pet"}
)
public class PetFeatureParallelExecutionLevel {
}
