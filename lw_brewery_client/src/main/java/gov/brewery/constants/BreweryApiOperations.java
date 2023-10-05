package gov.brewery.constants;

public class BreweryApiOperations {
	
	public static class Beer {
		public static final String V1_CREATE = "/api/v1/generated/beer";
		public static final String V1_UPDATE = "/api/v1/generated/beer/{id}";
		public static final String V1_DELETE = "/api/v1/generated/beer/{id}";
		public static final String V1_GET = "/api/v1/generated/beer/{id}";
		public static final String V1_GET_ALL = "/api/v1/generated/beer";
	}
}
