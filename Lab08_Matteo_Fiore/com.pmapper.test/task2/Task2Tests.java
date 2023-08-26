package task2;

import com.pmapper.ParamMapper;
import com.pmapper.ParameterReadingException;
import org.junit.Test;

import static org.junit.Assert.*;

public class Task2Tests {

	@Test
	public void simpleTextParam() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-A text".split(" ");
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
		
		assertTrue(param.isaPresent());
		assertEquals("text", param.getaValue());
	}
	
	@Test
	public void compositeTextParam() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-A composite text -B other text".split(" ");;
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
		
		assertEquals("composite text", param.getaValue());
		assertEquals("other text", param.getbValue());
	}
	
	@Test
	public void emptyString() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-A -B text".split(" ");;
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
		
		assertEquals("", param.getaValue());
		assertEquals("text", param.getbValue());
	}
	
	@Test
	public void nullValue() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-B text".split(" ");;
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
		
		assertNull(param.getaValue());
		assertEquals("text", param.getbValue());
	}
	
	@Test(expected=ParameterReadingException.class)
	public void commandLineStartingWithValue() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="text -B text".split(" ");;
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
	}
	
	@Test(expected=ParameterReadingException.class)
	public void classWithInvalidPropertyType() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-A text".split(" ");;
		InvalidParamClassTask2 param = mapper.map(args , InvalidParamClassTask2.class);
	}

}
