package restAssuredDefinitionsAPITest;

/*
 *  https://metadata.uw.edu/KNApi/List/Glossary - returns groups of terms
 *  https://metadata.uw.edu/api/reports/all -   returns all reports with report UI
 *  https://metadata.uw.edu/api/reports/terms/{report UID} -  returns all terms related to report UI
 *  https://metadata.uw.edu/Catalog/ViewItem/Term/{ParentUid UID} - loads page in KN for term
 *   and pass that as a parameter in my test method. This will resolve the issue of when the array order changes.
 *
 *  https://wiki.cac.washington.edu/pages/viewpage.action?spaceKey=BI&title=Adding+a+Definitions+Tab+To+BI+Portal
 *
 * notes from Huai:
 * 1) store url in a constant:  ie a FIELD
 *
 * 2) Huai's - compare stored json files for Refactor 2:
 *      https://stackoverflow.com/questions/44716665/full-json-match-with-restassured
 *
 * 3) notes from:  Use the response to verify other parts of the response
 *    https://github.com/rest-assured/rest-assured/wiki/usage#full-bodycontent-matching
 *
 *
 * */

import org.hamcrest.core.IsNull;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class API_ReportsAllTest {

    int reportCount = 113;

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

/*    VALIDATIONS FOR REPORTS - using API position to target report. Unfortunately every time we add or remove
      a report the position for each report changes and causes the test to fail.
      TODO - Need to change the way I access the report Key and Values in next Refactor.
*/

    @Test(priority = 3)
    public void api100PercentSponsorFundedFaculty() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[0]", equalTo("100PercentSponsorFundedFaculty"))
                .assertThat().body("ReportTitle[0]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[0]", equalTo("FIN1065"))
                .assertThat().body("Title[0]", equalTo("100 Percent Sponsor-Funded Faculty"))
                .assertThat().body("Uid[0]", equalTo("97d17dbd-9c3b-46aa-ae0e-39603a32250f"))
                .assertThat().body("ShortDefinition[0]", IsNull.notNullValue());
    }

    @Test
    public void apiAdvanceBudgetNumberReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[1]", equalTo("AdvanceBudgetNumberReport"))
                .assertThat().body("ReportTitle[1]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[1]", equalTo("FIN1066"))
                .assertThat().body("Title[1]", equalTo("Advance Budget Number Report"))
                .assertThat().body("Uid[1]", equalTo("f93c0249-c281-45de-b574-865736dc928e"))
                .assertThat().body("ShortDefinition[1]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiAffixedPaymentDetailTest() {

        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[2]", equalTo("AffixedPaymentDetail"))
                .assertThat().body("ReportTitle[2]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[2]", equalTo("ACAD1014"))
                .assertThat().body("Title[2]", equalTo("Affixed Payment Detail"))
                .assertThat().body("Uid[2]", equalTo("d4ffff98-b8a1-4735-90d4-cad45cf3ae31"))
                .assertThat().body("ShortDefinition[2]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiAidAuthorizedandDisbursedbyYear() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[3]", equalTo("AidAuthorizedandDisbursedbyYear"))
                .assertThat().body("ReportTitle[3]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[3]", equalTo("ACAD1020"))
                .assertThat().body("Title[3]", equalTo("Aid Authorization and Disbursement by Year"))
                .assertThat().body("Uid[3]", equalTo("80f71488-d76c-4fa8-b6ef-6971075bd55b"))
                .assertThat().body("ShortDefinition[3]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiAidAuthorizedandDisbursedbyQuarter() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[4]", equalTo("AidAuthorizedandDisbursedbyQuarter"))
                .assertThat().body("ReportTitle[4]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[4]", equalTo("ACAD1038"))
                //TODO: The text string in the api call states "Aid Authorized and Disbu…or Aid Year and Quarter"
                .assertThat().body("Title[4]", equalTo("Aid Authorized and Disbursed for Aid Year and Quarter"))
                .assertThat().body("Uid[4]", equalTo("feada1f3-ea53-4190-ab0b-278b9d13d50b"))
                .assertThat().body("ShortDefinition[4]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiAllApplicationsTest() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[5]", equalTo("AllApplications"))
                .assertThat().body("ReportTitle[5]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[5]", equalTo("ACAD1087"))
                .assertThat().body("Title[5]", equalTo("All Applications"))
                .assertThat().body("Uid[5]", equalTo("81fbc804-94cc-4347-8d30-faabfb8ea3b2"))
                .assertThat().body("ShortDefinition[5]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiBienniumToDateBudgetBalances() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[6]", equalTo("BienniumToDateBudgetBalances"))
                .assertThat().body("ReportTitle[6]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[6]", equalTo("FIN1079"))
                .assertThat().body("Title[6]", equalTo("Biennium To Date Budget Balances"))
                .assertThat().body("Uid[6]", equalTo("c032f3f5-3852-4df9-815e-2dbf11c0bb4f"))
                .assertThat().body("ShortDefinition[6]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiBudgetSummaryByOrgCode() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[7]", equalTo("BudgetSummaryByOrgCode"))
                .assertThat().body("ReportTitle[7]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[7]", equalTo("FIN1016"))
                .assertThat().body("Title[7]", equalTo("Budget Summary by OrgCode"))
                .assertThat().body("Uid[7]", equalTo("e4d88596-a7da-4e50-b1f2-9822adecb8fc"))
                .assertThat().body("ShortDefinition[7]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiBudgetTypeClassMatrix() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[8]", equalTo("BudgetTypeClassMatrix"))
                .assertThat().body("ReportTitle[8]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[8]", equalTo("FIN1027"))
                .assertThat().body("Title[8]", equalTo("Budget Type Class Matrix"))
                .assertThat().body("Uid[8]", equalTo("40787751-bfb7-4850-b857-8779deab8950"))
                .assertThat().body("ShortDefinition[8]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiBuildingSummarybyOrgCode() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[9]", equalTo("BuildingSummarybyOrgCode"))
                .assertThat().body("ReportTitle[9]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[9]", equalTo("MSTR1009"))
                .assertThat().body("Title[9]", equalTo("Building Summary by OrgCode"))
                .assertThat().body("Uid[9]", equalTo("a83e106e-f939-4784-ae4b-6ceb838c479a"))
                .assertThat().body("ShortDefinition[9]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiBuildingSummarybyPrimaryUse() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[10]", equalTo("BuildingSummarybyPrimaryUse"))
                .assertThat().body("ReportTitle[10]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[10]", equalTo("MSTR1010"))
                .assertThat().body("Title[10]", equalTo("Building Summary by Primary Use"))
                .assertThat().body("Uid[10]", equalTo("a150a1fa-dc43-413e-bb1d-903ca89c01fe"))
                .assertThat().body("ShortDefinition[10]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCampusApplicationCountsComparison() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[11]", equalTo("CampusApplicationCountsComparison"))
                .assertThat().body("ReportTitle[11]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[11]", equalTo("ACAD1084"))
                .assertThat().body("Title[11]", equalTo("Campus Application Counts Comparison"))
                .assertThat().body("Uid[11]", equalTo("ec0be166-1eca-41dc-8059-6c6328dd538a"))
                .assertThat().body("ShortDefinition[11]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCashierActivityReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[12]", equalTo("CashierActivityReport"))
                .assertThat().body("ReportTitle[12]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[12]", equalTo("ACAD1041"))
                .assertThat().body("Title[12]", equalTo("Cashier Activity Report - Payments Received"))
                .assertThat().body("Uid[12]", equalTo("fdb2a0f2-c636-4f41-a06b-57f4607c4a8c"))
                .assertThat().body("ShortDefinition[12]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiClassListByCurriculumCourseSectionTest() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[13]", equalTo("ClassListByCurriculumCourseSection"))
                .assertThat().body("ReportTitle[13]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[13]", equalTo("ACAD1034"))
                .assertThat().body("Title[13]", equalTo("Class List By Curriculum Course Section"))
                .assertThat().body("Uid[13]", equalTo("15b82ff5-6f82-4e1f-9392-436b898c87ee"))
                .assertThat().body("ShortDefinition[13]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCollegeSpaceDatabyFacility() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[14]", equalTo("CollegeSpaceDatabyFacility"))
                .assertThat().body("ReportTitle[14]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[14]", equalTo("MSTR1012"))
                .assertThat().body("Title[14]", equalTo("College Space Data by Facility"))
                .assertThat().body("Uid[14]", equalTo("413ec093-3589-4380-85b4-64e849139493"))
                .assertThat().body("ShortDefinition[14]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCollegeSpaceDatabyOrgCode() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[15]", equalTo("CollegeSpaceDatabyOrgCode"))
                .assertThat().body("ReportTitle[15]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[15]", equalTo("MSTR1011"))
                .assertThat().body("Title[15]", equalTo("College Space Data by OrgCode"))
                .assertThat().body("Uid[15]", equalTo("ac51b2f8-d0bb-4afb-9dab-9b5bd346a09b"))
                .assertThat().body("ShortDefinition[15]", IsNull.notNullValue());
    }

    // Archived: 10/1/2019 3:28 PM
    public void apiAcademicOrganizationHierarchy() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[16]", equalTo("AcademicOrganizationHierarchy"))
                .assertThat().body("ReportTitle[16]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[16]", equalTo("MSTR1001"))
                .assertThat().body("Title[16]", equalTo("Comparison of SDB & PNB Organization Hierarchies"))
                .assertThat().body("Uid[16]", equalTo("6d748cf0-0be0-4b9c-bbc0-fb3c1a7a04f8"))
                .assertThat().body("ShortDefinition[16]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiConsolidatedBudgetStatusReportMenu() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[16]", equalTo("ConsolidatedBudgetStatusReportMenu"))
                .assertThat().body("ReportTitle[16]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[16]", equalTo("FIN1021"))
                .assertThat().body("Title[16]", equalTo("Consolidated Budget Status Report Menu"))
                .assertThat().body("Uid[16]", equalTo("e8378a86-41fe-4771-b9a1-24b99e431f3b"))
                .assertThat().body("ShortDefinition[16]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiContactInformationForUnmetRequest() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[17]", equalTo("ContactInformationForUnmetRequest"))
                .assertThat().body("ReportTitle[17]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[17]", equalTo("ACAD1017"))
                .assertThat().body("Title[17]", equalTo("Contact Information - Unmet Requests"))
                .assertThat().body("Uid[17]", equalTo("2404f719-41cf-4aaf-92ea-c536055e9aaa"))
                .assertThat().body("ShortDefinition[17]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCostShareCommitmentsandContributions() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[18]", equalTo("CostShareCommitmentsandContributions"))
                .assertThat().body("ReportTitle[18]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[18]", equalTo("FIN1083"))
                .assertThat().body("Title[18]", equalTo("Cost Share Commitments and Contributions"))
                .assertThat().body("Uid[18]", equalTo("843ae532-d81e-43b3-944e-30e7b94d1a69"))
                .assertThat().body("ShortDefinition[18]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCourseSectionFillRatesTest() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[19]", equalTo("CourseSectionFillRates"))
                .assertThat().body("ReportTitle[19]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[19]", equalTo("ACAD1048"))
                .assertThat().body("Title[19]", equalTo("Course Section Fill Rate"))
                .assertThat().body("Uid[19]", equalTo("f998e2d9-8cb4-4429-b90b-225713120708"))
                .assertThat().body("ShortDefinition[19]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCurrentApplicationCountbyDepartmentandMajor() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[20]", equalTo("CurrentApplicationCountbyDepartmentandMajor"))
                .assertThat().body("ReportTitle[20]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[20]", equalTo("ACAD1088"))
                .assertThat().body("Title[20]", equalTo("Current Application Count by Department and Major"))
                .assertThat().body("Uid[20]", equalTo("dc86d43a-b2ea-41e6-953b-e408cadf3712"))
                .assertThat().body("ShortDefinition[20]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCurrentStudentInformation() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[21]", equalTo("CurrentStudentInformation"))
                .assertThat().body("ReportTitle[21]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[21]", equalTo("ACAD1097"))
                .assertThat().body("Title[21]", equalTo("Current Student Information"))
                .assertThat().body("Uid[21]", equalTo("73b35e15-f3fc-4250-93ea-98f81c69c03d"))
                .assertThat().body("ShortDefinition[21]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCurrentStudentInformationByMajor() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[22]", equalTo("CurrentStudentInformationByMajor"))
                .assertThat().body("ReportTitle[22]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[22]", equalTo("ACAD1026"))
                .assertThat().body("Title[22]", equalTo("Current Student Information By Major"))
                .assertThat().body("Uid[22]", equalTo("f9c0f09b-a191-4185-ba64-6fb51a3066c4"))
                .assertThat().body("ShortDefinition[22]", IsNull.notNullValue());
    }

    // Archived
    public void apiCurrentStudentInformationByMinor() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[23]", equalTo("CurrentStudentInformationByMinor"))
                .assertThat().body("ReportTitle[23]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[23]", equalTo("ACAD1029"))
                .assertThat().body("Title[23]", equalTo("Current Student Information By Minor"))
                .assertThat().body("Uid[23]", equalTo("46988752-c245-44fe-a53e-46baa5c312d0"))
                .assertThat().body("ShortDefinition[23]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCurrentStudentRegistrationCourses() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[23]", equalTo("CurrentStudentRegistrationCourses"))
                .assertThat().body("ReportTitle[23]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[23]", equalTo("ACAD1030"))
                .assertThat().body("Title[23]", equalTo("Current Student Registration Courses"))
                .assertThat().body("Uid[23]", equalTo("b226ad62-76ef-4cdc-87af-618777c6c453"))
                .assertThat().body("ShortDefinition[23]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCurrentStudentTranscriptCourses() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[24]", equalTo("CurrentStudentTranscriptCourses"))
                .assertThat().body("ReportTitle[24]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[24]", equalTo("ACAD1027"))
                .assertThat().body("Title[24]", equalTo("Current Student Transcript Courses"))
                .assertThat().body("Uid[24]", equalTo("e4944e4f-a0ac-4cdd-ac3b-61bbcfdb6712"))
                .assertThat().body("ShortDefinition[24]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCurrentStudentTranscriptQuarterlySummary() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[25]", equalTo("CurrentStudentTranscriptQuarterlySummary"))
                .assertThat().body("ReportTitle[25]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[25]", equalTo("ACAD1031"))
                .assertThat().body("Title[25]", equalTo("Current Student Transcript Quarterly Summary"))
                .assertThat().body("Uid[25]", equalTo("2ff68c12-9553-414a-a676-70898a5f56fa"))
                .assertThat().body("ShortDefinition[25]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCurrentStudentTransferSummary() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[26]", equalTo("CurrentStudentTransferSummary"))
                .assertThat().body("ReportTitle[26]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[26]", equalTo("ACAD1032"))
                .assertThat().body("Title[26]", equalTo("Current Student Transfer Summary"))
                .assertThat().body("Uid[26]", equalTo("db97494e-95a7-47f3-8d8a-3407821bfd38"))
                .assertThat().body("ShortDefinition[26]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCurriculumCodes() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[27]", equalTo("CurriculumCodes"))
                .assertThat().body("ReportTitle[27]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[27]", equalTo("ACAD1045"))
                .assertThat().body("Title[27]", equalTo("Curriculum Codes"))
                .assertThat().body("Uid[27]", equalTo("c91766da-7665-4f92-87e9-7e1516ccc81a"))
                .assertThat().body("ShortDefinition[27]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiCustomBudgetIndexReportbyOrgCode() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[28]", equalTo("CustomBudgetIndexReportbyOrgCode"))
                .assertThat().body("ReportTitle[28]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[28]", equalTo("FIN1012"))
                .assertThat().body("Title[28]", equalTo("Custom Budget Index Report by OrgCode"))
                .assertThat().body("Uid[28]", equalTo("d8d1ea91-1ac3-48c5-b321-e84751987fbf"))
                .assertThat().body("ShortDefinition[28]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiDACSecuredTablesViewsbyRole() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[29]", equalTo("DACSecuredTablesViewsbyRole"))
                .assertThat().body("ReportTitle[29]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[29]", equalTo("ADMIN1008"))
                .assertThat().body("Title[29]", equalTo("DAC Secured Tables, Views And Columns Overview by Roles"))
                .assertThat().body("Uid[29]", equalTo("2651b5c6-a027-4f06-9d94-df418b8f4082"))
                .assertThat().body("ShortDefinition[29]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiDACSecuredTablesViewsColumns() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[30]", equalTo("DACSecuredTablesViewsColumns"))
                .assertThat().body("ReportTitle[30]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[30]", equalTo("ADMIN1005"))
                .assertThat().body("Title[30]", equalTo("DAC-Secured Tables, Views And Columns"))
                .assertThat().body("Uid[30]", equalTo("87bee0aa-de15-44c5-8378-bc0189a159d0"))
                .assertThat().body("ShortDefinition[30]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiDailyAidandRefundCheckActivityFile() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[31]", equalTo("DailyAidandRefundCheckActivityFile"))
                .assertThat().body("ReportTitle[31]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[31]", equalTo("ACAD1042"))
                .assertThat().body("Title[31]", equalTo("Daily Aid and Refund Check Activity File"))
                .assertThat().body("Uid[31]", equalTo("0381f391-d2f9-4305-9211-4f73ef222dd9"))
                .assertThat().body("ShortDefinition[31]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiDegreeCodes() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[32]", equalTo("DegreeCodes"))
                .assertThat().body("ReportTitle[32]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[32]", equalTo("ACAD1021"))
                .assertThat().body("Title[32]", equalTo("Degree Codes"))
                .assertThat().body("Uid[32]", equalTo("3e6a670e-8209-431b-a906-e7f13bcca6af"))
                .assertThat().body("ShortDefinition[32]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiDegreesOrganizationAssociations() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[33]", equalTo("DegreesOrganizationAssociations"))
                .assertThat().body("ReportTitle[33]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[33]", equalTo("MSTR1007"))
                .assertThat().body("Title[33]", equalTo("Degrees and Organization Associations"))
                .assertThat().body("Uid[33]", equalTo("44ea54e3-2eb5-484c-becc-94162897cd77"))
                .assertThat().body("ShortDefinition[33]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apieFECSEffortReportingRetroactiveSalaryAdjustmentReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[34]", equalTo("eFECSEffortReportingRetroactiveSalaryAdjustmentReport"))
                .assertThat().body("ReportTitle[34]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[34]", equalTo("FIN1059"))
                .assertThat().body("Title[34]", equalTo("eFECS Effort Reporting Retroactive Salary Adjustment Report"))
                .assertThat().body("Uid[34]", equalTo("e8f08775-f5f7-4364-ac31-7af40b2fed0d"))
                .assertThat().body("ShortDefinition[34]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiExpenseandRevenueTransferReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[35]", equalTo("ExpenseandRevenueTransferReport"))
                .assertThat().body("ReportTitle[35]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[35]", equalTo("FIN1010"))
                .assertThat().body("Title[35]", equalTo("Expense and Revenue Transfer Report"))
                .assertThat().body("Uid[35]", equalTo("9eb9eb26-658c-4dd7-a183-107ae1b80f26"))
                .assertThat().body("ShortDefinition[35]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFacilityInformation() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[36]", equalTo("FacilityInformation"))
                .assertThat().body("ReportTitle[36]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[36]", equalTo("MSTR1013"))
                .assertThat().body("Title[36]", equalTo("Facility Information"))
                .assertThat().body("Uid[36]", equalTo("48835f63-ab79-40fc-bc99-4347a52d2324"))
                .assertThat().body("ShortDefinition[36]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFacilityRoomDetail() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[37]", equalTo("FacilityRoomDetail"))
                .assertThat().body("ReportTitle[37]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[37]", equalTo("MSTR1014"))
                .assertThat().body("Title[37]", equalTo("Facility Room Detail"))
                .assertThat().body("Uid[37]", equalTo("bf1e10f2-11d4-4179-9ec4-7dc5ffb6e59e"))
                .assertThat().body("ShortDefinition[37]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFacultyEffortCertificationFECDashboard() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[38]", equalTo("FacultyEffortCertification(FEC)Dashboard"))
                .assertThat().body("ReportTitle[38]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[38]", equalTo("FIN1063"))
                .assertThat().body("Title[38]", equalTo("Faculty Effort Certification (FEC) Dashboard"))
                .assertThat().body("Uid[38]", equalTo("38e46e79-1f00-4a0b-a20c-f87f29e5e8a4"))
                .assertThat().body("ShortDefinition[38]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFASBatchSummaryReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[39]", equalTo("FASBatchSummaryReport"))
                .assertThat().body("ReportTitle[39]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[39]", equalTo("FIN1024"))
                .assertThat().body("Title[39]", equalTo("FAS Batch Summary Report"))
                .assertThat().body("Uid[39]", equalTo("5ef127d9-287b-4563-ba24-1e5275b3f4a0"))
                .assertThat().body("ShortDefinition[39]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFECDashboardActionList() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[40]", equalTo("FECDashboardActionList"))
                .assertThat().body("ReportTitle[40]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[40]", equalTo("FIN1068"))
                .assertThat().body("Title[40]", equalTo("FEC Dashboard Action List"))
                .assertThat().body("Uid[40]", equalTo("eee0a819-cc94-41a6-969c-021f5bf76e12"))
                .assertThat().body("ShortDefinition[40]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFECDashboardReportbyFECCycle() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[41]", equalTo("FECDashboardReportbyFECCycle"))
                .assertThat().body("ReportTitle[41]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[41]", equalTo("FIN1067"))
                .assertThat().body("Title[41]", equalTo("FEC Dashboard Report by FEC Cycle"))
                .assertThat().body("Uid[41]", equalTo("ebe0b73a-8442-46dc-bf75-682b82b690d9"))
                .assertThat().body("ShortDefinition[41]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFECDashboardReportbyOrgCode() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[42]", equalTo("FECDashboardReportbyOrgCode"))
                .assertThat().body("ReportTitle[42]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[42]", equalTo("FIN1064"))
                .assertThat().body("Title[42]", equalTo("FEC Dashboard Report by OrgCode"))
                .assertThat().body("Uid[42]", equalTo("ffda88cc-146f-4de0-87e2-e47d0d690003"))
                .assertThat().body("ShortDefinition[42]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFiscalYearIncomeandExpenseSummarybyOrgCodeandFunction() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[43]", equalTo("FiscalYearIncomeandExpenseSummarybyOrgCodeandFunction"))
                .assertThat().body("ReportTitle[43]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[43]", equalTo("FIN1070"))
                .assertThat().body("Title[43]", equalTo("Fiscal Year Income and Expense Summary by OrgCode and Function"))
                .assertThat().body("Uid[43]", equalTo("84ea1a9c-75ed-4f61-9352-29b93fd12841"))
                .assertThat().body("ShortDefinition[43]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFiscalYearIncomeandExpenseSummarybyOrgCodeandFundingSource() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[44]", equalTo("FiscalYearIncomeandExpenseSummarybyOrgCodeandFundingSource"))
                .assertThat().body("ReportTitle[44]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[44]", equalTo("FIN1053"))
                .assertThat().body("Title[44]", equalTo("Fiscal Year Income and Expense Summary by OrgCode and Funding Source"))
                .assertThat().body("Uid[44]", equalTo("dc087820-0962-467d-b399-c6dd65b7143d"))
                .assertThat().body("ShortDefinition[44]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFiscalYearIncomeandExpenseSummaryReportMenu() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[45]", equalTo("FiscalYearIncomeandExpenseSummaryReportMenu"))
                .assertThat().body("ReportTitle[45]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[45]", equalTo("FIN1069"))
                .assertThat().body("Title[45]", equalTo("Fiscal Year Income and Expense Summary Report Menu"))
                .assertThat().body("Uid[45]", equalTo("64769b8b-1d91-4661-abed-a076c3f2733d"))
                .assertThat().body("ShortDefinition[45]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiForecastingTemplatebyFiscalYearandOrgCode() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[46]", equalTo("ForecastingTemplatebyFiscalYearandOrgCode"))
                .assertThat().body("ReportTitle[46]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[46]", equalTo("FIN1052"))
                .assertThat().body("Title[46]", equalTo("Forecasting Template by Fiscal Year and OrgCode"))
                .assertThat().body("Uid[46]", equalTo("c205da63-1750-48a1-acc4-e7d8c5b5ab9c"))
                .assertThat().body("ShortDefinition[46]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFreshmanApplicationsScoresandEthnicity() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[47]", equalTo("FreshmanApplicationsScoresandEthnicity"))
                .assertThat().body("ReportTitle[47]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[47]", equalTo("ACAD1085"))
                .assertThat().body("Title[47]", equalTo("Freshman Applications Scores and Ethnicity"))
                .assertThat().body("Uid[47]", equalTo("c20d810e-c9cf-44ee-9da8-9e348046d0cd"))
                .assertThat().body("ShortDefinition[47]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiAgencyFunds() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[48]", equalTo("AgencyFunds"))
                .assertThat().body("ReportTitle[48]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[48]", equalTo("FIN1078"))
                .assertThat().body("Title[48]", equalTo("Fund Group 70 Agency Funds"))
                .assertThat().body("Uid[48]", equalTo("4a261fe6-3735-4d1a-af9d-f28318ffe77e"))
                .assertThat().body("ShortDefinition[48]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGiftBalanceDetailbyBudgetList() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[49]", equalTo("GiftBalanceDetailbyBudgetList"))
                .assertThat().body("ReportTitle[49]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[49]", equalTo("FIN1076"))
                .assertThat().body("Title[49]", equalTo("Gift Balance Detail by Budget List"))
                .assertThat().body("Uid[49]", equalTo("45fef3a6-53b5-4265-af16-77887516f132"))
                .assertThat().body("ShortDefinition[49]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGiftBalanceDetailbySpendingRestrictionandOrgCode() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[50]", equalTo("GiftBalanceDetailbySpendingRestrictionandOrgCode"))
                .assertThat().body("ReportTitle[50]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[50]", equalTo("FIN1057"))
                .assertThat().body("Title[50]", equalTo("Gift Balance Detail by Spending Restriction and OrgCode"))
                .assertThat().body("Uid[50]", equalTo("9a736bd7-98dc-4730-889a-507c4cf6ef8f"))
                .assertThat().body("ShortDefinition[50]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGiftBalanceSummarybySpendingRestriction() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[51]", equalTo("GiftBalanceSummarybySpendingRestriction"))
                .assertThat().body("ReportTitle[51]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[51]", equalTo("FIN1056"))
                .assertThat().body("Title[51]", equalTo("Gift Balance Summary by Spending Restriction"))
                .assertThat().body("Uid[51]", equalTo("da5ff660-25e7-4871-9a04-fc2a7ed66dbc"))
                .assertThat().body("ShortDefinition[51]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGLSummaryReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[52]", equalTo("GLSummaryReport"))
                .assertThat().body("ReportTitle[52]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[52]", equalTo("GL1026"))
                .assertThat().body("Title[52]", equalTo("GL Summary Report"))
                .assertThat().body("Uid[52]", equalTo("a41a23c8-8f76-47d8-88c2-f08094c40c51"))
                .assertThat().body("ShortDefinition[52]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGLTransactionsbyMonth() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[53]", equalTo("GLTransactionsbyMonth"))
                .assertThat().body("ReportTitle[53]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[53]", equalTo("GL1027"))
                .assertThat().body("Title[53]", equalTo("GL Transactions by Month"))
                .assertThat().body("Uid[53]", equalTo("4aadf72b-bd9b-46ab-bb7c-3eec61b98c13"))
                .assertThat().body("ShortDefinition[53]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGrantandContractBudgetStatusReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[54]", equalTo("GrantandContractBudgetStatusReport"))
                .assertThat().body("ReportTitle[54]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[54]", equalTo("FIN1073"))
                .assertThat().body("Title[54]", equalTo("Grant and Contract Budget Status Report"))
                .assertThat().body("Uid[54]", equalTo("cec8d319-bf4b-4f43-8a18-daff2fc91943"))
                .assertThat().body("ShortDefinition[54]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGrantandContractCertificationReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[55]", equalTo("GrantandContractCertificationReport"))
                .assertThat().body("ReportTitle[55]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[55]", equalTo("FIN1008"))
                .assertThat().body("Title[55]", equalTo("Grant and Contract Certification Report"))
                .assertThat().body("Uid[55]", equalTo("93ad3676-d518-40e3-a1e5-8b660519fcde"))
                .assertThat().body("ShortDefinition[55]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGrantandContractDeficitReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[56]", equalTo("GrantandContractDeficitReport"))
                .assertThat().body("ReportTitle[56]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[56]", equalTo("FIN1060"))
                .assertThat().body("Title[56]", equalTo("Grant and Contract Deficit Report"))
                .assertThat().body("Uid[56]", equalTo("6c201688-dd3a-4e47-9f1f-267259043837"))
                .assertThat().body("ShortDefinition[56]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGrantandGiftBudgetsUnassignedtoSpace() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[57]", equalTo("GrantandGiftBudgetsUnassignedtoSpace"))
                .assertThat().body("ReportTitle[57]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[57]", equalTo("MSTR1016"))
                .assertThat().body("Title[57]", equalTo("Grant and Gift Budgets Unassigned to Space"))
                .assertThat().body("Uid[57]", equalTo("ad6c965a-e3d1-43e1-bf95-36255a3e20fd"))
                .assertThat().body("ShortDefinition[57]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiPayrollCashMainPage() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[58]", equalTo("PayrollCashMainPage"))
                .assertThat().body("ReportTitle[58]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[58]", equalTo("FIN1041"))
                .assertThat().body("Title[58]", equalTo("Hepps Payroll and GL Payroll Cash Account"))
                .assertThat().body("Uid[58]", equalTo("bf3245a0-713e-4d10-8602-c53da294e7aa"))
                .assertThat().body("ShortDefinition[58]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiHowManyCreditHoursByCollege() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[59]", equalTo("HowManyCreditHoursByCollege"))
                .assertThat().body("ReportTitle[59]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[59]", equalTo("ACAD1005"))
                .assertThat().body("Title[59]", equalTo("How Many Credit Hours Are Being Taught"))
                .assertThat().body("Uid[59]", equalTo("48877c10-74f5-4567-8275-9fae628deea2"))
                .assertThat().body("ShortDefinition[59]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiHowManyDegreesByCollege() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[60]", equalTo("HowManyDegreesByCollege"))
                .assertThat().body("ReportTitle[60]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[60]", equalTo("ACAD1016"))
                .assertThat().body("Title[60]", equalTo("How Many Degrees Have I Granted"))
                .assertThat().body("Uid[60]", equalTo("9e22e59e-7f6d-40d0-a0d4-d172a1f4631c"))
                .assertThat().body("ShortDefinition[60]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiHowManyStudentsUniversity() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[61]", equalTo("HowManyStudentsUniversity"))
                .assertThat().body("ReportTitle[61]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[61]", equalTo("ACAD1003"))
                .assertThat().body("Title[61]", equalTo("How Many Students at the University"))
                .assertThat().body("Uid[61]", equalTo("373cbd02-ad76-429b-be79-0da2557bdac7"))
                .assertThat().body("ShortDefinition[61]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiHowManyStudentsByCampus() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[62]", equalTo("HowManyStudentsByCampus"))
                .assertThat().body("ReportTitle[62]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[62]", equalTo("ACAD1002"))
                .assertThat().body("Title[62]", equalTo("How Many Students By Campus"))
                .assertThat().body("Uid[62]", equalTo("9b3e4a98-1b3b-40ac-a42a-afc41f485263"))
                .assertThat().body("ShortDefinition[62]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiHowManyStudentsByCollege() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[63]", equalTo("HowManyStudentsByCollege"))
                .assertThat().body("ReportTitle[63]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[63]", equalTo("ACAD1001"))
                .assertThat().body("Title[63]", equalTo("How Many Students By College"))
                .assertThat().body("Uid[63]", equalTo("0d8bd3d1-ebce-4024-955e-e28947232a30"))
                .assertThat().body("ShortDefinition[63]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiHowManyStudentsByDepartment() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[64]", equalTo("HowManyStudentsByDepartment"))
                .assertThat().body("ReportTitle[64]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[64]", equalTo("ACAD1007"))
                .assertThat().body("Title[64]", equalTo("How Many Students By Department"))
                .assertThat().body("Uid[64]", equalTo("49a4eb1d-2dbf-45b1-be7f-f2003b03c8e4"))
                .assertThat().body("ShortDefinition[64]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiHowManyStudentsListTest() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[65]", equalTo("HowManyStudentsList"))
                .assertThat().body("ReportTitle[65]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[65]", equalTo("ACAD1008"))
                .assertThat().body("Title[65]", equalTo("How Many Students List"))
                .assertThat().body("Uid[65]", equalTo("a617f28a-1085-43a4-8e76-a0ef339aa36f"))
                .assertThat().body("ShortDefinition[65]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiImmunizationReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[66]", equalTo("ImmunizationReport"))
                .assertThat().body("ReportTitle[66]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[66]", equalTo("ACAD1096"))
                .assertThat().body("Title[66]", equalTo("Immunization Report"))
                .assertThat().body("Uid[66]", equalTo("28159b18-3494-40a1-baa6-f6ce9e98b35a"))
                .assertThat().body("ShortDefinition[66]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiIndirectCostRecoveryEstimator() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[67]", equalTo("IndirectCostRecoveryEstimator"))
                .assertThat().body("ReportTitle[67]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[67]", equalTo("FIN1074"))
                .assertThat().body("Title[67]", equalTo("Indirect Cost Recovery Estimator"))
                .assertThat().body("Uid[67]", equalTo("030cbda6-51db-495e-9cb6-2977b3d1cd6b"))
                .assertThat().body("ShortDefinition[67]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiInternalandExternalSalesSummaryReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[68]", equalTo("InternalandExternalSalesSummaryReport"))
                .assertThat().body("ReportTitle[68]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[68]", equalTo("FIN1072"))
                .assertThat().body("Title[68]", equalTo("Internal and External Sales Summary Report"))
                .assertThat().body("Uid[68]", equalTo("bbe5d906-5245-4a5a-8b37-6318b9e8eb23"))
                .assertThat().body("ShortDefinition[68]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiJournalVoucherDetail() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[69]", equalTo("JournalVoucherDetail"))
                .assertThat().body("ReportTitle[69]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[69]", equalTo("FIN1054"))
                .assertThat().body("Title[69]", equalTo("Journal Voucher Detail"))
                .assertThat().body("Uid[69]", equalTo("45836886-93c5-4f45-bcec-5b9b52f2aef4"))
                .assertThat().body("ShortDefinition[69]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiLearningSpacesPolicy() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[70]", equalTo("LearningSpacesPolicy"))
                .assertThat().body("ReportTitle[70]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[70]", equalTo("ACAD1094"))
                .assertThat().body("Title[70]", equalTo("Learning Spaces Policy"))
                .assertThat().body("Uid[70]", equalTo("9f0bb5b8-ca7d-4d73-ad06-008ff0dad337"))
                .assertThat().body("ShortDefinition[70]", IsNull.notNullValue());
    }

    // Archived: 5/11/2020 1:22 PM
    public void apiFacultyOnLeave() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[73]", equalTo("FacultyOnLeave"))
                .assertThat().body("ReportTitle[73]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[73]", equalTo("APL1007"))
                .assertThat().body("Title[73]", equalTo("Legacy Faculty On Leave"))
                .assertThat().body("Uid[73]", equalTo("57eb1eb7-d905-4b58-bb15-e2e43dee1e2a"))
                .assertThat().body("ShortDefinition[73]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiFECDashboardActionListLegacy() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[71]", equalTo("FECDashboardActionListLegacy"))
                .assertThat().body("ReportTitle[71]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[71]", equalTo("FINL1068"))
                .assertThat().body("Title[71]", equalTo("Legacy FEC Dashboard Action List"))
                .assertThat().body("Uid[71]", equalTo("9480881b-3187-486e-af9a-bd3cfc4c9c57"))
                .assertThat().body("ShortDefinition[71]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGLReconcilingReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[72]", equalTo("GLReconcilingReport"))
                .assertThat().body("ReportTitle[72]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[72]", equalTo("FINL1035"))
                .assertThat().body("Title[72]", equalTo("Legacy GL Payroll Cash Activities in a Month: Major Payroll Runs"))
                .assertThat().body("Uid[72]", equalTo("6c16b832-4499-4bc8-a698-552bb865db8e"))
                .assertThat().body("ShortDefinition[72]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiGrantandContractCertificationReportLegacy() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[73]", equalTo("GrantandContractCertificationReportLegacy"))
                .assertThat().body("ReportTitle[73]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[73]", equalTo("FINL1008"))
                .assertThat().body("Title[73]", equalTo("Legacy Grant and Contract Certification Report"))
                .assertThat().body("Uid[73]", equalTo("5e425602-e263-4558-93fd-56e6560cfa74"))
                .assertThat().body("ShortDefinition[73]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiEmployeeSearch() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[74]", equalTo("EmployeeSearch"))
                .assertThat().body("ReportTitle[74]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[74]", equalTo("PAYRL1029"))
                .assertThat().body("Title[74]", equalTo("Legacy HR/P Employee Check and Funding History Report"))
                .assertThat().body("Uid[74]", equalTo("a9b98a6b-04d9-4ce4-a99d-38748f51b1f5"))
                .assertThat().body("ShortDefinition[74]", IsNull.notNullValue());
    }


    @Test(priority = 3)
    public void apiMajorCodes() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[75]", equalTo("MajorCodes"))
                .assertThat().body("ReportTitle[75]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[75]", equalTo("ACAD1039"))
                .assertThat().body("Title[75]", equalTo("Major Codes"))
                .assertThat().body("Uid[75]", equalTo("1b3d6f08-d3a5-419a-8046-e5f31663aead"))
                .assertThat().body("ShortDefinition[75]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiMinorCodes() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[76]", equalTo("MinorCodes"))
                .assertThat().body("ReportTitle[76]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[76]", equalTo("ACAD1044"))
                .assertThat().body("Title[76]", equalTo("Minor Codes"))
                .assertThat().body("Uid[76]", equalTo("c68e0e57-8711-44c3-a1cc-139132dc4c49"))
                .assertThat().body("ShortDefinition[76]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiMiscellaneousStudentAccountsNontuitionChargeSummary() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[77]", equalTo("MiscellaneousStudentAccountsNontuitionChargeSummary"))
                .assertThat().body("ReportTitle[77]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[77]", equalTo("ACAD1025"))
                .assertThat().body("Title[77]", equalTo("Miscellaneous Student Accounts - Non-Tuition Charge Summary"))
                .assertThat().body("Uid[77]", equalTo("2ad99fff-4c5d-4c70-ab7c-6b18923616d4"))
                .assertThat().body("ShortDefinition[77]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiMyReports() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[78]", equalTo("MyReports"))
                .assertThat().body("ReportTitle[78]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[78]", equalTo("ADMIN1010"))
                .assertThat().body("Title[78]", equalTo("My Reports"))
                .assertThat().body("Uid[78]", equalTo("46af4bbf-7fbc-4a06-9c09-f6878c33eb96"))
                .assertThat().body("ShortDefinition[78]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiMyFDExpenseTransferVolumeReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[79]", equalTo("MyFDExpenseTransferVolumeReport"))
                .assertThat().body("ReportTitle[79]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[79]", equalTo("FIN1013"))
                .assertThat().body("Title[79]", equalTo("MyFD Expense Transfer Volume Report"))
                .assertThat().body("Uid[79]", equalTo("4ad810ad-2a9e-4ee5-9f15-847ccc6f5770"))
                .assertThat().body("ShortDefinition[79]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiNSFCompensationbyFiscalYear() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[80]", equalTo("NSFCompensationbyFiscalYear"))
                .assertThat().body("ReportTitle[80]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[80]", equalTo("FIN1082"))
                .assertThat().body("Title[80]", equalTo("NSF Compensation by Fiscal Year"))
                .assertThat().body("Uid[80]", equalTo("424a91e2-9b46-41a6-b4bb-03bcd21babde"))
                .assertThat().body("ShortDefinition[80]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiOrganizationBudgetRelationshipReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[81]", equalTo("OrganizationBudgetRelationshipReport"))
                .assertThat().body("ReportTitle[81]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[81]", equalTo("FIN1019"))
                .assertThat().body("Title[81]", equalTo("Organization-Budget Relationship Report"))
                .assertThat().body("Uid[81]", equalTo("81ac0b4e-1833-40e5-aba3-0d1a0a4dfc6c"))
                .assertThat().body("ShortDefinition[81]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiPaidFTEbyFundingSource() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[82]", equalTo("PaidFTEbyFundingSource"))
                .assertThat().body("ReportTitle[82]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[82]", equalTo("FIN1026"))
                .assertThat().body("Title[82]", equalTo("Paid FTE by Funding Source"))
                .assertThat().body("Uid[82]", equalTo("31b1281c-6276-4ca4-8826-95aa037c7129"))
                .assertThat().body("ShortDefinition[82]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiPredominantlySponsorFundedFaculty() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[83]", equalTo("PredominantlySponsorFundedFaculty"))
                .assertThat().body("ReportTitle[83]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[83]", equalTo("FIN1080"))
                .assertThat().body("Title[83]", equalTo("Predominantly Sponsor-Funded Faculty"))
                .assertThat().body("Uid[83]", equalTo("dfb7d4d0-dbfa-46c7-9814-99afa4f7acb4"))
                .assertThat().body("ShortDefinition[83]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiRemainingBalancebyOrgCodeandCategory() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[84]", equalTo("RemainingBalancebyOrgCodeandCategory"))
                .assertThat().body("ReportTitle[84]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[84]", equalTo("FIN1001"))
                .assertThat().body("Title[84]", equalTo("Remaining Balance by OrgCode and Category"))
                .assertThat().body("Uid[84]", equalTo("05c00711-165b-4d78-9618-78cd19ea7e52"))
                .assertThat().body("ShortDefinition[84]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiResearchAwards() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[85]", equalTo("ResearchAwards"))
                .assertThat().body("ReportTitle[85]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[85]", equalTo("RAD1001"))
                .assertThat().body("Title[85]", equalTo("Research Awards"))
                .assertThat().body("Uid[85]", equalTo("e0e1d48e-c94d-4d49-aab7-aadccb9d28c5"))
                .assertThat().body("ShortDefinition[85]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiResearchBudgetExpenditures() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[86]", equalTo("ResearchBudgetExpenditures"))
                .assertThat().body("ReportTitle[86]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[86]", equalTo("RAD1006"))
                .assertThat().body("Title[86]", equalTo("Research Budget Expenditures"))
                .assertThat().body("Uid[86]", equalTo("b4367d5d-30e9-4bfc-bcf5-259269a4c011"))
                .assertThat().body("ShortDefinition[86]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiResearchProposalDetails() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[87]", equalTo("ResearchProposalDetails"))
                .assertThat().body("ReportTitle[87]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[87]", equalTo("RAD1003"))
                .assertThat().body("Title[87]", equalTo("Research Proposal Details"))
                .assertThat().body("Uid[87]", equalTo("48d793a8-40ab-458a-837a-783c10522fd3"))
                .assertThat().body("ShortDefinition[87]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiResearchProposals() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[88]", equalTo("ResearchProposals"))
                .assertThat().body("ReportTitle[88]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[88]", equalTo("RAD1002"))
                .assertThat().body("Title[88]", equalTo("Research Proposals"))
                .assertThat().body("Uid[88]", equalTo("f308bc0e-71fb-473d-90c0-cf0e8b53a5c0"))
                .assertThat().body("ShortDefinition[88]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiResearchSubawards() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[89]", equalTo("ResearchSubawards"))
                .assertThat().body("ReportTitle[89]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[89]", equalTo("RAD1005"))
                .assertThat().body("Title[89]", equalTo("Research Subawards"))
                .assertThat().body("Uid[89]", equalTo("37ce6b31-bdca-4863-ae98-7879951499ef"))
                .assertThat().body("ShortDefinition[89]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiRoomAssignmentDetailsbyDepartment() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[90]", equalTo("RoomAssignmentDetailsbyDepartment"))
                .assertThat().body("ReportTitle[90]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[90]", equalTo("MSTR1018"))
                .assertThat().body("Title[90]", equalTo("Room Assignment Details by Department"))
                .assertThat().body("Uid[90]", equalTo("8e150b05-8678-48ba-bd6e-430de2b2acac"))
                .assertThat().body("ShortDefinition[90]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiSalaryandWageExpenseDetail() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[91]", equalTo("SalaryandWageExpenseDetail"))
                .assertThat().body("ReportTitle[91]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[91]", equalTo("FIN1058"))
                .assertThat().body("Title[91]", equalTo("Salary and Wage Expense Detail"))
                .assertThat().body("Uid[91]", equalTo("b99a3f76-3c04-4175-bd14-40b4dbad5c86"))
                .assertThat().body("ShortDefinition[91]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiSalaryCapCostShareReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[92]", equalTo("SalaryCapCostShareReport"))
                .assertThat().body("ReportTitle[92]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[92]", equalTo("FIN1071"))
                .assertThat().body("Title[92]", equalTo("Salary Cap Cost Share Report"))
                .assertThat().body("Uid[92]", equalTo("e070a4e5-9e81-4a13-a8c0-a5e800f93dcf"))
                .assertThat().body("ShortDefinition[92]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiSatisfactoryProgressPolicyReport() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[93]", equalTo("SatisfactoryProgressPolicyReport"))
                .assertThat().body("ReportTitle[93]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[93]", equalTo("ACAD1082"))
                .assertThat().body("Title[93]", equalTo("Satisfactory Progress Policy Report"))
                .assertThat().body("Uid[93]", equalTo("1a20ec0a-f586-4276-89fc-3394e8a80a6f"))
                .assertThat().body("ShortDefinition[93]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiSEVISImmigrationReporting() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[94]", equalTo("SEVISImmigrationReporting"))
                .assertThat().body("ReportTitle[94]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[94]", equalTo("ACAD1083"))
                .assertThat().body("Title[94]", equalTo("SEVIS Immigration Reporting"))
                .assertThat().body("Uid[94]", equalTo("9cba22cc-314f-45dc-95df-6b7599969caf"))
                .assertThat().body("ShortDefinition[94]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiSMATHistory() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[95]", equalTo("SMATHistory"))
                .assertThat().body("ReportTitle[95]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[95]", equalTo("ADMIN1011"))
                .assertThat().body("Title[95]", equalTo("SMAT History"))
                .assertThat().body("Uid[95]", equalTo("9fa9d72f-bef4-48d9-bf5a-1fb27ca72a84"))
                .assertThat().body("ShortDefinition[95]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiSpaceDatabyDepartment() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[96]", equalTo("SpaceDatabyDepartment"))
                .assertThat().body("ReportTitle[96]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[96]", equalTo("MSTR1015"))
                .assertThat().body("Title[96]", equalTo("Space Data by Department"))
                .assertThat().body("Uid[96]", equalTo("2117c782-13bf-4b6e-ba17-aff043712e0d"))
                .assertThat().body("ShortDefinition[96]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiStudentAthletesEarning() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[97]", equalTo("StudentAthletesEarning"))
                .assertThat().body("ReportTitle[97]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[97]", equalTo("PAYR1027"))
                .assertThat().body("Title[97]", equalTo("Student Athletes Earning"))
                .assertThat().body("Uid[97]", equalTo("b3898a82-318a-4ebf-81f5-911b60a059a3"))
                .assertThat().body("ShortDefinition[97]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiStudentDegreeInformation() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[98]", equalTo("StudentDegreeInformation"))
                .assertThat().body("ReportTitle[98]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[98]", equalTo("ACAD1028"))
                .assertThat().body("Title[98]", equalTo("Student Degree Information"))
                .assertThat().body("Uid[98]", equalTo("79dedeb4-b57a-4ae8-a02f-1725980af499"))
                .assertThat().body("ShortDefinition[98]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiStudentGradeReportbyCourse() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[99]", equalTo("StudentGradeReportbyCourse"))
                .assertThat().body("ReportTitle[99]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[99]", equalTo("ACAD1090"))
                .assertThat().body("Title[99]", equalTo("Student Grade Report by Curriculum, Course and Section"))
                .assertThat().body("Uid[99]", equalTo("68b3fb3b-5352-4727-8b6b-cfcb3684c3f2"))
                .assertThat().body("ShortDefinition[99]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiStudentGradeReportbyMajor() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[100]", equalTo("StudentGradeReportbyMajor"))
                .assertThat().body("ReportTitle[100]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[100]", equalTo("ACAD1080"))
                .assertThat().body("Title[100]", equalTo("Student Grade Report by Major"))
                .assertThat().body("Uid[100]", equalTo("47a40200-1012-4d66-9b97-3afe38712bb6"))
                .assertThat().body("ShortDefinition[100]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiStudentTranscriptDetail() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[101]", equalTo("StudentTranscriptDetail"))
                .assertThat().body("ReportTitle[101]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[101]", equalTo("ACAD1013"))
                .assertThat().body("Title[101]", equalTo("Student Transcript Detail"))
                .assertThat().body("Uid[101]", equalTo("f77e2ae1-8247-45ca-b246-d3b32d31275c"))
                .assertThat().body("ShortDefinition[101]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiStudyAbroadStudents() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[102]", equalTo("StudyAbroadStudents"))
                .assertThat().body("ReportTitle[102]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[102]", equalTo("ACAD1095"))
                .assertThat().body("Title[102]", equalTo("Study Abroad Students by Unit"))
                .assertThat().body("Uid[102]", equalTo("fbd04b61-b99a-464e-b99c-3670c7d6dbd9"))
                .assertThat().body("ShortDefinition[102]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiSupplierSearch() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[103]", equalTo("SupplierSearch"))
                .assertThat().body("ReportTitle[103]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[103]", equalTo("FIN1081"))
                .assertThat().body("Title[103]", equalTo("Supplier Search"))
                .assertThat().body("Uid[103]", equalTo("1a5ec751-5f00-459b-bfbd-ed11078359a6"))
                .assertThat().body("ShortDefinition[103]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiTimeScheduleInformation() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[104]", equalTo("TimeScheduleInformation"))
                .assertThat().body("ReportTitle[104]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[104]", equalTo("ACAD1033"))
                .assertThat().body("Title[104]", equalTo("Time Schedule Information"))
                .assertThat().body("Uid[104]", equalTo("220bd95d-6cbb-4f98-90e1-919e02407216"))
                .assertThat().body("ShortDefinition[104]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiTransactionSummarybyOrgCode() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[105]", equalTo("TransactionSummarybyOrgCode"))
                .assertThat().body("ReportTitle[105]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[105]", equalTo("FIN1017"))
                .assertThat().body("Title[105]", equalTo("Transaction Summary by OrgCode"))
                .assertThat().body("Uid[105]", equalTo("01ed49cb-de6d-4427-88a2-42f90f8ec91c"))
                .assertThat().body("ShortDefinition[105]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiUndergraduateDegreeApplicants() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[106]", equalTo("UndergraduateDegreeApplicants"))
                .assertThat().body("ReportTitle[106]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[106]", equalTo("ACAD1012"))
                .assertThat().body("Title[106]", equalTo("Undergraduate Degree Applicants"))
                .assertThat().body("Uid[106]", equalTo("5b6f609e-a8a8-45d0-8ab5-5d673402b8ec"))
                .assertThat().body("ShortDefinition[106]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiUndergraduatesEligibletoRegister() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[108]", equalTo("UndergraduatesEligibletoRegister"))
                .assertThat().body("ReportTitle[108]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[108]", equalTo("ACAD1081"))
                .assertThat().body("Title[108]", equalTo("Undergraduates Eligible to Register"))
                .assertThat().body("Uid[108]", equalTo("bbd849b0-fde2-495b-83d8-bf2ba7ff425e"))
                .assertThat().body("ShortDefinition[108]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiUseTaxBienniumSummary() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[109]", equalTo("UseTaxBienniumSummary"))
                .assertThat().body("ReportTitle[109]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[109]", equalTo("FIN1043"))
                .assertThat().body("Title[109]", equalTo("Use Tax Liability Biennium Summaries"))
                .assertThat().body("Uid[109]", equalTo("2fecd537-683f-4095-93a0-86de8b553ca6"))
                .assertThat().body("ShortDefinition[109]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiVariableReportingPeriodBudgetSummary() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[110]", equalTo("VariableReportingPeriodBudgetSummary"))
                .assertThat().body("ReportTitle[110]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[110]", equalTo("FIN1051"))
                .assertThat().body("Title[110]", equalTo("Variable Reporting Period Budget Summary"))
                .assertThat().body("Uid[110]", equalTo("5ef70177-f98c-4bc4-8a2f-ad76516eb488"))
                .assertThat().body("ShortDefinition[110]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiVariableReportingPeriodTransactionSummary() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[111]", equalTo("VariableReportingPeriodTransactionSummary"))
                .assertThat().body("ReportTitle[111]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[111]", equalTo("FIN1055"))
                .assertThat().body("Title[111]", equalTo("Variable Reporting Period Transaction Summary"))
                .assertThat().body("Uid[111]", equalTo("f8c45a5b-c8e3-4526-bf4f-d70506948611"))
                .assertThat().body("ShortDefinition[111]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiWhoAmI() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[112]", equalTo("WhoAmI"))
                .assertThat().body("ReportTitle[112]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[112]", equalTo("ADMIN1001"))
                .assertThat().body("Title[112]", equalTo("Who Am I?"))
                .assertThat().body("Uid[112]", equalTo("56de9052-ca38-4673-9f66-ca118026b143"))
                .assertThat().body("ShortDefinition[112]", IsNull.notNullValue());
    }

    @Test(priority = 3)
    public void apiWhoHasAccess() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[113]", equalTo("WhoHasAccess"))
                .assertThat().body("ReportTitle[113]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[113]", equalTo("ADMIN1002"))
                .assertThat().body("Title[113]", equalTo("Who Has Access?"))
                .assertThat().body("Uid[113]", equalTo("cf5aa954-06b3-4181-a552-72b19250aaf7"))
                .assertThat().body("ShortDefinition[113]", IsNull.notNullValue());
    }

    // TEMPLATE
/*    @Test
    public void api() {
        get("https://metadata.uw.edu/api/reports/all")
                .then()
                .statusCode(200)
                .assertThat().body("ReportKey[]", equalTo(""))
                .assertThat().body("ReportTitle[]", equalTo("Report"))
                .assertThat().body("ReportCodeNbr[]", equalTo(""))
                .assertThat().body("Title[]", equalTo(""))
                .assertThat().body("Uid[]", equalTo(""))
                .assertThat().body("ShortDefinition[]", IsNull.notNullValue());
    }*/
}
