package teach0419;

public class House
{
	private int age = 0;
    private int rooms = 1;
	private String name;
	private String address;
    private String wall;
    private Boolean has_swimming_pool = false;
	
	public House() {
	}
	
	public House(HouseBuilder builder) {
		name = builder.name;
		age = builder.age;
		rooms = builder.rooms;
		address = builder.address;
		wall = builder.wall;
		has_swimming_pool = builder.has_swimming_pool;
	}
	
	public void printInfo() {
		System.out.println("House: " + name);
		System.out.println("Age: " + age + ", Rooms: " + rooms + ", Swimming Pool: " + has_swimming_pool);
		System.out.println("Address: " + address);
	}
	
	public static class HouseBuilder
	{
		private String name;
		private int age = 0;
		private int rooms = 1;
		private String address;
		private String wall;
		private Boolean has_swimming_pool = false;
		
		public HouseBuilder(String name) {
			this.name=name;
		}
		
		public HouseBuilder age(int age) {
			this.age=age;
			return this;
		}
		
		public HouseBuilder rooms(int rooms) {
			this.rooms=rooms;
			return this;
		}

		public HouseBuilder address(String address) {
			this.address=address;
			return this;
		}
		
		//Return the finally consrcuted User object
		public House build() {
			House house = new House(this);
			return house;
		}
	}
}
