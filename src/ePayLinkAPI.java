	import java.net.*;
	import java.io.*;
	import java.security.*;
	import ePayLinkPKI.*;
	public class ePayLinkAPI
	{
	public static void main(String[] args) throws Exception
	{
		//System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\piglet\\workspace\\InstallCert\\bin\\keystore");
		//System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk1.7.0\\jre\\lib\\security\\cacerts");
		//System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		//System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk1.7.0\\jre\\lib\\security\\cacerts");
//	Security.addProvider (new com.sun.net.ssl.internal.ssl.Provider());
//	System.setProperty("java.protocol.handler.pkgs",
//	"com.sun.net.ssl.internal.www.protocol");
//	ePayLinkClient eplc = new
//	ePayLinkClient("C:\\Program Files (x86)\\ePayLinkPKI\\conf\\ePayLinkPKI.conf");
//	String inPaymentType = new String("cc");
//	String inLocale = new String("zh");
//	String inCurrCode = new String("344");
//	String inSuccessURL = new String("http://");
//	String inFailURL = new String("http://");
//	String inCancelURL = new String("http://");
//	String inPayerEmail = new String("");
//	String inPaymentDesc = new String("");
//	// Set the ReferenceNo
//	String inReferenceNo = new String("demo2002121312222");
//	// Set the OpCode to (03 = Capture, 04 = Refund)
//	String inOpCode = new String("80");
//	// Set the Amount of Capture or Refund
//	String inAmount = new String("150");
//	String DoURL = new String(eplc.genEncDoURL(inPaymentType, inReferenceNo, inAmount, inLocale, inCurrCode, inOpCode, inSuccessURL,
//	
//	               inFailURL, inCancelURL, inPayerEmail, inPaymentDesc));
//	System.out.println(DoURL);
//	URL ePayLink = new URL(DoURL);
//	BufferedReader in = new BufferedReader(new
//	                       InputStreamReader(ePayLink.openStream()));
//	String DR;
//	
//	DR = in.readLine();
//	DRObject dro = eplc.decEncDR(DR);
//	System.out.println("DR = " + DR);
//	System.out.println("DR Format Correct = " + dro.isMsgFormatCorrect()); System.out.println("validateSignature = " + dro.validateSignature()); System.out.println("PaymentType = " + dro.getPaymentType()); System.out.println("MerchantID = " + dro.getMerchantID()); System.out.println("ReferenceNo = " + dro.getReferenceNo()); System.out.println("Amount = " + dro.getAmount()); System.out.println("Locale = " + dro.getLocale()); System.out.println("CurrCode = " + dro.getCurrCode()); System.out.println("OpCode = " + dro.getOpCode()); System.out.println("ResponseCode = " + dro.getResponseCode()); System.out.println("ePayLinkTxID = " + dro.getePayLinkTxID());
//	
//	in.close();
	//}
	
		///////////////////////////////////
		//For Test
		
		/* Create the ePayLink Client Object */
		/* Create the ePayLink Client Object */
		ePayLinkClient eplc = new ePayLinkClient();
		/* Load the config file */
		eplc.setConfigFile("C:\\Program Files (x86)\\ePayLinkPKI\\conf\\ePayLinkPKI.conf");
		/* Check whether the config file is correctly loaded */
		if (eplc.isConfigFileCorrect() == false)
		{
		System.out.println("Config file incorrect");
		}
		else
		{
		boolean setCC = false;
		String ccbrand = new String("VISA");
		String ccnum = new String("4111111111111111");
		String ccexpyyyy = new String("2017");
		String ccexpmm = new String("05");
		/* The fields ccHolderName, cvc2, cid are reserved for future use */
		String ccHolderName = new String("");
		String cvc2 = new String("");
		String cid = new String("");
		try
		{
		setCC = eplc.setCC(ccbrand,ccnum,ccexpyyyy,ccexpmm,ccHolderName,cvc2,cid);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		if (setCC != true)
		{/* setCC failed, display the last error code */
			System.out.println("Error when setting credit card information.<BR>");
		}
		else
		{
		/* Define the parameters for the transaction */
		String paymentType = new String("cc");
		String referenceNo = new String("00000000000100");
		String amount = new String("100");
		String locale = new String("zh");
		String currCode = new String("344");
		String opCode = new String("00");
		String successURL = new String("http://");
		String failURL = new String("http://");
		String cancelURL = new String("http://");
		String payerEmail = new String("cn.yu.zhao@gmail.com");
		String payFor = new String("a test transaction");
		/* Generate the URL with the Digital Order (DO) encrypted */
		String EncDoURL = null;
		try
		{
		EncDoURL = new String(eplc.genEncDoURL(paymentType,
		referenceNo,
		amount,
		locale,
		currCode,
		opCode,
		successURL,
		failURL,
		cancelURL,
		payerEmail,
		payFor));
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		if (EncDoURL == null || EncDoURL == "")
		{
		/* Gen DO failed, display the last error code */
			System.out.println("Error when generating DO<BR>");
			System.out.println("Last Error is : " + eplc.getLastError() + "<BR>");
		}
		else
		{
		//out.println(EncDoURL);
		/* Prepare to send to DO to ePayLink Host */
		System.setProperty("java.protocol.handler.pkgs",
		"com.sun.net.ssl.internal.www.protocol");
		URL ePayLink = new URL(EncDoURL);
		BufferedReader in = new BufferedReader(new
		InputStreamReader(ePayLink.openStream()));
		String DR;
		DR = in.readLine();
		DRObject dro = eplc.decEncDR(DR);
		System.out.println("DR = " + DR + "<BR>");
		System.out.println("DR Format Correct = " + dro.isMsgFormatCorrect() + "<BR>");
		System.out.println("PaymentType = " + dro.getPaymentType() + "<BR>");
		System.out.println("MerchantID = " + dro.getMerchantID() + "<BR>");
		System.out.println("ReferenceNo = " + dro.getReferenceNo() + "<BR>");
		System.out.println("Amount = " + dro.getAmount() + "<BR>");
		System.out.println("Locale = " + dro.getLocale() + "<BR>");
		System.out.println("CurrCode = " + dro.getCurrCode() + "<BR>");
		System.out.println("OpCode = " + dro.getOpCode() + "<BR>");
		System.out.println("ResponseCode = " + dro.getResponseCode() + "<BR>");
		System.out.println("ePayLinkTxID = " + dro.getePayLinkTxID() + "<BR>");
		in.close();
		}
		}
			}
		}
	}