package teach0419;

class HouseBuilderTest {

    public static void main(String[] args) {
        House house = new House.HouseBuilder("Pioneer Buidling")
                .age(1)
                .rooms(100)
                .address("No. 1, ZhongXiao East Road")
                .build();
        house.printInfo();
    }
}