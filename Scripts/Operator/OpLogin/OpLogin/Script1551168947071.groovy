import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('http://13.126.72.115:4200/')

WebUI.maximizeWindow(FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/div_Operator'))

Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng'), '8830175062')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_1'), 
    '0VuygnA+GFY=')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_2'), 
    '4QsYikuMnfc=')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_3'), 
    'uyqgJ3PclbQ=')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_4'), 
    '/yoJQMt7SF0=')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_5'), 
    '2wIR5rizw6I=')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_6'), 
    'xcsEsceOxMQ=')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_7'), 
    '0r6XmuTReV8=')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_8'), 
    'wEDVojD9906R+kXWuM9ihA==')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_9'), 
    'wEDVojD9906x1g0B3VxO5g==')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_10'), 
    'wEDVojD9905YSeMDfDCy9A==')

WebUI.setEncryptedText(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_form-control ng_11'), 
    'wEDVojD9905YSeMDfDCy9A==')

WebUI.click(findTestObject('Object Repository/Operator/OpLogin/Page_Runo/input_Operator_btn gardianButt'))

