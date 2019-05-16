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

public class Walkin_With_EditingServiceQuantity extends Walkin_With_IndDiscount{

	@Keyword

	public static void walkinWithEditingServiceQuantity(String customerNumber, String customerName) {
		customerPersonalDetails(customerNumber, customerName)

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.delay(2)

		WebUI.clearText(findTestObject('Object Repository/Operator/Walkin/e. Editing Service Quantity/getText_ServiceQuantity'))

		WebUI.setText(findTestObject('Object Repository/Operator/Walkin/e. Editing Service Quantity/getText_ServiceQuantity'), '4')

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.delay(2)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))
		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)

		WebUI.scrollToPosition(600, 0)

		String getServicePrice = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/d. Walkin_With_EditingServicePrice/Input_getText_ServicePrice1'),'value')

		int servicePrice = Integer.parseInt(getServicePrice.trim().split(" ")[0]);

		String getQuantityOfService = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/e. Editing Service Quantity/getText_ServiceQuantity'),'value')

		int quantityOfService = Integer.parseInt(getQuantityOfService);

		String getActPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))

		int actPriceOfSelectedService1 = Integer.parseInt(getActPriceOfSelectedService.trim().split(" ")[0]);

		int expPriceOfSelectedService1 = servicePrice*quantityOfService;

		WebUI.scrollToPosition(0, 600)
		if(actPriceOfSelectedService1==expPriceOfSelectedService1) {
			println " Test Case Passed !! As Price Of Service is calulated correctly"

			String getActGSTAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getActualGSTAmt'))

			actGSTAmt = Double.valueOf(getActGSTAmt.trim().split(" ")[0]);

			double expGSTAmt = expPriceOfSelectedService1*0.18;

			if(actGSTAmt==expGSTAmt) {
				println " Test Case Passed !! As GST Amount is correct"

				String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

				double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

				double expTotalPayable = expPriceOfSelectedService1+expGSTAmt;

				if(actTotalPayable==expTotalPayable) {

					println " Test Case Passed !! As Total Payable Amount is correct"
					String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

					double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

					double expAmountPaid = 0;

					if(actAmountPaid==expAmountPaid) {
						println " Test Case Passed !! As Amount Paid is correct"

						String getBalance = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))

						double actBalanceAmt = Double.valueOf(getBalance.trim().split(" ")[0])

						double expBalanceAmt = expTotalPayable;

						if(actBalanceAmt==expBalanceAmt) {
							println " Test Case Passed !! As Balance Amount is correct"
							String getpartialPaid = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_PartialPaidAmount'),'value')

							double actPartialPaid = Double.valueOf(getpartialPaid);

							double expPartialPaid = expTotalPayable;

							if(actPartialPaid==expPartialPaid) {
								println " Test Case Passed !! As Partial Paid Amount is updated"

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_SelectPaymentMode_DropDown'))

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_selectMode_Cash'))

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_On_ADDButtonToAddModesOfPayment'))

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))


								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))


								println " Congratulations !! Test Case Passed with Multiple Quantity Of Service";
							} else{
								println " Test Case Failed !! As Partial Paid Amount is not updated"
							}
						} else{
							println " Test Case Failed !! As Balance Amount is not correct"
						}
					} else{
						println " Test Case Failed !! As Amount Paid is not correct"
					}
				} else{
					println " Test Case Failed !! As Total Payable Amount is not correct"
				}
			} else{
				println " Test Case Failed !! As GST Amount is not correct"
			}
		} else {
			println " Test Case Failed !! As price Of service is not calculated correctly for multiple Quantity for the same service"
		}
	}

	@Keyword

	public static void walkinWithEditingServiceQuantity_And_Without_GST(String customerNumber, String customerName) {
		customerPersonalDetails(customerNumber, customerName)

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.delay(2)

		WebUI.clearText(findTestObject('Object Repository/Operator/Walkin/e. Editing Service Quantity/getText_ServiceQuantity'))

		WebUI.setText(findTestObject('Object Repository/Operator/Walkin/e. Editing Service Quantity/getText_ServiceQuantity'), '4')

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.delay(2)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))
		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)

		WebUI.scrollToPosition(600, 0)

		String getServicePrice = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/d. Walkin_With_EditingServicePrice/Input_getText_ServicePrice1'),'value')

		int servicePrice = Integer.parseInt(getServicePrice.trim().split(" ")[0]);

		String getQuantityOfService = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/e. Editing Service Quantity/getText_ServiceQuantity'),'value')

		int quantityOfService = Integer.parseInt(getQuantityOfService);

		String getActPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))

		int actPriceOfSelectedService1 = Integer.parseInt(getActPriceOfSelectedService.trim().split(" ")[0]);

		int expPriceOfSelectedService1 = servicePrice*quantityOfService;

		WebUI.scrollToPosition(0, 600)
		if(actPriceOfSelectedService1==expPriceOfSelectedService1) {
			println " Test Case Passed !! As Price Of Service is calulated correctly"

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_UnCheck_GST'))

			String getActGSTAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getActualGSTAmt'))

			actGSTAmt = Double.valueOf(getActGSTAmt.trim().split(" ")[0]);

			double expGSTAmt = 0.0;

			if(actGSTAmt==expGSTAmt) {
				println " Test Case Passed !! As GST Amount is correct"

				String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

				double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

				double expTotalPayable = expPriceOfSelectedService1+expGSTAmt;

				if(actTotalPayable==expTotalPayable) {

					println " Test Case Passed !! As Total Payable Amount is correct"
					String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

					double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

					double expAmountPaid = 0;

					if(actAmountPaid==expAmountPaid) {
						println " Test Case Passed !! As Amount Paid is correct"

						String getBalance = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))

						double actBalanceAmt = Double.valueOf(getBalance.trim().split(" ")[0])

						double expBalanceAmt = expTotalPayable;

						if(actBalanceAmt==expBalanceAmt) {
							println " Test Case Passed !! As Balance Amount is correct"
							String getpartialPaid = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_PartialPaidAmount'),'value')

							double actPartialPaid = Double.valueOf(getpartialPaid);

							double expPartialPaid = expTotalPayable;

							if(actPartialPaid==expPartialPaid) {
								println " Test Case Passed !! As Partial Paid Amount is updated"

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_SelectPaymentMode_DropDown'))

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_selectMode_Cash'))

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_On_ADDButtonToAddModesOfPayment'))

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))

								println " Congratulations !! Test Case Passed with Multiple Quantity Of Service";
							} else{
								println " Test Case Failed !! As Partial Paid Amount is not updated"
							}
						} else{
							println " Test Case Failed !! As Balance Amount is not correct"
						}
					} else{
						println " Test Case Failed !! As Amount Paid is not correct"
					}
				} else{
					println " Test Case Failed !! As Total Payable Amount is not correct"
				}
			} else{
				println " Test Case Failed !! As GST Amount is not correct"
			}
		} else {
			println " Test Case Failed !! As price Of service is not calculated correctly for multiple Quantity for the same service"
		}
	}
}


