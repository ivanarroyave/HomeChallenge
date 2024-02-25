package com.paloit.runners.store;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/store"},
        glue = {"com.paloit.stepdefinitions.store"}
)
public class StoreFeatureParallelExecutionLevel {
}
