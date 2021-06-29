package com.jobseeker.adminportal.domain;

import com.jobseeker.adminportal.common.JobExpireStatus;
import com.jobseeker.adminportal.common.JobStatus;
import com.jobseeker.adminportal.common.JobType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "JobPost")
@EntityListeners(AuditingEntityListener.class)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobId;

    private String jobTitle;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    private long salary;

    private int ageLimit;

    private int employeeLimit;

    private LocalDateTime formSubmissionDeadline;

    private int jobHour;

    private int jobDay;

    private String jobDescription;

    private String jobRequirement;

    private String jobBenefit;

    private LocalDateTime approvedDate;

    @Enumerated(EnumType.ORDINAL)
    private JobStatus jobStatus;

    @Enumerated(EnumType.ORDINAL)
    private JobExpireStatus expireStatus;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;


    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getEmployeeLimit() {
        return employeeLimit;
    }

    public void setEmployeeLimit(int employeeLimit) {
        this.employeeLimit = employeeLimit;
    }

    public LocalDateTime getFormSubmissionDeadline() {
        return formSubmissionDeadline;
    }

    public void setFormSubmissionDeadline(LocalDateTime formSubmissionDeadline) {
        this.formSubmissionDeadline = formSubmissionDeadline;
    }

    public LocalDateTime getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDateTime approvedDate) {
        this.approvedDate = approvedDate;
    }

    public int getJobHour() {
        return jobHour;
    }

    public void setJobHour(int jobHour) {
        this.jobHour = jobHour;
    }

    public int getJobDay() {
        return jobDay;
    }

    public void setJobDay(int jobDay) {
        this.jobDay = jobDay;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobRequirement() {
        return jobRequirement;
    }

    public void setJobRequirement(String jobRequirement) {
        this.jobRequirement = jobRequirement;
    }

    public String getJobBenefit() {
        return jobBenefit;
    }

    public void setJobBenefit(String jobBenefit) {
        this.jobBenefit = jobBenefit;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public JobExpireStatus getExpireStatus() {
        return expireStatus;
    }

    public void setExpireStatus(JobExpireStatus expireStatus) {
        this.expireStatus = expireStatus;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobType=" + jobType +
                ", salary=" + salary +
                ", ageLimit=" + ageLimit +
                ", employeeLimit=" + employeeLimit +
                ", formSubmissionDeadline=" + formSubmissionDeadline +
                ", jobHour=" + jobHour +
                ", jobDay=" + jobDay +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobRequirement='" + jobRequirement + '\'' +
                ", jobBenefit='" + jobBenefit + '\'' +
                ", approvedDate=" + approvedDate +
                ", jobStatus=" + jobStatus +
                ", expireStatus=" + expireStatus +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
