package pl.jdomanski.k47.category;

public enum CategoryType {
        INCOME("Wpływ"), 
        OUTCOME("Wydatek");
        
        private String description;

		private CategoryType(String description) {
			this.setDescription(description);
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	
}
