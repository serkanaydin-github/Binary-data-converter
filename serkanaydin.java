import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
//Edited just 40.line because of uncorrect method name and for loop borders in big endian method
public class serkanaydin {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Enter input path");
		String path = input.nextLine();
		System.out.println("Enter datatype: (Signed(1)/Unsigned(2)/Float Point(3)");
		String condition = input.nextLine();
		System.out.println("Enter endian type : (BIG Endian(1)/LITTLE Endian(2)");
		String endian =input.nextLine(); 
		System.out.println("Enter size(1/2/3/4/6)");
		int size = input.nextInt();
		input.nextLine();
		System.out.println("Enter output path");
		String output = input.nextLine();
		
	
		File file = 
			      new File(path); 
			    Scanner sc = new Scanner(file);  
			    while (sc.hasNextLine()) {
			    	String line;
			    	line = sc.nextLine();
			    	String[] bytes = (line.toUpperCase()).split(" ");
			    	for(int j=0;j<1;j++) {
			    		if(bytes[bytes.length-1].equals(""))
			    			continue;
			    	if(endian.toUpperCase().equals("2")){
			    		littleEndian(bytes,size,condition.toUpperCase(),output);
			    	}
			    	else if(endian.toUpperCase().equals("1")){
		    			bigEndian(bytes,size,condition.toUpperCase(),output);
		    			System.out.println();
		    	}
			    	
			    	}
			    }     
			  
	}
	public static String HexToBinary(String Hex) {
	    String toReturn = new BigInteger(Hex, 16).toString(2);
	    return String.format("%" + (Hex.length()*4) + "s", toReturn).replace(' ', '0');
	}
	  public static int hexToDecimal(String hex) {
		    int decimalValue = 0;
		    for (int i = 0; i < hex.length(); i++) {
		      char hexChar = hex.charAt(i);
		      decimalValue = decimalValue * 16 + hexCharToDecimal(hexChar);
		    }
		    
		    return decimalValue;
		  }
	

	  public static int binaryToDecimal(String str){
	    int decimal =0;
	      for(int i=0;i<str.length();i++){
	          if(str.charAt(i)== '1'){
	        	  decimal+= Math.pow(2,str.length()-1-i);
	       }

	      }
	      return decimal;
	  }

		  public static int hexCharToDecimal(char ch) {
		    if (ch >= 'A' && ch <= 'F')
		      return 10 + ch - 'A';
		    else  
		      return ch - '0';
		  }
	public static  void littleEndian(String[] hex,int size,String condition,String output) throws IOException {
 ArrayList<String> littleEnd = new ArrayList<>(12/size);		
		
		switch(size) {
		case 1: 
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length;i++) {
				littleEnd.add(signed(hex[hex.length-i-1]));}
			}
			else	if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length;i++) {
					littleEnd.add(unsigned(hex[hex.length-i-1]));}
			}
			else	if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length;i++) {
					littleEnd.add(floatpoint(hex[hex.length-i-1],size));}
			}
			break;
		
		case 2:
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length-1;) {
				littleEnd.add(signed(hex[hex.length-1-i]+hex[hex.length-1-i-1]));
				i=i+2;
			}}
			else	if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length-1;) {
					littleEnd.add(unsigned(hex[hex.length-1-i]+hex[hex.length-1-i-1]));	
					i=i+2;
				}}
			else	if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length-1;) {
					littleEnd.add(floatpoint(hex[hex.length-1-i]+hex[hex.length-1-i-1],size));	
					i=i+2;
				}}
			break;
		case 3:
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length-2;) {
				littleEnd.add(signed(hex[hex.length-1-i]+hex[hex.length-1-i-1]+ hex[hex.length-1-i-2]));	
				i=i+3;
			}}
			else	if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length-2;) {
					littleEnd.add(unsigned(hex[hex.length-1-i]+hex[hex.length-1-i-1]+ hex[hex.length-1-i-2]));	
					i=i+3;
				}}
			else	if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length-2;) {
					littleEnd.add(floatpoint(hex[hex.length-1-i]+hex[hex.length-1-i-1]+ hex[hex.length-1-i-2],size));	
					i=i+3;
				}}
			break;
		case 4:	
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length-3;) {
				littleEnd.add(signed(hex[hex.length-1-i]+hex[hex.length-1-i-1]+ hex[hex.length-1-i-2] + hex[hex.length-1-i-3]));	
			i=i+4;
		}}
			else	if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length-3;) {
					littleEnd.add(unsigned(hex[hex.length-1-i]+hex[hex.length-1-i-1]+ hex[hex.length-1-i-2] + hex[hex.length-1-i-3]));
				i=i+4;
			}}
			else if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length-1;) {
					littleEnd.add(floatpoint(hex[hex.length-1-i]+hex[hex.length-1-i-1]+ hex[hex.length-1-i-2] + hex[hex.length-1-i-3],size));	
				i=i+4;
			}}
			break;
		case 6: 
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length-5;) {
				littleEnd.add(signed(hex[hex.length-1-i]+hex[hex.length-1-i-1]+ hex[hex.length-1-i-2] + hex[hex.length-1-i-3] + hex[hex.length-1-i-4] + hex[hex.length-1-i-5]));	
			i=i+6;
		}
			}
			else if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length-5;) {
					littleEnd.add(unsigned(hex[hex.length-1-i]+hex[hex.length-1-i-1]+ hex[hex.length-1-i-2] + hex[hex.length-1-i-3] + hex[hex.length-1-i-4] + hex[hex.length-1-i-5]));	
				i=i+6;
			}
				}
			else if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length-5;) {
					littleEnd.add(floatpoint(hex[hex.length-1-i]+hex[hex.length-1-i-1]+ hex[hex.length-1-i-2] + hex[hex.length-1-i-3] + hex[hex.length-1-i-4] + hex[hex.length-1-i-5],size));	
				i=i+6;
			}
				}
			break;
	}
		
		
		
		File outputFile = new File(output);
		FileWriter printWriter = new FileWriter(output,true);
     
	   
	String temp="";
	for(int i=0;i<littleEnd.size();i++) {
		temp+=littleEnd.get(littleEnd.size()-i-1);}
	printWriter.write(temp);
	littleEnd.clear();
	printWriter.write(System.getProperty( "line.separator" ));
	printWriter.flush();
	}
	
	
	public static void bigEndian(String[] hex ,int size,String condition,String output) throws IOException {

		 ArrayList<String> bigEnd = new ArrayList<>(12/size);	
		switch(size) {
		case 1: 
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length;i++) {
				bigEnd.add(signed(hex[i]));}
			}
			else	if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length;i++) {
					bigEnd.add(unsigned(hex[i]));}
			}
			else	if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length;i++) {
					bigEnd.add(floatpoint(hex[i],size));}
			}
			break;
		
		case 2:
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length;) {
				bigEnd.add(signed(hex[i]+hex[i+1]));	
				i=i+2;
			}}
			else	if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length;) {
					bigEnd.add(unsigned(hex[i]+hex[i+1]));	i=i+2;
				}}
			else	if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length;) {
					bigEnd.add(floatpoint(hex[i]+hex[i+1],size));	i=i+2;
				}}
			break;
		case 3:
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length;) {
				bigEnd.add(signed(hex[i]+hex[i+1]+hex[i+2]));	i=i+3;
			}}
			else	if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length;) {
					bigEnd.add(unsigned(hex[i]+hex[i+1]+hex[i+2]));	i=i+3;
				}}
			else	if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length;) {
					bigEnd.add(floatpoint(hex[i]+hex[i+1]+hex[i+2],size));	i=i+3;
				}}
			break;
		case 4:	
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length;) {
				bigEnd.add(signed(hex[i]+hex[i+1]+hex[i+2]+hex[i+3])); i=i+4;
			i=i+4;	
		}}
			else	if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length;) {
					bigEnd.add(unsigned(hex[i]+hex[i+1]+hex[i+2]+hex[i+3]));i=i+4;	
			}}
			else if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length;) {
					bigEnd.add(floatpoint(hex[i]+hex[i+1]+hex[i+2]+hex[i+3],size));	i=i+4;
			}}
			break;
		case 6: 
			if(condition.toUpperCase().equals("1")) {
			for(int i=0;i<hex.length;) {
				bigEnd.add(signed(hex[i]+hex[i+1]+hex[i+2]+hex[i+3]+hex[i+4]+hex[i+5]));	i=i+6;
		
		}
			}
			else if(condition.toUpperCase().equals("2")) {
				for(int i=0;i<hex.length;) {
					bigEnd.add(unsigned(hex[i]+hex[i+1]+hex[i+2]+hex[i+3]+hex[i+4]+hex[i+5]));	i=i+6;
			
			}
				}
			else if(condition.toUpperCase().equals("3")) {
				for(int i=0;i<hex.length;) {
					bigEnd.add(floatpoint(hex[i]+hex[i+1]+hex[i+2]+hex[i+3]+hex[i+4]+hex[i+5],size)); i=i+6;	
			
			}
				}
			break;
	}
		String temp="";
		for(int i=0;i<bigEnd.size();i++) {
			temp+=bigEnd.get(i);}
		System.out.println(temp);
		File outputFile = new File(output);
		FileWriter printWriter = new FileWriter(output,true);
     
	printWriter.write(temp);
	bigEnd.clear();
	printWriter.write(System.getProperty( "line.separator" ));
	printWriter.flush();
	
	}
	public static String signed(String s) {
		String x = HexToBinary(s);
		int y=(int) Math.pow(2,(x.length()-1));
	
		if(x.charAt(0)=='1') {
	return " " + (hexToDecimal(s.toUpperCase()) -2*y);
		}
		else if(x.charAt(0)=='0')
		return " "+ (hexToDecimal(s));
		return "couldnt converted";
	}
	
		
	
