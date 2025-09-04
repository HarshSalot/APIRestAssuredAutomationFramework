package requests;

public enum APIresources {
	
	    addProject("/project/blank-spec"),
	    deleteProject("/project/"),
	    addContact("/contact"),
	    actionOnContact("/contact/"),
	    getUserProfile("/user/"),
	    addSpec("/spec"),
	    actionOnSpec("/spec/"),
	    login("/login"),
	    forgetPass("/forgotpassword"),
	    registration("/registration");

	    private String resource;

		APIresources(String resource) {
			this.resource=resource;
		}
		
		public String getResource() {
			return resource;
		}

}
