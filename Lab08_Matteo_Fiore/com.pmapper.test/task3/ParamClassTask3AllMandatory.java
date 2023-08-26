package task3;

import com.pmapper.annotations.Mandatory;
import com.pmapper.annotations.TextValue;

@Mandatory
public class ParamClassTask3AllMandatory {

	@TextValue(name="A")
	private String aValue;
	
	@TextValue(name="B")
	private String bValue;

	public String getaValue() {
		return aValue;
	}

	public void setaValue(String aValue) {
		this.aValue = aValue;
	}

	public String getbValue() {
		return bValue;
	}

	public void setbValue(String bValue) {
		this.bValue = bValue;
	}


}
