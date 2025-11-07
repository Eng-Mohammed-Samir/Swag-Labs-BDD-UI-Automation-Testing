package steps;

import context.ScenarioContext;

public class ProductDetails_steps {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public ProductDetails_steps(ScenarioContext context) {
        this.scenarioContext = context;
    }
}
