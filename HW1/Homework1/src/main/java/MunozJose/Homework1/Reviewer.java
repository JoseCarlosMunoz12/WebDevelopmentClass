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
    private int UID;
    @NotBlank(message = "Major cannot be blank")
    private String Name;
    @NotBlank(message = "Major cannot be blank")
    private String Bio;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }
}
