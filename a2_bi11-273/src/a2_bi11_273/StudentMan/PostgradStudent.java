package StudentMan;

import java.util.ArrayList;
import java.util.Collections;
import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;
/*
 * @attributes 
 * id               Integer 
 * name             String 
 * phoneNumber      String 
 * address          String 
 * gpa              Float
* @abstract_properties 
 * mutable(id)=false /\ optional(id)=false /\ min(id)=10^8 + 1 /\ max(id)=10^9
 * mutable(name)=true /\ optional(name)=false /\ length(name)=50 
 * mutable(phoneNumber)=true /\optional(phoneNumber)=false /\ length(phoneNumber)=10
 * mutable(address)=true /\ optional(address)=false /\length(address)=100 
 * mutable(gpa)=true /\optional(gpa)=false /\ min(gpa)=0.0 /\ max(gpa)=4.
 */



public class PostgradStudent extends Student {
	
        private static final int MIN_ID = 1;
	private static final int MAX_ID = 999999;
	private static final int MAX_NAME = 100;
        private static final int MAX_PHONENUMBER = 15;
        private static final int MAX_ADDRESS = 150;
    
	@DomainConstraint(type = "Float", optional = false, min = 0.0f, max = 4.0f, length = 0, mutable = false)
	protected float gpa ;

	public PostgradStudent(@AttrRef("id") int id, @AttrRef("name") String name,
			@AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address, @AttrRef("gpa") float gpa)
			throws NotPossibleException {
		super(id, name, phoneNumber, address);
		if (!validateGpa(gpa)) {
			throw new NotPossibleException("invalid gpa: " + gpa);
		}
		this.gpa = gpa;

	}
	
	private boolean validateGpa(float gpa) {
			if (gpa < 0.0f) {
				return false;
			}
			else if (gpa > 4.0f) {
				return false;
			}
			else {
				return true;
			}
	}
	
	protected boolean validateId (int id) {
		if (!(super.validateId(id)))
		      return false;

		    if (id > MAX_ID)
		      return false;
		    else
		      return true;
	}

	@DOpt(type=OptType.Observer) @AttrRef("gpa")
	public float getGpa() {
		return gpa;
	}

	@DOpt(type=OptType.Mutator) @AttrRef("id")
	public void setGpa(float gpa) throws NotPossibleException {
		if (validateGpa(gpa)) {
			this.gpa = gpa;
		} else {
			throw new NotPossibleException("invalid gpa: " + gpa);
		}
	}
	
	@Override
	public String toString() {
		return "PostgradStudent(" + getName() + ")" ;
	}
	
	public int compareTo(Student student) throws NullPointerException, ClassCastException {
		return super.compareTo(student) ;
	}

}
