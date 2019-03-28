package in.runo.operator.walkin.service

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

import internal.GlobalVariable

public class Walkin_With_EditingServicePrice extends Walkin_With_IndDiscount{


	@Keyword

	public static void walkinWithEditingServicePrice(String customerNumber, String customerName) {

		customerPersonalDetails(customerNumber, customerName)

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))


		WebUI.clearText(findTestObject('Object Repository/Operator/Walkin/d. Walkin_With_EditingServicePrice/Input_getText_ServicePrice1'))
		WebUI.setText(findTestObject('Object Repository/Operator/Walkin/d. Walkin_With_EditingServicePrice/Input_getText_ServicePrice1'), '5000')
		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)
		String getPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))
		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService.trim().split(" ")[0]);

		int editedServicePrice = 5000;


		String getActGSTAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getActualGSTAmt'))

		actGSTAmt = Double.valueOf(getActGSTAmt.trim().split(" ")[0]);

		double expGstAmt = 0;

		if(actGSTAmt==expGstAmt) {
			println " Test Case Passed !! As Gst amount is correct "

			String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

			double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

			double expTotalPayable = editedServicePrice+expGstAmt;

			if(actTotalPayable==expTotalPayable) {
				println " Test Case Passed !! As Total Payable Amount is correct"

				String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

				double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

				double expAmountPaid = expTotalPayable;

				if(actAmountPaid==expAmountPaid) {
					println " Test Case Passed !! As Amount Paid is correct"
					String getBalance = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))

					double actBalanceAmt = Double.valueOf(getBalance.trim().split(" ")[0])

					double expBalanceAmt = expTotalPayable-expAmountPaid;

					if(actBalanceAmt==expBalanceAmt) {
						println " Test Case Passed !! As Balance Amount is correct"

						String getpartialPaid = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_PartialPaidAmount'),'value')

						double actPartialPaid = Double.valueOf(getpartialPaid);

						double expPartialPaid = expAmountPaid;

						if(actPartialPaid==expPartialPaid) {
							println " Test Case Passed !! As Partial Paid amount is updated successfully"

							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_SelectPaymentMode_DropDown'))

							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_selectMode_Cash'))


							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))


							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))


							println " Congratulations !! Test Case Passed with Edited Service Price";
						} else{
							println " Test Case Failed !! As Partial Paid amount is not updated  successfully"
						}
					} else{
						println " Test Case Failed !! As Balance Amount is not correct"
					}
				} else{

					println " Test Case Failed !! As Amount Paid is  not correct"
				}
			} else{

				println " Test Case Failed !! As Total Payable Amount is not correct"
			}
		} else{
			println " Test Case Failed !! As Gst Amount is not correct"
		}
	}

	@Keyword

	public static void walkinWithEditingServicePrice_WithoutGst(String customerNumber, String customerName) {
		customerPersonalDetails(customerNumber, customerName)

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))


		WebUI.clearText(findTestObject('Object Repository/Operator/Walkin/d. Walkin_With_EditingServicePrice/Input_getText_ServicePrice1'))
		WebUI.setText(findTestObject('Object Repository/Operator/Walkin/d. Walkin_With_EditingServicePrice/Input_getText_ServicePrice1'), '5000')
		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)
		String getPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))
		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService.trim().split(" ")[0]);

		WebUI.scrollToElement(findTestObject('Object Repository/Operator/Walkin/d. Walkin_With_EditingServicePrice/Input_getText_ServicePrice1'), 2)

		int editedServicePrice = 5000;

		WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_UnCheck_GST'))

		String getActGSTAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getActualGSTAmt'))

		actGSTAmt = Double.valueOf(getActGSTAmt.trim().split(" ")[0]);

		if(actGSTAmt==0) {
			println " Test Case Passed !! As Gst amount is correct "

			String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

			double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

			double expTotalPayable = editedServicePrice;

			if(actTotalPayable==expTotalPayable) {
				println " Test Case Passed !! As Total Payable Amount is correct"

				String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

				double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

				double expAmountPaid = expTotalPayable;

				if(actAmountPaid==expAmountPaid) {
					println " Test Case Passed !! As Amount Paid is correct"
					String getBalance = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))

					double actBalanceAmt = Double.valueOf(getBalance.trim().split(" ")[0])

					double expBalanceAmt = expTotalPayable-expAmountPaid;

					if(actBalanceAmt==expBalanceAmt) {
						println " Test Case Passed !! As Balance Amount is correct"

						String getpartialPaid = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_PartialPaidAmount'),'value')

						double actPartialPaid = Double.valueOf(getpartialPaid);

						double expPartialPaid = expAmountPaid;

						if(actPartialPaid==expPartialPaid) {
							println " Test Case Passed !! As Partial Paid amount is updated successfully"

							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_SelectPaymentMode_DropDown'))

							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_selectMode_Cash'))


							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))


							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))


							println " Congratulations !! Test Case Passed with Edited Service Price";
						} else{
							println " Test Case Failed !! As Partial Paid amount is not updated  successfully"
						}
					} else{
						println " Test Case Failed !! As Balance Amount is not correct"
					}
				} else{

					println " Test Case Failed !! As Amount Paid is  not correct"
				}
			} else{

				println " Test Case Failed !! As Total Payable Amount is not correct"
			}
		} else{
			println " Test Case Failed !! As Gst Amount is not correct"
		}
	}
}
