package restAssuredDefinitionsAPITest;

/*
 *  .get("https://metadata.uw.edu/KNApi/List/Glossary") - returns groups of terms
 *  .get("https://metadata.uw.edu/api/reports/all") -   returns all reports with report UI
 *  .get("https://metadata.uw.edu/api/reports/terms/{report UID}")  -  returns all terms related to report UI
 * */

import org.hamcrest.core.IsNull;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class API_KN_GlossaryTest {

    // Site tests
    @Test
    public void apiStatusCode200() {
        int code = get("https://metadata.uw.edu/KNApi/List/Glossary").getStatusCode();
        Assert.assertEquals(code, 200);
    }
    @Test
    public void apiStatusCodeNot404() {
        int code = get("https://metadata.uw.edu/KNApi/List/Glossary").getStatusCode();
        Assert.assertNotEquals(code, 404);
    }
    @Test
    public void apiStatusCodeNot500() {
        int code = get("https://metadata.uw.edu/KNApi/List/Glossary").getStatusCode();
        Assert.assertNotEquals(code, 500);
    }

    // Section tests
    @Test
    public void apiAcademicPersonnelReportingTerms() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[0]", equalTo("4cfe2418-d49b-408f-801a-f51fc93988e0"))
                .assertThat().body("Title[0]", equalTo("Academic Personnel Reporting Terms"))
                .assertThat().body("ShortDefinition[0]", IsNull.notNullValue());
    }
    @Test
    public void apiCommonDataSet() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[1]", equalTo("749fed0c-1afd-4138-beb9-96f416234985"))
                .assertThat().body("Title[1]", equalTo("Common Data Set"))
                .assertThat().body("ShortDefinition[1]", IsNull.notNullValue());
    }
    @Test
    public void apiEDWDataDictionary() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[2]", equalTo("18c96607-34df-454f-8d62-804cea484fcf"))
                .assertThat().body("Title[2]", equalTo("EDW Data Dictionary"))
                .assertThat().body("ShortDefinition[2]", IsNull.notNullValue());
    }
    @Test
    public void apiFinanceTerms() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[3]", equalTo("211358c5-0808-4eaf-85c6-e3266abc6beb"))
                .assertThat().body("Title[3]", equalTo("Finance Terms"))
                .assertThat().body("ShortDefinition[3]", IsNull.notNullValue());
    }
    @Test
    public void apiGraduateSchoolTerms() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[4]", equalTo("5150fb9d-70d7-4477-8f6b-a2abfd22ff2b"))
                .assertThat().body("Title[4]", equalTo("Graduate School Terms"))
                .assertThat().body("ShortDefinition[4]", IsNull.notNullValue());
    }
    @Test
    public void apiHRPayrollTerms() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[5]", equalTo("72e4bd9a-0b5c-41f6-80f2-1dfd46366be9"))
                .assertThat().body("Title[5]", equalTo("HR/Payroll Terms"))
                .assertThat().body("ShortDefinition[5]", IsNull.notNullValue());
    }
    @Test
    public void apiInformationManagementTerms() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[7]", equalTo("105eec96-ad29-46c1-9413-2e6dbd414f72"))
                .assertThat().body("Title[7]", equalTo("Information Management Terms"))
                .assertThat().body("ShortDefinition[7]", IsNull.notNullValue());
    }
    @Test
    public void apiIPEDS() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[8]", equalTo("ipeds"))
                .assertThat().body("Title[8]", equalTo("IPEDS"))
                .assertThat().body("ShortDefinition[8]", IsNull.notNullValue());
    }
    @Test
    public void apiMyFD() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[10]", equalTo("myfd"))
                .assertThat().body("Title[10]", equalTo("MyFD"))
                .assertThat().body("ShortDefinition[10]", IsNull.notNullValue());
    }
    @Test
    public void apiResearchAdministrationTerms() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[11]", equalTo("63701908-78c3-451a-a9b6-ac4d2ff1112d"))
                .assertThat().body("Title[11]", equalTo("Research Administration Terms"))
                .assertThat().body("ShortDefinition[11]", IsNull.notNullValue());
    }
    @Test
    public void apiStudentDataTerms() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[12]", equalTo("studentdata"))
                .assertThat().body("Title[12]", equalTo("Student Data Terms"))
                .assertThat().body("ShortDefinition[12]", IsNull.notNullValue());
    }
    @Test
    public void apiUniversityAdvancementTerms() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[13]", equalTo("366c77d3-f5c2-483b-8e2f-8cc86d93d314"))
                .assertThat().body("Title[13]", equalTo("University Advancement Terms"))
                .assertThat().body("ShortDefinition[13]", IsNull.notNullValue());
    }
    // Template
/*    @Test
    public void api() {
        given()
                .when()
                .get("https://metadata.uw.edu/KNApi/List/Glossary")
                .then()
                .statusCode(200)
                .assertThat().body("Uid[]", equalTo(""))
                .assertThat().body("Title[]", equalTo(""))
                .assertThat().body("ShortDefinition[]", IsNull.notNullValue());
    }*/
}
