package pl.jdomanski.k47.util;

public final class Mappings {
    
    // == CONSTANTS ==
    // -- categories mappings --
    private static final String CATEGORY_ROOT = "categories";
    public static final String CATEGORIES_LIST  = CATEGORY_ROOT;
    public static final String CATEGORIES_LIST_REDIRECT = "redirect:/" + CATEGORIES_LIST;
    public static final String CATEGORY_ADD = CATEGORY_ROOT + "/add";
    public static final String CATEGORY_DELETE = CATEGORY_ROOT + "/delete";
    public static final String CATEGORY_UPDATE = CATEGORY_ROOT + "/update";
    public static final String CATEGORY_VIEW = CATEGORY_ROOT + "/view";

        
    // == constructor ==
    private Mappings(){}
}
