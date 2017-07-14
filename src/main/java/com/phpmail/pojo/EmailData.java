package com.phpmail.pojo;


public class EmailData {

    private String emailTo;
    private String emailSubject;
    private String companyLogoUrl;
    private String companyHomePage;
    private String greetingsText;
    private String fromText;
    private String successUrl;
    private String failureUrl;

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

    public String getCompanyHomePage() {
        return companyHomePage;
    }

    public void setCompanyHomePage(String companyHomePage) {
        this.companyHomePage = companyHomePage;
    }

    public String getGreetingsText() {
        return greetingsText;
    }

    public void setGreetingsText(String greetingsText) {
        this.greetingsText = greetingsText;
    }

    public String getFromText() {
        return fromText;
    }

    public void setFromText(String fromText) {
        this.fromText = fromText;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    @Override
    public String toString() {
        return "EmailData{" +
                "emailTo='" + emailTo + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", companyLogoUrl='" + companyLogoUrl + '\'' +
                ", companyHomePage='" + companyHomePage + '\'' +
                ", greetingsText='" + greetingsText + '\'' +
                ", fromText='" + fromText + '\'' +
                ", successUrl='" + successUrl + '\'' +
                ", failureUrl='" + failureUrl + '\'' +
                '}';
    }

}
