package Domain;

public class Customer {

    private  Integer Customer_id;
        private String Customer_emailid;
        private String Customer_password;
    private String Customer_name;
    private String Customer_gender;
    private String Customer_mobile_no;

    private String User_type;
    private String Customer_address;


//    Customer_id int primary key,
//    Customer_emailid varchar(40) NOT NULL,
//    Customer_password varchar(40) NOT NULL,
//    Customer_name varchar(20) NOT NULL,
//    Customer_gender varchar(10) NOT NULL,
//    Customer_mobile_no int NOT NULL  Integer id
        public Customer() {


        }



    // Getters and setters for username and password

    public Integer getid() {
        return Customer_id;
    }

    public void setid(Integer id) {
        this.Customer_id = id;
    }
        public String getemailid() {
            return Customer_emailid;
        }

        public void setemailid(String emailid) {
            this.Customer_emailid = emailid;
        }

        public String getPassword() {
            return Customer_password;
        }

        public void setPassword(String password) {
            this.Customer_password = password;
        }

        public String getName() {
            return Customer_name;
        }

        public void setName(String name) {
            this.Customer_name = name;
        }
        public String getGender() {
            return Customer_gender;
        }

        public void setGender(String gender) {
            this.Customer_gender = gender;
        }

    public String getMobile() {
        return Customer_mobile_no;
    }

    public void setMobile(String mobile) {
        this.Customer_mobile_no = mobile;
    }

    public String getUser_type(){return User_type;}

    public void setCustomer_address(String address){this.Customer_address=address;}

    public String getCustomer_address(){return Customer_address;}


}
