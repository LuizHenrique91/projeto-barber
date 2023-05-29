package br.com.podologia.service.impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AmazonSes {

    @Value("${aws.email.from}")
    private String from;
    private static final String SUBJECT = "Validação de E-mail";

    public void sendEmail(String emailTo, Integer code) {

        String HTMLBODY = "<h1>Obrigado por se cadastrar a loja Barber</h1>"
                + "<p>Seu código de confirmação de e-mail é: " + code + "</p>";

        String TEXTBODY = """
                          Obrigado por se cadastrar a loja Barber
                          Seu código de confirmação de e-mail é: %d
                          """.formatted(code);

        try {
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                    .withRegion(Regions.SA_EAST_1).build();

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(emailTo))
                    .withMessage(new Message().withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(HTMLBODY))
                                                    .withText(new Content().withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(this.from);
            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }
}
