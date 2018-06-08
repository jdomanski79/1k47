package pl.jdomanski.k47.category;

public enum CategoryType {
        INCOME("Wpływ", "Wpływy"), 
        OUTCOME("Wydatek", "Wydatki");
        
        private String singularDescription;
        private String pluralDescription;

		private CategoryType(String singular, String plural) {
			this.singularDescription = singular;
			this.pluralDescription = plural;
		}

		public String getSingularDescription() {
			return singularDescription;
		}

		public String getPluralDescription() {
			return pluralDescription;
		}
	
}
