package StudentMan;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;
import kengine.Doc;
import kengine.Engine;
import kengine.Query;
interface Document {
	String toHTMLDoc();
}
public class Student implements Comparable<Student> {
        private static final int MIN_ID = 1 ;
	private static final int MAX_ID = 999999 ;
	private static final int MAX_NAME = 100;
	private static final int MAX_PHONENUMBER = 15;
	private static final int MAX_ADDRESS = 150;
        
	@DomainConstraint(type = "integer",mutable=false,optional=false,min=(10^8)+1,max=10^9)
	private int id;
	@DomainConstraint(type = "string",mutable=true,optional=false,length=50)
	private String name;
	@DomainConstraint(type = "integer",mutable=true,optional=false,length=10)
	private String phoneNumber;
	@DomainConstraint(type = "string",mutable=true,optional=false,length=100)
	private String address;
	@DomainConstraint(type = "float",mutable=true,optional=false,min=0.0,max=4.0)
        private float gpa;
	
	
	public Student(@AttrRef("id") int id,@AttrRef("name") String name,@AttrRef("phoneNumber") String phoneNumber,@AttrRef("address") String address){
            if (!validateId(id)) {
		throw new NotPossibleException("invalid id: " + id) ;
			}
            if (!validateName(name)) {
                throw new NotPossibleException("invalid name: " + name) ;
			}
            if (!validatePhoneNumber(phoneNumber)) {
                throw new NotPossibleException("invalid phoneNumber: " + phoneNumber) ;
			}
            if (!validateAddress(address)) {
                throw new NotPossibleException("invalid address: " + address) ;
			}
            
                this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gpa = (float) gpa;
	}
        
	private boolean validateAddress(String address) {
                if (address == null) {
                    return false ;
                } else if (address.length() > MAX_ADDRESS) {
                    return false ;
                } else {
                    return true ;
                }
	}
        
	private boolean validatePhoneNumber(String phoneNumber) {
                if (phoneNumber == null) {
                    return false ;
                } else if (phoneNumber.length() > MAX_PHONENUMBER) {
                    return false ;
                } else {
                    return true ;
                }
	}
        
	private boolean validateName(String name) {
                if (name == null) {
                    return false ;
                }else if (name.length() > MAX_NAME) {
                    return false ;
                }else{
                    return true ;
                }
	}
        
	protected boolean validateId(int id) {
                if (id < MIN_ID) {
                    return false ;
                } else if (id > MAX_ID) {
                    return false ;
                }else{
                    return true ;
                }
	}
        
	public boolean repOK(){
                return validate(id,name,phoneNumber,address);
        }
       
        
	@DOpt(type=OptType.Observer) @AttrRef("id")
	public int getId() {
		return id;
	}
	@DOpt(type=OptType.Mutator) @AttrRef("id")
	public void setId(int id) throws NotPossibleException {
                if (validateId(id)) {
                    this.id = id ;
                }else{
                    throw new NotPossibleException ("invalid id: " + id) ;
                }
        } 

	@DOpt(type=OptType.Observer) @AttrRef("name")
	public String getName() {
		return name;
	}
        
        @DOpt(type=OptType.Mutator) @AttrRef("name")
	public void setName(String name)  throws NotPossibleException {
                if (validateName(name)) {
                    this.name = name ;
                }else{
                    throw new NotPossibleException ("invalid name: " + name) ;
                }
	}
        
	@DOpt(type=OptType.Observer) @AttrRef("phoneNumber")
	public String getPhoneNumber() {
		return getPhoneNumber();
	}
	@DOpt(type=OptType.Mutator) @AttrRef("phoneNumber")
	public void setPhoneName(String phoneNumber) {
                if (validatePhoneNumber(phoneNumber)){
                    this.phoneNumber = phoneNumber;
                }else{
                    throw new NotPossibleException("invalid phone number: "+ phoneNumber);
                }
	}
	@DOpt(type=OptType.Observer) @AttrRef("address")
	public String getAddress() {
		return address;
	}
	@DOpt(type=OptType.Mutator) @AttrRef("address")
	public void setAddress(String address) {
		if (validateAddress(address)){
                    this.address = address;
                }else{
                    throw new NotPossibleException("address: "+ address);
                }
	}
	@DOpt(type=OptType.Observer) @AttrRef("gpa")
	public float getGpa() {
		return gpa;
	}
	@DOpt(type=OptType.Mutator) @AttrRef("gpa")
	public void setGpa(double gpa) {
		this.gpa = (float) gpa;
	}
	 @Override
	    public String toString() {
	        return "Student: ID " + id + ", Name " + name + ", phoneNumber " + phoneNumber  + ", Address " + address + ", gpa " + gpa;
	    }

	@Override
	public int compareTo(Student student) throws NullPointerException, ClassCastException{
		 if (student == null)
		     throw new NullPointerException("student.compareByName");
		    else if (!(student instanceof Student))
		      throw new ClassCastException("not a Student: " + student);
			    
		    Student s = (Student) student;
		    return this.name.compareTo(s.name);
	}
        
        public void Document(){
            
        }

        

    private boolean validate(int id, String name, String phoneNumber, String address) {
           return validateId(id) && validateName(name) && validatePhoneNumber(phoneNumber) && validateAddress(address);
    }

	public String toHTMLDoc() {
		StringBuilder htmlBuilder = new StringBuilder();
		
		htmlBuilder.append("<html> \n");
		htmlBuilder.append("<head><title>"+"Student:"+id.toString()+"-"+name+"</title></head> \n");
		htmlBuilder.append("<body> \n");
		htmlBuilder.append(getId().toString()+" "+getName()+" "+getPhoneNumber()+" "+getAddress()+" "+"\n");
		htmlBuilder.append("</body></html>");
		
		return html.toString();
		
	}

    
	
}