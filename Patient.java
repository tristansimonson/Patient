/**
 *
 * @author tristan
 */

import java.util.ArrayList;

public class Patient {
    int patientID;  // id provided for searching and patients with same name
    String fname;   // first name
    String lname;   // last name
    int age;    // age of patient  
    Address address; // patient address
    EmergencyContact EC; // emergency contact of patient
    
    static ArrayList patients = new ArrayList<Patient>();   // stores all patients
    ArrayList illnesses = new ArrayList<Illness>();   // stores all illnesses
    ArrayList prescriptions = new ArrayList<Prescription>();   // stores all prescriptions
    
    // patient constructor
    public Patient(String fname, String lname, int age){
        this.patientID = patients.size() + 1;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.illnesses = new ArrayList<Illness>();
        this.prescriptions = new ArrayList<Prescription>();
        patients.add(this);
    }
    
    // method for updating patient name, possibly if married or legal name change
    public void changeName(String newFirst, String newLast){
        this.fname = newFirst;
        this.lname = newLast;
    }

    // sequential search for patient by patient ID
    public static Patient searchPatient(int pID){
        Patient result = null;
        for(int i = 0; i < patients.size(); i++){
            Patient temp = (Patient) patients.get(i);
            if(temp.patientID == pID){
                result = temp;
                break;
            }
        }
        return result;
    }
    
    // update address
    public void changeAddress(String line1, String line2, String city, String state, int zip){
        this.address = new Address(line1, line2, city, state, zip);
    }
    
    // add emergency contact for patient
    public void addEmergencyContact(String fname, String lname, String phoneNum){
        this.EC = new EmergencyContact(fname, lname, phoneNum);
    }
    
    // for doctor so they can add illness to patient
    public void addIllness(String name, String severity){
        Illness ill = new Illness(name, severity);
        this.illnesses.add(ill);
    }
    
    // for doctor so they can add prescription for patient
    public void addPrescription(String name, String type, String dose, Employee doctor, String date){
        Prescription script = new Prescription(name, type, dose, doctor, date);
        this.prescriptions.add(script);
    }
    
    // turn patient into string
    public String patientToString(){
        String s;
        s = "ID: " + this.patientID + "\n";
        s += "NAME: " + this.lname;
        s += ", " + this.fname + "\n";
        s += "AGE: " + this.age;
        if(this.address != null){
            s += "\n" + "ADDRESS: " + this.address.line1;
            if(this.address.line2 != null){
                s += " " + this.address.line2;
            }
            s += " " + this.address.city + ", " + this.address.state + " " + this.address.zip;
        }
        if(this.EC != null){
            s += "\n" + "EMERGENCY CONTACT: " + this.EC.lname + ", " + this.EC.fname;
        }
        if(this.illnesses.size() != 0){
            s += "\n" + "ILLNESSES: ";
            for(int i = 0; i < this.illnesses.size(); i++){
                Illness temp = (Illness) this.illnesses.get(i);
                s += temp.name + " ";
            }
        }
        if(this.prescriptions.size() != 0){
            s += "\n" + "PRESCRIPTIONS: ";
            for(int i = 0; i < this.prescriptions.size(); i++){
                Prescription temp = (Prescription) this.prescriptions.get(i);
                s += temp.name + " ";
            }
        }
        return s + "\n";
    }
    
    public static void main (String[] Args){
        new Patient("Chad", "Kruger", 22);
        Patient Chad = searchPatient(1);
        Chad.changeAddress("123 Rock On Blvd", "Apt A", "Lincoln", "NE", 12345);
        Chad.addEmergencyContact("Bill", "Nye", "(000)-867-5309");
        Chad.EC.changeAddress("Ginger Bread Lane", "", "Duloc", "TX", 55555);
        Illness cold = new Illness("cold", "severe");
        Chad.illnesses.add(cold);
        Employee DrDundee = new Employee("Crocodile", "Dundee", Type.DOCTOR);
        Prescription coldMedicine = new Prescription("ColdBeGone", "Psychadelic", "1 every hour",DrDundee, "1-1-2018");
        Chad.prescriptions.add(coldMedicine);
        System.out.println(Chad.patientToString());
    }
}

// class for addresses
class Address{
    String line1;
    String line2;
    String city;
    String state;
    int zip;
    
    public Address(String line1, String line2, String city, String state, int zip){
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}

// class for emergency contacts of patients
class EmergencyContact{
    String fname;
    String lname;
    String phoneNum;
    Address address;
    
    public EmergencyContact(String fname, String lname, String phoneNum){
        this.fname = fname;
        this.lname = lname;
        this.phoneNum = phoneNum;
    }
    
    // update address
    public void changeAddress(String line1, String line2, String city, String state, int zip){
        this.address = new Address(line1, line2, city, state, zip);
    }
}

// illnesses and details
class Illness{
    String name;
    String severity;
    
    public Illness(String name, String severity){
        this.name = name;
        this.severity = severity;
    }
}

// prescription medications and their details
class Prescription{
    String name;    // name of the prescription
    String type;    // opiod etc
    String dose;    // patient's dose
    Employee doctor;  // name of prescribing doctor
    String date;    // day-month-year

    public Prescription(String name, String type, String dose, Employee doctor, String date){
        this.name = name;
        this.type = type;
        this.dose = dose;
        this.doctor = doctor;
        this.date = date;
    }
}

// enumerator for defining occupations
enum Type{
        DOCTOR, NURSE;
}

// class for doctors
class Employee{
    String fname;
    String lname;
    Type type;    // uses enum values to display occupation of employee
    int employeeID;
    Address address;
    
    public static ArrayList employees = new ArrayList<Employee>();
    
    public Employee(String fname, String lname, Type type){
        this.fname = fname;
        this.lname = lname;
        this.type = type;
        this.employeeID = employees.size() + 1;
    }
    
    // update address
    public void changeAddress(String line1, String line2, String city, String state, int zip){
        this.address = new Address(line1, line2, city, state, zip);
    }
}
