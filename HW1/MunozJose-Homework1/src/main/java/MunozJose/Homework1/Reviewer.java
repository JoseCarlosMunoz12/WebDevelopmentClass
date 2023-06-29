/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MunozJose.Homework1;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author jose
 */
public class Reviewer {

    @Min(value = 10000, message = "SID must be a 5 digit value")
    @Max(value = 99999, message = "SID must be a 5 digit value")
    private int uid;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Biography cannot be blank")
    private String bio;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.uid;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reviewer other = (Reviewer) obj;
        return this.uid == other.uid;
    }

    public int getUID() {
        return uid;
    }

    public void setUID(int UID) {
        this.uid = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String Bio) {
        this.bio = Bio;
    }
}
