package restAssuredDefinitionsAPITest;

/*
 *  https://metadata.uw.edu/KNApi/List/Glossary - returns groups of terms
 *  https://metadata.uw.edu/api/reports/all -   returns all reports with report UI
 *  https://metadata.uw.edu/api/reports/terms/{report UID} -  returns all terms related to report UI
 *  https://metadata.uw.edu/Catalog/ViewItem/Term/{ParentUid UID} - loads page in KN for term
 *   and passes that as a parameter in my test method. This will resolve the issue of when the array order changes.
 *
 * https://stackoverflow.com/questions/44716665/full-json-match-with-restassured
 *
 *
 * */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class API_ReportsAllTest_Refactored {

    int reportCount = 113;

    @BeforeClass
    public Response doGetRequest() {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("ContentType", ContentType.JSON, "Accepts", ContentType.JSON)
                        .when().get("https://metadata.uw.edu/api/reports/all")
                        .then().statusCode(200).and().contentType(ContentType.JSON).extract().response();
    }

    @Test(priority = 1)
    public void apiStatusCode200() {
        int code = get("https://metadata.uw.edu/api/reports/all").getStatusCode();
        Assert.assertEquals(code, 200);
    }

    @Test(priority = 1)
    public void apiStatusCodeNot404() {
        int code = get("https://metadata.uw.edu/api/reports/all").getStatusCode();
        Assert.assertNotEquals(code, 404);
    }

    @Test(priority = 1)
    public void apiStatusCodeNot500() {
        int code = get("https://metadata.uw.edu/api/reports/all").getStatusCode();
        Assert.assertNotEquals(code, 500);
    }

    @Test(priority = 2)
    public void apiReportsAllCount() {
        int sizeOfList =
                when()
                        .get("https://metadata.uw.edu/api/reports/all")
                        .then()
                        .extract().
                        path("list.size()");
        Assert.assertEquals(sizeOfList, reportCount);
        // TODO - QUESTION:  What best? Having a variable declared outside of the Test method or left inside?
        Assert.assertEquals(sizeOfList, reportCount);
    }

    // ******************************************** REFACTORED PART ****************
    // Method to store the Key and Value for Uid and Title in Map.
    public Map<String, String> reportUIDsAndTitles() {
        Response response = doGetRequest();
        Gson gson = new Gson();
        JsonArray myElement = gson.fromJson(response.asString(), JsonArray.class);

        // Map to hold the actual values.
        Map<String, String> uIDTitleValues = new HashMap<>();

        // The ForEach loop get key and value from Map.
        for (JsonElement element : myElement) {
            JsonObject obj = element.getAsJsonObject();
            String uidString = obj.get("Uid").getAsString();
            String titleString = obj.get("Title").getAsString();

            //populates Map with actual values.
            uIDTitleValues.put(uidString, titleString);
        }
        return uIDTitleValues;
    }

    @Test(priority = 3)
    public void api100PercentSponsorFundedFaculty() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("97d17dbd-9c3b-46aa-ae0e-39603a32250f")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "100 Percent Sponsor-Funded Faculty", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiAdvanceBudgetNumberReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("f93c0249-c281-45de-b574-865736dc928e")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Advance Budget Number Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiXXX() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("XXX")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "XXX", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiAffixedPaymentDetail() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("d4ffff98-b8a1-4735-90d4-cad45cf3ae31")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Affixed Payment Detail", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiAidAuthorizedandDisbursedbyYear() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("80f71488-d76c-4fa8-b6ef-6971075bd55b")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Aid Authorization and Disbursement by Year", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiAidAuthorizedandDisbursedbyQuarter() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("feada1f3-ea53-4190-ab0b-278b9d13d50b")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Aid Authorized and Disbursed for Aid Year and Quarter", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiAllApplications() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("81fbc804-94cc-4347-8d30-faabfb8ea3b2")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "All Applications", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiBienniumToDateBudgetBalances() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("c032f3f5-3852-4df9-815e-2dbf11c0bb4f")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Biennium To Date Budget Balances", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiBudgetSummaryByOrgCode() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("e4d88596-a7da-4e50-b1f2-9822adecb8fc")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Budget Summary by OrgCode", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiBudgetTypeClassMatrix() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("40787751-bfb7-4850-b857-8779deab8950")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Budget Type Class Matrix", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiBuildingSummarybyOrgCode() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("a83e106e-f939-4784-ae4b-6ceb838c479a")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Building Summary by OrgCode", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiBuildingSummarybyPrimaryUse() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("a150a1fa-dc43-413e-bb1d-903ca89c01fe")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Building Summary by Primary Use", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCampusApplicationCountsComparison() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("ec0be166-1eca-41dc-8059-6c6328dd538a")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Campus Application Counts Comparison", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCashierActivityReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("fdb2a0f2-c636-4f41-a06b-57f4607c4a8c")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Cashier Activity Report - Payments Received", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiClassListByCurriculumCourseSection() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("15b82ff5-6f82-4e1f-9392-436b898c87ee")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Class List By Curriculum Course Section", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCollegeSpaceDatabyFacility() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("413ec093-3589-4380-85b4-64e849139493")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "College Space Data by Facility", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCollegeSpaceDatabyOrgCode() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("ac51b2f8-d0bb-4afb-9dab-9b5bd346a09b")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "College Space Data by OrgCode", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiConsolidatedBudgetStatusReportMenu() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("e8378a86-41fe-4771-b9a1-24b99e431f3b")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Consolidated Budget Status Report Menu", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiContactInformationForUnmetRequest() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("2404f719-41cf-4aaf-92ea-c536055e9aaa")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Contact Information - Unmet Requests", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCostShareCommitmentsandContributions() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("843ae532-d81e-43b3-944e-30e7b94d1a69")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Cost Share Commitments and Contributions", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCourseSectionFillRates() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("f998e2d9-8cb4-4429-b90b-225713120708")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Course Section Fill Rate", "Report Title not found.");
            }
        }
    }

    //Archived: 6/23/2020
    //TODO - This report falsely passed even thought the report no longer exists as it was archived. I need to update
    //       it to verify first if the report exists or not.
    @Test
    public void apiCurrentApplicationCountbyDepartmentandMajor() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("dc86d43a-b2ea-41e6-953b-e408cadf3712")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Current Application Count by Department and Major", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCurrentStudentInformation() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("73b35e15-f3fc-4250-93ea-98f81c69c03d")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Current Student Information", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCurrentStudentInformationByMajor() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();

        int code = given().when().get(baseURI + "f9c0f09b-a191-4185-ba64-6fb51a3066c4").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);

        //TODO - LEFT OFF HERE.
        /*
        for (Map.Entry pairEntry : pairs.entrySet()){
            if ()
        }*/
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("f9c0f09b-a191-4185-ba64-6fb51a3066c4")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Current Student Information By Major", "Report Title not found.");
            }
            else {
                System.out.println("failed to find this one.");
            }
        }
    }
    @Test
    public void apiCurrentStudentRegistrationCourses() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("b226ad62-76ef-4cdc-87af-618777c6c453")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Current Student Registration Courses", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCurrentStudentTranscriptCourses() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("e4944e4f-a0ac-4cdd-ac3b-61bbcfdb6712")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Current Student Transcript Courses", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCurrentStudentTranscriptQuarterlySummary() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("2ff68c12-9553-414a-a676-70898a5f56fa")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Current Student Transcript Quarterly Summary", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCurrentStudentTransferSummary() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("db97494e-95a7-47f3-8d8a-3407821bfd38")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Current Student Transfer Summary", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCurriculumCodes() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("c91766da-7665-4f92-87e9-7e1516ccc81a")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Curriculum Codes", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiCustomBudgetIndexReportbyOrgCode() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("d8d1ea91-1ac3-48c5-b321-e84751987fbf")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Custom Budget Index Report by OrgCode", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiDACSecuredTablesViewsbyRole() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("2651b5c6-a027-4f06-9d94-df418b8f4082")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "DAC Secured Tables, Views And Columns Overview by Roles", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiDACSecuredTablesViewsColumns() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("87bee0aa-de15-44c5-8378-bc0189a159d0")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "DAC-Secured Tables, Views And Columns", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiDailyAidandRefundCheckActivityFile() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("0381f391-d2f9-4305-9211-4f73ef222dd9")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Daily Aid and Refund Check Activity File", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiDegreeCodes() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("3e6a670e-8209-431b-a906-e7f13bcca6af")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Degree Codes", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiDegreesOrganizationAssociations() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("44ea54e3-2eb5-484c-becc-94162897cd77")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Degrees and Organization Associations", "Report Title not found.");
            }
        }
    }
    @Test
    public void apieFECSEffortReportingRetroactiveSalaryAdjustmentReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("e8f08775-f5f7-4364-ac31-7af40b2fed0d")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "eFECS Effort Reporting Retroactive Salary Adjustment Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiExpenseandRevenueTransferReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("9eb9eb26-658c-4dd7-a183-107ae1b80f26")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Expense and Revenue Transfer Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFacilityInformation() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("48835f63-ab79-40fc-bc99-4347a52d2324")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Facility Information", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFacilityRoomDetail() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("bf1e10f2-11d4-4179-9ec4-7dc5ffb6e59e")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Facility Room Detail", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFacultyEffortCertification() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("38e46e79-1f00-4a0b-a20c-f87f29e5e8a4")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Faculty Effort Certification (FEC) Dashboard", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFASBatchSummaryReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("5ef127d9-287b-4563-ba24-1e5275b3f4a0")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "FAS Batch Summary Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFECDashboardActionList() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("eee0a819-cc94-41a6-969c-021f5bf76e12")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "FEC Dashboard Action List", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFECDashboardReportbyFECCycle() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("ebe0b73a-8442-46dc-bf75-682b82b690d9")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "FEC Dashboard Report by FEC Cycle", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFECDashboardReportbyOrgCode() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("ffda88cc-146f-4de0-87e2-e47d0d690003")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "FEC Dashboard Report by OrgCode", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFiscalYearIncomeandExpenseSummarybyOrgCodeandFunction() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("84ea1a9c-75ed-4f61-9352-29b93fd12841")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Fiscal Year Income and Expense Summary by OrgCode and Function", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFiscalYearIncomeandExpenseSummarybyOrgCodeandFundingSource() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("dc087820-0962-467d-b399-c6dd65b7143d")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Fiscal Year Income and Expense Summary by OrgCode and Funding Source", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFiscalYearIncomeandExpenseSummaryReportMenu() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("64769b8b-1d91-4661-abed-a076c3f2733d")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Fiscal Year Income and Expense Summary Report Menu", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiForecastingTemplatebyFiscalYearandOrgCode() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("c205da63-1750-48a1-acc4-e7d8c5b5ab9c")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Forecasting Template by Fiscal Year and OrgCode", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFreshmanApplicationsScoresandEthnicity() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("c20d810e-c9cf-44ee-9da8-9e348046d0cd")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Freshman Applications Scores and Ethnicity", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiAgencyFunds() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("4a261fe6-3735-4d1a-af9d-f28318ffe77e")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Fund Group 70 Agency Funds", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGiftBalanceDetailbyBudgetList() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("45fef3a6-53b5-4265-af16-77887516f132")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Gift Balance Detail by Budget List", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGiftBalanceDetailbySpendingRestrictionandOrgCode() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("9a736bd7-98dc-4730-889a-507c4cf6ef8f")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Gift Balance Detail by Spending Restriction and OrgCode", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGiftBalanceSummarybySpendingRestriction() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("da5ff660-25e7-4871-9a04-fc2a7ed66dbc")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Gift Balance Summary by Spending Restriction", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGLSummaryReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("a41a23c8-8f76-47d8-88c2-f08094c40c51")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "GL Summary Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGLTransactionsbyMonth() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("4aadf72b-bd9b-46ab-bb7c-3eec61b98c13")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "GL Transactions by Month", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGrantandContractBudgetStatusReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("cec8d319-bf4b-4f43-8a18-daff2fc91943")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Grant and Contract Budget Status Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGrantandContractCertificationReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("93ad3676-d518-40e3-a1e5-8b660519fcde")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Grant and Contract Certification Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGrantandContractDeficitReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("6c201688-dd3a-4e47-9f1f-267259043837")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Grant and Contract Deficit Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGrantandGiftBudgetsUnassignedtoSpace() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("ad6c965a-e3d1-43e1-bf95-36255a3e20fd")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Grant and Gift Budgets Unassigned to Space", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiPayrollCashMainPage() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("bf3245a0-713e-4d10-8602-c53da294e7aa")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Hepps Payroll and GL Payroll Cash Account", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiHowManyCreditHoursByCollege() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("48877c10-74f5-4567-8275-9fae628deea2")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "How Many Credit Hours Are Being Taught", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiHowManyDegreesByCollege() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("9e22e59e-7f6d-40d0-a0d4-d172a1f4631c")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "How Many Degrees Have I Granted", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiHowManyStudentsUniversity() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("373cbd02-ad76-429b-be79-0da2557bdac7")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "How Many Students at the University", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiHowManyStudentsByCampus() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("9b3e4a98-1b3b-40ac-a42a-afc41f485263")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "How Many Students By Campus", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiHowManyStudentsByCollege() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("0d8bd3d1-ebce-4024-955e-e28947232a30")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "How Many Students By College", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiHowManyStudentsByDepartment() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("49a4eb1d-2dbf-45b1-be7f-f2003b03c8e4")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "How Many Students By Department", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiHowManyStudentsList() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("a617f28a-1085-43a4-8e76-a0ef339aa36f")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "How Many Students List", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiImmunizationReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("28159b18-3494-40a1-baa6-f6ce9e98b35a")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Immunization Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiIndirectCostRecoveryEstimator() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("030cbda6-51db-495e-9cb6-2977b3d1cd6b")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Indirect Cost Recovery Estimator", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiInternalandExternalSalesSummaryReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("bbe5d906-5245-4a5a-8b37-6318b9e8eb23")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Internal and External Sales Summary Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiJournalVoucherDetail() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("45836886-93c5-4f45-bcec-5b9b52f2aef4")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Journal Voucher Detail", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiLearningSpacesPolicy() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("9f0bb5b8-ca7d-4d73-ad06-008ff0dad337")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Learning Spaces Policy", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiFECDashboardActionListLegacy() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("9480881b-3187-486e-af9a-bd3cfc4c9c57")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Legacy FEC Dashboard Action List", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGLReconcilingReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("6c16b832-4499-4bc8-a698-552bb865db8e")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Legacy GL Payroll Cash Activities in a Month: Major Payroll Runs", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiGrantandContractCertificationReportLegacy() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("5e425602-e263-4558-93fd-56e6560cfa74")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Legacy Grant and Contract Certification Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiEmployeeSearch() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("a9b98a6b-04d9-4ce4-a99d-38748f51b1f5")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Legacy HR/P Employee Check and Funding History Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiMajorCodes() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("1b3d6f08-d3a5-419a-8046-e5f31663aead")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Major Codes", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiMinorCodes() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("c68e0e57-8711-44c3-a1cc-139132dc4c49")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Minor Codes", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiMiscellaneousStudentAccountsNontuitionChargeSummary() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("2ad99fff-4c5d-4c70-ab7c-6b18923616d4")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Miscellaneous Student Accounts - Non-Tuition Charge Summary", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiMyReports() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("46af4bbf-7fbc-4a06-9c09-f6878c33eb96")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "My Reports", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiMyFDExpenseTransferVolumeReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("4ad810ad-2a9e-4ee5-9f15-847ccc6f5770")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "MyFD Expense Transfer Volume Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiNSFCompensationbyFiscalYear() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("424a91e2-9b46-41a6-b4bb-03bcd21babde")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "NSF Compensation by Fiscal Year", "Report Title not found.");
            }
        }
    }    @Test
    public void apiOrganizationBudgetRelationshipReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("81ac0b4e-1833-40e5-aba3-0d1a0a4dfc6c")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Organization-Budget Relationship Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiPaidFTEbyFundingSource() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("31b1281c-6276-4ca4-8826-95aa037c7129")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Paid FTE by Funding Source", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiPredominantlySponsorFundedFaculty() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("dfb7d4d0-dbfa-46c7-9814-99afa4f7acb4")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Predominantly Sponsor-Funded Faculty", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiRemainingBalancebyOrgCodeandCategory() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("05c00711-165b-4d78-9618-78cd19ea7e52")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Remaining Balance by OrgCode and Category", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiResearchAwards() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("e0e1d48e-c94d-4d49-aab7-aadccb9d28c5")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Research Awards", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiResearchBudgetExpenditures() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("b4367d5d-30e9-4bfc-bcf5-259269a4c011")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Research Budget Expenditures", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiResearchProposalDetails() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("48d793a8-40ab-458a-837a-783c10522fd3")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Research Proposal Details", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiResearchProposals() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("f308bc0e-71fb-473d-90c0-cf0e8b53a5c0")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Research Proposals", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiResearchSubawards() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("37ce6b31-bdca-4863-ae98-7879951499ef")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Research Subawards", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiRoomAssignmentDetailsbyDepartment() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("8e150b05-8678-48ba-bd6e-430de2b2acac")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Room Assignment Details by Department", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiSalaryandWageExpenseDetail() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("b99a3f76-3c04-4175-bd14-40b4dbad5c86")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Salary and Wage Expense Detail", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiSalaryCapCostShareReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("e070a4e5-9e81-4a13-a8c0-a5e800f93dcf")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Salary Cap Cost Share Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiSatisfactoryProgressPolicyReport() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("1a20ec0a-f586-4276-89fc-3394e8a80a6f")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Satisfactory Progress Policy Report", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiSEVISImmigrationReporting() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("9cba22cc-314f-45dc-95df-6b7599969caf")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "SEVIS Immigration Reporting", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiSMATHistory() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("9fa9d72f-bef4-48d9-bf5a-1fb27ca72a84")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "SMAT History", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiSpaceDatabyDepartment() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("2117c782-13bf-4b6e-ba17-aff043712e0d")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Space Data by Department", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiStudentAthletesEarning() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("b3898a82-318a-4ebf-81f5-911b60a059a3")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Student Athletes Earning", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiStudentDegreeInformation() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("79dedeb4-b57a-4ae8-a02f-1725980af499")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Student Degree Information", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiStudentGradeReportbyCourse() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("68b3fb3b-5352-4727-8b6b-cfcb3684c3f2")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Student Grade Report by Curriculum, Course and Section", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiStudentGradeReportbyMajor() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("47a40200-1012-4d66-9b97-3afe38712bb6")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Student Grade Report by Major", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiStudentTranscriptDetail() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("f77e2ae1-8247-45ca-b246-d3b32d31275c")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Student Transcript Detail", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiStudyAbroadStudents() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("fbd04b61-b99a-464e-b99c-3670c7d6dbd9")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Study Abroad Students by Unit", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiSupplierSearch() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("1a5ec751-5f00-459b-bfbd-ed11078359a6")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Supplier Search", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiTimeScheduleInformation() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("220bd95d-6cbb-4f98-90e1-919e02407216")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Time Schedule Information", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiTransactionSummarybyOrgCode() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("01ed49cb-de6d-4427-88a2-42f90f8ec91c")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Transaction Summary by OrgCode", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiUndergraduateDegreeApplicants() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("5b6f609e-a8a8-45d0-8ab5-5d673402b8ec")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Undergraduate Degree Applicants", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiUndergraduateTranscriptCourseSearchbyMajor() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("148b610d-b2aa-4002-b9ae-9c7ca02e2867")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Undergraduate Transcript Course Search by Major", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiUndergraduatesEligibletoRegister() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("bbd849b0-fde2-495b-83d8-bf2ba7ff425e")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Undergraduates Eligible to Register", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiUseTaxBienniumSummary() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("2fecd537-683f-4095-93a0-86de8b553ca6")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Use Tax Liability Biennium Summaries", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiVariableReportingPeriodBudgetSummary() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("5ef70177-f98c-4bc4-8a2f-ad76516eb488")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Variable Reporting Period Budget Summary", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiVariableReportingPeriodTransactionSummary() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("f8c45a5b-c8e3-4526-bf4f-d70506948611")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Variable Reporting Period Transaction Summary", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiWhoAmI() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("56de9052-ca38-4673-9f66-ca118026b143")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Who Am I?", "Report Title not found.");
            }
        }
    }
    @Test
    public void apiWhoHasAccess() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("cf5aa954-06b3-4181-a552-72b19250aaf7")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "Who Has Access?", "Report Title not found.");
            }
        }
    }
/*    @Test
    public void apiXXX() {
        Map<String, String> pairs;
        pairs = reportUIDsAndTitles();
        for (Map.Entry pairEntry : pairs.entrySet()) {
            if (pairEntry.getKey().equals("XXX")) {
                String keyValue = (String) pairEntry.getValue();
                System.out.println(keyValue + ":  " +  pairEntry.getKey());
                assertEquals(keyValue, "XXX", "Report Title not found.");
            }
        }
    }*/























    }




