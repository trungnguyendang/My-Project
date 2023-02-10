package StudentMan;

import java.util.ArrayList;
import java.util.Collections;

import utils.AttrRef;
import utils.NotPossibleException;

/*
 * @attributes              
 *  id              Integer
 *  name            String
 *  phoneNumber     String
 *  address         String 
 * @abstract_properties 
 * mutable(id)=false /\ optional(id)=false /\ min(id)=10^5 /\ max(id)=10^8
 * mutable(name)=true /\ optional(name)=false /\ length(name)=50
 * mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 
 * mutable(address)=true /\ optional(address)=false /\ length(address)=100
 */


public class UndergradStudent extends Student {

	private static final int MIN_ID = 1 ;
	private static final int MAX_ID = 999999 ;
	
	public UndergradStudent(@AttrRef("id") int id, @AttrRef("name") String name,
			@AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address)
			throws NotPossibleException {
		super (id, name, phoneNumber, address) ;
	}
	
	protected boolean validateId(int id) {
		if (id < MIN_ID) {
			return false ;
		} else if (id > MAX_ID) {
			return false ;
		} else {
			return true ;
		}
	}
	
	@Override
	public String toString() {
		return "UndergradStudent(" + getName() + ")" ;
	}

	public int compareTo(Student student) throws NullPointerException, ClassCastException {
		return super.compareTo(student) ;
	}
}
