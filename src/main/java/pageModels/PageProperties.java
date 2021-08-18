package pageModels;

public enum PageProperties {

    BLOUSE("Blouse"),
    REFERENCE("Model demo_2"),
    CONDITION("Condition New"),
    DESCRIPTION("Short sleeved blouse with feminine draped sleeve detail."),
    BLOUSE_LINK("http://automationpractice.com/index.php?id_product=2&controller=product"),
    PAGE_PRODUCT_REFERENCE("product_reference"),
    PAGE_PRODUCT_CONDITION("product_condition"),
    PAGE_PRODUCT_DESCRIPTION("short_description_block"),
    TAG_H1("H1");


    private final String reference;


    PageProperties (String inputtedText) {
        this.reference = inputtedText;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return reference;
    }
}
