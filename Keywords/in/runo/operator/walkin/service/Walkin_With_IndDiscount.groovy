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

public class Walkin_With_IndDiscount {

	public static int priceOfSelectedService1;
	public static double actAppliedDiscount;
	public static double actGSTAmt;

	@Keyword

	public static void customerPersonalDetails(String customerNumber, String customerName) {

		WebUI.delay(1)

		WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/Input_mobileNumber'), customerNumber)

		try{

			WebUI.delay(3)
			println " customer is an existing customer"
			WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_existingCustomer'))
		} catch(Exception e) {


			println " customer is new customer"

			WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/Input_customerName'), customerName)

			WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/input_MailAddress'), 'katalonmail@gmail.com')

			WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/Click_BirthDate'), FailureHandling.STOP_ON_FAILURE)

			WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/Click_SelectDateOfBirth'))

			WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/Click_SelectFemaleGender'))

			WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/Input_FlatNo'), 'House No: 123')

			WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/Input_cityName'), 'Latur')

			WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/Input_PinCode'), '585416')

			WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/Input_Notes_Text-Field'), 'Regular Customer')
		}
	}

	@Keyword

	public static void createWalkinWithIndividualDiscountWithGST(String customerNumber, String customerName, String indDiscount) {
		customerPersonalDetails(customerNumber, customerName)

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_Individual_Discount'),
				indDiscount)

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

		WebUI.delay(1)

		int providedIndDiscount = Integer.parseInt(getProvidedDiscountInPercent);

		String getActAppliedIndDiscount = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getAppliedIndividualDiscount'))

		actAppliedDiscount = Double.valueOf(getActAppliedIndDiscount.trim().split(" ")[0]);

		double expAppliedDiscount = (priceOfSelectedService1*providedIndDiscount)/100;


		if(actAppliedDiscount==expAppliedDiscount) {
			println " Test Case Passed !! As Individual discount is matched with expected individual discount"

			String getActGSTAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getActualGSTAmt'))

			actGSTAmt = Double.valueOf(getActGSTAmt.trim().split(" ")[0]);



			double subTotal = priceOfSelectedService1-expAppliedDiscount;

			double expGSTAmt = subTotal*0.18;


			if(actGSTAmt==expGSTAmt) {
				println " Test Case Passed !! As GST amount is matched with expected GST amount ";

				String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

				double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

				double expTotalPayable = subTotal+expGSTAmt;



				if(actTotalPayable==expTotalPayable) {
					println " Test Case Passed !! As Total payable amount is matched with expected payable amount"

					String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

					double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

					double expAmountPaid = expTotalPayable;


					if(actAmountPaid==expAmountPaid) {

						println " Test Case Passed !! As Amount paid is taking correcly"


						String getBalance = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))

						double actBalanceAmt = Double.valueOf(getBalance.trim().split(" ")[0])

						double expBalanceAmt = expTotalPayable-expAmountPaid;


						if(actBalanceAmt==expBalanceAmt) {
							println " Test Case Passed !! As Balance Amount is correct"
							String getpartialPaid = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_PartialPaidAmount'),'value')

							double actPartialPaid = Double.valueOf(getpartialPaid);

							double expPartialPaid = expAmountPaid;

							if(actPartialPaid==expPartialPaid) {
								println " Test Case Passed !! As Partial Paid Amount is updated automatically"

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_SelectPaymentMode_DropDown'))

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_selectMode_Cash'))


								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))


								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))


								println " Congratulations !! Test Case Passed with Individual Discount";
							} else {
								println " Test Case Failed !! As Balance Amount is not correct"
							}
						}else{
							println " Test Case Failed !! As Partial Paid Amount is not updated automatically"
						}
					} else {
						println " Test Case Failed !! As Amount paid is not taking correctly"
					}
				}
				else {

					println " Test Case Failed !! As Total Payable amount is not matched with expected payable amount"
				}
			}else {
				println " Test Case Failed !! As GST amount is not matched with expected GST amount ";
			}
		}else {
			println " Test Case Failed !! As Individual discount is not matched with expected individual discount, Ind discount should be "+expAppliedDiscount+" instead of "+actAppliedDiscount
		}
	}


	@Keyword
	public static void createWalkinWithIndividualDiscountWithoutGST(String customerNumber, String customerName, String indDiscount) {
		customerPersonalDetails(customerNumber, customerName)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.setText(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_Individual_Discount'),
				indDiscount)


		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))


		String getPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))

		println" price of service is :"+getPriceOfSelectedService

		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService.trim().split(" ")[0]);

		println" int price of service is :"+priceOfSelectedService1

		WebUI.scrollToPosition(600, 0)

		WebUI.delay(2)

		String getProvidedDiscountInPercent = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getProvidedDiscountInPercent'), 'value'
				)

		int providedIndDiscount = Integer.parseInt(getProvidedDiscountInPercent);

		println " ** Prvided Dic is :"+providedIndDiscount;
		WebUI.scrollToPosition(0, 600)



		String getActAppliedIndDiscount = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getAppliedIndividualDiscount'))

		actAppliedDiscount = Double.valueOf(getActAppliedIndDiscount.trim().split(" ")[0]);

		double expAppliedDiscount = (priceOfSelectedService1*providedIndDiscount)/100;


		if(actAppliedDiscount==expAppliedDiscount) {
			println " Test Case Passed !! As Individual discount is matched with expected individual discount"


			WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_UnCheck_GST'))

			String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

			double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

			double subTotal = priceOfSelectedService1-expAppliedDiscount;

			double expGSTAmt = subTotal*0.18;

			double expTotalPayable = subTotal;

			if(actTotalPayable==expTotalPayable) {
				println " Test Case Passed !! As Total payable amount without gst is correct"

				String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

				double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

				double expAmountPaid = expTotalPayable;

				if(actAmountPaid==expTotalPayable) {
					println " Test Case Passed !! As Amount paid without GST is correct"

					String getBalance = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))

					double actBalanceAmt = Double.valueOf(getBalance.trim().split(" ")[0])

					double expBalanceAmt = expTotalPayable-expAmountPaid;

					if(actBalanceAmt==expBalanceAmt) {
						println " Test Case Passed !! As Balance Amount without GST is correct"

						String getpartialPaid = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_PartialPaidAmount'),'value')

						double actPartialPaid = Double.valueOf(getpartialPaid);

						double expPartialPaid = expAmountPaid;

						if(actPartialPaid==expPartialPaid) {
							println " Test Case Passed !! As Partial Paid without GST amount is correct"

							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_SelectPaymentMode_DropDown'))

							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_selectMode_Cash'))


							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))


							WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))


							println " Congratulations !! Test Case Passed with Individual Discount and without GST ";
						} else {
							println " Test Case Failed !! As Partial Paid without GST is not correct"
						}
					} else{
						println " Test Case Failed !! As Balance Amount without GST is not correct"
					}
				} else{

					println " Test Case Failed !! As Amount paid without GST is not correct"
				}
			} else{
				println " Test Case failed !! As total payable amount without GST isn't correct"
			}
		} else {
		}
	}
}