public static String unsigned(String s) {
	return " "+ (hexToDecimal(s));
	
	}
 
public static String floatpoint(String s,int size){
	double number;
	int E;
	double m=0;
	int bias;
	int e;
	switch (size) {
	case 1: 
		
		
		
		 bias = (int) (Math.pow(2,3)-1);
		 e = binaryToDecimal(HexToBinary(s).substring(1,5));
		
		if(e != 0 && HexToBinary(s).substring(1,5).equals("1111")==false) {	 
		E=e-bias;
		for(int i=0;i<HexToBinary(s).substring(5).length();i++) {
			if(HexToBinary(s).substring(5).charAt(i) == '1') {
				m+=Math.pow(2, -1-i);
			}
		}
		if(HexToBinary(s).charAt(0) == '1') {
	    number = 	-(1+m) * Math.pow(2, E);
	   return " " + (int)(number * 10000)/10000;
}else {
	 number =  (1+m) * Math.pow(2, E);
		return " " + (int)(number * 10000)/10000;
		} }
		else if(e ==0 && HexToBinary(s).substring(1,5).equals("1111")==false) {
			
			E=1-bias;
			for(int i=0;i<HexToBinary(s).substring(5).length();i++) {
				if(HexToBinary(s).substring(5).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
				}
			}
			if(HexToBinary(s).charAt(0) == '1') {
		    number = - m* Math.pow(2, E);
		   return " " + (int)(number * 10000)/10000 ;
		   
		   		
	}else {
		 number =  m * Math.pow(2, E);
		return " " +number ;}
		
		}
		else if(HexToBinary(s).substring(1,5).equals("1111")) {
			for(int i=0;i<HexToBinary(s).substring(5).length();i++) {
				if(HexToBinary(s).substring(5).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
					
				}
			}
			if(m==0 && HexToBinary(s).charAt(0)=='1') {
				return " -"+ Double.POSITIVE_INFINITY;
			}
			else if(m==0 && HexToBinary(s).charAt(0)=='0') {
				return " " + Double.POSITIVE_INFINITY;
			
			}
			else return " NaN ";
		}
		break;
		 
		
	case 2:
		bias = (int) (Math.pow(2,5)-1);
		e = binaryToDecimal(HexToBinary(s).substring(1,7));
	
			if(e != 0 && HexToBinary(s).substring(1,7).equals("111111")==false) {	 
		E=e-bias;
		for(int i=0;i<HexToBinary(s).substring(7).length();i++) {
			if(HexToBinary(s).substring(7).charAt(i) == '1') {
				m+=Math.pow(2, -1-i);
			}
		}
		if(HexToBinary(s).charAt(0) == '1') {
	    number = 	-(1+m) * Math.pow(2, E);
	   return " " + (int)(number * 10000)/10000;
}else {
	 number =  (1+m) * Math.pow(2, E);
		return " " + (int)(number * 10000)/10000;
		} }
		else if(e ==0  && HexToBinary(s).substring(1,7).equals("111111")==false) {
			
			E=1-bias;
			for(int i=0;i<HexToBinary(s).substring(7).length();i++) {
				if(HexToBinary(s).substring(7).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
				}
			}
			if(HexToBinary(s).charAt(0) == '1') {
		    number  = - m* Math.pow(2, E);
		   return  " " +number ;
		   
		   		
	}else {
		 number =  m * Math.pow(2, E);
		return " " +number;}
		
		}
		else if(HexToBinary(s).substring(1,7).equals("111111")) {
			for(int i=0;i<HexToBinary(s).substring(7).length();i++) {
				if(HexToBinary(s).substring(7).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
					
				}
			}
			if(m==0 && HexToBinary(s).charAt(0)=='1') {
				return " -"+ Double.POSITIVE_INFINITY;
			}
			else if(m==0 && HexToBinary(s).charAt(0)=='0') {
				return " " + Double.POSITIVE_INFINITY;
			
			}
			else return " NaN ";
		}
	break;	
	case 3:
		bias = (int) (Math.pow(2,7)-1);
		e = binaryToDecimal(HexToBinary(s).substring(1,9));
		
			if(e != 0 && HexToBinary(s).substring(1,9).equals("11111111")==false) {	 
		E=e-bias;
		for(int i=0;i<HexToBinary(s).substring(9,24).length();i++) {
			if(HexToBinary(s).substring(9,24).charAt(i) == '1') {
				m+=Math.pow(2, -1-i);
			}
		}
		if((HexToBinary(s).charAt(23) =='0') && (HexToBinary(s).charAt(24) =='1') && (HexToBinary(s).charAt(25) =='1')) {
			m+=Math.pow(2, -13);
		}
		else if((HexToBinary(s).charAt(23) =='1') && (HexToBinary(s).charAt(24) =='1')) {
			m+=Math.pow(2, -13);
			}
		if(HexToBinary(s).charAt(0) == '1') {
	    number = 	-(1+m) * Math.pow(2, E);
	   return String.format(" %.5e",number );
}else {
	 number =  (1+m) * Math.pow(2, E);
		return String.format(" %.5e",number );
		} }
		else if(e ==0 && HexToBinary(s).substring(1,9).equals("11111111")==false) {
			
			E=1-bias;
			for(int i=0;i<HexToBinary(s).substring(9,24).length();i++) {
				if(HexToBinary(s).substring(9,24).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
					
				}
			}
			if((HexToBinary(s).charAt(23) =='0') && (HexToBinary(s).charAt(24) =='1') && (HexToBinary(s).charAt(25) =='1')) {
				m+=Math.pow(2, -13);
			}
			else if((HexToBinary(s).charAt(23) =='1') && (HexToBinary(s).charAt(24) =='1')) {
				m+=Math.pow(2, -13);
				}
			if(HexToBinary(s).charAt(0) == '1') {
		    number = - m* Math.pow(2, E);
		   return String.format(" %.5e",number );
		   
		   		
	}else {
		 number =  m * Math.pow(2, E);
		return String.format(" %.5e",number );}
		
		}
		else if(HexToBinary(s).substring(1,9).equals("11111111")) {
			for(int i=0;i<HexToBinary(s).substring(11,24).length();i++) {
				if(HexToBinary(s).substring(11,24).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
					
				}
			}
			if((HexToBinary(s).charAt(23) =='0') && (HexToBinary(s).charAt(24) =='1') && (HexToBinary(s).charAt(25) =='1')) {
				m+=Math.pow(2, -13);
			}
			else if((HexToBinary(s).charAt(23) =='1') && (HexToBinary(s).charAt(24) =='1')) {
				m+=Math.pow(2, -13);
				}
			
			if(m==0 && HexToBinary(s).charAt(0)=='1') {
				return " -"+ Double.POSITIVE_INFINITY;
			}
			else if(m==0 && HexToBinary(s).charAt(0)=='0') {
				return " " + Double.POSITIVE_INFINITY;
			
			}
			else return " NaN ";
		}
	break;	
	case 4:
		bias = (int) (Math.pow(2,9)-1);
		e = binaryToDecimal(HexToBinary(s).substring(1,11));
	
			if(e != 0 && HexToBinary(s).substring(1,11).equals("1111111111")==false) {	 
		E=e-bias;
		for(int i=0;i<HexToBinary(s).substring(11,24).length();i++) {
			if(HexToBinary(s).substring(11,24).charAt(i) == '1') {
				m+=Math.pow(2, -1-i);
			}
		}
		if((HexToBinary(s).charAt(23) =='0') && (HexToBinary(s).charAt(24) =='1') && (HexToBinary(s).charAt(25) =='1')) {
			m+=Math.pow(2, -13);
		}
		else if((HexToBinary(s).charAt(23) =='1') && (HexToBinary(s).charAt(24) =='1')) {
			m+=Math.pow(2, -13);
			}
		if(HexToBinary(s).charAt(0) == '1') {
	    number = 	-(1+m) * Math.pow(2, E);
	    
	   return  String.format(" %.5e",number );
}else {
	 number =  (1+m) * Math.pow(2, E);
		return String.format(" %.5e",number );
		} }
		else if(e ==0 && HexToBinary(s).substring(1,11).equals("1111111111")==false) {
			
			E=1-bias;
			for(int i=0;i<HexToBinary(s).substring(11,24).length();i++) {
				if(HexToBinary(s).substring(11,24).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
					
				}
			}
			if((HexToBinary(s).charAt(23) =='0') && (HexToBinary(s).charAt(24) =='1') && (HexToBinary(s).charAt(25) =='1')) {
				m+=Math.pow(2, -13);
			}
			else if((HexToBinary(s).charAt(23) =='1') && (HexToBinary(s).charAt(24) =='1')) {
				m+=Math.pow(2, -13);
				}
			if(HexToBinary(s).charAt(0) == '1') {
		    number = - m* Math.pow(2, E);
		   return  String.format(" %.5e",number ) ;
		   
		   		
	}else {
		 number =  m * Math.pow(2, E);
		return String.format(" %.5e",number );}
		
		}
		else if(HexToBinary(s).substring(1,11).equals("1111111111")) {
			for(int i=0;i<HexToBinary(s).substring(11,24).length();i++) {
				if(HexToBinary(s).substring(11,24).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
					
				}
			}
			if((HexToBinary(s).charAt(23) =='0') && (HexToBinary(s).charAt(24) =='1') && (HexToBinary(s).charAt(25) =='1')) {
				m+=Math.pow(2, -13);
			}
			else if((HexToBinary(s).charAt(23) =='1') && (HexToBinary(s).charAt(24) =='1')) {
				m+=Math.pow(2, -13);
				}
			
			if(m==0 && HexToBinary(s).charAt(0)=='1') {
				return " -"+ Double.POSITIVE_INFINITY;
			}
			else if(m==0 && HexToBinary(s).charAt(0)=='0') {
				return " +" + Double.POSITIVE_INFINITY;
			
			}
			else return " NaN ";
		}
		break;
	case 6:
		bias = (int) (Math.pow(2,11)-1);
		e = binaryToDecimal(HexToBinary(s).substring(1,13));
		
		if(e != 0 && HexToBinary(s).substring(1,13).equals("111111111111")==false) {	 
		E=e-bias;
		for(int i=0;i<HexToBinary(s).substring(9,24).length();i++) {
			if(HexToBinary(s).substring(9,24).charAt(i) == '1') {
				m+=Math.pow(2, -1-i);
			}
		}
		if((HexToBinary(s).charAt(23) =='0') && (HexToBinary(s).charAt(24) =='1') && (HexToBinary(s).charAt(25) =='1')) {
			m+=Math.pow(2, -13);
		}
		else if((HexToBinary(s).charAt(23) =='1') && (HexToBinary(s).charAt(24) =='1')) {
			m+=Math.pow(2, -13);
			}
		if(HexToBinary(s).charAt(0) == '1') {
	    number = 	-(1+m) * Math.pow(2, E);
	   return String.format(" %.5e",number );
}else {
	 number =  (1+m) * Math.pow(2, E);
		return String.format(" %.5e",number );
		} }
		else if(e ==0 && HexToBinary(s).substring(1,13).equals("111111111111")==false) {
			
			E=1-bias;
			for(int i=0;i<HexToBinary(s).substring(9,24).length();i++) {
				if(HexToBinary(s).substring(9,24).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
					
				}
			}
			if((HexToBinary(s).charAt(23) =='0') && (HexToBinary(s).charAt(24) =='1') && (HexToBinary(s).charAt(25) =='1')) {
				m+=Math.pow(2, -13);
			}
			else if((HexToBinary(s).charAt(23) =='1') && (HexToBinary(s).charAt(24) =='1')) {
				m+=Math.pow(2, -13);
				}
			if(HexToBinary(s).charAt(0) == '1') {
		    number = - m* Math.pow(2, E);
		   return String.format(" %.5e",number ) ;
		   
		   		
	}else {
		 number =  m * Math.pow(2, E);
		return String.format(" %.5e",number );}
		
		}
		else if(HexToBinary(s).substring(1,13).equals("111111111111")) {
			for(int i=0;i<HexToBinary(s).substring(11,24).length();i++) {
				if(HexToBinary(s).substring(11,24).charAt(i) == '1') {
					m+=Math.pow(2, -1-i);
					
				}
			}
			if((HexToBinary(s).charAt(23) =='0') && (HexToBinary(s).charAt(24) =='1') && (HexToBinary(s).charAt(25) =='1')) {
				m+=Math.pow(2, -13);
			}
			else if((HexToBinary(s).charAt(23) =='1') && (HexToBinary(s).charAt(24) =='1')) {
				m+=Math.pow(2, -13);
				}
			
			if(m==0 && HexToBinary(s).charAt(0)=='1') {
				return " -"+ Double.POSITIVE_INFINITY;
			}
			else if(m==0 && HexToBinary(s).charAt(0)=='0') {
				return " " + Double.POSITIVE_INFINITY;
			
			}
			else return " NaN ";
		}
		break;
	}
	return "there is an error";
}
}