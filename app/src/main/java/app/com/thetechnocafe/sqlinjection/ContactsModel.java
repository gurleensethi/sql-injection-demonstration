package app.com.thetechnocafe.sqlinjection;

/**
 * Created by gurleensethi on 22/11/16.
 */

public class ContactsModel {
    private String name;
    private String email;
    private String contact;
    private String type;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
