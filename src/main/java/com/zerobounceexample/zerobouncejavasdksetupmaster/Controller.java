package com.zerobounceexample.zerobouncejavasdksetupmaster;

import com.zerobounce.ZBValidateBatchData;
import com.zerobounce.ZeroBounceSDK;
import javafx.scene.control.Button;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The controller that will handle the click events for the buttons found in the *sample.fxml* file.
 */
public class Controller {

    public Button creditsButton;
    public Button fileStatusButton;
    public Button getFileButton;
    public Button apiUsageButton;
    public Button deleteFileButton;
    public Button activityDataButton;
    public Button validateButton;
    public Button validateBatchButton;
    public Button pickFileButton;

    public void initialize() {

        System.out.println("Controller::initialize");
        ZeroBounceSDK.getInstance().initialize("dca06ad4eecb4b7fa3d188e92b4ab600");

        validateButton.setOnAction(event -> validate("<EMAIL_TO_TEST>"));

        validateBatchButton.setOnAction(event -> validateBatch());

        creditsButton.setOnAction(event -> getCredits());

        apiUsageButton.setOnAction(event -> getApiUsage());

        pickFileButton.setOnAction(event -> sendFile());

        fileStatusButton.setOnAction(event -> fileStatus("<YOUR_FILE_ID>"));

        getFileButton.setOnAction(event -> getFile("<YOUR_FILE_ID>"));

        deleteFileButton.setOnAction(event -> deleteFile("<YOUR_FILE_ID>"));

        activityDataButton.setOnAction(event -> getActivityData("<EMAIL_TO_TEST>"));
    }

    /**
     * Calls the *validate* method of the [ZeroBounceSDK].
     *
     * @param email the email address you want to validate
     */
    private void validate(String email) {
        ZeroBounceSDK.getInstance().validate(
                email,
                null,
                response -> System.out.println("Controller::validate response=" + response.toString()),
                errorMessage -> System.out.println("Controller::validate error=" + errorMessage)
        );
    }

    /**
     * Calls the *validate batch* method of the [ZeroBounceSDK].
     */
    private void validateBatch() {
        List<ZBValidateBatchData> emailsData = new ArrayList<ZBValidateBatchData>();
        emailsData.add(new ZBValidateBatchData("valid@example.com", "1.1.1.1"));
        emailsData.add(new ZBValidateBatchData("invalid@example.com", "1.1.1.1"));
        emailsData.add(new ZBValidateBatchData("disposable@example.com", null));

        ZeroBounceSDK.getInstance().validateBatch(
                emailsData,
                response -> System.out.println("Controller::validateBatch response=" + response.toString()),
                errorMessage -> System.out.println("Controller::validateBatch error=" + errorMessage)
        );
    }

    /**
     * Calls the *getCredits* method of the [ZeroBounceSDK].
     */
    private void getCredits() {
        ZeroBounceSDK.getInstance().getCredits(
                response -> System.out.println("Controller::getCredits response=" + response.toString()),
                errorMessage -> System.out.println("Controller::getCredits error=" + errorMessage)
        );
    }

    /**
     * Calls the *getApiUsage* method of the [ZeroBounceSDK].
     */
    private void getApiUsage() {
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 5 * 24 * 60 * 60 * 1000); // previous 5 days
        Date endDate = new Date();
        ZeroBounceSDK.getInstance().getApiUsage(
                startDate,
                endDate,
                response -> System.out.println("Controller::getApiUsage response=" + response.toString()),
                errorMessage -> System.out.println("Controller::getApiUsage error=" + errorMessage)
        );
    }

    /**
     * Calls the *sendFile* method of the [ZeroBounceSDK].
     */
    private void sendFile() {
        File file = new File("./email_file.csv");
        ZeroBounceSDK.getInstance().sendFile(
                file,
                1,
                new ZeroBounceSDK.SendFileOptions().setHasHeaderRow(true).setFirstNameColumn(2).setLastNameColumn(3),
                response -> System.out.println("Controller::sendFile response=" + response.toString()),
                errorMessage -> System.out.println("Controller::sendFile error=" + errorMessage)
        );
    }

    /**
     * Calls the *fileStatus* method of the [ZeroBounceSDK].
     *
     * @param fileId the id of the file you wish to check the status of
     */
    private void fileStatus(String fileId) {
        ZeroBounceSDK.getInstance().fileStatus(
                fileId,
                response -> System.out.println("Controller::fileStatus response=" + response.toString()),
                errorMessage -> System.out.println("Controller::fileStatus error=" + errorMessage)
        );
    }

    /**
     * Calls the *getFile* method of the [ZeroBounceSDK].
     *
     * @param fileId the if of the file you wish to download
     */
    private void getFile(String fileId) {
        ZeroBounceSDK.getInstance().getFile(
                fileId,
                "./downloads/file.csv",
                response -> System.out.println("Controller::getFile response=" + response.toString()),
                errorMessage -> System.out.println("Controller::getFile error=" + errorMessage)
        );
    }

    /**
     * Calls the *deleteFile* method of the [ZeroBounceSDK].
     *
     * @param fileId the id of the file you wish to delete
     */
    private void deleteFile(String fileId) {
        ZeroBounceSDK.getInstance().deleteFile(
                fileId,
                response -> System.out.println("Controller::deleteFile response=" + response.toString()),
                errorMessage -> System.out.println("Controller::deleteFile error=" + errorMessage)
        );
    }

    /**
     * Calls the *getActivityData* method of the [ZeroBounceSDK].
     *
     * @param email the email address
     */
    private void getActivityData(String email) {
        ZeroBounceSDK.getInstance().getActivityData(
                email,
                response -> System.out.println("Controller::getActivityData response=" + response.toString()),
                errorMessage -> System.out.println("Controller::getActivityData error=" + errorMessage)
        );
    }
}
