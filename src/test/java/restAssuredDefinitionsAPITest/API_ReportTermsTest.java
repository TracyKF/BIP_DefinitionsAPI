package restAssuredDefinitionsAPITest;
/*
 *  https://metadata.uw.edu/KNApi/List/Glossary - returns groups of terms
 *  https://metadata.uw.edu/api/reports/all -   returns all reports with report UI
 *  https://metadata.uw.edu/api/reports/terms/{report UID} -  returns all terms related to report UI
 *  https://metadata.uw.edu/Catalog/ViewItem/Term/{ParentUid UID} - loads page in KN for term
 * */

import org.hamcrest.core.IsNull;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class API_ReportTermsTest {

    @BeforeClass
    public void getBaseURI() {
        baseURI = "https://metadata.uw.edu/api/reports/terms/";
    }

    //TODO:  Currently, these tests are only validating that the .../reports/terms/{report UID}  exists or not.
    //       If it does not exist it means possibly that the report no longer exists which is a good test on
    //       it's own. What I could expand on is validating the list of terms associated to each report.  But
    //       the team felt this was an unnesssary test.  Is it?


    @Test(priority = 1)
    public void apiStatusCode200Test() {
        int code = given().when().get(baseURI + "97d17dbd-9c3b-46aa-ae0e-39603a32250f").getStatusCode();
        Assert.assertEquals(code, 200);
/*        try{
            Assert.assertEquals(code, 200);
        }catch (AssertionError e){
            System.out.println(e);
        }*/
    }
    @Test(priority = 1)
    public void apiStatusCodeNot500Test() {
        int code =
                given()
                        .when()
                        .get(baseURI + "97d17dbd-9c3b-46aa-ae0e-39603a32250f")
                        .getStatusCode();
        // TODO If assert below fails send email with error.
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void lOOPercentSponsorFundedFaculty() {
        int code = given().when().get(baseURI + "97d17dbd-9c3b-46aa-ae0e-39603a32250f").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
/*    @Test(priority = 1)
    public void Twenty13BaseYearPaidFTEReport() {
        int code = given().when().get(baseURI + "97b3f1e0-b17d-448b-86ae-6ed6b432dcd2"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }*/
    @Test(priority = 1)
    public void AdvanceBudgetNumberReport() {
        int code = given().when().get(baseURI + "f93c0249-c281-45de-b574-865736dc928e"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void AffixedPaymentDetail() {
        int code = given().when().get(baseURI + "d4ffff98-b8a1-4735-90d4-cad45cf3ae31"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void AidAuthorizedandDisbursedbyYear() {
        int code = given().when().get(baseURI + "80f71488-d76c-4fa8-b6ef-6971075bd55b"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void AidAuthorizedandDisbursedbyQuarter() {
        int code = given().when().get(baseURI + "feada1f3-ea53-4190-ab0b-278b9d13d50b"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void AllApplications() {
        int code = given().when().get(baseURI + "81fbc804-94cc-4347-8d30-faabfb8ea3b2"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void BienniumToDateBudgetBalances() {
        int code = given().when().get(baseURI + "c032f3f5-3852-4df9-815e-2dbf11c0bb4f"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void BudgetSummaryByOrgCode() {
        int code = given().when().get(baseURI + "e4d88596-a7da-4e50-b1f2-9822adecb8fc"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void BudgetTypeClassMatrix() {
        int code = given().when().get(baseURI + "40787751-bfb7-4850-b857-8779deab8950"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void BuildingSummarybyOrgCode() {
        int code = given().when().get(baseURI + "a83e106e-f939-4784-ae4b-6ceb838c479a"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void BuildingSummarybyPrimaryUse() {
        int code = given().when().get(baseURI + "a150a1fa-dc43-413e-bb1d-903ca89c01fe"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CampusApplicationCountsComparison() {
        int code = given().when().get(baseURI + "ec0be166-1eca-41dc-8059-6c6328dd538a"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CashierActivityReport() {
        int code = given().when().get(baseURI + "fdb2a0f2-c636-4f41-a06b-57f4607c4a8c"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ClassListByCurriculumCourseSection() {
        int code = given().when().get(baseURI + "15b82ff5-6f82-4e1f-9392-436b898c87ee"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CollegeSpaceDatabyFacility() {
        int code = given().when().get(baseURI + "413ec093-3589-4380-85b4-64e849139493"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CollegeSpaceDatabyOrgCode() {
        int code = given().when().get(baseURI + "ac51b2f8-d0bb-4afb-9dab-9b5bd346a09b"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ConsolidatedBudgetStatusReportMenu() {
        int code = given().when().get(baseURI + "e8378a86-41fe-4771-b9a1-24b99e431f3b"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ContactInformationForUnmetRequest() {
        int code = given().when().get(baseURI + "2404f719-41cf-4aaf-92ea-c536055e9aaa"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CostShareCommitmentsandContributions() {
        int code = given().when().get(baseURI + "843ae532-d81e-43b3-944e-30e7b94d1a69"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CourseSectionFillRates() {
        int code = given().when().get(baseURI + "f998e2d9-8cb4-4429-b90b-225713120708"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CurrentApplicationCountbyDepartmentandMajor() {
        int code = given().when().get(baseURI + "dc86d43a-b2ea-41e6-953b-e408cadf3712"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }

  //Archived: 6/23/2020
    @Test(priority = 1)
    public void CurrentStudentInformationByMajor() {
        int code = given().when().get(baseURI + "f9c0f09b-a191-4185-ba64-6fb51a3066c4"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }

/*  Archived: 6/4/2020
    @Test(priority = 1)
    public void CurrentStudentInformationByMinor() {
        int code = given().when().get(baseURI + "46988752-c245-44fe-a53e-46baa5c312d0"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }*/
    @Test(priority = 1)
    public void CurrentStudentRegistrationCourses() {
        int code = given().when().get(baseURI +
                "b226ad62-76ef-4cdc-87af-618777c6c453").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CurrentStudentTranscriptCourses() {
        int code = given().when().get(baseURI +
                "e4944e4f-a0ac-4cdd-ac3b-61bbcfdb6712"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CurrentStudentTranscriptQuarterlySummary() {
        int code = given().when().get(baseURI +
                "2ff68c12-9553-414a-a676-70898a5f56fa"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CurrentStudentTransferSummary() {
        int code = given().when().get(baseURI +
                "db97494e-95a7-47f3-8d8a-3407821bfd38"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CurriculumCodes() {
        int code = given().when().get(baseURI +
                "c91766da-7665-4f92-87e9-7e1516ccc81a"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void CustomBudgetIndexReportbyOrgCode() {
        int code = given().when().get(baseURI +
                "d8d1ea91-1ac3-48c5-b321-e84751987fbf"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void DACSecuredTablesViewsbyRole() {
        int code = given().when().get(baseURI +
                "2651b5c6-a027-4f06-9d94-df418b8f4082"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void DACSecuredTablesViewsColumns() {
        int code = given().when().get(baseURI +
                "87bee0aa-de15-44c5-8378-bc0189a159d0"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void DailyAidandRefundCheckActivityFile() {
        int code = given().when().get(baseURI +
                "0381f391-d2f9-4305-9211-4f73ef222dd9").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void DegreeCodes() {
        int code = given().when().get(baseURI +
                "3e6a670e-8209-431b-a906-e7f13bcca6af"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void DegreesOrganizationAssociations() {
        int code = given().when().get(baseURI +
                "44ea54e3-2eb5-484c-becc-94162897cd77").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void eFECSEffortReportingRetroactiveSalaryAdjustmentReport() {
        int code = given().when().get(baseURI +
                "e8f08775-f5f7-4364-ac31-7af40b2fed0d"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ExpenseandRevenueTransferReport() {
        int code = given().when().get(baseURI +
                "9eb9eb26-658c-4dd7-a183-107ae1b80f26"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FacilityInformation() {
        int code = given().when().get(baseURI +
                "48835f63-ab79-40fc-bc99-4347a52d2324"
        ).getStatusCode();
        //Assert.assertEquals(code, 200);
        Assert.assertEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FacilityRoomDetail() {
        int code = given().when().get(baseURI +
                "bf1e10f2-11d4-4179-9ec4-7dc5ffb6e59e"
        ).getStatusCode();
        //Assert.assertEquals(code, 200);
        Assert.assertEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FacultyEffortCertification() {
        int code = given().when().get(baseURI +
                "38e46e79-1f00-4a0b-a20c-f87f29e5e8a4"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FASBatchSummaryReport() {
        int code = given().when().get(baseURI +
                "5ef127d9-287b-4563-ba24-1e5275b3f4a0").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FECDashboardActionList() {
        int code = given().when().get(baseURI + "eee0a819-cc94-41a6-969c-021f5bf76e12"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FECDashboardReportbyFECCycle() {
        int code = given().when().get(baseURI +
                "ebe0b73a-8442-46dc-bf75-682b82b690d9"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FECDashboardReportbyOrgCode() {
        int code = given().when().get(baseURI +
                "ffda88cc-146f-4de0-87e2-e47d0d690003"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FiscalYearIncomeandExpenseSummarybyOrgCodeandFunction() {
        int code = given().when().get(baseURI +
                "84ea1a9c-75ed-4f61-9352-29b93fd12841"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FiscalYearIncomeandExpenseSummarybyOrgCodeandFundingSource() {
        int code = given().when().get(baseURI +
                "dc087820-0962-467d-b399-c6dd65b7143d"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FiscalYearIncomeandExpenseSummaryReportMenu() {
        int code = given().when().get(baseURI +
                "64769b8b-1d91-4661-abed-a076c3f2733d"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ForecastingTemplatebyFiscalYearandOrgCode() {
        int code = given().when().get(baseURI +
                "c205da63-1750-48a1-acc4-e7d8c5b5ab9c"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void FreshmanApplicationsScoresandEthnicity() {
        int code = given().when().get(baseURI +
                "c20d810e-c9cf-44ee-9da8-9e348046d0cd"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void AgencyFunds() {
        int code = given().when().get(baseURI +
                "4a261fe6-3735-4d1a-af9d-f28318ffe77e"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GiftBalanceDetailbyBudgetList() {
        int code = given().when().get(baseURI +
                "45fef3a6-53b5-4265-af16-77887516f132").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GiftBalanceDetailbySpendingRestrictionandOrgCode() {
        int code = given().when().get(baseURI +
                "9a736bd7-98dc-4730-889a-507c4cf6ef8f"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GiftBalanceSummarybySpendingRestriction() {
        int code = given().when().get(baseURI +
                "da5ff660-25e7-4871-9a04-fc2a7ed66dbc").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GLSummaryReport() {
        int code = given().when().get(baseURI +
                "a41a23c8-8f76-47d8-88c2-f08094c40c51"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GrantandContractBudgetStatusReport() {
        int code = given().when().get(baseURI +
                "cec8d319-bf4b-4f43-8a18-daff2fc91943").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GrantandContractCertificationReport() {
        int code = given().when().get(baseURI + "93ad3676-d518-40e3-a1e5-8b660519fcde"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GrantandContractDeficitReport() {
        int code = given().when().get(baseURI +
                "6c201688-dd3a-4e47-9f1f-267259043837").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GrantandGiftBudgetsUnassignedtoSpace() {
        int code = given().when().get(baseURI +
                "ad6c965a-e3d1-43e1-bf95-36255a3e20fd"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void PayrollCashMainPage() {
        int code = given().when().get(baseURI +
                "bf3245a0-713e-4d10-8602-c53da294e7aa").getStatusCode();
        //Assert.assertEquals(code, 200);
        Assert.assertEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void HowManyCreditHoursByCollege() {
        int code = given().when().get(baseURI + "48877c10-74f5-4567-8275-9fae628deea2"
        ).getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void HowManyDegreesByCollege() {
        int code = given().when().get(baseURI + "9e22e59e-7f6d-40d0-a0d4-d172a1f4631c").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void HowManyStudentsUniversity() {
        int code = given().when().get(baseURI + "373cbd02-ad76-429b-be79-0da2557bdac7").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void HowManyStudentsByCampus() {
        int code = given().when().get(baseURI + "9b3e4a98-1b3b-40ac-a42a-afc41f485263").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void HowManyStudentsByCollege() {
        int code = given().when().get(baseURI + "0d8bd3d1-ebce-4024-955e-e28947232a30").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void HowManyStudentsByDepartment() {
        int code = given().when().get(baseURI + "49a4eb1d-2dbf-45b1-be7f-f2003b03c8e4").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void HowManyStudentsList() {
        int code = given().when().get(baseURI + "a617f28a-1085-43a4-8e76-a0ef339aa36f").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ImmunizationReport() {
        int code = given().when().get(baseURI + "28159b18-3494-40a1-baa6-f6ce9e98b35a").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void IndirectCostRecoveryEstimator() {
        int code = given().when().get(baseURI + "030cbda6-51db-495e-9cb6-2977b3d1cd6b").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void InternalandExternalSalesSummaryReport() {
        int code = given().when().get(baseURI + "bbe5d906-5245-4a5a-8b37-6318b9e8eb23").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void JournalVoucherDetail() {
        int code = given().when().get(baseURI + "45836886-93c5-4f45-bcec-5b9b52f2aef4").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void LearningSpacesPolicy() {
        int code = given().when().get(baseURI + "9f0bb5b8-ca7d-4d73-ad06-008ff0dad337").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
/*  Archived: 5/11/2020
    @Test(priority = 1)
    public void FacultyOnLeave() {
        int code = given().when().get(baseURI + "57eb1eb7-d905-4b58-bb15-e2e43dee1e2a").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }*/
    @Test(priority = 1)
    public void FECDashboardActionListLegacy() {
        int code = given().when().get(baseURI + "9480881b-3187-486e-af9a-bd3cfc4c9c57").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GLReconcilingReport() {
        int code = given().when().get(baseURI + "6c16b832-4499-4bc8-a698-552bb865db8e").getStatusCode();
        //Assert.assertEquals(code, 200);
        Assert.assertEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void GrantandContractCertificationReportLegacy() {
        int code = given().when().get(baseURI + "5e425602-e263-4558-93fd-56e6560cfa74").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void MajorCodes() {
        int code = given().when().get(baseURI + "1b3d6f08-d3a5-419a-8046-e5f31663aead").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void MinorCodes() {
        int code = given().when().get(baseURI + "c68e0e57-8711-44c3-a1cc-139132dc4c49").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void MiscellaneousStudentAccountsNontuitionChargeSummary() {
        int code = given().when().get(baseURI + "2ad99fff-4c5d-4c70-ab7c-6b18923616d4").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void MyReports() {
        int code = given().when().get(baseURI + "46af4bbf-7fbc-4a06-9c09-f6878c33eb96").getStatusCode();
        //Assert.assertEquals(code, 200);
        Assert.assertEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void MyFDExpenseTransferVolumeReport() {
        int code = given().when().get(baseURI + "4ad810ad-2a9e-4ee5-9f15-847ccc6f5770").getStatusCode();
        //Assert.assertEquals(code, 200);
        Assert.assertEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void NSFCompensationbyFiscalYear() {
        int code = given().when().get(baseURI + "424a91e2-9b46-41a6-b4bb-03bcd21babde").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void OrganizationBudgetRelationshipReport() {
        int code = given().when().get(baseURI + "424a91e2-9b46-41a6-b4bb-03bcd21babde").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void PaidFTEbyFundingSource() {
        int code = given().when().get(baseURI + "31b1281c-6276-4ca4-8826-95aa037c7129").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void PredominantlySponsorFundedFaculty() {
        int code = given().when().get(baseURI + "dfb7d4d0-dbfa-46c7-9814-99afa4f7acb4").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void RemainingBalancebyOrgCodeandCategory() {
        int code = given().when().get(baseURI + "05c00711-165b-4d78-9618-78cd19ea7e52").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ResearchAwards() {
        int code = given().when().get(baseURI + "e0e1d48e-c94d-4d49-aab7-aadccb9d28c5").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ResearchBudgetExpenditures() {
        int code = given().when().get(baseURI + "b4367d5d-30e9-4bfc-bcf5-259269a4c011").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ResearchProposalDetails() {
        int code = given().when().get(baseURI + "48d793a8-40ab-458a-837a-783c10522fd3").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ResearchProposals() {
        int code = given().when().get(baseURI + "f308bc0e-71fb-473d-90c0-cf0e8b53a5c0").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void ResearchSubawards() {
        int code = given().when().get(baseURI + "37ce6b31-bdca-4863-ae98-7879951499ef").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void RoomAssignmentDetailsbyDepartment() {
        int code = given().when().get(baseURI + "8e150b05-8678-48ba-bd6e-430de2b2acac").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void SalaryandWageExpenseDetail() {
        int code = given().when().get(baseURI + "b99a3f76-3c04-4175-bd14-40b4dbad5c86").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void SalaryCapCostShareReport() {
        int code = given().when().get(baseURI + "e070a4e5-9e81-4a13-a8c0-a5e800f93dcf").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void SatisfactoryProgressPolicyReport() {
        int code = given().when().get(baseURI + "1a20ec0a-f586-4276-89fc-3394e8a80a6f").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void SEVISImmigrationReporting() {
        int code = given().when().get(baseURI + "9cba22cc-314f-45dc-95df-6b7599969caf").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void SMATHistory() {
        int code = given().when().get(baseURI + "9fa9d72f-bef4-48d9-bf5a-1fb27ca72a84").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void SpaceDatabyDepartment() {
        int code = given().when().get(baseURI + "2117c782-13bf-4b6e-ba17-aff043712e0d").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void StudentAthletesEarning() {
        int code = given().when().get(baseURI + "b3898a82-318a-4ebf-81f5-911b60a059a3").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void StudentDegreeInformation() {
        int code = given().when().get(baseURI + "79dedeb4-b57a-4ae8-a02f-1725980af499").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void StudentGradeReportbyCourse() {
        int code = given().when().get(baseURI + "68b3fb3b-5352-4727-8b6b-cfcb3684c3f2").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void StudentGradeReportbyMajor() {
        int code = given().when().get(baseURI + "47a40200-1012-4d66-9b97-3afe38712bb6").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void StudentTranscriptDetail() {
        int code = given().when().get(baseURI + "f77e2ae1-8247-45ca-b246-d3b32d31275c").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void StudyAbroadStudents() {
        int code = given().when().get(baseURI + "fbd04b61-b99a-464e-b99c-3670c7d6dbd9").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }

    @Test(priority = 1)
    public void SupplierSearch() {
        int code = given().when().get(baseURI + "1a5ec751-5f00-459b-bfbd-ed11078359a6").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void TimeScheduleInformation() {
        int code = given().when().get(baseURI + "220bd95d-6cbb-4f98-90e1-919e02407216").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }

//    Arichived: EDW-19232
/*    @Test(priority = 1)
    public void MajorsCreditsDegreesByCollege() {
        int code = given().when().get(baseURI + "6fa589fb-d56a-4530-8665-ea4034650de0").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }*/
    @Test(priority = 1)
    public void TransactionSummarybyOrgCode() {
        int code = given().when().get(baseURI + "01ed49cb-de6d-4427-88a2-42f90f8ec91c").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void UndergraduateDegreeApplicants() {
        int code = given().when().get(baseURI + "5b6f609e-a8a8-45d0-8ab5-5d673402b8ec").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void UndergraduatesEligibletoRegister() {
        int code = given().when().get(baseURI + "bbd849b0-fde2-495b-83d8-bf2ba7ff425e").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void UseTaxBienniumSummary() {
        int code = given().when().get(baseURI + "2fecd537-683f-4095-93a0-86de8b553ca6").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void VariableReportingPeriodBudgetSummary() {
        int code = given().when().get(baseURI + "5ef70177-f98c-4bc4-8a2f-ad76516eb488").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void VariableReportingPeriodTransactionSummary() {
        int code = given().when().get(baseURI + "f8c45a5b-c8e3-4526-bf4f-d70506948611").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void WhoAmI() {
        int code = given().when().get(baseURI + "56de9052-ca38-4673-9f66-ca118026b143").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    @Test(priority = 1)
    public void WhoHasAccess() {
        int code = given().when().get(baseURI + "cf5aa954-06b3-4181-a552-72b19250aaf7").getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNotEquals(code, 404);
        Assert.assertNotEquals(code, 500);
    }
    // Not necessary per Moon........... will delete when finished.
    @Test(enabled = false)
    public void apiCount_100PercentSponsorFundedFaculty() {
        int expectedSizeOfList = 8;
        int actualSizeOfList =
                get(baseURI + "97d17dbd-9c3b-46aa-ae0e-39603a32250f")
                        .then().statusCode(200).extract().path("list.size()");
        Assert.assertEquals(expectedSizeOfList, actualSizeOfList);
    }
    @Test(enabled = false)
    public void apiTerms_100PercentSponsorFundedFaculty() {
        given()
                .when()
                .get(baseURI + "97d17dbd-9c3b-46aa-ae0e-39603a32250f")
                .then().statusCode(200)
                .assertThat().body("ParentUid[0]", equalTo("0e939f5c-a287-462d-abdc-d03ea0bc071d"))
                .assertThat().body("Synonyms[0]", equalTo("FEC Cycle"))
                .assertThat().body("Title[0]", equalTo("Faculty Effort Certification Cycle"))
                .assertThat().body("ShortDefinition[0]", IsNull.notNullValue());
    }
    @Test(enabled = false)
    public void apiCount_2013BaseYearPaidFTEReport() {
        int expectedSizeOfList = 5;
        int actualSizeOfList =
                get(baseURI + "97b3f1e0-b17d-448b-86ae-6ed6b432dcd2")
                        .then().statusCode(200).extract().path("list.size()");
        Assert.assertEquals(expectedSizeOfList, actualSizeOfList);
    }
    @Test(enabled = false)
    public void apiTerms_2013BaseYearPaidFTEReport() {
        given()
                .when()
                .get(baseURI + "97b3f1e0-b17d-448b-86ae-6ed6b432dcd2")
                .then().statusCode(200)
                .assertThat().body("ParentUid[0]", equalTo("1b464dc0-f944-4657-aa2e-2f62de52a284"))
                .assertThat().body("Synonyms[0]", equalTo(null))
                .assertThat().body("Title[0]", equalTo("Organization Code"))
                .assertThat().body("ShortDefinition[0]", IsNull.notNullValue());
    }
    // TEMPLATES *********************************
/*    @Test
    public void apiCount_xxxTest() {
        int expectedSizeOfList = 0;
        int actualSizeOfList =
                get(baseURI + "97d17dbd-9c3b-46aa-ae0e-39603a32250f")
                        .then().statusCode(200).extract().path("list.size()");
        Assert.assertEquals(expectedSizeOfList, actualSizeOfList);
    }
    @Test
    public void apiTerms_xxxTest() {
        given()
                .when()
                .get(baseURI + "97d17dbd-9c3b-46aa-ae0e-39603a32250f")
                .then().statusCode(200)
                .assertThat().body("ParentUid[0]", equalTo(""))
                .assertThat().body("Synonyms[0]", equalTo(""))
                .assertThat().body("Title[0]", equalTo(""))
                .assertThat().body("ShortDefinition[0]", IsNull.notNullValue());
    }*/
}
