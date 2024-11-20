package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AddEmployeePage;
import utils.CommonMethods;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    AddEmployeePage addEmployeePage= new AddEmployeePage();

    @When("user enters firstname and lastname in the name fields")
    public void user_enters_firstname_and_lastname_in_the_name_fields() {
       // WebElement firstName = driver.findElement(By.id("firstName"));
        //WebElement lastName = driver.findElement(By.id("lastName"));

        sendText("mark", addEmployeePage.firstNameLocator);
        sendText("jacob", addEmployeePage.lastNameLocator);

    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        //WebElement saveButton = driver.findElement(By.id("btnSave"));
        click(addEmployeePage.saveButton);

    }

    @Then("user added successfully")
    public void user_added_successfully() {
        System.out.println("test passed");
    }

    @When("user enters employee name")
    public void user_enters_employee_name() {
        WebElement empsearchNameField = driver.findElement(By.id("empsearch_employee_name_empName"));
        empsearchNameField.sendKeys("ola");
    }

    @When("user enters firstname middlename lastname in the name fields")
    public void user_enters_firstname_middlename_lastname_in_the_name_fields() {
        //WebElement firstName = driver.findElement(By.id("firstName"));
        //WebElement lastName = driver.findElement(By.id("lastName"));
        //WebElement middleName = driver.findElement(By.id("middleName"));
       // firstName.sendKeys("mark");
       // middleName.sendKeys("ms");
        //lastName.sendKeys("jacob");
        sendText("mark",addEmployeePage.firstNameLocator);
        sendText("ms", addEmployeePage.middleNameLocator);
        sendText("jacob", addEmployeePage.lastNameLocator);
    }

    @When("user enters {string} and {string} and {string} in the application")
    public void user_enters_and_and_in_the_application
            (String fName, String mName, String lName) {
        //WebElement firstName = driver.findElement(By.id("firstName"));
        //WebElement middleName = driver.findElement(By.id("middleName"));
       // WebElement lastName = driver.findElement(By.id("lastName"));
        //firstName.sendKeys(fName);
        //middleName.sendKeys(mName);
        //lastName.sendKeys(lName);
        sendText(fName, addEmployeePage.firstNameLocator);
        sendText(mName,addEmployeePage.middleNameLocator);
        sendText(lName, addEmployeePage.lastNameLocator);

    }

    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String fn, String mn, String ln) {
        //WebElement firstName = driver.findElement(By.id("firstName"));
        //WebElement middleName = driver.findElement(By.id("middleName"));
        //WebElement lastName = driver.findElement(By.id("lastName"));
        //firstName.sendKeys(fn);
        //middleName.sendKeys(mn);
        //lastName.sendKeys(ln);
        sendText(fn, addEmployeePage.firstNameLocator);
        sendText(mn, addEmployeePage.middleNameLocator);
        sendText(ln, addEmployeePage.lastNameLocator);

    }

    @When("user enters employees using data table and save them")
    public void user_enters_employees_using_data_table_and_save_them
            (io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeenames = dataTable.asMaps();
        for (Map<String, String> employee : employeenames) {
            //WebElement firstName = driver.findElement(By.id("firstName"));
            //WebElement middleName = driver.findElement(By.id("middleName"));
            //WebElement lastName = driver.findElement(By.id("lastName"));

            addEmployeePage.firstNameLocator.sendKeys(employee.get("firstName"));
            addEmployeePage.middleNameLocator.sendKeys(employee.get("middleName"));
            addEmployeePage.lastNameLocator.sendKeys(employee.get("lastName"));


            //WebElement saveButton = driver.findElement(By.id("btnSave"));
            //addEmployeePage.saveButton.click();
            click(addEmployeePage.saveButton);

            WebElement addEmployeeOption = driver.findElement(By.id("menu_pim_addEmployee"));
            //addEmployeeOption.click();
            click(addEmployeeOption);
        }

    }
    @When("user adds multiple employees using excel file")
    public void user_adds_multiple_employees_using_excel_file () throws IOException {
        List<Map<String, String>> employeenames = ExcelReader.read();

        for (Map<String, String> employee :
                employeenames) {

            //WebElement firstnameLocator = driver.findElement(By.id("firstName"));
            //WebElement lastnameLocator = driver.findElement(By.id("lastName"));
            //WebElement middlenameLocator = driver.findElement(By.id("middleName"));

            addEmployeePage.firstNameLocator.sendKeys(employee.get("firstName"));
            addEmployeePage.middleNameLocator.sendKeys(employee.get("middleName"));
            addEmployeePage.lastNameLocator.sendKeys(employee.get("lastName"));

            //WebElement saveButton = driver.findElement(By.id("btnSave"));
            //addEmployeePage.saveButton.click();
            click(addEmployeePage.saveButton);


            WebElement addEmployeeOption = driver.findElement(By.id("menu_pim_addEmployee"));
            //addEmployeeOption.click();
            click(addEmployeeOption);
        }
    }

}
