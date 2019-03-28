package in.runo.operator.walkin.credit

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import in.runo.operator.walkin.service.Walkin_With_IndDiscount
import internal.GlobalVariable

public class Walkin_With_CreditAmount extends Walkin_With_IndDiscount{

	@Keyword

	public static void addCreditsAmount(String customerNumber, String customerName, String creditValue) {
		Thread.sleep(1000)
		customerPersonalDetails(customerNumber, customerName)
		WebUI.scrollToPosition(0, 600)

		try{
			WebUI.delay(1)
			WebUI.click(findTestObject('Operator/Walkin/f. Credits/Click_Add_Credits_Button'))

			WebUI.setText(findTestObject('Object Repository/Operator/Walkin/f. Credits/Input_CreditAmount'), creditValue)

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/f. Credits/Click_Add_Credit_Yes_Button'))

			String CreditAmt = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/f. Credits/getText_getCreditAmopunt_On_Summary_Page'),'value')

			println " Credit Amount is : "+CreditAmt;

			int  creditAmount= Integer.parseInt(CreditAmt);


			String amtPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/f. Credits/getText_getAmountPaid'))

			println " Amount Paid is : "+amtPaid;

			int amountPaid = Integer.parseInt(amtPaid.trim().split(" ")[0])

			if(creditAmount==amountPaid){

				println " Test Case Passed !! Credit Amount and Amount Paid is equals"

				WebUI.click(findTestObject('Object Repository/Operator/Walkin/f. Credits/Click_On DropDownOfPaymentMode'))

				WebUI.click(findTestObject('Object Repository/Operator/Walkin/f. Credits/Click_Google Pay Mode'))

				WebUI.click(findTestObject('Object Repository/Operator/Walkin/f. Credits/Click_Submit'))

				WebUI.click(findTestObject('Object Repository/Operator/Walkin/f. Credits/Click_Create_New_Walkin'))
			} else{
				println " Test Case Failed !! As Credit Amount and Amount Paid is not equals "
			}
		}catch(Exception E){

			println " Warning !! Customer is a new customer so user is not able to add credit value in customer's account"
		}
	}


	@Keyword

	public static void usingCreditsValueForBilling(String customerNumber, String customerName){

		Walkin_With_CreditAmount.addCreditsAmount(customerNumber, customerName, '3540')

		customerPersonalDetails(customerNumber, customerName)

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)

		String getCreditValue = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/f. Credits/getText_CreditValue'))

		println " Actual Credit Amount is :"+getCreditValue;

		double availableCreditValue = Double.valueOf(getCreditValue);

		String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

		String totalPaybleAmt = getTotalPayable.trim().split(" ")[0];
		double totalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

