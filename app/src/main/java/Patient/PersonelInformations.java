package Patient;

public class PersonelInformations  extends  Patient{

    public String adress_description;
    public String city;
    public String district;
    public String neighborhood;
    public String street;
    public String apartmant_name;
    public int door_number;


    public String tel_no_description;
    public String tel_no1;
    public String tel_no2;


    public PersonelInformations(String adress_description, String city, String district,
                                String neighborhood, String street, String apartmant_name,
                                int door_number, String tel_no_description, String tel_no1, String tel_no2) {
        this.adress_description = adress_description;
        this.city = city;
        this.district = district;
        this.neighborhood = neighborhood;
        this.street = street;
        this.apartmant_name = apartmant_name;
        this.door_number = door_number;
        this.tel_no_description = tel_no_description;
        this.tel_no1 = tel_no1;
        this.tel_no2 = tel_no2;
    }

    public PersonelInformations() {
    }
}
