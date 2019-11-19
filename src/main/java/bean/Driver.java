package bean;

import Repository.JDBC;
import Util.Utility;
import entity.request.XmlRequest;
import entity.response.XmlResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.log4j.Logger;
import parse.XMLParser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import static Util.Constants.AUTH_HOSTNAME;
import static Util.Constants.AUTH_TOKEN;
import static Util.Constants.SERVICE_POST_URL;

public class Driver {

    private static final Logger log = Logger.getLogger(Driver.class);

    private JDBC jdbc = new JDBC(log);
    private XmlRequest xmlRequest;
    private XmlResponse xmlResponse;

    public void start(String requestFilePath, String responseFilePath) {
        try {
            log.info("Start");
            xmlRequest = buildRequest(requestFilePath);
            long clientId = xmlRequest.getPersonId();
            saveRequest(xmlRequest);
            post(clientId, requestFilePath, responseFilePath);
            XmlResponse response = buildResponse(responseFilePath, clientId);
            saveResponse(response);

        } catch (TimeoutException e) {
            log.error(" TimeoutException" + e.getMessage());
            log.error(e.getLocalizedMessage());
        } catch (NullPointerException e) {
            log.error(" NullPointerException" + e.getMessage());
            log.error(e.getLocalizedMessage());
        } catch (SQLException | ClassNotFoundException e) {
            log.error(" SQLException" + e.getMessage());
            log.error(e.getLocalizedMessage());
        } catch (Exception e) {
            log.error(" Exception" + e.getMessage());
            log.error(e.getLocalizedMessage());
        } catch (Throwable t) {
            log.error(" Throwable" + t.getMessage());
            log.error(t.getLocalizedMessage());
        } finally {
            log.info("End");
        }
    }

    private XmlRequest buildRequest(String filePath) throws IOException, JAXBException {
        if (Utility.fileExist(filePath, ".xml")) {
            xmlRequest = new XMLParser().deserializable(XmlRequest.class, filePath);
        } else {
            throw new NullPointerException(" No input file: " + filePath);
        }
        return xmlRequest;
    }

    private void saveRequest(XmlRequest xmlRequest) throws SQLException, ClassNotFoundException {
        jdbc.saveRequest(xmlRequest);
    }

    private XmlResponse buildResponse(String filePath, long clientId) throws IOException, JAXBException {
        if (Utility.fileExist(filePath, ".xml")) {
            xmlResponse = new XMLParser().deserializable(XmlResponse.class, filePath);
            xmlResponse.setClientId(clientId);
        } else {
            throw new NullPointerException(" No output file: " + filePath);
        }
        return xmlResponse;
    }

    private void saveResponse(XmlResponse xmlResponse) throws SQLException, ClassNotFoundException {
        jdbc.saveResponse(xmlResponse);
    }

    private void post(long clientId, String requestFilePath, String responseFilePath) throws IOException, TimeoutException {
        log.info(" Start post");
        StringBuilder strResponse = new StringBuilder();
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost(SERVICE_POST_URL);

            FileEntity entity = new FileEntity(new File(requestFilePath), ContentType.APPLICATION_XML);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/xml");
            httpPost.setHeader("Host", AUTH_HOSTNAME);
            httpPost.setHeader("Authorization", "Basic " + AUTH_TOKEN);

            try (CloseableHttpResponse response = client.execute(httpPost);
                 BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));) {
                log.info(" Read response");
                String line;
                while ((line = rd.readLine()) != null) {
                    strResponse.append(line);
                }
            }

            writeFile(strResponse.toString(), responseFilePath);
        }
        log.info(" End post");
    }

    private void writeFile(String strResponse, String responseFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(responseFilePath));) {
            writer.write(strResponse);
        }
    }

}