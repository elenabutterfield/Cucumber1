package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.ExcelReader;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {

    LoginPage loginPage = new LoginPage();

    @Given("user is able to access HRMS application")
    public void user_is_able_to_access_hrms_application() {
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("user enters admin username and admin password")
    public void user_enters_admin_username_and_admin_password() throws IOException {
        //WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //usernameField.sendKeys("admin");
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        //WebElement passwordField = driver.findElement(By.id("txtPassword"));
        //passwordField.sendKeys("Hum@nhrm123");
        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        // loginButton.click();
        click(loginPage.loginButton);
    }

    @Then("user is navigated to dashbaord page")
    public void user_is_navigated_to_dashbaord_page() {
        Assert.assertTrue(dashboardPage.welcomeText.isDisplayed());
        System.out.println("test passed");
    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        //  WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //loginPage.sendKeys("admin");
        sendText("admin", loginPage.usernameField);
        //  WebElement passwordField = driver.findElement(By.id("txtPassword"));
        // passwordField.sendKeys("Hum@nhrm12234563");
        sendText("Hum@h", loginPage.passwordField);
    }

    @Then("user can see error message")
    public void user_can_see_error_message(){
        String actualMessage=loginPage.errorMessage.getText();
        Assert.assertEquals("Invalid credentials",actualMessage);
    }

    //@When("user clicks on PIM option")
    //public void user_clicks_on_pim_option() {
    //   WebElement PimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
     //   PimOption.click();

    //}

    //@When("user clicks on employee list option")
    //public void user_clicks_on_employee_list_option() {
      //  WebElement empListOption = driver.findElement(By.id("menu_pim_viewEmployeeList"));
     //   empListOption.click();
    //}

    //@When("user enters employee ID")
    //public void user_enters_employee_id() {
     //   WebElement empIdTextBox = driver.findElement(By.id("empsearch_id"));
      //  empIdTextBox.sendKeys("110360A");
    //}

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        WebElement searchButton = driver.findElement(By.id("searchBtn"));
        //searchButton.click();
        click(searchButton);
    }

    @Then("user is able to see searched employee on screen")
    public void user_is_able_to_see_searched_employee_on_screen() {
        System.out.println("test passed");
    }

    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        WebElement addEmployeeOption = driver.findElement(By.id("menu_pim_addEmployee"));
       // addEmployeeOption.click();
        click(addEmployeeOption);

    }

    @When("user enters firstname and lastname in the name fields")
    public void user_enters_firstname_and_lastname_in_the_name_fields() {
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement middleName = driver.findElement(By.id("middleName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        firstName.sendKeys("faylin");
        middleName.sendKeys("ashley");
        lastName.sendKeys("maksana");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        WebElement saveButton = driver.findElement(By.id("btnSave"));
        //saveButton.click();
        click(saveButton);

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
        System.out.println("will add later");
    }

    @When("user enters {string} and {string} and {string} in the application")
    public void user_enters_and_and_in_the_application
            (String fName, String mName, String lName) {
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement middleName = driver.findElement(By.id("middleName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        firstName.sendKeys(fName);
        middleName.sendKeys(mName);
        lastName.sendKeys(lName);
    }

    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String fn, String mn, String ln) {
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement middleName = driver.findElement(By.id("middleName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        firstName.sendKeys(fn);
        middleName.sendKeys(mn);
        lastName.sendKeys(ln);
    }

    @When("user enters employees using data table and save them")
    public void user_enters_employees_using_data_table_and_save_them
            (io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeenames = dataTable.asMaps();
        for (Map<String, String> employee : employeenames) {
            WebElement firstName = driver.findElement(By.id("firstName"));
            WebElement middleName = driver.findElement(By.id("middleName"));
            WebElement lastName = driver.findElement(By.id("lastName"));

            firstName.sendKeys(employee.get("firstname"));
            middleName.sendKeys(employee.get("middlename"));
            lastName.sendKeys(employee.get("lastname"));

            WebElement saveButton = driver.findElement(By.id("btnSave"));
            saveButton.click();

            WebElement addEmployeeOption = driver.findElement(By.id("menu_pim_addEmployee"));
            addEmployeeOption.click();
        }

    }
        @When("user adds multiple employees using excel file")
        public void user_adds_multiple_employees_using_excel_file () throws IOException {
            List<Map<String, String>> employeenames = ExcelReader.read();

            for (Map<String, String> employee :
                    employeenames) {

                WebElement firstnameLocator = driver.findElement(By.id("firstName"));
                WebElement lastnameLocator = driver.findElement(By.id("lastName"));
                WebElement middlenameLocator = driver.findElement(By.id("middleName"));

                firstnameLocator.sendKeys(employee.get("firstName"));
                middlenameLocator.sendKeys(employee.get("middleName"));
                lastnameLocator.sendKeys(employee.get("lastName"));

                WebElement saveButton = driver.findElement(By.id("btnSave"));
                saveButton.click();

                WebElement addEmployeeOption = driver.findElement(By.id("menu_pim_addEmployee"));
                addEmployeeOption.click();
            }

        }

    }