		if(availableCreditValue>=totalPayable) {
			println " Test Case Passed !! As Available Credit Amount is sufficient for billing";

			WebUI.waitForElementPresent(findTestObject('Object Repository/Operator/Walkin/f. Credits/Input_Avail_Credit_Amount'), 3)
			WebUI.click(findTestObject('Object Repository/Operator/Walkin/f. Credits/Input_Avail_Credit_Amount'))

			WebUI.setText(findTestObject('Object Repository/Operator/Walkin/f. Credits/Input_Avail_Credit_Amount'), totalPaybleAmt)

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/f. Credits/Click_RedeemCredit_Value'))

			WebUI.delay(1)

			String getRedeemedCreditValue = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/f. Credits/getText_Get_Redeemed_Amount_From-SummaryPage'))
			println " Redeemed Credit Value :"+getRedeemedCreditValue;

			double reedmedCreditvalue = Double.valueOf(getRedeemedCreditValue.trim().split(" ")[1]);

			println " credit value : "+reedmedCreditvalue;

			String getActualAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

			println " amount paid :"+getActualAmountPaid

			double actAmountPaid = Double.valueOf(getActualAmountPaid.trim().split(" ")[0])
			double expAmountPaid = totalPayable-reedmedCreditvalue;

			if(actAmountPaid==expAmountPaid) {
				println " Test Case Passed !! As Amount Paid is correct"

				String getActBalanceAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))
				double actBalanceAmt = Double.valueOf(getActBalanceAmt.trim().split(" ")[0])
				double expBalanceAmt = totalPayable-reedmedCreditvalue;
				if(actBalanceAmt==expBalanceAmt) {
					println " Test Case Passed !! As Balance Amount is correct "
					WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))
					WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))
					println " Congratulations !! Credit Amount is successfully used for avail services  ";
				} else{
					println " Test Case Failed !! As Balance Amount is correct"
				}
			} else{
				println " Test Case Failed !! As Amount Paid is not correct"
			}
		} else{
			println "Test Case Failed !! As Available credit is insufficent for billing. required credit amount is :"+totalPayable;
		}
	}
	
	@Keyword
	
		public static void usingCreditsValueForBilling_Without_GST(String customerNumber, String customerName){
	
			Walkin_With_CreditAmount.addCreditsAmount(customerNumber, customerName, '3540')
	
			customerPersonalDetails(customerNumber, customerName)
	
			WebUI.delay(1)
	
			WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))
	
			WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))
	
			WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))
	
			WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))
	
			WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))
	
			WebUI.delay(1)
	
			String getCreditValue = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/f. Credits/getText_CreditValue'))
	
			println " Actual Credit Amount is :"+getCreditValue;
	
			double availableCreditValue = Double.valueOf(getCreditValue);
	
			String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))
	
			String totalPayableAmt = getTotalPayable.trim().split(" ")[0];
			double totalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);
	
			if(availableCreditValue>=totalPayable) {
				println " Test Case Passed !! As Available Credit Amount is sufficient for billing";
	
				WebUI.waitForElementPresent(findTestObject('Object Repository/Operator/Walkin/f. Credits/Input_Avail_Credit_Amount'), 3)
				WebUI.click(findTestObject('Object Repository/Operator/Walkin/f. Credits/Input_Avail_Credit_Amount'))
	
				WebUI.setText(findTestObject('Object Repository/Operator/Walkin/f. Credits/Input_Avail_Credit_Amount'), totalPayableAmt)
	
				WebUI.click(findTestObject('Object Repository/Operator/Walkin/f. Credits/Click_RedeemCredit_Value'))
	
				WebUI.delay(1)
	
				String getRedeemedCreditValue = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/f. Credits/getText_Get_Redeemed_Amount_From-SummaryPage'))
				println " Redeemed Credit Value :"+getRedeemedCreditValue;
	
				double reedmedCreditvalue = Double.valueOf(getRedeemedCreditValue.trim().split(" ")[1]);
	
				println " credit value : "+reedmedCreditvalue;
	
				String getActualAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))
	
				println " amount paid :"+getActualAmountPaid
	
				double actAmountPaid = Double.valueOf(getActualAmountPaid.trim().split(" ")[0])
				double expAmountPaid = totalPayable-reedmedCreditvalue;
	
				if(actAmountPaid==expAmountPaid) {
					println " Test Case Passed !! As Amount Paid is correct"
	
					String getActBalanceAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))
					double actBalanceAmt = Double.valueOf(getActBalanceAmt.trim().split(" ")[0])
					double expBalanceAmt = totalPayable-reedmedCreditvalue;
					if(actBalanceAmt==expBalanceAmt) {
						println " Test Case Passed !! As Balance Amount is correct "
						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))
						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))
						println " Congratulations !! Credit Amount is successfully used for avail services  ";
					} else{
						println " Test Case Failed !! As Balance Amount is correct"
					}
				} else{
					println " Test Case Failed !! As Amount Paid is not correct"
				}
			} else{
				println "Test Case Failed !! As Available credit is insufficent for billing. required credit amount is :"+totalPayable;
			}
		}
	
}

