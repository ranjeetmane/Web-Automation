package in.runo.operator.walkin.membership

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

public class Walkin_With_OverallDiscount_And_MembershipDiscount extends Walkin_With_IndDiscount{


	@Keyword

	public static void createWalkinWithOverallAndMembershipDiscount(String customerNumber, String customerName, String overallDiscount) {
		customerPersonalDetails(customerNumber, customerName)

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)

		WebUI.click(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/Click_Overall_DiscountButton'))


		WebUI.setText(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/Input_OverallDiscount_In_Percentage'), overallDiscount)

		WebUI.click(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/Click_ApplyOverall_Discount'))

		String getProvidedOverallDisc = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/getText_ProvidedOverall_Discount_In_Percentage'), 'value')

		println " Discount in percentage :"+getProvidedOverallDisc;

		int actProvidedOverallDisc = Integer.parseInt(getProvidedOverallDisc);

		String getPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService.trim().split(" ")[0]);

		println " servuce price is :"+priceOfSelectedService1;

		String getActGSTAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getActualGSTAmt'))

		String getActMembershipDiscount = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/c. Membership/Avail membership benefits/getText_AppliedMembership_Discount'))

		actGSTAmt = Double.valueOf(getActGSTAmt.trim().split(" ")[0]);

		double MembershipDiscount = Double.valueOf(getActMembershipDiscount.trim().split(" ")[0]);

		String getActAppliedOverallDiscount = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/getText_Applied_Overall_Discount'))


		double OverallDiscount = Double.valueOf(getActAppliedOverallDiscount.trim().split(" ")[0])

		double subTotal = (priceOfSelectedService1-MembershipDiscount)-OverallDiscount;
		double expGstAmt = subTotal*0.18;

		if(actGSTAmt==expGstAmt ) {
			println " Test Case Passed !! As GST Amount is correct"
		} else{
			println " Test Case Failed !! As GST Amount is not correct"
		}
		String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

		double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

		double expTotalPayable = subTotal+expGstAmt;

		if(actTotalPayable==expTotalPayable) {

			println " Test Case Passed !! As Total Payable Amount is correct"

			String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

			double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

			double expAmountPaid = expTotalPayable;

			if(actAmountPaid==expAmountPaid) {
				println " Test Case Passed !! As amount paid is correct"

				String getBalance = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))

				double actBalanceAmt = Double.valueOf(getBalance.trim().split(" ")[0])

				double expBalanceAmt = expTotalPayable-expAmountPaid;

				if(actBalanceAmt==expBalanceAmt) {
					println " Test Case Passed !! As Balance Amount is correct"

					String getpartialPaid = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_PartialPaidAmount'),'value')

					double actPartialPaid = Double.valueOf(getpartialPaid);

					double expPartialPaid = expAmountPaid;

					if(actPartialPaid==expPartialPaid) {
						println " Test Case Passed !! As Partial Paid Amount is not correct "

						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_SelectPaymentMode_DropDown'))

						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_selectMode_Cash'))


						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))


						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))


						println " Congratulation !! Test Case Passed with Membership Discount and Overall Discount "
					} else{
						println " Test Case Failed !! As Partial Paid amount is not correct"
					}
				} else{
					println " Test Case Failed !! As Balance Amount is not correct"
				}
			} else{

				println " Test Case Failed !! As Amount paid is not correct"
			}
		} else{

			println " Test Case Failed !! As Total Payable amount is not correct"
		}
	}

	@Keyword

	public static void walkinWithOverallAndMembershipDiscount_WithoutGst(String customerNumber, String customerName, String overallDiscount) {

		customerPersonalDetails(customerNumber, customerName)

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)

		WebUI.click(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/Click_Overall_DiscountButton'))


		WebUI.setText(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/Input_OverallDiscount_In_Percentage'), overallDiscount)


		
		
		
		WebUI.click(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/Click_ApplyOverall_Discount'))

		String getProvidedOverallDisc = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/getText_ProvidedOverall_Discount_In_Percentage'), 'value')

		println " Discount in percentage :"+getProvidedOverallDisc;

		int actProvidedOverallDisc = Integer.parseInt(getProvidedOverallDisc);

		String getPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService.trim().split(" ")[0]);

		println " servuce price is :"+priceOfSelectedService1;

		WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_UnCheck_GST'))
		String getActMembershipDiscount = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/c. Membership/Avail membership benefits/getText_AppliedMembership_Discount'))

		String getActGSTAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getActualGSTAmt'))

		actGSTAmt = Double.valueOf(getActGSTAmt.trim().split(" ")[0]);

		double MembershipDiscount = Double.valueOf(getActMembershipDiscount.trim().split(" ")[0]);

		String getActAppliedOverallDiscount = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/b. Walkin_With_Overall_Discount/getText_Applied_Overall_Discount'))


		double OverallDiscount = Double.valueOf(getActAppliedOverallDiscount.trim().split(" ")[0])

		double subTotal = (priceOfSelectedService1-MembershipDiscount)-OverallDiscount;
		double expGstAmt = 0;
		String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

		double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

		double expTotalPayable = subTotal+expGstAmt;

		if(actGSTAmt==expGstAmt) {
			println " Test Case Passed !! As GST amount is correct"
		} else{

			println " Test Case Failed !! As GST amount is not correct"
		}


		if(actTotalPayable==expTotalPayable) {

			println " Test Case Passed !! As Total Payable Amount is correct"

			String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

			double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

			double expAmountPaid = expTotalPayable;

			if(actAmountPaid==expAmountPaid) {
				println " Test Case Passed !! As amount paid is correct"

				String getBalance = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))

				double actBalanceAmt = Double.valueOf(getBalance.trim().split(" ")[0])

				double expBalanceAmt = expTotalPayable-expAmountPaid;

				if(actBalanceAmt==expBalanceAmt) {
					println " Test Case Passed !! As Balance Amount is correct"

					String getpartialPaid = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_PartialPaidAmount'),'value')

					double actPartialPaid = Double.valueOf(getpartialPaid);

					double expPartialPaid = expAmountPaid;

					if(actPartialPaid==expPartialPaid) {
						println " Test Case Passed !! As Partial Paid Amount is not correct "

						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_SelectPaymentMode_DropDown'))

						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_selectMode_Cash'))


						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))


						WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))


						println " Congratulation !! Test Case Passed with Membership Discount and Overall Discount "
					} else{
						println " Test Case Failed !! As Partial Paid amount is not correct"
					}
				} else{
					println " Test Case Failed !! As Balance Amount is not correct"
				}
			} else{

				println " Test Case Failed !! As Amount paid is not correct"
			}
		} else{

			println " Test Case Failed !! As Total Payable amount is not correct"
		}
	}
}
