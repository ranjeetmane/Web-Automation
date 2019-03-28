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
import internal.GlobalVariable
import in.runo.operator.walkin.service.Walkin_With_IndDiscount

public class Walkin_With_Membership extends Walkin_With_IndDiscount{

	@Keyword

	public static void addMembership(String customerNumber, String customerName) {
		customerPersonalDetails(customerNumber, customerName)

		try{

			WebUI.scrollToElement(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_MembershipButton'), 2)
			WebUI.delay(6)
			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_MembershipButton'))

			WebUI.delay(2)

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_SelectMembership_Plan'))


			WebUI.delay(2)

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_ModeOfPayment_DropDown'))

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_SelectMode_Paytm'))


			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_ClickOnSubmit_Button'))

			WebUI.scrollToElement(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_MembershipButton'), 2)

			WebUI.delay(6)
			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_MembershipButton'))

			WebUI.delay(2)

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_SelectMembership_Plan'))


			WebUI.delay(2)

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_ModeOfPayment_DropDown'))

			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_SelectMode_Paytm'))


			WebUI.click(findTestObject('Object Repository/Operator/Walkin/c. Membership/Click_ClickOnSubmit_Button'))
		}catch(Exception E) {

			println " Warning !! Customer has already membership plan, so you can not buy's membership plan for the same customer";


			WebUI.refresh()

			WebUI.delay(2)
		}
	}

	@Keyword
	public static void availMembershipBenefits(String customerNumber, String customerName) {

		customerPersonalDetails(customerNumber, customerName)

		WebUI.delay(1)

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)


		int membershipDiscount = 20;


		String getPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))


		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService.trim().split(" ")[0]);


		String getActMembershipDiscount = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/c. Membership/Avail membership benefits/getText_AppliedMembership_Discount'))

		double actMembershipDiscount = Double.valueOf(getActMembershipDiscount.trim().split(" ")[0]);

		double expMembershipDiscount = (priceOfSelectedService1*membershipDiscount)/100;

		if(actMembershipDiscount==expMembershipDiscount) {
			println " Test Case Passed !! As Membership discount is correct"

			String getActGSTAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getActualGSTAmt'))

			actGSTAmt = Double.valueOf(getActGSTAmt.trim().split(" ")[0]);

			double subTotal = priceOfSelectedService1-expMembershipDiscount;

			double expGSTAmt = subTotal*0.18;

			if(actGSTAmt==expGSTAmt) {
				println " Test Case Passed !! As GST Amount is correct"


				String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

				double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

				double expTotalPayable = subTotal+expGSTAmt;


				if(actTotalPayable==expTotalPayable) {
					println " Test Case Passed !! As Total payable amount is correct"

					String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

					double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

					double expAmountPaid = expTotalPayable;

					if(actAmountPaid==expAmountPaid) {
						println " Test Case Passed !! As Amount paid has updated successfully"


						String getBalance = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_Balance'))

						double actBalanceAmt = Double.valueOf(getBalance.trim().split(" ")[0])

						double expBalanceAmt = expTotalPayable-expAmountPaid;

						if(actBalanceAmt==expBalanceAmt) {
							println " Test Case Passed !! As Balance Amount is correct"

							String getpartialPaid = WebUI.getAttribute(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Input_PartialPaidAmount'),'value')

							double actPartialPaid = Double.valueOf(getpartialPaid);

							double expPartialPaid = expAmountPaid;

							if(actPartialPaid==expPartialPaid) {
								println " Test Case Passed !! As partial Paid Amount has updated successfully"

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_SelectPaymentMode_DropDown'))

								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_selectMode_Cash'))


								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Submit_Walkin'))


								WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/Click_Create_New_Walkin'))


								println " Congratulations !! Test Case Passed with Membership Benefits";
							} else{
								println " Test Case Failed !! As partial Paid amount is not updated successfully"
							}
						} else{
							println " Test Case Failed !! As Balance Amount is not correct"
						}
					} else{
						println " Test Case Failed !! As amount paid is not updated"
					}
				} else {
					println " Test Case Failed !! As Total Payable amount is not correct"
				}
			} else{
				println " Test Case Failed !! As GST amount is not correct"
			}
		} else{
			println " Test Case Failed !! As Membership discount is not correct"
		}
	}

	@Keyword
	public static void availMembershipBenefits_Without_GST(String customerNumber, String customerName) {

		customerPersonalDetails(customerNumber, customerName)
		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Service_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select_serviceName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Employee_DropDown_List'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_Select-EmployeeName'))

		WebUI.click(findTestObject('Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_AddSummary'))

		WebUI.delay(1)


		int membershipDiscount = 20;



		WebUI.click(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/Click_UnCheck_GST'))


		String getPriceOfSelectedService = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_ServicePrice1'))


		priceOfSelectedService1 = Integer.parseInt(getPriceOfSelectedService.trim().split(" ")[0]);


		String getActMembershipDiscount = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/c. Membership/Avail membership benefits/getText_AppliedMembership_Discount'))

		double actMembershipDiscount = Double.valueOf(getActMembershipDiscount.trim().split(" ")[0]);

		double expMembershipDiscount = (priceOfSelectedService1*membershipDiscount)/100;

		if(actMembershipDiscount==expMembershipDiscount) {
			println " Test Case Passed !! As Membership discount is correct"

			String getActGSTAmt = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getActualGSTAmt'))

			actGSTAmt = Double.valueOf(getActGSTAmt.trim().split(" ")[0]);

			double subTotal = priceOfSelectedService1-expMembershipDiscount;

			double expGSTAmt = 0;



			if(actGSTAmt==expGSTAmt) {

				println " Test Case Passed !! As GST amount is correct"
				String getTotalPayable = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_getTotalPayable'))

				double actTotalPayable = Double.valueOf(getTotalPayable.trim().split(" ")[0]);

				double expTotalPayable = subTotal+expGSTAmt;

				if(actTotalPayable==expTotalPayable) {
					println " Test Case Passed !! As Total Payable amount is correct";

					String getAmountPaid = WebUI.getText(findTestObject('Object Repository/Operator/Walkin/a. Walkin_With_IndDiscount/SummaryValidation Object/getText_AmountPaid'))

					double actAmountPaid = Double.valueOf(getAmountPaid.trim().split(" ")[0]);

					double expAmountPaid = expTotalPayable;


					if(actAmountPaid==expAmountPaid) {
						println " Test Case Passed !! As Amount paid is correct"

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


								println " Congratulation !! Test Case Passed with Membership Benefits and Without GST "
							} else{
								println " Test Case Passed !! As Partial Paid amount is not updated successfully"
							}
						} else{

							println " Test Case Failed !! As Balance Amount is not correct"
						}
					} else{

						println " Test Case Failed !! As Amount paid is not correct"
					}
				}else{

					println " Test Case Failed !! As Total Payable amount is not correct";
				}
			} else{

				println " Test Case Failed !! As GST amount is not correct"
			}
		}
	}
}