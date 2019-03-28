package in.runo.operator.walkin.services

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

public class Walkin_With_IndDisc_And_MultipleService extends Walkin_With_IndDiscount{

	public static double priceOfSelectedService2;

	public static void createWalkinWithIndividualDiscountWithoutGST(String customerNumber, String customerName, String indDiscount) {
		customerPersonalDetails(customerNumber, customerName)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_Individual_Discount'),
				indDiscount)

		WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddButton_For_Multiple_Service'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.delay(2)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_Individual_Discount'),
				indDiscount)

		WebUI.delay(2)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)

		String getPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))

		println" price of service is :"+getPriceOfSelectedService

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService.trim().split(" ")[0]);

		println" int price of service is :"+priceOfSelectedService1

		WebUI.scrollToPosition(600, 0)

		WebUI.delay(2)

		String getProvidedDiscountInPercent = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getProvidedDiscountInPercent'), 'value'
				)
		WebUI.scrollToPosition(0, 600)

		int providedIndDiscount = Integer.parseInt(getProvidedDiscountInPercent);

		String getActAppliedIndDiscount = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getAppliedIndividualDiscount'))

		actAppliedDiscount = Double.valueOf(getActAppliedIndDiscount.trim().split(" ")[0]);

		double expAppliedDiscount = (priceOfSelectedService1*providedIndDiscount)/100;


		if(actAppliedDiscount==expAppliedDiscount) {
			println " Test Case Passed !! As Individual discount is matched with expected individual discount"

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_UnCheck_GST'))
		} else {
		}
	}
}