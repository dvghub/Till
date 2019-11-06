package nu.educom.warehouse.till;

import nu.educom.warehouse.till.display.ConsoleTillDisplay;
import nu.educom.warehouse.till.products.ProductCatalog;
import nu.educom.warehouse.till.till.ITill;
import nu.educom.warehouse.till.till.Till;

class Main {

    public static void main(String[] args) {
        // Make a new 'till' based on the ITill interface
	    ITill till = new Till(new ProductCatalog());

	    // Construct a console interaction file
        var consoleInteraction = new ConsoleTillDisplay(till);

        // Start the main loop
        consoleInteraction.run();
    }
}
